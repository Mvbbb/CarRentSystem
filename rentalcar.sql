/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : rentalcar

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 01/12/2020 12:08:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `adminname` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `admin_password` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`adminname`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('12345', '12345');

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '车辆编号',
  `carname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车辆名称',
  `cartype` varchar(25) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL COMMENT '车辆型号',
  `rent` tinyint(1) NOT NULL COMMENT '是否被租用',
  `price` decimal(10, 2) NOT NULL COMMENT '价格',
  `color` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '颜色',
  `fk_username` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_username`(`fk_username`) USING BTREE,
  CONSTRAINT `car_ibfk_1` FOREIGN KEY (`fk_username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car
-- ----------------------------
INSERT INTO `car` VALUES (4, '小飞机', 'ccc919', 1, 8.80, '红', '123');
INSERT INTO `car` VALUES (10, '可靠的', 'fej-1', 1, 33.30, '白', '123');
INSERT INTO `car` VALUES (11, '划分', 'fefe-2', 1, 99.00, '绿', '123');
INSERT INTO `car` VALUES (23, '宝马', 'ew-2', 1, 32.00, '绿', '123');
INSERT INTO `car` VALUES (24, '保时捷', 're-2', 0, 89.10, '灰', NULL);
INSERT INTO `car` VALUES (25, '灰机', 'fei-21', 0, 32.00, '紫', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `username` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `user_password` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`username`) USING BTREE,
  INDEX `user_password`(`user_password`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('123', '123');
INSERT INTO `user` VALUES ('1234', '123');
INSERT INTO `user` VALUES ('123456789', '123');
INSERT INTO `user` VALUES ('gerge', '123');
INSERT INTO `user` VALUES ('1235', '1235');
INSERT INTO `user` VALUES ('ds', 'ds');

SET FOREIGN_KEY_CHECKS = 1;
