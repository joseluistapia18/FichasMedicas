CREATE DATABASE  IF NOT EXISTS `fichas_medicas` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `fichas_medicas`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: fichas_medicas
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `area`
--

DROP TABLE IF EXISTS `area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `area` (
  `id_area` int NOT NULL AUTO_INCREMENT,
  `nombre_area` varchar(100) NOT NULL,
  `id_usuario` varchar(30) DEFAULT NULL,
  `estado` char(1) DEFAULT 'A',
  PRIMARY KEY (`id_area`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `area_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area`
--

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;
/*!40000 ALTER TABLE `area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `correo`
--

DROP TABLE IF EXISTS `correo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `correo` (
  `id_correo` int NOT NULL AUTO_INCREMENT,
  `correo` varchar(100) NOT NULL,
  `id_persona` varchar(20) DEFAULT NULL,
  `id_usuario` varchar(30) DEFAULT NULL,
  `estado` char(1) DEFAULT 'A',
  PRIMARY KEY (`id_correo`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_persona` (`id_persona`),
  CONSTRAINT `correo_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`username`),
  CONSTRAINT `correo_ibfk_2` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`cedula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `correo`
--

LOCK TABLES `correo` WRITE;
/*!40000 ALTER TABLE `correo` DISABLE KEYS */;
/*!40000 ALTER TABLE `correo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_civil`
--

DROP TABLE IF EXISTS `estado_civil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado_civil` (
  `id_estado_civil` int NOT NULL AUTO_INCREMENT,
  `nombre_estado_civil` varchar(50) NOT NULL,
  `id_usuario` varchar(30) DEFAULT NULL,
  `estado` char(1) DEFAULT 'A',
  PRIMARY KEY (`id_estado_civil`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `estado_civil_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_civil`
--

LOCK TABLES `estado_civil` WRITE;
/*!40000 ALTER TABLE `estado_civil` DISABLE KEYS */;
/*!40000 ALTER TABLE `estado_civil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ficha_medica`
--

DROP TABLE IF EXISTS `ficha_medica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ficha_medica` (
  `id_ficha_medica` int NOT NULL AUTO_INCREMENT,
  `fecha_registro` date DEFAULT NULL,
  `id_persona` varchar(20) DEFAULT NULL,
  `antecedentes_patologicos_personales` text,
  `antecedentes_patologicos_familiares` text,
  `habito` text,
  `estado_actual` text,
  `examen_fisico` text,
  `examen_complementario` text,
  `firma` varchar(100) DEFAULT NULL,
  `foto` text,
  `id_usuario` varchar(30) DEFAULT NULL,
  `estado` char(1) DEFAULT 'A',
  PRIMARY KEY (`id_ficha_medica`),
  KEY `id_persona` (`id_persona`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `ficha_medica_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`cedula`),
  CONSTRAINT `ficha_medica_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ficha_medica`
--

LOCK TABLES `ficha_medica` WRITE;
/*!40000 ALTER TABLE `ficha_medica` DISABLE KEYS */;
/*!40000 ALTER TABLE `ficha_medica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo_sanguineo`
--

DROP TABLE IF EXISTS `grupo_sanguineo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupo_sanguineo` (
  `id_grupo_sanguineo` int NOT NULL AUTO_INCREMENT,
  `nombre_grupo_sanguineo` varchar(50) NOT NULL,
  `id_usuario` varchar(30) NOT NULL,
  `estado` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id_grupo_sanguineo`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `grupo_sanguineo_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo_sanguineo`
--

LOCK TABLES `grupo_sanguineo` WRITE;
/*!40000 ALTER TABLE `grupo_sanguineo` DISABLE KEYS */;
/*!40000 ALTER TABLE `grupo_sanguineo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parte_diario`
--

DROP TABLE IF EXISTS `parte_diario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parte_diario` (
  `id_parte_diario` int NOT NULL AUTO_INCREMENT,
  `fecha_registro` date NOT NULL,
  `hora_entrada` time NOT NULL,
  `id_persona` varchar(30) NOT NULL,
  `diagnostico` text NOT NULL,
  `tratamiento` text NOT NULL,
  `obervacion` text NOT NULL,
  `permisos` text NOT NULL,
  `hora_salida` time NOT NULL,
  `id_usuario` varchar(30) NOT NULL,
  `frecuencia_cardiaca` text NOT NULL,
  `saturacion` text NOT NULL,
  `peso` double NOT NULL,
  `imc` double NOT NULL,
  `estatura` double NOT NULL,
  `estado` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id_parte_diario`),
  KEY `parte_diario_ibfk_1` (`id_persona`),
  KEY `parte_diario_ibfk_2` (`id_usuario`),
  CONSTRAINT `parte_diario_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`cedula`),
  CONSTRAINT `parte_diario_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parte_diario`
--

LOCK TABLES `parte_diario` WRITE;
/*!40000 ALTER TABLE `parte_diario` DISABLE KEYS */;
/*!40000 ALTER TABLE `parte_diario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `cedula` varchar(20) NOT NULL,
  `nombres` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `n_hijos` int NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `id_mail` int NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `id_grupo_sanguineo` int NOT NULL,
  `id_estado_civil` int NOT NULL,
  `id_area` int NOT NULL,
  `id_usuario` varchar(30) NOT NULL,
  `estado` char(1) NOT NULL DEFAULT 'A',
  `lugar_nacimiento` text NOT NULL,
  `fecha_registro` date NOT NULL,
  PRIMARY KEY (`cedula`),
  KEY `persona_ibfk_1` (`id_mail`),
  KEY `persona_ibfk_2` (`id_grupo_sanguineo`),
  KEY `persona_ibfk_3` (`id_estado_civil`),
  KEY `persona_ibfk_4` (`id_area`),
  KEY `persona_ibfk_5` (`id_usuario`),
  CONSTRAINT `persona_ibfk_1` FOREIGN KEY (`id_mail`) REFERENCES `correo` (`id_correo`),
  CONSTRAINT `persona_ibfk_2` FOREIGN KEY (`id_grupo_sanguineo`) REFERENCES `grupo_sanguineo` (`id_grupo_sanguineo`),
  CONSTRAINT `persona_ibfk_3` FOREIGN KEY (`id_estado_civil`) REFERENCES `estado_civil` (`id_estado_civil`),
  CONSTRAINT `persona_ibfk_4` FOREIGN KEY (`id_area`) REFERENCES `area` (`id_area`),
  CONSTRAINT `persona_ibfk_5` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id_rol` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `id_usuario` varchar(30) DEFAULT NULL,
  `estado` char(1) DEFAULT 'A',
  PRIMARY KEY (`id_rol`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `roles_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `username` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `apellido` varchar(100) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `id_rol` int DEFAULT NULL,
  `id_usuario_registro` varchar(30) DEFAULT NULL,
  `estado` char(1) DEFAULT 'A',
  PRIMARY KEY (`username`),
  KEY `id_rol` (`id_rol`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id_rol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-07 22:44:07
