/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : localhost:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 11/11/2022 15:16:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_case
-- ----------------------------
DROP TABLE IF EXISTS `tb_case`;
CREATE TABLE `tb_case`  (
  `caseId` int(8) NOT NULL AUTO_INCREMENT,
  `patientId` int(8) NOT NULL,
  `medicalNeed` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createData` date NOT NULL,
  PRIMARY KEY (`caseId`) USING BTREE,
  INDEX `fk_case_patient`(`patientId`) USING BTREE,
  CONSTRAINT `fk_case_patient` FOREIGN KEY (`patientId`) REFERENCES `tb_patient` (`patientId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_case
-- ----------------------------
INSERT INTO `tb_case` VALUES (1, 2, '111', '2022-11-10');
INSERT INTO `tb_case` VALUES (7, 3, '12;23;4', '2022-11-10');

-- ----------------------------
-- Table structure for tb_medical
-- ----------------------------
DROP TABLE IF EXISTS `tb_medical`;
CREATE TABLE `tb_medical`  (
  `medicalId` int(4) NOT NULL,
  `medicalName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`medicalId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_medical
-- ----------------------------

-- ----------------------------
-- Table structure for tb_patient
-- ----------------------------
DROP TABLE IF EXISTS `tb_patient`;
CREATE TABLE `tb_patient`  (
  `patientId` int(8) NOT NULL AUTO_INCREMENT,
  `patientName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`patientId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_patient
-- ----------------------------
INSERT INTO `tb_patient` VALUES (2, '戴頴璇');
INSERT INTO `tb_patient` VALUES (3, '邵慧珊');
INSERT INTO `tb_patient` VALUES (4, '邓杰宏');
INSERT INTO `tb_patient` VALUES (5, '薛致远');
INSERT INTO `tb_patient` VALUES (6, '石晓明');
INSERT INTO `tb_patient` VALUES (7, '朱慧敏');
INSERT INTO `tb_patient` VALUES (8, '郭心穎');

SET FOREIGN_KEY_CHECKS = 1;
