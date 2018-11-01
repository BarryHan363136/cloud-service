/*
Navicat MySQL Data Transfer

Source Server         : mysql-master192.168.33.153
Source Server Version : 50724
Source Host           : 192.168.33.153:3306
Source Database       : spark_test

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2018-11-01 16:47:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `spark_staff`
-- ----------------------------
DROP TABLE IF EXISTS `spark_staff`;
CREATE TABLE `spark_staff` (
  `id` bigint(20) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `gender` tinyint(4) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of spark_staff
-- ----------------------------
INSERT INTO `spark_staff` VALUES ('1', '张三', '0', '15865438569', '上海市闵行区莲花路', '张三测试数据');
INSERT INTO `spark_staff` VALUES ('2', '李四', '0', '13572568953', '上海市虹口区长治路', '李四测试数据');
