-- MariaDB dump 10.19  Distrib 10.4.28-MariaDB, for osx10.10 (x86_64)
--
-- Host: 127.0.0.1    Database: AustralianOpen2k26
-- ------------------------------------------------------
-- Server version	10.4.28-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Prijave`
--

DROP TABLE IF EXISTS `Prijave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Prijave` (
  `prijavaId` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(100) NOT NULL,
  `prezime` varchar(100) NOT NULL,
  `jmbg` varchar(100) NOT NULL,
  `DatumVolontiranja` date NOT NULL,
  `smena` varchar(100) NOT NULL,
  `pozicija` varchar(100) NOT NULL,
  `DatumPrijave` date NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`prijavaId`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Prijave`
--

LOCK TABLES `Prijave` WRITE;
/*!40000 ALTER TABLE `Prijave` DISABLE KEYS */;
INSERT INTO `Prijave` VALUES (2,'Marko','Markovic','1212003987765','2026-01-28','Vecernja','Posluga u VIP zoni','2026-01-22','marko@gmail.com'),(5,'Marko','Markovic','1212003987765','2026-01-27','Vecernja','Osoblje za informacije','2026-01-25','marko@gmail.com'),(7,'Janko','Jakovic','1234567890123','2026-01-27','Jutarnja','Redar','2026-01-20','janko@janko.rs'),(9,'Milos','Milosevic','0099123456789','2026-02-05','Jutarnja','Redar','2026-01-27','milos@gmail.com'),(10,'Marko','Markovic','1212003987765','2026-02-02','Popodnevna','Osoblje za informacije','2026-01-27','marko@gmail.com'),(12,'Marko','Markovic','1212003987765','2026-02-01','Popodnevna','Osoblje za informacije','2026-01-27','marko@gmail.com'),(13,'Marko','Markovic','1212003987765','2026-01-30','Popodnevna','Posluga u VIP zoni','2026-01-27','marko@gmail.com'),(14,'Marko','Markovic','1212003987765','2026-01-31','Popodnevna','Posluga u VIP zoni','2026-01-27','marko@gmail.com'),(15,'Marko','Markovic','1212003987765','2026-01-29','Popodnevna','Osoblje za informacije','2026-01-27','marko@gmail.com'),(17,'Sima','SImic','1234567890123','2026-02-05','Vecernja','Mediji','2026-01-27','sima@sima.rs'),(19,'Mika','Mikic','1009980312232','2026-02-04','Popodnevna','Redar','2026-01-30','mika@mikic.rs'),(20,'Marko','Markovic','1212003987765','2026-02-05','Vecernja','Osoblje za informacije','2026-02-02','marko@gmail.com'),(22,'Primer1','Primer1','1234567890123','2026-02-04','Popodnevna','Redar','2026-02-03','primer@gmail.com');
/*!40000 ALTER TABLE `Prijave` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `jmbg` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `ime` varchar(100) NOT NULL,
  `prezime` varchar(100) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (2,'marko','1234','1212003987765','marko@gmail.com','Marko','Markovic'),(4,'milanm','1234','1234567890123','milan@milan.rs','Milan','Milanovic'),(5,'sima','12345','1234567891255','sima@gmai.rs','Sima','Simic'),(6,'noviUser123','user123','0901998276653','novi.user@123.rs','Novi','User');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-04 15:47:55
