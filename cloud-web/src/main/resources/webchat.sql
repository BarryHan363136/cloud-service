/*
Navicat MySQL Data Transfer

Source Server         : Local-VM128
Source Server Version : 50723
Source Host           : 192.168.33.128:3306
Source Database       : webchat

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-08-16 15:58:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `spark_shiro_permission`
-- ----------------------------
DROP TABLE IF EXISTS `spark_shiro_permission`;
CREATE TABLE `spark_shiro_permission` (
  `permission_id` bigint(20) NOT NULL,
  `permission_name` varchar(200) NOT NULL,
  `permission_url` varchar(200) NOT NULL,
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of spark_shiro_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `spark_shiro_role`
-- ----------------------------
DROP TABLE IF EXISTS `spark_shiro_role`;
CREATE TABLE `spark_shiro_role` (
  `role_id` bigint(20) NOT NULL,
  `rele_name` varchar(100) NOT NULL,
  `describe` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of spark_shiro_role
-- ----------------------------

-- ----------------------------
-- Table structure for `spark_shiro_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `spark_shiro_role_permission`;
CREATE TABLE `spark_shiro_role_permission` (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_ROLE_PERMISSION` (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of spark_shiro_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `spark_shiro_user`
-- ----------------------------
DROP TABLE IF EXISTS `spark_shiro_user`;
CREATE TABLE `spark_shiro_user` (
  `user_id` bigint(64) NOT NULL,
  `user_name` varchar(32) NOT NULL,
  `user_pwd` varchar(128) NOT NULL,
  `nick` varchar(100) DEFAULT NULL,
  `real_name` varchar(32) DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `mobile` varchar(32) DEFAULT NULL,
  `position` varchar(100) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL COMMENT '加密密码的盐',
  `state` int(2) DEFAULT NULL COMMENT '用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `last_login_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of spark_shiro_user
-- ----------------------------
INSERT INTO `spark_shiro_user` VALUES ('1', 'zhangsan', '1985425htb', 'zsan', '张三', '0', '30', 'zhangsan@163.com', '15936523654', '员工', 'zhangsan', '1', '2018-08-16 15:41:50', '2018-08-16 15:41:50', null);
INSERT INTO `spark_shiro_user` VALUES ('2', 'lisi', 'fasdf', 'lsi', '李四', '1', '20', 'lisi@163.com', '15896352365', '经理', 'lisi', '1', '2018-08-15 14:18:44', null, null);
INSERT INTO `spark_shiro_user` VALUES ('3', 'wangwu', 'fafa', 'wwu', '王五', '0', '40', 'wangwu@163.com', '15842352362', '总监', 'wangwu', '2', '2018-08-15 14:20:00', null, null);

-- ----------------------------
-- Table structure for `spark_shiro_user_role_relation`
-- ----------------------------
DROP TABLE IF EXISTS `spark_shiro_user_role_relation`;
CREATE TABLE `spark_shiro_user_role_relation` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_USER_ROLE_RELATION` (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of spark_shiro_user_role_relation
-- ----------------------------

-- ----------------------------
-- Table structure for `webchat_user`
-- ----------------------------
DROP TABLE IF EXISTS `webchat_user`;
CREATE TABLE `webchat_user` (
  `user_id` varchar(64) NOT NULL,
  `user_name` varchar(64) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of webchat_user
-- ----------------------------
INSERT INTO `webchat_user` VALUES ('0001', '张三', 'zhangsan@163.com', '15896534522', '上海市闵行区古美路', null);
INSERT INTO `webchat_user` VALUES ('0002', '李四', 'lisi@163.com', '15975413582', '上海市徐汇区宜山路', null);
