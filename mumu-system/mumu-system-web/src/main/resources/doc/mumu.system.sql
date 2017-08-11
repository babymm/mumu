/*
Navicat MySQL Data Transfer

Source Server         : 192.168.11.25
Source Server Version : 50632
Source Host           : 192.168.11.25:3307
Source Database       : mumu.system

Target Server Type    : MYSQL
Target Server Version : 50632
File Encoding         : 65001

Date: 2017-08-11 16:27:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_ddl`
-- ----------------------------
DROP TABLE IF EXISTS `sys_ddl`;
CREATE TABLE `sys_ddl` (
  `ddl_id` int(11) NOT NULL AUTO_INCREMENT,
  `ddl_status` varchar(20) DEFAULT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `editor` varchar(20) DEFAULT NULL,
  `edit_time` datetime DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `ddl_code` varchar(50) DEFAULT NULL COMMENT '数据字典内码',
  `parent_code` varchar(100) DEFAULT NULL COMMENT '拓展数据字典值',
  `ddl_value` varchar(100) DEFAULT NULL COMMENT '数据字典值',
  `ddl_number` int(11) DEFAULT NULL COMMENT '数据字典排序',
  PRIMARY KEY (`ddl_id`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_ddl
-- ----------------------------
INSERT INTO `sys_ddl` VALUES ('113', '1', null, '2017-08-11 14:06:45', null, null, null, 'roleType', '', '角色类型', '1');
INSERT INTO `sys_ddl` VALUES ('114', '1', null, '2017-08-11 14:07:26', null, null, null, 'adminRole', 'roleType', '管理员角色', '2');
INSERT INTO `sys_ddl` VALUES ('115', '1', null, '2017-08-11 14:07:41', null, null, null, 'commonRole', 'roleType', '普通角色', '3');
INSERT INTO `sys_ddl` VALUES ('116', '1', null, '2017-08-11 14:10:41', null, null, null, 'sexType', '', '性别类型', '10');
INSERT INTO `sys_ddl` VALUES ('117', '1', null, '2017-08-11 14:11:00', null, null, null, 'm', 'sexType', '男性', '11');
INSERT INTO `sys_ddl` VALUES ('118', '1', null, '2017-08-11 14:11:14', null, null, null, 'f', 'sexType', '女性', '12');
INSERT INTO `sys_ddl` VALUES ('119', '1', null, '2017-08-11 14:11:38', null, null, null, 'u', 'sexType', '未知', '13');
INSERT INTO `sys_ddl` VALUES ('120', '1', null, '2017-08-11 14:15:01', null, null, null, 'userType', '', '用户类型', '20');
INSERT INTO `sys_ddl` VALUES ('121', '1', null, '2017-08-11 14:15:19', null, null, null, 'commonUser', 'userType', '普通用户', '21');
INSERT INTO `sys_ddl` VALUES ('122', '1', null, '2017-08-11 14:15:37', null, null, null, 'adminUser', 'userType', '管理员用户', '22');
INSERT INTO `sys_ddl` VALUES ('123', '1', null, '2017-08-11 14:15:53', null, null, null, 'operatorUser', 'userType', '操作员', '23');
INSERT INTO `sys_ddl` VALUES ('124', '1', null, '2017-08-11 14:18:16', null, null, null, 'userLike', '', '用户爱好', '30');
INSERT INTO `sys_ddl` VALUES ('125', '1', null, '2017-08-11 14:18:39', null, null, null, 'reading', 'userLike', '阅读', '31');
INSERT INTO `sys_ddl` VALUES ('126', '1', null, '2017-08-11 14:18:54', null, null, null, 'writing', 'userLike', '写作', '32');
INSERT INTO `sys_ddl` VALUES ('127', '1', null, '2017-08-11 14:19:07', null, null, null, 'lazying', 'userLike', '发呆', '33');

-- ----------------------------
-- Table structure for `sys_export_model`
-- ----------------------------
DROP TABLE IF EXISTS `sys_export_model`;
CREATE TABLE `sys_export_model` (
  `model_id` int(11) NOT NULL AUTO_INCREMENT,
  `model_status` varchar(10) DEFAULT NULL COMMENT '状态 PublicStatusEnum',
  `creator` varchar(45) DEFAULT NULL COMMENT '创建人.',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间.',
  `editor` varchar(45) DEFAULT NULL COMMENT '修改人.',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间.',
  `remark` varchar(45) DEFAULT NULL COMMENT '描述',
  `model_name` varchar(45) DEFAULT NULL COMMENT '所属模块，如用户管理为：system-user',
  `c_names` varchar(500) DEFAULT NULL COMMENT '导出字段的中文名',
  `e_names` varchar(500) DEFAULT NULL COMMENT '未导出字段的中文名',
  PRIMARY KEY (`model_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='系统导出字段表';

-- ----------------------------
-- Records of sys_export_model
-- ----------------------------
INSERT INTO `sys_export_model` VALUES ('8', '1', null, '2016-11-02 13:21:19', null, null, null, 'sys_user_log', '日志id,状态 PublicStatusEnum,创建人.,创建时间.,修改人.,修改时间.,描述,用户ID,用户名,操作类型（参与枚举:OperatorLogTypeEnum,1:增加,2:修改,3:删除,4:查询,5:登录）,IP地址,操作内容, , , , ', 'user_log_id,user_log_status,creator,create_time,editor,edit_time,remark,user_id,user_name,operate_type,ip,content,method,parameter,result,usetime');
INSERT INTO `sys_export_model` VALUES ('9', '1', null, '2016-11-02 20:07:43', null, null, null, 'sys_menu', '逐渐,状态 PublicStatusEnum,创建人.,创建时间., 修改人.,修改时间.,描述,菜单内码,菜单名称,菜单地址,菜单编号（用于显示时排序）,是否为叶子节点,菜单图标,菜单可见 show,hidden,菜单层级,父节点:一级菜单为0', 'menu_id,menu_status,creator,create_time,editor,edit_time,remark,menu_code,menu_name,menu_url,menu_num,is_leaf,menu_icon,menu_visible,level,parent_menu_id');
INSERT INTO `sys_export_model` VALUES ('10', '1', null, '2017-03-12 10:57:37', null, null, null, 'sys_organize', '组织id,组织状态,创建者,创建时间,编辑人,编辑时间,描述,组名,组织id', 'org_id,org_status,creator,create_time,editor,edit_time,remark,org_name,parent_org_id');
INSERT INTO `sys_export_model` VALUES ('12', '1', null, '2017-03-12 19:39:29', null, null, null, 'sys_user', ' ,状态 PublicStatusEnum,创建人.,创建时间.,修改人,修改时间.,描述,登录名,登录密码,盐,真实姓名,昵称,用户类型（admin:超级管理员，common:普通操作员），超级管理员由系统初始化时添加，不能删除),邮件号码,激活yes；未激活no,手机号,激活yes；未激活no,头像地址（缩略图,unknow未知；male 男；female 女,生日,用户详细地址,省,市,区', 'user_id,user_status,creator,create_time,editor,edit_time,remark,user_name,password,salt,real_name,nick_name,type,email,email_active,phone,phone_active,avator,sex,birthday,detail_area,province,city,area');

-- ----------------------------
-- Table structure for `sys_group`
-- ----------------------------
DROP TABLE IF EXISTS `sys_group`;
CREATE TABLE `sys_group` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_status` varchar(10) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `editor` varchar(50) DEFAULT NULL,
  `edit_time` datetime DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL COMMENT '描述',
  `group_name` varchar(50) DEFAULT NULL COMMENT '组名',
  `group_motto` varchar(200) DEFAULT NULL COMMENT '团队座右铭',
  `group_honors` varchar(2000) DEFAULT NULL COMMENT '团队荣誉图片',
  `org_id` int(11) DEFAULT NULL COMMENT '组织id',
  PRIMARY KEY (`group_id`),
  KEY `org_id` (`org_id`),
  CONSTRAINT `sys_group_ibfk_1` FOREIGN KEY (`org_id`) REFERENCES `sys_organize` (`org_id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_group
-- ----------------------------
INSERT INTO `sys_group` VALUES ('3', '1', 'lgan', '2016-11-11 15:31:09', null, null, '管理员组；管理整个系统数据', '管理员组', null, null, '2');
INSERT INTO `sys_group` VALUES ('4', '1', 'lgan', '2016-11-11 15:31:43', 'lgan', '2017-08-10 15:02:28', '测试用户组织', '测试数据组', null, '', '3');
INSERT INTO `sys_group` VALUES ('5', '1', 'lgan', '2017-03-12 11:12:23', 'lgan', '2017-08-10 15:05:15', '<div align=\"center\"><p style=\"text-align: left; \">这是一个测试组</p></div>', '测试组', '测试既是王道', '', '1');

-- ----------------------------
-- Table structure for `sys_group_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_group_role`;
CREATE TABLE `sys_group_role` (
  `group_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_role_status` varchar(45) DEFAULT NULL COMMENT '状态 PublicStatusEnum',
  `creator` varchar(45) DEFAULT NULL COMMENT '创建人.',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间.',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `group_id` int(11) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`group_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='系统用户角色中间表';

-- ----------------------------
-- Records of sys_group_role
-- ----------------------------
INSERT INTO `sys_group_role` VALUES ('14', '1', 'admin', '2016-11-07 13:36:44', '20', '5');
INSERT INTO `sys_group_role` VALUES ('22', '1', 'lgan', '2017-03-12 13:39:10', '19', '3');
INSERT INTO `sys_group_role` VALUES ('23', '1', 'lgan', '2017-03-12 13:59:00', '20', '4');

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_status` varchar(10) DEFAULT NULL COMMENT '状态 PublicStatusEnum',
  `creator` varchar(45) DEFAULT NULL COMMENT '创建人.',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间.',
  `editor` varchar(45) DEFAULT NULL COMMENT ' 修改人.',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间.',
  `remark` varchar(500) DEFAULT NULL COMMENT '描述',
  `menu_code` varchar(45) DEFAULT NULL COMMENT '菜单内码',
  `menu_name` varchar(45) DEFAULT NULL COMMENT '菜单名称',
  `menu_url` varchar(45) DEFAULT NULL COMMENT '菜单地址',
  `menu_num` int(11) DEFAULT NULL COMMENT '菜单编号（用于显示时排序）',
  `is_leaf` tinyint(4) DEFAULT NULL COMMENT '是否为叶子节点',
  `menu_icon` varchar(45) DEFAULT NULL COMMENT '菜单图标',
  `menu_visible` varchar(10) DEFAULT NULL COMMENT '菜单可见 show,hidden',
  `level` int(11) DEFAULT NULL COMMENT '菜单层级',
  `parent_menu_id` int(11) DEFAULT '0' COMMENT '父节点:一级菜单为0',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('44', '1', null, '2016-11-07 11:43:36', null, '2016-11-07 11:50:58', '系统管理', 'systems', '系统管理', 'system', '1000', '0', 'glyphicon glyphicon-wrench', 'show', null, '0');
INSERT INTO `sys_menu` VALUES ('46', '1', null, '2016-11-07 11:45:30', null, '2016-11-07 11:51:12', '用户管理', 'users', '用户管理', 'system/user/index', '990', '0', 'glyphicon glyphicon-user', 'show', null, '44');
INSERT INTO `sys_menu` VALUES ('47', '1', null, '2016-11-07 11:46:05', null, '2016-11-07 11:51:24', '角色管理', 'roles', '角色管理', 'system/role/index', '980', '0', 'glyphicon glyphicon-asterisk', 'show', null, '44');
INSERT INTO `sys_menu` VALUES ('48', '1', null, '2016-11-07 11:46:44', null, '2016-11-07 11:51:32', '菜单管理', 'menus', '菜单管理', 'system/menu/index', '970', '0', 'glyphicon glyphicon-align-justify', 'show', null, '44');
INSERT INTO `sys_menu` VALUES ('49', '1', null, '2016-11-07 11:47:19', null, '2016-11-07 11:51:43', '权限管理', 'permissions', '权限管理', 'system/permission/index', '960', '0', 'glyphicon glyphicon-asterisk', 'show', null, '44');
INSERT INTO `sys_menu` VALUES ('50', '1', null, '2016-11-07 11:48:39', null, '2016-11-07 11:51:56', '日志管理', 'logs', '日志管理', 'system/log/index', '950', '0', 'glyphicon glyphicon-text-width', 'show', null, '44');
INSERT INTO `sys_menu` VALUES ('51', '1', null, '2016-11-07 11:49:22', null, '2016-11-07 11:52:06', '导出设置', 'exports', '导出设置', 'system/export/index', '940', '0', 'glyphicon glyphicon-open', 'show', null, '44');
INSERT INTO `sys_menu` VALUES ('52', '1', null, '2016-11-07 11:50:08', null, '2016-11-07 11:52:15', '数据字典', 'ddls', '数据字典', 'system/ddl/index', '930', '0', 'glyphicon glyphicon-tasks', 'show', null, '44');
INSERT INTO `sys_menu` VALUES ('61', '1', null, '2016-11-10 14:18:07', null, '2017-08-10 08:50:58', '公司组织机构', 'orgs', '组织机构', 'system/organize/index', '999', '0', 'glyphicon glyphicon-align-left', 'show', null, '44');
INSERT INTO `sys_menu` VALUES ('62', '1', null, '2016-11-10 14:19:17', null, '2016-11-11 09:50:58', '用户组', 'groups', '用户群组', 'system/group/index', '998', '0', 'glyphicon glyphicon-align-right', 'show', null, '44');
INSERT INTO `sys_menu` VALUES ('63', '1', null, '2016-11-14 08:42:10', null, '2016-11-14 08:42:54', 'druid  数据库连接池数据监控', 'druids', '数据监控', 'druid/index.html', '910', '0', 'glyphicon glyphicon-calendar', 'show', null, '44');
INSERT INTO `sys_menu` VALUES ('71', '1', null, '2017-08-10 13:22:23', null, '2017-08-10 13:23:42', '个人中心，用户可以预览个人的用户信息、修改密码、接受到信息等功能。', 'personalCenter', '个人中心', 'persion', '100', '0', 'glyphicon glyphicon-user', 'show', null, '0');
INSERT INTO `sys_menu` VALUES ('72', '1', null, '2017-08-10 13:25:50', null, '2017-08-10 13:38:03', '<p>个人信息可以查看登录用户的个人信息。</p>', 'userInfo', '个人信息', 'personal/user/info', '99', '0', 'glyphicon glyphicon-asterisk', 'show', null, '71');
INSERT INTO `sys_menu` VALUES ('73', '1', null, '2017-08-10 13:27:17', null, '2017-08-10 13:38:31', '用户可以修改自己的密码。', 'changePassword', '修改密码', 'personal/user/password', '80', '0', 'glyphicon glyphicon-qrcode', 'show', null, '71');
INSERT INTO `sys_menu` VALUES ('74', '1', null, '2017-08-10 13:28:34', null, '2017-08-10 13:51:50', '我的消息，用户和用户之间可以发送消息（暂未实现）。', 'userMessage', '我的消息', 'personal/message/index', '70', '0', 'glyphicon glyphicon-th-list', 'show', null, '71');

-- ----------------------------
-- Table structure for `sys_organize`
-- ----------------------------
DROP TABLE IF EXISTS `sys_organize`;
CREATE TABLE `sys_organize` (
  `org_id` int(11) NOT NULL AUTO_INCREMENT,
  `org_status` varchar(10) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `editor` varchar(50) DEFAULT NULL,
  `edit_time` datetime DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL COMMENT '描述',
  `org_name` varchar(50) DEFAULT NULL COMMENT '组名',
  `parent_org_id` int(11) DEFAULT '0' COMMENT '组织id',
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_organize
-- ----------------------------
INSERT INTO `sys_organize` VALUES ('1', '1', 'lgan', '2016-11-10 17:01:31', null, null, '华四科技有限公司', '华四科技有限公司', '0');
INSERT INTO `sys_organize` VALUES ('2', '1', 'lgan', '2016-11-10 17:11:24', null, null, '小墨科技有限公司是华四科技有限公司分公司。', '小墨科技有限公司', '1');
INSERT INTO `sys_organize` VALUES ('3', '1', 'lgan', '2016-11-10 17:12:18', 'lgan', '2017-03-09 22:54:14', '小墨车行APP', '小墨车行APP', '2');
INSERT INTO `sys_organize` VALUES ('4', '1', 'lgan', '2016-11-10 17:12:51', null, null, '小魔车行APP；技术部门', '技术部门', '3');
INSERT INTO `sys_organize` VALUES ('5', '1', 'lgan', '2016-11-10 17:13:09', 'lgan', '2017-03-09 22:51:30', '服务端', '服务端', '4');
INSERT INTO `sys_organize` VALUES ('6', '1', 'lgan', '2016-11-10 17:13:31', null, null, 'ANDROID客户端', 'ANDROID客户端', '4');
INSERT INTO `sys_organize` VALUES ('8', '1', 'lgan', '2016-11-10 17:14:13', 'lgan', '2017-03-12 10:51:43', '前段编写静态html', 'UI前端', '4');
INSERT INTO `sys_organize` VALUES ('9', '1', 'lgan', '2016-11-10 17:14:40', 'lgan', '2016-11-11 16:16:02', '美工222', '美工', '4');
INSERT INTO `sys_organize` VALUES ('13', '1', 'lgan', '2016-11-10 21:30:48', null, null, '销售部门', '销售部门', '3');
INSERT INTO `sys_organize` VALUES ('14', '1', 'lgan', '2016-11-10 21:31:07', 'lgan', '2016-11-11 16:18:21', '销售经理222', '销售经理', '13');
INSERT INTO `sys_organize` VALUES ('15', '1', 'lgan', '2016-11-10 21:31:23', null, null, '销售人员', '销售人员', '13');

-- ----------------------------
-- Table structure for `sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_status` varchar(45) DEFAULT NULL COMMENT '状态 PublicStatusEnum',
  `creator` varchar(45) DEFAULT NULL COMMENT '创建人.',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间.',
  `editor` varchar(45) DEFAULT NULL COMMENT '修改人.',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间.',
  `remark` varchar(45) DEFAULT NULL COMMENT '描述',
  `permission_code` varchar(45) DEFAULT NULL COMMENT '权限内码',
  `permission_name` varchar(45) DEFAULT NULL COMMENT '权限名称',
  `permission` varchar(45) DEFAULT NULL COMMENT '权限标识',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单id（权限是从菜单项中派发出来的）',
  `permission_path` varchar(45) DEFAULT NULL COMMENT '权限路径',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8 COMMENT='系统权限表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('57', '1', null, '2016-11-07 12:04:00', 'lgan', '2017-03-12 20:19:31', null, 'system:user:view', '系统管理:用户管理:详情', 'system:user:view', '46', 'view');
INSERT INTO `sys_permission` VALUES ('58', '1', null, '2016-11-07 12:05:00', null, '2016-11-07 12:38:15', null, 'system:user:edit', '系统管理:用户管理:编辑', 'system:user:edit', '46', 'edit');
INSERT INTO `sys_permission` VALUES ('59', '1', null, '2016-11-07 12:06:00', null, '2016-11-07 12:38:20', null, 'system:user:delete', '系统管理:用户管理:删除', 'system:user:delete', '46', 'delete');
INSERT INTO `sys_permission` VALUES ('60', '1', null, '2016-11-07 12:11:00', null, '2016-11-07 12:38:26', null, 'system:user:add', '系统管理:用户管理:添加', 'system:user:add', '46', 'add');
INSERT INTO `sys_permission` VALUES ('61', '1', null, '2016-11-07 12:11:00', null, '2016-11-07 12:38:33', null, 'system:user:resetPassword', '系统管理:用户管理:重置密码', 'system:user:resetPassword', '46', 'resetPwd');
INSERT INTO `sys_permission` VALUES ('62', '1', null, '2016-11-07 12:12:00', null, '2016-11-07 12:38:40', null, 'system:user:allowRole', '系统管理:用户管理:分配角色', 'system:user:allowRole', '46', 'system/user/allowRole');
INSERT INTO `sys_permission` VALUES ('63', '1', null, '2016-11-07 12:12:00', null, '2016-11-07 12:38:47', null, 'system:user:export', '系统管理:用户管理:导出', 'system:user:export', '46', 'export');
INSERT INTO `sys_permission` VALUES ('64', '1', null, '2016-11-07 12:13:00', 'lgan', '2017-03-12 14:28:37', null, 'system:user:statistics', '系统管理:用户管理:统计', 'system:user:statistics', '46', 'statistics');
INSERT INTO `sys_permission` VALUES ('65', '1', null, '2016-11-07 12:15:00', null, '2016-11-07 12:39:08', null, 'system:role:view', '系统管理:角色管理:详情', 'system:role:view', '47', 'view');
INSERT INTO `sys_permission` VALUES ('66', '1', null, '2016-11-07 12:16:00', null, '2016-11-07 12:39:13', null, 'system:role:edit', '系统管理:角色管理:编辑', 'system:role:edit', '47', 'edit');
INSERT INTO `sys_permission` VALUES ('67', '1', null, '2016-11-07 12:16:00', null, '2016-11-07 12:39:17', null, 'system:role:add', '系统管理:角色管理:添加', 'system:role:add', '47', 'add');
INSERT INTO `sys_permission` VALUES ('68', '1', null, '2016-11-07 12:16:00', null, '2016-11-07 12:39:23', null, 'system:role:delete', '系统管理:角色管理:删除', 'system:role:delete', '47', 'delete');
INSERT INTO `sys_permission` VALUES ('69', '1', null, '2016-11-07 12:17:00', null, '2016-11-07 12:39:28', null, 'system:role:allowMenu', '系统管理:角色管理:分配菜单', 'system:role:allowMenu', '47', 'allowMenu');
INSERT INTO `sys_permission` VALUES ('70', '1', null, '2016-11-07 12:17:00', null, '2016-11-07 12:39:33', null, 'system:role:allowPermission', '系统管理:角色管理:分配权限', 'system:role:allowPermission', '47', 'allowPermission');
INSERT INTO `sys_permission` VALUES ('71', '1', null, '2016-11-07 12:17:00', null, '2016-11-07 12:39:45', null, 'system:role:export', '系统管理:角色管理:导出', 'system:role:export', '47', 'export');
INSERT INTO `sys_permission` VALUES ('72', '1', null, '2016-11-07 12:18:00', null, '2016-11-07 12:39:43', null, 'system:role:statistics', '系统管理:角色管理:统计', 'system:role:statistics', '47', 'statistics');
INSERT INTO `sys_permission` VALUES ('73', '1', null, '2016-11-07 12:25:00', null, '2016-11-07 12:41:31', null, 'system:permission:view', '系统管理:权限管理:详情', 'system:permission:view', '49', 'view');
INSERT INTO `sys_permission` VALUES ('74', '1', null, '2016-11-07 12:26:00', null, '2016-11-07 12:41:37', null, 'system:permission:edit', '系统管理:权限管理:编辑', 'system:permission:edit', '49', 'edit');
INSERT INTO `sys_permission` VALUES ('75', '1', null, '2016-11-07 12:26:00', null, '2016-11-07 12:41:42', null, 'system:permission:delete', '系统管理:权限管理:删除', 'system:permission:delete', '49', 'delete');
INSERT INTO `sys_permission` VALUES ('76', '1', null, '2016-11-07 12:26:00', null, '2016-11-07 12:41:50', null, 'system:permission:add', '系统管理:权限管理:添加', 'system:permission:add', '49', 'add');
INSERT INTO `sys_permission` VALUES ('79', '1', null, '2016-11-07 12:28:00', null, '2016-11-07 12:41:55', null, 'system:permission:export', '系统管理:权限管理:导出', 'system:permission:export', '49', 'export');
INSERT INTO `sys_permission` VALUES ('80', '1', null, '2016-11-07 12:28:00', null, '2016-11-07 12:42:00', null, 'system:permission:statistics', '系统管理:权限管理:统计', 'system:permission:statistics', '49', 'statistics');
INSERT INTO `sys_permission` VALUES ('81', '1', null, '2016-11-07 12:33:00', 'lgan', '2016-11-07 13:34:50', null, 'system:menu:add', '系统管理:菜单管理:添加', 'system:menu:add', '48', 'add');
INSERT INTO `sys_permission` VALUES ('82', '1', null, '2016-11-07 12:33:00', null, '2016-11-07 12:40:20', null, 'system:menu:view', '系统管理:菜单管理:详情', 'system:menu:view', '48', 'view');
INSERT INTO `sys_permission` VALUES ('83', '1', null, '2016-11-07 12:33:00', null, '2016-11-07 12:40:13', null, 'system:menu:edit', '系统管理:菜单管理:编辑', 'system:menu:edit', '48', 'edit');
INSERT INTO `sys_permission` VALUES ('84', '1', null, '2016-11-07 12:34:00', null, '2016-11-07 12:40:33', null, 'system:menu:delete', '系统管理:菜单管理:删除', 'system:menu:delete', '48', 'delete');
INSERT INTO `sys_permission` VALUES ('85', '1', null, '2016-11-07 12:34:00', null, '2016-11-07 12:40:39', null, 'system:menu:leaf', '系统管理:菜单管理:子菜单', 'system:menu:leaf', '48', 'leaf');
INSERT INTO `sys_permission` VALUES ('86', '1', null, '2016-11-07 12:34:00', null, '2016-11-07 12:40:45', null, 'system:menu:permission', '系统管理:菜单管理:菜单权限', 'system:menu:permission', '48', 'permission');
INSERT INTO `sys_permission` VALUES ('87', '1', null, '2016-11-07 12:35:00', null, '2016-11-07 12:40:52', null, 'system:menu:export', '系统管理:菜单管理:导出', 'system:menu:export', '48', 'export');
INSERT INTO `sys_permission` VALUES ('88', '1', null, '2016-11-07 12:35:00', null, '2016-11-07 12:41:17', null, 'system:menu:statistics', '系统管理:菜单管理:统计', 'system:menu:statistics', '48', 'statistics');
INSERT INTO `sys_permission` VALUES ('89', '1', null, '2016-11-07 12:42:54', null, null, null, 'system:log:view', '系统管理:日志管理:详情', 'system:log:view', '50', 'system:log:view');
INSERT INTO `sys_permission` VALUES ('90', '1', null, '2016-11-07 12:43:00', null, '2016-11-07 12:44:46', null, 'system:log:delete', '系统管理:日志管理:删除', 'system:log:delete', '50', 'delete');
INSERT INTO `sys_permission` VALUES ('91', '1', null, '2016-11-07 12:43:00', null, '2016-11-07 12:44:02', null, 'system:log:statistics', '系统管理:日志管理:统计', 'system:log:statistics', '50', 'statistics');
INSERT INTO `sys_permission` VALUES ('92', '1', null, '2016-11-07 12:44:44', null, null, null, 'system:log:export', '系统管理:日志管理:导出', 'system:log:export', '50', 'system:log:export');
INSERT INTO `sys_permission` VALUES ('93', '1', null, '2016-11-07 12:46:11', null, null, null, 'system:export:view', '系统管理:导出设置:详情', 'system:export:view', '51', 'system:export:view');
INSERT INTO `sys_permission` VALUES ('94', '1', null, '2016-11-07 12:46:30', null, null, null, 'system:export:add', '系统管理:导出设置:添加', 'system:export:add', '51', 'system:export:add');
INSERT INTO `sys_permission` VALUES ('95', '1', null, '2016-11-07 12:46:48', null, null, null, 'system:export:edit', '系统管理:导出设置:编辑', 'system:export:edit', '51', 'system:export:edit');
INSERT INTO `sys_permission` VALUES ('96', '1', null, '2016-11-07 12:47:07', null, null, null, 'system:export:delete', '系统管理:导出设置:删除', 'system:export:delete', '51', 'system:export:delete');
INSERT INTO `sys_permission` VALUES ('97', '1', null, '2016-11-07 12:47:30', null, null, null, 'system:export:export', '系统管理:导出设置:导出', 'system:export:export', '51', 'system:export:export');
INSERT INTO `sys_permission` VALUES ('98', '1', null, '2016-11-07 12:48:15', null, null, null, 'system:export:statistics', '	系统管理:导出设置:统计', 'system:export:statistics', '51', 'system:export:statistics');
INSERT INTO `sys_permission` VALUES ('99', '1', null, '2016-11-07 12:49:26', null, null, null, 'system:ddl:view', '系统管理:数据字典:详情', 'system:ddl:view', '52', 'system:ddl:view');
INSERT INTO `sys_permission` VALUES ('100', '1', null, '2016-11-07 12:49:43', null, null, null, 'system:ddl:add', '系统管理:数据字典:添加', 'system:ddl:add', '52', 'system:ddl:add');
INSERT INTO `sys_permission` VALUES ('101', '1', null, '2016-11-07 12:50:00', null, null, null, 'system:ddl:edit', '系统管理:数据字典:编辑', 'system:ddl:edit', '52', 'system:ddl:edit');
INSERT INTO `sys_permission` VALUES ('102', '1', null, '2016-11-07 12:50:18', null, null, null, 'system:ddl:delete', '系统管理:数据字典:删除', 'system:ddl:delete', '52', 'system:ddl:delete');
INSERT INTO `sys_permission` VALUES ('103', '1', null, '2016-11-07 12:50:46', null, null, null, 'system:ddl:dataset', '系统管理:数据字典:数据集', 'system:ddl:dataset', '52', 'system:ddl:dataset');
INSERT INTO `sys_permission` VALUES ('104', '1', null, '2016-11-07 12:51:06', null, null, null, 'system:ddl:export', '系统管理:数据字典:导出', 'system:ddl:export', '52', 'system:ddl:export');
INSERT INTO `sys_permission` VALUES ('105', '1', null, '2016-11-07 12:51:41', null, null, null, 'system:ddl:statistics', '系统管理:数据字典:统计', 'system:ddl:statistics', '52', 'system:ddl:statistics');
INSERT INTO `sys_permission` VALUES ('106', '1', 'lgan', '2017-03-09 21:22:00', 'lgan', '2017-03-09 21:22:47', null, 'system:organize:view', '系统管理:组织机构:详情', 'system:organize:view', '61', 'system:organize:view');
INSERT INTO `sys_permission` VALUES ('107', '1', 'lgan', '2017-03-09 21:23:21', null, null, null, 'system:organize:add', '系统管理:组织机构:添加', 'system:organize:add', '61', 'system:organize:add');
INSERT INTO `sys_permission` VALUES ('108', '1', 'lgan', '2017-03-09 21:23:41', 'lgan', '2017-08-09 15:25:31', null, 'system:organize:edit', '系统管理:组织机构:编辑', 'system:organize:edit', '61', 'system:organize:edit');
INSERT INTO `sys_permission` VALUES ('109', '1', 'lgan', '2017-03-09 21:24:00', 'lgan', '2017-03-12 11:02:57', null, 'system:organize:delete', '系统管理:组织机构:删除', 'system:organize:delete', '61', 'system:organize:delete');
INSERT INTO `sys_permission` VALUES ('110', '1', 'lgan', '2017-03-09 21:24:32', null, null, null, 'system:organize:graph', '系统管理:组织机构:组织结构图', 'system:organize:graph', '61', 'system:organize:graph');
INSERT INTO `sys_permission` VALUES ('111', '1', 'lgan', '2017-03-12 11:01:54', null, null, null, 'system:organize:export', '系统管理:组织机构:导出', 'system:organize:export', '61', 'system:organize:export');
INSERT INTO `sys_permission` VALUES ('112', '1', 'lgan', '2017-03-12 11:52:01', 'lgan', '2017-08-10 10:10:36', null, 'system:group:view', '系统管理:用户组:详情', 'system:group:view', '62', 'system:group:view');
INSERT INTO `sys_permission` VALUES ('113', '1', 'lgan', '2017-03-12 11:52:28', null, null, null, 'system:group:edit', '系统管理:用户组:编辑', 'system:group:edit', '62', 'system:group:edit');
INSERT INTO `sys_permission` VALUES ('114', '1', 'lgan', '2017-03-12 11:52:00', 'lgan', '2017-03-12 11:54:44', null, 'system:group:delete', '系统管理:用户组:删除', 'system:group:delete', '62', 'system:group:delete');
INSERT INTO `sys_permission` VALUES ('115', '1', 'lgan', '2017-03-12 11:53:22', null, null, null, 'system:group:add', '系统管理:用户组:添加', 'system:group:add', '62', 'system:group:add');
INSERT INTO `sys_permission` VALUES ('116', '1', 'lgan', '2017-03-12 11:53:49', null, null, null, 'system:group:alloRole', '系统管理:用户组:分配角色', 'system:group:alloRole', '62', 'system:group:alloRole');
INSERT INTO `sys_permission` VALUES ('118', '1', 'lgan', '2017-03-12 11:55:47', null, null, null, 'system:group:memberAdd', '系统管理:用户组:组员添加', 'system:group:memberAdd', '62', 'system:group:memberAdd');
INSERT INTO `sys_permission` VALUES ('119', '1', 'lgan', '2017-03-12 11:56:17', null, null, null, 'system:group:memberView', '系统管理:用户组:组员详情', 'system:group:memberView', '62', 'system:group:memberView');
INSERT INTO `sys_permission` VALUES ('120', '1', 'lgan', '2017-03-12 11:56:40', null, null, null, 'system:group:memberEdit', '系统管理:用户组:组员编辑', 'system:group:memberEdit', '62', 'system:group:memberEdit');
INSERT INTO `sys_permission` VALUES ('121', '1', 'lgan', '2017-03-12 11:57:04', null, null, null, 'system:group:memberDelete', '系统管理:用户组:组员删除', 'system:group:memberDelete', '62', 'system:group:memberDelete');
INSERT INTO `sys_permission` VALUES ('122', '1', 'lgan', '2017-03-12 12:08:58', null, null, null, 'system:group:export', '系统管理:用户组:导出', 'system:group:export', '62', 'system:group:export');
INSERT INTO `sys_permission` VALUES ('123', '1', 'lgan', '2017-03-12 14:56:49', null, null, null, 'system:user:regStatistics', '系统管理:用户管理:注册统计', 'system:user:regStatistics', '46', 'system:user:regStatistics');
INSERT INTO `sys_permission` VALUES ('124', '1', 'lgan', '2017-03-12 14:57:34', null, null, null, 'system:user:hareaStatistics', '系统管理:用户管理:地区分布统计', 'system:user:hareaStatistics', '46', 'system:user:hareaStatistics');
INSERT INTO `sys_permission` VALUES ('125', '1', 'lgan', '2017-03-12 14:58:22', null, null, null, 'system:user:accessStatistics', '系统管理:用户管理:访问统计', 'system:user:accessStatistics', '46', 'system:user:accessStatistics');
INSERT INTO `sys_permission` VALUES ('126', '1', 'lgan', '2017-03-12 14:58:54', null, null, null, 'system:user:sexStatistics', '系统管理:用户管理:性别统计', 'system:user:sexStatistics', '46', 'system:user:sexStatistics');
INSERT INTO `sys_permission` VALUES ('127', '1', 'lgan', '2017-03-12 14:59:27', null, null, null, 'system:user:ageStatistics', '	系统管理:用户管理:年龄统计', 'system:user:ageStatistics', '46', 'system:user:ageStatistics');
INSERT INTO `sys_permission` VALUES ('128', '1', 'lgan', '2017-03-12 21:11:46', null, null, null, 'system:ddl:datasetAdd', '	系统管理:数据字典:添加数据集', 'system:ddl:datasetAdd', '52', 'system:ddl:datasetAdd');
INSERT INTO `sys_permission` VALUES ('129', '1', 'lgan', '2017-03-12 21:12:16', null, null, null, 'system:ddl:datasetDelete', '系统管理:数据字典:删除数据集', 'system:ddl:datasetDelete', '52', 'system:ddl:datasetDelete');
INSERT INTO `sys_permission` VALUES ('130', '1', 'lgan', '2017-03-12 21:14:14', null, null, null, 'system:ddl:datasetEdit', '	系统管理:数据字典:编辑数据集', 'system:ddl:datasetEdit', '52', 'system:ddl:datasetEdit');
INSERT INTO `sys_permission` VALUES ('131', '1', 'lgan', '2017-03-17 08:54:55', null, null, null, 'official:product:view', '官网管理:产品管理:详情', 'official:product:view', '66', 'official:product:view');
INSERT INTO `sys_permission` VALUES ('132', '1', 'lgan', '2017-03-17 08:55:15', null, null, null, 'official:product:edit', '官网管理:产品管理:编辑', 'official:product:edit', '66', 'official:product:edit');
INSERT INTO `sys_permission` VALUES ('134', '1', 'lgan', '2017-03-17 08:56:00', null, null, null, 'official:product:delete', '官网管理:产品管理:删除', 'official:product:delete', '66', 'official:product:delete');
INSERT INTO `sys_permission` VALUES ('135', '1', 'lgan', '2017-03-17 08:56:23', null, null, null, 'official:product:add', '官网管理:产品管理:添加', 'official:product:add', '66', 'official:product:add');
INSERT INTO `sys_permission` VALUES ('136', '1', 'lgan', '2017-03-17 08:57:43', null, null, null, 'official:product:categoryView', '官网管理:产品管理:分类详情', 'official:product:categoryView', '66', 'official:product:categoryView');
INSERT INTO `sys_permission` VALUES ('137', '1', 'lgan', '2017-03-17 08:58:02', null, null, null, 'official:product:categoryEdit', '官网管理:产品管理:分类编辑', 'official:product:categoryEdit', '66', 'official:product:categoryEdit');
INSERT INTO `sys_permission` VALUES ('138', '1', 'lgan', '2017-03-17 09:22:38', null, null, null, 'official:product:categoryAdd', '官网管理:产品管理:分类添加', 'official:product:categoryAdd', '66', 'official:product:categoryAdd');
INSERT INTO `sys_permission` VALUES ('139', '1', 'lgan', '2017-03-17 09:23:20', null, null, null, 'official:product:categoryDelete', '	官网管理:产品管理:分类删除', 'official:product:categoryDelete', '66', 'official:product:categoryDelete');
INSERT INTO `sys_permission` VALUES ('140', '1', 'lgan', '2017-03-17 09:27:47', null, null, null, 'official:service:categoryDelete', '官网管理:服务管理:分类删除', 'official:service:categoryDelete', '67', 'official:service:categoryDelete');
INSERT INTO `sys_permission` VALUES ('141', '1', 'lgan', '2017-03-17 09:28:03', null, null, null, 'official:service:categoryView', '	官网管理:服务管理:分类详情', 'official:service:categoryView', '67', 'official:service:categoryView');
INSERT INTO `sys_permission` VALUES ('142', '1', 'lgan', '2017-03-17 09:28:23', null, null, null, 'official:service:categoryEdit', '	官网管理:服务管理:分类编辑', 'official:service:categoryEdit', '67', 'official:service:categoryEdit');
INSERT INTO `sys_permission` VALUES ('143', '1', 'lgan', '2017-03-17 09:28:41', null, null, null, 'official:service:categoryAdd', '官网管理:服务管理:分类添加', 'official:service:categoryAdd', '67', 'official:service:categoryAdd');
INSERT INTO `sys_permission` VALUES ('144', '1', 'lgan', '2017-03-17 09:29:04', null, null, null, 'official:service:add', '官网管理:服务管理:添加', 'official:service:add', '67', 'official:service:add');
INSERT INTO `sys_permission` VALUES ('145', '1', 'lgan', '2017-03-17 09:29:23', null, null, null, 'official:service:view', '官网管理:服务管理:详情', 'official:service:view', '67', 'official:service:view');
INSERT INTO `sys_permission` VALUES ('146', '1', 'lgan', '2017-03-17 09:29:47', null, null, null, 'official:service:edit', '官网管理:服务管理:编辑', 'official:service:edit', '67', 'official:service:edit');
INSERT INTO `sys_permission` VALUES ('147', '1', 'lgan', '2017-03-17 09:30:06', null, null, null, 'official:service:delete', '官网管理:服务管理:删除', 'official:service:delete', '67', 'official:service:delete');
INSERT INTO `sys_permission` VALUES ('148', '1', 'lgan', '2017-03-17 09:30:59', null, null, null, 'official:news:add', '官网管理:新闻管理:添加', 'official:news:add', '68', 'official:news:add');
INSERT INTO `sys_permission` VALUES ('149', '1', 'lgan', '2017-03-17 09:31:17', null, null, null, 'official:news:view', '官网管理:新闻管理:详情', 'official:news:view', '68', 'official:news:view');
INSERT INTO `sys_permission` VALUES ('151', '1', 'lgan', '2017-03-17 09:31:52', null, null, null, 'official:news:delete', '官网管理:新闻管理:删除', 'official:news:delete', '68', 'official:news:delete');
INSERT INTO `sys_permission` VALUES ('152', '1', 'lgan', '2017-03-17 09:32:12', null, null, null, 'official:news:categoryAdd', '官网管理:新闻管理:分类添加', 'official:news:categoryAdd', '68', 'official:news:categoryAdd');
INSERT INTO `sys_permission` VALUES ('153', '1', 'lgan', '2017-03-17 09:32:27', null, null, null, 'official:news:categoryView', '官网管理:新闻管理:分类详情', 'official:news:categoryView', '68', 'official:news:categoryView');
INSERT INTO `sys_permission` VALUES ('154', '1', 'lgan', '2017-03-17 09:32:47', null, null, null, 'official:news:categoryEdit', '官网管理:新闻管理:分类编辑', 'official:news:categoryEdit', '68', 'official:news:categoryEdit');
INSERT INTO `sys_permission` VALUES ('155', '1', 'lgan', '2017-03-17 09:33:05', null, null, null, 'official:news:categoryDelete', '官网管理:新闻管理:分类删除', 'official:news:categoryDelete', '68', 'official:news:categoryDelete');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_status` varchar(10) DEFAULT NULL COMMENT ' 状态 PublicStatusEnum',
  `creator` varchar(45) DEFAULT NULL COMMENT '创建人.',
  `create_time` datetime DEFAULT NULL COMMENT ' 创建时间',
  `editor` varchar(45) DEFAULT NULL COMMENT '修改人.',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间.',
  `remark` varchar(45) DEFAULT NULL COMMENT '描述',
  `role_code` varchar(45) DEFAULT NULL COMMENT ' 角色编码：例如：admin',
  `role_name` varchar(45) DEFAULT NULL COMMENT ' 角色名称',
  `role_type` varchar(45) DEFAULT NULL COMMENT '角色类型任务分配：assignment、管理角色：security-role、普通角色：user',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='系统角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('19', '1', null, '2017-08-11 14:29:08', 'lgan', '2017-08-11 14:29:08', '管理员角色&nbsp;', 'admin', '管理员', 'adminRole');
INSERT INTO `sys_role` VALUES ('20', '1', null, '2017-08-11 14:29:15', 'lgan', '2017-08-11 14:29:15', '测试用户角色', 'test', '测试用户角色', 'commonRole');

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_menu_status` varchar(45) DEFAULT NULL COMMENT '状态 PublicStatusEnum',
  `creator` varchar(45) DEFAULT NULL COMMENT '创建人.',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间.',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`role_menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=302 DEFAULT CHARSET=utf8 COMMENT='系统角色菜单表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('271', '1', 'lgan', '2017-08-10 13:34:51', '20', '44');
INSERT INTO `sys_role_menu` VALUES ('272', '1', 'lgan', '2017-08-10 13:34:51', '20', '46');
INSERT INTO `sys_role_menu` VALUES ('273', '1', 'lgan', '2017-08-10 13:34:51', '20', '47');
INSERT INTO `sys_role_menu` VALUES ('274', '1', 'lgan', '2017-08-10 13:34:51', '20', '48');
INSERT INTO `sys_role_menu` VALUES ('275', '1', 'lgan', '2017-08-10 13:34:51', '20', '49');
INSERT INTO `sys_role_menu` VALUES ('276', '1', 'lgan', '2017-08-10 13:34:51', '20', '50');
INSERT INTO `sys_role_menu` VALUES ('277', '1', 'lgan', '2017-08-10 13:34:51', '20', '51');
INSERT INTO `sys_role_menu` VALUES ('278', '1', 'lgan', '2017-08-10 13:34:51', '20', '52');
INSERT INTO `sys_role_menu` VALUES ('279', '1', 'lgan', '2017-08-10 13:34:51', '20', '71');
INSERT INTO `sys_role_menu` VALUES ('280', '1', 'lgan', '2017-08-10 13:34:51', '20', '72');
INSERT INTO `sys_role_menu` VALUES ('281', '1', 'lgan', '2017-08-10 13:34:51', '20', '73');
INSERT INTO `sys_role_menu` VALUES ('282', '1', 'lgan', '2017-08-10 13:34:51', '20', '74');
INSERT INTO `sys_role_menu` VALUES ('283', '1', 'lgan', '2017-08-11 10:18:33', '19', '44');
INSERT INTO `sys_role_menu` VALUES ('284', '1', 'lgan', '2017-08-11 10:18:33', '19', '61');
INSERT INTO `sys_role_menu` VALUES ('285', '1', 'lgan', '2017-08-11 10:18:33', '19', '62');
INSERT INTO `sys_role_menu` VALUES ('286', '1', 'lgan', '2017-08-11 10:18:33', '19', '46');
INSERT INTO `sys_role_menu` VALUES ('287', '1', 'lgan', '2017-08-11 10:18:33', '19', '47');
INSERT INTO `sys_role_menu` VALUES ('288', '1', 'lgan', '2017-08-11 10:18:33', '19', '48');
INSERT INTO `sys_role_menu` VALUES ('289', '1', 'lgan', '2017-08-11 10:18:33', '19', '49');
INSERT INTO `sys_role_menu` VALUES ('290', '1', 'lgan', '2017-08-11 10:18:33', '19', '50');
INSERT INTO `sys_role_menu` VALUES ('291', '1', 'lgan', '2017-08-11 10:18:33', '19', '51');
INSERT INTO `sys_role_menu` VALUES ('292', '1', 'lgan', '2017-08-11 10:18:33', '19', '52');
INSERT INTO `sys_role_menu` VALUES ('293', '1', 'lgan', '2017-08-11 10:18:33', '19', '63');
INSERT INTO `sys_role_menu` VALUES ('298', '1', 'lgan', '2017-08-11 10:18:33', '19', '71');
INSERT INTO `sys_role_menu` VALUES ('299', '1', 'lgan', '2017-08-11 10:18:33', '19', '72');
INSERT INTO `sys_role_menu` VALUES ('300', '1', 'lgan', '2017-08-11 10:18:33', '19', '73');
INSERT INTO `sys_role_menu` VALUES ('301', '1', 'lgan', '2017-08-11 10:18:33', '19', '74');

-- ----------------------------
-- Table structure for `sys_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_permission_status` varchar(45) DEFAULT NULL COMMENT '状态 PublicStatusEnum',
  `creator` varchar(45) DEFAULT NULL COMMENT '创建人.',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间.',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `permission_id` int(11) DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`role_permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1015 DEFAULT CHARSET=utf8 COMMENT='系统角色权限表';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('875', '1', 'lgan', '2017-03-12 21:17:38', '20', '57');
INSERT INTO `sys_role_permission` VALUES ('876', '1', 'lgan', '2017-03-12 21:17:38', '20', '58');
INSERT INTO `sys_role_permission` VALUES ('877', '1', 'lgan', '2017-03-12 21:17:38', '20', '59');
INSERT INTO `sys_role_permission` VALUES ('878', '1', 'lgan', '2017-03-12 21:17:38', '20', '60');
INSERT INTO `sys_role_permission` VALUES ('879', '1', 'lgan', '2017-03-12 21:17:38', '20', '61');
INSERT INTO `sys_role_permission` VALUES ('880', '1', 'lgan', '2017-03-12 21:17:38', '20', '62');
INSERT INTO `sys_role_permission` VALUES ('881', '1', 'lgan', '2017-03-12 21:17:38', '20', '63');
INSERT INTO `sys_role_permission` VALUES ('882', '1', 'lgan', '2017-03-12 21:17:38', '20', '64');
INSERT INTO `sys_role_permission` VALUES ('883', '1', 'lgan', '2017-03-12 21:17:38', '20', '65');
INSERT INTO `sys_role_permission` VALUES ('884', '1', 'lgan', '2017-03-12 21:17:38', '20', '66');
INSERT INTO `sys_role_permission` VALUES ('885', '1', 'lgan', '2017-03-12 21:17:38', '20', '67');
INSERT INTO `sys_role_permission` VALUES ('886', '1', 'lgan', '2017-03-12 21:17:38', '20', '68');
INSERT INTO `sys_role_permission` VALUES ('887', '1', 'lgan', '2017-03-12 21:17:38', '20', '69');
INSERT INTO `sys_role_permission` VALUES ('888', '1', 'lgan', '2017-03-12 21:17:38', '20', '70');
INSERT INTO `sys_role_permission` VALUES ('889', '1', 'lgan', '2017-03-12 21:17:38', '20', '71');
INSERT INTO `sys_role_permission` VALUES ('890', '1', 'lgan', '2017-03-12 21:17:38', '20', '72');
INSERT INTO `sys_role_permission` VALUES ('891', '1', 'lgan', '2017-03-12 21:17:38', '20', '81');
INSERT INTO `sys_role_permission` VALUES ('892', '1', 'lgan', '2017-03-12 21:17:38', '20', '82');
INSERT INTO `sys_role_permission` VALUES ('893', '1', 'lgan', '2017-03-12 21:17:38', '20', '83');
INSERT INTO `sys_role_permission` VALUES ('894', '1', 'lgan', '2017-03-12 21:17:38', '20', '84');
INSERT INTO `sys_role_permission` VALUES ('895', '1', 'lgan', '2017-03-12 21:17:38', '20', '85');
INSERT INTO `sys_role_permission` VALUES ('896', '1', 'lgan', '2017-03-12 21:17:38', '20', '86');
INSERT INTO `sys_role_permission` VALUES ('897', '1', 'lgan', '2017-03-12 21:17:38', '20', '87');
INSERT INTO `sys_role_permission` VALUES ('898', '1', 'lgan', '2017-03-12 21:17:38', '20', '88');
INSERT INTO `sys_role_permission` VALUES ('899', '1', 'lgan', '2017-03-12 21:17:38', '20', '73');
INSERT INTO `sys_role_permission` VALUES ('900', '1', 'lgan', '2017-03-12 21:17:38', '20', '74');
INSERT INTO `sys_role_permission` VALUES ('901', '1', 'lgan', '2017-03-12 21:17:38', '20', '75');
INSERT INTO `sys_role_permission` VALUES ('902', '1', 'lgan', '2017-03-12 21:17:38', '20', '76');
INSERT INTO `sys_role_permission` VALUES ('903', '1', 'lgan', '2017-03-12 21:17:38', '20', '79');
INSERT INTO `sys_role_permission` VALUES ('904', '1', 'lgan', '2017-03-12 21:17:38', '20', '80');
INSERT INTO `sys_role_permission` VALUES ('905', '1', 'lgan', '2017-03-12 21:17:38', '20', '89');
INSERT INTO `sys_role_permission` VALUES ('906', '1', 'lgan', '2017-03-12 21:17:38', '20', '90');
INSERT INTO `sys_role_permission` VALUES ('907', '1', 'lgan', '2017-03-12 21:17:38', '20', '91');
INSERT INTO `sys_role_permission` VALUES ('908', '1', 'lgan', '2017-03-12 21:17:38', '20', '92');
INSERT INTO `sys_role_permission` VALUES ('909', '1', 'lgan', '2017-03-12 21:17:38', '20', '93');
INSERT INTO `sys_role_permission` VALUES ('910', '1', 'lgan', '2017-03-12 21:17:38', '20', '94');
INSERT INTO `sys_role_permission` VALUES ('911', '1', 'lgan', '2017-03-12 21:17:38', '20', '95');
INSERT INTO `sys_role_permission` VALUES ('912', '1', 'lgan', '2017-03-12 21:17:38', '20', '96');
INSERT INTO `sys_role_permission` VALUES ('913', '1', 'lgan', '2017-03-12 21:17:38', '20', '97');
INSERT INTO `sys_role_permission` VALUES ('914', '1', 'lgan', '2017-03-12 21:17:38', '20', '98');
INSERT INTO `sys_role_permission` VALUES ('915', '1', 'lgan', '2017-03-12 21:17:38', '20', '99');
INSERT INTO `sys_role_permission` VALUES ('916', '1', 'lgan', '2017-03-17 09:54:04', '19', '106');
INSERT INTO `sys_role_permission` VALUES ('917', '1', 'lgan', '2017-03-17 09:54:04', '19', '107');
INSERT INTO `sys_role_permission` VALUES ('918', '1', 'lgan', '2017-03-17 09:54:04', '19', '108');
INSERT INTO `sys_role_permission` VALUES ('919', '1', 'lgan', '2017-03-17 09:54:04', '19', '109');
INSERT INTO `sys_role_permission` VALUES ('920', '1', 'lgan', '2017-03-17 09:54:04', '19', '110');
INSERT INTO `sys_role_permission` VALUES ('921', '1', 'lgan', '2017-03-17 09:54:04', '19', '111');
INSERT INTO `sys_role_permission` VALUES ('922', '1', 'lgan', '2017-03-17 09:54:04', '19', '112');
INSERT INTO `sys_role_permission` VALUES ('923', '1', 'lgan', '2017-03-17 09:54:04', '19', '113');
INSERT INTO `sys_role_permission` VALUES ('924', '1', 'lgan', '2017-03-17 09:54:04', '19', '114');
INSERT INTO `sys_role_permission` VALUES ('925', '1', 'lgan', '2017-03-17 09:54:04', '19', '115');
INSERT INTO `sys_role_permission` VALUES ('926', '1', 'lgan', '2017-03-17 09:54:04', '19', '116');
INSERT INTO `sys_role_permission` VALUES ('927', '1', 'lgan', '2017-03-17 09:54:04', '19', '118');
INSERT INTO `sys_role_permission` VALUES ('928', '1', 'lgan', '2017-03-17 09:54:04', '19', '119');
INSERT INTO `sys_role_permission` VALUES ('929', '1', 'lgan', '2017-03-17 09:54:04', '19', '120');
INSERT INTO `sys_role_permission` VALUES ('930', '1', 'lgan', '2017-03-17 09:54:04', '19', '121');
INSERT INTO `sys_role_permission` VALUES ('931', '1', 'lgan', '2017-03-17 09:54:04', '19', '122');
INSERT INTO `sys_role_permission` VALUES ('932', '1', 'lgan', '2017-03-17 09:54:04', '19', '57');
INSERT INTO `sys_role_permission` VALUES ('933', '1', 'lgan', '2017-03-17 09:54:04', '19', '58');
INSERT INTO `sys_role_permission` VALUES ('934', '1', 'lgan', '2017-03-17 09:54:04', '19', '59');
INSERT INTO `sys_role_permission` VALUES ('935', '1', 'lgan', '2017-03-17 09:54:04', '19', '60');
INSERT INTO `sys_role_permission` VALUES ('936', '1', 'lgan', '2017-03-17 09:54:04', '19', '61');
INSERT INTO `sys_role_permission` VALUES ('937', '1', 'lgan', '2017-03-17 09:54:04', '19', '62');
INSERT INTO `sys_role_permission` VALUES ('938', '1', 'lgan', '2017-03-17 09:54:04', '19', '63');
INSERT INTO `sys_role_permission` VALUES ('939', '1', 'lgan', '2017-03-17 09:54:04', '19', '64');
INSERT INTO `sys_role_permission` VALUES ('940', '1', 'lgan', '2017-03-17 09:54:04', '19', '123');
INSERT INTO `sys_role_permission` VALUES ('941', '1', 'lgan', '2017-03-17 09:54:04', '19', '124');
INSERT INTO `sys_role_permission` VALUES ('942', '1', 'lgan', '2017-03-17 09:54:04', '19', '125');
INSERT INTO `sys_role_permission` VALUES ('943', '1', 'lgan', '2017-03-17 09:54:04', '19', '126');
INSERT INTO `sys_role_permission` VALUES ('944', '1', 'lgan', '2017-03-17 09:54:04', '19', '127');
INSERT INTO `sys_role_permission` VALUES ('945', '1', 'lgan', '2017-03-17 09:54:04', '19', '65');
INSERT INTO `sys_role_permission` VALUES ('946', '1', 'lgan', '2017-03-17 09:54:04', '19', '66');
INSERT INTO `sys_role_permission` VALUES ('947', '1', 'lgan', '2017-03-17 09:54:04', '19', '67');
INSERT INTO `sys_role_permission` VALUES ('948', '1', 'lgan', '2017-03-17 09:54:04', '19', '68');
INSERT INTO `sys_role_permission` VALUES ('949', '1', 'lgan', '2017-03-17 09:54:04', '19', '69');
INSERT INTO `sys_role_permission` VALUES ('950', '1', 'lgan', '2017-03-17 09:54:04', '19', '70');
INSERT INTO `sys_role_permission` VALUES ('951', '1', 'lgan', '2017-03-17 09:54:04', '19', '71');
INSERT INTO `sys_role_permission` VALUES ('952', '1', 'lgan', '2017-03-17 09:54:04', '19', '72');
INSERT INTO `sys_role_permission` VALUES ('953', '1', 'lgan', '2017-03-17 09:54:04', '19', '81');
INSERT INTO `sys_role_permission` VALUES ('954', '1', 'lgan', '2017-03-17 09:54:04', '19', '82');
INSERT INTO `sys_role_permission` VALUES ('955', '1', 'lgan', '2017-03-17 09:54:04', '19', '83');
INSERT INTO `sys_role_permission` VALUES ('956', '1', 'lgan', '2017-03-17 09:54:04', '19', '84');
INSERT INTO `sys_role_permission` VALUES ('957', '1', 'lgan', '2017-03-17 09:54:04', '19', '85');
INSERT INTO `sys_role_permission` VALUES ('958', '1', 'lgan', '2017-03-17 09:54:04', '19', '86');
INSERT INTO `sys_role_permission` VALUES ('959', '1', 'lgan', '2017-03-17 09:54:04', '19', '87');
INSERT INTO `sys_role_permission` VALUES ('960', '1', 'lgan', '2017-03-17 09:54:04', '19', '88');
INSERT INTO `sys_role_permission` VALUES ('961', '1', 'lgan', '2017-03-17 09:54:04', '19', '73');
INSERT INTO `sys_role_permission` VALUES ('962', '1', 'lgan', '2017-03-17 09:54:04', '19', '74');
INSERT INTO `sys_role_permission` VALUES ('963', '1', 'lgan', '2017-03-17 09:54:04', '19', '75');
INSERT INTO `sys_role_permission` VALUES ('964', '1', 'lgan', '2017-03-17 09:54:04', '19', '76');
INSERT INTO `sys_role_permission` VALUES ('965', '1', 'lgan', '2017-03-17 09:54:04', '19', '79');
INSERT INTO `sys_role_permission` VALUES ('966', '1', 'lgan', '2017-03-17 09:54:04', '19', '80');
INSERT INTO `sys_role_permission` VALUES ('967', '1', 'lgan', '2017-03-17 09:54:04', '19', '89');
INSERT INTO `sys_role_permission` VALUES ('968', '1', 'lgan', '2017-03-17 09:54:04', '19', '90');
INSERT INTO `sys_role_permission` VALUES ('969', '1', 'lgan', '2017-03-17 09:54:04', '19', '91');
INSERT INTO `sys_role_permission` VALUES ('970', '1', 'lgan', '2017-03-17 09:54:04', '19', '92');
INSERT INTO `sys_role_permission` VALUES ('971', '1', 'lgan', '2017-03-17 09:54:04', '19', '93');
INSERT INTO `sys_role_permission` VALUES ('972', '1', 'lgan', '2017-03-17 09:54:04', '19', '94');
INSERT INTO `sys_role_permission` VALUES ('973', '1', 'lgan', '2017-03-17 09:54:04', '19', '95');
INSERT INTO `sys_role_permission` VALUES ('974', '1', 'lgan', '2017-03-17 09:54:04', '19', '96');
INSERT INTO `sys_role_permission` VALUES ('975', '1', 'lgan', '2017-03-17 09:54:04', '19', '97');
INSERT INTO `sys_role_permission` VALUES ('976', '1', 'lgan', '2017-03-17 09:54:04', '19', '98');
INSERT INTO `sys_role_permission` VALUES ('977', '1', 'lgan', '2017-03-17 09:54:04', '19', '99');
INSERT INTO `sys_role_permission` VALUES ('978', '1', 'lgan', '2017-03-17 09:54:04', '19', '100');
INSERT INTO `sys_role_permission` VALUES ('979', '1', 'lgan', '2017-03-17 09:54:04', '19', '101');
INSERT INTO `sys_role_permission` VALUES ('980', '1', 'lgan', '2017-03-17 09:54:04', '19', '102');
INSERT INTO `sys_role_permission` VALUES ('981', '1', 'lgan', '2017-03-17 09:54:04', '19', '103');
INSERT INTO `sys_role_permission` VALUES ('982', '1', 'lgan', '2017-03-17 09:54:04', '19', '104');
INSERT INTO `sys_role_permission` VALUES ('983', '1', 'lgan', '2017-03-17 09:54:04', '19', '105');
INSERT INTO `sys_role_permission` VALUES ('984', '1', 'lgan', '2017-03-17 09:54:04', '19', '128');
INSERT INTO `sys_role_permission` VALUES ('985', '1', 'lgan', '2017-03-17 09:54:04', '19', '129');
INSERT INTO `sys_role_permission` VALUES ('986', '1', 'lgan', '2017-03-17 09:54:04', '19', '130');
INSERT INTO `sys_role_permission` VALUES ('987', '1', 'lgan', '2017-03-17 09:54:04', '19', '131');
INSERT INTO `sys_role_permission` VALUES ('988', '1', 'lgan', '2017-03-17 09:54:04', '19', '132');
INSERT INTO `sys_role_permission` VALUES ('989', '1', 'lgan', '2017-03-17 09:54:04', '19', '134');
INSERT INTO `sys_role_permission` VALUES ('990', '1', 'lgan', '2017-03-17 09:54:04', '19', '135');
INSERT INTO `sys_role_permission` VALUES ('991', '1', 'lgan', '2017-03-17 09:54:04', '19', '136');
INSERT INTO `sys_role_permission` VALUES ('992', '1', 'lgan', '2017-03-17 09:54:04', '19', '137');
INSERT INTO `sys_role_permission` VALUES ('993', '1', 'lgan', '2017-03-17 09:54:04', '19', '138');
INSERT INTO `sys_role_permission` VALUES ('994', '1', 'lgan', '2017-03-17 09:54:04', '19', '139');
INSERT INTO `sys_role_permission` VALUES ('995', '1', 'lgan', '2017-03-17 09:54:04', '19', '140');
INSERT INTO `sys_role_permission` VALUES ('996', '1', 'lgan', '2017-03-17 09:54:04', '19', '141');
INSERT INTO `sys_role_permission` VALUES ('997', '1', 'lgan', '2017-03-17 09:54:04', '19', '142');
INSERT INTO `sys_role_permission` VALUES ('998', '1', 'lgan', '2017-03-17 09:54:04', '19', '143');
INSERT INTO `sys_role_permission` VALUES ('999', '1', 'lgan', '2017-03-17 09:54:04', '19', '144');
INSERT INTO `sys_role_permission` VALUES ('1000', '1', 'lgan', '2017-03-17 09:54:04', '19', '145');
INSERT INTO `sys_role_permission` VALUES ('1001', '1', 'lgan', '2017-03-17 09:54:04', '19', '146');
INSERT INTO `sys_role_permission` VALUES ('1002', '1', 'lgan', '2017-03-17 09:54:04', '19', '147');
INSERT INTO `sys_role_permission` VALUES ('1003', '1', 'lgan', '2017-03-17 09:54:04', '19', '148');
INSERT INTO `sys_role_permission` VALUES ('1004', '1', 'lgan', '2017-03-17 09:54:04', '19', '149');
INSERT INTO `sys_role_permission` VALUES ('1006', '1', 'lgan', '2017-03-17 09:54:04', '19', '151');
INSERT INTO `sys_role_permission` VALUES ('1007', '1', 'lgan', '2017-03-17 09:54:04', '19', '152');
INSERT INTO `sys_role_permission` VALUES ('1008', '1', 'lgan', '2017-03-17 09:54:04', '19', '153');
INSERT INTO `sys_role_permission` VALUES ('1009', '1', 'lgan', '2017-03-17 09:54:04', '19', '154');
INSERT INTO `sys_role_permission` VALUES ('1010', '1', 'lgan', '2017-03-17 09:54:04', '19', '155');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_status` varchar(10) DEFAULT NULL COMMENT '状态 PublicStatusEnum',
  `creator` varchar(45) DEFAULT NULL COMMENT '创建人.',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间.',
  `editor` varchar(45) DEFAULT NULL COMMENT '修改人',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间.',
  `remark` varchar(500) DEFAULT NULL COMMENT '描述',
  `user_name` varchar(45) DEFAULT NULL COMMENT '登录名',
  `password` varchar(45) DEFAULT NULL COMMENT '登录密码',
  `salt` varchar(45) DEFAULT NULL COMMENT '盐',
  `real_name` varchar(45) DEFAULT NULL COMMENT '真实姓名',
  `nick_name` varchar(45) DEFAULT NULL COMMENT '昵称',
  `type` varchar(10) DEFAULT NULL COMMENT '用户类型（admin:超级管理员，common:普通操作员），超级管理员由系统初始化时添加，不能删除)',
  `user_like` varchar(50) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL COMMENT '邮件号码',
  `email_active` varchar(10) DEFAULT NULL COMMENT '激活yes；未激活no',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `phone_active` varchar(10) DEFAULT NULL COMMENT '激活yes；未激活no',
  `avator` varchar(100) DEFAULT NULL COMMENT '头像地址（缩略图',
  `sex` varchar(10) DEFAULT NULL COMMENT 'unknow未知；male 男；female 女',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `detail_area` varchar(200) DEFAULT NULL COMMENT '用户详细地址',
  `province` varchar(20) DEFAULT NULL COMMENT '省',
  `city` varchar(20) DEFAULT NULL COMMENT '市',
  `area` varchar(20) DEFAULT NULL COMMENT '区',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `userName_UNIQUE` (`user_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('3', '1', null, '2016-11-04 13:45:57', 'lgan', '2017-08-11 15:49:35', '测试用户 测试编辑', 'lgan', 'e60e585fe4debcd19d7ac02e2921f16b', '7d0f67616088c7d89eab290a712e5ff3', '甘亮', null, 'adminUser', 'reading,lazying', 'lovercws@gmail.com', null, '15330061450', null, 'http://www.xiaomoc.top/group1/M00/00/08/eE0NXVmMJsKAL_0lAABfCaR9Ey4204.png', 'm', '2016-11-10 00:00:00', 'nullnullnull', '浙江', '杭州', '西湖区');
INSERT INTO `sys_user` VALUES ('6', '1', 'lgan', '2017-08-11 14:49:55', 'lgan', '2017-08-11 15:47:09', '我家宝宝最可爱了！', 'admin', '3942d195da9c3f8e5f7d1f1738e7bca4', 'da46e48c11662a5ea1fc86e637d1eda9', '甘亮', null, 'adminUser', 'reading,writing,lazying', 'babymm@aliyun.com', '0', '18971336061', '0', 'http://www.xiaomoc.top/group1/M00/00/08/eE0NXVmNUuCAYAERABS4Fco06rc080.jpg', 'm', '2017-01-24 00:00:00', 'nullnullnull', null, null, null);

-- ----------------------------
-- Table structure for `sys_user_group`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_group`;
CREATE TABLE `sys_user_group` (
  `user_group_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户组id',
  `user_group_status` varchar(10) DEFAULT NULL COMMENT '状态',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `editor` varchar(50) DEFAULT NULL,
  `edit_time` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `group_id` int(11) DEFAULT NULL COMMENT '组织id',
  `privilage` varchar(50) DEFAULT NULL COMMENT '权限(基本用户1，副组长2；组长3）',
  PRIMARY KEY (`user_group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_group
-- ----------------------------
INSERT INTO `sys_user_group` VALUES ('6', '1', 'lgan', '2016-11-11 15:56:48', null, null, '管理員；分配系統功能職能；', '3', '3', '3');

-- ----------------------------
-- Table structure for `sys_user_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_log`;
CREATE TABLE `sys_user_log` (
  `user_log_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_log_status` varchar(45) DEFAULT NULL COMMENT '状态 PublicStatusEnum',
  `creator` varchar(45) DEFAULT NULL COMMENT '创建人.',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间.',
  `editor` varchar(45) DEFAULT NULL COMMENT '修改人.',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间.',
  `remark` varchar(45) DEFAULT NULL COMMENT '描述',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `user_name` varchar(45) DEFAULT NULL COMMENT '用户名',
  `operate_type` varchar(10) DEFAULT NULL COMMENT '操作类型（参与枚举:OperatorLogTypeEnum,1:增加,2:修改,3:删除,4:查询,5:登录）',
  `ip` varchar(20) DEFAULT NULL COMMENT 'IP地址',
  `content` varchar(500) DEFAULT NULL COMMENT '操作内容',
  `method` varchar(100) DEFAULT NULL,
  `parameter` varchar(1000) DEFAULT NULL,
  `result` text,
  `usetime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`user_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COMMENT='系统日志表';

-- ----------------------------
-- Records of sys_user_log
-- ----------------------------
INSERT INTO `sys_user_log` VALUES ('2', '1', 'lgan', '2017-08-11 11:11:19', null, null, null, '3', 'lgan', 'DELETE', '0:0:0:0:0:0:0:1', '删除导出模型', 'com.lovecws.mumu.system.controller.system.export.SystemExportModelController deleteExportModel', '\"6\"', '{\"code\":200,\"msg\":\"导出模型删除成功\"}', '46ms');
INSERT INTO `sys_user_log` VALUES ('3', '1', 'lgan', '2017-08-11 11:19:26', null, null, null, '3', 'lgan', 'DELETE', '0:0:0:0:0:0:0:1', '删除导出模型', 'com.lovecws.mumu.system.controller.system.export.SystemExportModelController deleteExportModel', '\"7\"', '{\"code\":200,\"msg\":\"导出模型删除成功\"}', '101ms');
INSERT INTO `sys_user_log` VALUES ('4', '1', 'lgan', '2017-08-11 11:52:54', null, null, null, '3', 'lgan', 'DELETE', '0:0:0:0:0:0:0:1', '删除权限', 'com.lovecws.mumu.system.controller.system.permission.SystemPermissionController permissionDelete', '\"156\"', '{\"code\":200,\"msg\":\"删除菜单权限操作成功\"}', '70ms');
INSERT INTO `sys_user_log` VALUES ('5', '1', 'lgan', '2017-08-11 12:08:08', null, null, null, '3', 'lgan', 'DELETE', '0:0:0:0:0:0:0:1', '删除用户操作日志', 'com.lovecws.mumu.system.controller.system.log.SystemLogController logDelete', '\"1\"', '{\"code\":200,\"msg\":\"success\",\"data\":{}}', '99ms');
INSERT INTO `sys_user_log` VALUES ('6', '1', null, '2017-08-11 13:35:34', null, null, null, null, null, 'POST', '0:0:0:0:0:0:0:1', '用户登录', 'com.lovecws.mumu.system.controller.system.index.SysLoginController logining', 'null,null', '{\"view\":\"login\",\"model\":{\"msg\":\"failure\",\"code\":\"500\",\"data\":\"错误提示：用户未登录\"},\"cleared\":false}', '24ms');
INSERT INTO `sys_user_log` VALUES ('7', '1', 'lgan', '2017-08-11 13:36:50', null, null, null, '3', 'lgan', 'POST', '0:0:0:0:0:0:0:1', '用户群组添加成员', 'com.lovecws.mumu.system.controller.system.group.SystemGroupController savemember', '3,3,\"123\",\"1\"', '{\"code\":200,\"msg\":\"添加组成员成功\"}', '61ms');
INSERT INTO `sys_user_log` VALUES ('8', '1', 'lgan', '2017-08-11 13:36:57', null, null, null, '3', 'lgan', 'DELETE', '0:0:0:0:0:0:0:1', '用户群组移除成员', 'com.lovecws.mumu.system.controller.system.group.SystemGroupController memberDelete', '\"9\"', '{\"code\":200,\"msg\":\"删除组成员成功\"}', '89ms');
INSERT INTO `sys_user_log` VALUES ('9', '1', 'lgan', '2017-08-11 13:55:52', null, null, null, '3', 'lgan', 'DELETE', '0:0:0:0:0:0:0:1', '用户群组移除成员', 'com.lovecws.mumu.system.controller.system.group.SystemGroupController memberDelete', '\"8\"', '{\"code\":200,\"msg\":\"删除组成员成功\"}', '58ms');
INSERT INTO `sys_user_log` VALUES ('10', '1', 'lgan', '2017-08-11 13:55:57', null, null, null, '3', 'lgan', 'DELETE', '0:0:0:0:0:0:0:1', '用户群组移除成员', 'com.lovecws.mumu.system.controller.system.group.SystemGroupController memberDelete', '\"7\"', '{\"code\":200,\"msg\":\"删除组成员成功\"}', '98ms');
INSERT INTO `sys_user_log` VALUES ('11', '1', 'lgan', '2017-08-11 14:03:59', null, null, null, '3', 'lgan', 'DELETE', '0:0:0:0:0:0:0:1', '删除数据字典', 'com.lovecws.mumu.system.controller.system.ddl.SystemDDLController ddlDelete', '111,\"roleType\"', '{\"code\":400,\"msg\":\"数据字典删除失败,该数据字典存在子数据字典，请先删除子数据字典!\"}', '7ms');
INSERT INTO `sys_user_log` VALUES ('12', '1', 'lgan', '2017-08-11 14:04:08', null, null, null, '3', 'lgan', 'DELETE', '0:0:0:0:0:0:0:1', '删除数据字典', 'com.lovecws.mumu.system.controller.system.ddl.SystemDDLController ddlDelete', '111,\"roleType\"', '{\"code\":400,\"msg\":\"数据字典删除失败,该数据字典存在子数据字典，请先删除子数据字典!\"}', '6ms');
INSERT INTO `sys_user_log` VALUES ('13', '1', 'lgan', '2017-08-11 14:06:13', null, null, null, '3', 'lgan', 'DELETE', '0:0:0:0:0:0:0:1', '删除数据字典', 'com.lovecws.mumu.system.controller.system.ddl.SystemDDLController ddlDelete', '112,\"123\"', '{\"code\":200,\"msg\":\"数据字典删除成功\"}', '61ms');
INSERT INTO `sys_user_log` VALUES ('14', '1', 'lgan', '2017-08-11 14:06:20', null, null, null, '3', 'lgan', 'DELETE', '0:0:0:0:0:0:0:1', '删除数据字典', 'com.lovecws.mumu.system.controller.system.ddl.SystemDDLController ddlDelete', '111,\"roleType\"', '{\"code\":200,\"msg\":\"数据字典删除成功\"}', '99ms');
INSERT INTO `sys_user_log` VALUES ('15', '1', 'lgan', '2017-08-11 14:06:45', null, null, null, '3', 'lgan', 'PUT', '0:0:0:0:0:0:0:1', '添加数据字典', 'com.lovecws.mumu.system.controller.system.ddl.SystemDDLController saveSystemDDL', '{\"ddlCode\":\"roleType\",\"ddlValue\":\"角色类型\",\"parentCode\":\"\",\"ddlNumber\":\"1\",\"id\":0}', '{\"code\":200,\"msg\":\"数据字典保存成功\"}', '55ms');
INSERT INTO `sys_user_log` VALUES ('16', '1', 'lgan', '2017-08-11 14:07:26', null, null, null, '3', 'lgan', 'PUT', '0:0:0:0:0:0:0:1', '添加数据字典', 'com.lovecws.mumu.system.controller.system.ddl.SystemDDLController saveSystemDDL', '{\"ddlCode\":\"adminRole\",\"ddlValue\":\"管理员角色\",\"parentCode\":\"roleType\",\"ddlNumber\":\"2\",\"id\":0}', '{\"code\":200,\"msg\":\"数据字典保存成功\"}', '90ms');
INSERT INTO `sys_user_log` VALUES ('17', '1', 'lgan', '2017-08-11 14:07:41', null, null, null, '3', 'lgan', 'PUT', '0:0:0:0:0:0:0:1', '添加数据字典', 'com.lovecws.mumu.system.controller.system.ddl.SystemDDLController saveSystemDDL', '{\"ddlCode\":\"commonRole\",\"ddlValue\":\"普通角色\",\"parentCode\":\"roleType\",\"ddlNumber\":\"3\",\"id\":0}', '{\"code\":200,\"msg\":\"数据字典保存成功\"}', '59ms');
INSERT INTO `sys_user_log` VALUES ('18', '1', 'lgan', '2017-08-11 14:10:41', null, null, null, '3', 'lgan', 'PUT', '0:0:0:0:0:0:0:1', '添加数据字典', 'com.lovecws.mumu.system.controller.system.ddl.SystemDDLController saveSystemDDL', '{\"ddlCode\":\"sexType\",\"ddlValue\":\"性别类型\",\"parentCode\":\"\",\"ddlNumber\":\"10\",\"id\":0}', '{\"code\":200,\"msg\":\"数据字典保存成功\"}', '112ms');
INSERT INTO `sys_user_log` VALUES ('19', '1', 'lgan', '2017-08-11 14:11:00', null, null, null, '3', 'lgan', 'PUT', '0:0:0:0:0:0:0:1', '添加数据字典', 'com.lovecws.mumu.system.controller.system.ddl.SystemDDLController saveSystemDDL', '{\"ddlCode\":\"m\",\"ddlValue\":\"男性\",\"parentCode\":\"sexType\",\"ddlNumber\":\"11\",\"id\":0}', '{\"code\":200,\"msg\":\"数据字典保存成功\"}', '52ms');
INSERT INTO `sys_user_log` VALUES ('20', '1', 'lgan', '2017-08-11 14:11:14', null, null, null, '3', 'lgan', 'PUT', '0:0:0:0:0:0:0:1', '添加数据字典', 'com.lovecws.mumu.system.controller.system.ddl.SystemDDLController saveSystemDDL', '{\"ddlCode\":\"f\",\"ddlValue\":\"女性\",\"parentCode\":\"sexType\",\"ddlNumber\":\"12\",\"id\":0}', '{\"code\":200,\"msg\":\"数据字典保存成功\"}', '81ms');
INSERT INTO `sys_user_log` VALUES ('21', '1', 'lgan', '2017-08-11 14:11:38', null, null, null, '3', 'lgan', 'PUT', '0:0:0:0:0:0:0:1', '添加数据字典', 'com.lovecws.mumu.system.controller.system.ddl.SystemDDLController saveSystemDDL', '{\"ddlCode\":\"u\",\"ddlValue\":\"未知\",\"parentCode\":\"sexType\",\"ddlNumber\":\"13\",\"id\":0}', '{\"code\":200,\"msg\":\"数据字典保存成功\"}', '59ms');
INSERT INTO `sys_user_log` VALUES ('22', '1', 'lgan', '2017-08-11 14:15:01', null, null, null, '3', 'lgan', 'PUT', '0:0:0:0:0:0:0:1', '添加数据字典', 'com.lovecws.mumu.system.controller.system.ddl.SystemDDLController saveSystemDDL', '{\"ddlCode\":\"userType\",\"ddlValue\":\"用户类型\",\"parentCode\":\"\",\"ddlNumber\":\"20\",\"id\":0}', '{\"code\":200,\"msg\":\"数据字典保存成功\"}', '104ms');
INSERT INTO `sys_user_log` VALUES ('23', '1', 'lgan', '2017-08-11 14:15:19', null, null, null, '3', 'lgan', 'PUT', '0:0:0:0:0:0:0:1', '添加数据字典', 'com.lovecws.mumu.system.controller.system.ddl.SystemDDLController saveSystemDDL', '{\"ddlCode\":\"commonUser\",\"ddlValue\":\"普通用户\",\"parentCode\":\"userType\",\"ddlNumber\":\"21\",\"id\":0}', '{\"code\":200,\"msg\":\"数据字典保存成功\"}', '64ms');
INSERT INTO `sys_user_log` VALUES ('24', '1', 'lgan', '2017-08-11 14:15:37', null, null, null, '3', 'lgan', 'PUT', '0:0:0:0:0:0:0:1', '添加数据字典', 'com.lovecws.mumu.system.controller.system.ddl.SystemDDLController saveSystemDDL', '{\"ddlCode\":\"adminUser\",\"ddlValue\":\"管理员用户\",\"parentCode\":\"userType\",\"ddlNumber\":\"22\",\"id\":0}', '{\"code\":200,\"msg\":\"数据字典保存成功\"}', '70ms');
INSERT INTO `sys_user_log` VALUES ('25', '1', 'lgan', '2017-08-11 14:15:53', null, null, null, '3', 'lgan', 'PUT', '0:0:0:0:0:0:0:1', '添加数据字典', 'com.lovecws.mumu.system.controller.system.ddl.SystemDDLController saveSystemDDL', '{\"ddlCode\":\"operatorUser\",\"ddlValue\":\"操作员\",\"parentCode\":\"userType\",\"ddlNumber\":\"23\",\"id\":0}', '{\"code\":200,\"msg\":\"数据字典保存成功\"}', '107ms');
INSERT INTO `sys_user_log` VALUES ('26', '1', 'lgan', '2017-08-11 14:18:16', null, null, null, '3', 'lgan', 'PUT', '0:0:0:0:0:0:0:1', '添加数据字典', 'com.lovecws.mumu.system.controller.system.ddl.SystemDDLController saveSystemDDL', '{\"ddlCode\":\"userLikes\",\"ddlValue\":\"用户爱好\",\"parentCode\":\"\",\"ddlNumber\":\"30\",\"id\":0}', '{\"code\":200,\"msg\":\"数据字典保存成功\"}', '61ms');
INSERT INTO `sys_user_log` VALUES ('27', '1', 'lgan', '2017-08-11 14:18:39', null, null, null, '3', 'lgan', 'PUT', '0:0:0:0:0:0:0:1', '添加数据字典', 'com.lovecws.mumu.system.controller.system.ddl.SystemDDLController saveSystemDDL', '{\"ddlCode\":\"reading\",\"ddlValue\":\"阅读\",\"parentCode\":\"userLikes\",\"ddlNumber\":\"31\",\"id\":0}', '{\"code\":200,\"msg\":\"数据字典保存成功\"}', '56ms');
INSERT INTO `sys_user_log` VALUES ('28', '1', 'lgan', '2017-08-11 14:18:54', null, null, null, '3', 'lgan', 'PUT', '0:0:0:0:0:0:0:1', '添加数据字典', 'com.lovecws.mumu.system.controller.system.ddl.SystemDDLController saveSystemDDL', '{\"ddlCode\":\"writing\",\"ddlValue\":\"写作\",\"parentCode\":\"userLikes\",\"ddlNumber\":\"32\",\"id\":0}', '{\"code\":200,\"msg\":\"数据字典保存成功\"}', '85ms');
INSERT INTO `sys_user_log` VALUES ('29', '1', 'lgan', '2017-08-11 14:19:07', null, null, null, '3', 'lgan', 'PUT', '0:0:0:0:0:0:0:1', '添加数据字典', 'com.lovecws.mumu.system.controller.system.ddl.SystemDDLController saveSystemDDL', '{\"ddlCode\":\"lazying\",\"ddlValue\":\"发呆\",\"parentCode\":\"userLikes\",\"ddlNumber\":\"33\",\"id\":0}', '{\"code\":200,\"msg\":\"数据字典保存成功\"}', '57ms');
INSERT INTO `sys_user_log` VALUES ('30', '1', 'lgan', '2017-08-11 14:29:08', null, null, null, '3', 'lgan', 'PUT', '0:0:0:0:0:0:0:1', '编辑角色', 'com.lovecws.mumu.system.controller.system.role.SystemRoleController updateRole', '{\"roleId\":19,\"createTime\":\"2017-08-11 14:29:07\",\"remark\":\"管理员角色\\u0026nbsp;\",\"roleCode\":\"admin\",\"roleName\":\"管理员\",\"roleType\":\"adminRole\"}', '{\"code\":200,\"msg\":\"更新角色成功\"}', '64ms');
INSERT INTO `sys_user_log` VALUES ('31', '1', 'lgan', '2017-08-11 14:29:15', null, null, null, '3', 'lgan', 'PUT', '0:0:0:0:0:0:0:1', '编辑角色', 'com.lovecws.mumu.system.controller.system.role.SystemRoleController updateRole', '{\"roleId\":20,\"createTime\":\"2017-08-11 14:29:14\",\"remark\":\"测试用户角色\",\"roleCode\":\"test\",\"roleName\":\"测试用户角色\",\"roleType\":\"commonRole\"}', '{\"code\":200,\"msg\":\"更新角色成功\"}', '47ms');
INSERT INTO `sys_user_log` VALUES ('32', '1', 'lgan', '2017-08-11 14:39:29', null, null, null, '3', 'lgan', 'POST', '0:0:0:0:0:0:0:1', '更新数据字典', 'com.lovecws.mumu.system.controller.system.ddl.SystemDDLController updateSystemDDL', '{\"ddlCode\":\"userLike\",\"ddlValue\":\"用户爱好\",\"parentCode\":\"\",\"ddlNumber\":\"30\",\"id\":124}', '{\"code\":200,\"msg\":\"数据字典更新成功\"}', '63ms');
INSERT INTO `sys_user_log` VALUES ('33', '1', 'lgan', '2017-08-11 14:49:55', null, null, null, '3', 'lgan', 'POST', '0:0:0:0:0:0:0:1', '添加用户', 'com.lovecws.mumu.system.controller.system.user.SystemUserController saveUser', '{\"remark\":\"我家宝宝最可爱了！\",\"userName\":\"admin\",\"password\":\"123456\",\"realName\":\"甘亮\",\"type\":\"adminUser\",\"email\":\"babymm@aliyun.com\",\"phone\":\"18971336061\",\"avator\":\"http://www.xiaomoc.top/group1/M00/00/08/eE0NXVmNUuCAYAERABS4Fco06rc080.jpg\",\"sex\":\"m\",\"birthday\":\"2017-01-24 00:00:00\",\"userGroupId\":0}', '{\"code\":200,\"msg\":\"添加用户操作成功\"}', '484ms');
INSERT INTO `sys_user_log` VALUES ('34', '1', 'lgan', '2017-08-11 15:28:16', null, null, null, '3', 'lgan', 'PUT', '0:0:0:0:0:0:0:1', '更新用户信息', 'com.lovecws.mumu.system.controller.system.user.SystemUserController updateUser', '{\"userId\":3,\"remark\":\"测试用户 测试编辑\",\"userName\":\"lgan\",\"realName\":\"甘亮\",\"type\":\"adminUser\",\"email\":\"lovercws@gmail.com\",\"phone\":\"15330061450\",\"avator\":\"http://www.xiaomoc.top/group1/M00/00/08/eE0NXVmMJsKAL_0lAABfCaR9Ey4204.png\",\"sex\":\"m\",\"birthday\":\"2016-11-10 00:00:00\",\"userGroupId\":0}', '{\"code\":200,\"msg\":\"更新用户操作成功\"}', '67ms');
INSERT INTO `sys_user_log` VALUES ('35', '1', 'lgan', '2017-08-11 15:32:04', null, null, null, '3', 'lgan', 'PUT', '0:0:0:0:0:0:0:1', '更新用户信息', 'com.lovecws.mumu.system.controller.system.user.SystemUserController updateUser', '{\"userId\":3,\"remark\":\"测试用户 测试编辑\",\"userName\":\"lgan\",\"realName\":\"甘亮\",\"type\":\"adminUser\",\"email\":\"lovercws@gmail.com\",\"phone\":\"15330061450\",\"avator\":\"http://www.xiaomoc.top/group1/M00/00/08/eE0NXVmMJsKAL_0lAABfCaR9Ey4204.png\",\"sex\":\"m\",\"birthday\":\"2016-11-10 00:00:00\",\"userGroupId\":0}', '{\"code\":200,\"msg\":\"更新用户操作成功\"}', '77ms');
INSERT INTO `sys_user_log` VALUES ('36', '1', 'lgan', '2017-08-11 15:33:41', null, null, null, '3', 'lgan', 'PUT', '0:0:0:0:0:0:0:1', '更新用户信息', 'com.lovecws.mumu.system.controller.system.user.SystemUserController updateUser', '{\"userId\":3,\"remark\":\"测试用户 测试编辑\",\"userName\":\"lgan\",\"realName\":\"甘亮\",\"type\":\"adminUser\",\"email\":\"lovercws@gmail.com\",\"phone\":\"15330061450\",\"avator\":\"http://www.xiaomoc.top/group1/M00/00/08/eE0NXVmMJsKAL_0lAABfCaR9Ey4204.png\",\"sex\":\"m\",\"birthday\":\"2016-11-10 00:00:00\",\"userGroupId\":0}', '{\"code\":200,\"msg\":\"更新用户操作成功\"}', '73ms');
INSERT INTO `sys_user_log` VALUES ('37', '1', 'lgan', '2017-08-11 15:34:49', null, null, null, '3', 'lgan', 'PUT', '0:0:0:0:0:0:0:1', '更新用户信息', 'com.lovecws.mumu.system.controller.system.user.SystemUserController updateUser', '{\"userId\":3,\"remark\":\"测试用户 测试编辑\",\"userName\":\"lgan\",\"realName\":\"甘亮\",\"type\":\"adminUser\",\"email\":\"lovercws@gmail.com\",\"phone\":\"15330061450\",\"avator\":\"http://www.xiaomoc.top/group1/M00/00/08/eE0NXVmMJsKAL_0lAABfCaR9Ey4204.png\",\"sex\":\"m\",\"birthday\":\"2016-11-10 00:00:00\",\"userGroupId\":0}', '{\"code\":200,\"msg\":\"更新用户操作成功\"}', '69ms');
INSERT INTO `sys_user_log` VALUES ('38', '1', 'lgan', '2017-08-11 15:35:47', null, null, null, '3', 'lgan', 'PUT', '0:0:0:0:0:0:0:1', '更新用户信息', 'com.lovecws.mumu.system.controller.system.user.SystemUserController updateUser', '{\"userId\":3,\"remark\":\"测试用户 测试编辑\",\"userName\":\"lgan\",\"realName\":\"甘亮\",\"type\":\"adminUser\",\"email\":\"lovercws@gmail.com\",\"phone\":\"15330061450\",\"avator\":\"http://www.xiaomoc.top/group1/M00/00/08/eE0NXVmMJsKAL_0lAABfCaR9Ey4204.png\",\"sex\":\"m\",\"birthday\":\"2016-11-10 00:00:00\",\"userGroupId\":0}', '{\"code\":200,\"msg\":\"更新用户操作成功\"}', '65ms');
INSERT INTO `sys_user_log` VALUES ('39', '1', 'lgan', '2017-08-11 15:37:30', null, null, null, '3', 'lgan', 'PUT', '0:0:0:0:0:0:0:1', '更新用户信息', 'com.lovecws.mumu.system.controller.system.user.SystemUserController updateUser', '{\"userId\":3,\"remark\":\"测试用户 测试编辑\",\"userName\":\"lgan\",\"realName\":\"甘亮\",\"type\":\"adminUser\",\"userLike\":\"reading,writing,lazying\",\"email\":\"lovercws@gmail.com\",\"phone\":\"15330061450\",\"avator\":\"http://www.xiaomoc.top/group1/M00/00/08/eE0NXVmMJsKAL_0lAABfCaR9Ey4204.png\",\"sex\":\"m\",\"birthday\":\"2016-11-10 00:00:00\",\"userGroupId\":0}', '{\"code\":200,\"msg\":\"更新用户操作成功\"}', '64ms');
INSERT INTO `sys_user_log` VALUES ('40', '1', 'lgan', '2017-08-11 15:47:09', null, null, null, '3', 'lgan', 'PUT', '0:0:0:0:0:0:0:1', '更新用户信息', 'com.lovecws.mumu.system.controller.system.user.SystemUserController updateUser', '{\"userId\":6,\"remark\":\"我家宝宝最可爱了！\",\"userName\":\"admin\",\"realName\":\"甘亮\",\"type\":\"adminUser\",\"userLike\":\"reading,writing,lazying\",\"email\":\"babymm@aliyun.com\",\"phone\":\"18971336061\",\"avator\":\"http://www.xiaomoc.top/group1/M00/00/08/eE0NXVmNUuCAYAERABS4Fco06rc080.jpg\",\"sex\":\"m\",\"birthday\":\"2017-01-24 00:00:00\",\"userGroupId\":0}', '{\"code\":200,\"msg\":\"更新用户操作成功\"}', '74ms');
INSERT INTO `sys_user_log` VALUES ('41', '1', 'lgan', '2017-08-11 15:49:35', null, null, null, '3', 'lgan', 'PUT', '0:0:0:0:0:0:0:1', '更新用户信息', 'com.lovecws.mumu.system.controller.system.user.SystemUserController updateUser', '{\"userId\":3,\"remark\":\"测试用户 测试编辑\",\"userName\":\"lgan\",\"realName\":\"甘亮\",\"type\":\"adminUser\",\"userLike\":\"reading,lazying\",\"email\":\"lovercws@gmail.com\",\"phone\":\"15330061450\",\"avator\":\"http://www.xiaomoc.top/group1/M00/00/08/eE0NXVmMJsKAL_0lAABfCaR9Ey4204.png\",\"sex\":\"m\",\"birthday\":\"2016-11-10 00:00:00\",\"userGroupId\":0}', '{\"code\":200,\"msg\":\"更新用户操作成功\"}', '71ms');
INSERT INTO `sys_user_log` VALUES ('42', '1', 'lgan', '2017-08-11 16:08:27', null, null, null, '3', 'lgan', 'DELETE', '0:0:0:0:0:0:0:1', '删除菜单下的权限', 'com.lovecws.mumu.system.controller.system.menu.SystemMenuController deleteMenuPermission', '\"0\"', '{\"code\":200,\"msg\":\"删除菜单权限操作成功\"}', '16ms');
INSERT INTO `sys_user_log` VALUES ('43', '1', 'lgan', '2017-08-11 16:24:31', null, null, null, '3', 'lgan', 'DELETE', '0:0:0:0:0:0:0:1', '删除导出模型', 'com.lovecws.mumu.system.controller.system.export.SystemExportModelController deleteExportModel', '\"11\"', '{\"code\":200,\"msg\":\"导出模型删除成功\"}', '52ms');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_role_status` varchar(45) DEFAULT NULL COMMENT '状态 PublicStatusEnum',
  `creator` varchar(45) DEFAULT NULL COMMENT '创建人.',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间.',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='系统用户角色中间表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('16', '1', 'lgan', '2017-08-09 14:45:14', '19', '3');
