 package com.lovecws.mumu.system.controller.system.menu;

 import com.lovecws.mumu.common.core.enums.PublicEnum;
 import com.lovecws.mumu.common.core.page.PageBean;
 import com.lovecws.mumu.common.core.response.ResponseEntity;
 import com.lovecws.mumu.system.entity.SysMenu;
 import com.lovecws.mumu.system.entity.SysPermission;
 import com.lovecws.mumu.system.service.SysMenuService;
 import com.lovecws.mumu.system.service.SysPermissionService;
 import org.apache.log4j.Logger;
 import org.apache.shiro.SecurityUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestMethod;
 import org.springframework.web.bind.annotation.ResponseBody;

 import javax.servlet.http.HttpServletRequest;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;

@Controller
@RequestMapping("/system/menu")
public class SystemMenuController {

	private static final Logger log=Logger.getLogger(SystemMenuController.class);
	
	@Autowired
	private SysMenuService menuService;
	@Autowired
	private SysPermissionService permissionService;
	
	/**
	 * 菜单列表
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String menus(HttpServletRequest request){
		List<SysMenu> topMenus = menuService.getTopSysMenu(PublicEnum.NORMAL.value());
		request.setAttribute("topMenus", topMenus);
		return "system/menu/index";
	}
	
	/**
	 * 分页获取菜单列表
	 * @param parentMenuId 父菜单id
	 * @param menuName 菜单名称
	 * @param beginIndex 开始索引
	 * @param pageSize 一页数量
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/page"})
	public Map<String,Object> menuPage(String parentMenuId,String menuName,int beginIndex,int pageSize){
		if("0".equals(parentMenuId)){
			parentMenuId=null;
		}
		// 分页查询
		PageBean<SysMenu> pageBean = menuService.listPage(parentMenuId,menuName, beginIndex / pageSize + 1, pageSize);
		Map<String, Object> page = new HashMap<String, Object>();
		page.put("total", pageBean.getTotalCount());
		page.put("rows", pageBean.getRecordList());
	    return page;
	}
	
	/**
	 * 添加菜单
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String menuAdd(HttpServletRequest request){
		List<SysMenu> topMenus = menuService.getTopSysMenu(PublicEnum.NORMAL.value());
		request.setAttribute("topMenus", topMenus);
		return "system/menu/add";
	}
	
	/**
	 * 保存菜单
	 * @param menu 菜单实体
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ResponseEntity saveMenu(SysMenu menu){
		try {
			List<SysMenu> menus = menuService.querySysMenuByCondition(null, menu.getMenuCode(), menu.getMenuName());
			if (menus != null && menus.size() > 0) {
				return new ResponseEntity(500, "菜单内码、菜单名称不可重复", null);
			}
			menuService.addMenu(menu);
			return new ResponseEntity(200,"菜单保存成功",null);
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity(500, "保存菜单出现异常", null);
		}
	}
	
	/**
	 * 编辑菜单
	 * @param menuId 菜单id
	 * @return
	 */
	@RequestMapping(value="/view/{menuId}",method=RequestMethod.GET)
	public String menuView(@PathVariable String menuId, HttpServletRequest request){
		SysMenu menu = menuService.getSysMenuById(menuId);
		request.setAttribute("menu", menu);
		return "system/menu/view";
	}
	
	/**
	 * 编辑菜单
	 * @param menuId 菜单id
	 * @return
	 */
	@RequestMapping(value="/edit/{menuId}",method=RequestMethod.GET)
	public String menuEdit(@PathVariable String menuId,HttpServletRequest request){
		SysMenu menu = menuService.getSysMenuById(menuId);
		request.setAttribute("menu", menu);
		
		List<SysMenu> topMenus = menuService.getTopSysMenu(PublicEnum.NORMAL.value());
		Iterator<SysMenu> iterator = topMenus.iterator();
		while(iterator.hasNext()){
			SysMenu sysMenu = iterator.next();
			if(sysMenu.getMenuId()==Integer.parseInt(menuId)){
				iterator.remove();
			}
		}
		request.setAttribute("topMenus", topMenus);
		return "system/menu/edit";
	}
	
	/**
	 * 更新菜单
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/edit",method=RequestMethod.PUT)
	public ResponseEntity updateMenu(SysMenu menu){
		try {
			menuService.updateMenuById(menu);
			String loginName = SecurityUtils.getSubject().getPrincipal().toString();
			return new ResponseEntity(200, "菜单更新成功", null);
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity(500, "跟新菜单出现异常", "");
		}
	}
	
	/**
	 * 删除菜单
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delete/{menuId}",method=RequestMethod.DELETE)
	public ResponseEntity menuDelete(@PathVariable String menuId){
		try {
			menuService.deleteMenuById(menuId);
			String loginName = SecurityUtils.getSubject().getPrincipal().toString();
			return new ResponseEntity(200, "菜单删除成功", null);
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity(500, "删除菜单出现异常", "");
		}
	}
	
	/**
	 * 子菜单
	 * @return
	 */
	@RequestMapping(value="/leaf/{menuId}",method=RequestMethod.GET)
	public String menuLeaf(@PathVariable String menuId,HttpServletRequest request){
		request.setAttribute("parentMenuId", menuId);
		return "system/menu/leaf";
	}

	/**
	 * 子菜单
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/leafData",method=RequestMethod.GET)
	public Map<String, Object> menuLeafData(String parentMenuId){
		List<SysMenu> menus=menuService.querySysMenuByCondition(parentMenuId,null,null);
		Map<String, Object> page = new HashMap<String, Object>();
		page.put("total", menus.size());
		page.put("rows", menus);
		return page;
	}

	/**
	 * 进入菜单权限列表
	 * @param menuId 菜单id
	 * @return
	 */
	@RequestMapping(value = "/permission/{menuId}", method = RequestMethod.GET)
	public String menuPermission(@PathVariable String menuId, HttpServletRequest request) {
		return "system/menu/permission";
	}

	/**
	 * 获取菜单下的权限列表
	 * @param menuId 菜单id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/permissionData/{menuId}", method = RequestMethod.GET)
	public Map<String, Object> menuPermissionData(@PathVariable String menuId) {
		List<SysPermission> permissions = permissionService.querySysPermissionByCondition(menuId, null, null, PublicEnum.NORMAL.value());
		Map<String, Object> page = new HashMap<String, Object>();
		page.put("total", permissions.size());
		page.put("rows", permissions);
		return page;
	}

	/**
	 * 删除权限
	 * @param permissionId 权限id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/permission/{permissionId}", method = RequestMethod.DELETE)
	public ResponseEntity deleteMenuPermission(@PathVariable String permissionId) {
		try {
			permissionService.deletePermissionById(permissionId);
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity(500, "删除菜单权限出现异常", null);
		}
		return new ResponseEntity(200,"删除菜单权限操作成功",null);
	}

	/**
	 * 更新或者保存权限
	 * @param permission 权限实体
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/permission", method = RequestMethod.POST)
	public ResponseEntity saveMenuPermission(SysPermission permission) {
		//获取权限内码和权限名称下面的权限列表
		List<SysPermission> permissions = permissionService.querySysPermissionByCondition(null, permission.getPermissionCode(), permission.getPermissionName(), PublicEnum.NORMAL.value());
		if (permissions != null && permissions.size() > 1) {
			return new ResponseEntity(500, "权限内码、权限名称不可重复", null);
		}
		if (permissions != null && permissions.size() == 1) {
			if (permission.getPermissionId() != null && permissions.get(0).getPermissionId() != permission.getPermissionId()) {
				return new ResponseEntity(500, "权限内码、权限名称不可重复", null);
			}
		}
		try {
			if (permission.getPermissionId() == 0) {
				permission.setCreator(SecurityUtils.getSubject().getPrincipal().toString());
				permissionService.addPermission(permission);
			} else {
				permission.setEditor(SecurityUtils.getSubject().getPrincipal().toString());
				permissionService.updatePermissionById(permission);
			}
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity(500, "保存或者更新权限出现异常", null);
		}
		return new ResponseEntity(200, "保存或者更新权限操作成功", null);
	}
}
