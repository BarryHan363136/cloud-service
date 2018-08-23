/*
Navicat MySQL Data Transfer

Source Server         : Local-VM128
Source Server Version : 50723
Source Host           : 192.168.33.128:3306
Source Database       : spark_jpa

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-08-23 14:19:33
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_566ggk3hrc4kg0ckv17d25g36` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of spark_staff
-- ----------------------------
INSERT INTO `spark_staff` VALUES ('100', '0', '123456', '张三', 'zhangsan');
INSERT INTO `spark_staff` VALUES ('101', '0', '654321', '李四', 'lisi');
