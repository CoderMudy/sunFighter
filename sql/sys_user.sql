/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : study

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-07-24 22:20:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `user_name` varchar(30) NOT NULL COMMENT '登录账号',
  `nick_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phone` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像路径',
  `password` varchar(50) DEFAULT '' COMMENT '密码',
  `salt` varchar(20) DEFAULT '' COMMENT '盐加密',
  `token` varchar(256) DEFAULT NULL COMMENT 'APP会话token',
  `uuid` varchar(128) DEFAULT NULL COMMENT '客户端唯一标识',
  `personal_sign` varchar(512) DEFAULT NULL COMMENT '个性签名',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `locked` tinyint(1) DEFAULT NULL COMMENT '是否锁定',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(20) DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `sys_menu_id` bigint(18) NOT NULL COMMENT '资源id' AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '资源名称',
	`parent_id` bigint(18) DEFAULT NULL COMMENT '父id',
  `type` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单类型，button或者menu',
  `url` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '链接地址',
  `icon` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '图标',
  `show_flag` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '是否展示',
  `permission` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '权限标识',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
	`status` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '删除标记Y：正常；N：删除；A：审核',
	`remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
	`create_id` bigint(18) DEFAULT NULL COMMENT '创建者id',
  `update_id` bigint(18) DEFAULT NULL COMMENT '修改者id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`sys_menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统菜单sys_menu, 资源树，按钮';

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `sys_role_id` int(5) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(30) DEFAULT NULL COMMENT '角色名称',
  `desc` varchar(50) DEFAULT NULL COMMENT '角色描述',
  `code` varchar(20) DEFAULT NULL COMMENT '角色编号',
  `create_id` int(11) DEFAULT NULL COMMENT '创建者id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` datetime DEFAULT NULL COMMENT '修改者id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`sys_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色表';

DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `sys_role_permission_id` bigint(18) NOT NULL COMMENT '系统资源id' AUTO_INCREMENT,
  `sys_menu_id` bigint(18) NOT NULL COMMENT '资源id',
  `sys_role_id` bigint(18) NOT NULL COMMENT '角色id',
	`create_id` bigint(18) DEFAULT NULL COMMENT '创建者id',
  `update_id` bigint(18) DEFAULT NULL COMMENT '修改者id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`sys_role_permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色权限表';

DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `sys_log_id` bigint(18) NOT NULL COMMENT '日志id' AUTO_INCREMENT,
  `module_name` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '模块名称',
  `class_name` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '全类名或者操作url',
  `ip` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT 'ip',
  `msg` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '信息',
  `params` varchar(5000) COLLATE utf8_bin DEFAULT NULL COMMENT '请求参数',
  `user_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '用户登录名',
	`log_type` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '日志来源类型',
  `times` bigint(13) DEFAULT NULL COMMENT '使用时长 毫米单位',
	`create_id` bigint(18) DEFAULT NULL COMMENT '创建者id',
  `update_id` bigint(18) DEFAULT NULL COMMENT '修改者id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`sys_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统日志记录表';
