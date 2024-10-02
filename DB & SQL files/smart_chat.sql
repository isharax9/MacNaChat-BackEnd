-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.34 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.4.0.6659
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping data for table smart_chat.chat: ~0 rows (approximately)
DELETE FROM `chat`;

-- Dumping data for table smart_chat.chat_status: ~2 rows (approximately)
DELETE FROM `chat_status`;
INSERT INTO `chat_status` (`id`, `name`) VALUES
	(1, 'Seen'),
	(2, 'Seen');

-- Dumping data for table smart_chat.user: ~5 rows (approximately)
DELETE FROM `user`;
INSERT INTO `user` (`id`, `mobile`, `first_name`, `last_name`, `password`, `registered_date_time`, `user_status_id`) VALUES
	(1, '0710351156', 'Ishara', 'Lakshitha ', '11010001', '2024-09-29 20:28:30', 2),
	(2, '0775006001', 'Anura', 'Dissanayaka', '11010001', '2024-09-29 10:37:37', 1),
	(3, '0716092775', 'Nimal', 'Hitibandara', '11010001', '2024-09-30 10:38:26', 2),
	(4, '0775006002', 'Sajith', 'Premadasa', '11010001', '2024-09-30 13:05:07', 2),
	(5, '0775006003', 'Tharaka', 'Lakshan ', '11010001', '2024-10-02 10:54:34', 2);

-- Dumping data for table smart_chat.user_status: ~2 rows (approximately)
DELETE FROM `user_status`;
INSERT INTO `user_status` (`id`, `name`) VALUES
	(1, 'Online'),
	(2, 'Offline');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
