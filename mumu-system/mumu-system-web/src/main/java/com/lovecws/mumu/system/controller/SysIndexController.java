package com.lovecws.mumu.system.controller;

import com.lovecws.mumu.system.entity.SysMenu;
import com.lovecws.mumu.system.entity.SysUser;
import com.lovecws.mumu.system.service.SysMenuService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/7/007.
 */
@Controller
@RequestMapping("/system")
public class SysIndexController {

    @Autowired
    private SysMenuService menuService;

    /**
     * 管理系统后台首页
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(HttpServletRequest request){
        String loginName = SecurityUtils.getSubject().getPrincipal().toString();
        List<SysMenu> treeMenus=new ArrayList<SysMenu>();
        //获取保存在session中的用户
        SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(SysUser.SYS_USER);
        //获取登录用户的菜单
        List<SysMenu> menus = menuService.getSysMenuByUserId(user.getUserId());
        //组建菜单树
        for (SysMenu topMenu:menus){
            //找到顶级菜单
            if(topMenu.getParentMenuId()==0){
                //顶级菜单下的子菜单
                List<SysMenu> childrens=new ArrayList<SysMenu>();
                for (SysMenu menu : menus) {
                    if (menu.getParentMenuId() == topMenu.getMenuId()) {
                        childrens.add(menu);
                    }
                }
                topMenu.setChildrens(childrens);
                treeMenus.add(topMenu);
            }
        }
        request.setAttribute("menuTree", treeMenus);
        return "index";
    }

    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String main(){
        return "main";
    }
}
