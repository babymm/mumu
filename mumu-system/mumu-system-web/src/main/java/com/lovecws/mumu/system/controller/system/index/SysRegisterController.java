package com.lovecws.mumu.system.controller.system.index;

import com.lovecws.mumu.common.core.response.ResponseEntity;
import com.lovecws.mumu.common.core.serialize.JavaSerializeUtil;
import com.lovecws.mumu.common.core.utils.DateUtils;
import com.lovecws.mumu.common.core.utils.SecurityUtil;
import com.lovecws.mumu.common.core.utils.ValidateUtils;
import com.lovecws.mumu.common.email.exception.EmailException;
import com.lovecws.mumu.common.email.service.SimpleEmailService;
import com.lovecws.mumu.common.security.shiro.entity.BaseRealm;
import com.lovecws.mumu.common.security.shiro.utils.PasswordHelper;
import com.lovecws.mumu.common.sms.exception.SMSException;
import com.lovecws.mumu.common.sms.service.JPushSMSService;
import com.lovecws.mumu.system.entity.SysUser;
import com.lovecws.mumu.system.service.SysUserService;
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

;

/**
 * Created by Administrator on 2017/8/15/015.
 */
@Controller
@RequestMapping("/system/register")
public class SysRegisterController {

    @Autowired
    private SimpleEmailService emailService;
    @Autowired
    private VelocityEngine velocityEngine;
    @Autowired
    private SysUserService userService;
    @Autowired
    private JPushSMSService smsService;

    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    /**
     * 跳转到手机注册页面
     * @return
     */
    @RequestMapping(value = "/phone",method = RequestMethod.GET)
    public String registerByPhone(){
        return "registerByPhone";
    }

    /**
     * 手机号码注册用户
     * @param username 会员名称
     * @param phone 手机号码
     * @param password 密码
     * @param code 短信验证码
     * @param smsId 短信消息id
     * @param model 重定向模型
     * @param request
     * @return
     */
    @RequestMapping(value = "/phone",method = RequestMethod.POST)
    public String registerPhone(String username,String phone,String password,String code,String smsId,RedirectAttributes model,HttpServletRequest request){
        //检验参数
        if(username==null||username.length()<6){
            request.setAttribute("shiroLoginFailure","会员名称格式错误!");
            return "registerByPhone";
        }
        if(!ValidateUtils.isMobile(phone)){
            request.setAttribute("shiroLoginFailure","手机号码格式错误!");
            return "registerByPhone";
        }
        if(password==null||password.length()<6){
            request.setAttribute("shiroLoginFailure","密码格式错误!");
            return "registerByPhone";
        }
        if(code==null||smsId==null){
            request.setAttribute("shiroLoginFailure","短信验证码不能为空!");
            return "registerByPhone";
        }
        //验证短信验证码
        try {
            boolean validateMessage = smsService.validateMessage(smsId, code);
            if(!validateMessage){
                request.setAttribute("shiroLoginFailure","短信验证码错误!");
                return "registerByPhone";
            }
        } catch (SMSException e) {
            e.printStackTrace();
            request.setAttribute("shiroLoginFailure","短信验证出现异常!");
            return "registerByPhone";
        }
        //检测用户名称和手机号码是否已经注册
        List<SysUser> sysUsers = userService.querySysUserByCondition(username, null, null, phone, null);
        if(sysUsers!=null&&sysUsers.size()>0){
            boolean hasExists=false,usernameExists=false,phoneExists=false;
            for (SysUser sysUser:sysUsers){
                //如果是未激活的用户
                if(SysUser.USER_STATUS_UNACTIVE.equals(sysUser.getUserStatus())){
                    //提取未过期的用户
                    if(DateUtils.compareDate(new Date(),sysUser.getCreateTime(), Calendar.HOUR)<=48){
                        if(sysUser.getUserName().equals(username)){
                            hasExists=true;
                            usernameExists=true;
                        }
                        if(sysUser.getPhone().equals(phone)){
                            hasExists=true;
                            phoneExists=true;
                        }
                    }
                }
                //如果是已经删除的用户
                else if(SysUser.USER_STATUS_DELETE.equals(sysUser.getUserStatus())){
                    continue;
                }
                //如果是 已激活的用户和被禁止登录的用户
                else{
                    if(sysUser.getUserName().equals(username)){
                        hasExists=true;
                        usernameExists=true;
                    }else if(sysUser.getPhone().equals(phone)){
                        hasExists=true;
                        phoneExists=true;
                    }
                }
            }
            //如果有账号存在  去除已经删除的账号和未激活并且已经过期的账户
            if(hasExists){
                if(usernameExists){
                    request.setAttribute("shiroLoginFailure","会员名称已经被占用!");
                }else if(phoneExists){
                    request.setAttribute("shiroLoginFailure","手机号码已经被占用!");
                }
                return "registerByPhone";
            }
        }
        //注册用户
        SysUser registerUser=new SysUser();
        registerUser.setUserStatus(SysUser.USER_STATUS_ACTIVE);
        registerUser.setPhoneActive(SysUser.USER_STATUS_ACTIVE);
        registerUser.setPhone(phone);
        registerUser.setUserName(username);
        registerUser.setRegType(SysUser.USER_REGTYPE_PHONE);
        registerUser.setType(SysUser.USER_TYPE_COMMON);
        BaseRealm baseRealm = PasswordHelper.encryptPassword(new BaseRealm(username,password));
        registerUser.setPassword(baseRealm.getPassword());
        registerUser.setSalt(baseRealm.getSalt());
        registerUser.setRemark("手机号码注册用户");
        registerUser = userService.addUser(registerUser);

        model.addAttribute("registerType", "phone");
        model.addAttribute("contact", phone);
        return "redirect:/system/register/success";
    }

    /**
     * 发送短信验证码
     * @param phone 手机号码
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sendMessage",method = RequestMethod.POST)
    public ResponseEntity sendMessage(String phone,HttpServletRequest request){
        if(!ValidateUtils.isMobile(phone)){
            return new ResponseEntity(400,"fail","手机号码格式不正确");
        }
        try {
            String smsId = smsService.sendSMS(phone);
            return new ResponseEntity(200,"success",smsId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity(500,"server error","短信发送失败");
    }

    /**
     * 跳转到邮箱注册页面
     * @return
     */
    @RequestMapping(value = "/email",method = RequestMethod.GET)
    public String registerByEmail(){
        return "registerByEmail";
    }

    /**
     * 邮箱注册用户
     * @param username 用户名称
     * @param email 邮箱
     * @param password 密码
     * @param model 重定向模型
     * @param request
     * @return
     */
    @RequestMapping(value = "/email",method = RequestMethod.POST)
    public String registerEmail(String username,String email,String password,RedirectAttributes model,HttpServletRequest request){
        //检验参数
        if(username==null||username.length()<6){
            request.setAttribute("shiroLoginFailure","会员名称格式错误!");
            return "registerByEmail";
        }
        if(!ValidateUtils.isEmail(email)){
            request.setAttribute("shiroLoginFailure","邮箱格式错误!");
            return "registerByEmail";
        }
        if(password==null||password.length()<6){
            request.setAttribute("shiroLoginFailure","密码格式错误!");
            return "registerByEmail";
        }
        //检测用户名称和邮箱是否已经注册
        List<SysUser> sysUsers = userService.querySysUserByCondition(username, null, email, null, null);
        if(sysUsers!=null&&sysUsers.size()>0){
            boolean hasExists=false,usernameExists=false,emailExists=false;
            for (SysUser sysUser:sysUsers){
                //如果是未激活的用户
                if(SysUser.USER_STATUS_UNACTIVE.equals(sysUser.getUserStatus())){
                    //提取未过期的用户
                    if(DateUtils.compareDate(new Date(),sysUser.getCreateTime(), Calendar.HOUR)<=48){
                        if(sysUser.getUserName().equals(username)){
                            hasExists=true;
                            usernameExists=true;
                        }else if(sysUser.getEmail().equals(email)){
                            hasExists=true;
                            emailExists=true;
                        }
                    }
                }
                //如果是已经删除的用户
                else if(SysUser.USER_STATUS_DELETE.equals(sysUser.getUserStatus())){
                    continue;
                }
                //如果是 已激活的用户和被禁止登录的用户
                else{
                    if(sysUser.getUserName().equals(username)){
                        hasExists=true;
                        usernameExists=true;
                    }else if(sysUser.getEmail().equals(email)){
                        hasExists=true;
                        emailExists=true;
                    }
                }
            }
            //如果有账号存在  去除已经删除的账号和未激活并且已经过期的账户
            if(hasExists){
                if(usernameExists){
                    request.setAttribute("shiroLoginFailure","会员名称已经被占用!");
                }
                if(emailExists){
                    request.setAttribute("shiroLoginFailure","邮箱已经被占用!");
                }
                return "registerByEmail";
            }
        }
        //发送注册邮件
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath()+"/";
        Map<String,Object> modelMap=new HashMap<String,Object>();
        modelMap.put("USERNAME",username);
        modelMap.put("LOGOIMG",basePath+"resources/img/logo.png");
        try {
            Map<String,Object> paramMap=new HashMap<String,Object>();
            paramMap.put("email",email);
            paramMap.put("date",new Date());
            //生成验证码
            String checkcode = SecurityUtil.encryptBASE64(JavaSerializeUtil.serialize(paramMap));
            modelMap.put("VERIFYURL", basePath + "system/register/mailprove?checkcode=" + checkcode);
            modelMap.put("LOGINURL",basePath+"system/login");
            modelMap.put("OFFICIALURL",basePath);
            String content= VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "tpl/regEmail.html","UTF-8",modelMap);

            boolean sendSuccess=emailService.send(email,null,"baby慕慕开放平台-开发者注册认证",content);
            if(sendSuccess){
                //邮件发送成功 之后再添加用户信息
                SysUser registerUser=new SysUser();
                registerUser.setUserStatus(SysUser.USER_STATUS_UNACTIVE);
                registerUser.setEmailActive(SysUser.USER_STATUS_UNACTIVE);
                registerUser.setEmail(email);
                registerUser.setUserName(username);
                registerUser.setType(SysUser.USER_TYPE_COMMON);
                registerUser.setRegType(SysUser.USER_REGTYPE_EMAIL);
                BaseRealm baseRealm = PasswordHelper.encryptPassword(new BaseRealm(username,password));
                registerUser.setPassword(baseRealm.getPassword());
                registerUser.setSalt(baseRealm.getSalt());
                registerUser.setRemark("邮箱注册用户");
                registerUser = userService.addUser(registerUser);

                model.addAttribute("registerType", "email");
                model.addAttribute("contact", email);
                return "redirect:/system/register/success";
            }
        } catch (EmailException e) {
            e.printStackTrace();
        }
        request.setAttribute("shiroLoginFailure","邮箱发送失败!");
        return "registerByEmail";
    }

    /**
     * 验证邮箱
     * @param checkcode 验证码
     * @return
     */
    @RequestMapping(value = "/mailprove",method = RequestMethod.GET)
    public String mailprove(String checkcode,RedirectAttributes model){
        model.addAttribute("registerType", "email");
        //参数检测
        if(checkcode==null||"".equals(checkcode)){
            model.addAttribute("code", 500);
            model.addAttribute("msg", "验证码不能为空!");
            return "redirect:/system/register/success";
        }
        //获取校验参数
        byte[] bytes = SecurityUtil.decryptBASE64(checkcode);
        Map<String,Object> paramMap = ( Map<String,Object>) JavaSerializeUtil.deserialize(bytes);
        String email = (String) paramMap.get("email");
        Date date = (Date) paramMap.get("date");
        model.addAttribute("contact", email);
        if(email==null||date==null||DateUtils.compareDate(new Date(),date, Calendar.HOUR)>48){
            model.addAttribute("code", 500);
            model.addAttribute("msg", "注册链接已过期,请重新注册!");
            return "redirect:/system/register/success";
        }
        //根据邮箱账号查找用户
        List<SysUser> sysUsers = userService.querySysUserByCondition(null, null, email, null, null);
        if(sysUsers==null||sysUsers.size()!=1){
            model.addAttribute("code", 500);
            model.addAttribute("msg", "邮箱激活出现异常,请联系管理员!");
            return "redirect:/system/register/success";
        }
        //检测用户是否已经激活
        SysUser sysUser = sysUsers.get(0);
        if(SysUser.USER_STATUS_ACTIVE.equals(sysUser.getUserStatus())){
            model.addAttribute("code", 500);
            model.addAttribute("msg", "邮箱已经激活,请登录!");
            return "redirect:/system/register/success";
        }
        //激活用户
        SysUser registerUser=new SysUser();
        registerUser.setUserId(sysUser.getUserId());
        registerUser.setUserStatus(SysUser.USER_STATUS_ACTIVE);
        registerUser.setEmailActive(SysUser.USER_STATUS_ACTIVE);
        userService.updateById(registerUser);

        model.addAttribute("code", 200);
        model.addAttribute("msg", "邮箱激活成功！");
        return "redirect:/system/register/success";
    }

    /**
     * 跳转到邮箱注册页面
     * @return
     */
    @RequestMapping(value = "/success",method = RequestMethod.GET)
    public String registerSuccess(@ModelAttribute("registerType") String registerType,@ModelAttribute("contact") String contact,@ModelAttribute("code") String code,@ModelAttribute("msg") String msg, Model model){
        model.addAttribute("registerType", registerType);
        model.addAttribute("contact", contact);
        model.addAttribute("code", code);
        model.addAttribute("msg", msg);
        return "registerSuccess";
    }
}
