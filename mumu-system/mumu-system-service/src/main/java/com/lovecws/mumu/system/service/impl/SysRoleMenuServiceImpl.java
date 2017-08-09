package com.lovecws.mumu.system.service.impl;

import com.lovecws.mumu.common.core.enums.PublicEnum;
import com.lovecws.mumu.system.dao.SysRoleMenuDao;
import com.lovecws.mumu.system.entity.SysRoleMenu;
import com.lovecws.mumu.system.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

	@Autowired
	private SysRoleMenuDao roleMenuDao;

	@Override
	@Transactional(readOnly = false)
	public void saveRoleMenu(String roleId, String menuIds, String creator) {
		// 删除 角色原菜单
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleId", roleId);
		roleMenuDao.delete(paramMap);
		if (menuIds != null && !"".equals(menuIds)) {
			String[] menuIdArray = menuIds.split(",");
			List<SysRoleMenu> roleMenus = new ArrayList<SysRoleMenu>();
			for (String menuId : menuIdArray) {
				roleMenus.add(new SysRoleMenu(PublicEnum.NORMAL.value(), creator, new Date(), Integer.parseInt(roleId),
						Integer.parseInt(menuId)));
			}
			// 添加角色菜单
			roleMenuDao.insert(roleMenus);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteRoleMenuByMenuId(String menuId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menuId", menuId);
		roleMenuDao.delete(paramMap);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteRoleMenuByRoleId(String roleId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleId", roleId);
		roleMenuDao.delete(paramMap);
	}

}
