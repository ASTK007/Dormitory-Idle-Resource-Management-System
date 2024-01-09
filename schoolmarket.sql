/*
 Navicat Premium Data Transfer

 Source Server         : ldk
 Source Server Type    : MySQL
 Source Server Version : 80033 (8.0.33)
 Source Host           : localhost:3306
 Source Schema         : schoolmarket

 Target Server Type    : MySQL
 Target Server Version : 80033 (8.0.33)
 File Encoding         : 65001

 Date: 09/01/2024 12:24:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `uid` int NULL DEFAULT NULL COMMENT '用户ID',
  `comment_content` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `comment_time` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '评论时间',
  `gid` int NULL DEFAULT NULL COMMENT '物品ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 2, '这脑白金看起来好好吃哦!', '2023-12-23 23:09:05 ', 2);
INSERT INTO `comment` VALUES (2, 3, '这iPhone15看起来是伊拉克成色唉', '2023-12-24 13:09:05 ', 1);
INSERT INTO `comment` VALUES (5, 22, '二手iPhone永远的神！', '2024-01-06 01:05:33', 1);
INSERT INTO `comment` VALUES (6, 2, '这价格只能当传家宝了', '2024-01-07 08:28:52', 1);
INSERT INTO `comment` VALUES (10, 34, '这个保真吗', '2024-01-08 10:56:05', 11);

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `goods_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `goods_type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商品类型：数码、服装、日用品、家具、食品',
  `goods_des` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商品描述',
  `goods_img` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商品图片',
  `goods_status` varchar(16) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商品状态 上架中/已下架',
  `goods_value` float NULL DEFAULT NULL COMMENT '商品价格',
  `goods_num` int NULL DEFAULT NULL COMMENT '商品数量',
  `uid` int NULL DEFAULT NULL COMMENT '关联用户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uid`(`uid` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, 'iPhone15', '数码', '女生自用99新', 'http://localhost:8081/upload/1f4782d2-a593-40c6-9588-4f6696e83a0b.jpg', '上架中', 3999, 1, 2);
INSERT INTO `goods` VALUES (2, '脑白金', '食品', '全新未拆封', 'http://localhost:8081/upload/7849013b-eef8-4b6f-9dd5-843f58ca0512.jpg', '上架中', 200, 3, 3);
INSERT INTO `goods` VALUES (11, 'HUAWEI Mate 60 Pro 雅川青 12+512', '数码', '二手自用九九新，可小刀', 'http://localhost:8081/upload/bdd42496-c33c-456f-9d20-a184b316b8b1.jpg', '上架中', 6666, 1, 22);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `uid` int NULL DEFAULT NULL COMMENT '买家用户ID',
  `gid` int NULL DEFAULT NULL COMMENT '物品ID',
  `num` int NULL DEFAULT NULL COMMENT '商品数量',
  `order_time` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '出售时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (2, 3, 1, 1, '2022-12-23 18:53:36');
INSERT INTO `orders` VALUES (10, 3, 1, 1, '2024-01-06 04:51:09');
INSERT INTO `orders` VALUES (11, 27, 11, 1, '2024-01-08 06:33:29');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `user_type` varchar(16) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户类型',
  `user_passwd` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `user_phonenum` varchar(16) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户号码',
  `user_email` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'adm1', 'admin', '123', '', NULL);
INSERT INTO `user` VALUES (2, 'usr1', 'user', '123', '15687596423', '24854585@qq.com');
INSERT INTO `user` VALUES (3, '卢本伟', 'user', '1234', '15648932657', '15156156456@qq.com');
INSERT INTO `user` VALUES (22, '任我行', 'user', '123', '14432534552', '4321411@qq.com');
INSERT INTO `user` VALUES (23, 'ldk', 'user', '123qwe', '15642356799', '1564556416@qq.com');
INSERT INTO `user` VALUES (24, 'user3', 'user', '12345', '15448984946', '2564449489@163.com');
INSERT INTO `user` VALUES (26, 'user5', 'user', '123', '15464494946', '1645645642@qq.com');
INSERT INTO `user` VALUES (27, '123456', 'user', '123654', '18888888888', '15465456465@qq.com');
INSERT INTO `user` VALUES (31, '123', 'user', '123', '14156465465', '45465156@qq.com');
INSERT INTO `user` VALUES (32, '1234', 'user', '1234', '15456465467', '');
INSERT INTO `user` VALUES (33, '12345', 'user', '12345', '', '');
INSERT INTO `user` VALUES (34, 'lwh', 'user', '123', '15616798566', '5161631516515');

SET FOREIGN_KEY_CHECKS = 1;
