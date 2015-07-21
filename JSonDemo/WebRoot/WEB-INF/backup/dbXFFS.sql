-- MySQL dump 10.10
--
-- Host: localhost    Database: dbxffs
-- ------------------------------------------------------
-- Server version	5.0.22-community-nt

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bls`
--

DROP TABLE IF EXISTS `bls`;
CREATE TABLE `bls` (
  `id` varchar(40) NOT NULL,
  `btnname` varchar(255) NOT NULL,
  `btnid` varchar(40) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `btnid` (`btnid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bls`
--


/*!40000 ALTER TABLE `bls` DISABLE KEYS */;
LOCK TABLES `bls` WRITE;
INSERT INTO `bls` VALUES ('011','讲解','5'),('012','向前','8'),('013','向后','9'),('014','上级菜单','7'),('015','主菜单','6'),('016','控制台','10'),('017','关闭','3');
UNLOCK TABLES;
/*!40000 ALTER TABLE `bls` ENABLE KEYS */;

--
-- Table structure for table `blse`
--

DROP TABLE IF EXISTS `blse`;
CREATE TABLE `blse` (
  `id` varchar(40) NOT NULL,
  `btnname` varchar(255) NOT NULL,
  `btnid` varchar(40) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `btnid` (`btnid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `blse`
--


/*!40000 ALTER TABLE `blse` DISABLE KEYS */;
LOCK TABLES `blse` WRITE;
INSERT INTO `blse` VALUES ('021','开始训练','204'),('022','复位','205'),('023','记忆回放','206'),('024','停止回放','209'),('025','关闭','203');
UNLOCK TABLES;
/*!40000 ALTER TABLE `blse` ENABLE KEYS */;

--
-- Table structure for table `blskh`
--

DROP TABLE IF EXISTS `blskh`;
CREATE TABLE `blskh` (
  `id` varchar(40) NOT NULL,
  `btnname` varchar(255) NOT NULL,
  `btnid` varchar(40) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `btnid` (`btnid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `blskh`
--


/*!40000 ALTER TABLE `blskh` DISABLE KEYS */;
LOCK TABLES `blskh` WRITE;
INSERT INTO `blskh` VALUES ('041','开始考核','404'),('042','交卷','405'),('043','国际新标准','407'),('044','自定义标准','408'),('045','记忆回放','409'),('046','停止回放','410'),('047','关闭','403');
UNLOCK TABLES;
/*!40000 ALTER TABLE `blskh` ENABLE KEYS */;

--
-- Table structure for table `blskh042`
--

DROP TABLE IF EXISTS `blskh042`;
CREATE TABLE `blskh042` (
  `id` varchar(40) NOT NULL,
  `btnname` varchar(255) NOT NULL,
  `btnid` varchar(40) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `btnid` (`btnid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `blskh042`
--


/*!40000 ALTER TABLE `blskh042` DISABLE KEYS */;
LOCK TABLES `blskh042` WRITE;
INSERT INTO `blskh042` VALUES ('0421','保存','323'),('0422','关闭','324');
UNLOCK TABLES;
/*!40000 ALTER TABLE `blskh042` ENABLE KEYS */;

--
-- Table structure for table `blskh043`
--

DROP TABLE IF EXISTS `blskh043`;
CREATE TABLE `blskh043` (
  `id` varchar(40) NOT NULL,
  `btnname` varchar(255) NOT NULL,
  `btnid` varchar(40) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `btnid` (`btnid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `blskh043`
--


/*!40000 ALTER TABLE `blskh043` DISABLE KEYS */;
LOCK TABLES `blskh043` WRITE;
INSERT INTO `blskh043` VALUES ('0431','确定','313');
UNLOCK TABLES;
/*!40000 ALTER TABLE `blskh043` ENABLE KEYS */;

--
-- Table structure for table `blskh044`
--

DROP TABLE IF EXISTS `blskh044`;
CREATE TABLE `blskh044` (
  `id` varchar(40) NOT NULL,
  `btnname` varchar(255) NOT NULL,
  `btnid` varchar(40) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `btnid` (`btnid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `blskh044`
--


/*!40000 ALTER TABLE `blskh044` DISABLE KEYS */;
LOCK TABLES `blskh044` WRITE;
INSERT INTO `blskh044` VALUES ('0441','保存','318'),('0442','取消','319');
UNLOCK TABLES;
/*!40000 ALTER TABLE `blskh044` ENABLE KEYS */;

--
-- Table structure for table `blsmnkhe`
--

DROP TABLE IF EXISTS `blsmnkhe`;
CREATE TABLE `blsmnkhe` (
  `id` varchar(40) NOT NULL,
  `btnname` varchar(255) NOT NULL,
  `btnid` varchar(40) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `btnid` (`btnid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `blsmnkhe`
--


/*!40000 ALTER TABLE `blsmnkhe` DISABLE KEYS */;
LOCK TABLES `blsmnkhe` WRITE;
INSERT INTO `blsmnkhe` VALUES ('031','开始考核','204'),('032','交卷','205'),('033','国际新标准','206'),('034','自定义标准','207'),('035','记忆回放','208'),('036','停止回放','209'),('037','关闭','210');
UNLOCK TABLES;
/*!40000 ALTER TABLE `blsmnkhe` ENABLE KEYS */;

--
-- Table structure for table `mainface`
--

DROP TABLE IF EXISTS `mainface`;
CREATE TABLE `mainface` (
  `id` varchar(40) NOT NULL,
  `btnname` varchar(500) NOT NULL,
  `btnid` varchar(40) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `btnid` (`btnid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `mainface`
--


/*!40000 ALTER TABLE `mainface` DISABLE KEYS */;
LOCK TABLES `mainface` WRITE;
INSERT INTO `mainface` VALUES ('01','基本生命支持','1000'),('02','基本生命支持训练','1004'),('03','基本生命支持模拟考核训练','1005'),('04','基本生命支持考核','1006'),('05','心肺复苏竞赛','1007');
UNLOCK TABLES;
/*!40000 ALTER TABLE `mainface` ENABLE KEYS */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` varchar(40) NOT NULL,
  `username` varchar(40) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--


/*!40000 ALTER TABLE `users` DISABLE KEYS */;
LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES ('1','admin','admin'),('2','mushroom','123456');
UNLOCK TABLES;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

--
-- Table structure for table `xffsface1`
--

DROP TABLE IF EXISTS `xffsface1`;
CREATE TABLE `xffsface1` (
  `id` varchar(40) NOT NULL,
  `btnname` varchar(255) NOT NULL,
  `btnid` varchar(40) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `btnid` (`btnid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `xffsface1`
--


/*!40000 ALTER TABLE `xffsface1` DISABLE KEYS */;
LOCK TABLES `xffsface1` WRITE;
INSERT INTO `xffsface1` VALUES ('11','开始','506'),('12','返回','504');
UNLOCK TABLES;
/*!40000 ALTER TABLE `xffsface1` ENABLE KEYS */;

--
-- Table structure for table `xffsface2`
--

DROP TABLE IF EXISTS `xffsface2`;
CREATE TABLE `xffsface2` (
  `id` varchar(40) NOT NULL,
  `btnname` varchar(255) NOT NULL,
  `btnid` varchar(40) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `btnid` (`btnid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `xffsface2`
--


/*!40000 ALTER TABLE `xffsface2` DISABLE KEYS */;
LOCK TABLES `xffsface2` WRITE;
INSERT INTO `xffsface2` VALUES ('21','交卷','405'),('22','返回','424'),('23','国际新标准','407'),('24','记忆回放','409'),('25','停止回放','410');
UNLOCK TABLES;
/*!40000 ALTER TABLE `xffsface2` ENABLE KEYS */;

--
-- Table structure for table `xffsface3`
--

DROP TABLE IF EXISTS `xffsface3`;
CREATE TABLE `xffsface3` (
  `id` varchar(40) NOT NULL,
  `btnname` varchar(255) NOT NULL,
  `btnid` varchar(40) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `btnid` (`btnid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `xffsface3`
--


/*!40000 ALTER TABLE `xffsface3` DISABLE KEYS */;
LOCK TABLES `xffsface3` WRITE;
INSERT INTO `xffsface3` VALUES ('31','确定','313');
UNLOCK TABLES;
/*!40000 ALTER TABLE `xffsface3` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

