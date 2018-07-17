/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.40 : Database - farmer
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`farmer` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `farmer`;

/*Table structure for table `admin` */

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

/*Data for the table `admin` */

/*Table structure for table `card` */

DROP TABLE IF EXISTS `card`;

CREATE TABLE `card` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `CardId` int(10) DEFAULT NULL COMMENT '卡券ID',
  `Name` varchar(50) DEFAULT NULL COMMENT '卡券名称',
  `Price` int(10) DEFAULT NULL COMMENT '卡券价格',
  `Introduc` text COMMENT '产品介绍',
  `Img` varchar(100) DEFAULT NULL COMMENT '产品图片',
  `Date` datetime DEFAULT NULL COMMENT '卡券有效期',
  `Type` tinyint(4) DEFAULT NULL COMMENT '卡券种类',
  `Inventory` int(10) DEFAULT NULL COMMENT '剩余库存',
  `Soldnum` int(10) DEFAULT NULL COMMENT '卖出数量',
  `CardState` int(10) DEFAULT NULL COMMENT '卡券状态：可用，过期',
  `Remarks` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `card` */

/*Table structure for table `orderdetail` */

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
  `CteateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `usingTime` timestamp NULL DEFAULT NULL COMMENT '使用时间',
  `overTime` timestamp NULL DEFAULT NULL COMMENT '过期时间',
  `CardName` varchar(50) DEFAULT NULL COMMENT '卡券名称',
  `CardType` tinyint(4) DEFAULT NULL COMMENT '卡券类型',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orderdetail` */

/*Table structure for table `userinfo` */

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

/*Data for the table `userinfo` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
