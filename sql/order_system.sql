/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : order_system

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-10-31 11:56:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_area
-- ----------------------------
DROP TABLE IF EXISTS `t_area`;
CREATE TABLE `t_area` (
  `id` int(255) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL COMMENT '区域表'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_area
-- ----------------------------
INSERT INTO `t_area` VALUES ('1', '大厅');
INSERT INTO `t_area` VALUES ('2', '包厢');

-- ----------------------------
-- Table structure for t_cart
-- ----------------------------
DROP TABLE IF EXISTS `t_cart`;
CREATE TABLE `t_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车Id',
  `sid` int(11) DEFAULT NULL COMMENT '商家Id',
  `dishId` int(11) DEFAULT NULL COMMENT '菜品Id',
  `dishName` varchar(255) DEFAULT NULL COMMENT '菜名',
  `dishImage` varchar(255) DEFAULT NULL COMMENT '菜品图片',
  `dishPrice` decimal(10,2) DEFAULT NULL,
  `number` int(11) DEFAULT NULL COMMENT '数量',
  `tableId` int(100) DEFAULT NULL,
  `totalPrice` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_cart
-- ----------------------------

-- ----------------------------
-- Table structure for t_feedback
-- ----------------------------
DROP TABLE IF EXISTS `t_feedback`;
CREATE TABLE `t_feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shopId` int(11) DEFAULT NULL,
  `openId` varchar(100) DEFAULT NULL,
  `remark` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_feedback
-- ----------------------------

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单Id',
  `sid` int(11) DEFAULT NULL COMMENT '商家Id',
  `dishName` varchar(255) DEFAULT NULL COMMENT '菜名',
  `dishPrice` decimal(10,2) DEFAULT NULL COMMENT '菜价',
  `dishImage` varchar(255) DEFAULT NULL COMMENT '菜品图片',
  `dishType` int(11) DEFAULT NULL COMMENT '菜品类型Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '1', '姜撞奶', '23.00', '\r\nhttps://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E9%A4%90%E5%90%8E%E7%94%9C%E7%82%B9/%E5%A7%9C%E6%92%9E%E5%A5%B6.jpg', '7');
INSERT INTO `t_menu` VALUES ('2', '1', '栗子蛋糕', '33.00', '\r\nhttps://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E9%A4%90%E5%90%8E%E7%94%9C%E7%82%B9/%E6%A0%97%E5%AD%90%E8%9B%8B%E7%B3%95.jpg', '7');
INSERT INTO `t_menu` VALUES ('3', '1', '浓巧克力蛋糕', '28.00', '\r\nhttps://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E9%A4%90%E5%90%8E%E7%94%9C%E7%82%B9/%E6%B5%93%E5%B7%A7%E5%85%8B%E5%8A%9B%E8%9B%8B%E7%B3%95.jpg', '7');
INSERT INTO `t_menu` VALUES ('4', '1', '砂糖冰雪冷元子', '60.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E9%A4%90%E5%90%8E%E7%94%9C%E7%82%B9/%E7%A0%82%E7%B3%96%E5%86%B0%E9%9B%AA%E5%86%B7%E5%85%83%E5%AD%90.jpg', '7');
INSERT INTO `t_menu` VALUES ('5', '1', '双皮奶', '20.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E9%A4%90%E5%90%8E%E7%94%9C%E7%82%B9/%E5%8F%8C%E7%9A%AE%E5%A5%B6.jpg', '7');
INSERT INTO `t_menu` VALUES ('6', '1', '养颜西米露', '22.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E9%A4%90%E5%90%8E%E7%94%9C%E7%82%B9/%E5%85%BB%E9%A2%9C%E8%A5%BF%E7%B1%B3%E9%9C%B2.jpg', '7');
INSERT INTO `t_menu` VALUES ('7', '1', '拌土豆丝', '10.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%B2%BE%E7%BE%8E%E5%87%89%E8%8F%9C/%E6%8B%8C%E5%9C%9F%E8%B1%86%E4%B8%9D.jpg', '1');
INSERT INTO `t_menu` VALUES ('8', '1', '酱牛肉', '30.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%B2%BE%E7%BE%8E%E5%87%89%E8%8F%9C/%E9%85%B1%E7%89%9B%E8%82%89.jpg', '1');
INSERT INTO `t_menu` VALUES ('9', '1', '口水鸡', '32.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%B2%BE%E7%BE%8E%E5%87%89%E8%8F%9C/%E5%8F%A3%E6%B0%B4%E9%B8%A1.jpg', '1');
INSERT INTO `t_menu` VALUES ('10', '1', '凉拌木耳', '10.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%B2%BE%E7%BE%8E%E5%87%89%E8%8F%9C/%E5%87%89%E6%8B%8C%E6%9C%A8%E8%80%B3.jpg', '1');
INSERT INTO `t_menu` VALUES ('11', '1', '拍黄瓜', '8.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%B2%BE%E7%BE%8E%E5%87%89%E8%8F%9C/%E6%8B%8D%E9%BB%84%E7%93%9C.jpg', '1');
INSERT INTO `t_menu` VALUES ('12', '1', '可乐', '3.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E9%85%92%E6%B0%B4/%E5%8F%AF%E4%B9%90.jpg', '4');
INSERT INTO `t_menu` VALUES ('13', '1', '矿泉水', '2.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E9%85%92%E6%B0%B4/%E7%9F%BF%E6%B3%89%E6%B0%B4.jpg', '4');
INSERT INTO `t_menu` VALUES ('14', '1', '崂山啤酒', '2.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E9%85%92%E6%B0%B4/%E5%B4%82%E5%B1%B1%E5%95%A4%E9%85%92.jpg', '4');
INSERT INTO `t_menu` VALUES ('15', '1', '农夫山泉', '2.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E9%85%92%E6%B0%B4/%E5%86%9C%E5%A4%AB%E5%B1%B1%E6%B3%89.jpg', '4');
INSERT INTO `t_menu` VALUES ('16', '1', '雪碧', '3.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E9%85%92%E6%B0%B4/%E9%9B%AA%E7%A2%A7.jpg', '4');
INSERT INTO `t_menu` VALUES ('17', '1', '雪花啤酒', '5.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E9%85%92%E6%B0%B4/%E9%9B%AA%E8%8A%B1%E5%95%A4%E9%85%92.jpg', '4');
INSERT INTO `t_menu` VALUES ('18', '1', '潮州卤水猪肚', '38.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%83%A7%E8%85%8A%E5%8D%A4%E7%B1%BB/%E6%BD%AE%E5%B7%9E%E5%8D%A4%E6%B0%B4%E7%8C%AA%E8%82%9A.jpg', '3');
INSERT INTO `t_menu` VALUES ('19', '1', '卤水豆腐', '22.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%83%A7%E8%85%8A%E5%8D%A4%E7%B1%BB/%E5%8D%A4%E6%B0%B4%E8%B1%86%E8%85%90.jpg', '3');
INSERT INTO `t_menu` VALUES ('20', '1', '蜜汁烧鹅肝', '98.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%83%A7%E8%85%8A%E5%8D%A4%E7%B1%BB/%E8%9C%9C%E6%B1%81%E7%83%A7%E9%B9%85%E8%82%9D.jpg', '3');
INSERT INTO `t_menu` VALUES ('21', '1', '蜜汁烧鸡翼', '76.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%83%A7%E8%85%8A%E5%8D%A4%E7%B1%BB/%E8%9C%9C%E6%B1%81%E7%83%A7%E9%B8%A1%E7%BF%BC.jpg', '3');
INSERT INTO `t_menu` VALUES ('22', '1', '炭烧猪脚', '86.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%83%A7%E8%85%8A%E5%8D%A4%E7%B1%BB/%E7%82%AD%E7%83%A7%E7%8C%AA%E8%84%9A.jpg', '3');
INSERT INTO `t_menu` VALUES ('23', '1', '钵钵鸡', '34.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E5%9B%9B%E5%B7%9D%E8%8F%9C%E7%B3%BB/%E9%92%B5%E9%92%B5%E9%B8%A1.jpg', '3');
INSERT INTO `t_menu` VALUES ('24', '1', '宫保鸡丁', '29.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E5%9B%9B%E5%B7%9D%E8%8F%9C%E7%B3%BB/%E5%AE%AB%E4%BF%9D%E9%B8%A1%E4%B8%81.jpg', '2');
INSERT INTO `t_menu` VALUES ('25', '1', '回锅肉', '38.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E5%9B%9B%E5%B7%9D%E8%8F%9C%E7%B3%BB/%E5%9B%9E%E9%94%85%E8%82%89.jpg', '2');
INSERT INTO `t_menu` VALUES ('26', '1', '麻婆豆腐', '28.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E5%9B%9B%E5%B7%9D%E8%8F%9C%E7%B3%BB/%E9%BA%BB%E5%A9%86%E8%B1%86%E8%85%90.jpg', '2');
INSERT INTO `t_menu` VALUES ('27', '1', '水煮牛肉', '45.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E5%9B%9B%E5%B7%9D%E8%8F%9C%E7%B3%BB/%E6%B0%B4%E7%85%AE%E7%89%9B%E8%82%89.jpg', '2');
INSERT INTO `t_menu` VALUES ('28', '1', '冬阴功汤', '31.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E6%B1%A4%E7%B1%BB/%E5%86%AC%E9%98%B4%E5%8A%9F%E6%B1%A4.jpg', '6');
INSERT INTO `t_menu` VALUES ('29', '1', '番茄疙瘩汤', '18.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E6%B1%A4%E7%B1%BB/%E7%95%AA%E8%8C%84%E7%96%99%E7%98%A9%E6%B1%A4.jpg', '6');
INSERT INTO `t_menu` VALUES ('30', '1', '萝卜丝鲫鱼汤', '26.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E6%B1%A4%E7%B1%BB/%E8%90%9D%E5%8D%9C%E4%B8%9D%E9%B2%AB%E9%B1%BC%E6%B1%A4.jpg', '6');
INSERT INTO `t_menu` VALUES ('31', '1', '酸菜芋头汤', '27.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E6%B1%A4%E7%B1%BB/%E9%85%B8%E8%8F%9C%E8%8A%8B%E5%A4%B4%E6%B1%A4.jpg', '6');
INSERT INTO `t_menu` VALUES ('32', '1', '土豆白菜汤', '26.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E6%B1%A4%E7%B1%BB/%E5%9C%9F%E8%B1%86%E7%99%BD%E8%8F%9C%E6%B1%A4.jpg', '6');
INSERT INTO `t_menu` VALUES ('33', '1', '素炒饼', '10.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E4%B8%BB%E9%A3%9F/%E7%B4%A0%E7%82%92%E9%A5%BC.jpg', '5');
INSERT INTO `t_menu` VALUES ('34', '1', '扬州炒饭', '22.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E4%B8%BB%E9%A3%9F/%E6%89%AC%E5%B7%9E%E7%82%92%E9%A5%AD.jpg', '5');

-- ----------------------------
-- Table structure for t_menu_dishtype
-- ----------------------------
DROP TABLE IF EXISTS `t_menu_dishtype`;
CREATE TABLE `t_menu_dishtype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) DEFAULT NULL,
  `dishType` varchar(255) DEFAULT NULL COMMENT '菜品类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu_dishtype
-- ----------------------------
INSERT INTO `t_menu_dishtype` VALUES ('1', '1', '精美凉菜');
INSERT INTO `t_menu_dishtype` VALUES ('2', '1', '四川菜系');
INSERT INTO `t_menu_dishtype` VALUES ('3', '1', '烧腊卤菜');
INSERT INTO `t_menu_dishtype` VALUES ('4', '1', '酒水');
INSERT INTO `t_menu_dishtype` VALUES ('5', '1', '主食');
INSERT INTO `t_menu_dishtype` VALUES ('6', '1', '汤类');
INSERT INTO `t_menu_dishtype` VALUES ('7', '1', '餐后甜点');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单Id',
  `orderId` varchar(255) DEFAULT NULL,
  `dishId` int(11) DEFAULT NULL COMMENT '菜品Id',
  `sid` int(11) DEFAULT NULL,
  `tableId` int(11) DEFAULT NULL COMMENT '桌号',
  `openId` varchar(255) DEFAULT NULL,
  `dishName` varchar(255) DEFAULT NULL,
  `dishPrice` decimal(10,2) DEFAULT NULL,
  `dishImage` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `totalPrice` decimal(10,2) DEFAULT NULL,
  `status` int(11) DEFAULT '0' COMMENT '订单状态 0:未付款 1:已付款',
  `dishStatus` int(255) DEFAULT NULL COMMENT '菜品状态 0: 已出单 1:正在做 2:已上菜 3:已退菜',
  `orderTime` datetime DEFAULT NULL,
  `payTime` datetime DEFAULT NULL,
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注',
  `isDelete` int(11) unsigned zerofill DEFAULT NULL COMMENT '0: 未删除   1：已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('1', '201910301758360001', '11', '1', '2', null, '拍黄瓜', '8.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%B2%BE%E7%BE%8E%E5%87%89%E8%8F%9C/%E6%8B%8D%E9%BB%84%E7%93%9C.jpg', '1', '0.00', '0', '3', '2019-10-30 17:58:36', null, '', '00000000000');
INSERT INTO `t_order` VALUES ('2', '201910301758360001', '10', '1', '2', null, '凉拌木耳', '10.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%B2%BE%E7%BE%8E%E5%87%89%E8%8F%9C/%E5%87%89%E6%8B%8C%E6%9C%A8%E8%80%B3.jpg', '1', '0.00', '0', '3', '2019-10-30 17:58:36', null, '', '00000000000');
INSERT INTO `t_order` VALUES ('3', '201910301758360001', '9', '1', '2', null, '口水鸡', '32.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%B2%BE%E7%BE%8E%E5%87%89%E8%8F%9C/%E5%8F%A3%E6%B0%B4%E9%B8%A1.jpg', '1', '0.00', '0', '3', '2019-10-30 17:58:36', null, '', '00000000000');
INSERT INTO `t_order` VALUES ('4', '201910301758360001', '8', '1', '2', null, '酱牛肉', '30.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%B2%BE%E7%BE%8E%E5%87%89%E8%8F%9C/%E9%85%B1%E7%89%9B%E8%82%89.jpg', '1', '0.00', null, '2', null, null, null, '00000000000');
INSERT INTO `t_order` VALUES ('5', '201910301758360001', '9', '1', '2', null, '口水鸡', '32.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%B2%BE%E7%BE%8E%E5%87%89%E8%8F%9C/%E5%8F%A3%E6%B0%B4%E9%B8%A1.jpg', '1', '0.00', null, '3', null, null, null, '00000000000');
INSERT INTO `t_order` VALUES ('6', '201910301758360001', '16', '1', '2', null, '雪碧', '3.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E9%85%92%E6%B0%B4/%E9%9B%AA%E7%A2%A7.jpg', '1', '3.00', '0', '0', null, null, null, '00000000000');
INSERT INTO `t_order` VALUES ('7', '201910301842300001', '7', '1', '6', null, '拌土豆丝', '10.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%B2%BE%E7%BE%8E%E5%87%89%E8%8F%9C/%E6%8B%8C%E5%9C%9F%E8%B1%86%E4%B8%9D.jpg', '1', '10.00', '0', '0', '2019-10-30 18:42:30', null, '', '00000000000');
INSERT INTO `t_order` VALUES ('8', '201910301842300001', '8', '1', '6', null, '酱牛肉', '30.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%B2%BE%E7%BE%8E%E5%87%89%E8%8F%9C/%E9%85%B1%E7%89%9B%E8%82%89.jpg', '1', '30.00', '0', '0', '2019-10-30 18:42:30', null, '', '00000000000');
INSERT INTO `t_order` VALUES ('9', '201910301842300001', '9', '1', '6', null, '口水鸡', '32.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%B2%BE%E7%BE%8E%E5%87%89%E8%8F%9C/%E5%8F%A3%E6%B0%B4%E9%B8%A1.jpg', '1', '32.00', '0', '0', '2019-10-30 18:42:30', null, '', '00000000000');
INSERT INTO `t_order` VALUES ('10', '201910301842300001', '10', '1', '6', null, '凉拌木耳', '10.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%B2%BE%E7%BE%8E%E5%87%89%E8%8F%9C/%E5%87%89%E6%8B%8C%E6%9C%A8%E8%80%B3.jpg', '1', '10.00', '0', '0', '2019-10-30 18:42:30', null, '', '00000000000');
INSERT INTO `t_order` VALUES ('11', '201910301842300001', '11', '1', '6', null, '拍黄瓜', '8.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%B2%BE%E7%BE%8E%E5%87%89%E8%8F%9C/%E6%8B%8D%E9%BB%84%E7%93%9C.jpg', '1', '8.00', '0', '0', '2019-10-30 18:42:30', null, '', '00000000000');
INSERT INTO `t_order` VALUES ('12', '201910301842300001', '17', '1', '6', null, '雪花啤酒', '5.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E9%85%92%E6%B0%B4/%E9%9B%AA%E8%8A%B1%E5%95%A4%E9%85%92.jpg', '1', '5.00', '0', '0', null, null, null, '00000000000');
INSERT INTO `t_order` VALUES ('13', '201910301842300001', '16', '1', '6', null, '雪碧', '3.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E9%85%92%E6%B0%B4/%E9%9B%AA%E7%A2%A7.jpg', '1', '3.00', '0', '0', null, null, null, '00000000000');
INSERT INTO `t_order` VALUES ('14', '201910301843380001', '10', '1', '5', null, '凉拌木耳', '10.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%B2%BE%E7%BE%8E%E5%87%89%E8%8F%9C/%E5%87%89%E6%8B%8C%E6%9C%A8%E8%80%B3.jpg', '1', '10.00', '0', '2', '2019-10-30 18:43:38', null, '', '00000000000');
INSERT INTO `t_order` VALUES ('15', '201910301843380001', '9', '1', '5', null, '口水鸡', '32.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%B2%BE%E7%BE%8E%E5%87%89%E8%8F%9C/%E5%8F%A3%E6%B0%B4%E9%B8%A1.jpg', '2', '0.00', '0', '3', '2019-10-30 18:43:38', null, '', '00000000000');
INSERT INTO `t_order` VALUES ('16', '201910301843380001', '8', '1', '5', null, '酱牛肉', '30.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%B2%BE%E7%BE%8E%E5%87%89%E8%8F%9C/%E9%85%B1%E7%89%9B%E8%82%89.jpg', '2', '30.00', '0', '0', '2019-10-30 18:43:38', null, '', '00000000000');
INSERT INTO `t_order` VALUES ('17', '201910301843380001', '7', '1', '5', null, '拌土豆丝', '10.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%B2%BE%E7%BE%8E%E5%87%89%E8%8F%9C/%E6%8B%8C%E5%9C%9F%E8%B1%86%E4%B8%9D.jpg', '1', '10.00', '0', '0', '2019-10-30 18:43:38', null, '', '00000000000');
INSERT INTO `t_order` VALUES ('18', '201910301843380001', '24', '1', '5', null, '宫保鸡丁', '29.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E5%9B%9B%E5%B7%9D%E8%8F%9C%E7%B3%BB/%E5%AE%AB%E4%BF%9D%E9%B8%A1%E4%B8%81.jpg', '1', '29.00', '0', '0', '2019-10-30 18:43:38', null, '', '00000000000');
INSERT INTO `t_order` VALUES ('19', '201910301843380001', '12', '1', '5', null, '可乐', '3.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E9%85%92%E6%B0%B4/%E5%8F%AF%E4%B9%90.jpg', '1', '3.00', '0', '0', null, null, null, '00000000000');
INSERT INTO `t_order` VALUES ('20', '201910301843380001', '15', '1', '5', null, '农夫山泉', '2.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E9%85%92%E6%B0%B4/%E5%86%9C%E5%A4%AB%E5%B1%B1%E6%B3%89.jpg', '1', '2.00', '0', '0', null, null, null, '00000000000');
INSERT INTO `t_order` VALUES ('21', '201910301842300001', '7', '1', '6', null, '拌土豆丝', '10.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%B2%BE%E7%BE%8E%E5%87%89%E8%8F%9C/%E6%8B%8C%E5%9C%9F%E8%B1%86%E4%B8%9D.jpg', '1', '10.00', '0', '0', null, null, null, '00000000000');
INSERT INTO `t_order` VALUES ('22', '201910301842300001', '8', '1', '6', null, '酱牛肉', '30.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%B2%BE%E7%BE%8E%E5%87%89%E8%8F%9C/%E9%85%B1%E7%89%9B%E8%82%89.jpg', '1', '30.00', '0', '0', null, null, null, '00000000000');
INSERT INTO `t_order` VALUES ('23', '201910311125570001', '7', '1', '7', null, '拌土豆丝', '10.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%B2%BE%E7%BE%8E%E5%87%89%E8%8F%9C/%E6%8B%8C%E5%9C%9F%E8%B1%86%E4%B8%9D.jpg', '1', '10.00', '0', '2', '2019-10-31 11:25:58', null, '', '00000000000');
INSERT INTO `t_order` VALUES ('24', '201910311125570001', '8', '1', '7', null, '酱牛肉', '30.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%B2%BE%E7%BE%8E%E5%87%89%E8%8F%9C/%E9%85%B1%E7%89%9B%E8%82%89.jpg', '1', '0.00', '0', '3', '2019-10-31 11:25:58', null, '', '00000000000');
INSERT INTO `t_order` VALUES ('25', '201910311125570001', '9', '1', '7', null, '口水鸡', '32.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%B2%BE%E7%BE%8E%E5%87%89%E8%8F%9C/%E5%8F%A3%E6%B0%B4%E9%B8%A1.jpg', '1', '0.00', '0', '3', '2019-10-31 11:25:58', null, '', '00000000000');
INSERT INTO `t_order` VALUES ('26', '201910311125570001', '11', '1', '7', null, '拍黄瓜', '8.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%B2%BE%E7%BE%8E%E5%87%89%E8%8F%9C/%E6%8B%8D%E9%BB%84%E7%93%9C.jpg', '1', '0.00', '0', '3', '2019-10-31 11:25:58', null, '', '00000000000');
INSERT INTO `t_order` VALUES ('27', '201910311125570001', '24', '1', '7', null, '宫保鸡丁', '29.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E5%9B%9B%E5%B7%9D%E8%8F%9C%E7%B3%BB/%E5%AE%AB%E4%BF%9D%E9%B8%A1%E4%B8%81.jpg', '1', '29.00', '0', '2', '2019-10-31 11:25:58', null, '', '00000000000');
INSERT INTO `t_order` VALUES ('28', '201910311125570001', '25', '1', '7', null, '回锅肉', '38.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E5%9B%9B%E5%B7%9D%E8%8F%9C%E7%B3%BB/%E5%9B%9E%E9%94%85%E8%82%89.jpg', '1', '38.00', '0', '0', '2019-10-31 11:25:58', null, '', '00000000000');
INSERT INTO `t_order` VALUES ('29', '201910311151070001', '8', '1', '8', null, '酱牛肉', '30.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%B2%BE%E7%BE%8E%E5%87%89%E8%8F%9C/%E9%85%B1%E7%89%9B%E8%82%89.jpg', '2', '60.00', '0', '0', '2019-10-31 11:51:08', null, '', '00000000000');
INSERT INTO `t_order` VALUES ('30', '201910311151070001', '9', '1', '8', null, '口水鸡', '32.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%B2%BE%E7%BE%8E%E5%87%89%E8%8F%9C/%E5%8F%A3%E6%B0%B4%E9%B8%A1.jpg', '1', '32.00', '0', '0', '2019-10-31 11:51:08', null, '', '00000000000');
INSERT INTO `t_order` VALUES ('31', '201910311151070001', '24', '1', '8', null, '宫保鸡丁', '29.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E5%9B%9B%E5%B7%9D%E8%8F%9C%E7%B3%BB/%E5%AE%AB%E4%BF%9D%E9%B8%A1%E4%B8%81.jpg', '1', '29.00', '0', '0', '2019-10-31 11:51:08', null, '', '00000000000');
INSERT INTO `t_order` VALUES ('32', '201910311151070001', '7', '1', '8', null, '拌土豆丝', '10.00', 'https://menufile.oss-cn-beijing.aliyuncs.com/shanxiandajiudian/%E7%B2%BE%E7%BE%8E%E5%87%89%E8%8F%9C/%E6%8B%8C%E5%9C%9F%E8%B1%86%E4%B8%9D.jpg', '1', '10.00', '0', '0', null, null, null, '00000000000');

-- ----------------------------
-- Table structure for t_shop
-- ----------------------------
DROP TABLE IF EXISTS `t_shop`;
CREATE TABLE `t_shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商铺Id',
  `name` varchar(255) DEFAULT NULL COMMENT '店铺名字',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `address` varchar(255) DEFAULT NULL,
  `wifi` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `longitude` varchar(255) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(255) DEFAULT NULL COMMENT '纬度',
  `details` varchar(2000) DEFAULT NULL COMMENT '店铺展示图',
  `banner` varchar(255) DEFAULT NULL COMMENT '轮播图',
  `orgNo` varchar(255) DEFAULT NULL COMMENT '机构号',
  `mercId` varchar(255) DEFAULT NULL COMMENT '商家号',
  `trmNo` varchar(255) DEFAULT NULL COMMENT '设备号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_shop
-- ----------------------------
INSERT INTO `t_shop` VALUES ('1', '聪悦大饭店', null, '河北省石家庄市中华北大街中储广场B座', '1234567890', '0311-88818808', '114.48308', '38.074283', 'https://shopfiles.oss-cn-beijing.aliyuncs.com/details/1/1.jpg;https://shopfiles.oss-cn-beijing.aliyuncs.com/details/1/2.jpg;https://shopfiles.oss-cn-beijing.aliyuncs.com/details/1/3.jpg', 'https://shopfiles.oss-cn-beijing.aliyuncs.com/banner/1/1.jpg;https://shopfiles.oss-cn-beijing.aliyuncs.com/banner/1/2.jpg', null, null, null);

-- ----------------------------
-- Table structure for t_table
-- ----------------------------
DROP TABLE IF EXISTS `t_table`;
CREATE TABLE `t_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '餐桌Id',
  `sid` int(11) DEFAULT NULL COMMENT '店铺Id',
  `table_name` varchar(255) DEFAULT NULL COMMENT '餐桌名',
  `status` int(11) unsigned zerofill DEFAULT NULL COMMENT '餐桌状态 0:空闲  1: 已开桌  2:用餐  3:待清理',
  `floor` int(11) unsigned DEFAULT '1' COMMENT '楼层',
  `specification` int(11) DEFAULT NULL COMMENT '餐桌的规格',
  `area` int(11) DEFAULT NULL COMMENT '区域',
  `population` int(11) unsigned zerofill DEFAULT '00000000000' COMMENT '该桌的人数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_table
-- ----------------------------
INSERT INTO `t_table` VALUES ('1', '1', '001', '00000000002', '1', '1', '1', '00000000004');
INSERT INTO `t_table` VALUES ('2', '1', '002', '00000000002', '2', '2', '2', '00000000009');
INSERT INTO `t_table` VALUES ('4', '1', '004', '00000000000', '1', '1', '2', null);
INSERT INTO `t_table` VALUES ('5', '1', '005', '00000000002', '1', '1', '1', '00000000123');
INSERT INTO `t_table` VALUES ('6', '1', '006', '00000000002', '1', '1', '1', '00000000123');
INSERT INTO `t_table` VALUES ('7', '1', '007', '00000000002', '1', '1', '1', '00000000006');
INSERT INTO `t_table` VALUES ('8', '1', '008', '00000000002', '1', '1', '1', null);
INSERT INTO `t_table` VALUES ('9', '1', '009', '00000000000', '1', '1', '1', null);
INSERT INTO `t_table` VALUES ('10', '1', '010', '00000000000', '1', '1', '1', null);
INSERT INTO `t_table` VALUES ('11', '1', '011', '00000000001', '1', '1', '2', null);

-- ----------------------------
-- Table structure for t_table_area
-- ----------------------------
DROP TABLE IF EXISTS `t_table_area`;
CREATE TABLE `t_table_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `area` int(11) DEFAULT NULL COMMENT '区域ID',
  `floor` int(11) DEFAULT NULL,
  `shopId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_table_area
-- ----------------------------
INSERT INTO `t_table_area` VALUES ('1', '1', '1', '1');
INSERT INTO `t_table_area` VALUES ('2', '2', '1', '1');
INSERT INTO `t_table_area` VALUES ('4', '2', '2', '1');

-- ----------------------------
-- Table structure for t_table_floor
-- ----------------------------
DROP TABLE IF EXISTS `t_table_floor`;
CREATE TABLE `t_table_floor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shopId` int(11) DEFAULT NULL,
  `floor` varchar(255) DEFAULT NULL COMMENT '餐桌_楼层关联表',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_table_floor
-- ----------------------------
INSERT INTO `t_table_floor` VALUES ('1', '1', '1');
INSERT INTO `t_table_floor` VALUES ('2', '1', '2');
INSERT INTO `t_table_floor` VALUES ('3', '1', '3');

-- ----------------------------
-- Table structure for t_table_specification
-- ----------------------------
DROP TABLE IF EXISTS `t_table_specification`;
CREATE TABLE `t_table_specification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `specification` varchar(255) DEFAULT NULL,
  `shopId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_table_specification
-- ----------------------------
INSERT INTO `t_table_specification` VALUES ('1', '4人桌', '1');
INSERT INTO `t_table_specification` VALUES ('2', '6人桌', '1');
INSERT INTO `t_table_specification` VALUES ('3', '8人桌', '1');

-- ----------------------------
-- Table structure for t_table_status
-- ----------------------------
DROP TABLE IF EXISTS `t_table_status`;
CREATE TABLE `t_table_status` (
  `id` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_table_status
-- ----------------------------
INSERT INTO `t_table_status` VALUES ('0', '空闲');
INSERT INTO `t_table_status` VALUES ('1', '已开桌');
INSERT INTO `t_table_status` VALUES ('2', '用餐中');
INSERT INTO `t_table_status` VALUES ('3', '待清理');
