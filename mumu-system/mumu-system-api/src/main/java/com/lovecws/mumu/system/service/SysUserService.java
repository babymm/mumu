package com.lovecws.mumu.system.service;

import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.system.entity.SysUser;

import java.util.List;

public interface SysUserService {

	/**
	 * 查询用户
	 * @param quserName 用户名称
	 * @param qnickName 用户昵称 
	 * @param qemail 用户邮件
	 * @param qphone 用户手机号码
	 * @param userStatus 用户状态
	 * @return
	 */
	public List<SysUser> querySysUserByCondition(String quserName, String qnickName, String qemail, String qphone, String userStatus);

	/**
	 * 获取用户详情
	 * @param userId 用户id
	 * @return
	 */
	public SysUser getSysUserById(String userId);

	/**
	 * 更新用户
	 * @param user 用户实体
	 * @return
	 */
	public SysUser updateById(SysUser user);

	/**
	 * 添加用户
	 * @param user 用户实体
	 * @return  
	 */
	public SysUser addUser(SysUser user);

	/**
	 * 删除用户
	 * @param userId 用户id
	 */
	public void deleteById(String userId);

	/**
	 * 根据登录名称获取用户信息
	 * @param loginName 用户名称
	 * @return
	 */
	public SysUser getUserByUserName(String loginName);

	/**
	 * 根据手机号码获取用户
	 * @param userPhone
	 * @return
	 */
	public SysUser getUserByUserPhone(String userPhone);

	/**
	 * 获取用户信息
	 * @param userName
	 * @param userPhone
	 * @param userEmail
	 * @return
	 */
	public SysUser getUserByCondition(String userName, String userPhone, String userEmail);

	/**
	 * 分页查询
	 * @param userName 用户名称
	 * @param email 手机邮箱
	 * @param phone 手机号码
	 * @param pageNum 分页数量
	 * @param pageSize 一页数量
	 * @return
	 */
	public PageBean<SysUser> listPage(String userName, String email, String phone, int pageNum, int pageSize);

	/**
	 * 查询用户组下的用户集合
	 * @param groupId 用户组id
	 * @param userStatus
	 * @return
	 */
	public List<SysUser> querySysUserByGroupId(String groupId, String userStatus);

}
