package com.jeedcp.services.support;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 登录账户校验类
 * @author sheungxin
 *
 */
public class LoginNameUtils {

    /**
     * 正则匹配校验
     * @param str
     * @param reg
     * @return
     */
    public static boolean matcher(String str,String reg){
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    /**
     * 判断是否为手机号
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile){
        return matcher(mobile, "(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}");
    }

    /**
     * 判断是否为邮箱
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        return matcher(email, "\\w+@(\\w+)(\\.\\w+){1,2}");
    }
}

