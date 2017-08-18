/*
 * Copyright 2015-2102 RonCoo(http://www.roncoo.com) Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lovecws.mumu.system.entity;

import com.lovecws.mumu.common.core.entity.PersistentEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * @desc 权限管理-用户
 * @author ganliang
 * @version 2016年9月10日 下午9:45:15
 */
public class SysUser extends PersistentEntity {

	private static final long serialVersionUID = 1L;

	private Integer userId;// 主键ID.

	public static final String USER_STATUS_UNACTIVE="unactive";//未激活
	public static final String USER_STATUS_ACTIVE="active";//已激活
	public static final String USER_STATUS_FORBIT="forbit";//被禁止登录
	public static final String USER_STATUS_DELETE="delete";//删除
	private String userStatus;// 状态 PublicStatusEnum

	private String creator;// 创建人.
	private Date createTime = null;// 创建时间.

	private String editor;// 修改人.
	private Date editTime;// 修改时间.
	private String remark;// 描述

	private String userName;// 登录名
	private String password; // 登录密码
	private String salt;// 盐

	private String realName; // 真实姓名
	private String nickName; // 昵称

	public static final String USER_TYPE_ADMIN="commonUser";//未激活
	public static final String USER_TYPE_COMMON="adminUser";//已激活
	public static final String USER_TYPE_OPERATOR="operatorUser";//被禁止登录
	private String type; // 用户类型（admin:超级管理员，common:普通操作员），超级管理员由系统初始化时添加，不能删除)
	private String userLike;//用户爱好

	private String email; // 邮件号码
	private String emailActive;// 激活yes；未激活no

	private String phone;// 手机号
	private String phoneActive;// 激活1；未激活0
	private String avator;// 头像地址（缩略图）

	private String sex;// unknow未知；male 男；female 女
	private Date birthday;// 生日
	private String detailArea;// 用户详细地址

	private String province;// 省
	private String city;// 市
	private String area;// 区

	public static final String USER_REGTYPE_PHONE="phone";
	public static final String USER_REGTYPE_EMAIL="email";
	public static final String USER_REGTYPE_QQ="qq";
	public static final String USER_REGTYPE_WEIXIN="weixin";
	public static final String USER_REGTYPE_WEIBO="weibo";
	private String regType;//注册方式（phone、email、qq、weixin、weibo）

	private String thirdpartyToken;//第三方登录令牌
	private String thirdpartyData;//第三方登录返回数据

	public static final String SYS_USER="SYS_USER";

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserLike() {
		return userLike;
	}

	public void setUserLike(String userLike) {
		this.userLike = userLike;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailActive() {
		return emailActive;
	}

	public void setEmailActive(String emailActive) {
		this.emailActive = emailActive;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoneActive() {
		return phoneActive;
	}

	public void setPhoneActive(String phoneActive) {
		this.phoneActive = phoneActive;
	}

	public String getAvator() {
		return avator;
	}

	public void setAvator(String avator) {
		this.avator = avator;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getDetailArea() {
		return detailArea;
	}

	public void setDetailArea(String detailArea) {
		this.detailArea = detailArea;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getRegType() {
		return regType;
	}

	public void setRegType(String regType) {
		this.regType = regType;
	}

	public String getThirdpartyToken() {
		return thirdpartyToken;
	}

	public void setThirdpartyToken(String thirdpartyToken) {
		this.thirdpartyToken = thirdpartyToken;
	}

	public String getThirdpartyData() {
		return thirdpartyData;
	}

	public void setThirdpartyData(String thirdpartyData) {
		this.thirdpartyData = thirdpartyData;
	}

	private int userGroupId;
	private String privilage;
	private String userGroupDesc;
	public String getPrivilage() {
		return privilage;
	}
	public void setPrivilage(String privilage) {
		this.privilage = privilage;
	}
	public int getUserGroupId() {
		return userGroupId;
	}
	public void setUserGroupId(int userGroupId) {
		this.userGroupId = userGroupId;
	}
	public String getUserGroupDesc() {
		return userGroupDesc;
	}
	public void setUserGroupDesc(String userGroupDesc) {
		this.userGroupDesc = userGroupDesc;
	}
	
}
