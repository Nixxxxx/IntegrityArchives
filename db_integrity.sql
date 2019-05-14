/*
Navicat MySQL Data Transfer

Source Server         : 1
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : db_integrity

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-05-14 21:29:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_number` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '管理员账号',
  `admin_passwd` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '管理员密码',
  `level` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '管理员类型',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态 1：审核通过  2：待审核  3：未通过审核',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tb_admin
-- ----------------------------
INSERT INTO `tb_admin` VALUES ('1', '005909', '21232f297a57a5a743894a0e4a801fc3', null, '2019-04-24 17:23:06', '2019-05-04 14:05:07', '1');
INSERT INTO `tb_admin` VALUES ('2', '003457', '21232f297a57a5a743894a0e4a801fc3', null, '2019-04-24 18:56:21', '2019-05-02 11:43:11', '2');
INSERT INTO `tb_admin` VALUES ('4', '005463', '21232f297a57a5a743894a0e4a801fc3', null, '2019-04-26 10:06:28', '2019-04-28 21:20:01', '1');
INSERT INTO `tb_admin` VALUES ('5', '239891', 'asasa', null, '2019-04-29 18:39:27', '2019-04-29 19:11:38', '1');
INSERT INTO `tb_admin` VALUES ('6', '312233', '1517d51e8b33dfce7daa03a00bcdd48f', null, '2019-04-29 18:46:34', '2019-05-04 09:20:40', '1');
INSERT INTO `tb_admin` VALUES ('7', '003452', 'c3284d0f94606de1fd2af172aba15bf3', null, '2019-04-30 15:49:06', '2019-05-02 11:43:24', '1');
INSERT INTO `tb_admin` VALUES ('8', '004673', '363ea1ec9184e82885c8172896959412', null, '2019-05-03 11:34:19', '2019-05-04 09:20:55', '1');
INSERT INTO `tb_admin` VALUES ('9', '213121', '21232f297a57a5a743894a0e4a801fc3', null, '2019-05-04 10:48:44', '2019-05-04 10:48:44', '1');

-- ----------------------------
-- Table structure for tb_clean_arichive
-- ----------------------------
DROP TABLE IF EXISTS `tb_clean_arichive`;
CREATE TABLE `tb_clean_arichive` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `shoushou` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '收受红包、礼金、有价证券及其他受馈赠的情况。',
  `geren` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '个人操办婚丧嫁娶报备及执行情况',
  `peiou` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '配偶及成年子女就业及所在国籍情况',
  `zaiqi` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '个人在企业、社会及其酬取情况',
  `shifou` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '个人是否参与涉矿、涉矿企业经营活动或参与分红情况',
  `niandu` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '个人年度科研经费入账使用及财务个人借款情况',
  `yinsi` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '个人因私出入国（境）情况',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`,`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tb_clean_arichive
-- ----------------------------
INSERT INTO `tb_clean_arichive` VALUES ('6', '40', '2', '2', '2', '2', '2', '2', '2', '2019-04-26 10:19:07', '2019-05-02 11:22:21', '3');
INSERT INTO `tb_clean_arichive` VALUES ('7', '41', '1', '1', '111', '11', '1', '1', '1', '2019-04-26 10:20:05', '2019-04-26 10:20:05', '1');
INSERT INTO `tb_clean_arichive` VALUES ('8', '42', '1', '11', '1', '11', '11', '1', '1', '2019-04-26 11:25:21', '2019-04-26 11:25:21', '1');
INSERT INTO `tb_clean_arichive` VALUES ('9', '43', 'qsasa\ndasas\ndas\ndas', 'qdas', 'q', '111111111111111111', 'q\n是', '1', '1s\na\n112312321', '2019-04-26 11:26:03', '2019-05-03 23:45:37', '2');
INSERT INTO `tb_clean_arichive` VALUES ('13', '47', null, null, null, null, null, null, null, '2019-04-30 15:51:11', '2019-04-30 15:51:11', '1');

-- ----------------------------
-- Table structure for tb_person_decla
-- ----------------------------
DROP TABLE IF EXISTS `tb_person_decla`;
CREATE TABLE `tb_person_decla` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `renmian` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '任免情况',
  `renshi` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '人事档案情况',
  `yinbu` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '因不如实报告个人有关事项受到处理的情况',
  `xunshi` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '巡视视察、信访、案件监督管理以及其他方面移交的问题线索和处理情况',
  `kaizhan` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '开展谈话函询、初步核实、审查调查、以及其他工作形成的材料',
  `dangfeng` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '党风廉政意见回复材料',
  `qita` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '其他反映廉政情况的材料',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tb_person_decla
-- ----------------------------
INSERT INTO `tb_person_decla` VALUES ('7', '40', '3\nsdad', '3', '3', '3', '3', '3', 'i我饿去啊\n圣诞节安康', '2019-04-26 10:19:07', '2019-04-29 18:36:12', '2');
INSERT INTO `tb_person_decla` VALUES ('8', '41', '11', '1', '1', '1', '232', '2', '2', '2019-04-26 10:20:05', '2019-04-26 10:20:05', '1');
INSERT INTO `tb_person_decla` VALUES ('9', '42', '11', '1', '1', '23', '3', '22', '2', '2019-04-26 11:25:21', '2019-05-02 11:22:12', '1');
INSERT INTO `tb_person_decla` VALUES ('10', '43', 'sad', '1', '1', '1', '1', '1', '1', '2019-04-26 11:26:03', '2019-04-30 10:02:44', '2');
INSERT INTO `tb_person_decla` VALUES ('14', '47', null, null, null, null, null, null, null, '2019-04-30 15:51:11', '2019-04-30 15:51:11', '1');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_number` varchar(11) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户工号',
  `user_passwd` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户密码',
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '姓名',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` tinyint(4) NOT NULL DEFAULT '4' COMMENT '状态 1：审核通过  2：待审核  3：未通过审核',
  PRIMARY KEY (`id`,`user_number`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('40', '045362', '21232f297a57a5a743894a0e4a801fc3', '江浩', '2019-04-26 10:19:07', '2019-05-04 10:51:09', '1');
INSERT INTO `tb_user` VALUES ('42', '005960', '21232f297a57a5a743894a0e4a801fc3', '张鑫', '2019-04-26 11:25:21', '2019-04-30 14:25:37', '1');
INSERT INTO `tb_user` VALUES ('43', '005909', '21232f297a57a5a743894a0e4a801fc3', '长大', '2019-04-26 11:26:03', '2019-04-30 21:35:52', '1');
INSERT INTO `tb_user` VALUES ('47', '674891', '0de2443a74cb964ea170ab6de12e6914', '张晓孙', '2019-04-30 15:51:11', '2019-04-30 15:51:11', '1');

-- ----------------------------
-- Table structure for tb_user_family
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_family`;
CREATE TABLE `tb_user_family` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `appellation` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '称谓',
  `user_family_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '姓名',
  `age` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '年龄',
  `politics_status` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '政治面貌',
  `work_unit_and_position` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '工作单位及职务',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tb_user_family
-- ----------------------------
INSERT INTO `tb_user_family` VALUES ('1', '43', '1', '1', '11', '11', '11', '2019-04-25 11:20:04', '2019-04-18 11:20:09');
INSERT INTO `tb_user_family` VALUES ('2', '43', '1', '1', '11', '11', '11', '2019-04-25 11:20:04', '2019-04-18 11:20:09');
INSERT INTO `tb_user_family` VALUES ('7', '40', '1', '1', '1', '1', '1', '2019-05-02 14:14:29', '2019-05-02 18:17:56');
INSERT INTO `tb_user_family` VALUES ('8', '40', 'qw', 'qw', 'wq', 'wq', 'wq', '2019-05-02 14:15:37', '2019-05-02 14:15:37');

-- ----------------------------
-- Table structure for tb_user_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_info`;
CREATE TABLE `tb_user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(255) NOT NULL COMMENT '用户工号',
  `avatar` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '照片',
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户姓名',
  `gender` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '性别',
  `date_of_birth` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '出生年月',
  `nation` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '民族',
  `native_place` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '籍贯',
  `place_of_birth` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '出生地',
  `date_of_join_party` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '入党时间',
  `date_of_join_work` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '参加工作时间',
  `physical_condition` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '健康状况',
  `technical_position` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '专业技术职务',
  `familiar_major_and_specialty` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '熟悉专业有何特长',
  `full_time_degree` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '全日制学历学位',
  `full_time_graduated_university_and_major` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '全日制毕业院校系及专业',
  `part_time_degree` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '在职学历学位',
  `part_time_graduated_university_and_major` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '在职毕业院校系及专业',
  `current_position` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '现任职务',
  `resume` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '简历',
  `rewards_and_punishment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '奖惩情况',
  `annual_assessment_results` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '年度考核结果',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态 1：审核通过  2：待审核  3：未通过审核',
  PRIMARY KEY (`id`,`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tb_user_info
-- ----------------------------
INSERT INTO `tb_user_info` VALUES ('10', '40', 'D:\\upload\\江浩1.png', '江浩', '男', '1', '213', '23', '1231', '1231', '123', '213', '学生2131', '学生1231', '学生', '学生', '学生', '1221321', '123', '1231', '12312', '12321', '2019-04-26 10:19:07', '2019-05-14 15:26:37', '1');
INSERT INTO `tb_user_info` VALUES ('12', '42', 'D:\\upload\\2017-07-03 191531.jpg', '张鑫x', '男', '2019.02', '汉', '重庆', '重庆', '2019.02', '2019.02', '123132', '1', '1', '1', '1', '1', '1232131', '1', '1', '1', '1', '2019-04-26 11:25:21', '2019-05-04 12:20:31', '1');
INSERT INTO `tb_user_info` VALUES ('13', '43', 'http://www.springboot.xyz:8080/pictures/我是张大壮1.png', '我是张大壮', '女', '1996年12月', '汉族', '重庆', '江北', '1998年12月', '我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮', '我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮', '1231231我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮', '我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮', '我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮', '我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮', '我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮', '我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮', '我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮', '我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮', '我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮', '我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮我是张大壮', '2019-04-26 11:26:03', '2019-05-05 12:56:34', '1');
INSERT INTO `tb_user_info` VALUES ('17', '47', '/pictures/张晓孙user3.png', '张晓孙', '男', '2019.06', 'q', 'q\'q', 'q', '2019.06', '2019.06', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', '2019-04-30 15:51:11', '2019-05-05 10:25:53', '1');
