package com.lovecws.mumu.system.controller.role;

import com.lovecws.mumu.common.core.enums.PublicEnum;
import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.response.ResponseEntity;
import com.lovecws.mumu.common.core.tree.ZTreeBean;
import com.lovecws.mumu.common.core.utils.PropertiesLoader;
import com.lovecws.mumu.system.entity.SysDDL;
import com.lovecws.mumu.system.entity.SysMenu;
import com.lovecws.mumu.system.entity.SysPermission;
import com.lovecws.mumu.system.entity.SysRole;
import com.lovecws.mumu.system.service.*;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
@RequestMapping("/system/role")
public class SystemRoleController {

	private static final Logger log=Logger.getLogger(SystemRoleController.class);
	//加载数据字典配置文件
	private static PropertiesLoader loader = new PropertiesLoader("properties/ddl.properties");
	@Autowired
	private SysRoleService roleService;
	@Autowired
	private SysDDLService ddlService;
	@Autowired
	private SysRoleMenuService roleMenuService;
	@Autowired
	private SysRolePermissionService rolePermissionService;
	@Autowired
	private SysPermissionService permissionService;
	@Autowired
	private SysMenuService menuService;
	
	/**
	 * 角色列表
	 * @return
	 */
	@RequestMapping(value={"/index"},method=RequestMethod.GET)
	public String roles(){
		return "system/role/index";
	}
	
	/**
	 * 角色列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/page"})
	public Map<String,Object> rolePage(String roleType, String roleName,int beginIndex, int pageSize){
		PageBean<SysRole> pageBean = roleService.listPage(roleType, roleName, beginIndex / pageSize + 1, pageSize);
		Map<String, Object> page = new HashMap<String, Object>();
		page.put("total", pageBean.getTotalCount());
		page.put("rows", pageBean.getRecordList());
		return page;
	}
	
	/**
	 * 添加角色
	 * @return
	 */
	@RequestMapping(value={"/add"},method=RequestMethod.GET)
	public String roleAdd(HttpServletRequest request){
		//获取角色类型数据字典
		List<SysDDL> roleTypes = ddlService.getSystemDDLByCondition("roleType");
		request.setAttribute("roleTypes", roleTypes);
		return "system/role/add";
	}
	
	/**
	 * 保存角色
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/add"},method=RequestMethod.POST)
	public ResponseEntity save(SysRole role){
		List<SysRole> roles = roleService.querySysRoleByCondition(null,role.getRoleCode(), role.getRoleName(), null);
		if(roles!=null&&roles.size()>0){
			return new ResponseEntity(500, "角色内码、角色名称不可重复", null);
		}
		try {
			role.setCreator(SecurityUtils.getSubject().getPrincipal().toString());
			roleService.addSysRole(role);
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity(500, "保存角色出现异常", null);
		}
		return new ResponseEntity(200,"保存角色成功",null);
	}
	
	/**
	 * 角色详情
	 * @param roleId 角色id
	 * @return
	 */
	@RequestMapping(value={"/view/{roleId}"},method=RequestMethod.GET)
	public String roleView(@PathVariable  String roleId,HttpServletRequest request){
		//获取角色类型数据字典
		List<SysDDL> roleTypes = ddlService.getSystemDDLByCondition("roleType");
		request.setAttribute("roleTypes", roleTypes);

		SysRole role = roleService.getSysRoleById(roleId);
		request.setAttribute("role", role);
		return "system/role/view";
	}
	
	/**
	 * 编辑角色
	 * @param roleId 角色id
	 * @return
	 */
	@RequestMapping(value={"/edit/{roleId}"},method=RequestMethod.GET)
	public String roleEdit(@PathVariable  String roleId, HttpServletRequest request){
		//获取角色类型数据字典
		List<SysDDL> roleTypes = ddlService.getSystemDDLByCondition("roleType");
		request.setAttribute("roleTypes", roleTypes);

		SysRole role = roleService.getSysRoleById(roleId);
		request.setAttribute("role", role);
		return "system/role/edit";
	}
	
	/**
	 * 更新角色
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/edit"},method=RequestMethod.PUT)
	public ResponseEntity updateRole(SysRole role){
		List<SysRole> roles = roleService.querySysRoleByCondition(null,role.getRoleCode(), role.getRoleName(), null);
		if(roles!=null&&roles.size()>0&&roles.get(0).getRoleId()!=role.getRoleId()){
			return new ResponseEntity(500, "角色内码、角色名称不可重复", null);
		}
		try {
			role.setEditor(SecurityUtils.getSubject().getPrincipal().toString());
			roleService.updateSysRoleById(role);
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity(500, "更新角色出现异常", null);
		}
		return new ResponseEntity(200, "更新角色成功", null);
	}
	
	/**
	 * 删除角色
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/delete/{roleId}"},method=RequestMethod.DELETE)
	public ResponseEntity roleDelete(@PathVariable String roleId){
		try {
			roleService.deleteById(roleId);
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity(500, "删除角色出现异常", null);
		}
		return new ResponseEntity(200, "删除角色成功", null);
	}

	/**
	 * 用户分配菜单
	 * @return
	 */
	@RequestMapping(value = {"/allowMenu"}, method = RequestMethod.GET)
	public String roleAllowMenuPage(String roleId, HttpServletRequest request) {
		request.setAttribute("roleId", roleId);
		return "system/role/allowMenu";
	}

	/**
	 * 用户菜单列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"/allowMenu/{roleId}"}, method = RequestMethod.GET)
	public List<ZTreeBean> roleAllowMenus(@PathVariable String roleId) {
		//获取所有的菜单
		List<SysMenu> allMenus = menuService.querySysMenuByCondition(null, null, null);
		//获取用户已选择的菜单
		List<SysMenu> selectedMenus = menuService.getSysMenuByRoleId(roleId, PublicEnum.NORMAL.value());
		//Ztree 树
		List<ZTreeBean> beans = new ArrayList<ZTreeBean>();
		for (SysMenu sysMenu : allMenus) {
			boolean checked = false;
			for (SysMenu selectedMenu : selectedMenus) {
				if (sysMenu.getMenuId() == selectedMenu.getMenuId()) {
					checked = true;
					break;
				}
			}
			if (sysMenu.getParentMenuId() == 0) {
				beans.add(new ZTreeBean(sysMenu.getMenuId().toString(), "topMenu", sysMenu.getMenuName(), true, "", checked));
			} else {
				beans.add(new ZTreeBean(sysMenu.getMenuId().toString(), sysMenu.getParentMenuId().toString(), sysMenu.getMenuName(), true, "", checked));
			}
		}
		beans.add(new ZTreeBean("topMenu", "0", "菜单树", true, "", "", false));
		//ZTreeBean bean=new ZTreeBean("topMenu", "0", "菜单树", true, "", "", false, beans);
		return beans;
	}

	/**
	 * 保存角色菜单
	 *
	 * @param roleId  角色id
	 * @param menuIds 菜单id（多条以逗号隔开）
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"/allowMenu"}, method = RequestMethod.POST)
	public ResponseEntity roleAllowMenu(String roleId, String menuIds) {
		String loginName= SecurityUtils.getSubject().getPrincipal().toString();
		try {
			roleMenuService.saveRoleMenu(roleId, menuIds, loginName );
		} catch (Exception e) {
			log.error(e);
			new ResponseEntity(500, "保存角色菜单出现异常", null);
		}
		return new ResponseEntity(200, "角色分配菜单操作成功", null);
	}

	/**
	 * 用户分配权限
	 * @return
	 */
	@RequestMapping(value = {"/allowPermission"}, method = RequestMethod.GET)
	public String roleAllowPermissionPage(String roleId, HttpServletRequest request) {
		request.setAttribute("roleId", roleId);
		return "system/role/allowPermission";
	}

	/**
	 * 角色权限列表列表
	 * @param roleId 角色id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"/allowPermission/{roleId}"}, method = RequestMethod.GET)
	public List<ZTreeBean> roleAllowPermissions(@PathVariable String roleId) {
		// 获取当前角色下的菜单
		List<SysMenu> selectedMenus = menuService.getSysMenuByRoleId(roleId, PublicEnum.NORMAL.value());
		// 获取所有的权限
		List<SysPermission> allPermissions = permissionService.querySysPermissionByCondition(null, null, null, PublicEnum.NORMAL.value());
		// 获取当前角色下的权限
		List<SysPermission> selectedPermissions = permissionService.getSysPermissionByRoleId(roleId, PublicEnum.NORMAL.value());
		// ztree 权限树树
		List<ZTreeBean> ztree = new ArrayList<ZTreeBean>();
		// ztree菜单树
		for (SysMenu sysMenu : selectedMenus) {
			if (sysMenu.getParentMenuId() == 0) {
				ztree.add(new ZTreeBean(sysMenu.getMenuId().toString(), "topPermission", sysMenu.getMenuName(), true, sysMenu.getMenuIcon(), true));
			} else {
				ztree.add(new ZTreeBean(sysMenu.getMenuId().toString(), sysMenu.getParentMenuId().toString(), sysMenu.getMenuName(), true, sysMenu.getMenuIcon(), true));
			}

			for (SysPermission sysPermission : allPermissions) {
				// 找到菜单下的权限
				if (sysPermission.getMenuId() == sysMenu.getMenuId()) {
					// 在判断这个权限是否被选中
					Integer permissionId = sysPermission.getPermissionId();
					boolean flag = false;
					for (SysPermission selectedPermission : selectedPermissions) {
						if (selectedPermission.getPermissionId() == permissionId) {
							flag = true;
							break;
						}
					}
					ztree.add(new ZTreeBean("p" + permissionId.toString(), sysPermission.getMenuId().toString(), sysPermission.getPermissionName(), true, null, flag));
				}
			}
		}
		ztree.add(new ZTreeBean("topPermission", "0", "权限树", true, "", "", false));
		return ztree;
	}

	/**
	 * 保存角色权限
	 * @param roleId        角色id
	 * @param permissionIds 权限id（多条以逗号隔开）
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"/allowPermission"}, method = RequestMethod.POST)
	public ResponseEntity roleAllowPermission(String roleId, String permissionIds) {
		String loginName = SecurityUtils.getSubject().getPrincipal().toString();
		try {
			rolePermissionService.saveRolePermission(roleId, permissionIds, loginName);
		} catch (Exception e) {
			log.error(e);
			new ResponseEntity(500, "保存角色权限出现异常", null);
		}
		return new ResponseEntity(200, "角色分配权限操作成功", null);
	}
}
