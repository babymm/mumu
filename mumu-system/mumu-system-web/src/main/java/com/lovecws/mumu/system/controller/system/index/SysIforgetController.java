package com.lovecws.mumu.system.controller.system.index;

import com.lovecws.mumu.common.core.response.HttpCode;
import com.lovecws.mumu.common.core.response.ResponseEntity;
import com.lovecws.mumu.common.email.exception.EmailException;
import com.lovecws.mumu.common.email.service.SimpleEmailService;
import org.apache.commons.lang3.RandomUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Administrator on 2017/8/15/015.
 * 找回密码控制器
 */
@Controller
@RequestMapping("/system/iforget")
public class SysIforgetController {

    @Autowired
    private SimpleEmailService emailService;
    //velocity模板引擎
    @Autowired
    private VelocityEngine velocityEngine;

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
     * @return
     */
    @RequestMapping(value = "/phone",method = RequestMethod.POST)
    public String iforgetPhone(RedirectAttributes model){
        model.addFlashAttribute("registerType", "phone");
        model.addFlashAttribute("contact", "18971336061");
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
     * @return
     */
    @RequestMapping(value = "/email",method = RequestMethod.POST)
    public String iforgetEmail(RedirectAttributes model){
        model.addFlashAttribute("registerType", "email");
        model.addFlashAttribute("contact", "lovercws@gmail.com");
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
        if(email!=null&&!"".equals(email)){
            return new ResponseEntity(400,"error","邮箱账号不能为空!");
        }
        //发送注册邮件
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath()+"/";
        Map<String,Object> modelMap=new HashMap<String,Object>();
        modelMap.put("USERNAME","baby慕慕");
        modelMap.put("LOGOIMG",basePath+"resources/img/logo.png");
        int verifyCode = RandomUtils.nextInt(100000, 999999);
        request.getSession().setAttribute("VERIFYCODE",verifyCode);
        modelMap.put("VERIFYCODE",verifyCode);
        modelMap.put("IFORGOTURL",basePath+"system/iforget");
        modelMap.put("LOGINURL",basePath+"system/login");
        modelMap.put("OFFICIALURL",basePath);
        String content= VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "tpl/verifyCodeEmail.html","UTF-8",modelMap);
        System.out.println(content);
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
     * 通过邮箱来找回密码
     * @return
     */
    @RequestMapping(value = "/service",method = RequestMethod.POST)
    public String iforgetService(RedirectAttributes model){
        model.addFlashAttribute("registerType", "phone");
        model.addFlashAttribute("contact", "18971336061");
        return "redirect:/system/iforget/success";
    }

    /**
     * 找回密码成功
     * @return
     */
    @RequestMapping(value = "/success",method = RequestMethod.GET)
    public String iforgetSuccess(){
        return "iforgetSuccess";
    }
}
