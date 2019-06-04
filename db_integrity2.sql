/*
SQLyog v10.2 
MySQL - 5.0.67-community-log : Database - db_integrity
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_integrity` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_integrity`;

/*Table structure for table `tb_admin` */

DROP TABLE IF EXISTS `tb_admin`;

CREATE TABLE `tb_admin` (
  `id` int(11) NOT NULL auto_increment,
  `admin_number` varchar(255) collate utf8_unicode_ci NOT NULL COMMENT '管理员账号',
  `admin_passwd` varchar(255) collate utf8_unicode_ci default NULL COMMENT '管理员密码',
  `level` varchar(255) collate utf8_unicode_ci default NULL COMMENT '管理员类型',
  `create_time` datetime default NULL,
  `last_edit_time` datetime default NULL,
  `enable_status` tinyint(4) NOT NULL default '1' COMMENT '状态 1：审核通过  2：待审核  3：未通过审核',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `tb_admin` */

insert  into `tb_admin`(`id`,`admin_number`,`admin_passwd`,`level`,`create_time`,`last_edit_time`,`enable_status`) values (1,'005909','21232f297a57a5a743894a0e4a801fc3',NULL,'2019-04-24 17:23:06','2019-05-04 14:05:07',1),(2,'003457','21232f297a57a5a743894a0e4a801fc3',NULL,'2019-04-24 18:56:21','2019-05-02 11:43:11',2),(4,'005463','21232f297a57a5a743894a0e4a801fc3',NULL,'2019-04-26 10:06:28','2019-04-28 21:20:01',1),(5,'239891','asasa',NULL,'2019-04-29 18:39:27','2019-04-29 19:11:38',1),(6,'312233','1517d51e8b33dfce7daa03a00bcdd48f',NULL,'2019-04-29 18:46:34','2019-05-04 09:20:40',1),(7,'003452','c3284d0f94606de1fd2af172aba15bf3',NULL,'2019-04-30 15:49:06','2019-05-02 11:43:24',1),(8,'004673','363ea1ec9184e82885c8172896959412',NULL,'2019-05-03 11:34:19','2019-05-04 09:20:55',1),(9,'213121','21232f297a57a5a743894a0e4a801fc3',NULL,'2019-05-04 10:48:44','2019-05-04 10:48:44',1);

/*Table structure for table `tb_clean_arichive` */

DROP TABLE IF EXISTS `tb_clean_arichive`;

CREATE TABLE `tb_clean_arichive` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL,
  `shoushou` varchar(255) collate utf8_unicode_ci default NULL COMMENT '收受红包、礼金、有价证券及其他受馈赠的情况。',
  `geren` varchar(255) collate utf8_unicode_ci default NULL COMMENT '个人操办婚丧嫁娶报备及执行情况',
  `peiou` varchar(255) collate utf8_unicode_ci default NULL COMMENT '配偶及成年子女就业及所在国籍情况',
  `zaiqi` varchar(255) collate utf8_unicode_ci default NULL COMMENT '个人在企业、社会及其酬取情况',
  `shifou` varchar(255) collate utf8_unicode_ci default NULL COMMENT '个人是否参与涉矿、涉矿企业经营活动或参与分红情况',
  `niandu` varchar(255) collate utf8_unicode_ci default NULL COMMENT '个人年度科研经费入账使用及财务个人借款情况',
  `yinsi` varchar(255) collate utf8_unicode_ci default NULL COMMENT '个人因私出入国（境）情况',
  `create_time` datetime default NULL,
  `last_edit_time` datetime default NULL,
  `enable_status` tinyint(4) NOT NULL default '1',
  PRIMARY KEY  (`id`,`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `tb_clean_arichive` */

insert  into `tb_clean_arichive`(`id`,`user_id`,`shoushou`,`geren`,`peiou`,`zaiqi`,`shifou`,`niandu`,`yinsi`,`create_time`,`last_edit_time`,`enable_status`) values (7,41,'1','1','111','11','1','1','1','2019-04-26 10:20:05','2019-04-26 10:20:05',1),(8,42,'1','11','1','11','11','1','1','2019-04-26 11:25:21','2019-04-26 11:25:21',1),(9,43,'qsasa\ndasas\ndas\ndas','qdas','q','111111111111111111','q\n是','1','1s\na\n112312321','2019-04-26 11:26:03','2019-05-03 23:45:37',2),(13,47,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2019-04-30 15:51:11','2019-04-30 15:51:11',1);

/*Table structure for table `tb_person_consultations` */

DROP TABLE IF EXISTS `tb_person_consultations`;

CREATE TABLE `tb_person_consultations` (
  `name` varchar(255) collate utf8_unicode_ci default NULL,
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL,
  `year` varchar(4) collate utf8_unicode_ci default NULL COMMENT '本年已经上传内容',
  `path` varchar(100) collate utf8_unicode_ci default NULL COMMENT '上传路径',
  PRIMARY KEY  (`id`,`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `tb_person_consultations` */

insert  into `tb_person_consultations`(`name`,`id`,`user_id`,`year`,`path`) values ('长大',21,43,'2019','F:\\IntegrityArchivesUpload\\20190530114151-43-2019个人述职述联文件.docx');

/*Table structure for table `tb_person_decla` */

DROP TABLE IF EXISTS `tb_person_decla`;

CREATE TABLE `tb_person_decla` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) default NULL,
  `renmian` varchar(255) collate utf8_unicode_ci default NULL COMMENT '任免情况',
  `renshi` varchar(255) collate utf8_unicode_ci default NULL COMMENT '人事档案情况',
  `yinbu` varchar(255) collate utf8_unicode_ci default NULL COMMENT '因不如实报告个人有关事项受到处理的情况',
  `xunshi` varchar(255) collate utf8_unicode_ci default NULL COMMENT '巡视视察、信访、案件监督管理以及其他方面移交的问题线索和处理情况',
  `kaizhan` varchar(255) collate utf8_unicode_ci default NULL COMMENT '开展谈话函询、初步核实、审查调查、以及其他工作形成的材料',
  `dangfeng` varchar(255) collate utf8_unicode_ci default NULL COMMENT '党风廉政意见回复材料',
  `qita` varchar(255) collate utf8_unicode_ci default NULL COMMENT '其他反映廉政情况的材料',
  `create_time` datetime default NULL,
  `last_edit_time` datetime default NULL,
  `enable_status` tinyint(4) NOT NULL default '1',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `tb_person_decla` */

insert  into `tb_person_decla`(`id`,`user_id`,`renmian`,`renshi`,`yinbu`,`xunshi`,`kaizhan`,`dangfeng`,`qita`,`create_time`,`last_edit_time`,`enable_status`) values (8,41,'11','1','1','1','232','2','2','2019-04-26 10:20:05','2019-04-26 10:20:05',1),(9,42,'11','1','1','23','3','22','2','2019-04-26 11:25:21','2019-05-02 11:22:12',1),(10,43,'sad','1','1','1','1','1','1','2019-04-26 11:26:03','2019-04-30 10:02:44',2),(14,47,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2019-04-30 15:51:11','2019-04-30 15:51:11',1);

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL auto_increment,
  `user_number` varchar(11) collate utf8_unicode_ci NOT NULL COMMENT '用户工号',
  `user_passwd` varchar(255) collate utf8_unicode_ci default NULL COMMENT '用户密码',
  `name` varchar(255) collate utf8_unicode_ci default NULL COMMENT '姓名',
  `create_time` datetime default NULL,
  `last_edit_time` datetime default NULL,
  `enable_status` tinyint(4) NOT NULL default '4' COMMENT '状态 1：审核通过  2：待审核  3：未通过审核',
  PRIMARY KEY  (`id`,`user_number`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `tb_user` */

insert  into `tb_user`(`id`,`user_number`,`user_passwd`,`name`,`create_time`,`last_edit_time`,`enable_status`) values (42,'005960','21232f297a57a5a743894a0e4a801fc3','张鑫','2019-04-26 11:25:21','2019-04-30 14:25:37',1),(43,'005909','21232f297a57a5a743894a0e4a801fc3','长大','2019-04-26 11:26:03','2019-04-30 21:35:52',1),(47,'674891','0de2443a74cb964ea170ab6de12e6914','张晓孙','2019-04-30 15:51:11','2019-04-30 15:51:11',1);

/*Table structure for table `tb_user_family` */

DROP TABLE IF EXISTS `tb_user_family`;

CREATE TABLE `tb_user_family` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL,
  `appellation` varchar(255) collate utf8_unicode_ci default NULL COMMENT '称谓',
  `user_family_name` varchar(255) collate utf8_unicode_ci default NULL COMMENT '姓名',
  `age` varchar(11) collate utf8_unicode_ci default NULL COMMENT '年龄',
  `politics_status` varchar(255) collate utf8_unicode_ci default NULL COMMENT '政治面貌',
  `work_unit_and_position` varchar(255) collate utf8_unicode_ci default NULL COMMENT '工作单位及职务',
  `create_time` datetime default NULL,
  `last_edit_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `tb_user_family` */

insert  into `tb_user_family`(`id`,`user_id`,`appellation`,`user_family_name`,`age`,`politics_status`,`work_unit_and_position`,`create_time`,`last_edit_time`) values (1,43,'1','1','11','11','11','2019-04-25 11:20:04','2019-04-18 11:20:09'),(2,43,'1','1','11','11','11','2019-04-25 11:20:04','2019-04-18 11:20:09'),(7,40,'1','1','1','1','1','2019-05-02 14:14:29','2019-05-02 18:17:56'),(8,40,'qw','qw','wq','wq','wq','2019-05-02 14:15:37','2019-05-02 14:15:37');

/*Table structure for table `tb_user_info` */

DROP TABLE IF EXISTS `tb_user_info`;

CREATE TABLE `tb_user_info` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(255) NOT NULL COMMENT '用户工号',
  `avatar` varchar(255) collate utf8_unicode_ci default NULL COMMENT '照片',
  `name` varchar(255) collate utf8_unicode_ci default NULL COMMENT '用户姓名',
  `gender` varchar(255) collate utf8_unicode_ci default NULL COMMENT '性别',
  `date_of_birth` varchar(255) collate utf8_unicode_ci default NULL COMMENT '出生年月',
  `nation` varchar(255) collate utf8_unicode_ci default NULL COMMENT '民族',
  `native_place` varchar(255) collate utf8_unicode_ci default NULL COMMENT '籍贯',
  `place_of_birth` varchar(255) collate utf8_unicode_ci default NULL COMMENT '出生地',
  `date_of_join_party` varchar(255) collate utf8_unicode_ci default NULL COMMENT '入党时间',
  `date_of_join_work` varchar(255) collate utf8_unicode_ci default NULL COMMENT '参加工作时间',
  `physical_condition` varchar(255) collate utf8_unicode_ci default NULL COMMENT '健康状况',
  `technical_position` varchar(255) collate utf8_unicode_ci default NULL COMMENT '专业技术职务',
  `familiar_major_and_specialty` varchar(255) collate utf8_unicode_ci default NULL COMMENT '熟悉专业有何特长',
  `full_time_degree` varchar(255) collate utf8_unicode_ci default NULL COMMENT '全日制学历学位',
  `full_time_graduated_university_and_major` varchar(255) collate utf8_unicode_ci default NULL COMMENT '全日制毕业院校系及专业',
  `part_time_degree` varchar(255) collate utf8_unicode_ci default NULL COMMENT '在职学历学位',
  `part_time_graduated_university_and_major` varchar(255) collate utf8_unicode_ci default NULL COMMENT '在职毕业院校系及专业',
  `current_position` varchar(255) collate utf8_unicode_ci default NULL COMMENT '现任职务',
  `resume` varchar(255) collate utf8_unicode_ci default NULL COMMENT '简历',
  `rewards_and_punishment` varchar(255) collate utf8_unicode_ci default NULL COMMENT '奖惩情况',
  `annual_assessment_results` varchar(255) collate utf8_unicode_ci default NULL COMMENT '年度考核结果',
  `create_time` datetime default NULL,
  `last_edit_time` datetime default NULL,
  `enable_status` tinyint(4) NOT NULL default '1' COMMENT '状态 1：审核通过  2：待审核  3：未通过审核',
  PRIMARY KEY  (`id`,`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `tb_user_info` */

insert  into `tb_user_info`(`id`,`user_id`,`avatar`,`name`,`gender`,`date_of_birth`,`nation`,`native_place`,`place_of_birth`,`date_of_join_party`,`date_of_join_work`,`physical_condition`,`technical_position`,`familiar_major_and_specialty`,`full_time_degree`,`full_time_graduated_university_and_major`,`part_time_degree`,`part_time_graduated_university_and_major`,`current_position`,`resume`,`rewards_and_punishment`,`annual_assessment_results`,`create_time`,`last_edit_time`,`enable_status`) values (12,42,'D:\\upload\\2017-07-03 191531.jpg','张鑫x','男','2019.02','汉','重庆','重庆','2019.02','2019.02','123132','1','1','1','1','1','1232131','1','1','1','1','2019-04-26 11:25:21','2019-05-04 12:20:31',1),(13,43,'http://www.springboot.xyz:8080/pictures/我是张大壮1.png','我是张大壮','女','1996年12月','汉族','重庆','江北','1998年12月','我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮','我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮','1231231我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮','我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮','我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮','我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮','我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮','我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮','我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮','我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮','我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮','我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮','2019-04-26 11:26:03','2019-05-05 12:56:34',1),(17,47,'/pictures/张晓孙user3.png','张晓孙','男','2019.06','q','q\'q','q','2019.06','2019.06','q','q','q','q','q','q','q','q','q','q','q','2019-04-30 15:51:11','2019-05-05 10:25:53',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
