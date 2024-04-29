/*
 Navicat Premium Data Transfer

 Source Server         : locahost
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : dl24049

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 27/04/2024 19:23:21
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
-- Table structure for um_wxyy
-- ----------------------------
DROP TABLE IF EXISTS `um_wxyy`;
CREATE TABLE `um_wxyy`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `search` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '搜索内容',
  `reply` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '返回内容',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `modifier` bigint NULL DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文心一言搜索' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of um_wxyy
-- ----------------------------
INSERT INTO `um_wxyy` VALUES (2, '规划一条从武汉到常州的路线', '从武汉到常州的路线规划通常有以下几种方式：\n\n1. 飞机：\n   - 从武汉天河国际机场（WUH）出发，直飞至常州奔牛国际机场（CZX）。这是最快捷的方式，但通常也是最昂贵的。\n\n2. 高铁加地铁/公交：\n   - 从武汉乘坐高铁到南京南站，然后转乘地铁或公交前往常州。这是比较快速且舒适的方式，但需要转乘。\n   - 高铁路线：武汉 - 南京南\n   - 地铁/公交路线：南京南站 - 常州\n\n3. 长途汽车：\n   - 从武汉乘坐长途汽车直达常州。这种方式可能需要较长时间，但通常价格较为便宜。\n\n4. 自驾：\n   - 如果你选择自驾，可以使用导航软件（如高德地图、百度地图等）规划路线。请注意，自驾需要考虑路况、油费、过路费等因素。\n\n以下是高铁加地铁/公交的示例路线：\n\n- 武汉 - 南京南站的高铁车程大约需要3小时左右，具体时间表和票价可以在中国铁路官方网站或APP上查询。\n- 南京南站 - 常州可以通过地铁S1号线转地铁1号线，或者直接乘坐公交，大约需要1.5-2小时左右。\n\n请根据你的预算、时间和舒适度偏好选择合适的出行方式。由于交通情况可能会随季节、天气和节假日等因素变化，建议在出行前再次确认最新的交通信息。', 'line', '2024-04-26 15:39:13', 1, '2024-04-26 15:39:13', 1);
INSERT INTO `um_wxyy` VALUES (3, '武汉在哪里', '武汉是中国的湖北省省会，位于中国的中部地区，长江中游。武汉是一个历史悠久的城市，有着丰富的文化和历史遗迹，同时也是中国的重要交通枢纽和经济中心。武汉的地理位置十分重要，它连接着中国的南北和东西，是中国内陆地区的重要门户。武汉的面积约为8,569平方公里，人口超过1100万，是中国的特大城市之一。', 'line', '2024-04-26 15:44:23', 1, '2024-04-26 15:44:23', 1);
INSERT INTO `um_wxyy` VALUES (4, '规划一条从武汉到常州的路线', '从武汉到常州的路线规划通常有以下几种方式：\n\n1. 火车：\n   - 从武汉火车站乘坐高铁到常州北站。这条线路较为直接，耗时较短，但可能需要提前预订车票。\n   - 火车路线：武汉 - 常州\n\n2. 长途汽车：\n   - 从武汉乘坐长途汽车到常州。这种方式可能需要转车，耗时较长，但价格相对便宜。\n   - 汽车路线：武汉 - 常州\n\n3. 飞机：\n   - 从武汉天河国际机场乘坐飞机到常州奔牛国际机场。这种方式速度快，但需要考虑机票价格和机场到目的地的交通。\n   - 飞机路线：武汉 - 常州\n\n4. 自驾车：\n   - 如果你选择自驾，可以通过高速公路系统到达常州。具体路线可以参考导航软件，如百度地图、高德地图等。\n   - 自驾路线：武汉 - 常州\n\n无论选择哪种方式，建议提前规划行程，预订车票或机票，并考虑交通高峰期等因素。此外，由于疫情的影响，出行前请关注最新的疫情防控政策和措施，确保安全出行。', 'line', '2024-04-26 15:52:29', 1, '2024-04-26 15:52:29', 1);
INSERT INTO `um_wxyy` VALUES (5, '规划一条从武汉到常州的路线', '规划从武汉到常州的路线，可以选择多种交通方式，包括飞机、高铁、长途汽车或自驾车。以下是每种交通方式的大致规划：\n\n1. 飞机：\n   - 武汉天河国际机场（WUH）有直达常州奔牛国际机场（CZX）的航班。飞行时间大约为1.5小时。\n   - 到达奔牛机场后，可以选择乘坐机场大巴或出租车前往常州市区。\n\n2. 高铁加地铁：\n   - 从武汉乘坐高铁到南京南站，然后转乘地铁到南京站。\n   - 从南京站乘坐高铁到常州站。\n   - 高铁从武汉到南京的时间大约为3.5小时，从南京到常州的时间大约为1小时。\n   - 到达常州站后，可以乘坐公交或出租车前往目的地。\n\n3. 长途汽车：\n   - 从武汉乘坐长途汽车到常州。有多家长途汽车站有发往常州的班车，如武汉傅家坡长途汽车站。\n   - 车程大约为8-10小时，具体取决于路况和停靠站点。\n\n4. 自驾车：\n   - 从武汉出发，沿着沪蓉高速公路（G42）向南京方向行驶，然后转至沪宁高速公路（G4211）向常州方向行驶。\n   - 全程大约500公里，驾车时间大约为5-6小时，具体取决于路况和休息时间。\n\n请注意，由于交通状况和天气等因素的影响，实际旅行时间可能会有所变化。建议在出行前查看最新的交通信息，并预留足够的时间。此外，由于疫情等原因，部分交通服务可能会有所调整，建议出行前再次确认。', 'line', '2024-04-26 15:53:44', 1, '2024-04-26 15:53:44', 1);
INSERT INTO `um_wxyy` VALUES (6, '规划一条从北京到常州的路线', '从北京到常州的路线规划，最常见的方式是乘坐高铁。以下是高铁的路线规划：\n\n1. 从北京出发，可以选择北京南站或者北京站乘坐高铁。\n2. 乘坐高铁前往常州北站。北京到常州的高铁车次较多，车程大约需要5小时左右，具体时间取决于列车班次。\n3. 到达常州北站后，可以乘坐公交或者出租车前往常州市区。\n\n如果你选择自驾或者乘坐长途汽车，那么路线如下：\n\n1. 自驾：沿着京沪高速公路（G3）南下，经过天津、济南、徐州、南京等城市，最后到达常州。全程大约需要10-12小时，具体取决于路况和休息时间。\n2. 长途汽车：从北京的长途汽车站（如北京首都机场、北京站、北京西站等）乘坐前往常州的直达长途汽车，车程大约需要12-14小时，具体时间取决于班次和路况。\n\n无论选择哪种方式，建议提前预订车票或检查好出发时间，并了解最新的交通信息。此外，由于路程较长，如果选择自驾或者长途汽车，需要确保有足够的休息时间，以保证安全驾驶。', 'line', '2024-04-26 16:13:15', 1, '2024-04-26 16:13:15', 1);
INSERT INTO `um_wxyy` VALUES (9, '常州有哪些景点', '常州，位于中国江苏省南部，是一座历史悠久的城市，拥有丰富的旅游资源。以下是一些常州的知名景点：\n\n1. 中华恐龙园 - 这是一个以恐龙为主题的综合性游乐园，集科普、娱乐、休闲于一体，适合家庭和年轻人游玩。\n\n2. 淹城春秋乐园 - 以春秋时期的历史文化为背景，是一个集文化、旅游、娱乐为一体的主题公园，有仿古建筑、表演、游乐设施等。\n\n3. 天目湖旅游度假区 - 包括天目湖、天目湖御水温泉等，是一个集自然风光、休闲度假、温泉养生为一体的旅游度假区。\n\n4. 环球恐龙城 - 这是一个包含中华恐龙园在内的综合性旅游度假区，包括主题公园、酒店、商业街等。\n\n5. 红梅公园 - 位于常州市中心，是一个历史悠久的综合性公园，内有红梅阁、文笔塔等历史建筑，以及动物园、盆景园等。\n\n6. 篦箕巷 - 这是常州市区的一条古街，以其历史悠久的篦箕制作工艺而闻名，现在是一个集文化、旅游、购物为一体的街区。\n\n7. 茅山风景区 - 位于常州市郊，是一个以道教文化、自然风光为主的旅游景区，包括九峰、十九泉、二十六洞、二十八池等自然景观。\n\n8. 西太湖 - 又称滆湖，是一个大型淡水湖，湖光山色，自然风光优美，适合休闲观光。\n\n9. 常州市博物馆 - 展示了常州地区的历史文化，包括古文物、书画、工艺品等。\n\n10. 东坡公园 - 位于常州市区，是为了纪念宋代文学家苏东坡而建的公园，内有苏东坡的纪念建筑和碑刻。\n\n这些是常州的一些主要景点，常州还有其他许多值得游览的地方，如古运河、近园、白塔等。根据您的兴趣和旅行目的，可以选择合适的景点进行参观。', 'places', '2024-04-26 16:33:52', 1, '2024-04-26 16:33:52', 1);
INSERT INTO `um_wxyy` VALUES (10, '中华恐龙园 ', '中华恐龙园，又称常州恐龙园，位于中国江苏省常州市新北区，是一个以恐龙为主题的综合性旅游度假区。园区内不仅有各种恐龙雕塑和景观，还有多个游乐设施和表演项目，是一个适合家庭和儿童游玩的场所。中华恐龙园于2000年开园，是中国第一个恐龙主题公园，也是国家5A级旅游景区。', 'places', '2024-04-26 16:34:13', 1, '2024-04-26 16:34:13', 1);
INSERT INTO `um_wxyy` VALUES (11, '帮我规划一条从北京到常州的路线', '规划从北京到常州的路线，通常有以下几种方式：\n\n1. 飞机：\n   - 北京首都国际机场（PEK）或北京大兴国际机场（PKX）飞往常州奔牛国际机场（CZX）。\n   - 飞行时间大约需要2小时左右，具体取决于航班和机场。\n\n2. 高铁加地铁/公交：\n   - 从北京乘坐高铁到南京，再从南京乘坐高铁到常州。\n   - 北京到南京的高铁车程大约需要3小时，南京到常州的高铁车程大约需要1小时。\n   - 南京有地铁和公交系统，可以方便地到达南京南站。\n\n3. 长途汽车：\n   - 从北京乘坐长途汽车直达常州。\n   - 车程大约需要10-12小时，具体取决于路况和停靠站点。\n\n以下是北京到常州的高铁加地铁/公交的示例路线：\n\n- 从北京乘坐高铁到南京南站。\n- 在南京南站乘坐地铁S1号线（机场线）到南京禄口国际机场站。\n- 在禄口机场站，转乘S9号线到翔宇路南站。\n- 在翔宇路南站，转乘857路公交到溧水汽车客运站。\n- 在溧水汽车客运站，转乘长途汽车到常州。\n\n请注意，具体的列车时刻、票价和长途汽车班次可能会随时间变化，建议在出行前通过官方网站或客服热线查询最新的信息。此外，由于疫情或其他原因，部分交通服务可能会临时调整或取消，请提前确认。', 'line', '2024-04-26 21:26:26', 8, '2024-04-26 21:26:26', 8);
INSERT INTO `um_wxyy` VALUES (12, '帮我规划一条从上海到常州的路线', '从上海到常州的路线规划通常有以下几种方式：\n\n1. 自驾：\n如果你选择自驾，可以通过高速公路前往常州。以下是大概的路线：\n- 从上海出发，你可以选择沪宁高速（G42）或者沿江高速（S38）。\n- 沪宁高速：从上海市区上沪宁高速，一直往南京方向行驶，到常州出口下高速。\n- 沿江高速：从上海市区上沿江高速，往苏州方向行驶，然后在常州北出口下高速。\n- 两条路线的车程大约在1.5到2小时之间，具体取决于交通情况。\n\n2. 长途汽车：\n上海和常州之间有直达的长途汽车班次。你可以在上海的汽车站（如上海长途客运总站、上海虹桥长途汽车站等）乘坐前往常州的班车。车程大约在2小时左右。\n\n3. 火车：\n上海和常州之间有频繁的火车班次，包括高速铁路（高铁）和普通列车。你可以选择在上海火车站（上海站）或上海虹桥火车站乘坐火车前往常州。高铁车程大约在45分钟到1小时之间，而普通列车车程大约在2到3小时之间。\n\n以下是一些规划步骤：\n\n- 确定你的出发点和目的地（上海的哪个区域和常州的哪个区域）。\n- 选择你喜欢的出行方式（自驾、长途汽车或火车）。\n- 根据你的出发点和目的地，以及选择的出行方式，查找相应的路线和交通信息。\n- 考虑到交通堵塞、班次时间等因素，提前规划好出发时间和预计到达时间。\n\n建议你根据实际情况（如你的出发点、预算、时间限制等）和当时的交通状况，选择最适合自己的出行方式和路线。', 'line', '2024-04-26 21:27:15', 8, '2024-04-26 21:27:15', 8);
INSERT INTO `um_wxyy` VALUES (13, '常州有哪个好玩的景点', '常州，位于中国江苏省南部，是一座历史悠久的城市，拥有丰富的文化遗产和自然风光。以下是一些常州的知名景点：\n\n1. 中华恐龙园 - 这是一个以恐龙为主题的综合性游乐园，集科普、娱乐、休闲于一体，适合家庭和年轻人游玩。\n\n2. 淹城春秋乐园 - 以春秋时期的文化为背景，结合了现代科技，是一个集文化、娱乐、休闲为一体的主题公园。\n\n3. 天目湖旅游度假区 - 这里有美丽的自然风光，包括天目湖和周边的山区，适合休闲度假、观光旅游。\n\n4. 南山竹海 - 位于常州市溧阳市，是一个以竹林为主的自然风景区，环境幽静，空气清新，是避暑休闲的好去处。\n\n5. 西太湖 - 又称滆湖，是常州的另一个重要湖泊，湖光山色，景色宜人，适合钓鱼、游船等水上活动。\n\n6. 篦箕巷 - 这是常州的一条历史街巷，以制作和销售篦箕（一种梳子）而闻名，同时也是一个品尝当地小吃的好地方。\n\n7. 常州博物馆 - 展示了常州乃至江苏的悠久历史和丰富的文化，是一个了解当地历史的好去处。\n\n8. 红梅公园 - 位于常州市中心，是一个结合了自然景观和人文景观的综合性公园，适合散步、休闲。\n\n9. 东坡公园 - 位于常州市西郊，是为了纪念宋代文学家苏东坡而建，公园内有苏东坡的塑像和一些相关历史遗迹。\n\n10. 近园 - 又称近水园，是常州市内的一座古典园林，具有典型的江南园林风格，环境优雅，适合摄影和欣赏园林艺术。\n\n这些只是常州众多景点中的一部分，常州还有许多其他的历史遗迹、自然风光和现代娱乐设施，等待着游客去探索和体验。', 'line', '2024-04-26 21:27:54', 8, '2024-04-26 21:27:54', 8);
INSERT INTO `um_wxyy` VALUES (15, '淹城春秋乐园', '淹城春秋乐园位于中国江苏省常州市武进区，是一个以春秋时期文化为主题的旅游景区。该乐园于2010年正式对外开放，占地面积约2000亩，由淹城遗址、春秋文化主题公园、野生动物世界等部分组成。\n\n淹城遗址是景区的核心，它是一座保存完好的古代城池，始建于春秋时期，距今已有2500多年的历史。城池分为内、中、外三城，呈“三城三河”的独特形制，是中国现存最古老、最完整的土城遗址之一。\n\n春秋文化主题公园围绕淹城遗址而建，包括了春秋淹城、诸子百家、淹城春秋乐园等几个主要区域。在这里，游客可以欣赏到春秋时期的建筑风格，体验古代的礼仪、音乐、舞蹈等文化活动，还可以参观各种展览，了解春秋时期的历史和文化。\n\n野生动物世界是淹城春秋乐园的另一个重要组成部分，这里生活着多种珍稀野生动物，游客可以乘坐观光车或步行观赏，与动物们亲密接触。\n\n淹城春秋乐园是一个集历史文化、旅游观光、休闲娱乐为一体的综合性旅游景区，适合各个年龄段的游客前来参观体验。', 'places', '2024-04-26 21:28:37', 8, '2024-04-26 21:28:37', 8);
INSERT INTO `um_wxyy` VALUES (16, '红梅公园', '红梅公园位于中国江苏省常州市中心，是一座以梅花为主题的综合性公园。公园内种植了大量的梅花，品种繁多，是赏梅的好去处。除了梅花，公园内还有其他各种花卉和植物，以及亭台楼阁、假山瀑布等园林景观。此外，公园内还有游船、儿童乐园等娱乐设施，是当地居民和游客休闲娱乐的好地方。红梅公园也是常州市的一张名片，每年吸引着大量游客前来观光。', 'places', '2024-04-26 21:28:56', 8, '2024-04-26 21:28:56', 8);

-- ----------------------------
-- Table structure for ums_activity
-- ----------------------------
DROP TABLE IF EXISTS `ums_activity`;
CREATE TABLE `ums_activity`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主办单位',
  `num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最大人数',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系方式',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '活动描述',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `modifier` bigint NULL DEFAULT NULL COMMENT '修改者',
  `place_id` int NULL DEFAULT NULL COMMENT '所属景点',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '景区活动' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_activity
-- ----------------------------
INSERT INTO `ums_activity` VALUES (1, '唱歌1111', '刹石狩', '23', '是', '是', '2024-04-25 17:12:49', 1, '2024-04-26 21:22:03', 1, 2);
INSERT INTO `ums_activity` VALUES (2, '3', '3', '3', '3', '3', '2024-04-25 22:14:49', 1, '2024-04-25 22:14:49', 1, 3);
INSERT INTO `ums_activity` VALUES (3, '123', '1111', '111', '11', '11', '2024-04-26 17:51:48', 1, '2024-04-26 17:51:48', 1, 4);

-- ----------------------------
-- Table structure for ums_activity_apply
-- ----------------------------
DROP TABLE IF EXISTS `ums_activity_apply`;
CREATE TABLE `ums_activity_apply`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `activity_id` int NOT NULL COMMENT '活动ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `modifier` bigint NULL DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '活动报名' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_activity_apply
-- ----------------------------
INSERT INTO `ums_activity_apply` VALUES (13, 1, '2024-04-26 21:10:47', 7, '2024-04-26 21:10:47', 7);
INSERT INTO `ums_activity_apply` VALUES (14, 2, '2024-04-26 21:10:47', 7, '2024-04-26 21:10:47', 7);
INSERT INTO `ums_activity_apply` VALUES (15, 3, '2024-04-26 21:11:01', 6, '2024-04-26 21:11:01', 6);
INSERT INTO `ums_activity_apply` VALUES (16, 2, '2024-04-26 21:11:02', 6, '2024-04-26 21:11:02', 6);
INSERT INTO `ums_activity_apply` VALUES (17, 1, '2024-04-26 21:25:46', 8, '2024-04-26 21:25:46', 8);
INSERT INTO `ums_activity_apply` VALUES (18, 2, '2024-04-26 21:25:46', 8, '2024-04-26 21:25:46', 8);
INSERT INTO `ums_activity_apply` VALUES (20, 3, '2024-04-26 21:25:49', 8, '2024-04-26 21:25:49', 8);

-- ----------------------------
-- Table structure for ums_comment
-- ----------------------------
DROP TABLE IF EXISTS `ums_comment`;
CREATE TABLE `ums_comment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `obj_id` int NOT NULL COMMENT '关联对象id',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评论对象',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `score` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评分',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `modifier` bigint NULL DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '景区/商店 评论' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_comment
-- ----------------------------
INSERT INTO `ums_comment` VALUES (1, 2, 'places', '11', NULL, '2024-04-25 20:56:46', 1, '2024-04-25 20:56:46', 1);
INSERT INTO `ums_comment` VALUES (2, 2, 'places', '还不错', NULL, '2024-04-25 21:05:40', 1, '2024-04-25 21:05:40', 1);
INSERT INTO `ums_comment` VALUES (3, 3, 'places', '333333', NULL, '2024-04-25 21:06:53', 1, '2024-04-25 21:06:53', 1);
INSERT INTO `ums_comment` VALUES (4, 2, 'store', '111', NULL, '2024-04-25 21:14:10', 1, '2024-04-25 21:14:10', 1);
INSERT INTO `ums_comment` VALUES (5, 2, 'store', '12222222', NULL, '2024-04-25 21:15:47', 1, '2024-04-25 21:15:47', 1);
INSERT INTO `ums_comment` VALUES (6, 4, 'places', 'hahahah', NULL, '2024-04-26 17:53:23', 7, '2024-04-26 17:53:23', 7);
INSERT INTO `ums_comment` VALUES (7, 4, 'places', '111111111', NULL, '2024-04-26 17:53:30', 7, '2024-04-26 17:53:30', 7);
INSERT INTO `ums_comment` VALUES (8, 3, 'store', 'rrrrr', NULL, '2024-04-26 17:53:48', 7, '2024-04-26 17:53:48', 7);
INSERT INTO `ums_comment` VALUES (9, 3, 'places', '111111111111111111', NULL, '2024-04-26 21:23:28', 8, '2024-04-26 21:23:28', 8);
INSERT INTO `ums_comment` VALUES (10, 3, 'store', '23', NULL, '2024-04-26 21:24:38', 8, '2024-04-26 21:24:38', 8);

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '数据字典' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_dict
-- ----------------------------
INSERT INTO `ums_dict` VALUES (1, '是否', 'dict_yes_no', '2024-04-25 15:31:47', 1, '2024-04-25 15:37:10', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '字典项' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_dict_item
-- ----------------------------
INSERT INTO `ums_dict_item` VALUES (1, 1, '否', '否', 0, '2024-04-25 15:32:02', 1, '2024-04-25 15:32:02', 1);
INSERT INTO `ums_dict_item` VALUES (2, 1, '是', '是', 1, '2024-04-25 15:32:09', 1, '2024-04-25 15:32:09', 1);

-- ----------------------------
-- Table structure for ums_notice
-- ----------------------------
DROP TABLE IF EXISTS `ums_notice`;
CREATE TABLE `ums_notice`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
  `is_new` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '是否最新公告',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `modifier` bigint NULL DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '公告管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_notice
-- ----------------------------
INSERT INTO `ums_notice` VALUES (1, '神十八乘组出发带了啥？还有个小秘密要在太空揭晓', '<p>神舟十八号乘组的三名航天员将在中国空间站驻留约6个月，空间站是航天员在太空的家，这一次，三名航天员会携带哪些物品前往空间站呢？</p><p>　　这是2022年2月航天员叶光富在中国空间站演奏《月光下的凤尾竹》的“名场面”。时隔2年多，叶光富再次飞天，这次他将会带另外一种乐器前往空间站。</p><p><img src=\"https://k.sinaimg.cn/n/sinakd20240424s/200/w640h360/20240424/8870-ab52b67be592956a9cd078cf78e02208.jpg/w700d1q75cms.jpg?by=cms_fixed_width\" alt=\"\" width=\"640\" height=\"360\"></p><p>　　航天员 叶光富：一个小的乐器，可能不大，只有巴掌这么大，留点悬念。因为我到时候我也想利用一些业余时间，在比较闲暇的时候拿来一块练一练、学一学，在陶冶之余也算是自我放松一下，到时候会给你揭晓。个人物品方面，我可能带的比较简单一些，有家人的一些照片，还有家人和孩子们一起创作的画，还有用于解压的一些小玩偶。</p><p><img src=\"https://k.sinaimg.cn/n/sinakd20240424s/200/w640h360/20240424/602c-2b88b5ec42c670d2c3b66498bede608a.jpg/w700d1q75cms.jpg?by=cms_fixed_width\" alt=\"\" width=\"640\" height=\"360\"></p><p>　　首次上太空，航天员李聪和航天员李广苏的背囊里，装满了对家人的思念。</p><p>　　航天员 李聪：我带了一些家庭的照片，然后还有一个比较特别的，我妻子为我准备了两个小礼物。她只告诉我是用我两个女儿的胎毛做了一个手工制品，具体是什么样子，我没有还没看到过，所以说这只能到进入太空以后，然后把快递拆下来才能一睹真容。</p>', '是', '2024-04-25 15:50:59', 1, '2024-04-25 15:52:29', 1);
INSERT INTO `ums_notice` VALUES (2, '金价大幅暴跌，普通人买入黄金的良机到了？', '<p>文|谢九</p><p>　　疯狂上涨的黄金最近终于开始降温，4月22日，国际黄金价格暴跌3%，创下两年多来的单日最大跌幅。几天之内的累计跌幅已经超过5%，让最近追涨黄金的投资者快速套牢。高歌猛进的黄金为什么会突然暴跌呢？这是否意味着这一轮黄金牛市已经结束了？还是又一次买入黄金的良机？</p><p>　　黄金价格暴跌，首先是因为最近涨幅过快过大，技术面上已经有了强烈的回调要求。而更直接的导火索还在于，美联储最近突然降低了降息的预期，黄金失去了继续上涨的动力。</p><p><img src=\"https://k.sinaimg.cn/n/spider20240426/77/w1080h597/20240426/85b4-a5c4f64bb5b548c70df64b53b84f7104.jpg/w700d1q75cms.jpg?by=cms_fixed_width\" alt=\"\" width=\"700\" height=\"387\"></p><p>　　2023年，美国的通胀快速下降，全球都以为美联储很快就会从加息周期转向降息，从而推动了黄金的超级行情。但是今年美国的通胀降温之路并不顺利，前三个月的通胀都超出了市场预期，尤其是3月份的通胀更是创下过去半年来的新高。不只是通胀数据，美国的就业数据也是远超市场预期，3月份的新增就业人数比预期高出了50%。</p><p>　　美联储是否降息，最主要的两大依据就是通胀和就业，由于通胀和就业数据双双表现强势，这让美联储不得不推迟了降息的时间表。</p><p>　　美联储主席鲍威尔近日表示，除非美国经济数据格外疲软，否则，今年可能都不会降息。除了鲍维尔之外，还有美联储的高级官员甚至表示，美联储当前不仅不应该降息，甚至还应该继续加息。</p>', '是', '2024-04-26 17:52:23', 1, '2024-04-26 17:52:23', 1);
INSERT INTO `ums_notice` VALUES (3, '2024北京车展：全新福特Mustang敞篷运动版国内首秀', '<p>北京车展期间，<a href=\"https://db.auto.sina.com.cn/b10.html?c=spr_auto_trackid_1e17744b130ea936\">福特</a><a href=\"https://db.auto.sina.com.cn/657/?c=spr_auto_trackid_1e17744b130ea936\">Mustang</a><a href=\"https://db.auto.sina.com.cn/657/peizhi/?c=spr_auto_trackid_1e17744b130ea936\">(配置</a><a href=\"javascript: void(0)\">|询价)</a>敞篷版车型迎来中国首秀，这也是福特官方首次将Mustang敞篷版车型引入中国市场，新车定位敞篷跑车。据悉，全新Mustang将于今年6月正式在国内上市。</p><p><img src=\"https://n.sinaimg.cn/www/transform/355/w660h495/20240426/ef44-05c5b0fd94ef38d2a6183bc4e4d38431.jpg\" alt=\"2024北京车展：全新福特Mustang敞篷运动版国内首秀\" width=\"660\" height=\"495\"></p><p>　　外观上，全新福特Mustang敞篷运动版延续福特经典设计与肌肉感，前脸线条凌厉且富有攻击性，运动气息满满。进气格栅造型也与初代Mustang极其相似，尾部也依然是极具标志性的三柱尾灯。</p><p><img src=\"https://n.sinaimg.cn/www/transform/355/w660h495/20240426/8d06-3d6076ab40f1b571ebbddf6a8a4470e4.jpg\" alt=\"2024北京车展：全新福特Mustang敞篷运动版国内首秀\" width=\"660\" height=\"495\"></p><p>　　敞篷运动版车顶为全内衬式绝缘多层织物车顶，单把手式中央锁扣，操作简单，轻松实现一键开闭，最大支持在时速低于5公里/时完成开合。紧凑的车顶设计加上独立后悬挂系统，实现了充裕的后备厢空间表现，足以放下两套高尔夫球袋。</p>', '否', '2024-04-26 21:21:01', 1, '2024-04-26 21:21:01', 1);

-- ----------------------------
-- Table structure for ums_notice_copy1
-- ----------------------------
DROP TABLE IF EXISTS `ums_notice_copy1`;
CREATE TABLE `ums_notice_copy1`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
  `is_new` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '是否最新公告',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `modifier` bigint NULL DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '公告管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_notice_copy1
-- ----------------------------
INSERT INTO `ums_notice_copy1` VALUES (1, '神十八乘组出发带了啥？还有个小秘密要在太空揭晓', '<p>神舟十八号乘组的三名航天员将在中国空间站驻留约6个月，空间站是航天员在太空的家，这一次，三名航天员会携带哪些物品前往空间站呢？</p><p>　　这是2022年2月航天员叶光富在中国空间站演奏《月光下的凤尾竹》的“名场面”。时隔2年多，叶光富再次飞天，这次他将会带另外一种乐器前往空间站。</p><p><img src=\"https://k.sinaimg.cn/n/sinakd20240424s/200/w640h360/20240424/8870-ab52b67be592956a9cd078cf78e02208.jpg/w700d1q75cms.jpg?by=cms_fixed_width\" alt=\"\" width=\"640\" height=\"360\"></p><p>　　航天员 叶光富：一个小的乐器，可能不大，只有巴掌这么大，留点悬念。因为我到时候我也想利用一些业余时间，在比较闲暇的时候拿来一块练一练、学一学，在陶冶之余也算是自我放松一下，到时候会给你揭晓。个人物品方面，我可能带的比较简单一些，有家人的一些照片，还有家人和孩子们一起创作的画，还有用于解压的一些小玩偶。</p><p><img src=\"https://k.sinaimg.cn/n/sinakd20240424s/200/w640h360/20240424/602c-2b88b5ec42c670d2c3b66498bede608a.jpg/w700d1q75cms.jpg?by=cms_fixed_width\" alt=\"\" width=\"640\" height=\"360\"></p><p>　　首次上太空，航天员李聪和航天员李广苏的背囊里，装满了对家人的思念。</p><p>　　航天员 李聪：我带了一些家庭的照片，然后还有一个比较特别的，我妻子为我准备了两个小礼物。她只告诉我是用我两个女儿的胎毛做了一个手工制品，具体是什么样子，我没有还没看到过，所以说这只能到进入太空以后，然后把快递拆下来才能一睹真容。</p>', '是', '2024-04-25 15:50:59', 1, '2024-04-25 15:52:29', 1);
INSERT INTO `ums_notice_copy1` VALUES (2, '金价大幅暴跌，普通人买入黄金的良机到了？', '<p>文|谢九</p><p>　　疯狂上涨的黄金最近终于开始降温，4月22日，国际黄金价格暴跌3%，创下两年多来的单日最大跌幅。几天之内的累计跌幅已经超过5%，让最近追涨黄金的投资者快速套牢。高歌猛进的黄金为什么会突然暴跌呢？这是否意味着这一轮黄金牛市已经结束了？还是又一次买入黄金的良机？</p><p>　　黄金价格暴跌，首先是因为最近涨幅过快过大，技术面上已经有了强烈的回调要求。而更直接的导火索还在于，美联储最近突然降低了降息的预期，黄金失去了继续上涨的动力。</p><p><img src=\"https://k.sinaimg.cn/n/spider20240426/77/w1080h597/20240426/85b4-a5c4f64bb5b548c70df64b53b84f7104.jpg/w700d1q75cms.jpg?by=cms_fixed_width\" alt=\"\" width=\"700\" height=\"387\"></p><p>　　2023年，美国的通胀快速下降，全球都以为美联储很快就会从加息周期转向降息，从而推动了黄金的超级行情。但是今年美国的通胀降温之路并不顺利，前三个月的通胀都超出了市场预期，尤其是3月份的通胀更是创下过去半年来的新高。不只是通胀数据，美国的就业数据也是远超市场预期，3月份的新增就业人数比预期高出了50%。</p><p>　　美联储是否降息，最主要的两大依据就是通胀和就业，由于通胀和就业数据双双表现强势，这让美联储不得不推迟了降息的时间表。</p><p>　　美联储主席鲍威尔近日表示，除非美国经济数据格外疲软，否则，今年可能都不会降息。除了鲍维尔之外，还有美联储的高级官员甚至表示，美联储当前不仅不应该降息，甚至还应该继续加息。</p>', '是', '2024-04-26 17:52:23', 1, '2024-04-26 17:52:23', 1);
INSERT INTO `ums_notice_copy1` VALUES (3, '2024北京车展：全新福特Mustang敞篷运动版国内首秀', '<p>北京车展期间，<a href=\"https://db.auto.sina.com.cn/b10.html?c=spr_auto_trackid_1e17744b130ea936\">福特</a><a href=\"https://db.auto.sina.com.cn/657/?c=spr_auto_trackid_1e17744b130ea936\">Mustang</a><a href=\"https://db.auto.sina.com.cn/657/peizhi/?c=spr_auto_trackid_1e17744b130ea936\">(配置</a><a href=\"javascript: void(0)\">|询价)</a>敞篷版车型迎来中国首秀，这也是福特官方首次将Mustang敞篷版车型引入中国市场，新车定位敞篷跑车。据悉，全新Mustang将于今年6月正式在国内上市。</p><p><img src=\"https://n.sinaimg.cn/www/transform/355/w660h495/20240426/ef44-05c5b0fd94ef38d2a6183bc4e4d38431.jpg\" alt=\"2024北京车展：全新福特Mustang敞篷运动版国内首秀\" width=\"660\" height=\"495\"></p><p>　　外观上，全新福特Mustang敞篷运动版延续福特经典设计与肌肉感，前脸线条凌厉且富有攻击性，运动气息满满。进气格栅造型也与初代Mustang极其相似，尾部也依然是极具标志性的三柱尾灯。</p><p><img src=\"https://n.sinaimg.cn/www/transform/355/w660h495/20240426/8d06-3d6076ab40f1b571ebbddf6a8a4470e4.jpg\" alt=\"2024北京车展：全新福特Mustang敞篷运动版国内首秀\" width=\"660\" height=\"495\"></p><p>　　敞篷运动版车顶为全内衬式绝缘多层织物车顶，单把手式中央锁扣，操作简单，轻松实现一键开闭，最大支持在时速低于5公里/时完成开合。紧凑的车顶设计加上独立后悬挂系统，实现了充裕的后备厢空间表现，足以放下两套高尔夫球袋。</p>', '否', '2024-04-26 21:21:01', 1, '2024-04-26 21:21:01', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_permission
-- ----------------------------
INSERT INTO `ums_permission` VALUES (1, '系统管理', '', '', 0, 0, b'1', NULL, NULL, 0, '2023-12-10 19:03:33', NULL, '2023-12-10 19:03:33', NULL);
INSERT INTO `ums_permission` VALUES (2, '部门管理', '', '/dept', 0, 1, b'1', NULL, 'Dept', 0, '2023-12-03 11:12:10', NULL, '2024-01-26 22:16:09', 1);
INSERT INTO `ums_permission` VALUES (3, '权限管理', '', '/permission', 0, 1, b'1', NULL, 'Permission', 1, '2023-12-03 11:12:23', NULL, '2024-01-26 22:15:44', 1);
INSERT INTO `ums_permission` VALUES (4, '角色管理', '', '/role', 0, 1, b'1', NULL, 'Role', 2, '2023-12-09 10:28:39', NULL, '2024-01-26 22:15:52', 1);
INSERT INTO `ums_permission` VALUES (5, '用户管理', '', '/user', 0, 1, b'1', NULL, 'User', 3, '2023-12-09 13:04:52', NULL, '2024-01-26 22:16:25', 1);
INSERT INTO `ums_permission` VALUES (6, '字典管理', '', '/dict', 0, 1, b'1', NULL, 'UmsDict', 4, '2024-03-05 14:26:08', 1, '2024-03-05 14:26:08', 1);
INSERT INTO `ums_permission` VALUES (7, '公告管理', '', '/notice', 0, 1, b'1', NULL, 'UmsNotice', 5, '2024-04-25 15:42:59', 1, '2024-04-25 15:42:59', 1);
INSERT INTO `ums_permission` VALUES (8, '景点管理', '', '/places', 0, 0, b'1', NULL, 'UmsPlaces', 1, '2024-04-25 16:32:43', 1, '2024-04-25 16:32:43', 1);
INSERT INTO `ums_permission` VALUES (9, '商铺管理', '', '/store', 0, 0, b'1', NULL, 'UmsStore', 2, '2024-04-25 16:54:54', 1, '2024-04-25 16:54:54', 1);
INSERT INTO `ums_permission` VALUES (10, '活动管理', '', '/activity', 0, 0, b'1', NULL, 'UmsActivity', 4, '2024-04-25 17:05:36', 1, '2024-04-25 17:05:36', 1);
INSERT INTO `ums_permission` VALUES (11, '景点浏览', '', 'placeView', 0, 0, b'1', NULL, 'UmsPlacesView', 5, '2024-04-25 20:43:48', 1, '2024-04-26 17:07:36', 1);
INSERT INTO `ums_permission` VALUES (12, '商铺浏览', '', 'storeView', 0, 0, b'1', NULL, 'UmsStoreView', 5, '2024-04-25 21:08:06', 1, '2024-04-25 21:08:06', 1);
INSERT INTO `ums_permission` VALUES (13, '社交分享', '', '/share', 0, 0, b'1', NULL, 'UmsShare', 6, '2024-04-25 21:30:13', 1, '2024-04-25 21:48:14', 1);
INSERT INTO `ums_permission` VALUES (14, '旅游日记', '', '/travrl', 0, 0, b'1', NULL, 'UmsTravel', 7, '2024-04-25 21:47:56', 1, '2024-04-25 21:48:04', 1);
INSERT INTO `ums_permission` VALUES (15, '活动报名', '', '/umsActivityApply', 0, 0, b'1', NULL, 'UmsActivityApply', 8, '2024-04-25 21:55:43', 1, '2024-04-25 21:55:43', 1);
INSERT INTO `ums_permission` VALUES (16, '文心一言', '', '', 0, 0, b'1', NULL, NULL, 8, '2024-04-26 14:29:38', 1, '2024-04-26 14:29:38', 1);
INSERT INTO `ums_permission` VALUES (17, '个性化路线规划', '', '/wxyy', 0, 16, b'1', NULL, 'UmsWxyy', 0, '2024-04-26 14:29:57', 1, '2024-04-26 14:29:57', 1);
INSERT INTO `ums_permission` VALUES (18, '景点信息', '', '/wxyyPlaces', 0, 16, b'1', NULL, 'UmsWxyyPlaces', 1, '2024-04-26 16:28:05', 1, '2024-04-26 16:28:05', 1);
INSERT INTO `ums_permission` VALUES (19, '统计', '', '/statistics', 0, 0, b'1', NULL, 'UmsStatistics', 4, '2024-04-26 17:07:09', 1, '2024-04-26 17:08:02', 1);

-- ----------------------------
-- Table structure for ums_permission_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_permission_role`;
CREATE TABLE `ums_permission_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `permission_id` bigint NULL DEFAULT NULL COMMENT '权限id',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 245 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_permission_role
-- ----------------------------
INSERT INTO `ums_permission_role` VALUES (237, 11, 2);
INSERT INTO `ums_permission_role` VALUES (238, 12, 2);
INSERT INTO `ums_permission_role` VALUES (239, 13, 2);
INSERT INTO `ums_permission_role` VALUES (240, 14, 2);
INSERT INTO `ums_permission_role` VALUES (241, 15, 2);
INSERT INTO `ums_permission_role` VALUES (242, 16, 2);
INSERT INTO `ums_permission_role` VALUES (243, 17, 2);
INSERT INTO `ums_permission_role` VALUES (244, 18, 2);
INSERT INTO `ums_permission_role` VALUES (245, 1, 1);
INSERT INTO `ums_permission_role` VALUES (247, 5, 1);
INSERT INTO `ums_permission_role` VALUES (248, 7, 1);
INSERT INTO `ums_permission_role` VALUES (249, 8, 1);
INSERT INTO `ums_permission_role` VALUES (250, 9, 1);
INSERT INTO `ums_permission_role` VALUES (251, 10, 1);
INSERT INTO `ums_permission_role` VALUES (252, 19, 1);
INSERT INTO `ums_permission_role` VALUES (253, 16, 1);
INSERT INTO `ums_permission_role` VALUES (254, 17, 1);
INSERT INTO `ums_permission_role` VALUES (255, 18, 1);

-- ----------------------------
-- Table structure for ums_places
-- ----------------------------
DROP TABLE IF EXISTS `ums_places`;
CREATE TABLE `ums_places`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '地址',
  `price` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '门票价格',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系方式',
  `description` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `degree` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '等级',
  `search_count` int NULL DEFAULT NULL COMMENT '点击量',
  `key_word` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '关键词',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `modifier` bigint NULL DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '景点' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_places
-- ----------------------------
INSERT INTO `ums_places` VALUES (2, '故宫', '北京', '120', '13659865415', 'form', '国家AAAAA景区', 16, '历史，帝都， 长城', '2024-04-25 17:11:48', 1, '2024-04-26 17:53:07', 7);
INSERT INTO `ums_places` VALUES (3, '长城', '北京', '80', '13654569874', '长城', '', 8, '长城', '2024-04-25 17:12:10', 1, '2024-04-26 21:23:36', 8);
INSERT INTO `ums_places` VALUES (4, '测试景点1', '测试景点-地址', '80', '14234234234', '测试景点', '国家4A景区', 3, '测试景点', '2024-04-26 17:51:00', 1, '2024-04-26 17:53:30', 7);

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
INSERT INTO `ums_role` VALUES (2, 'role_tourist', '游客', '', '2024-01-31 14:13:58', 1, '2024-04-25 20:44:36', 1);

-- ----------------------------
-- Table structure for ums_share
-- ----------------------------
DROP TABLE IF EXISTS `ums_share`;
CREATE TABLE `ums_share`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标题',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '描述',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `modifier` bigint NULL DEFAULT NULL COMMENT '修改者',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片地址',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '分享' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_share
-- ----------------------------
INSERT INTO `ums_share` VALUES (1, '拜登签署涉TikTok法案，公司回应：将诉诸法庭，期待最终获胜', '<p>当地时间4月24日，美国总统拜登签署了价值950亿美元的一揽子对外援助法案，该法案还涉及强制字节跳动剥离旗下应用TikTok美国业务。在相关条款中，字节跳动被限期约九个月剥离其美国业务，否则将面临美国全国禁令。此外，如果拜登确定出售事宜取得进展，他还可行使一次90天的延期权力。</p><p>对此，TikTok方面誓言通过法律途径反击。该公司在拜登签署法案后表示，这项违宪法律就是对TikTok的禁令，公司将在法庭上提出质疑。“我们相信事实和法律显然站在我们这一边，我们最终会取得胜利。”</p><p>TikTok表示，公司已投入数十亿美元来保护美国数据的安全，并重申这项禁令将摧毁700万家企业，使1.7亿美国人噤声。“在继续挑战这一违宪禁令的同时，我们将继续投资和创新，以确保TikTok仍是一个各行各业的美国人都可以安全地分享他们的经历、寻找快乐和获得灵感的空间。”</p><p>TikTok首席执行官周受资也发布视频称，这是一个令人失望的时刻，并重申了该公司发起法律挑战的承诺。“请放心，我们不会去任何地方。事实和宪法都站在我们这一边，我们期待再次获胜。”</p><p><img src=\"https://n.sinaimg.cn/spider20240425/290/w700h390/20240425/faaa-3e0bd4022ae2b4a39827ce3efa929e64.png\" width=\"700\" height=\"390\"></p><p>此前，中国商务部曾就涉TikTok法案回应称，美方应切实尊重市场经济和公平竞争原则，停止无理打压别国企业，为各国企业在美投资经营提供开放、公平、公正和非歧视的环境。相关方应严格遵守中国法律法规，中方将采取一切必要措施，坚决维护自身合法权益。</p>', '2024-04-25 21:31:02', 1, '2024-04-25 21:45:55', 1, '', 'share');
INSERT INTO `ums_share` VALUES (2, '布林肯在上海对所谓中方“非市场经济行为”提出关切，汪文斌给出原则性回应', '<p>[环球时报-环球网报道 记者乌元春 索炎琦]4月24日下午，美国国务卿安东尼·布林肯抵达上海，开启任内的第二次访华行程。据中美双方此前发布的消息，应中共中央政治局委员、外交部长王毅邀请，布林肯于4月24日至26日访华，此行他将先后到访上海和北京，预计将会见商界人士，并与中方官员举行会谈。</p><p>　　在4月25日举行的中国外交部例行记者会上，彭博社记者提问称：美国国务卿布林肯在上海会见了上海市委书记陈吉宁，在会上他提出了美方对于中方“不公平的贸易行为”的关切，提出美国企业需要一个“公平的竞争环境”，并且对于中方“非市场经济行为”提出了关切。你对此有何回应？</p><p>　　对此，外交部发言人汪文斌表示，首先我要告诉大家的是，这两天我们已就美国国务卿布林肯访华做了背景吹风，介绍了中方在相关问题上的原则立场。</p><p>　　“关于布林肯国务卿访华的消息，中方也会及时的发布，大家可以关注”，汪文斌称，针对你提到的问题，我想做一个原则性的回应：中方一贯按照市场原则开展经贸合作，始终坚定支持多边贸易体制，全面履行世贸组织规则。我们希望美方也切实尊重公平竞争原则，遵守世贸组织规则，同中方相向而行，为推动中美经贸关系健康稳定发展创造有利条件。</p>', '2024-04-25 21:50:21', 1, '2024-04-25 21:50:21', 1, '', 'travel');
INSERT INTO `ums_share` VALUES (3, 'rrrr', '<p>在今天开幕的2024北京车展上，<a href=\"https://db.auto.sina.com.cn/b329.html?c=spr_auto_trackid_1e17744b130ea936\">智己</a>汽车举办了一场别开生面的媒体沟通会，联席CEO刘涛、软件及用户多元触点执行总监李微萌，以及品牌代言人金晨悉数出席，就智己汽车的最新技术、市场定位及未来展望进行了深入交流。</p><p><img src=\"https://n.sinaimg.cn/auto/transform/300/w660h440/20240426/996c-2db9928cdce69e0b8a9676a488d8fd08.jpg\" alt=\"智己汽车媒体群访：推动电动车迈向智慧生物体\" width=\"660\" height=\"440\"></p><p>　　刘涛在会上强调，智己汽车正引领智能电动汽车进入“智慧生物体”时代，不再局限于智能座舱的单一升级，而是致力于实现底盘数字化、整车数字化，将智舱与智驾彻底融合。智己汽车的“灵蜥数字底盘”，作为行业首例，如同为汽车植入了“超级小脑”，通过整合汽车的六个自由度，确保在各种路况下的驾驶都能达到前所未有的灵活、舒适和安全水平，为用户带来跨代际的驾驶体验。</p><p>　　智己汽车不仅在底盘技术上取得突破，还携手高通公司，打造了国内首个Q+L深度定制基线，充分利用8295芯片的潜能，为用户打造了软硬件完美融合的智能座舱体验。刘涛表示，</p>', '2024-04-26 17:54:08', 7, '2024-04-26 17:54:08', 7, '', 'share');
INSERT INTO `ums_share` VALUES (4, '1213', '<p>在今天开幕的2024北京车展上，<a href=\"https://db.auto.sina.com.cn/b329.html?c=spr_auto_trackid_1e17744b130ea936\">智己</a>汽车举办了一场别开生面的媒体沟通会，联席CEO刘涛、软件及用户多元触点执行总监李微萌，以及品牌代言人金晨悉数出席，就智己汽车的最新技术、市场定位及未来展望进行了深入交流。</p><p><img src=\"https://n.sinaimg.cn/auto/transform/300/w660h440/20240426/996c-2db9928cdce69e0b8a9676a488d8fd08.jpg\" alt=\"智己汽车媒体群访：推动电动车迈向智慧生物体\" width=\"660\" height=\"440\"></p><p>　　刘涛在会上强调，智己汽车正引领智能电动汽车进入“智慧生物体”时代，不再局限于智能座舱的单一升级，而是致力于实现底盘数字化、整车数字化，将智舱与智驾彻底融合。智己汽车的“灵蜥数字底盘”，作为行业首例，如同为汽车植入了“超级小脑”，通过整合汽车的六个自由度，确保在各种路况下的驾驶都能达到前所未有的灵活、舒适和安全水平，为用户带来跨代际的驾驶体验。</p><p>　　智己汽车不仅在底盘技术上取得突破，还携手高通公司，打造了国内首个Q+L深度定制基线，充分利用8295芯片的潜能，为用户打造了软硬件完美融合的智能座舱体验。刘涛表示，</p>', '2024-04-26 17:54:17', 7, '2024-04-26 17:54:17', 7, '', 'travel');
INSERT INTO `ums_share` VALUES (5, '微软及亚马逊的AI交易面临英国竞争监管机构审查', '<p>北京时间24日晚，英国竞争监管机构周三表示，他们将仔细审查<a href=\"https://stock.finance.sina.com.cn/usstock/quotes/MSFT.html\">微软</a>(399.04, 0.00, 0.00%)和<a href=\"https://stock.finance.sina.com.cn/usstock/quotes/AMZN.html\">亚马逊</a>(173.67, 0.00, 0.00%)最近的人工智能（AI）交易，因为他们担心这种交易可能会阻碍人工智能行业的竞争。</p><p><img src=\"https://n.sinaimg.cn/tech/transform/116/w550h366/20240424/bcc8-11c7a29a15ccf668141b176ed271c3b3.webp\" alt=\"Mistral AI联合创始人兼首席执行官Arthur Mensch\" width=\"550\" height=\"366\">Mistral AI联合创始人兼首席执行官Arthur Mensch</p><p>　　英国竞争与市场管理局（Competition and Markets Authority，CMA）表示，正在调查微软与法国Mistral AI的合作，以及该公司从另一家初创公司Inflection AI招聘关键员工的情况。</p><p>　　该监管机构还单独宣布，它正在调查亚马逊对总部位于旧金山的Anthropic公司的40亿美元投资。</p><p>　　随着公众和企业对生成式人工智能技术的兴趣日益浓厚，大型科技公司一直在向这个行业的初创公司投资，但这些投资也引起了反垄断机构的关注。</p><p>　　英国监管机构表示，在决定是否进行深入的反垄断调查之前，它正在征求“有利益关系的第三方”的意见。</p><p>　　CMA负责企业并购审查的执行总监Joel Bamford在一份声明中表示：“我们将客观公正地评估这三笔交易是否符合英国的合并规则，如果符合，它们是否对英国的竞争有任何影响。”</p>', '2024-04-26 21:25:02', 8, '2024-04-26 21:25:02', 8, '', 'share');
INSERT INTO `ums_share` VALUES (6, '周琦为肘击道歉：我是下意识反应 不是故意的', '<p>　北京时间4月24日消息，CBA联盟官方今晚公布罚单，广东队球员周琦因肘击广厦球员李金效面部被停赛两场，罚款10万。</p><p>　　据广厦队人员透露，李金效被肘击后出现了呕吐的脑震荡症状。</p><p><img src=\"https://k.sinaimg.cn/n/sports/transform/133/w636h297/20240424/6ed2-badc42342f2b2ae8180739c3c4646d3d.png/w700d1q75cms.jpg?by=cms_fixed_width\" alt=\"\" width=\"636\" height=\"297\"></p>', '2024-04-26 21:25:35', 8, '2024-04-26 21:25:35', 8, '', 'travel');

-- ----------------------------
-- Table structure for ums_store
-- ----------------------------
DROP TABLE IF EXISTS `ums_store`;
CREATE TABLE `ums_store`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '地址',
  `scope` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '经营范围',
  `charge` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '法人',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系方式',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `modifier` bigint NULL DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商铺' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_store
-- ----------------------------
INSERT INTO `ums_store` VALUES (2, '111', '111', '11', '11', '11', '2024-04-25 21:13:53', 1, '2024-04-25 21:13:53', 1);
INSERT INTO `ums_store` VALUES (3, '商铺', '商铺11', '商铺11', '商铺11', '商铺11', '2024-04-26 17:51:23', 1, '2024-04-26 17:51:32', 1);

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
  `age` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_user_login_name`(`login_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_user
-- ----------------------------
INSERT INTO `ums_user` VALUES (1, 'admin', '$2a$10$5c6/Jrq3gM8qh0WfFkzPGuLf2DrUmfLbEzAZrzgeKGPkiQ5.oA7b.', 'admin1234', 'admin@qq.com', '15965698545', '2024-04-26//67f58bd4d12743b39c9d173ebc7901e5.png', NULL, NULL, 1, 0, 520629937825120258, '289');
INSERT INTO `ums_user` VALUES (2, 'test01', '$2a$10$vvWPtvh7dB0T.ibG1kUtyO14ZRzbnHcoFD14YQdospjTsckOVQavu', 'test01', '456985421@qq.com', '13659845234', '2024-01-31//c2c6836d83694e7784b6dfd0a9a1206d.jpg', '2024-04-30 14:14:35', '2024-04-30 14:14:35', 1, 0, NULL, '281');
INSERT INTO `ums_user` VALUES (4, 'test02', '$2a$10$YmN0cB4n90RduecTpJN3YuvhU7mYQHXHh22iQBcvBaRQuFo14iMzy', 'test02', NULL, NULL, NULL, '2024-05-19 17:04:47', '2024-05-19 17:04:47', 1, 0, NULL, NULL);
INSERT INTO `ums_user` VALUES (5, 'user01', '$2a$10$mb3jeanbLuyJ6GWk5gRhq.BqQ.6E8Ad.Ol2Qzto1beZHiharn9182', 'user01', NULL, NULL, NULL, '2024-07-26 16:43:46', '2024-07-26 16:43:46', 1, 0, NULL, '25');
INSERT INTO `ums_user` VALUES (6, 'user02', '$2a$10$Vbfm7RaK3oipdKkBar8e3etfaiQcuoILfUGOsX.dtcJAqdXtNVi3y', 'user02', NULL, NULL, NULL, '2024-07-26 16:45:03', '2024-07-26 16:45:03', 1, 0, NULL, '12');
INSERT INTO `ums_user` VALUES (7, 'user03', '$2a$10$tRYvSXqkEvcH0k.PY30zN.61JLF3Ad0IhLjQ7xdZgKKkxbG1K0JRa', 'user03', 'user03@qq.com', '13698564521', NULL, '2024-07-26 17:49:45', '2024-07-26 17:49:45', 1, 0, NULL, '25');
INSERT INTO `ums_user` VALUES (8, 'user04', '$2a$10$TIttGHTbJIL9x1asXukOcOVEG91Gle2qzQ.mRyvJSBTIsTOfU5a7a', 'user04', 'user04@qq.ocm', '13654898745', '2024-04-26//82f55ab9759b422b99468f9e1680fb61.jpg', '2024-07-26 21:22:56', '2024-07-26 21:22:56', 1, 0, NULL, '23');

-- ----------------------------
-- Table structure for ums_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_user_role`;
CREATE TABLE `ums_user_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户id',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_user_role
-- ----------------------------
INSERT INTO `ums_user_role` VALUES (12, 1, 1);
INSERT INTO `ums_user_role` VALUES (13, 2, 2);
INSERT INTO `ums_user_role` VALUES (14, 5, 2);
INSERT INTO `ums_user_role` VALUES (15, 6, 2);
INSERT INTO `ums_user_role` VALUES (16, 7, 2);
INSERT INTO `ums_user_role` VALUES (17, 8, 2);

SET FOREIGN_KEY_CHECKS = 1;
