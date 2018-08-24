/*
Navicat MySQL Data Transfer

Source Server         : Local-VM128
Source Server Version : 50723
Source Host           : 192.168.33.128:3306
Source Database       : spark_security

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-08-20 21:33:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `spark_user`
-- ----------------------------
DROP TABLE IF EXISTS `spark_user`;
CREATE TABLE `spark_user` (
  `id` bigint(20) NOT NULL,
  `user_name` varchar(64) DEFAULT NULL,
  `user_pwd` varchar(64) DEFAULT NULL,
  `real_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of spark_user
-- ----------------------------
INSERT INTO `spark_user` VALUES ('1', 'zhangsan', '123456', '张三');
