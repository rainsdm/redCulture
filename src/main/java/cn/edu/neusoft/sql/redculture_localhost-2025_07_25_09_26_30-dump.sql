-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: redculture
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `announcements`
--

DROP TABLE IF EXISTS `announcements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `announcements` (
  `anno_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `content` text NOT NULL,
  `post_time` datetime NOT NULL,
  `comment` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`anno_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcements`
--

LOCK TABLES `announcements` WRITE;
/*!40000 ALTER TABLE `announcements` DISABLE KEYS */;
INSERT INTO `announcements` (`anno_id`, `title`, `content`, `post_time`, `comment`) VALUES (1,'升级维护通知','为提升系统性能、优化用户体验，并确保数据安全，[系统名称/平台名称]将于近期进行计划内升级维护。\r\n届时系统将暂停服务，请您提前做好数据备份及业务安排。','2025-06-23 00:00:00',' '),(6,'egsae','dfsaga','2025-07-07 20:56:28',''),(8,'ceag','agse','2025-07-07 20:57:28',' '),(10,'sgsggs','daghgqwetnrgbfdf','2025-07-08 11:34:43','dasfggsefd'),(12,'test35','test','2025-07-09 23:40:00','dasg');
/*!40000 ALTER TABLE `announcements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `records`
--

DROP TABLE IF EXISTS `records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `records` (
  `record_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `spot_id` int NOT NULL,
  `produce_time` datetime NOT NULL,
  `learn_note` text,
  PRIMARY KEY (`record_id`),
  KEY `user_id` (`user_id`),
  KEY `cons_spotId` (`spot_id`),
  CONSTRAINT `cons_spotId` FOREIGN KEY (`spot_id`) REFERENCES `spots` (`spot_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `records_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `records`
--

LOCK TABLES `records` WRITE;
/*!40000 ALTER TABLE `records` DISABLE KEYS */;
INSERT INTO `records` (`record_id`, `user_id`, `spot_id`, `produce_time`, `learn_note`) VALUES (1,2,1,'2025-06-23 00:00:00','好好学习，天天向上'),(3,1,9,'2025-07-02 15:55:28',NULL),(7,1,6,'2025-07-02 16:24:23',NULL),(9,1,5,'2025-07-02 16:25:05',NULL),(10,1,8,'2025-07-02 16:26:24','中山临'),(11,1,10,'2025-07-02 16:33:07','：百色起义纪念'),(12,17,10,'2025-07-04 18:27:33',NULL),(21,17,3,'2025-07-09 13:39:07','dasdfg'),(24,1,6,'2025-07-12 17:34:59',NULL),(25,1,1,'2025-07-12 17:35:25','中共一大纪念馆');
/*!40000 ALTER TABLE `records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spots`
--

DROP TABLE IF EXISTS `spots`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spots` (
  `spot_id` int NOT NULL AUTO_INCREMENT,
  `spot_name` varchar(20) NOT NULL,
  `location` varchar(20) NOT NULL,
  `history` text NOT NULL,
  PRIMARY KEY (`spot_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spots`
--

LOCK TABLES `spots` WRITE;
/*!40000 ALTER TABLE `spots` DISABLE KEYS */;
INSERT INTO `spots` (`spot_id`, `spot_name`, `location`, `history`) VALUES (1,'中共一大会址纪念馆','上海市黄浦区','中国共产党第一次全国代表大会在此召开，标志着中国共产党的诞生，是中国革命红色基因的发源地。'),(2,'嘉兴南湖红船','浙江省嘉兴','中共一大最后一天的会议转移到南湖红船上举行，宣告了中国共产党的诞生，是中国革命的原点象征。'),(3,'井冈山革命根据地','江西井冈山','毛泽东、朱德在此创建第一个农村革命根据地，开辟“农村包围城市”的革命道路，被誉为“中国革命的摇篮”。'),(4,'遵义会议会址','贵州省遵义市','遵义会议确立了毛泽东的领导地位，是中国共产党历史上生死攸关的转折点，标志着党从幼稚走向成熟。'),(5,'延安革命纪念馆','陕西省延安市','延安是中国革命的圣地，中国共产党在此领导革命13年，孕育了延安精神，是抗日战争和解放战争的总后方。'),(6,'西柏坡纪念馆','河北省石家庄市','西柏坡是解放战争时期党中央所在地，“新中国从这里走来”，三大战役在此指挥，提出了“两个务必”的赶考精神。'),(7,'韶山毛泽东同志纪念馆','湖南省湘潭市','韶山是毛泽东的故乡，纪念馆展示了毛泽东的生平事迹和革命历程，是感受伟人成长足迹的重要场所。'),(8,'南京中山陵','江苏省南京市','中山陵是中国近代伟大的革命先驱孙中山的纪念地，体现了对革命先驱的缅怀和对未来的激励。'),(9,'南昌八一起义纪念馆','江西省南昌市','南昌起义打响武装反抗国民党反动派的第一枪，标志着中国共产党独立领导革命战争、创建人民军队的开端。'),(10,'百色起义纪念馆','广西壮族自治区百色市','百色起义是邓小平同志领导的起义，创建了右江革命根据地，是少数民族地区革命斗争的光辉典范。');
/*!40000 ALTER TABLE `spots` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `spots_visit_num`
--

DROP TABLE IF EXISTS `spots_visit_num`;
/*!50001 DROP VIEW IF EXISTS `spots_visit_num`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `spots_visit_num` AS SELECT 
 1 AS `spot_id`,
 1 AS `spot_name`,
 1 AS `visit_num`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `role` int DEFAULT '1',
  `study_points` int DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`user_id`, `username`, `password`, `role`, `study_points`) VALUES (1,'张三','zhangsan',1,34),(2,'李四','lisi',1,0),(3,'王五','wangwu',1,0),(4,'赵六','zhaoliu',1,0),(5,'钱七','qianqi',1,0),(6,'孙八','sunba',1,0),(7,'周九','zhoujiu',1,0),(8,'吴十','wushi',1,0),(9,'zhangsan','123456',0,0),(11,'张四','zhangsi',1,0),(13,'dsf','101',1,0),(15,'a16','11133',1,0),(17,'ccc','test',1,25),(18,'admin','admin5559680',0,0),(19,'zhoujjj','dddfa',1,0),(20,'abcd','123456',1,0),(29,'aaa','aaa',1,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'redculture'
--

--
-- Final view structure for view `spots_visit_num`
--

/*!50001 DROP VIEW IF EXISTS `spots_visit_num`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `spots_visit_num` AS select `s`.`spot_id` AS `spot_id`,`s`.`spot_name` AS `spot_name`,count(0) AS `visit_num` from (`spots` `s` join `records` `r` on((`s`.`spot_id` = `r`.`spot_id`))) group by `s`.`spot_id` order by `visit_num` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-25  9:26:30
