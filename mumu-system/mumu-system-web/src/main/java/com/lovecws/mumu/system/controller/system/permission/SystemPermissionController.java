package com.lovecws.mumu.system.controller.system.permission;

import com.lovecws.mumu.common.core.enums.PublicEnum;
import com.lovecws.mumu.common.core.log.MumuLog;
import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.response.ResponseEntity;
import com.lovecws.mumu.system.entity.SysDDL;
import com.lovecws.mumu.system.entity.SysMenu;
import com.lovecws.mumu.system.entity.SysPermission;
import com.lovecws.mumu.system.service.SysMenuService;
import com.lovecws.mumu.system.service.SysPermissionService;
import com.lovecws.mumu.system.util.NodeUtil;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/system/permission")
public class SystemPermissionController {

	private static final Logger log=Logger.getLogger(SystemPermissionController.class);
	@Autowired
	private SysPermissionService permissionService;
	@Autowired
	private SysMenuService menuService;
	
	/**
	 * 权限列表
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String permissions(HttpServletRequest request){
		List<SysMenu> subMenus = menuService.getSubSysMenu(PublicEnum.NORMAL.value());
		request.setAttribute("subMenus", subMenus);
		return "system/permission/index";
	}
	
	/**
	 * 菜单列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/page"})
	public Map<String,Object> permissionPage(String menuId,String permissionCode,String permissionName,int beginIndex,int pageSize,HttpServletRequest request){
		// 分页查询
		PageBean<SysPermission> pageBean = permissionService.listPage(menuId,permissionCode,permissionName, beginIndex / pageSize + 1, pageSize);
		Map<String, Object> page = new HashMap<String, Object>();
		page.put("total", pageBean.getTotalCount());
		page.put("rows", pageBean.getRecordList());
		return page;
	}
	
	/**
	 * 添加权限
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String permissionAdd(HttpServletRequest request){
        List<SysMenu> sysMenus = menuService.querySysMenuByCondition(null, null, null);
        List<NodeUtil.NodeBean> nodeBeans = new ArrayList<NodeUtil.NodeBean>();
        for (SysMenu sysMenu : sysMenus) {
            nodeBeans.add(new NodeUtil.NodeBean(String.valueOf(sysMenu.getMenuId()), String.valueOf(sysMenu.getParentMenuId()), sysMenu));
        }
        List<T> list = NodeUtil.iterator(nodeBeans, "﹎﹎", "menuName");
		request.setAttribute("subMenus", list);
		return "system/permission/add";
	}
	
	/**
	 * 添加权限
	 * @return
	 */
	@ResponseBody
	@MumuLog(name = "添加权限",operater = "POST")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ResponseEntity savePermission(SysPermission permission, HttpServletRequest request){
		//获取权限内码和权限名称下面的权限列表
		List<SysPermission> permissions = permissionService.querySysPermissionByCondition(null, permission.getPermissionCode(), permission.getPermissionName(), PublicEnum.NORMAL.value());
		if(permissions!=null&&permissions.size()>0){
			return new ResponseEntity(500, "权限内码、权限名称不可重复", null);
		}
		try {
			permission.setCreator(SecurityUtils.getSubject().getPrincipal().toString());
			permissionService.addPermission(permission);
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity(500,"保存权限内码出现异常",null);
		}
		return new ResponseEntity(200, "保存权限操作成功", null);
	}
	
	/**
	 * 查看权限详情
	 * @param permissionId 权限id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/view/{permissionId}",method=RequestMethod.GET)
	public String permissionView(@PathVariable String permissionId, HttpServletRequest request){
	    /*List<SysMenu> subMenus = menuService.getSubSysMenu(PublicEnum.NORMAL.value());
		request.setAttribute("subMenus", subMenus);*/
        List<SysMenu> sysMenus = menuService.querySysMenuByCondition(null, null, null);
        List<NodeUtil.NodeBean> nodeBeans = new ArrayList<NodeUtil.NodeBean>();
        for (SysMenu sysMenu : sysMenus) {
            nodeBeans.add(new NodeUtil.NodeBean(String.valueOf(sysMenu.getMenuId()), String.valueOf(sysMenu.getParentMenuId()), sysMenu));
        }
        List<T> list = NodeUtil.iterator(nodeBeans, "﹎﹎", "menuName");
        request.setAttribute("subMenus", list);

		SysPermission permission = permissionService.getSysPermissionById(permissionId);
		request.setAttribute("permission",permission);
		return "system/permission/view";
	}
	
	/**
	 * 编辑权限
	 * @param permissionId 权限id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/edit/{permissionId}",method=RequestMethod.GET)
	public String editPermission(@PathVariable String permissionId,HttpServletRequest request){
		/*List<SysMenu> subMenus = menuService.getSubSysMenu(PublicEnum.NORMAL.value());
		request.setAttribute("subMenus", subMenus);*/
        List<SysMenu> sysMenus = menuService.querySysMenuByCondition(null, null, null);
        List<NodeUtil.NodeBean> nodeBeans = new ArrayList<NodeUtil.NodeBean>();
        for (SysMenu sysMenu : sysMenus) {
            nodeBeans.add(new NodeUtil.NodeBean(String.valueOf(sysMenu.getMenuId()), String.valueOf(sysMenu.getParentMenuId()), sysMenu));
        }
        List<T> list = NodeUtil.iterator(nodeBeans, "﹎﹎", "menuName");
        request.setAttribute("subMenus", list);
		
		SysPermission permission = permissionService.getSysPermissionById(permissionId);
		request.setAttribute("permission",permission);
		return "system/permission/edit";
	}
	
	/**
	 * 编辑权限
	 * @param permission 权限id
	 * @param request
	 * @return
	 */
	@ResponseBody
	@MumuLog(name = "编辑权限",operater = "PUT")
	@RequestMapping(value="/edit",method=RequestMethod.PUT)
	public ResponseEntity udatePermission(SysPermission permission,HttpServletRequest request){
		//获取权限内码和权限名称下面的权限列表
		List<SysPermission> permissions = permissionService.querySysPermissionByCondition(null, permission.getPermissionCode(), permission.getPermissionName(), PublicEnum.NORMAL.value());
		if(permissions!=null&&permissions.size()>1){
			return new ResponseEntity(500, "权限内码、权限名称不可重复", null);
		}
		if(permissions!=null&&permissions.size()==1){
			if(permission.getPermissionId()!=null&&permissions.get(0).getPermissionId()!=permission.getPermissionId()){
				return new ResponseEntity(500, "权限内码、权限名称不可重复", null);
			}
		}
		try {
			String loginName=SecurityUtils.getSubject().getPrincipal().toString();
			permission.setEditor(loginName);
			permissionService.updatePermissionById(permission);
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity(500, "更新权限出现异常", null);
		}
		return new ResponseEntity(200, "更新权限操作成功", null);
	}
	
	/**
	 * 删除权限
	 * @param permissionId 权限id
	 * @return
	 */
	@ResponseBody
	@MumuLog(name = "删除权限",operater = "DELETE")
	@RequestMapping(value="/delete/{permissionId}",method=RequestMethod.DELETE)
	public ResponseEntity permissionDelete(@PathVariable String permissionId){
		try {
			permissionService.deletePermissionById(permissionId);
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity(500, "删除菜单权限出现异常", null);
		}
		return new ResponseEntity(200,"删除菜单权限操作成功",null);
	}
}
