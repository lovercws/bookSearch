/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.5.50-log : Database - bookSearch
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bookSearch` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `bookSearch`;

/*Table structure for table `tb_menu` */

DROP TABLE IF EXISTS `tb_menu`;

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
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

/*Data for the table `tb_menu` */

insert  into `tb_menu`(`menu_id`,`parent_menu_id`,`menu_code`,`menu_title`,`menu_order`,`menu_icon`,`menu_src`,`menu_state`,`menu_auto_show`,`menu_create_date`,`menu_is_menu`) values (3,0,'SYSTEM','系统管理',1,'','','open','','2016-06-16 20:20:40',''),(4,0,'BOOK','书籍管理',2,'','','open','','2016-06-16 20:21:06',''),(5,3,'SYSTEM_USER','用户管理',10,'','','open','','2016-06-16 20:51:27',''),(6,3,'SYSTEM_ROLE','角色管理',11,'','','open','','2016-06-16 20:51:34',''),(7,3,'SYSTEM_MENU','菜单管理',12,'','','open','','2016-06-16 20:59:40',''),(8,3,'SYSTEM_AUTH','权限管理',13,'','','open','','2016-06-16 20:51:43',''),(11,4,'BOOK_CLASSIFY','书籍分类管理',21,'','','open','','2016-06-16 20:52:31',''),(12,4,'BOOK_UPLOAD','书籍上传管理',22,'','','open','','2016-06-16 20:53:18',''),(13,4,'BOOK_COLLECTION','书籍收藏管理',23,'','','open','','2016-06-16 20:54:05',''),(14,4,'BOOK_MANAGER','书籍管理',24,'','','open','','2016-06-16 20:55:09',''),(15,4,'BOOK_DOWNLOAD','书籍下载管理',25,'','','open','','2016-06-16 20:56:18',''),(16,5,'SYSTEM_USER_DETAIL','用户详情',101,'','system/userManagerAction_userDetailMessage.do','open','','2016-06-16 21:09:52',''),(17,5,'SYSTEM_USER_CHANGEPASSWORD','修改密码',102,'','system/userManagerAction_changePassword.do','open','','2016-06-16 21:09:43',''),(18,5,'SYSTEM_USER_LIST','用户列表',103,'','system/userManagerAction_userList.do','open','','2016-06-16 21:09:32',''),(19,5,'SYSTEM_USER_ONLINE','在线用户',104,'','system/userManagerAction_onlineUser.do','open','','2016-06-16 21:09:23',''),(20,5,'SYSTEM_USER_STATISTICS','用户统计',105,'','system/userManagerAction_userStatistics.do','open','','2016-06-16 21:09:15',''),(21,6,'SYSTEM_ROLE_LIST','角色列表',110,'','system/roleManagerAction_roleList.do','open','','2016-06-17 14:06:19',''),(22,7,'SYSTEM_MENU_TREE','菜单树',121,'','system/menuManagerAction_menuTree.do','open','','2016-06-16 21:11:41',''),(23,7,'SYSTEM_MENU_TABLE','菜单列表',122,'','system/menuManagerAction_menuTable.do','open','','2016-06-16 21:12:22',''),(24,8,'SYSTEM_AUTH_LIST','权限列表',131,'','system/authManagerAction_authTable.do','open','','2016-06-17 11:39:33',''),(25,12,'BOOK_UPLOAD_STATISTICS','上传统计',224,'','book/uploadManagerAction_uploadStatistics.do','open','','2016-06-16 21:14:57',''),(26,12,'BOOK_UPLOAD_SETTING','上传设置',221,'','book/uploadManagerAction_uploadSetting.do','open','','2016-06-16 21:15:40',''),(27,12,'BOOK_UPLOAD_HISTORY','上传记录',222,'','book/uploadManagerAction_uploadHistory.do','open','','2016-06-16 21:16:35',''),(28,12,'BOOK_UPLOAD_BOOKUPLOAD','书籍上传',223,'','book/uploadManagerAction_uploadBook.do','open','','2016-06-16 21:17:22',''),(29,11,'BOOK_CLASSIFY_TREE','分类树',211,'','book/collectionManagerAction_collectionHistory.do','open','','2016-06-16 21:19:01',''),(30,11,'BOOK_CLASSIFY_TABLE','分类表',212,'','book/categoryManagerAction_categoryTable.do','open','','2016-06-16 21:19:34',''),(31,14,'BOOK_MANAGER_LIST','书籍列表',241,'','book/bookManagerAction_bookList.do','open','','2016-06-16 21:20:28',''),(32,14,'BOOK_MANAGER_CLASSIFY','书籍分类',242,'','book/bookManagerAction_bookClassify.do','open','','2016-06-16 21:21:17',''),(33,14,'BOOK_MANAGER_STATISTIC','书籍分类统计',243,'','book/bookManagerAction_bookClassifyStatistics.do','open','','2016-06-16 21:22:27',''),(34,13,'BOOK_COLLECTION_HISTORY','收藏记录',231,'','book/collectionManagerAction_collectionHistory.do','open','','2016-06-16 21:23:09',''),(35,15,'BOOK_DOWNLOAD_HISTORY','下载记录',251,'','book/downloadManagerAction_downloadHistory.do','open','','2016-06-16 21:24:06',''),(36,15,'BOOK_DOWNLOAD_STATISTIC','下载统计',252,'','book/downloadManagerAction_downloadStatistics.do','open','','2016-06-16 21:24:54',''),(38,23,'save','保存',1001,'','system/menuManagerAction_saveMenu.do','open','','2016-06-17 11:49:55','\0'),(39,23,'delete','删除',1002,'','system/menuManagerAction_deleteMenu.do','open','','2016-06-17 11:50:00','\0'),(41,8,'SYSTEM_AUTH_TREE','权限树',1,'','system/authManagerAction_authTree.do','open','','2016-06-17 12:03:09','');

/*Table structure for table `tb_role` */

DROP TABLE IF EXISTS `tb_role`;

CREATE TABLE `tb_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `role_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `role_remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `role_is_system` bit(1) DEFAULT NULL,
  `menu_create_date` datetime DEFAULT NULL,
  `role_issystem` bit(1) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `tb_role` */

insert  into `tb_role`(`role_id`,`role_code`,`role_name`,`role_remark`,`role_is_system`,`menu_create_date`,`role_issystem`) values (1,'admin_role','管理员角色','管理员角色\r\n管理员角色\r\n管理员角色',NULL,'2016-06-17 15:07:04',''),(3,'system_role','系统角色','系统用户的角色',NULL,'2016-06-17 15:11:00',''),(5,'comm_role','普通用户','普通用户',NULL,'2016-06-17 17:49:46','');

/*Table structure for table `tb_role_menu` */

DROP TABLE IF EXISTS `tb_role_menu`;

CREATE TABLE `tb_role_menu` (
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  KEY `FK37507EF72804FC9D` (`menu_id`),
  KEY `FK37507EF74173913D` (`role_id`),
  CONSTRAINT `FK37507EF74173913D` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`role_id`),
  CONSTRAINT `FK37507EF72804FC9D` FOREIGN KEY (`menu_id`) REFERENCES `tb_menu` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `tb_role_menu` */

insert  into `tb_role_menu`(`role_id`,`menu_id`) values (1,3),(1,5),(1,16),(1,17),(1,18),(1,19),(1,20),(1,6),(1,21),(1,7),(1,22),(1,23),(1,38),(1,39),(1,8),(1,41),(1,24),(1,4),(1,11),(1,29),(1,30),(1,12),(1,26),(1,27),(1,28),(1,25),(1,13),(1,34),(1,14),(1,31),(1,32),(1,33),(1,15),(1,35),(1,36);

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

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
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `tb_user` */

/*Table structure for table `tb_user_role` */

DROP TABLE IF EXISTS `tb_user_role`;

CREATE TABLE `tb_user_role` (
  `role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `FK37884BD94173913D` (`role_id`),
  KEY `FK37884BD9E69E551D` (`user_id`),
  CONSTRAINT `FK37884BD9E69E551D` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`),
  CONSTRAINT `FK37884BD94173913D` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `tb_user_role` */

/* Function  structure for function  `getChildLst` */

/*!50003 DROP FUNCTION IF EXISTS `getChildLst` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `getChildLst`(rootId INT) RETURNS varchar(1000) CHARSET utf8 COLLATE utf8_bin
BEGIN  
		DECLARE sTemp VARCHAR(1000) ;
		DECLARE sTempChd VARCHAR(1000);
		
		SET sTemp = '';
		SET sTempChd =rootId;
		
		WHILE sTempChd IS NOT NULL DO
			SET sTemp = CONCAT(sTemp,',',sTempChd);
			SELECT GROUP_CONCAT(menu_id) INTO sTempChd FROM tb_menu WHERE FIND_IN_SET(parent_menu_id,sTempChd)>0;
		END WHILE;
		
		RETURN sTemp;
	END */$$
DELIMITER ;

/* Function  structure for function  `getMenuChildren` */

/*!50003 DROP FUNCTION IF EXISTS `getMenuChildren` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `getMenuChildren`(rootId INT) RETURNS varchar(1000) CHARSET utf8 COLLATE utf8_bin
BEGIN  
		DECLARE sTemp VARCHAR(1000) ;
		DECLARE sTempChd VARCHAR(1000);
		
		SET sTemp = '';
		SET sTempChd =rootId;
		
		WHILE sTempChd IS NOT NULL DO
		
		        if sTemp='' 
		        then
		           SET sTemp=sTempChd;
		        else
			    SET sTemp = CONCAT(sTemp,',',sTempChd);
		        end if;
		        
			
			SELECT GROUP_CONCAT(menu_id) INTO sTempChd FROM tb_menu WHERE FIND_IN_SET(parent_menu_id,sTempChd)>0;
			
		END WHILE;
		
		RETURN sTemp;
	END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
