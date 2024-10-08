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

-- Dumping data for table macna_chat.chat: ~68 rows (approximately)
DELETE FROM `chat`;
INSERT INTO `chat` (`id`, `from_user_id`, `to_user_id`, `message`, `date_time`, `chat_status_id`) VALUES
	(1, 1, 2, 'Test1', '2024-10-01 15:34:30', 1),
	(2, 2, 1, 'Test2', '2024-10-02 15:34:48', 1),
	(3, 1, 3, 'Test3', '2024-10-02 15:35:15', 1),
	(4, 3, 4, 'Test4', '2024-10-01 15:35:37', 1),
	(5, 1, 2, 'Test5', '2024-10-02 15:36:56', 1),
	(6, 2, 4, 'Test6', '2024-10-02 15:36:21', 1),
	(7, 1, 2, 'Hello', '2024-10-04 12:32:48', 1),
	(8, 1, 2, 'Hello', '2024-10-04 12:34:29', 1),
	(9, 1, 2, 'Hi anura', '2024-10-04 12:50:14', 1),
	(10, 1, 4, 'Hi sajith', '2024-10-04 12:53:13', 1),
	(11, 1, 5, 'Hi tharaka', '2024-10-04 12:55:44', 1),
	(12, 1, 2, 'Hi', '2024-10-04 12:58:39', 1),
	(13, 1, 2, 'Yo anura', '2024-10-04 13:02:58', 1),
	(14, 1, 4, 'Sajith what\'s going on', '2024-10-04 13:04:07', 1),
	(15, 1, 3, 'Hi thaththe', '2024-10-04 13:15:09', 1),
	(16, 1, 4, 'Hi', '2024-10-04 13:23:15', 1),
	(17, 1, 4, 'Mk Ishara', '2024-10-04 16:55:05', 1),
	(18, 1, 4, 'Sorry ð', '2024-10-04 16:55:24', 1),
	(19, 4, 1, 'Innwa ohe Ishara', '2024-10-04 16:57:27', 1),
	(20, 3, 1, 'Hi puthe ð', '2024-10-04 17:13:24', 1),
	(21, 2, 1, 'Hello Ishara kohomda ð', '2024-10-04 17:17:06', 1),
	(22, 5, 1, 'Hello Malli ð¤ð', '2024-10-04 17:24:52', 1),
	(23, 4, 2, 'Ai mokda', '2024-10-04 17:26:33', 1),
	(24, 1, 5, 'Aiye mk', '2024-10-04 17:31:07', 1),
	(25, 6, 1, 'Hey chuti', '2024-10-04 17:46:45', 1),
	(26, 1, 6, 'Hello Amma ð', '2024-10-04 18:03:26', 1),
	(27, 1, 3, 'Den kawada', '2024-10-04 21:33:41', 1),
	(28, 1, 4, 'Ehemda', '2024-10-04 22:03:14', 1),
	(29, 1, 2, 'Oya dinna neda', '2024-10-04 22:05:14', 1),
	(30, 3, 4, 'Oya paradai neda sajith', '2024-10-04 22:07:16', 1),
	(31, 1, 2, 'Maru Habei', '2024-10-04 22:45:59', 1),
	(32, 1, 2, 'App eka maru', '2024-10-04 23:02:45', 1),
	(33, 2, 3, 'Hello nimal', '2024-10-04 23:08:52', 1),
	(34, 2, 4, 'Nikan ane', '2024-10-04 23:10:51', 1),
	(35, 1, 4, 'Meka DiGa message ekak hodada api balanne meken DiGa message home eke handle karana widiaya', '2024-10-04 23:41:55', 1),
	(36, 1, 5, 'Wadada', '2024-10-04 23:53:46', 1),
	(37, 1, 5, 'Nadda', '2024-10-05 00:05:21', 1),
	(38, 5, 1, 'Wada wada ban', '2024-10-05 00:06:20', 1),
	(39, 1, 5, 'Okay ðð', '2024-10-05 00:07:00', 1),
	(40, 1, 3, 'Gedara awa neda', '2024-10-05 19:49:40', 1),
	(41, 3, 4, 'Ado', '2024-10-06 11:18:15', 1),
	(42, 3, 4, 'Ado', '2024-10-06 11:18:18', 1),
	(43, 3, 2, 'Hello ð', '2024-10-06 14:55:25', 1),
	(44, 3, 5, 'Hi putha', '2024-10-06 14:56:42', 1),
	(45, 5, 3, 'Hello ththathe', '2024-10-06 14:57:20', 1),
	(46, 5, 2, 'Hello anura mahaththaya', '2024-10-06 14:57:54', 1),
	(47, 4, 3, 'Ai nimal mokda', '2024-10-06 15:04:20', 1),
	(48, 3, 1, 'Awa', '2024-10-06 21:36:45', 1),
	(49, 3, 1, 'Mk ', '2024-10-06 21:44:52', 1),
	(50, 3, 1, 'Me athi ban', '2024-10-06 21:54:58', 1),
	(51, 3, 1, 'Athi neda', '2024-10-06 22:27:55', 1),
	(52, 1, 3, 'Athi athi', '2024-10-07 13:45:55', 1),
	(53, 1, 2, 'Neda', '2024-10-07 13:49:39', 1),
	(54, 1, 3, 'Hi', '2024-10-07 13:50:19', 1),
	(55, 1, 3, 'Test q', '2024-10-07 16:11:57', 1),
	(56, 6, 3, 'Hello thththa ð¤', '2024-10-07 21:00:08', 1),
	(57, 3, 6, 'Hello Amma ð', '2024-10-07 21:01:22', 2),
	(58, 3, 4, 'à¶±à·à¶à¶±à·', '2024-10-07 21:17:23', 2),
	(59, 3, 1, 'Hello chuti putha', '2024-10-07 22:04:18', 1),
	(60, 2, 1, 'Wada wada', '2024-10-08 00:11:59', 1),
	(61, 1, 2, 'Mmm maru ð', '2024-10-08 00:26:17', 1),
	(62, 1, 2, 'Bath kamuda', '2024-10-08 13:33:16', 1),
	(63, 2, 1, 'Kamu kamu', '2024-10-08 13:33:36', 1),
	(64, 9, 2, 'Hi', '2024-10-08 14:56:01', 1),
	(65, 9, 1, 'I signup', '2024-10-08 14:56:22', 1),
	(66, 1, 9, 'Nice ð', '2024-10-08 18:17:17', 2),
	(67, 2, 9, 'Hello lakshan me chandeth dinanu neda. Chande denwa neda apita ð', '2024-10-08 18:18:49', 2),
	(68, 8, 1, 'Hello chuti', '2024-10-08 18:20:46', 2);

-- Dumping data for table macna_chat.chat_status: ~2 rows (approximately)
DELETE FROM `chat_status`;
INSERT INTO `chat_status` (`id`, `name`) VALUES
	(1, 'Seen'),
	(2, 'Unseen');

-- Dumping data for table macna_chat.user: ~9 rows (approximately)
DELETE FROM `user`;
INSERT INTO `user` (`id`, `mobile`, `first_name`, `last_name`, `password`, `registered_date_time`, `user_status_id`) VALUES
	(1, '0710351156', 'Ishara', 'Lakshitha ', '11010001', '2024-09-29 20:28:30', 1),
	(2, '0775006001', 'Anura', 'Dissanayaka', '11010001', '2024-09-29 10:37:37', 1),
	(3, '0775006005', 'Channa', 'Gayan', '11010001', '2024-09-30 10:38:26', 1),
	(4, '0775006002', 'Sajith', 'Premadasa', '11010001', '2024-09-30 13:05:07', 2),
	(5, '0775006003', 'Tharaka', 'Lakshan ', '11010001', '2024-10-02 10:54:34', 2),
	(6, '0717439057', 'Paminda', 'Priyanka ', '11010001', '2024-10-04 17:28:21', 1),
	(7, '0775006004', 'Tharuka', 'Dilhani', '11010001', '2024-10-07 11:21:31', 1),
	(8, '0716092775', 'Nimal', 'Hitibandara ', '11010001', '2024-10-07 23:33:43', 1),
	(9, '0710507342', 'Lakshan', 'Tharaka', 'nawwa123', '2024-10-08 14:54:54', 1);

-- Dumping data for table macna_chat.user_status: ~2 rows (approximately)
DELETE FROM `user_status`;
INSERT INTO `user_status` (`id`, `name`) VALUES
	(1, 'Online'),
	(2, 'Offline');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
