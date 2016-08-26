/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : springboot

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2016-08-26 11:27:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for demo_info
-- ----------------------------
DROP TABLE IF EXISTS `demo_info`;
CREATE TABLE `demo_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of demo_info
-- ----------------------------
INSERT INTO `demo_info` VALUES ('1', 'admin', '123');
INSERT INTO `demo_info` VALUES ('2', 'sun', '456');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_ids` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `resource_type` enum('menu','button') DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `seq` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '', '系统设置', '0', '', 'system', 'menu', '', null);
INSERT INTO `sys_permission` VALUES ('2', '', '用户添加', '7', '', 'userInfo:add', 'button', 'userInfo/add', null);
INSERT INTO `sys_permission` VALUES ('5', '', '用户删除', '7', null, 'userInfo:del', 'button', 'userInfo/del', null);
INSERT INTO `sys_permission` VALUES ('6', '', '用户编辑', '7', null, 'userInfo:edit', 'button', 'userInfo/edit', null);
INSERT INTO `sys_permission` VALUES ('7', '', '用户管理', '1', null, 'userInfo:list', 'menu', 'userInfo/list', null);
INSERT INTO `sys_permission` VALUES ('8', '', '资源管理', '1', null, 'permission:list', 'menu', 'sysper/list', null);
INSERT INTO `sys_permission` VALUES ('11', '', '资源增加', '8', null, 'permission:add', 'button', 'sysper/add', null);
INSERT INTO `sys_permission` VALUES ('12', '', '资源编辑', '8', null, 'permission:edit', 'button', 'sysper/edit', null);
INSERT INTO `sys_permission` VALUES ('13', '', '资源删除', '8', null, 'permission:del', 'button', 'sysper/del', null);
INSERT INTO `sys_permission` VALUES ('16', '', '角色管理', '1', null, 'role:list', 'menu', 'role/list', null);
INSERT INTO `sys_permission` VALUES ('17', '', '角色添加', '16', null, 'role:add', 'button', 'role/add', null);
INSERT INTO `sys_permission` VALUES ('18', '', '角色编辑', '16', null, 'role:edit', 'button', 'role/edit', null);
INSERT INTO `sys_permission` VALUES ('19', '', '角色删除', '16', null, 'role:del', 'button', 'role/del', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '', '管理员', 'admin');
INSERT INTO `sys_role` VALUES ('8', '', '普通用户', 'user');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  KEY `FKomxrs8a388bknvhjokh440waq` (`permission_id`),
  KEY `FK9q28ewrhntqeipl1t04kh1be7` (`role_id`),
  CONSTRAINT `FK9q28ewrhntqeipl1t04kh1be7` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FKomxrs8a388bknvhjokh440waq` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1');
INSERT INTO `sys_role_permission` VALUES ('1', '2');
INSERT INTO `sys_role_permission` VALUES ('1', '5');
INSERT INTO `sys_role_permission` VALUES ('1', '6');
INSERT INTO `sys_role_permission` VALUES ('1', '8');
INSERT INTO `sys_role_permission` VALUES ('1', '7');
INSERT INTO `sys_role_permission` VALUES ('1', '11');
INSERT INTO `sys_role_permission` VALUES ('1', '12');
INSERT INTO `sys_role_permission` VALUES ('1', '16');
INSERT INTO `sys_role_permission` VALUES ('1', '13');
INSERT INTO `sys_role_permission` VALUES ('1', '17');
INSERT INTO `sys_role_permission` VALUES ('1', '18');
INSERT INTO `sys_role_permission` VALUES ('1', '19');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `role_id` bigint(20) NOT NULL,
  `uid` bigint(20) NOT NULL,
  KEY `FKgkmyslkrfeyn9ukmolvek8b8f` (`uid`),
  KEY `FKhh52n8vd4ny9ff4x9fb8v65qx` (`role_id`),
  CONSTRAINT `FKgkmyslkrfeyn9ukmolvek8b8f` FOREIGN KEY (`uid`) REFERENCES `user_info` (`uid`),
  CONSTRAINT `FKhh52n8vd4ny9ff4x9fb8v65qx` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('1', '2');
INSERT INTO `sys_user_role` VALUES ('1', '5');
INSERT INTO `sys_user_role` VALUES ('1', '6');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `state` tinyint(4) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `UK_f2ksd6h8hsjtd57ipfq9myr64` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '管理员', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', '0', 'admin');
INSERT INTO `user_info` VALUES ('2', '123', '231', '131', '1', '131');
INSERT INTO `user_info` VALUES ('5', '李四', 'f0ba154c35029b214a1328cb6ae83131', null, '0', 'lilili');
INSERT INTO `user_info` VALUES ('6', '赵四', null, null, '1', 'zhaosi');
SET FOREIGN_KEY_CHECKS=1;
