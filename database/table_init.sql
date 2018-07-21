/*
Navicat MySQL Data Transfer

Source Server         : project
Source Server Version : 50639
Source Host           : localhost:3306
Source Database       : farmer

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2018-07-21 14:45:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `Id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `Adid` varchar(20) DEFAULT NULL COMMENT '根据openId生成注册用户id',
  `Account` varchar(20) DEFAULT NULL COMMENT '账户',
  `Password` varchar(100) DEFAULT NULL COMMENT '密码',
  `Name` varchar(100) DEFAULT NULL COMMENT '用户真实姓名',
  `Sex` int(10) DEFAULT NULL COMMENT '性别',
  `Phone` int(20) DEFAULT NULL COMMENT '电话',
  `Address` varchar(50) DEFAULT NULL COMMENT '地址',
  `Timestamp` varchar(20) DEFAULT NULL COMMENT '时间戳',
  `Remarks` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for card
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `CardId` int(10) DEFAULT NULL COMMENT '卡券ID',
  `Name` varchar(50) DEFAULT NULL COMMENT '卡券名称',
  `Price` int(10) DEFAULT NULL COMMENT '卡券价格',
  `Introduc` text COMMENT '产品介绍',
  `Img` varchar(100) DEFAULT NULL COMMENT '产品图片',
  `Timestrap` int(10) DEFAULT NULL COMMENT '创建时间',
  `Type` tinyint(4) DEFAULT NULL COMMENT '卡券种类',
  `Inventory` int(10) DEFAULT NULL COMMENT '剩余库存',
  `Soldnum` int(10) DEFAULT NULL COMMENT '卖出数量',
  `CardState` int(10) DEFAULT NULL COMMENT '卡券状态：可用，过期',
  `Remarks` varchar(100) DEFAULT NULL COMMENT '备注',
  `StartDate` datetime DEFAULT NULL COMMENT '卡券开始时间',
  `EndDate` datetime DEFAULT NULL COMMENT '卡券结束时间',
  `TimeRemarks` varchar(50) DEFAULT NULL COMMENT '时间备注',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for orderdetail
-- ----------------------------
DROP TABLE IF EXISTS `orderdetail`;
CREATE TABLE `orderdetail` (
  `Id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `OrderId` varchar(20) CHARACTER SET latin1 DEFAULT NULL COMMENT '12位订单号',
  `Adid` varchar(20) CHARACTER SET latin1 DEFAULT NULL COMMENT '购买用户ID',
  `CardId` int(10) DEFAULT NULL COMMENT '卡券ID',
  `Count` int(10) DEFAULT NULL COMMENT '购买数量',
  `Price` int(10) DEFAULT NULL COMMENT '单价',
  `TotlePrice` int(10) DEFAULT NULL COMMENT '总价',
  `OrderState` int(10) DEFAULT NULL COMMENT '订单状态',
  `Remarks` varchar(100) CHARACTER SET latin1 DEFAULT NULL COMMENT '备注',
  `CreateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `usingTime` timestamp NULL DEFAULT NULL COMMENT '使用时间',
  `overTime` timestamp NULL DEFAULT NULL COMMENT '过期时间',
  `CardName` varchar(50) DEFAULT NULL COMMENT '卡券名称',
  `CardType` tinyint(4) DEFAULT NULL COMMENT '卡券类型',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `Id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `OpenId` varchar(50) DEFAULT NULL COMMENT 'OpenId',
  `Adid` varchar(50) DEFAULT NULL COMMENT '根据openId生成注册用户id',
  `NickName` varchar(20) DEFAULT NULL COMMENT '用户昵称',
  `Sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `Province` varchar(20) DEFAULT NULL COMMENT '省',
  `City` varchar(20) DEFAULT NULL COMMENT '市',
  `Country` varchar(20) DEFAULT NULL COMMENT '国家',
  `HeadImgUrl` varchar(20) DEFAULT NULL COMMENT '用户头像',
  `Code` int(10) DEFAULT NULL COMMENT '短信验证码',
  `Remarks` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
