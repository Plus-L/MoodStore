/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : moodstore

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 15/10/2022 17:02:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mood_comment
-- ----------------------------
DROP TABLE IF EXISTS `mood_comment`;
CREATE TABLE `mood_comment`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(0) NULL DEFAULT NULL,
  `entity_type` int(0) NULL DEFAULT NULL,
  `entity_id` int(0) NULL DEFAULT NULL,
  `target_id` int(0) NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文本',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_user_id`(`user_id`) USING BTREE,
  INDEX `index_entity_id`(`entity_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mood_comment
-- ----------------------------

-- ----------------------------
-- Table structure for mood_discuss_post
-- ----------------------------
DROP TABLE IF EXISTS `mood_discuss_post`;
CREATE TABLE `mood_discuss_post`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(0) NOT NULL,
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `type` int(0) NULL DEFAULT NULL COMMENT '0-普通; 1-置顶;',
  `status` int(0) NULL DEFAULT NULL COMMENT '0-正常; 1-精华; 2-拉黑;',
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `comment_count` int(0) NULL DEFAULT NULL,
  `score` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mood_discuss_post
-- ----------------------------

-- ----------------------------
-- Table structure for mood_message
-- ----------------------------
DROP TABLE IF EXISTS `mood_message`;
CREATE TABLE `mood_message`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `from_id` bigint(0) NULL DEFAULT NULL,
  `to_id` bigint(0) NULL DEFAULT NULL,
  `conversation_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `status` int(0) NULL DEFAULT NULL COMMENT '0-未读;1-已读;2-删除;',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_from_id`(`from_id`) USING BTREE,
  INDEX `index_to_id`(`to_id`) USING BTREE,
  INDEX `index_conversation_id`(`conversation_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mood_message
-- ----------------------------

-- ----------------------------
-- Table structure for mood_user
-- ----------------------------
DROP TABLE IF EXISTS `mood_ums_user`;
CREATE TABLE `mood_ums_user` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `user_level_id` bigint(20) DEFAULT NULL,
    `username` varchar(64) DEFAULT NULL COMMENT '用户名',
    `password` varchar(64) DEFAULT NULL COMMENT '密码',
    `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
    `phone` varchar(64) DEFAULT NULL COMMENT '手机号码',
    `status` int(1) DEFAULT NULL COMMENT '帐号启用状态:0->禁用；1->启用',
    `create_time` datetime DEFAULT NULL COMMENT '注册时间',
    `icon` varchar(500) DEFAULT NULL COMMENT '头像',
    `gender` int(1) DEFAULT NULL COMMENT '性别：0->未知；1->男；2->女',
    `birthday` date DEFAULT NULL COMMENT '生日',
    `city` varchar(64) DEFAULT NULL COMMENT '所做城市',
    `job` varchar(100) DEFAULT NULL COMMENT '职业',
    `personalized_signature` varchar(200) DEFAULT NULL COMMENT '个性签名',
    `integration` int(11) DEFAULT NULL COMMENT '积分',
    `growth` int(11) DEFAULT NULL COMMENT '成长值',
    `luckey_count` int(11) DEFAULT NULL COMMENT '剩余抽奖次数',
    `history_integration` int(11) DEFAULT NULL COMMENT '历史积分数量',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_username` (`username`),
    UNIQUE KEY `idx_phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='会员表';

-- ----------------------------
-- Records of mood_user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
