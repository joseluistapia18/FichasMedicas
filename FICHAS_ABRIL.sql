CREATE DATABASE  IF NOT EXISTS `fichas_medicas_desarrollo` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `fichas_medicas_desarrollo`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: fichas_medicas_desarrollo
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
  `id_usuario` varchar(100) DEFAULT NULL,
  `estado` char(1) DEFAULT 'A',
  PRIMARY KEY (`id_area`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `area_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area`
--

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;
INSERT INTO `area` VALUES (1,'INFORMÁTICA','SOFIA24','A'),(2,'MATEMÁTICAS','SOFIA24','A'),(3,'INGLÉS','SOFIA24','A'),(4,'LENGUAJE','SOFIA24','A'),(5,'EDUCACIÓN FÍSICA','SOFIA24','A'),(6,'CIENCIAS NATURALES','SOFIA24','A'),(7,'ESTUDIOS SOCIALES','SOFIA24','A'),(8,'INSPECCIÓN','SOFIA24','A'),(9,'ECA','SOFIA24','A'),(10,'SERVICIOS GENERALES','SOFIA24','A'),(11,'COLECTURIA','SOFIA24','A'),(12,'RECTORADO','SOFIA24','A'),(13,'VICERECTORADO','SOFIA24','A'),(14,'SEGURIDAD','SOFIA24','A');
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
  `id_usuario` varchar(100) DEFAULT NULL,
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
  `id_usuario` varchar(100) DEFAULT NULL,
  `estado` char(1) DEFAULT 'A',
  PRIMARY KEY (`id_estado_civil`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `estado_civil_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_civil`
--

LOCK TABLES `estado_civil` WRITE;
/*!40000 ALTER TABLE `estado_civil` DISABLE KEYS */;
INSERT INTO `estado_civil` VALUES (1,'CASADO','SOFIA24','A'),(2,'DIVORCIADO','SOFIA24','A'),(3,'SOLTERO','SOFIA24','A'),(4,'UNION LIBRE','SOFIA24','A'),(5,'VIUDO','SOFIA24','A');
/*!40000 ALTER TABLE `estado_civil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examen`
--

DROP TABLE IF EXISTS `examen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `examen` (
  `id_examen` int NOT NULL AUTO_INCREMENT,
  `id_persona` varchar(20) NOT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  `frecuencia_cardiaca` int DEFAULT NULL,
  `sistolica` int DEFAULT NULL,
  `diastolica` int DEFAULT NULL,
  `saturacion` int DEFAULT NULL,
  `peso_kg` double DEFAULT NULL,
  `estatura_cm` double DEFAULT NULL,
  `temperatura` double DEFAULT NULL,
  `imc` double DEFAULT NULL,
  `estado_actual` text,
  `habitos` text,
  `estado` varchar(1) DEFAULT NULL,
  `id_ficha_medica` int DEFAULT NULL,
  `condiciones_fisicas` text,
  PRIMARY KEY (`id_examen`),
  KEY `id_ficha_medica` (`id_ficha_medica`),
  CONSTRAINT `examen_ibfk_1` FOREIGN KEY (`id_ficha_medica`) REFERENCES `ficha_medica` (`id_ficha_medica`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examen`
--

LOCK TABLES `examen` WRITE;
/*!40000 ALTER TABLE `examen` DISABLE KEYS */;
INSERT INTO `examen` VALUES (1,'1234567890','2025-02-17 00:00:00',90,120,80,99,75,1.4,35,38.27,'BUENO',NULL,'A',NULL,NULL),(2,'4543534534','2025-02-24 00:00:00',100,120,89,80,70,1.69,37,38.27,'REGULAR',NULL,'A',NULL,NULL),(3,'8767675756','2025-02-24 00:00:00',75,120,85,75,82,1.65,37,30.12,'ESTA MUY BIEN','COME MUCHO','A',NULL,NULL),(4,'5654534232','2025-03-15 00:00:00',45,120,90,56,45,1.89,39,12.6,'xxxxxxxxxxxxxxxxxxxxxxxxxx','fffffffffffffffffffffffffffffffffffffffffffffffff','A',NULL,NULL),(5,'2322222222','2025-04-03 00:00:00',34,34,566,34,34,1.6,35,13.28,'MMMMMMMMMMMMMMMMMMMMMM','MMMMMMMMMMMMMMMMMMMMMMM','A',9,NULL),(6,'7656646456','2025-04-03 00:00:00',45,3,5,34,45,1.7,34,15.57,'XXXXXXXXXXXXXXXXXXXXXXXXXXX','XXXXXXXXXXXXXXXXXXXXXXXXX','A',11,NULL),(7,'0876666666','2025-04-03 00:00:00',45,80,120,90,56,1.9,38,15.51,'BUENO','COMER, TRABAJAR','A',12,NULL),(8,'0876666666','2025-04-03 00:00:00',45,80,120,90,56,1.9,38,15.51,'BUENO','COMER, TRABAJAR','A',13,NULL),(9,'0584635333','2025-04-03 00:00:00',45,80,120,90,80,1.7,45,27.68,'BUENO','COMER GRASA','A',14,NULL),(10,'0699994444','2025-04-03 00:00:00',45,80,110,34,80,1.9,56,22.16,'NADA','NADA','A',15,NULL),(11,'1122334455','2025-04-03 00:00:00',45,80,120,45,72,1.9,36,19.94,'LOCO','LOCO','A',16,NULL),(12,'0494948989','2025-04-03 00:00:00',34,80,120,34,80,1.85,45,23.37,'NINGUNO','NINGUNO','A',18,'MAL');
/*!40000 ALTER TABLE `examen` ENABLE KEYS */;
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
  `id_usuario` varchar(100) DEFAULT NULL,
  `estado` char(1) DEFAULT 'A',
  PRIMARY KEY (`id_ficha_medica`),
  KEY `id_persona` (`id_persona`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `ficha_medica_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`cedula`),
  CONSTRAINT `ficha_medica_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ficha_medica`
--

LOCK TABLES `ficha_medica` WRITE;
/*!40000 ALTER TABLE `ficha_medica` DISABLE KEYS */;
INSERT INTO `ficha_medica` VALUES (1,'2025-02-17','1234567890','Intenso dolor en la columna vetebral','Sufre de diabetes','SOFIA24','A'),(2,'2025-02-24','4543534534','Insomio cada noche','Sufre de colesterol','JTAPIA','A'),(3,'2025-02-24','8767675756','NINGUNO','NINGUNO','JTAPIA','A'),(4,'2025-03-15','4543534533','TODO BIEN','MEJOR','JTAPIA','A'),(5,'2025-03-15','4543534533','TODO BIEN','MEJOR','JTAPIA','A'),(6,'2025-03-15','5654534232','hfddddddddddddddhhhhhhhhhhhhhhhhh','ncvvvvnnnnnnnnnnnnnnnnnnnn','JTAPIA','A'),(7,'2025-04-03','4304398093','HGGGGGGGGGGGGGGGGGGGGGGGGG','FHHHHHHHHHHHHHHHHHH','SOFIA24','A'),(8,'2025-04-03','1111111111','NADA MADA','NADA MADA','SOFIA24','A'),(9,'2025-04-03','2322222222','MMMMMMMMMMMMMMMMMMMMMMM','MMMMMMMMMMMMMMMMMMMMM','SOFIA24','A'),(10,'2025-04-03','7656646456','XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX','XXXXXXXXXXXXXXXXXXXXXXXXXXX','SOFIA24','A'),(11,'2025-04-03','7545444444','NINGUNO','NERVIOSA','SOFIA24','A'),(12,'2025-04-03','0876666666','DIABETES','ASMA','SOFIA24','A'),(13,'2025-04-03','0876666666','DIABETES','ASMA','SOFIA24','A'),(14,'2025-04-03','0584635333','NEUROTICOS','DIABETICOS','SOFIA24','A'),(15,'2025-04-03','0699994444','NADA','NADA','SOFIA24','A'),(16,'2025-04-03','1122334455','NADA','NADA','SOFIA24','A'),(17,'2025-04-03','2344345546','MALO','MALO','SOFIA24','A'),(18,'2025-04-03','0494948989','NINGUNO','NINGUNO','SOFIA24','A');
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
  `id_usuario` varchar(100) DEFAULT NULL,
  `estado` char(1) DEFAULT 'A',
  PRIMARY KEY (`id_grupo_sanguineo`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `grupo_sanguineo_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo_sanguineo`
--

LOCK TABLES `grupo_sanguineo` WRITE;
/*!40000 ALTER TABLE `grupo_sanguineo` DISABLE KEYS */;
INSERT INTO `grupo_sanguineo` VALUES (1,'A+','SOFIA24','A'),(2,'O+','SOFIA24','A'),(3,'B+','SOFIA24','A'),(4,'AB+','SOFIA24','A'),(5,'A-','SOFIA24','A'),(6,'O-','SOFIA24','A'),(7,'B-','SOFIA24','A'),(8,'AB-','SOFIA24','A');
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
  `fecha_registro` date DEFAULT NULL,
  `hora_entrada` time DEFAULT NULL,
  `id_persona` varchar(20) DEFAULT NULL,
  `diagnostico` text,
  `tratamiento` text,
  `obervacion` text,
  `permisos` text,
  `hora_salida` time DEFAULT NULL,
  `id_usuario` varchar(100) DEFAULT NULL,
  `estado` char(1) DEFAULT 'A',
  PRIMARY KEY (`id_parte_diario`),
  KEY `id_persona` (`id_persona`),
  KEY `id_usuario` (`id_usuario`),
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
  `lugar_nacimiento` text,
  `fecha_nacimiento` date DEFAULT NULL,
  `n_hijos` int DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `telefono_emergencia` varchar(20) DEFAULT NULL,
  `id_grupo_sanguineo` int DEFAULT NULL,
  `id_estado_civil` int DEFAULT NULL,
  `id_area` int DEFAULT NULL,
  `id_usuario` varchar(100) DEFAULT NULL,
  `foto` text,
  `fecha_registro` date DEFAULT NULL,
  `estado` char(1) DEFAULT 'A',
  PRIMARY KEY (`cedula`),
  KEY `id_grupo_sanguineo` (`id_grupo_sanguineo`),
  KEY `id_estado_civil` (`id_estado_civil`),
  KEY `id_area` (`id_area`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `persona_ibfk_1` FOREIGN KEY (`id_grupo_sanguineo`) REFERENCES `grupo_sanguineo` (`id_grupo_sanguineo`),
  CONSTRAINT `persona_ibfk_2` FOREIGN KEY (`id_estado_civil`) REFERENCES `estado_civil` (`id_estado_civil`),
  CONSTRAINT `persona_ibfk_3` FOREIGN KEY (`id_area`) REFERENCES `area` (`id_area`),
  CONSTRAINT `persona_ibfk_4` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES ('0494948989','RITA ATENEA','LUNA BAILON','GUAYAQUIL','1989-08-16',2,'MARTHA DE ROLDOS','454545455645','454555454544',2,1,4,'SOFIA24','C://Fichas_Medicas//img//logofoto.png','2025-04-03','A'),('0584635333','KARLA CRISTINA','MENDEZ PARRALES','MILAGRO','1980-06-18',3,'ACACIAS','111111111111','222222222222',1,2,1,'SOFIA24','C:\\\\Users\\\\Usuario\\\\Documents\\\\imagen2.jpg','2025-04-03','A'),('0699994444','DFSSSSSSSS','FSDDDDDDDDDDDDD','CUENCA','2025-04-23',1,'CEIBOS','333333333333','444444444444',1,1,4,'SOFIA24','C://Fichas_Medicas//img//logofoto.png','2025-04-03','A'),('0876666666','CARLOS IVAN','BEDOLLA PINOS','CUENCA','1980-01-16',3,'CUENCA','087666666666','697654554444',2,2,2,'SOFIA24','C://Fichas_Medicas//img//logofoto.png','2025-04-03','A'),('0877455434','LINA FRIO','PEREZ CUERO','RIOBAMBA','1988-04-03',3,'CENTRO','334090349090','450905349503',2,2,4,'JTAPIA','C://Fichas_Medicas//img//logofoto.png','2025-04-03','A'),('1111111111','SHIRLEY NADIA','TORRES VALLEJO','TULCAN','1975-07-15',1,'CEIBOS','565466666666','576777777777',3,1,3,'SOFIA24','C://Fichas_Medicas//img//logofoto.png','2025-04-03','A'),('1122334455','MARLON CARLOS','GARCIA OCAÑA','CUENCA','2025-04-09',3,'MILAGRO','444444444444','333333333333',2,2,2,'SOFIA24','C://Fichas_Medicas//img//logofoto.png','2025-04-03','A'),('1234567890','RICARDO ENRIQUE','LINARES FABRE','GUAYAQUIL','1980-03-27',5,'CEIBOS','012345123',NULL,2,3,2,'SOFIA24',NULL,'2021-12-11','A'),('1234567892','CARLOS ERNESTO','LINARES PEREZ','QUITO','1980-04-08',5,'DURAN','039393837373','333988989898',2,3,4,'JTAPIA','C://Fichas_Medicas//img//logofoto.png','2025-03-14','A'),('1234567894','JORGE FABRICIO','MARQUEZ BRAVO','PORTOVIEJO','1980-10-11',2,'PLAYA','454354543543','676576575675',2,5,5,'JTAPIA','C://Fichas_Medicas//img//logofoto.png','2025-03-14','A'),('1234567895','LAURO RICARDO','PEREZ BAQUE','GUAYAQUIL','1980-09-12',6,'MAPASINGUE','222222222222','111111111111',1,1,1,'JTAPIA','C://Fichas_Medicas//img//logofoto.png','2025-03-14','A'),('2322222222','REWEWREWR','EREWREWREWR','RRRRRRRRRRRRRRRRRRRRR','2025-04-15',3,'GGGGGGGGGGGGGGGGGGGGGGGGGGGGG','555555555555','333333333333',2,1,5,'SOFIA24','C://Fichas_Medicas//img//logofoto.png','2025-04-03','A'),('2344345546','MILTON ENRIQUE','LINO PEREZ','ESMERALDAS','1980-12-23',3,'MACHALA','566456456456','654645645645',4,5,3,'SOFIA24','C://Fichas_Medicas//img//logofoto.png','2025-04-03','A'),('4304093409','DSSADSADSAD','GFDGFDGFDG','ANADA','2025-04-09',4,'VBCXVBCXVXC','656464564565','564564564564',2,4,2,'SOFIA24','C://Fichas_Medicas//img//logofoto.png','2025-04-03','A'),('4304398093','RWEEEEEEE','EWRRRRRRRRRRRRR','REWREWREWR','2025-04-03',5,'EWRRRRRRRRRRRRRRRRRRR','345456456546','654654645645',2,3,2,'SOFIA24','C://Fichas_Medicas//img//logofoto.png','2025-04-03','A'),('4333333333','FFFFFFFFFF','FFFFFFFFFFFFFFFF','NADA','2025-04-29',3,'GGGGGGGGGGGGGGGGGGGGGG','444444444444','222222222222',2,2,3,'SOFIA24','C://Fichas_Medicas//img//logofoto.png','2025-04-03','A'),('4543534533','fgfgfdggfdg','gfdgfdgfdgdf','sadasdasdasd','2025-06-18',4,'gfdgfdg','546456546546','565465465464',3,3,2,'JTAPIA','C://Fichas_Medicas//img//logofoto.png','2025-03-15','A'),('4543534534','JORGE ENRIQUE','RICARDI VALDANO','GUAYAQUIL','1980-06-10',3,'CEIBOS','122224434343','090908878787',2,2,5,'JTAPIA','C://Fichas_Medicas//img//logofoto.png','2025-02-24','A'),('5654534232','vgxvxcvcxv','vxcvcxvxcvxc','vcvcxvxc','2025-03-12',6,'cxvvxv','546646444444','575555555555',1,2,2,'JTAPIA','C://Fichas_Medicas//img//logofoto.png','2025-03-15','A'),('7545444444','MARGARETH LIBIA','LIMA PALMA','TEXAS','2000-09-12',3,'CENTRO SUR','444444444444','333333333333',4,3,7,'SOFIA24','C://Fichas_Medicas//img//logofoto.png','2025-04-03','A'),('7656646456','5ERGDFGDFGDF','GFDGFDGDFGDFG','CEIBOS','2025-04-15',2,'TRTERTERTERT','555555555555','444444444444',3,3,2,'SOFIA24','C://Fichas_Medicas//img//logofoto.png','2025-04-03','A'),('8767675756','JOSE LUIS','VANEGAS LOPEZ','QUITO','1990-09-12',0,'DURAN','45535345345','454354354353',2,3,2,'JTAPIA','C:\\\\Fichas_Medicas\\\\img\\\\JoseTapia.PNG','2025-02-24','A'),('9078657867','KARLA LINA','VALLEJO PEREZ','CUENCA','1999-09-22',4,'AMERICAS','634435435435','075654654654',1,3,6,'JTAPIA','C://Fichas_Medicas//img//logofoto.png','2025-03-14','A');
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
  `id_usuario` varchar(100) DEFAULT NULL,
  `estado` char(1) DEFAULT 'A',
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'DESARROLLADOR','DESO24','A'),(4,'DIRECTOR PROYECTO','DESO02','A'),(5,'ADMINISTRADOR','DESO02','A'),(6,'USUARIO','DESO02','A');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `username` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `apellido` varchar(100) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `id_rol` int DEFAULT NULL,
  `id_usuario_registro` varchar(100) DEFAULT NULL,
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
INSERT INTO `usuario` VALUES ('JTAPIA','1234','JOSE','TAPIA','jose@gmail.com',4,'SOFIA24','A'),('SOFIA24','12345','SOFIA ','PULIDO','sofia@gmail.com',6,'ADMIN','A');
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

-- Dump completed on 2025-04-03  4:40:54
