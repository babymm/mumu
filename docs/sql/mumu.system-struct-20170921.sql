/*
Navicat MySQL Data Transfer

Source Server         : 192.168.0.25
Source Server Version : 50632
Source Host           : 192.168.0.25:3306
Source Database       : mumu.system

Target Server Type    : MYSQL
Target Server Version : 50632
File Encoding         : 65001

Date: 2017-09-21 21:14:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for data_area_ region
-- ----------------------------
DROP TABLE IF EXISTS `data_area_ region`;
CREATE TABLE `data_area_ region` (
  `a_id` int(11) NOT NULL,
  `a_code` varchar(6) DEFAULT NULL,
  `c_code` varchar(6) DEFAULT NULL,
  `a_name` varchar(50) DEFAULT NULL,
  `a_sname` varchar(50) DEFAULT NULL,
  `a_pinying` varchar(100) DEFAULT NULL,
  `a_py` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`a_id`),
  KEY `c_code` (`c_code`) USING BTREE,
  CONSTRAINT `data_area_ region_ibfk_1` FOREIGN KEY (`c_code`) REFERENCES `data_area_city` (`c_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for data_area_city
-- ----------------------------
DROP TABLE IF EXISTS `data_area_city`;
CREATE TABLE `data_area_city` (
  `c_id` int(11) NOT NULL,
  `c_code` varchar(6) DEFAULT NULL,
  `p_code` varchar(6) DEFAULT NULL,
  `c_name` varchar(50) DEFAULT NULL,
  `c_sname` varchar(50) DEFAULT NULL,
  `c_pinying` varchar(100) DEFAULT NULL,
  `c_py` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`c_id`),
  KEY `p_code` (`p_code`) USING BTREE,
  KEY `c_code` (`c_code`) USING BTREE,
  CONSTRAINT `data_area_city_ibfk_1` FOREIGN KEY (`p_code`) REFERENCES `data_area_province` (`p_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for data_area_province
-- ----------------------------
DROP TABLE IF EXISTS `data_area_province`;
CREATE TABLE `data_area_province` (
  `p_id` int(11) NOT NULL,
  `p_code` varchar(6) DEFAULT NULL,
  `p_name` varchar(20) DEFAULT NULL,
  `p_sname` varchar(20) DEFAULT NULL,
  `p_pinying` varchar(100) DEFAULT NULL,
  `p_py` varchar(20) DEFAULT NULL,
  `p_abb` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`p_id`),
  KEY `p_code` (`p_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for data_car_attr
-- ----------------------------
DROP TABLE IF EXISTS `data_car_attr`;
CREATE TABLE `data_car_attr` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `initial` varchar(11) DEFAULT NULL,
  `parentid` int(11) DEFAULT NULL,
  `price` varchar(20) DEFAULT NULL,
  `yeartype` varchar(20) DEFAULT NULL,
  `productionstate` varchar(50) DEFAULT NULL,
  `salestate` varchar(50) DEFAULT NULL,
  `sizetype` varchar(50) DEFAULT NULL,
  `depth` int(11) DEFAULT NULL,
  `basic` varchar(500) DEFAULT NULL,
  `body` varchar(1000) DEFAULT NULL,
  `engine` varchar(1000) DEFAULT NULL,
  `gearbox` varchar(500) DEFAULT NULL,
  `chassisbrake` varchar(500) DEFAULT NULL,
  `safe` varchar(1000) DEFAULT NULL,
  `wheel` varchar(500) DEFAULT NULL,
  `drivingauxiliary` varchar(1000) DEFAULT NULL,
  `doormirror` varchar(1000) DEFAULT NULL,
  `light` varchar(500) DEFAULT NULL,
  `internalconfig` varchar(500) DEFAULT NULL,
  `seat` varchar(1000) DEFAULT NULL,
  `entcom` varchar(500) DEFAULT NULL,
  `aircondrefrigerator` varchar(500) DEFAULT NULL,
  `actualtest` varchar(200) DEFAULT NULL,
  `facade_img` varchar(2000) DEFAULT NULL COMMENT '外观图片',
  `facade_img_count` int(11) DEFAULT '0' COMMENT '外观图片总数',
  `central_img` varchar(2000) DEFAULT NULL COMMENT '中控图片集合',
  `central_img_count` int(11) DEFAULT '0' COMMENT '中控图片总数',
  `seat_img` varchar(2000) DEFAULT NULL COMMENT '座椅图片集',
  `seat_img_count` int(11) DEFAULT '0' COMMENT '座椅图片的总数',
  `detail_img` varchar(2000) DEFAULT NULL COMMENT '细节的图片集',
  `detail_img_count` int(11) DEFAULT '0' COMMENT '细节图片集的总数',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for data_car_brand
-- ----------------------------
DROP TABLE IF EXISTS `data_car_brand`;
CREATE TABLE `data_car_brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `initial` varchar(20) DEFAULT NULL,
  `parentid` int(11) DEFAULT NULL,
  `logo` varchar(100) DEFAULT NULL,
  `depth` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33714 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for data_car_cars
-- ----------------------------
DROP TABLE IF EXISTS `data_car_cars`;
CREATE TABLE `data_car_cars` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识id',
  `name` varchar(20) DEFAULT NULL COMMENT '车系简称',
  `fullname` varchar(100) DEFAULT NULL COMMENT '车系全称',
  `initial` varchar(10) DEFAULT NULL COMMENT '首字母',
  `parentid` int(11) DEFAULT NULL COMMENT '父id',
  `logo` varchar(200) DEFAULT NULL COMMENT '车系图片地址',
  `salestate` int(11) DEFAULT NULL COMMENT '销售状态（1、在销；2、待销；3、停销）',
  `sizetype` int(11) DEFAULT NULL COMMENT '汽车型号(SUV、跑车等）',
  `price` varchar(50) DEFAULT NULL COMMENT '价格区间',
  `depth` int(11) DEFAULT NULL COMMENT '级别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33883 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for data_car_model
-- ----------------------------
DROP TABLE IF EXISTS `data_car_model`;
CREATE TABLE `data_car_model` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `initial` varchar(10) DEFAULT NULL,
  `parentid` int(11) DEFAULT NULL,
  `price` varchar(20) DEFAULT NULL,
  `yeartype` varchar(20) DEFAULT NULL,
  `productionstate` varchar(20) DEFAULT NULL,
  `salestate` varchar(20) DEFAULT NULL,
  `sizetype` int(11) DEFAULT NULL,
  `maxhorsepower` varchar(20) DEFAULT NULL,
  `gearbox` varchar(100) DEFAULT NULL,
  `depth` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37159 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for data_car_sizetype
-- ----------------------------
DROP TABLE IF EXISTS `data_car_sizetype`;
CREATE TABLE `data_car_sizetype` (
  `c_size_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '汽车型号表id',
  `c_size_name` varchar(20) DEFAULT NULL COMMENT '汽车型号名称（MPV、SUV等）',
  `c_size_status` int(11) DEFAULT NULL COMMENT '状态（1、可用；2、不可用）',
  `c_size_recomm` int(11) DEFAULT NULL COMMENT '推荐条件（1、推荐；2、不推荐）',
  PRIMARY KEY (`c_size_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for data_car_subbrand
-- ----------------------------
DROP TABLE IF EXISTS `data_car_subbrand`;
CREATE TABLE `data_car_subbrand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `initial` varchar(10) DEFAULT NULL,
  `parentid` int(11) DEFAULT NULL,
  `depth` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33881 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for data_ip_addr
-- ----------------------------
DROP TABLE IF EXISTS `data_ip_addr`;
CREATE TABLE `data_ip_addr` (
  `start_ip` varchar(15) DEFAULT NULL,
  `sip` bigint(10) DEFAULT NULL,
  `end_ip` varchar(15) DEFAULT NULL,
  `eip` bigint(10) DEFAULT NULL,
  `city_name` varchar(50) DEFAULT NULL,
  `company_name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_ddl
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
-- Table structure for sys_export_model
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
-- Table structure for sys_group
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
-- Table structure for sys_group_role
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
-- Table structure for sys_menu
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
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

-- ----------------------------
-- Table structure for sys_message
-- ----------------------------
DROP TABLE IF EXISTS `sys_message`;
CREATE TABLE `sys_message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息id',
  `message_status` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '消息状态',
  `create_date` datetime DEFAULT NULL COMMENT '消息创建日期\r\n',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建消息的用户id',
  `message_title` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '消息标题\r\n ',
  `message_content` text COLLATE utf8_bin COMMENT '消息内容',
  `message_image` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '消息图片',
  `message_link` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '消息连接',
  `message_type` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '消息类型',
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for sys_message_container
-- ----------------------------
DROP TABLE IF EXISTS `sys_message_container`;
CREATE TABLE `sys_message_container` (
  `message_container_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息容器id',
  `message_container_status` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '消息容器状态',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `edit_date` datetime DEFAULT NULL COMMENT '编辑时间',
  `user_id` int(11) DEFAULT NULL COMMENT '接受消息的用户id',
  `message_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`message_container_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for sys_organize
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
-- Table structure for sys_permission
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
) ENGINE=InnoDB AUTO_INCREMENT=182 DEFAULT CHARSET=utf8 COMMENT='系统权限表';

-- ----------------------------
-- Table structure for sys_role
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
-- Table structure for sys_role_menu
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
) ENGINE=InnoDB AUTO_INCREMENT=349 DEFAULT CHARSET=utf8 COMMENT='系统角色菜单表';

-- ----------------------------
-- Table structure for sys_role_permission
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
) ENGINE=InnoDB AUTO_INCREMENT=1519 DEFAULT CHARSET=utf8 COMMENT='系统角色权限表';

-- ----------------------------
-- Table structure for sys_user
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
  `reg_type` varchar(10) DEFAULT NULL COMMENT '注册方式（phone、email、qq、weixin、weibo）',
  `thirdparty_token` varchar(50) DEFAULT NULL COMMENT '第三方登录令牌token',
  `thirdparty_data` text COMMENT '第三方登录返回值',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `userName_UNIQUE` (`user_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- ----------------------------
-- Table structure for sys_user_group
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user_log
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
) ENGINE=InnoDB AUTO_INCREMENT=310 DEFAULT CHARSET=utf8 COMMENT='系统日志表';

-- ----------------------------
-- Table structure for sys_user_role
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
