package com.lovecws.mumu.system.controller.system.index;

import com.lovecws.mumu.common.core.log.MumuLog;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
    @RequestMapping(value = {"/login",""},method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    /**
     * 用户登录
     * @return
     */
    @MumuLog(name = "用户登录",operater = "POST")
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
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
        Map<String,String> map=new HashMap<String,String>();
        if(error!=null){
            request.setAttribute("shiroLoginFailure", error);
            map.put("code","500");
            map.put("msg","failure");
            map.put("data",error);
            return new ModelAndView("login",map);
        }
        map.put("code","200");
        map.put("msg","success");
        map.put("data","登录成功");
        return new ModelAndView("redirect:/system/index",map);
    }
}
