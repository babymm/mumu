package com.lovecws.mumu.system.controller.personal.user;

import com.lovecws.mumu.common.core.response.HttpCode;
import com.lovecws.mumu.common.core.response.ResponseEntity;
import com.lovecws.mumu.common.core.utils.StringUtil;
import com.lovecws.mumu.common.security.shiro.entity.BaseRealm;
import com.lovecws.mumu.common.security.shiro.utils.PasswordHelper;
import com.lovecws.mumu.system.entity.SysUser;
import com.lovecws.mumu.system.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/8/10/010.
 */
@Controller
@RequestMapping("/personal/user")
public class SysUserInfoController {

    @Autowired
    private SysUserService userService;

    /**
     * 进入个人信息页面
     * @return
     */
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public String  info(HttpServletRequest request){
        SysUser user=(SysUser)SecurityUtils.getSubject().getSession().getAttribute(SysUser.SYS_USER);
        request.setAttribute(SysUser.SYS_USER,user);
        System.out.println(user);
        return "personal/user/info";
    }

    /**
     * 更新个人信息
     * @param user 更新用户实体
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/info",method = RequestMethod.PUT)
    public ResponseEntity updateUserInfo(SysUser user){
        userService.updateById(user);
        user = userService.getSysUserById(String.valueOf(user.getUserId()));
        SecurityUtils.getSubject().getSession().setAttribute(SysUser.SYS_USER,user);
        return new ResponseEntity(200,"用户信息更新成功",null);
    }

    /**
     *进入到修改密码页面
     * @return
     */
    @RequestMapping(value = "/password",method = RequestMethod.GET)
    public String password(){
        return "personal/user/password";
    }

    /**
     * 更新用户密码
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param reNewPassword 重复新密码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/password",method = RequestMethod.PUT)
    public ResponseEntity updateUserPassword(String oldPassword,String newPassword,String reNewPassword){
        if(StringUtil.isEmpty(oldPassword)||StringUtil.isEmpty(newPassword)||StringUtil.isEmpty(reNewPassword)){
            return new ResponseEntity(HttpCode.PARAMETER_ERROR,null);
        }if(!newPassword.equals(reNewPassword)){
            return new ResponseEntity(400,"两次输入的密码不一致",null);
        }
        try {
            //获取session中保存的用户信息
            SysUser user= (SysUser)SecurityUtils.getSubject().getSession().getAttribute(SysUser.SYS_USER);
            //获取用户信息
            user=userService.getSysUserById(String.valueOf(user.getUserId()));
            //获取用户原来的密文密码
            String pwd = PasswordHelper.getPwd(oldPassword, user.getUserName(), user.getSalt());
            if(!pwd.equals(user.getPassword())){
                return new ResponseEntity(400, "用户密码不正确", null);
            }
            //加密新密码
            BaseRealm baseRealm = PasswordHelper.encryptPassword(new BaseRealm(user.getUserName(), newPassword));
            user.setPassword(baseRealm.getPassword());
            user.setSalt(baseRealm.getSalt());
            user.setEditor(SecurityUtils.getSubject().getPrincipal().toString());
            userService.updateById(user);
        } catch (Exception e) {
            return new ResponseEntity(500, "用户重置密码出现异常", null);
        }
        return new ResponseEntity(200,"用戶重置密码成功,请重新登录",null);
    }

}
