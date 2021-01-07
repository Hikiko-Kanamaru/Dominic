CREATE DATABASE  IF NOT EXISTS `dominic_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dominic_db`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: dominic_db
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `actionlist`
--

DROP TABLE IF EXISTS `actionlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actionlist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '未設定',
  `URL` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `Strength` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1013 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actionlist`
--

LOCK TABLES `actionlist` WRITE;
/*!40000 ALTER TABLE `actionlist` DISABLE KEYS */;
INSERT INTO `actionlist` VALUES (0,'トランプを覚えている','',1),(1,'ベッカムヘアーにしている','',1),(2,'宇宙ステーションで艦長をしている','',1),(3,'デブってダンスしている','',1),(4,'これは芸術だとキレる','',1),(5,'強力ワカモトを宣伝している','',1),(6,'ゴンさんになっている','',1),(7,'しくじり先生で授業をしている','',1),(8,'ハッチポッチステーションに出てる','',1),(9,'スターウォーズを撮影してる','',1),(10,'TEDを紹介している','',1),(11,'声優だけはやめておけと説得してる','',1),(12,'幸楽で配膳をしている','',1),(13,'ニンテンドーダイレクトを行なっている','',1),(14,'だめだ、こりゃっとオチをつけている','',1),(15,'モノクマの声を出している','',1),(16,'EVAを撮影している','',1),(17,'ショムニで蛍光灯を変えている','',1),(18,'サラリーマンの小芝居してる','',1),(19,'隕石を止めている','',1),(20,'GHQの洗脳を解説している','',1),(21,'７人の侍を撮影している','',1),(22,'まずいサンドイッチを食べている','',1),(23,'魔法少女のコスプレをしている','',1),(24,'アウトレイジを撮影している','',1),(25,'K-1でレフェリーをしている','',1),(26,'MGSを監督してる','',1),(27,'光学迷彩で落下している','',1),(28,'ピンポンをしている','',1),(29,'ニュースを読んでいる','',1),(30,'スーツでダンスしてる','',1),(31,'でじこのキーホルダー作ってる','',1),(32,'バカ殿の格好をしている','',1),(33,'オールスター感謝祭で司会をしている','',1),(34,'壁を壊してる','',1),(35,'3の倍数だけアホになる','',1),(36,'ヤングマンを踊っている','',1),(37,'秒速５cmで動いている','',1),(38,'スケ番でガッカリしている','',1),(39,'TDLについて熱く語っている','',1),(40,'世田谷ベースで遊んでいる','',1),(41,'ブラックジャックを書いている','',1),(42,'黄色いハンカチを掲げている','',1),(43,'走り方を教えている','',1),(44,'詐欺保険の宣伝をしている','',1),(45,'笑いながら怒っている','',1),(46,'方正アウト〜','',1),(47,'ラジオ番組で寝ている','',1),(48,'霞ヶ関をぶっ壊している','',1),(49,'ラグビーボールを投げている','',1),(50,'ボクシングをしている','',1),(51,'マフラーをぐるぐる巻きにしている','',1),(52,'ブラックビスケッツでリーダーしてる','',1),(53,'セミの抜け殻を頭につけている','',1),(54,'HOTLIMITしている','',1),(55,'空気砲を撃っている','',1),(56,'２chしてる','',1),(57,'かごめかごめを歌っている','',1),(58,'マルっとお見通ししている','',1),(59,'赤子を抱いてる','',1),(60,'柱に飛び込んでいる','',1),(61,'崖で説得している','',1),(62,'ゲームショップで働いている','',1),(63,'スプーを書いている','',1),(64,'ともえ投げをしている','',1),(65,'刑事７人で捜査してる','',1),(66,'仮面ライダーに変身している','',1),(67,'ネギを回している','',1),(68,'将棋を指している','',1),(69,'料理にマグネットを入れている','',1),(70,'杖ついてドームでコントしてる','',1),(71,'マジンガーZを歌ってる','',1),(72,'マツケンサンバを踊っている','',1),(73,'マリオを遊んでいる','',1),(74,'NYでインタビューしてる','',1),(75,'さくらを独唱してる','',1),(76,'momo-iコールしている','',1),(77,'超電磁砲を撃っている','',1),(78,'腹を撃たれて叫んでる','',1),(79,'カンブリア宮殿で話をしている','',1),(80,'ゲームアンドウォッチを作ってる','',1),(81,'おら東京さ行くだ','',1),(82,'おはスタに出演している','',1),(83,'iPS細胞を作っている','',1),(84,'バカの壁を壊している','',1),(85,'トリビアを紹介している','',1),(86,'やってやるですって言っている','',1),(87,'ローマで温泉に入っている','',1),(88,'心療内科を紹介している','',1),(89,'本に埋まっている','',1),(90,'タイタニックに乗船している','',1),(91,'ギィ・ナゴを飛ばしている','',1),(92,'派出所を爆破している','',1),(93,'鑑識を行なっている。','',1),(94,'紅蓮弐式を建造している','',1),(95,'トーラスに操縦している','',1),(96,'電気屋で店番をしている','',1),(97,'炎を錬成している','',1),(98,'今日のレッドに答えてる','',1),(99,'お茶漬けを勧めている','',1);
/*!40000 ALTER TABLE `actionlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `namelist`
--

DROP TABLE IF EXISTS `namelist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `namelist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '未設定',
  `URL` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `Strength` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1014 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `namelist`
--

LOCK TABLES `namelist` WRITE;
/*!40000 ALTER TABLE `namelist` DISABLE KEYS */;
INSERT INTO `namelist` VALUES (0,'ドミニク・オブライエン','',1),(1,'デヴィット・ベッカム','',1),(2,'若田光一','',1),(3,'パパイヤ・鈴木','',1),(4,'渡瀬恒彦','',1),(5,'若本規夫','',1),(6,'ゴン・フリークス','',1),(7,'若林正恭(まさやす)','',1),(8,'グッチ裕三','',1),(9,'ジョージ・ルーカス','',1),(10,'伊藤穰一','',1),(11,'大塚明夫','',1),(12,'えなりかずき','',1),(13,'岩田聡','',1),(14,'いかりや長介','',1),(15,'大山のぶ代','',1),(16,'庵野秀明','',1),(17,'江角マキコ','',1),(18,'江口洋介','',1),(19,'アムロ・レイ','',1),(20,'ケント・ギルバート','',1),(21,'黒澤明','',1),(22,'金木研','',1),(23,'木ノ本さくら','',1),(24,'北野武','',1),(25,'角田信朗(のぶあき)','',1),(26,'小島秀夫','',1),(27,'草薙素子','',1),(28,'窪塚洋介','',1),(29,'菊川怜','',1),(30,'須藤元気','',1),(31,'真田アサミ','',1),(32,'志村けん','',1),(33,'島田紳助','',1),(34,'反町隆史','',1),(35,'世界のナベアツ','',1),(36,'西城秀樹','',1),(37,'新海誠','',1),(38,'桜塚やっくん','',1),(39,'新谷良子','',1),(40,'所ジョージ','',1),(41,'手塚治虫','',1),(42,'高倉健','',1),(43,'武井壮','',1),(44,'地井武男','',1),(45,'竹中直人','',1),(46,'月亭方正','',1),(47,'茅原実里','',1),(48,'高橋洋一','',1),(49,'竹内力','',1),(50,'内藤大助','',1),(51,'中尾彬','',1),(52,'南原清隆','',1),(53,'中川翔子','',1),(54,'西川貴教','',1),(55,'野比のび太','',1),(56,'西村ひろゆき','',1),(57,'能登麻美子','',1),(58,'仲間由紀恵','',1),(59,'ノーマン・リーダス','',1),(60,'ハリー・ポッター','',1),(61,'船越英一郎','',1),(62,'平野耕太','',1),(63,'はいだ・しょうこ','',1),(64,'原田泰造','',1),(65,'東山紀之','',1),(66,'藤岡弘、','',1),(67,'初音ミク','',1),(68,'羽生善治','',1),(69,'平野レミ','',1),(70,'みうらじゅん','',1),(71,'水木一郎','',1),(72,'松平健','',1),(73,'宮本茂','',1),(74,'町山智浩','',1),(75,'森山直太朗','',1),(76,'桃井はるこ','',1),(77,'御坂美琴','',1),(78,'松田優作','',1),(79,'村上龍','',1),(80,'横井軍平','',1),(81,'吉幾三','',1),(82,'山寺宏一','',1),(83,'山中伸弥','',1),(84,'養老孟司','',1),(85,'八嶋智人(のりと)','',1),(86,'安元洋貴','',1),(87,'ヤマザキ・マリ','',1),(88,'ゆうき・ゆう','',1),(89,'読子・リードマン','',1),(90,'レオナルド・ディカプリオ','',1),(91,'レイチェル＝アルカード','',1),(92,'両津勘吉','',1),(93,'六角精児','',1),(94,'ラクシャータ・チャウラー','',1),(95,'ルクレツィア・ノイン','',1),(96,'リリー・フランキー','',1),(97,'ロイ・マスタング','',1),(98,'レッド吉田','',1),(99,'ラモス・瑠偉','',1);
/*!40000 ALTER TABLE `namelist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `numbers`
--

DROP TABLE IF EXISTS `numbers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `numbers` (
  `user_id` int NOT NULL DEFAULT '99',
  `list_number` int NOT NULL DEFAULT '200',
  `name_id` int NOT NULL DEFAULT '0',
  `name_URL` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `action_id` int NOT NULL DEFAULT '0',
  `action_URL` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `memory_Level` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`,`list_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `numbers`
--

LOCK TABLES `numbers` WRITE;
/*!40000 ALTER TABLE `numbers` DISABLE KEYS */;
INSERT INTO `numbers` VALUES (1,0,0,'',0,'',1),(1,1,1,'',1,'',1),(1,2,2,'',2,'',1),(1,3,3,'',3,'',1),(1,4,4,'',4,'',1),(1,5,5,'',5,'',1),(1,6,6,'',6,'',1),(1,7,7,'',7,'',1),(1,8,8,'',8,'',1),(1,9,9,'',9,'',1),(1,10,10,'',10,'',1),(1,11,11,'',11,'',1),(1,12,12,'',12,'',1),(1,13,13,'',13,'',1),(1,14,14,'',14,'',1),(1,15,15,'',15,'',1),(1,16,16,'',16,'',1),(1,17,17,'',17,'',1),(1,18,18,'',18,'',1),(1,19,19,'',19,'',1),(1,20,20,'',20,'',1),(1,21,21,'',21,'',1),(1,22,22,'',22,'',1),(1,23,23,'',23,'',1),(1,24,24,'',24,'',1),(1,25,25,'',25,'',1),(1,26,26,'',26,'',1),(1,27,27,'',27,'',1),(1,28,28,'',28,'',1),(1,29,29,'',29,'',1),(1,30,30,'',30,'',1),(1,31,31,'',31,'',1),(1,32,32,'',32,'',1),(1,33,33,'',33,'',1),(1,34,34,'',34,'',1),(1,35,35,'',35,'',1),(1,36,36,'',36,'',1),(1,37,37,'',37,'',1),(1,38,38,'',38,'',1),(1,39,39,'',39,'',1),(1,40,40,'',40,'',1),(1,41,41,'',41,'',1),(1,42,42,'',42,'',1),(1,43,43,'',43,'',1),(1,44,44,'',44,'',1),(1,45,45,'',45,'',1),(1,46,46,'',46,'',1),(1,47,47,'',47,'',1),(1,48,48,'',48,'',1),(1,49,49,'',49,'',1),(1,50,50,'',50,'',1),(1,51,51,'',51,'',1),(1,52,52,'',52,'',1),(1,53,53,'',53,'',1),(1,54,54,'',54,'',1),(1,55,55,'',55,'',1),(1,56,56,'',56,'',1),(1,57,57,'',57,'',1),(1,58,58,'',58,'',1),(1,59,59,'',59,'',1),(1,60,60,'',60,'',1),(1,61,61,'',61,'',1),(1,62,62,'',62,'',1),(1,63,63,'',63,'',1),(1,64,64,'',64,'',1),(1,65,65,'',65,'',1),(1,66,66,'',66,'',1),(1,67,67,'',67,'',1),(1,68,68,'',68,'',1),(1,69,69,'',69,'',1),(1,70,70,'',70,'',1),(1,71,71,'',71,'',1),(1,72,72,'',72,'',1),(1,73,73,'',73,'',1),(1,74,74,'',74,'',1),(1,75,75,'',75,'',1),(1,76,76,'',76,'',1),(1,77,77,'',77,'',1),(1,78,78,'',78,'',1),(1,79,79,'',79,'',1),(1,80,80,'',80,'',1),(1,81,81,'',81,'',1),(1,82,82,'',82,'',1),(1,83,83,'',83,'',1),(1,84,84,'',84,'',1),(1,85,85,'',85,'',1),(1,86,86,'',86,'',1),(1,87,87,'',87,'',1),(1,88,88,'',88,'',1),(1,89,89,'',89,'',1),(1,90,90,'',90,'',1),(1,91,91,'',91,'',1),(1,92,92,'',92,'',1),(1,93,93,'',93,'',1),(1,94,94,'',94,'',1),(1,95,95,'',95,'',1),(1,96,96,'',96,'',1),(1,97,97,'',97,'',1),(1,98,98,'',98,'',1),(1,99,99,'',99,'',1);
/*!40000 ALTER TABLE `numbers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `traningscore`
--

DROP TABLE IF EXISTS `traningscore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `traningscore` (
  `user_id` int NOT NULL DEFAULT '99',
  `traning_id` int NOT NULL DEFAULT '200',
  `point_1` int NOT NULL DEFAULT '-1',
  `point_2` int NOT NULL DEFAULT '-1',
  `point_3` int NOT NULL DEFAULT '-1',
  `count` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`,`traning_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `traningscore`
--

LOCK TABLES `traningscore` WRITE;
/*!40000 ALTER TABLE `traningscore` DISABLE KEYS */;
/*!40000 ALTER TABLE `traningscore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `traningtype`
--

DROP TABLE IF EXISTS `traningtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `traningtype` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `traningtype`
--

LOCK TABLES `traningtype` WRITE;
/*!40000 ALTER TABLE `traningtype` DISABLE KEYS */;
/*!40000 ALTER TABLE `traningtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `pass` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `userrank` int NOT NULL DEFAULT '1',
  `state` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'act',
  `lasttime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'default1','aqwsedfrgtyhujkilop;@:;polikujytreedrftghujikolp;loikujytrew',1,'del','2020-11-28 16:05:30'),(100,'default2','aswedrftgyhujikolrtyujikolprtyhujikolp;',1,'del','2021-01-02 14:03:07');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `word_book`
--

DROP TABLE IF EXISTS `word_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `word_book` (
  `id` int NOT NULL,
  `user_id` int NOT NULL DEFAULT '99',
  `memory_Level` int NOT NULL DEFAULT '0',
  `text` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `word_book`
--

LOCK TABLES `word_book` WRITE;
/*!40000 ALTER TABLE `word_book` DISABLE KEYS */;
/*!40000 ALTER TABLE `word_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `word_profile`
--

DROP TABLE IF EXISTS `word_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `word_profile` (
  `id` int NOT NULL AUTO_INCREMENT,
  `word` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `value` int NOT NULL DEFAULT '0',
  `text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `meam` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '未設定',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `word_profile`
--

LOCK TABLES `word_profile` WRITE;
/*!40000 ALTER TABLE `word_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `word_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'dominic_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-07 15:06:37
