-- MySQL dump 10.13  Distrib 5.7.20, for Win64 (x86_64)
--
-- Host: localhost    Database: intranetdb
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `allergen`
--

DROP TABLE IF EXISTS `allergen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `allergen` (
  `allergen_id` int(11) NOT NULL AUTO_INCREMENT,
  `allergen_name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `allergen_description` varchar(120) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`allergen_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allergen`
--

LOCK TABLES `allergen` WRITE;
/*!40000 ALTER TABLE `allergen` DISABLE KEYS */;
INSERT INTO `allergen` VALUES (1,'Nikkel','Info om hvad man skal gøre ved det!'),(2,'Nødder','Info om hvad man skal gøre ved det!'),(3,'Human resources','Info om hvad man skal gøre ved det!'),(4,'Ingen Allergener','Barnet har ingen allergener.');
/*!40000 ALTER TABLE `allergen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `child`
--

DROP TABLE IF EXISTS `child`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `child` (
  `child_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(30) CHARACTER SET utf8 NOT NULL,
  `last_name` varchar(30) CHARACTER SET utf8 NOT NULL,
  `birthday` date NOT NULL,
  `address` varchar(200) CHARACTER SET utf8 NOT NULL,
  `fk_wing_id` int(11) NOT NULL,
  PRIMARY KEY (`child_id`),
  KEY `child_wing_wing_id_fk` (`fk_wing_id`),
  CONSTRAINT `child_wing_wing_id_fk` FOREIGN KEY (`fk_wing_id`) REFERENCES `wing` (`wing_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COMMENT='nap is incomplete! ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `child`
--

LOCK TABLES `child` WRITE;
/*!40000 ALTER TABLE `child` DISABLE KEYS */;
INSERT INTO `child` VALUES (1,'Claus','Bove','2015-11-01','Hjemmehosdigselv 12, 2 th, 2500',1),(2,'David','Ema','1890-11-01','Hjemmehosdigselv 12, 2 th, 2500',3),(3,'Faisal','Jarkass','2017-12-05','Hjemmehosdigselv 12, 2 th, 2500',2);
/*!40000 ALTER TABLE `child` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `child_allergen`
--

DROP TABLE IF EXISTS `child_allergen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `child_allergen` (
  `fk_child_id` int(11) NOT NULL,
  `fk_allergen_id` int(11) NOT NULL,
  PRIMARY KEY (`fk_child_id`,`fk_allergen_id`),
  KEY `child_allergen_allergen_allergen_id_fk` (`fk_allergen_id`),
  CONSTRAINT `child_allergen_allergen_allergen_id_fk` FOREIGN KEY (`fk_allergen_id`) REFERENCES `allergen` (`allergen_id`),
  CONSTRAINT `child_allergen_child_child_id_fk` FOREIGN KEY (`fk_child_id`) REFERENCES `child` (`child_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `child_allergen`
--

LOCK TABLES `child_allergen` WRITE;
/*!40000 ALTER TABLE `child_allergen` DISABLE KEYS */;
INSERT INTO `child_allergen` VALUES (1,1),(2,1),(1,2),(3,4);
/*!40000 ALTER TABLE `child_allergen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `child_trusted_pickup_person`
--

DROP TABLE IF EXISTS `child_trusted_pickup_person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `child_trusted_pickup_person` (
  `fk_child_id` int(11) NOT NULL,
  `fk_trusted_pickup_person_id` int(11) NOT NULL,
  PRIMARY KEY (`fk_child_id`,`fk_trusted_pickup_person_id`),
  KEY `child_trusted_pickup_person_id_fk` (`fk_trusted_pickup_person_id`),
  CONSTRAINT `child_trusted_pickup_person_child_child_id_fk` FOREIGN KEY (`fk_child_id`) REFERENCES `child` (`child_id`),
  CONSTRAINT `child_trusted_pickup_person_id_fk` FOREIGN KEY (`fk_trusted_pickup_person_id`) REFERENCES `trusted_pickup_person` (`trusted_pickup_person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `child_trusted_pickup_person`
--

LOCK TABLES `child_trusted_pickup_person` WRITE;
/*!40000 ALTER TABLE `child_trusted_pickup_person` DISABLE KEYS */;
/*!40000 ALTER TABLE `child_trusted_pickup_person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_user_group`
--

DROP TABLE IF EXISTS `employee_user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_user_group` (
  `fk_employee_user_id` int(11) NOT NULL,
  `fk_group_id` int(11) NOT NULL,
  PRIMARY KEY (`fk_employee_user_id`,`fk_group_id`),
  KEY `employee_user_group_group_group_id_fk` (`fk_group_id`),
  CONSTRAINT `employee_user_group_group_group_id_fk` FOREIGN KEY (`fk_group_id`) REFERENCES `group` (`group_id`),
  CONSTRAINT `employee_user_group_user_user_id_fk` FOREIGN KEY (`fk_employee_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_user_group`
--

LOCK TABLES `employee_user_group` WRITE;
/*!40000 ALTER TABLE `employee_user_group` DISABLE KEYS */;
INSERT INTO `employee_user_group` VALUES (322,1),(323,2);
/*!40000 ALTER TABLE `employee_user_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_user_wing`
--

DROP TABLE IF EXISTS `employee_user_wing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_user_wing` (
  `fk_user_id` int(11) NOT NULL,
  `fk_wing_id` int(11) NOT NULL,
  PRIMARY KEY (`fk_user_id`,`fk_wing_id`),
  KEY `employee_user_wing_wing_wing_id_fk` (`fk_wing_id`),
  CONSTRAINT `employee_user_wing_user_user_id_fk` FOREIGN KEY (`fk_user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `employee_user_wing_wing_wing_id_fk` FOREIGN KEY (`fk_wing_id`) REFERENCES `wing` (`wing_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_user_wing`
--

LOCK TABLES `employee_user_wing` WRITE;
/*!40000 ALTER TABLE `employee_user_wing` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_user_wing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(25) CHARACTER SET utf8 NOT NULL,
  `group_description` varchar(120) NOT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group`
--

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
INSERT INTO `group` VALUES (1,'testGroup1','Dette er en test gruppe med test mennesker i!'),(2,'testGroup2','Dette er en test gruppe med test mennesker i!');
/*!40000 ALTER TABLE `group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `other_info`
--

DROP TABLE IF EXISTS `other_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `other_info` (
  `fk_child_id` int(11) NOT NULL,
  `info_title` varchar(30) CHARACTER SET utf8 NOT NULL,
  `info_body` varchar(500) CHARACTER SET utf8 NOT NULL,
  KEY `other_info_child_child_id_fk` (`fk_child_id`),
  CONSTRAINT `other_info_child_child_id_fk` FOREIGN KEY (`fk_child_id`) REFERENCES `child` (`child_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `other_info`
--

LOCK TABLES `other_info` WRITE;
/*!40000 ALTER TABLE `other_info` DISABLE KEYS */;
INSERT INTO `other_info` VALUES (1,'testinfo','Other child specifik infomation goes here. A child can have n amount of \'other_info\' entities connected.');
/*!40000 ALTER TABLE `other_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parent_user_child`
--

DROP TABLE IF EXISTS `parent_user_child`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parent_user_child` (
  `fk_parent_user_id` int(11) NOT NULL,
  `fk_child_id` int(11) NOT NULL,
  PRIMARY KEY (`fk_parent_user_id`,`fk_child_id`),
  KEY `parent_user_child_child_child_id_fk` (`fk_child_id`),
  CONSTRAINT `parent_user_child_child_child_id_fk` FOREIGN KEY (`fk_child_id`) REFERENCES `child` (`child_id`),
  CONSTRAINT `parent_user_child_user_user_id_fk` FOREIGN KEY (`fk_parent_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Many to many connections from users with parent type to child';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parent_user_child`
--

LOCK TABLES `parent_user_child` WRITE;
/*!40000 ALTER TABLE `parent_user_child` DISABLE KEYS */;
INSERT INTO `parent_user_child` VALUES (322,1),(323,1),(325,1),(322,2),(323,2),(325,2);
/*!40000 ALTER TABLE `parent_user_child` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(40) CHARACTER SET utf8 NOT NULL,
  `permission_description` varchar(120) NOT NULL,
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (7,'Create employee','Adds a new employee to the system.'),(8,'Read employee','Shows infomation of a specific employee.'),(9,'Read all employees','Shows all existing employees. '),(10,'Update employee','Option to update information on a specific employee.'),(11,'Delete employee','Deletes specific employee.'),(12,'Create parent','Adds a new parent to the system.'),(13,'Read parent','Shows infomation of a specific parent.'),(14,'Read all parents','Shows all existing parents. '),(15,'Update parent','Option to update information on a specific parent.'),(16,'Delete parent','Deletes specific parent.'),(17,'Create wing','Adds a new wing to the System.'),(18,'Read wing','Shows infomation of a specific wing.'),(19,'Read all wings','Shows all existing wings.'),(20,'Update wing','Option to update information on a specific wing.'),(21,'Delete wing','Deletes specific wing.'),(22,'Create child','Adds a new child to the System.'),(23,'Read child','Shows infomation of a specific child.'),(24,'Read all children','Shows all existing child.'),(25,'Update child','Option to update information on a specific child.'),(26,'Delete child','Deletes specific child.');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trusted_pickup_person`
--

DROP TABLE IF EXISTS `trusted_pickup_person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trusted_pickup_person` (
  `trusted_pickup_person_id` int(11) NOT NULL AUTO_INCREMENT,
  `trusted_pickup_person_name` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`trusted_pickup_person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trusted_pickup_person`
--

LOCK TABLES `trusted_pickup_person` WRITE;
/*!40000 ALTER TABLE `trusted_pickup_person` DISABLE KEYS */;
/*!40000 ALTER TABLE `trusted_pickup_person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(60) NOT NULL,
  `user_email` varchar(50) CHARACTER SET utf8 NOT NULL,
  `first_name` varchar(30) CHARACTER SET utf8 NOT NULL,
  `last_name` varchar(30) CHARACTER SET utf8 NOT NULL,
  `type` varchar(10) CHARACTER SET utf8 NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=327 DEFAULT CHARSET=latin1 COMMENT='passwords are stored in SHA1 format.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (322,'$2a$10$w2c1TdHiE/NJqpmnNIIRAufElt4ql0hOtJ8GZHTj9MXBeTgJXr6YC','jonas@holm.tech','Jonas','Holm','ROLE_EMP',1),(323,'$2a$10$VzkWw169lMgS98RNpSTTwu9.LszTBJMUN/a5vQ3l9E0jMx9EgmI/6','admin@test.dk','Koden er 123','Koden er 123','ROLE_EMP',1),(325,'$2a$10$wsnx/.FZx9Sy1Vl3y/lZE.70ceV/ebx1PWu2Nt92DD2yxUHjaJmX.','noob','Koden er 123','Koden er 123','ROLE_PAR',1),(326,'$2a$10$x3QYddcYaS0IVnlJnQJ/ruqnbbqV3DSfsZze/RSa.PdFZ2F0zz4FG','boon','Phillip','Lind','ROLE_PAR',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_permission`
--

DROP TABLE IF EXISTS `user_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_permission` (
  `fk_user_id` int(11) NOT NULL,
  `fk_permission_id` int(11) NOT NULL,
  PRIMARY KEY (`fk_user_id`,`fk_permission_id`),
  KEY `user_permission_permission_permission_id_fk` (`fk_permission_id`),
  CONSTRAINT `user_permission_permission_permission_id_fk` FOREIGN KEY (`fk_permission_id`) REFERENCES `permission` (`permission_id`),
  CONSTRAINT `user_permission_user_user_id_fk` FOREIGN KEY (`fk_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_permission`
--

LOCK TABLES `user_permission` WRITE;
/*!40000 ALTER TABLE `user_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wing`
--

DROP TABLE IF EXISTS `wing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wing` (
  `wing_id` int(11) NOT NULL AUTO_INCREMENT,
  `wing_name` varchar(25) CHARACTER SET utf8 NOT NULL,
  `wing_description` varchar(120) NOT NULL,
  PRIMARY KEY (`wing_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wing`
--

LOCK TABLES `wing` WRITE;
/*!40000 ALTER TABLE `wing` DISABLE KEYS */;
INSERT INTO `wing` VALUES (1,'Blå','Dette er en test Wing!'),(2,'Grøn','Dette er en test wing!'),(3,'Bæbrun','test');
/*!40000 ALTER TABLE `wing` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-08 14:47:30
