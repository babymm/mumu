package com.lovecws.mumu.system.controller.system.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Administrator on 2017/8/15/015.
 */
@Controller
@RequestMapping("/system/register")
public class SysRegisterController {

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
     * @return
     */
    @RequestMapping(value = "/phone",method = RequestMethod.POST)
    public String registerPhone(RedirectAttributes model){
        model.addFlashAttribute("registerType", "phone");
        model.addFlashAttribute("contact", "18971336061");
        return "redirect:/system/register/success";
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
     * @return
     */
    @RequestMapping(value = "/email",method = RequestMethod.POST)
    public String registerEmail(RedirectAttributes model){
        model.addFlashAttribute("registerType", "email");
        model.addFlashAttribute("contact", "babymm@aliyun.com");
        return "redirect:/system/register/success";
    }

    /**
     * 跳转到邮箱注册页面
     * @return
     */
    @RequestMapping(value = "/success",method = RequestMethod.GET)
    public String registerSuccess(@ModelAttribute("registerType") String registerType,@ModelAttribute("contact") String contact, Model model){
        model.addAttribute("registerType", registerType);
        model.addAttribute("contact", contact);
        return "registerSuccess";
    }
}
