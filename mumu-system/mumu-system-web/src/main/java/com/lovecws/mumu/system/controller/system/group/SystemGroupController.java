package com.lovecws.mumu.system.controller.system.group;

import com.alibaba.fastjson.JSON;
import com.lovecws.mumu.common.core.enums.PublicEnum;
import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.response.ResponseEntity;
import com.lovecws.mumu.common.core.tree.ZTreeBean;
import com.lovecws.mumu.system.entity.*;
import com.lovecws.mumu.system.service.*;
import com.lovecws.mumu.system.util.NodeUtil;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system/group")
public class SystemGroupController {
	
	private static final Logger log=Logger.getLogger(SystemGroupController.class);
	@Autowired
	private SysGroupService groupService;
	@Autowired
	private SysOrganizeService organizeService;
	@Autowired
	private SysGroupRoleService groupRoleService;
	@Autowired
	private SysRoleService roleService;
	@Autowired
	private SysUserService userService;
	@Autowired
	private SysUserGroupService userGroupService;
	
	/**
	 * 用户组列表
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String groups(){
		return "system/group/index";
	}
	
	/**
	 * 分页获取用户组列表
	 * @param orgId 组织id
	 * @param beginIndex 开始索引
	 * @param pageSize 一页数量
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/page"})
	public Map<String,Object> groupPage(String orgId,String groupName,int beginIndex,int pageSize){
		// 分页查询
		PageBean<SysGroup> pageBean = groupService.listPage(orgId,groupName, beginIndex / pageSize + 1, pageSize);
		Map<String, Object> page = new HashMap<String, Object>();
		page.put("total", pageBean.getTotalCount());
		page.put("rows", pageBean.getRecordList());
	    return page;
	}
	
	/**
	 * 添加用户组
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(HttpServletRequest request){
		List<SysOrganize> organizes = organizeService.querySysOrganizeByCondition(null);
		List<NodeUtil.NodeBean> nodeBeans = new ArrayList<NodeUtil.NodeBean>();
		for (SysOrganize org : organizes) {
			nodeBeans.add(new NodeUtil.NodeBean(String.valueOf(org.getOrgId()), String.valueOf(org.getParentOrgId()), org));
		}
		List<T> organizesList = NodeUtil.iterator(nodeBeans, "﹎﹎", "orgName");
		request.setAttribute("organizes", organizesList);
		return "system/group/add";
	}
	
	/**
	 * 保存用户组
	 * @param orgId 机构组织id
	 * @param groupName 用户组名称
	 * @param remark 描述
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ResponseEntity saveSysGroup(int orgId,String groupName,String remark,String groupMotto,String groupHonors){
		List<SysGroup> groups=groupService.querySysGroupByCondition(null,groupName);
		if(groups!=null&&groups.size()>0){
			return new ResponseEntity(500, "用户组名称不能重复!", null);
		}
		try {
			SysGroup group=new SysGroup();
			group.setGroupName(groupName);
			group.setGroupHonors(groupHonors);
			group.setGroupMotto(groupMotto);
			group.setOrgId(orgId);
			group.setRemark(remark);
			group.setCreator(SecurityUtils.getSubject().getPrincipal().toString());
			groupService.addSysGroup(group);
			return new ResponseEntity(200, "用户组添加成功", null);
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity(500, "保存用户组出现异常!", null);
		}
	}
	
	/**
	 * 查看用户组详情
	 * @param groupId 用户组id
	 * @return
	 */
	@RequestMapping(value="/view/{groupId}",method=RequestMethod.GET)
	public String groupView(@PathVariable String groupId,HttpServletRequest request){
		SysGroup group=groupService.getSysGroupById(groupId);
		request.setAttribute("group", group);

		List<SysOrganize> organizes = organizeService.querySysOrganizeByCondition(null);
		List<NodeUtil.NodeBean> nodeBeans = new ArrayList<NodeUtil.NodeBean>();
		for (SysOrganize org : organizes) {
			nodeBeans.add(new NodeUtil.NodeBean(String.valueOf(org.getOrgId()), String.valueOf(org.getParentOrgId()), org));
		}
		List<T> organizesList = NodeUtil.iterator(nodeBeans, "﹎﹎", "orgName");
		request.setAttribute("organizes", organizesList);
		return "system/group/view";
	}
	
	/**
	 * 编辑用户组
	 * @param groupId 用户组id
	 * @return
	 */
	@RequestMapping(value="/edit/{groupId}",method=RequestMethod.GET)
	public String groupEdit(@PathVariable String groupId,HttpServletRequest request){
		SysGroup group=groupService.getSysGroupById(groupId);
		request.setAttribute("group", group);
		
		List<SysOrganize> organizes = organizeService.querySysOrganizeByCondition(null);
		List<NodeUtil.NodeBean> nodeBeans = new ArrayList<NodeUtil.NodeBean>();
		for (SysOrganize org : organizes) {
			nodeBeans.add(new NodeUtil.NodeBean(String.valueOf(org.getOrgId()), String.valueOf(org.getParentOrgId()), org));
		}
		List<T> organizesList = NodeUtil.iterator(nodeBeans, "﹎﹎", "orgName");
		request.setAttribute("organizes", organizesList);
		return "system/group/edit";
	}
	
	/**
	 * 更新用户组
	 * @param groupId 用户id
	 * @param orgId 机构id
	 * @param groupName 用户组id
	 * @param remark 描述
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/edit",method=RequestMethod.PUT)
	public ResponseEntity updateSysGroup(int groupId,int orgId,String groupName,String remark,String groupMotto,String groupHonors){
		List<SysGroup> groups=groupService.querySysGroupByCondition(null,groupName);
		if(groups!=null&&groups.size()>0&&groups.get(0).getGroupId()!=groupId){
			return new ResponseEntity(500, "用户组名称不能重复!", null);
		}
		try {
			SysGroup group=new SysGroup();
			group.setGroupId(groupId);
			group.setOrgId(orgId);
			group.setGroupName(groupName);
			group.setGroupHonors(groupHonors);
			group.setGroupMotto(groupMotto);
			group.setRemark(remark);
			group.setEditor(SecurityUtils.getSubject().getPrincipal().toString());
			groupService.updateSysGroupById(group);
			return new ResponseEntity(200, "用户组更新成功", null);
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity(500, "更新用户组出现异常!", null);
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/delete/{groupId}",method=RequestMethod.DELETE)
	public ResponseEntity groupDelete(@PathVariable String groupId){
		try {
			groupService.deleteSysGroupById(groupId);
			return new ResponseEntity(200,"删除用户组成功",null);
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity(500, "删除用户组出现异常!", null);
		}
	}

	/**
	 * 用户组分配角色
	 * @param groupId 用户组id
	 * @return
	 */
	@RequestMapping(value = {"/allowRole"}, method = RequestMethod.GET)
	public String allowRole(String groupId, HttpServletRequest request) {
		request.setAttribute("groupId", groupId);
		return "system/group/allowRole";
	}

	/**
	 * 用户组分配角色
	 *
	 * @param groupId 用户组id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"/allowRole/{groupId}"}, method = RequestMethod.GET)
	public ZTreeBean groupAllowRole(@PathVariable String groupId) {
		//获取所有的角色
		List<SysRole> allRoles = roleService.querySysRoleByCondition(null, null, null, PublicEnum.NORMAL.value());
		//获取当前用户的角色
		List<SysRole> selectedRoles = roleService.getSysRoleByGroupId(groupId, PublicEnum.NORMAL.value());
		List<ZTreeBean> beans = new ArrayList<ZTreeBean>();
		for (SysRole sysRole : allRoles) {
			boolean checked = false;
			for (SysRole selectedRole : selectedRoles) {
				if (selectedRole.getRoleId() == sysRole.getRoleId()) {
					checked = true;
					break;
				}
			}
			beans.add(new ZTreeBean(String.valueOf(sysRole.getRoleId()), "topRole", sysRole.getRoleName(), true, "", checked));
		}
		ZTreeBean bean = new ZTreeBean("topRole", "0", "角色树", true, "", "", false, beans);
		return bean;
	}

	/**
	 * 用户组分配角色
	 * @param groupId 用户组id
	 * @param roleIds 角色id集合
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"/allowRole"}, method = RequestMethod.POST)
	public ResponseEntity saveGroupRole(String groupId, String roleIds) {
		String loginName = SecurityUtils.getSubject().getPrincipal().toString();
		try {
			groupRoleService.saveGroupRole(groupId, roleIds, loginName);
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity(500, "用户组角色保存出现异常", null);
		}
		return new ResponseEntity(200, "用户组角色保存成功", null);
	}

	/**
	 * 用户组下的用户组
	 * @param groupId 用户组id
	 * @return
	 */
	@RequestMapping(value = {"/members/{groupId}"}, method = RequestMethod.GET)
	public String members(@PathVariable String groupId) {
		return "system/group/members";
	}

	/**
	 * 用户组分配角色
	 * @param groupId 用户组id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"/memberPage/{groupId}"}, method = RequestMethod.GET)
	public Map<String, Object> member(@PathVariable String groupId) {
		// 分页查询
		List<SysUser> users = userService.querySysUserByGroupId(groupId, PublicEnum.NORMAL.value());
		Map<String, Object> page = new HashMap<String, Object>();
		page.put("total", users.size());
		page.put("rows", users);
		return page;
	}

	/**
	 * 用户组添加用户页面
	 * @param groupId 用户组id
	 * @return
	 */
	@RequestMapping(value = {"/memberAdd/{groupId}"}, method = RequestMethod.GET)
	public String memberAdd(@PathVariable String groupId, HttpServletRequest request) {
		//获取该分组下的用户列表
		List<SysUser> users = userService.querySysUserByGroupId(groupId, null);
		//获取所有的用户列表
		List<SysUser> allUsers = userService.querySysUserByCondition(null, null, null, null,null);

		List<SysUser> noSelectedUsers = new ArrayList<SysUser>();
		for (SysUser sysUser : allUsers) {
			boolean exists = false;
			for (SysUser user : users) {
				if (user.getUserId() == sysUser.getUserId()) {
					exists = true;
					break;
				}
			}
			if (!exists) {
				noSelectedUsers.add(sysUser);
			}
		}
		request.setAttribute("users", noSelectedUsers);
		return "system/group/memberAdd";
	}

	/**
	 * 保存组成员
	 *
	 * @param groupId   组id
	 * @param userId    用户id
	 * @param remark    描述
	 * @param privilage 权限
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"/memberAdd"}, method = RequestMethod.POST)
	public ResponseEntity savemember(int groupId, int userId, String remark, String privilage, HttpServletRequest request) {
		try {
			SysUserGroup userGroup = new SysUserGroup();
			userGroup.setCreator(SecurityUtils.getSubject().getPrincipal().toString());
			userGroup.setGroupId(groupId);
			userGroup.setPrivilage(privilage);
			userGroup.setRemark(remark);
			userGroup.setUserId(userId);
			userGroupService.addSysUserGroup(userGroup);
			String loginName = SecurityUtils.getSubject().getPrincipal().toString();
			return new ResponseEntity(200, "添加组成员成功", null);
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity(500, "添加组成员出现异常", null);
		}
	}

	/**
	 * 移除组成员
	 *
	 * @param userGroupId id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"/memberDelete/{userGroupId}"}, method = RequestMethod.DELETE)
	public ResponseEntity memberDelete(@PathVariable String userGroupId) {
		try {
			userGroupService.deleteSysUserGroupById(userGroupId);
			String loginName = SecurityUtils.getSubject().getPrincipal().toString();
			return new ResponseEntity(200, "删除组成员成功", null);
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity(500, "移除组成员出现异常", null);
		}
	}

	/**
	 * 查看组成员详情
	 *
	 * @param userGroupId id
	 * @return
	 */
	@RequestMapping(value = {"/memberView/{userGroupId}"}, method = RequestMethod.GET)
	public String memberView(@PathVariable String userGroupId, HttpServletRequest request) {
		SysUserGroup userGroup = userGroupService.getSysUserGroupById(userGroupId);
		request.setAttribute("userGroup", userGroup);
		request.setAttribute("groupId", userGroup.getGroupId());
		return "system/group/memberView";
	}

	/**
	 * 编辑组成员信息
	 * @param userGroupId id
	 * @return
	 */
	@RequestMapping(value = {"/memberEdit/{userGroupId}"}, method = RequestMethod.GET)
	public String memberEdit(@PathVariable String userGroupId, HttpServletRequest request) {
		SysUserGroup userGroup = userGroupService.getSysUserGroupById(userGroupId);
		request.setAttribute("userGroup", userGroup);
		request.setAttribute("groupId", userGroup.getGroupId());
		return "system/group/memberEdit";
	}

	/**
	 * 更新组成员信息
	 * @param userGroupId 用户组和用户关系主键id
	 * @param privilage   权限
	 * @param remark      描述
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"/memberEdit"}, method = RequestMethod.PUT)
	public ResponseEntity updateMember(int userGroupId, String privilage, String remark, HttpServletRequest request) {
		try {
			SysUserGroup userGroup = new SysUserGroup();
			userGroup.setUserGroupId(userGroupId);
			userGroup.setEditor(SecurityUtils.getSubject().getPrincipal().toString());
			userGroup.setPrivilage(privilage);
			userGroup.setRemark(remark);
			userGroupService.updateSysUserGroupById(userGroup);
			return new ResponseEntity(200, "成功更新组成员信息", null);
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity(500, "编辑组成员信息出现异常", null);
		}
	}

	/**
	 * 查看组成员分布统计
	 * @return
	 */
	@RequestMapping(value = {"/statistics"}, method = RequestMethod.GET)
	public String statistics(HttpServletRequest request) {
		//群组分布统计
		List<SysGroup> sysGroups = groupService.queryGroupStatistics();
		int totalCount=0;
		for(SysGroup group:sysGroups){
			totalCount+=group.getUserCount();
		}
		List<Map<String,Object>> groupData=new ArrayList<Map<String,Object>>();
		for(SysGroup group:sysGroups){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("name",group.getGroupName());
			BigDecimal b=new BigDecimal(group.getUserCount()/(totalCount*1.00f)*100);
			float   f1   =  b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
			map.put("y",f1);
			groupData.add(map);
		}
		request.setAttribute("groupData", JSON.toJSONString(groupData));
		return "system/group/statistics";
	}
}
