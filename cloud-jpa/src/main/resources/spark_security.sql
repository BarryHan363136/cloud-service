/*
Navicat MySQL Data Transfer

Source Server         : Local-VM128
Source Server Version : 50723
Source Host           : 192.168.33.128:3306
Source Database       : spark_security

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-08-20 17:31:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `spark_staff`
-- ----------------------------
DROP TABLE IF EXISTS `spark_staff`;
CREATE TABLE `spark_staff` (
  `id` bigint(20) NOT NULL,
  `gender` int(11) NOT NULL,
  `password` varchar(255) NOT NULL,
  `real_name` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of spark_staff
-- ----------------------------
INSERT INTO `spark_staff` VALUES ('100', '0', '123456', '张三', 'zhangsan');
INSERT INTO `spark_staff` VALUES ('151', '0', '123456', '张三', 'zhangsan');
INSERT INTO `spark_staff` VALUES ('201', '0', '123456', '张三', 'zhangsan');

-- ----------------------------
-- Table structure for `staff_sequence`
-- ----------------------------
DROP TABLE IF EXISTS `staff_sequence`;
CREATE TABLE `staff_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of staff_sequence
-- ----------------------------
INSERT INTO `staff_sequence` VALUES ('300');
