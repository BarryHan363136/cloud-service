/*
Navicat MySQL Data Transfer

Source Server         : Local-VM128
Source Server Version : 50723
Source Host           : 192.168.33.128:3306
Source Database       : spark_security

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-08-28 16:23:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `spark_role`
-- ----------------------------
DROP TABLE IF EXISTS `spark_role`;
CREATE TABLE `spark_role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of spark_role
-- ----------------------------
INSERT INTO `spark_role` VALUES ('1', 'ROLE_USER');
INSERT INTO `spark_role` VALUES ('2', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for `spark_user`
-- ----------------------------
DROP TABLE IF EXISTS `spark_user`;
CREATE TABLE `spark_user` (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3j3vmlbdcw3wnpk6pkrruwc73` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of spark_user
-- ----------------------------
INSERT INTO `spark_user` VALUES ('1', 'zhangsan', '$2a$10$yHUrJQ1Cw893R0/rSJ/0HOhXFDLYtLQ4d2WrEdm8LR7vVDgCk8qRy');
INSERT INTO `spark_user` VALUES ('2', 'lisi', '$2a$10$qRNK4AJtCMM.GlTDX6gCiOEgiqNEsyGiTrkINDm03bDmqR/Mt6WZS');

-- ----------------------------
-- Table structure for `spark_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `spark_user_role`;
CREATE TABLE `spark_user_role` (
  `uid` bigint(20) NOT NULL,
  `rid` bigint(20) NOT NULL,
  KEY `FK2xqq6u0voyl6dtwso8uwd1qjy` (`rid`),
  KEY `FKlnq7rbeo3c3fn5qdudbl3of46` (`uid`),
  CONSTRAINT `FK2xqq6u0voyl6dtwso8uwd1qjy` FOREIGN KEY (`rid`) REFERENCES `spark_role` (`id`),
  CONSTRAINT `FKlnq7rbeo3c3fn5qdudbl3of46` FOREIGN KEY (`uid`) REFERENCES `spark_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of spark_user_role
-- ----------------------------
INSERT INTO `spark_user_role` VALUES ('1', '1');
INSERT INTO `spark_user_role` VALUES ('2', '2');
