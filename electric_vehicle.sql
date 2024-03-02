/*
 Navicat MySQL Data Transfer

 Source Server         : MySql
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : electric_vehicle

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 07/01/2024 13:11:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for breakdown
-- ----------------------------
DROP TABLE IF EXISTS `breakdown`;
CREATE TABLE `breakdown`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `time` datetime(0) NULL DEFAULT NULL,
  `pile_id` int(0) NULL DEFAULT NULL,
  `user_id` int(0) NULL DEFAULT NULL,
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pile_position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `point_id` int(0) NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of breakdown
-- ----------------------------
INSERT INTO `breakdown` VALUES (1, '2023-11-29 20:04:31', 1, 1, '插口坏了', '双理楼', 1, '已完成');
INSERT INTO `breakdown` VALUES (2, '2023-12-01 21:06:12', 2, 2, '插口坏了', '操场', 1, '已完成');
INSERT INTO `breakdown` VALUES (3, '2023-12-18 15:20:49', 2, 1, 'www', '双理楼', 1, '已完成');
INSERT INTO `breakdown` VALUES (4, '2024-01-05 21:34:10', 0, 1, NULL, '双理楼', 1, '未完成');
INSERT INTO `breakdown` VALUES (5, '2024-01-05 21:51:11', 2, 1, '1234', '操场', 2, '已完成');

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car`  (
  `id` int(0) NOT NULL,
  `user_id` int(0) NULL DEFAULT NULL,
  `color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `car_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car
-- ----------------------------
INSERT INTO `car` VALUES (1, 1, '粉色', '雅迪', 'jisoo', 'https://web-framework-dngb.oss-cn-beijing.aliyuncs.com/066a035b-1fab-4230-87db-3fdef28544d3.jpg');
INSERT INTO `car` VALUES (2, 2, '白色', '台铃', 'jennie', 'https://web-framework-dngb.oss-cn-beijing.aliyuncs.com/cd6e36ad-427f-4fc8-b2df-a317a9ec33c1.jpg');
INSERT INTO `car` VALUES (3, 4, '黑色', '小刀', '朴彩英', 'https://web-framework-dngb.oss-cn-beijing.aliyuncs.com/37af4457-b3b9-4852-9225-0fa7e036eb1d.jpg');

-- ----------------------------
-- Table structure for charge_order
-- ----------------------------
DROP TABLE IF EXISTS `charge_order`;
CREATE TABLE `charge_order`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NULL DEFAULT NULL,
  `pile_id` int(0) NULL DEFAULT NULL,
  `start_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `car_id` int(0) NULL DEFAULT NULL,
  `money` int(0) NULL DEFAULT NULL,
  `point_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of charge_order
-- ----------------------------
INSERT INTO `charge_order` VALUES (1, 2, 1, '2023-11-28 20:05:13', '2023-11-29 20:05:17', 1, 2, 1);
INSERT INTO `charge_order` VALUES (2, 3, 2, '2023-12-04 16:26:04', '2023-12-04 17:26:07', 2, 2, 1);
INSERT INTO `charge_order` VALUES (5, 3, 3, '2023-12-05 17:18:25', '2023-12-05 17:39:28', 3, 2, 1);
INSERT INTO `charge_order` VALUES (9, 2, 1, '2023-12-06 21:21:38', '2023-12-06 21:21:46', 2, 2, 2);
INSERT INTO `charge_order` VALUES (10, 3, 2, '2023-12-06 21:22:27', '2023-12-06 22:01:18', 3, 0, 1);
INSERT INTO `charge_order` VALUES (11, 3, 2, '2023-12-06 22:02:15', '2023-12-06 22:02:23', 3, 2, 1);
INSERT INTO `charge_order` VALUES (12, 2, 2, '2023-12-08 16:42:04', '2023-12-08 17:01:31', 2, 2, 1);
INSERT INTO `charge_order` VALUES (13, 1, 1, '2023-12-18 14:43:05', '2023-12-18 14:51:37', 1, 2, 1);
INSERT INTO `charge_order` VALUES (14, 1, 1, '2024-01-05 21:35:35', '2024-01-05 21:35:55', 1, 2, 1);
INSERT INTO `charge_order` VALUES (15, 1, 1, '2024-01-05 21:50:45', '2024-01-05 21:51:44', 1, 2, 1);
INSERT INTO `charge_order` VALUES (16, 1, 1, '2024-01-06 08:51:33', '2024-01-06 08:51:47', 1, 2, 1);

-- ----------------------------
-- Table structure for charging
-- ----------------------------
DROP TABLE IF EXISTS `charging`;
CREATE TABLE `charging`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `number` int(0) NULL DEFAULT NULL,
  `using_number` int(0) NULL DEFAULT NULL,
  `free_number` int(0) NULL DEFAULT NULL,
  `attendant_id` int(0) NULL DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of charging
-- ----------------------------
INSERT INTO `charging` VALUES (1, '双理楼', 20, 2, 17, 1, 'https://web-framework-dngb.oss-cn-beijing.aliyuncs.com/381cbb51-11a0-40f6-b370-679e7bbfaf24.jpg');
INSERT INTO `charging` VALUES (2, '操场', 20, 2, 18, 1, 'https://web-framework-dngb.oss-cn-beijing.aliyuncs.com/02e6d655-1840-4f49-a557-9fa69ed7cebc.jpg');
INSERT INTO `charging` VALUES (3, '食堂', 20, 0, 20, 0, 'https://web-framework-dngb.oss-cn-beijing.aliyuncs.com/cf6c92b4-57a6-45a4-9570-ab3e34712d87.jpg');

-- ----------------------------
-- Table structure for charging_pile
-- ----------------------------
DROP TABLE IF EXISTS `charging_pile`;
CREATE TABLE `charging_pile`  (
  `id` int(0) NOT NULL,
  `point_id` int(0) NOT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `point_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of charging_pile
-- ----------------------------
INSERT INTO `charging_pile` VALUES (1, 1, '空闲', '双理楼');
INSERT INTO `charging_pile` VALUES (1, 2, '空闲', '操场');
INSERT INTO `charging_pile` VALUES (1, 3, '空闲', '食堂');
INSERT INTO `charging_pile` VALUES (2, 1, '空闲', '双理楼');
INSERT INTO `charging_pile` VALUES (3, 1, '空闲', '双理楼');

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`  (
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role_identification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `describeing` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `status` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('超级管理员', 'admin', 'admin', '这是超级管理员，拥有一切权限', '2023-12-01 08:56:45', 1);
INSERT INTO `dept` VALUES ('用户', 'other', '测试用户', 'other', '2023-12-03 08:57:44', 1);
INSERT INTO `dept` VALUES ('管理员', 'role', 'admin', '普通管理员', '2023-12-02 08:57:20', 1);

-- ----------------------------
-- Table structure for parking
-- ----------------------------
DROP TABLE IF EXISTS `parking`;
CREATE TABLE `parking`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `number` int(0) NULL DEFAULT NULL,
  `attendant_id` int(0) NULL DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of parking
-- ----------------------------
INSERT INTO `parking` VALUES (1, '双理楼', 25, 1, 'https://web-framework-dngb.oss-cn-beijing.aliyuncs.com/3b8df196-06d2-457f-8856-4eb0a9d32bf4.jpg');
INSERT INTO `parking` VALUES (2, '田家炳', 40, 1, 'https://web-framework-dngb.oss-cn-beijing.aliyuncs.com/9f06f718-1842-4fcb-837d-4dab6395a484.jpg');
INSERT INTO `parking` VALUES (3, '九栋', 50, 0, 'https://web-framework-dngb.oss-cn-beijing.aliyuncs.com/4ffb217a-2e6e-442b-917e-f620a114d0c2.jpg');

-- ----------------------------
-- Table structure for topup_order
-- ----------------------------
DROP TABLE IF EXISTS `topup_order`;
CREATE TABLE `topup_order`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `money` int(0) NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT NULL,
  `user_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of topup_order
-- ----------------------------
INSERT INTO `topup_order` VALUES (1, 15, '2023-11-30 20:01:10', 2);
INSERT INTO `topup_order` VALUES (2, 30, '2023-11-22 20:01:24', 2);
INSERT INTO `topup_order` VALUES (3, 70, '2023-12-09 21:13:02', 1);
INSERT INTO `topup_order` VALUES (4, 70, '2023-12-09 21:15:57', 3);
INSERT INTO `topup_order` VALUES (5, 0, '2023-12-09 21:20:46', 3);
INSERT INTO `topup_order` VALUES (6, 0, '2023-12-09 21:21:31', 3);
INSERT INTO `topup_order` VALUES (7, 10, '2023-12-09 21:22:46', 3);
INSERT INTO `topup_order` VALUES (8, 20, '2023-12-18 14:36:07', 1);
INSERT INTO `topup_order` VALUES (9, 20, '2023-12-18 14:42:27', 1);
INSERT INTO `topup_order` VALUES (10, 0, '2023-12-27 20:15:42', 1);
INSERT INTO `topup_order` VALUES (11, 15, '2024-01-05 21:52:03', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` int(0) NULL DEFAULT NULL,
  `age` int(0) NULL DEFAULT NULL,
  `avator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `dept` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` tinyint(0) NULL DEFAULT NULL,
  `money` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '123456', 'jisoo', '3270239@qq.com', '19047918657', 0, 23, 'https://web-framework-dngb.oss-cn-beijing.aliyuncs.com/d411830c-dad4-40a3-a3a3-323177ad7888.jpg', '超级管理员', 1, 12);
INSERT INTO `user` VALUES (2, '123456', 'jennie', '2934956283@qq.com', '15979316431', 1, 28, 'https://web-framework-dngb.oss-cn-beijing.aliyuncs.com/fdb1c278-8ca8-4294-acd1-8f0ada50ca26.jpg', '管理员', 1, 40);
INSERT INTO `user` VALUES (3, '123456', '朴彩英', '2934956283@qq.com', '19047918657', 0, 26, 'https://web-framework-dngb.oss-cn-beijing.aliyuncs.com/6ee8e9d9-3a57-40d8-b892-3f096296d297.jpg', '用户', 1, 0);
INSERT INTO `user` VALUES (4, '123456', 'lisa', '2934956283@qq.com', '18162139827', 0, 22, 'https://web-framework-dngb.oss-cn-beijing.aliyuncs.com/35ae504c-e9ea-49eb-8b9d-aca4da8b9a2e.jpg', '用户', 1, 0);

SET FOREIGN_KEY_CHECKS = 1;
