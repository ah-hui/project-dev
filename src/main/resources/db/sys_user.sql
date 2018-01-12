/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50638
Source Host           : localhost:3306
Source Database       : sys_db

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2018-01-12 14:55:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `created_by` int(19) DEFAULT '-1' COMMENT '创建人',
  `date_created` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `updated_by` int(19) DEFAULT '-1' COMMENT '最后修改人',
  `version` int(19) DEFAULT '0' COMMENT '版本号',
  `account_expired` bit(1) DEFAULT NULL,
  `email` varchar(120) DEFAULT NULL,
  `hashed_password` varchar(240) NOT NULL,
  `login_name` varchar(20) NOT NULL,
  `nick_name` varchar(60) DEFAULT NULL,
  `phone` varchar(20) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_blyyljcvmmqokx6t10jvmcj98` (`login_name`),
  UNIQUE KEY `UK_pulp17fvich5aby4m0kc820h6` (`phone`),
  UNIQUE KEY `UK_ahtq5ew3v0kt1n7hf1sgp7p8l` (`email`),
  UNIQUE KEY `UK_nojhu32mbxra1u46c3ydgo3ct` (`nick_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', null, '2017-12-15 15:01:52', '\0', '2017-12-15 15:01:52', null, null, '0', '\0', '13085399920@163.com', '44563daed882df6c85f6ab208d12ee09', 'lgh', '刘光辉', '13085399920', null);
INSERT INTO `sys_user` VALUES ('2', null, '2017-12-25 15:26:23', '\0', '2017-12-25 15:26:23', null, null, '0', '\0', '131@163.com', 'c6a1ec89b4dbe0de5b7b6acd8ab0a25c', 'user', 'user', '131', null);
INSERT INTO `sys_user` VALUES ('4', null, '2017-12-15 15:01:52', null, '2017-12-15 15:01:52', null, null, '0', '\0', '13085399921@163.com', '111', 'lgh2', '刘光辉2', '13085399921', null);
