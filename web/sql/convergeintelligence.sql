/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : convergeintelligence

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2013-12-03 00:15:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `ID` decimal(19,0) NOT NULL,
  `ARTICLE_NAME` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) DEFAULT NULL,
  `ISBN` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(45) DEFAULT NULL,
  `VERSION` varchar(45) DEFAULT NULL COMMENT '版本',
  `PUBLISHE_VENDOR` varchar(255) DEFAULT NULL COMMENT '出版商',
  `ORIGINAL_LANG` varchar(128) NOT NULL COMMENT '原著语言',
  `DOC_TYPE` varchar(128) NOT NULL COMMENT '文档类型.doc|.txt|.pdf|',
  `PUBLISH_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `AUTHOR_IDX` (`AUTHOR`) USING BTREE,
  KEY `ISBN_IDX` (`ISBN`) USING HASH,
  KEY `EMAIL_IDX` (`EMAIL`) USING BTREE,
  KEY `VERSION_IDX` (`VERSION`) USING BTREE,
  KEY `ORIGINAL_LANG_IDX` (`ORIGINAL_LANG`) USING BTREE,
  KEY `DOC_TYPE_IDX` (`DOC_TYPE`) USING BTREE,
  KEY `PUBLISH_DATE_IDX` (`PUBLISH_DATE`) USING BTREE,
  KEY `ARTICLE_NAME_IDX` (`ARTICLE_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------

-- ----------------------------
-- Table structure for chapter
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter` (
  `ID` decimal(19,0) NOT NULL,
  `CHAPTER_NO` int(11) NOT NULL,
  `CHAPTER_NAME` varchar(255) NOT NULL,
  `FK_ARTICLE_ID` decimal(19,0) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `CHAPTER_NO_IDX` (`CHAPTER_NO`) USING BTREE,
  KEY `CHAPTER_NAME_IDX` (`CHAPTER_NAME`) USING BTREE,
  KEY `FK_ARTICLE_ID_IDX` (`FK_ARTICLE_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chapter
-- ----------------------------

-- ----------------------------
-- Table structure for paragraph
-- ----------------------------
DROP TABLE IF EXISTS `paragraph`;
CREATE TABLE `paragraph` (
  `GUID` varchar(255) NOT NULL,
  `PARAGRAPH_NO` decimal(19,0) NOT NULL COMMENT '第几段',
  `FK_SECTION_GUID` varchar(255) NOT NULL,
  `FULL_PATH` varchar(1024) NOT NULL,
  PRIMARY KEY (`GUID`),
  KEY `PARAGRAPH_NO_IDX` (`PARAGRAPH_NO`) USING BTREE,
  KEY `FK_SECTION_GUID_IDX` (`FK_SECTION_GUID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paragraph
-- ----------------------------

-- ----------------------------
-- Table structure for section
-- ----------------------------
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section` (
  `GUID` varchar(255) NOT NULL,
  `SECTION_NO` int(11) NOT NULL,
  `SECTION_NAME` varchar(255) NOT NULL,
  `FK_CHAPTER_ID` decimal(19,0) NOT NULL,
  PRIMARY KEY (`GUID`),
  KEY `SECTION_NO_IDX` (`SECTION_NO`),
  KEY `SECTION_NAME_IDX` (`SECTION_NAME`),
  KEY `FK_CHAPTER_ID_IDX` (`FK_CHAPTER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of section
-- ----------------------------

-- ----------------------------
-- Table structure for translation
-- ----------------------------
DROP TABLE IF EXISTS `translation`;
CREATE TABLE `translation` (
  `GUID` varchar(255) NOT NULL,
  `FK_USER_ID` decimal(19,0) NOT NULL,
  `FK_PARAGRAPH_GUID` varchar(255) NOT NULL,
  `UP_RANK` decimal(19,0) NOT NULL DEFAULT '0',
  `DOWN_RANK` decimal(19,0) NOT NULL DEFAULT '0',
  PRIMARY KEY (`GUID`),
  KEY `FK_USER_ID_IDX` (`FK_USER_ID`) USING BTREE,
  KEY `FK_PARAGRAPH_GUID_IDX` (`FK_PARAGRAPH_GUID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of translation
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` decimal(19,0) NOT NULL,
  `NAME` varchar(45) NOT NULL,
  `DISPALY_NAME` varchar(45) DEFAULT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  `GANDER` int(11) DEFAULT NULL,
  `BIRTHDAY` date DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `NAME_IDX` (`NAME`) USING BTREE,
  KEY `GANDER_IDX` (`GANDER`) USING BTREE,
  KEY `BIRTHDAY_IDX` (`BIRTHDAY`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'tain198127', 'tain198127', '123456', '1', '2013-12-03');

-- ----------------------------
-- View structure for article_detail_view
-- ----------------------------
DROP VIEW IF EXISTS `article_detail_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER  VIEW `article_detail_view` AS SELECT
article.ID AS ARTICLE_ID,
article.ARTICLE_NAME,
article.AUTHOR,
article.ISBN,
article.EMAIL,
article.VERSION,
article.PUBLISHE_VENDOR,
article.ORIGINAL_LANG,
article.DOC_TYPE,
article.PUBLISH_DATE,
chapter.ID AS CHAPTER_ID,
chapter.CHAPTER_NO,
chapter.CHAPTER_NAME,
chapter.FK_ARTICLE_ID,
paragraph.GUID AS PARAGRAPH_GUID,
paragraph.PARAGRAPH_NO,
paragraph.FK_SECTION_GUID,
section.GUID AS SECTION_GUID,
section.SECTION_NO,
section.SECTION_NAME,
section.FK_CHAPTER_ID
FROM
	article
RIGHT OUTER JOIN chapter ON article.ID = chapter.FK_ARTICLE_ID
RIGHT OUTER JOIN section ON chapter.ID = section.FK_CHAPTER_ID
RIGHT OUTER JOIN paragraph ON section.GUID = paragraph.FK_SECTION_GUID ;
