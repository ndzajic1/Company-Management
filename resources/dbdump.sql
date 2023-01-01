-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: sql.freedb.tech    Database: freedb_rpr_db
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Departments`
--

DROP TABLE IF EXISTS `Departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Departments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `department_name` varchar(45) NOT NULL,
  `location` varchar(45) DEFAULT NULL,
  `manager_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idDepartments_UNIQUE` (`id`),
  KEY `manager_idx` (`manager_id`),
  CONSTRAINT `manager` FOREIGN KEY (`manager_id`) REFERENCES `Employees` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Departments`
--

LOCK TABLES `Departments` WRITE;
/*!40000 ALTER TABLE `Departments` DISABLE KEYS */;
INSERT INTO `Departments` VALUES (1,'Accounting','Seattle',NULL),(2,'Finance','Oxford',NULL),(3,'Sales','Tokyo',NULL),(4,'IT','Southlake',NULL),(5,'Shipping','San Francisco',NULL),(6,'Marketing','Seattle',NULL),(7,'Executive','Seattle',NULL),(8,'Administration','Seattle',NULL);
/*!40000 ALTER TABLE `Departments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employees`
--

DROP TABLE IF EXISTS `Employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Employees` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `hire_date` date NOT NULL,
  `department_id` int DEFAULT NULL,
  `job_id` int DEFAULT NULL,
  `salary` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `dept_fk_idx` (`department_id`),
  KEY `job_fk_idx` (`job_id`),
  CONSTRAINT `dept_fk` FOREIGN KEY (`department_id`) REFERENCES `Departments` (`id`),
  CONSTRAINT `job_fk` FOREIGN KEY (`job_id`) REFERENCES `Jobs` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employees`
--

LOCK TABLES `Employees` WRITE;
/*!40000 ALTER TABLE `Employees` DISABLE KEYS */;
INSERT INTO `Employees` VALUES (1,'Jennifer','Whalen','2003-09-17',8,3,4400.00),(2,'Shelley','Higgins','2003-01-17',1,6,4400.00),(3,'William','Gietz','2004-03-22',1,7,8300.00),(4,'Donald','Trump','2002-12-01',7,1,24000.00),(5,'Neena','Kochhar','2003-04-04',7,2,17000.00),(6,'Lex','De Haan','2004-05-05',7,2,17000.00),(7,'Nancy','Greenberg','2005-09-01',2,4,17000.00),(8,'Daniel','Faviet','2004-09-02',2,5,9000.00),(9,'John','Chen','2004-02-02',2,5,8000.00),(10,'Ismael','Sciarra','2005-07-01',2,5,7800.00);
/*!40000 ALTER TABLE `Employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Jobs`
--

DROP TABLE IF EXISTS `Jobs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Jobs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `job_title` varchar(45) NOT NULL,
  `min_salary` decimal(10,2) DEFAULT NULL,
  `max_salary` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Jobs`
--

LOCK TABLES `Jobs` WRITE;
/*!40000 ALTER TABLE `Jobs` DISABLE KEYS */;
INSERT INTO `Jobs` VALUES (1,'President',20080.00,40000.00),(2,'Administration Vice President',15000.00,30000.00),(3,'Administration Assistant',3000.00,6000.00),(4,'Finance Manager',8200.00,16000.00),(5,'Accountant',4200.00,9000.00),(6,'Accounting Manager',8200.00,16000.00),(7,'Public Accountant',4200.00,9000.00);
/*!40000 ALTER TABLE `Jobs` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-01 21:20:19
