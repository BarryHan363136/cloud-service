/*
Navicat MySQL Data Transfer

Source Server         : Local-VM128
Source Server Version : 50723
Source Host           : 192.168.33.128:3306
Source Database       : webchat

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-08-10 10:30:13
*/

SET FOREIGN_KEY_CHECKS=0;

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
INSERT INTO `webchat_user` VALUES ('0001', '张三', null, null, null, null);
INSERT INTO `webchat_user` VALUES ('0002', '李四', null, null, null, null);
