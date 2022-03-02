/*
 Navicat MySQL Data Transfer

 Source Server         : 李哥哥
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : localhost:3306
 Source Schema         : boot

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 02/03/2022 16:57:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_book
-- ----------------------------
DROP TABLE IF EXISTS `tbl_book`;
CREATE TABLE `tbl_book`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_book
-- ----------------------------
INSERT INTO `tbl_book` VALUES (1, '摄影', '摄影教程', '摄影师');
INSERT INTO `tbl_book` VALUES (2, '英语', '英语教程', '翻译官');
INSERT INTO `tbl_book` VALUES (3, '数学', '数学教程', '数学家');
INSERT INTO `tbl_book` VALUES (4, '地理', '地理教程', '地理家');
INSERT INTO `tbl_book` VALUES (5, '历史', '历史教程', '历史师');
INSERT INTO `tbl_book` VALUES (6, '政治', '政治教程', '政治家');
INSERT INTO `tbl_book` VALUES (7, '物理', '物理教程', '科学家');
INSERT INTO `tbl_book` VALUES (8, '生物', '生物教程', '生物家');
INSERT INTO `tbl_book` VALUES (9, '播音', '播音主持', '播音佳');
INSERT INTO `tbl_book` VALUES (11, '美术', '美术教程', '美术李');
INSERT INTO `tbl_book` VALUES (12, '软件', '软件开发', '软件李');
INSERT INTO `tbl_book` VALUES (20, '爱情', '“我的爱情故事”', '2019年，我上了大学....');
INSERT INTO `tbl_book` VALUES (27, 's', 'c', 'v');
INSERT INTO `tbl_book` VALUES (28, 'das', 'va', 's');
INSERT INTO `tbl_book` VALUES (29, 'vdas', 'vads', 'v');
INSERT INTO `tbl_book` VALUES (30, 'ba', 'b', 'bba');
INSERT INTO `tbl_book` VALUES (31, 'd', 'b', 'd');
INSERT INTO `tbl_book` VALUES (32, 's', 'a', 'v');
INSERT INTO `tbl_book` VALUES (33, 'd', 'b', 'c');
INSERT INTO `tbl_book` VALUES (34, 'ad', 'v', 'ba');

SET FOREIGN_KEY_CHECKS = 1;
