/*
SQLyog Ultimate v8.8 
MySQL - 5.7.20-log : Database - mytmall
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mytmall` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mytmall`;

/*Table structure for table `t_category` */

DROP TABLE IF EXISTS `t_category`;

CREATE TABLE `t_category` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

/*Data for the table `t_category` */

insert  into `t_category`(`cid`,`cname`) values (2,'马桶'),(3,'平板电视'),(4,'沙发'),(5,'热水器'),(24,'平衡车'),(25,'扫地机器人'),(26,'原汁机'),(27,'冰箱'),(28,'空调'),(29,'女表'),(30,'男表'),(31,'男拿手提包'),(32,'男士西服'),(33,'时尚男鞋'),(34,'品牌女装'),(35,'太阳镜'),(36,'安全座椅'),(46,'阿萨德'),(47,'阿发');

/*Table structure for table `t_order` */

DROP TABLE IF EXISTS `t_order`;

CREATE TABLE `t_order` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `ordCode` varchar(20) NOT NULL,
  `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `payDate` timestamp NULL DEFAULT NULL,
  `deliveryDate` timestamp NULL DEFAULT NULL,
  `confirmDate` timestamp NULL DEFAULT NULL,
  `reviewDate` timestamp NULL DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `post` varchar(10) DEFAULT NULL,
  `telnumber` varchar(11) DEFAULT NULL,
  `receiver` varchar(20) DEFAULT NULL,
  `userMessage` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `FK_order_ordStatus` (`sid`),
  KEY `FK_user_order` (`uid`),
  CONSTRAINT `FK_order_ordStatus` FOREIGN KEY (`sid`) REFERENCES `t_order_status` (`sid`),
  CONSTRAINT `FK_user_order` FOREIGN KEY (`uid`) REFERENCES `t_user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

/*Data for the table `t_order` */

insert  into `t_order`(`oid`,`sid`,`uid`,`ordCode`,`createDate`,`payDate`,`deliveryDate`,`confirmDate`,`reviewDate`,`address`,`post`,`telnumber`,`receiver`,`userMessage`) values (13,5,1,'201712072139053542','2017-04-03 22:27:56','2017-12-07 21:39:08','2017-12-07 21:39:24','2017-12-07 21:39:33',NULL,'四川理工学院','618303','15283897229','帅',''),(14,5,1,'2017120814252110590','2017-04-03 22:27:51','2017-12-08 14:26:16','2017-12-08 14:26:37','2017-12-08 14:26:50',NULL,'四川理工学院','618303','15283897229','帅',''),(22,3,1,'10020182283882926','2018-04-04 23:01:47',NULL,NULL,NULL,NULL,'四川省成都市武侯区','610000','12333334444','tttt',NULL);

/*Table structure for table `t_order_item` */

DROP TABLE IF EXISTS `t_order_item`;

CREATE TABLE `t_order_item` (
  `oiid` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) DEFAULT '0',
  `prices` float DEFAULT '0',
  `pid` int(11) NOT NULL,
  `oid` int(11) NOT NULL,
  PRIMARY KEY (`oiid`),
  KEY `FK_t_order_item` (`oid`),
  CONSTRAINT `FK_t_order_item` FOREIGN KEY (`oid`) REFERENCES `t_order` (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `t_order_item` */

insert  into `t_order_item`(`oiid`,`number`,`prices`,`pid`,`oid`) values (6,1,1614.06,5,13),(7,1,1614.06,5,14),(8,10,220,6,13);

/*Table structure for table `t_order_status` */

DROP TABLE IF EXISTS `t_order_status`;

CREATE TABLE `t_order_status` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `t_order_status` */

insert  into `t_order_status`(`sid`,`status`) values (1,'待支付'),(2,'待发货'),(3,'待收货'),(4,'待评价'),(5,'已完成'),(6,'已取消');

/*Table structure for table `t_product` */

DROP TABLE IF EXISTS `t_product`;

CREATE TABLE `t_product` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pname` varchar(30) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `orignalprice` float DEFAULT NULL,
  `promotionprice` float DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `cid` int(11) NOT NULL,
  `createdate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`pid`),
  KEY `FK_category_product` (`cid`),
  CONSTRAINT `FK_category_product` FOREIGN KEY (`cid`) REFERENCES `t_category` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `t_product` */

insert  into `t_product`(`pid`,`pname`,`title`,`orignalprice`,`promotionprice`,`stock`,`cid`,`createdate`) values (5,'Konka/康佳 LED40S1 40英寸','10核 64位芯片 安卓智能 无线WIFI 网络',1699.01,1614.06,62,3,'2017-12-04 14:02:15'),(6,'阿萨德','风中一匹狼',100,22,1111,3,'2018-01-03 20:14:26');

/*Table structure for table `t_product_image` */

DROP TABLE IF EXISTS `t_product_image`;

CREATE TABLE `t_product_image` (
  `piid` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`piid`),
  KEY `FK_product_pimage` (`pid`),
  CONSTRAINT `FK_product_pimage` FOREIGN KEY (`pid`) REFERENCES `t_product` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

/*Data for the table `t_product_image` */

insert  into `t_product_image`(`piid`,`type`,`pid`) values (18,'single',5),(19,'single',5),(20,'single',5),(21,'details',5),(22,'details',5),(23,'details',5),(24,'details',5),(25,'details',5),(47,'single',6),(48,'details',6),(49,'details',6),(50,'single',6),(51,'single',6),(52,'single',6),(53,'single',6),(55,'details',6),(57,'single',6),(58,'single',6),(64,'single',6);

/*Table structure for table `t_property` */

DROP TABLE IF EXISTS `t_property`;

CREATE TABLE `t_property` (
  `ppid` int(11) NOT NULL AUTO_INCREMENT,
  `ppname` varchar(20) DEFAULT NULL,
  `cid` int(11) NOT NULL,
  PRIMARY KEY (`ppid`),
  KEY `FK_category_property` (`cid`),
  CONSTRAINT `FK_category_property` FOREIGN KEY (`cid`) REFERENCES `t_category` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

/*Data for the table `t_property` */

insert  into `t_property`(`ppid`,`ppname`,`cid`) values (20,'操作系统',3),(21,'3D类型',3),(22,'能效等级',3),(23,'产品名称',3),(24,'网络连接方式',3),(25,'制造商名称',3),(26,'型号',3),(27,'分辨率',3),(28,'证书编号',3),(29,'申请人名称',3),(30,'3C产品型号',3),(31,'证书状态',3),(32,'品牌',3),(47,'阿萨德',46),(48,'阿发',46),(49,'啊啊',46);

/*Table structure for table `t_property_value` */

DROP TABLE IF EXISTS `t_property_value`;

CREATE TABLE `t_property_value` (
  `ppvid` int(11) NOT NULL AUTO_INCREMENT,
  `ppvalue` varchar(255) DEFAULT NULL,
  `ppid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  PRIMARY KEY (`ppvid`),
  KEY `FK_property_value` (`ppid`),
  CONSTRAINT `FK_property_value` FOREIGN KEY (`ppid`) REFERENCES `t_property` (`ppid`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

/*Data for the table `t_property_value` */

insert  into `t_property_value`(`ppvid`,`ppvalue`,`ppid`,`pid`) values (9,'VIDAA',20,5),(10,'无',21,5),(11,'一级',22,5),(12,'Hisense/海信',23,5),(13,'全部支持',24,5),(14,'青岛海信电器股份有限',25,5),(15,'LED60EC660',26,5),(16,'3840x2160',27,5),(17,'2016010808',28,5),(18,'青岛海信电器股份有限',29,5),(19,'LED60K5500',30,5),(20,'有效',31,5),(21,'Hisense/海信',32,5),(22,'23',20,6),(23,'---',21,6),(24,'---',22,6),(25,'---',23,6),(26,'---',24,6),(27,'---',25,6),(28,'---',26,6),(29,'---',27,6),(30,'111',28,6),(31,'---',29,6),(32,'---',30,6),(33,'---',31,6),(34,'---',32,6);

/*Table structure for table `t_review` */

DROP TABLE IF EXISTS `t_review`;

CREATE TABLE `t_review` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(250) DEFAULT NULL,
  `review_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `pid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`rid`),
  KEY `FK_review_product` (`pid`),
  KEY `FK_user_review` (`uid`),
  CONSTRAINT `FK_review_product` FOREIGN KEY (`pid`) REFERENCES `t_product` (`pid`),
  CONSTRAINT `FK_user_review` FOREIGN KEY (`uid`) REFERENCES `t_user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `t_review` */

insert  into `t_review`(`rid`,`content`,`review_date`,`pid`,`uid`) values (2,'还好啦','2017-12-09 13:58:37',5,1),(3,'感觉一般','2017-12-09 13:58:47',5,1);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL,
  `user_pwd` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`uid`,`user_name`,`user_pwd`) values (1,'sherl','34c4a3d76783b4110fcfeb8d8eecd53e'),(2,'admin','21232f297a57a5a743894a0e4a801fc3');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
