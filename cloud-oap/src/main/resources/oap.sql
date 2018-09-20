/*
Navicat MySQL Data Transfer

Source Server         : LOCALVM~192.168.33.128
Source Server Version : 50723
Source Host           : 192.168.33.128:3306
Source Database       : oap

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-09-18 16:21:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `spark_customer`
-- ----------------------------
DROP TABLE IF EXISTS `spark_customer`;
CREATE TABLE `spark_customer` (
  `id` bigint(20) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `accout` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of spark_customer
-- ----------------------------
INSERT INTO `spark_customer` VALUES ('1000', '楚鸣鸣', 'aaa', 'bbb');
INSERT INTO `spark_customer` VALUES ('1001', '李恩科', 'ccc', 'ddd');
INSERT INTO `spark_customer` VALUES ('1002', '王爱民', 'eee', 'fff');

-- ----------------------------
-- Table structure for `spark_goods`
-- ----------------------------
DROP TABLE IF EXISTS `spark_goods`;
CREATE TABLE `spark_goods` (
  `id` bigint(20) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of spark_goods
-- ----------------------------

-- ----------------------------
-- Table structure for `spark_order`
-- ----------------------------
DROP TABLE IF EXISTS `spark_order`;
CREATE TABLE `spark_order` (
  `id` bigint(20) NOT NULL,
  `customer_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of spark_order
-- ----------------------------
INSERT INTO `spark_order` VALUES ('1', '1000');
INSERT INTO `spark_order` VALUES ('2', '1001');
INSERT INTO `spark_order` VALUES ('3', '1002');

-- ----------------------------
-- Table structure for `spark_order_goods`
-- ----------------------------
DROP TABLE IF EXISTS `spark_order_goods`;
CREATE TABLE `spark_order_goods` (
  `id` bigint(20) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `goods_id` bigint(20) DEFAULT NULL,
  `quantity` float DEFAULT NULL,
  `unit_price` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of spark_order_goods
-- ----------------------------
