package com.lovecws.mumu.system.controller.system.index;

import com.lovecws.mumu.common.core.response.HttpCode;
import com.lovecws.mumu.common.core.response.ResponseEntity;
import com.lovecws.mumu.common.core.utils.DateUtils;
import com.lovecws.mumu.common.core.utils.ValidateUtils;
import com.lovecws.mumu.common.email.exception.EmailException;
import com.lovecws.mumu.common.email.service.SimpleEmailService;
import com.lovecws.mumu.common.security.shiro.entity.BaseRealm;
import com.lovecws.mumu.common.security.shiro.utils.PasswordHelper;
import com.lovecws.mumu.common.sms.exception.SMSException;
import com.lovecws.mumu.common.sms.service.JPushSMSService;
import com.lovecws.mumu.system.entity.SysUser;
import com.lovecws.mumu.system.service.SysUserService;
import org.apache.commons.lang3.RandomUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Administrator on 2017/8/15/015.
 * 找回密码控制器
 */
@Controller
@RequestMapping("/system/iforget")
public class SysIforgetController {

    @Autowired
    private SimpleEmailService emailService;
    @Autowired
    private VelocityEngine velocityEngine;
    @Autowired
    private SysUserService userService;
    @Autowired
    private JPushSMSService smsService;

    /**
     * 跳转到找回密码主页面
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String iforget(){
        return "iforget";
    }

    /**
     * 跳转到手机号码来找回密码页面
     * @return
     */
    @RequestMapping(value = "/phone",method = RequestMethod.GET)
    public String iforgetByPhone(){
        return "iforgetByPhone";
    }

    /**
     * 通过手机号码来找回密码
     * @param phone 手机号码
     * @param password 密码
     * @param code 验证码
     * @param smsId 消息id
     * @param model 跳转模型
     * @param request
     * @return
     */
    @RequestMapping(value = "/phone",method = RequestMethod.POST)
    public String iforgetPhone(String phone,String password,String code,String smsId,RedirectAttributes model,HttpServletRequest request){
        //检验参数
        if(!ValidateUtils.isMobile(phone)){
            request.setAttribute("shiroLoginFailure","手机号码格式错误!");
            return "iforgetByPhone";
        }
        if(password==null||password.length()<6){
            request.setAttribute("shiroLoginFailure","密码格式错误!");
            return "iforgetByPhone";
        }
        if(code==null||smsId==null){
            request.setAttribute("shiroLoginFailure","短信验证码不能为空!");
            return "iforgetByPhone";
        }
        //验证短信验证码
        try {
            boolean validateMessage = smsService.validateMessage(smsId, code);
            if(!validateMessage){
                request.setAttribute("shiroLoginFailure","短信验证码错误!");
                return "iforgetByPhone";
            }
        } catch (SMSException e) {
            e.printStackTrace();
            request.setAttribute("shiroLoginFailure","短信验证出现异常!");
            return "iforgetByPhone";
        }
        //根据手机号码查找用户[手机号码注册之后 立即激活账户]
        List<SysUser> sysUsers = userService.querySysUserByCondition(null, null, null, phone, SysUser.USER_STATUS_ACTIVE);
        if(sysUsers==null||sysUsers.size()==0){
            request.setAttribute("shiroLoginFailure","手机号码未注册！");
            return "iforgetByPhone";
        }
        //系统异常
        if(sysUsers.size()>1){
            request.setAttribute("shiroLoginFailure","手机号码注册系统出现异常，请联系管理员！");
            return "iforgetByEmail";
        }
        SysUser sysUser = sysUsers.get(0);
        //更新用户密码
        SysUser iforgetUser=new SysUser();
        iforgetUser.setUserId(sysUser.getUserId());
        BaseRealm baseRealm = PasswordHelper.encryptPassword(new BaseRealm(sysUser.getUserName(), password));
        iforgetUser.setPassword(baseRealm.getPassword());
        iforgetUser.setSalt(baseRealm.getSalt());
        userService.updateById(iforgetUser);

        model.addFlashAttribute("registerType", "phone");
        model.addFlashAttribute("contact", phone);
        return "redirect:/system/iforget/success";
    }

    /**
     * 通过邮箱验证码来找回密码
     * @return
     */
    @RequestMapping(value = "/email",method = RequestMethod.GET)
    public String iforgetByEmail(){
        return "iforgetByEmail";
    }

    /**
     * 通过邮箱来找回密码
     * @param email 邮箱
     * @param code 邮箱验证码
     * @param password 新设置的密码
     * @param model 跳转模型
     * @param request
     * @return
     */
    @RequestMapping(value = "/email",method = RequestMethod.POST)
    public String iforgetEmail(String email,String code,String password,RedirectAttributes model,HttpServletRequest request){
        //参数检验
        if(email==null||!ValidateUtils.isEmail(email)){
            request.setAttribute("shiroLoginFailure","邮箱错误");
            return "iforgetByEmail";
        }
        if(password==null||password.length()<6){
            request.setAttribute("shiroLoginFailure","密码错误");
            return "iforgetByEmail";
        }
        //邮箱验证码校验
        String sessionCode = (String)request.getSession().getAttribute("VERIFYCODE");
        if(code==null||!code.equals(sessionCode)){
            request.setAttribute("shiroLoginFailure","邮箱验证码错误");
            return "iforgetByEmail";
        }
        //根据邮箱查找用户
        List<SysUser> sysUsers = userService.querySysUserByCondition(null, null, email, null, null);
        if(sysUsers==null||sysUsers.size()==0){
            request.setAttribute("shiroLoginFailure","邮箱未注册！");
            return "iforgetByEmail";
        }
        //删除未激活过期用户和标记删除的用户
        Iterator<SysUser> iterator = sysUsers.iterator();
        while (iterator.hasNext()){
            SysUser sysUser = iterator.next();
            if(SysUser.USER_STATUS_UNACTIVE.equals(sysUser.getUserStatus())&&DateUtils.truncatedCompareTo(new Date(),sysUser.getCreateTime(),Calendar.HOUR)>48){
                iterator.remove();
            }else if(SysUser.USER_STATUS_DELETE.equals(sysUser.getUserStatus())){
                iterator.remove();
            }
        }
        //系统异常
        if(sysUsers.size()>1){
            request.setAttribute("shiroLoginFailure","邮箱注册系统出现异常，请联系管理员！");
            return "iforgetByEmail";
        }
        if(sysUsers.size()==0){
            request.setAttribute("shiroLoginFailure","邮箱未注册，或者注册过期，请重新注册");
            return "iforgetByEmail";
        }
        SysUser sysUser = sysUsers.get(0);
        if(SysUser.USER_STATUS_UNACTIVE.equals(sysUser.getUserStatus())){
            request.setAttribute("shiroLoginFailure","账户未激活，请登录邮箱进行激活操作。");
            return "iforgetByEmail";
        }
        //更新用户密码
        SysUser iforgetUser=new SysUser();
        iforgetUser.setUserId(sysUser.getUserId());
        BaseRealm baseRealm = PasswordHelper.encryptPassword(new BaseRealm(sysUser.getUserName(), password));
        iforgetUser.setPassword(baseRealm.getPassword());
        iforgetUser.setSalt(baseRealm.getSalt());
        userService.updateById(iforgetUser);
        //跳转到找回密码成功页面
        model.addFlashAttribute("registerType", "email");
        model.addFlashAttribute("contact", email);
        return "redirect:/system/iforget/success";
    }

    /**
     * 发送邮箱验证码
     * @param email 邮箱账号
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sendEmail",method = RequestMethod.POST)
    public ResponseEntity sendEmail(String email, HttpServletRequest request){
        if(email==null||!ValidateUtils.isEmail(email)){
            return new ResponseEntity(400,"error","邮箱账号错误!");
        }
        //发送注册邮件
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath()+"/";
        Map<String,Object> modelMap=new HashMap<String,Object>();
        modelMap.put("USERNAME","baby慕慕");
        modelMap.put("LOGOIMG",basePath+"resources/img/logo.png");
        int verifyCode = RandomUtils.nextInt(100000, 999999);
        request.getSession().setAttribute("VERIFYCODE",String.valueOf(verifyCode));
        modelMap.put("VERIFYCODE",verifyCode);
        modelMap.put("IFORGOTURL",basePath+"system/iforget");
        modelMap.put("LOGINURL",basePath+"system/login");
        modelMap.put("OFFICIALURL",basePath);
        String content= VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "tpl/verifyCodeEmail.html","UTF-8",modelMap);
        try {
            boolean sendSuccess=emailService.send(email,null,"baby慕慕开放平台-验证码找回密码",content);
            if(sendSuccess){
                return new ResponseEntity(200,"success","验证码发送成功");
            }
        } catch (EmailException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(400,"error","邮箱发送失败!");
    }

    /**
     * 通过人工服务来找回密码
     * @return
     */
    @RequestMapping(value = "/service",method = RequestMethod.GET)
    public String iforgetByService(){
        return "iforgetByService";
    }

    /**
     * 通过人工服务来找回密码
     * @return
     */
    @RequestMapping(value = "/service",method = RequestMethod.POST)
    public String iforgetService(String username,String regType,String contact,String regDate,String password,RedirectAttributes model,HttpServletRequest request){
        //检验参数
        if(username==null||username.length()<6){
            request.setAttribute("shiroLoginFailure","会员名称错误!");
            return "iforgetByService";
        }
        if(regType==null||contact==null){
            request.setAttribute("shiroLoginFailure","注册联系方式不能为空!");
            return "iforgetByService";
        }
        if(password==null||password.length()<6){
            request.setAttribute("shiroLoginFailure","密码错误!");
            return "iforgetByService";
        }
        //根据会员名称查找用户
        List<SysUser> sysUsers = userService.querySysUserByCondition(username, null, null, null, null);
        if(sysUsers==null||sysUsers.size()==0){
            request.setAttribute("shiroLoginFailure","会员名称未注册!");
            return "iforgetByService";
        }
        //删除未激活过期用户和标记删除的用户
        Iterator<SysUser> iterator = sysUsers.iterator();
        while (iterator.hasNext()){
            SysUser sysUser = iterator.next();
            if(SysUser.USER_STATUS_UNACTIVE.equals(sysUser.getUserStatus())&&DateUtils.truncatedCompareTo(new Date(),sysUser.getCreateTime(),Calendar.HOUR)>48){
                iterator.remove();
            }else if(SysUser.USER_STATUS_DELETE.equals(sysUser.getUserStatus())){
                iterator.remove();
            }
        }
        //系统异常
        if(sysUsers.size()>1){
            request.setAttribute("shiroLoginFailure","注册系统出现异常，请联系管理员！");
            return "iforgetByService";
        }
        //注册过期
        if(sysUsers.size()==0){
            request.setAttribute("shiroLoginFailure","用户未注册，或者注册过期，请重新注册");
            return "iforgetByService";
        }
        //账户未激活
        SysUser sysUser = sysUsers.get(0);
        if(SysUser.USER_STATUS_UNACTIVE.equals(sysUser.getUserStatus())){
            request.setAttribute("shiroLoginFailure","账户未激活，请登录邮箱进行激活操作。");
            return "iforgetByService";
        }
        //匹配注册时间
        if(!DateUtils.formatDate(sysUser.getCreateTime()).equals(regDate)){
            request.setAttribute("shiroLoginFailure","用户信息填写不完整，无法找回密码");
            return "iforgetByService";
        }
        //匹配注册方式
        String userRegType = sysUser.getRegType();
        if(userRegType==null||(SysUser.USER_REGTYPE_PHONE.equals(userRegType)&&!contact.equals(sysUser.getPhone()))||(SysUser.USER_REGTYPE_EMAIL.equals(userRegType)&&!contact.equals(sysUser.getEmail()))){
            request.setAttribute("shiroLoginFailure","用户信息填写不完整，无法找回密码");
            return "iforgetByService";
        }
        //修改账户密码
        SysUser iforgetUser=new SysUser();
        iforgetUser.setUserId(sysUser.getUserId());
        BaseRealm baseRealm = PasswordHelper.encryptPassword(new BaseRealm(sysUser.getUserName(), password));
        iforgetUser.setPassword(baseRealm.getPassword());
        iforgetUser.setSalt(baseRealm.getSalt());
        userService.updateById(iforgetUser);

        model.addFlashAttribute("registerType", "service");
        model.addFlashAttribute("contact", contact);
        return "redirect:/system/iforget/success";
    }

    /**
     * 找回密码成功
     * @return
     */
    @RequestMapping(value = "/success",method = RequestMethod.GET)
    public String iforgetSuccess(@ModelAttribute("registerType") String registerType, @ModelAttribute("contact") String contact, Model model){
        model.addAttribute("registerType", registerType);
        model.addAttribute("contact", contact);
        return "iforgetSuccess";
    }
}
