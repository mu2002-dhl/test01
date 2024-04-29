/*
 Navicat Premium Data Transfer

 Source Server         : locahost
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : java-vue

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 05/03/2024 21:47:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `origin_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '原始文件名称',
  `extend` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件后缀名',
  `size` bigint NULL DEFAULT NULL COMMENT '文件文件大小',
  `module` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件所属模块',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `modifier` bigint NULL DEFAULT NULL COMMENT '修改者',
  `user_select` int NULL DEFAULT NULL COMMENT '用户是否选中',
  `obj_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_file
-- ----------------------------

-- ----------------------------
-- Table structure for ums_dept
-- ----------------------------
DROP TABLE IF EXISTS `ums_dept`;
CREATE TABLE `ums_dept`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dept_num` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部门编号',
  `dept_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部门名称',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '父部门id',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部门描述',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `modifier` bigint NULL DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_dept_name_id`(`dept_name` ASC, `id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '部门' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_dept
-- ----------------------------

-- ----------------------------
-- Table structure for ums_dict
-- ----------------------------
DROP TABLE IF EXISTS `ums_dict`;
CREATE TABLE `ums_dict`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `dict_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典名称',
  `dict_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典编号',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `modifier` bigint NULL DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unionIndex_dict_code`(`dict_code` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '数据字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_dict
-- ----------------------------

-- ----------------------------
-- Table structure for ums_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `ums_dict_item`;
CREATE TABLE `ums_dict_item`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `dict_id` int NOT NULL COMMENT '字典Id',
  `item_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典项名称',
  `item_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典项内容',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `modifier` bigint NULL DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '字典项' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_dict_item
-- ----------------------------

-- ----------------------------
-- Table structure for ums_permission
-- ----------------------------
DROP TABLE IF EXISTS `ums_permission`;
CREATE TABLE `ums_permission`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限名称',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限标识',
  `url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '前端访问地址',
  `type` tinyint NULL DEFAULT NULL COMMENT '权限类型：0-菜单，1-按钮，2-接口',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '上级权限',
  `enable` bit(1) NULL DEFAULT b'1' COMMENT '是否可用：0-不可用，1-可用',
  `icon` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '前端组件名称',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `modifier` bigint NULL DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_permission
-- ----------------------------
INSERT INTO `ums_permission` VALUES (1, '系统管理', '', '', 0, 0, b'1', NULL, NULL, 0, '2023-12-10 19:03:33', NULL, '2023-12-10 19:03:33', NULL);
INSERT INTO `ums_permission` VALUES (2, '部门管理', '', '/dept', 0, 1, b'1', NULL, 'Dept', 0, '2023-12-03 11:12:10', NULL, '2024-01-26 22:16:09', 1);
INSERT INTO `ums_permission` VALUES (3, '权限管理', '', '/permission', 0, 1, b'1', NULL, 'Permission', 1, '2023-12-03 11:12:23', NULL, '2024-01-26 22:15:44', 1);
INSERT INTO `ums_permission` VALUES (4, '角色管理', '', '/role', 0, 1, b'1', NULL, 'Role', 2, '2023-12-09 10:28:39', NULL, '2024-01-26 22:15:52', 1);
INSERT INTO `ums_permission` VALUES (5, '用户管理', '', '/user', 0, 1, b'1', NULL, 'User', 3, '2023-12-09 13:04:52', NULL, '2024-01-26 22:16:25', 1);
INSERT INTO `ums_permission` VALUES (6, '字典管理', '', '/dict', 0, 1, b'1', NULL, 'UmsDict', 4, '2024-03-05 14:26:08', 1, '2024-03-05 14:26:08', 1);

-- ----------------------------
-- Table structure for ums_permission_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_permission_role`;
CREATE TABLE `ums_permission_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `permission_id` bigint NULL DEFAULT NULL COMMENT '权限id',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_permission_role
-- ----------------------------
INSERT INTO `ums_permission_role` VALUES (6, 1, 2);
INSERT INTO `ums_permission_role` VALUES (7, 2, 2);
INSERT INTO `ums_permission_role` VALUES (14, 1, 1);
INSERT INTO `ums_permission_role` VALUES (15, 2, 1);
INSERT INTO `ums_permission_role` VALUES (16, 3, 1);
INSERT INTO `ums_permission_role` VALUES (17, 4, 1);
INSERT INTO `ums_permission_role` VALUES (18, 5, 1);
INSERT INTO `ums_permission_role` VALUES (19, 6, 1);

-- ----------------------------
-- Table structure for ums_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_role`;
CREATE TABLE `ums_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `num` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色编号',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `modifier` bigint NULL DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_role_name`(`name` ASC) USING BTREE,
  UNIQUE INDEX `index_role_num`(`num` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_role
-- ----------------------------
INSERT INTO `ums_role` VALUES (1, 'role_admim', '管理员', '', '2024-01-26 21:22:10', 1, '2024-01-26 21:22:10', 1);
INSERT INTO `ums_role` VALUES (2, 'test_role', '测试角色', '', '2024-01-31 14:13:58', 1, '2024-01-31 14:13:58', 1);

-- ----------------------------
-- Table structure for ums_user
-- ----------------------------
DROP TABLE IF EXISTS `ums_user`;
CREATE TABLE `ums_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `login_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '登录名称',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户密码',
  `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机',
  `head_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像地址',
  `account_expired_time` datetime NULL DEFAULT NULL COMMENT '账号过期时间',
  `pwd_expired_time` datetime NULL DEFAULT NULL COMMENT '密码过期时间',
  `enable` tinyint(1) NULL DEFAULT NULL COMMENT '启用状态',
  `locked` tinyint(1) NULL DEFAULT NULL COMMENT '锁定状态',
  `dept_id` bigint NULL DEFAULT NULL COMMENT '所属部门',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_user_login_name`(`login_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_user
-- ----------------------------
INSERT INTO `ums_user` VALUES (1, 'admin', '$2a$10$5c6/Jrq3gM8qh0WfFkzPGuLf2DrUmfLbEzAZrzgeKGPkiQ5.oA7b.', 'admin1234', 'admin@qq.com', '15965698545', '2024-02-18//c328a2819c27458491a46ea5c190b97d.jpg', NULL, NULL, 1, 0, 520629937825120258);
INSERT INTO `ums_user` VALUES (2, 'test01', '$2a$10$vvWPtvh7dB0T.ibG1kUtyO14ZRzbnHcoFD14YQdospjTsckOVQavu', 'test01', '456985421@qq.com', '13659845234', '2024-01-31//c2c6836d83694e7784b6dfd0a9a1206d.jpg', '2024-04-30 14:14:35', '2024-04-30 14:14:35', 1, 0, NULL);
INSERT INTO `ums_user` VALUES (4, 'test02', '$2a$10$YmN0cB4n90RduecTpJN3YuvhU7mYQHXHh22iQBcvBaRQuFo14iMzy', 'test02', NULL, NULL, NULL, '2024-05-19 17:04:47', '2024-05-19 17:04:47', 1, 0, NULL);

-- ----------------------------
-- Table structure for ums_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_user_role`;
CREATE TABLE `ums_user_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户id',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_user_role
-- ----------------------------
INSERT INTO `ums_user_role` VALUES (1, 1, 1);
INSERT INTO `ums_user_role` VALUES (2, 2, 2);

SET FOREIGN_KEY_CHECKS = 1;
