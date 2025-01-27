-- MySQL dump 10.13  Distrib 8.0.40, for Linux (x86_64)
--
-- Host: localhost    Database: agora_db
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activity_type`
--

DROP TABLE IF EXISTS `activity_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_type`
--

LOCK TABLES `activity_type` WRITE;
/*!40000 ALTER TABLE `activity_type` DISABLE KEYS */;
INSERT INTO `activity_type` VALUES (1,'Un racconto avvincente della storia della Cascina e del suo impegno contro le mafie.','STORIA DI CASCINA CARLA E BRUNO CACCIA'),(2,'Laboratorio sulla figura di Bruno Caccia sotto il profilo istituzionale e umano.','BRUNO CACCIA'),(3,'Attività introduttiva volta a definire, conoscere e far conoscere il fenomeno mafioso.','INTRODUZIONE AL TEMA MAFIA'),(4,'Attività di approfondimento sui beni confiscati, delle leggi sulla confisca dei beni e delle novità legislative.','I BENI CONFISCATI'),(5,'Attività di approfondimento sulla presenza delle mafie sul territorio piemontese.','LE MAFIE IN PIEMONTE'),(6,'Approfondimenti sui temi della sostenibilità ambientale e della disuguaglianza con riflessioni sui problemi e la ricerca delle possibili soluzioni.','SOSTENIBILITÀ AMBIENTALE'),(7,'Riflessioni sui modelli mafiosi presentati dal cinema per comprendere il loro impatto sulla società e migliorare la comprensione critica (e su quanto siano rappresentativi della realtà oggettiva e del sentire comune).','LA MAFIA ATTRAVERSO IL CINEMA'),(8,'L\'attività mira a esplorare le varie tipologie di gioco e le loro caratteristiche e a riflettere sulla correttezza dei giochi a cui si prende parte.','IL GIOCO NON È UN AZZARDO'),(9,'Considerazioni per sensibilizzare ai rischi e alla dipendenza del gioco d\'azzardo e ai collegamenti tra mafia e gioco d\'azzardo illegale.','CHI NON GIOCA VINCE');
/*!40000 ALTER TABLE `activity_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_emails`
--

DROP TABLE IF EXISTS `admin_emails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_emails` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `admin_email` varchar(255) DEFAULT NULL,
  `no_reply_email` varchar(255) DEFAULT NULL,
  `no_reply_password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_emails`
--

LOCK TABLES `admin_emails` WRITE;
/*!40000 ALTER TABLE `admin_emails` DISABLE KEYS */;
INSERT INTO `admin_emails` VALUES (1,'testcascinacaccia@gmail.com','testcascinacaccia@gmail.com','vwiwpzvtuypoinuo');
/*!40000 ALTER TABLE `admin_emails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `age_group`
--

DROP TABLE IF EXISTS `age_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `age_group` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `max_age` int NOT NULL,
  `min_age` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `age_group`
--

LOCK TABLES `age_group` WRITE;
/*!40000 ALTER TABLE `age_group` DISABLE KEYS */;
INSERT INTO `age_group` VALUES (1,'Da 6 a 99 anni',99,6,'Fascia unica'),(2,'Da 6 a 14 anni - IL GIOCO NON È UN AZZARDO, ecc.',14,6,'6-14 anni');
/*!40000 ALTER TABLE `age_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking_duration`
--

DROP TABLE IF EXISTS `booking_duration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking_duration` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_duration`
--

LOCK TABLES `booking_duration` WRITE;
/*!40000 ALTER TABLE `booking_duration` DISABLE KEYS */;
INSERT INTO `booking_duration` VALUES (1,'Mezza giornata','1/2 giornata'),(2,'Giornata intera','1 giorno'),(3,'Due giornate','2 giorni');
/*!40000 ALTER TABLE `booking_duration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `form_booking`
--

DROP TABLE IF EXISTS `form_booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `form_booking` (
  `begin_time` date DEFAULT NULL,
  `booking_status` enum('CONFIRMED','PENDING') DEFAULT NULL,
  `end_time` date DEFAULT NULL,
  `guides_quantity` int NOT NULL,
  `participants_quantity` int NOT NULL,
  `id` bigint NOT NULL,
  `booking_duration_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8sg8qk6rqncfgktl7hx3txocp` (`booking_duration_id`),
  CONSTRAINT `FK8sg8qk6rqncfgktl7hx3txocp` FOREIGN KEY (`booking_duration_id`) REFERENCES `booking_duration` (`id`),
  CONSTRAINT `FKi44jfaekmr4hwk0w2q1bfqc0e` FOREIGN KEY (`id`) REFERENCES `form_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `form_booking`
--

LOCK TABLES `form_booking` WRITE;
/*!40000 ALTER TABLE `form_booking` DISABLE KEYS */;
INSERT INTO `form_booking` VALUES ('2023-03-10','PENDING','2023-03-15',3,15,3,1),('2024-12-30','PENDING','2024-12-30',0,0,5,1),('2024-12-30','CONFIRMED','2024-12-30',32,56,6,1);
/*!40000 ALTER TABLE `form_booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `form_info`
--

DROP TABLE IF EXISTS `form_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `form_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `additional_info` varchar(255) DEFAULT NULL,
  `association` varchar(255) DEFAULT NULL,
  `contact_date` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `form_type` enum('FORM_BOOKING','FORM_INFO','FORM_NEWSLETTER') DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `newsletter_check` enum('NO','YES') DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `unique_code` varchar(255) DEFAULT NULL,
  `age_group_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqu3g4vlyces7mw9ehe1xxuupt` (`age_group_id`),
  CONSTRAINT `FKqu3g4vlyces7mw9ehe1xxuupt` FOREIGN KEY (`age_group_id`) REFERENCES `age_group` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `form_info`
--

LOCK TABLES `form_info` WRITE;
/*!40000 ALTER TABLE `form_info` DISABLE KEYS */;
INSERT INTO `form_info` VALUES (1,'Descrizione di esempio 1','Ente 1','2023-01-01','john.doe@example.com','FORM_INFO','John','YES','1234567890','Doe',NULL,1),(2,'Descrizione di esempio 2','Ente 2','2023-02-01','jane.smith@example.com','FORM_INFO','Jane','NO','0987654321','Smith',NULL,1),(3,'Descrizione di esempio 3','Ente 3','2023-03-01','alice.johnson@example.com','FORM_BOOKING','Alice','YES','1122334455','Johnson',NULL,1),(4,'string','string','2025-01-26','test@test.it','FORM_INFO','string','NO','string','string',NULL,1),(5,'string','string','2025-01-26','test@test.it','FORM_INFO','string','YES','string','string','133726',1),(6,'Updated description','Example Association','2024-12-30','test@test.it','FORM_INFO','John','YES','1234567890','Doe','155786',1);
/*!40000 ALTER TABLE `form_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `form_info_activity_type`
--

DROP TABLE IF EXISTS `form_info_activity_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `form_info_activity_type` (
  `form_info_id` bigint NOT NULL,
  `activity_type_id` bigint NOT NULL,
  PRIMARY KEY (`form_info_id`,`activity_type_id`),
  KEY `FKrksmkowgwnroka97r93ak4cd5` (`activity_type_id`),
  CONSTRAINT `FKk0bkiyfrvw2iiwlqaqeg52fki` FOREIGN KEY (`form_info_id`) REFERENCES `form_info` (`id`),
  CONSTRAINT `FKrksmkowgwnroka97r93ak4cd5` FOREIGN KEY (`activity_type_id`) REFERENCES `activity_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `form_info_activity_type`
--

LOCK TABLES `form_info_activity_type` WRITE;
/*!40000 ALTER TABLE `form_info_activity_type` DISABLE KEYS */;
INSERT INTO `form_info_activity_type` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(2,2),(3,2),(5,2),(6,2),(3,3);
/*!40000 ALTER TABLE `form_info_activity_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `form_newsletter`
--

DROP TABLE IF EXISTS `form_newsletter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `form_newsletter` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contact_date` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `form_type` enum('FORM_BOOKING','FORM_INFO','FORM_NEWSLETTER') DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `newsletter_check` enum('NO','YES') DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `form_newsletter`
--

LOCK TABLES `form_newsletter` WRITE;
/*!40000 ALTER TABLE `form_newsletter` DISABLE KEYS */;
/*!40000 ALTER TABLE `form_newsletter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `deleted` bit(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,_binary '\0','pandolfo@pancrazio.com','Pandolfo','$2a$10$v0raMWLAh86kqEICaaEMDuL6E1Pcpq/HGHGoa.L9ptCjOMPy3J9zi','pandolfo'),(2,_binary '\0','ericazz@enigmistica.com','Eritreo Cazzulati','$2a$10$JMHw.RiSjtEDoAs0kzHkCO9z1YtsDXRvnIoZlmMh/1/2Fz9.w9phS','ericazz');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`),
  CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-27 13:17:49
