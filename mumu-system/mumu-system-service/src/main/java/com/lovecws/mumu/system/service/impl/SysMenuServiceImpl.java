package com.lovecws.mumu.system.service.impl;

import com.lovecws.mumu.common.core.enums.PublicEnum;
import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.page.PageParam;
import com.lovecws.mumu.system.dao.SysMenuDao;
import com.lovecws.mumu.system.entity.SysMenu;
import com.lovecws.mumu.system.service.SysMenuService;
import com.lovecws.mumu.system.service.SysPermissionService;
import com.lovecws.mumu.system.service.SysRoleMenuService;
import com.lovecws.mumu.system.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=true)
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuDao menuDao;
	@Autowired
	private SysRoleMenuService roleMenuService;
	@Autowired
	private SysRolePermissionService rolePermissionService;
	@Autowired
	private SysPermissionService permissionService;

	@Override
	public List<SysMenu> getTopSysMenu(String menuStatus) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("menuStatus", menuStatus);
		return menuDao.selectList("getTopSysMenu", paramMap);
	}

	@Override
	@Transactional(readOnly=false)
	public SysMenu addMenu(SysMenu menu) {
		menu.setMenuStatus(PublicEnum.NORMAL.value());
		menu.setCreateTime(new Date());
		return menuDao.insert(menu);
	}

	@Override
	public SysMenu getSysMenuById(String menuId) {
		return menuDao.getById(menuId);
	}

	@Override
	@Transactional(readOnly=false)
	public SysMenu updateMenuById(SysMenu menu) {
		menu.setEditTime(new Date());
		menuDao.update(menu);
		return menu;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteMenuById(String menuId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menuId", menuId);
		//删除角色权限
		rolePermissionService.deleteRolePermissionByMenuId(menuId);
		//删除菜单权限
		permissionService.deletePermissionByMenuId(menuId);
		//删除角色菜单
		roleMenuService.deleteRoleMenuByMenuId(menuId);
		//删除菜单
		menuDao.delete(menuId);
	}

	@Override
	public List<SysMenu> getSubSysMenu(String menuStatus) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("menuStatus", menuStatus);
		return menuDao.selectList("getSubSysMenu", paramMap);
	}

	@Override
	public List<SysMenu> getSysMenuByRoleId(String roleId,String menuStatus) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("roleId", roleId);
		paramMap.put("menuStatus", menuStatus);
		return menuDao.selectList("getSysMenuByRoleId", paramMap);
	}

	@Override
	public List<SysMenu> getSysMenuByUserId(Integer userId) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("userId", userId);
		return menuDao.selectList("getSysMenuByUserId", paramMap);
	}

	@Override
	public PageBean<SysMenu> listPage(String parentMenuId, String menuName, int pageNum, int pageSize) {
		PageParam pageParam=new PageParam(pageNum, pageSize);
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("parentMenuId", parentMenuId);
		paramMap.put("menuName", menuName);
		paramMap.put("menuStatus", PublicEnum.NORMAL.value());
		return menuDao.listPage(pageParam, paramMap);
	}

	@Override
	public List<SysMenu> querySysMenuByCondition(String parentMenuId,String menuCode, String menuName) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("menuCode", menuCode);
		paramMap.put("menuName", menuName);
		paramMap.put("menuStatus", PublicEnum.NORMAL.value());
		paramMap.put("parentMenuId", parentMenuId);
		return menuDao.listByColumn(paramMap);
	}

}
