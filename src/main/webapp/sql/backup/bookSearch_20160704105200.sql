-- MySQL dump 10.13  Distrib 5.6.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bookSearch
-- ------------------------------------------------------
-- Server version	5.6.31-log

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
-- Table structure for table `tb_ddl_sex`
--

DROP TABLE IF EXISTS `tb_ddl_sex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_ddl_sex` (
  `sex_id` int(11) NOT NULL AUTO_INCREMENT,
  `sex_code` varchar(20) COLLATE utf8_bin NOT NULL,
  `sex_name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `sex_remark` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `sex_active` bit(1) DEFAULT b'0',
  `start_sate` datetime DEFAULT NULL,
  `limit_date` datetime DEFAULT NULL,
  PRIMARY KEY (`sex_id`),
  UNIQUE KEY `sex_code_UNIQUE` (`sex_code`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_ddl_sex`
--

LOCK TABLES `tb_ddl_sex` WRITE;
/*!40000 ALTER TABLE `tb_ddl_sex` DISABLE KEYS */;
INSERT INTO `tb_ddl_sex` VALUES (4,'MAN','男性','男性','',NULL,NULL),(5,'FEMAL','女性','女性','',NULL,NULL);
/*!40000 ALTER TABLE `tb_ddl_sex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_menu`
--

DROP TABLE IF EXISTS `tb_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_menu_id` int(11) DEFAULT NULL,
  `menu_code` varchar(255) DEFAULT NULL,
  `menu_title` varchar(500) DEFAULT NULL,
  `menu_order` int(11) DEFAULT NULL,
  `menu_icon` varchar(255) DEFAULT NULL,
  `menu_src` varchar(255) DEFAULT NULL,
  `menu_state` varchar(255) DEFAULT NULL,
  `menu_auto_show` bit(1) DEFAULT NULL,
  `menu_create_date` datetime DEFAULT NULL,
  `menu_is_menu` bit(1) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_menu`
--

LOCK TABLES `tb_menu` WRITE;
/*!40000 ALTER TABLE `tb_menu` DISABLE KEYS */;
INSERT INTO `tb_menu` VALUES (3,0,'SYSTEM','系统管理',1,'','','open','','2016-06-16 20:20:40',''),(4,0,'BOOK','书籍管理',2,'','','open','','2016-06-16 20:21:06',''),(5,3,'SYSTEM_USER','用户管理',10,'','','open','','2016-06-16 20:51:27',''),(6,3,'SYSTEM_ROLE','角色管理',11,'','','open','','2016-06-16 20:51:34',''),(7,3,'SYSTEM_MENU','菜单管理',12,'','','open','','2016-06-16 20:59:40',''),(8,3,'SYSTEM_AUTH','权限管理',13,'','','open','','2016-06-16 20:51:43',''),(11,4,'BOOK_CLASSIFY','书籍分类管理',21,'','','open','','2016-06-16 20:52:31',''),(12,4,'BOOK_UPLOAD','书籍上传管理',22,'','','open','','2016-06-16 20:53:18',''),(13,4,'BOOK_COLLECTION','书籍收藏管理',23,'','','open','','2016-06-16 20:54:05',''),(14,4,'BOOK_MANAGER','书籍管理',24,'','','open','','2016-06-16 20:55:09',''),(15,4,'BOOK_DOWNLOAD','书籍下载管理',25,'','','open','','2016-06-16 20:56:18',''),(16,5,'SYSTEM_USER_DETAIL','用户详情',101,'','system/userManagerAction_userDetailMessage.do','open','','2016-06-16 21:09:52',''),(17,5,'SYSTEM_USER_CHANGEPASSWORD','修改密码',102,'','system/userManagerAction_changePassword.do','open','','2016-06-16 21:09:43',''),(18,5,'SYSTEM_USER_LIST','用户列表',103,'','system/userManagerAction_userList.do','open','','2016-06-16 21:09:32',''),(19,5,'SYSTEM_USER_ONLINE','在线用户',104,'','system/userManagerAction_onlineUser.do','open','','2016-06-16 21:09:23',''),(20,5,'SYSTEM_USER_STATISTICS','用户统计',105,'','system/userManagerAction_userStatistics.do','open','','2016-06-16 21:09:15',''),(21,6,'SYSTEM_ROLE_LIST','角色列表',110,'','system/roleManagerAction_roleList.do','open','','2016-06-17 14:06:19',''),(22,7,'SYSTEM_MENU_TREE','菜单树',121,'','system/menuManagerAction_menuTree.do','open','','2016-06-16 21:11:41',''),(23,7,'SYSTEM_MENU_TABLE','菜单列表',122,'','system/menuManagerAction_menuTable.do','open','','2016-06-16 21:12:22',''),(24,8,'SYSTEM_AUTH_LIST','权限列表',131,'','system/authManagerAction_authTable.do','open','','2016-06-17 11:39:33',''),(25,12,'BOOK_UPLOAD_STATISTICS','上传统计',224,'','book/uploadManagerAction_uploadStatistics.do','open','','2016-06-16 21:14:57',''),(26,12,'BOOK_UPLOAD_SETTING','上传设置',221,'','book/uploadManagerAction_uploadSetting.do','open','','2016-06-16 21:15:40',''),(27,12,'BOOK_UPLOAD_HISTORY','上传记录',222,'','book/uploadManagerAction_uploadHistory.do','open','','2016-06-16 21:16:35',''),(28,12,'BOOK_UPLOAD_BOOKUPLOAD','书籍上传',223,'','book/uploadManagerAction_uploadBook.do','open','','2016-06-16 21:17:22',''),(29,11,'BOOK_CLASSIFY_TREE','分类树',211,'','book/collectionManagerAction_collectionHistory.do','open','','2016-06-16 21:19:01',''),(30,11,'BOOK_CLASSIFY_TABLE','分类表',212,'','book/categoryManagerAction_categoryTable.do','open','','2016-06-16 21:19:34',''),(31,14,'BOOK_MANAGER_LIST','书籍列表',241,'','book/bookManagerAction_bookList.do','open','','2016-06-16 21:20:28',''),(32,14,'BOOK_MANAGER_CLASSIFY','书籍分类',242,'','book/bookManagerAction_bookClassify.do','open','','2016-06-16 21:21:17',''),(33,14,'BOOK_MANAGER_STATISTIC','书籍分类统计',243,'','book/bookManagerAction_bookClassifyStatistics.do','open','','2016-06-16 21:22:27',''),(34,13,'BOOK_COLLECTION_HISTORY','收藏记录',231,'','book/collectionManagerAction_collectionHistory.do','open','','2016-06-16 21:23:09',''),(35,15,'BOOK_DOWNLOAD_HISTORY','下载记录',251,'','book/downloadManagerAction_downloadHistory.do','open','','2016-06-16 21:24:06',''),(36,15,'BOOK_DOWNLOAD_STATISTIC','下载统计',252,'','book/downloadManagerAction_downloadStatistics.do','open','','2016-06-16 21:24:54',''),(38,23,'save','保存',1001,'','system/menuManagerAction_saveMenu.do','open','','2016-06-17 11:49:55','\0'),(39,23,'delete','删除',1002,'','system/menuManagerAction_deleteMenu.do','open','','2016-06-17 11:50:00','\0'),(41,8,'SYSTEM_AUTH_TREE','权限树',1,'','system/authManagerAction_authTree.do','open','','2016-06-17 12:03:09',''),(42,3,'SYSTEM_LOG','日志管理',15,'','','open','','2016-07-01 13:38:14',''),(43,3,'SYSTEM_UPLOADSETTING','导出设置管理',14,'','','open','','2016-07-01 13:45:35',''),(44,3,'SYSTEM_DDL','数据字典管理',16,'','','open','','2016-07-01 13:40:27',''),(45,44,'LOG_SEX','性别字典',200,'','/ddl/sexDDLManagerAction_table.do','open','','2016-07-01 14:51:13','');
/*!40000 ALTER TABLE `tb_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_role`
--

DROP TABLE IF EXISTS `tb_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `role_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `role_remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `role_is_system` bit(1) DEFAULT NULL,
  `menu_create_date` datetime DEFAULT NULL,
  `role_issystem` bit(1) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_role`
--

LOCK TABLES `tb_role` WRITE;
/*!40000 ALTER TABLE `tb_role` DISABLE KEYS */;
INSERT INTO `tb_role` VALUES (1,'admin_role','管理员角色','管理员角色\r\n管理员角色\r\n管理员角色',NULL,'2016-06-17 15:07:04',''),(3,'system_role','系统角色','系统用户的角色',NULL,'2016-06-17 15:11:00',''),(5,'comm_role','普通用户','普通用户',NULL,'2016-06-17 17:49:46',''),(6,'book_manager','书籍管理员','书籍管理员',NULL,'2016-06-24 16:14:53','');
/*!40000 ALTER TABLE `tb_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_role_menu`
--

DROP TABLE IF EXISTS `tb_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_role_menu` (
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  KEY `FK37507EF72804FC9D` (`menu_id`),
  KEY `FK37507EF74173913D` (`role_id`),
  CONSTRAINT `FK37507EF72804FC9D` FOREIGN KEY (`menu_id`) REFERENCES `tb_menu` (`menu_id`),
  CONSTRAINT `FK37507EF74173913D` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_role_menu`
--

LOCK TABLES `tb_role_menu` WRITE;
/*!40000 ALTER TABLE `tb_role_menu` DISABLE KEYS */;
INSERT INTO `tb_role_menu` VALUES (3,3),(3,5),(3,16),(3,17),(3,18),(3,19),(3,20),(3,6),(3,21),(3,7),(3,22),(3,23),(3,38),(3,39),(3,8),(3,41),(3,24),(5,3),(5,5),(5,16),(5,17),(5,19),(5,6),(5,21),(5,8),(5,41),(5,24),(5,4),(5,12),(5,26),(5,27),(5,28),(5,14),(5,33),(6,4),(6,11),(6,29),(6,30),(6,12),(6,26),(6,27),(6,28),(6,25),(6,13),(6,34),(6,14),(6,31),(6,32),(6,33),(6,15),(6,35),(6,36),(1,3),(1,5),(1,16),(1,17),(1,18),(1,19),(1,20),(1,6),(1,21),(1,7),(1,22),(1,23),(1,38),(1,39),(1,8),(1,41),(1,24),(1,43),(1,42),(1,44),(1,45),(1,4),(1,11),(1,29),(1,30),(1,12),(1,26),(1,27),(1,28),(1,25),(1,13),(1,34),(1,14),(1,31),(1,32),(1,33),(1,15),(1,35),(1,36);
/*!40000 ALTER TABLE `tb_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `user_password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `user_nickname` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `user_sex` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `user_email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `user_mobile` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `user_contactTel` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `user_address` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `user_remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `user_header_image` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `user_online` bit(1) DEFAULT NULL,
  `user_birthday` datetime DEFAULT NULL,
  `user_registry_date` datetime DEFAULT NULL,
  `user_lastaccess_date` datetime DEFAULT NULL,
  `user_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES (1,'admin','E10ADC3949BA59ABBE56E057F20F883E','管理员','1','915827225@qq.com','15330061450','','','','','\0','2016-06-24 00:00:00',NULL,NULL,'\0'),(2,'lgan','123456','lgan','1','549283053@qq.com','18639012025','','','','','\0','2016-06-08 00:00:00',NULL,NULL,'\0'),(3,'cws','123456','陈伟生','2','gl515xxx@163.com','18639012025','','','','','\0','2016-06-07 00:00:00',NULL,NULL,'\0'),(7,'dnf','123','dnf',NULL,'1916513144@qq.com',NULL,NULL,NULL,NULL,NULL,'\0',NULL,'2016-06-30 08:30:36',NULL,'');
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user_role`
--

DROP TABLE IF EXISTS `tb_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user_role` (
  `role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `FK37884BD94173913D` (`role_id`),
  KEY `FK37884BD9E69E551D` (`user_id`),
  CONSTRAINT `FK37884BD94173913D` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`role_id`),
  CONSTRAINT `FK37884BD9E69E551D` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_role`
--

LOCK TABLES `tb_user_role` WRITE;
/*!40000 ALTER TABLE `tb_user_role` DISABLE KEYS */;
INSERT INTO `tb_user_role` VALUES (1,1),(1,2),(1,3),(3,2),(3,3),(5,2),(5,3),(6,2),(6,3);
/*!40000 ALTER TABLE `tb_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-04 10:52:00
