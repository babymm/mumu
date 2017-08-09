package com.lovecws.mumu.system.controller;

import com.lovecws.mumu.common.core.response.ResponseEntity;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/8/7/007.
 * 用户登录
 */
@Controller
@RequestMapping("/system")
public class SysLoginController {

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    /**
     * 用户登录
     * @return
     */
    @RequestMapping(value = "/logining",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView logining(HttpServletRequest request){
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        String error = null;
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(ExcessiveAttemptsException.class.getName().equals(exceptionClassName)){
            error = "输入错误次数太过，请稍后重试";
        } else if(DisabledAccountException.class.getName().equals(exceptionClassName)){
            error="账户被锁定，请联系管理员";
        }else if (exceptionClassName != null) {
            error = "错误提示：" + exceptionClassName;
        }
        if(error!=null){
            request.setAttribute("shiroLoginFailure", error);
            return new ModelAndView("login");
        }
        return new ModelAndView("redirect:/system/index");
    }
}
