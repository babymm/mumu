package com.lovecws.mumu.system.controller.system.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Administrator on 2017/8/15/015.
 * 找回密码控制器
 */
@Controller
@RequestMapping("/system/iforget")
public class SysIforgetController {

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
