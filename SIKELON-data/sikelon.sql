SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";
DROP DATABASE IF EXISTS sikelon
CREATE DATABASE sikelon
USE sikelon
CREATE TABLE IF NOT EXISTS `cart` (`cart_id` int(11) NOT NULL, `user_id` int(11) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `cart` (`cart_id`, `user_id`) VALUES(1, 1),(2, 2);
CREATE TABLE `cartdetail` (`cartDetail_id` int(11) NOT NULL,`cart_id` int(11) NOT NULL,`store_id` int(11) NOT NULL,`item_id` int(11) NOT NULL,`quantity` int(11) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `cartdetail` (`cartDetail_id`, `cart_id`, `store_id`, `item_id`, `quantity`) VALUES(1, 1, 1, 1, 3);
CREATE TABLE `item` ( `item_id` int(11) NOT NULL, `item_name` varchar(200) NOT NULL,`price` int(11) NOT NULL, `store_id` int(11) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `item` (`item_id`, `item_name`, `price`, `store_id`) VALUES(1, 'bengbeng maxx', 3000, 1);
CREATE TABLE `store` (`store_id` int(11) NOT NULL,`store_name` varchar(200) NOT NULL,`address` varchar(200) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `store` (`store_id`, `store_name`, `address`) VALUES(1, 'test store', '35th Ran St.');
CREATE TABLE `user` (`user_id` int(11) NOT NULL,`username` varchar(200) NOT NULL,`name` varchar(200) NOT NULL,`join_date` timestamp NOT NULL DEFAULT current_timestamp()) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `user` (`user_id`, `username`, `name`, `join_date`) VALUES(1, 'TheMan', 'John Man', '2025-05-16 03:56:03'),(2, 'Two', 'Test Two', '2025-05-16 03:57:14');
ALTER TABLE `user` ADD PRIMARY KEY (`user_id`);
ALTER TABLE `store` ADD PRIMARY KEY (`store_id`);
ALTER TABLE `cart` ADD PRIMARY KEY (`cart_id`), ADD UNIQUE KEY `customer_id` (`user_id`);
ALTER TABLE `item` ADD PRIMARY KEY (`item_id`), ADD UNIQUE KEY `store_id` (`store_id`);
ALTER TABLE `cartdetail` ADD PRIMARY KEY (`cartDetail_id`), ADD UNIQUE KEY `cart_id` (`cart_id`), ADD UNIQUE KEY `store_id` (`store_id`), ADD UNIQUE KEY `item_id` (`item_id`);
ALTER TABLE `user` MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
ALTER TABLE `store` MODIFY `store_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
ALTER TABLE `item` MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
ALTER TABLE `item` ADD CONSTRAINT `item_ibfk_1` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`);
ALTER TABLE `cart` MODIFY `cart_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
ALTER TABLE `cart` ADD CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);
ALTER TABLE `cartdetail` MODIFY `cartDetail_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
ALTER TABLE `cartdetail` ADD CONSTRAINT `cartdetail_ibfk_1` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`), ADD CONSTRAINT `cartdetail_ibfk_2` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`), ADD CONSTRAINT `cartdetail_ibfk_3` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`);
COMMIT;