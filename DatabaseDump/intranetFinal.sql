-- MySQL dump 10.16  Distrib 10.1.29-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: intranetdb
-- ------------------------------------------------------
-- Server version	10.1.28-MariaDB

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allergen`
--

LOCK TABLES `allergen` WRITE;
/*!40000 ALTER TABLE `allergen` DISABLE KEYS */;
INSERT INTO `allergen` VALUES (5,'Mælk','Mælkeallergi, også kendt som laktoseintolerans.'),(6,'Æg','Allergi for æg.'),(7,'Fisk','Må ikke spise æg.'),(8,'Skaldyr','Skaldyrsallergi'),(9,'Nødder','Nøddeallergi, f.eks. jordnød, mandel, hasselnød etc.'),(10,'Sojabønne','Sojabønneallergi er allergi for stoffet \"Glycine max\"'),(11,'Pollen','Allergi for pollen som f.eks. græs, bynke og birk.'),(12,'Parfume ','Parfumeallergi.');
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
  PRIMARY KEY (`child_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `child`
--

LOCK TABLES `child` WRITE;
/*!40000 ALTER TABLE `child` DISABLE KEYS */;
INSERT INTO `child` VALUES (4,'Drew','Berrymore','1975-02-22','Culverstreet 1, Culver City, U.S.'),(5,'Britney','Spears','1981-12-02','McCombstreet 1, McComb, U.S.'),(6,'Justin','Bieber','1994-03-01','Londonstreet 1, London, Canada'),(7,'Jaden','Smith','1998-07-08','Malibustreet 1, Malibu, U.S.'),(8,'Macaulay','Culkin ','1980-08-26','Manhattanstreet 1, Manhattan, U.S.'),(9,'Anna','Chlumsky','1980-12-03','Chicagostreet 1, Chicaco, U.S.'),(10,'Michael ','Jackson','1958-08-29','Garystreet 1, Gary, U.S.'),(11,'Mary-Kate','Olsen ','1986-06-13','Sherman Oaks\'street, Sherman Oaks, U.S.'),(12,'Ashley','Olsen','1986-06-13','Sherman Oaks\'street, Sherman Oaks, U.S.'),(13,'Jaleel','White','1976-11-27','Culverstreet 2, Culver City, U.S.'),(14,'Jonathan','Lipnicki','1990-10-22','Westlake Villagestreet, Westlake Village, U.S.'),(15,'Haley','Osment','1988-04-10','Los Angelesstreet, Los Angeles, U.S.'),(16,'Jake','Lloyd','1989-03-05','Fort Collins\'street, Fort Collins, U.S.');
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
/*!40000 ALTER TABLE `child_allergen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `child_wing`
--

DROP TABLE IF EXISTS `child_wing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `child_wing` (
  `fk_child_id` int(11) NOT NULL,
  `fk_wing_id` int(11) NOT NULL,
  PRIMARY KEY (`fk_child_id`,`fk_wing_id`),
  UNIQUE KEY `child_wing_fk_child_id_uindex` (`fk_child_id`),
  KEY `child_wing_wing_wing_id_fk` (`fk_wing_id`),
  CONSTRAINT `child_wing_child_child_id_fk` FOREIGN KEY (`fk_child_id`) REFERENCES `child` (`child_id`),
  CONSTRAINT `child_wing_wing_wing_id_fk` FOREIGN KEY (`fk_wing_id`) REFERENCES `wing` (`wing_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `child_wing`
--

LOCK TABLES `child_wing` WRITE;
/*!40000 ALTER TABLE `child_wing` DISABLE KEYS */;
/*!40000 ALTER TABLE `child_wing` ENABLE KEYS */;
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
  UNIQUE KEY `employee_user_group_fk_employee_user_id_uindex` (`fk_employee_user_id`),
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
INSERT INTO `employee_user_group` VALUES (327,3),(330,4),(331,5),(337,4),(338,5);
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
  UNIQUE KEY `employee_user_wing_fk_user_id_uindex` (`fk_user_id`),
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group`
--

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
INSERT INTO `group` VALUES (3,'Admin','Administrator af institutionen'),(4,'Medarbejdere','Hverdags ansatte'),(5,'Medhjælpere','Ansatter der hjælper til (ingen uddannelse)');
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
INSERT INTO `parent_user_child` VALUES (332,10),(333,4),(334,5),(335,5),(336,6),(339,7),(340,7);
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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (7,'Create employee','Adds a new employee to the system.'),(8,'Read employee','Shows infomation of a specific employee.'),(9,'Read all employees','Shows all existing employees. '),(10,'Update employee','Option to update information on a specific employee.'),(11,'Delete employee','Deletes specific employee.'),(12,'Create parent','Adds a new parent to the system.'),(13,'Read parent','Shows infomation of a specific parent.'),(14,'Read all parents','Shows all existing parents. '),(15,'Update parent','Option to update information on a specific parent.'),(16,'Delete parent','Deletes specific parent.'),(17,'Create wing','Adds a new wing to the System.'),(18,'Read wing','Shows infomation of a specific wing.'),(19,'Read all wings','Shows all existing wings.'),(20,'Update wing','Option to update information on a specific wing.'),(21,'Delete wing','Deletes specific wing.'),(22,'Create child','Adds a new child to the System.'),(23,'Read child','Shows infomation of a specific child.'),(24,'Read all children','Shows all existing child.'),(25,'Update child','Option to update information on a specific child.'),(26,'Delete child','Deletes specific child.'),(27,'Edit settings','Edit settings'),(28,'Edit permissions','Edit permissions'),(29,'Edit own permissions','Edit own permissions');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=341 DEFAULT CHARSET=latin1 COMMENT='passwords are stored in SHA1 format.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (327,'','phillip@plmedia.dk','Phillip','Nissen','ROLE_EMP',0),(330,'$2a$10$TIRU/yFRXKb3MIKoWwk.6er3H2km/OyVhXYyWGJCE1Zoohi8tq2hS','thebuther@plainfield.net','Ed','Gein','ROLE_EMP',1),(331,'$2a$10$lI7bkJOle6QK22lI2TcdUujhu1sHBU0f5o7LKOfPwikux8mveXA72','Cannibal@Milwaukee-post.net','Jeffrey','Dahmer','ROLE_EMP',1),(332,'$2a$10$c9qZykFU2d30jAOBFmCfne0zg3SRdIvL.qc0s/XQ0oWLZ7hy8ZFQS','joejack5@aol.com','Joe','Jackson','ROLE_PAR',1),(333,'$2a$10$UfdCvDmRQ6FNmaS30AgUzuSvbbqR1C9qMzlzlSLb/m.S455utQkte','john@Barrymore.com','John ','Barrymore','ROLE_PAR',1),(334,'$2a$10$Vr7BpkWJL3Y4rQoErWKIBudiJyInnus.AlaVVgXGA5gUmaeTqDH92','Jman@spears-holdnings.net','James','Spears','ROLE_PAR',1),(335,'$2a$10$khF.myu5Vt/XDqSJm3YiOuHhxpuzVjsFxKcBhK5cK4KvMhb/80FHG','Lynne@spears-holding.com','Lynne','Spears','ROLE_PAR',1),(336,'$2a$10$s.eSEMJWkcxD.5MKA8ekaOeIaZzL5qezX/UECfkpS.o5..4YH46Qi','pmallette@hotmail.com','Patricia','Mallette','ROLE_PAR',1),(337,'$2a$10$YrtrYt7tW7gvu1YuNnTOzejxoyggsROr3k3xctg.aap9zN.KFWDP6','rolf_miller@outlook.com','Ted','Bundy','ROLE_EMP',1),(338,'$2a$10$2URDxGd7.zG49SuvJ2VcjOK1q10vT8ha0IUv9Q82oyCPf4h7g017W','SugarZero@hotmail.com','Aileen','Wuornos','ROLE_EMP',1),(339,'$2a$10$rFK8JRC.9TNyEF/evmduleHS5cfDvtgj8Pa7.s2Z/qNn4ZJZSeU6e','AgentJ@MIB.com','Will','Smith','ROLE_PAR',1),(340,'$2a$10$QRRIh3Eh7eqBBlhx./fhDO6ObPVSZJ6MjTsYheuHluNVf//kruEKy',' pinkett@blm.com','Jada','Smith','ROLE_PAR',1);
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
INSERT INTO `user_permission` VALUES (327,7),(327,8),(327,9),(327,10),(327,11),(327,12),(327,13),(327,14),(327,15),(327,16),(327,17),(327,18),(327,19),(327,20),(327,21),(327,22),(327,23),(327,24),(327,25),(327,26),(327,27),(327,28),(327,29),(330,7),(330,8),(330,9),(330,10),(330,11),(330,12),(330,13),(330,14),(330,15),(330,16),(330,17),(330,18),(330,19),(330,20),(330,21),(330,22),(330,23),(330,24),(330,25),(330,26),(330,27),(330,28),(330,29),(331,27),(338,8);
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wing`
--

LOCK TABLES `wing` WRITE;
/*!40000 ALTER TABLE `wing` DISABLE KEYS */;
INSERT INTO `wing` VALUES (4,'Blå','Himmelbørnene'),(5,'Grøn','Naturbørnene'),(6,'Gul','Solensbørn'),(7,'Rød','Kærlighedensbørn');
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

-- Dump completed on 2017-12-13 17:10:31
