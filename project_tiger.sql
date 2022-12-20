-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 20, 2022 at 08:36 PM
-- Server version: 10.9.1-MariaDB-log
-- PHP Version: 7.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project_tiger`
--

DELIMITER $$
--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `getYear` () RETURNS DATE DETERMINISTIC NO SQL return @year$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Stand-in structure for view `cafe_report`
-- (See below for the actual view)
--
CREATE TABLE `cafe_report` (
`วันที่` datetime
,`รายการ` varchar(19)
,`จำนวน` bigint(21)
,`ยอดรวม` double
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `co_working_report`
-- (See below for the actual view)
--
CREATE TABLE `co_working_report` (
`วันที่` datetime
,`รายการ` varchar(18)
,`จำนวน` bigint(21)
,`ยอดรวม` double
);

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `phoneNum` char(10) COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`phoneNum`) VALUES
('0661128806'),
('0661128808'),
('0812345678');

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `inv_id` int(11) NOT NULL,
  `inv_type` int(11) NOT NULL,
  `cus_phone` char(10) COLLATE utf8mb4_bin DEFAULT NULL,
  `inv_discount` double NOT NULL DEFAULT 0,
  `inv_vat` double NOT NULL,
  `inv_total` double NOT NULL,
  `inv_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`inv_id`, `inv_type`, `cus_phone`, `inv_discount`, `inv_vat`, `inv_total`, `inv_date`) VALUES
(1, 2, NULL, 0, 21, 321, '2022-11-28 10:23:38'),
(2, 2, NULL, 0, 21, 321, '2022-11-28 10:23:45'),
(3, 2, NULL, 0, 21, 321, '2022-11-28 10:23:52'),
(4, 2, NULL, 0, 21, 321, '2022-11-28 10:23:59'),
(5, 2, NULL, 0, 21, 321, '2022-11-28 10:24:06'),
(6, 2, '0661128806', 80, 0, 0, '2022-11-28 11:17:51'),
(7, 1, NULL, 0, 8.4, 128.4, '2022-11-28 11:18:16'),
(8, 1, NULL, 0, 6.3, 96.3, '2022-11-28 11:18:29'),
(9, 1, NULL, 0, 6.65, 101.65, '2022-11-28 11:20:41'),
(10, 2, NULL, 0, 17.5, 267.5, '2022-11-28 11:31:01'),
(11, 2, '0661128806', 200, 3.5, 53.5, '2022-11-28 11:32:11'),
(12, 3, NULL, 0, 105, 1605, '2022-11-28 11:33:37'),
(13, 1, NULL, 0, 8.05, 123.05, '2022-11-28 11:34:21');

-- --------------------------------------------------------

--
-- Table structure for table `invoice_items`
--

CREATE TABLE `invoice_items` (
  `inv_items_id` int(11) NOT NULL,
  `inv_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `qty` int(11) NOT NULL,
  `subtotal` double NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `invoice_items`
--

INSERT INTO `invoice_items` (`inv_items_id`, `inv_id`, `item_id`, `qty`, `subtotal`) VALUES
(7, 1, 2, 1, 300),
(8, 2, 2, 1, 300),
(9, 3, 2, 1, 300),
(10, 4, 2, 1, 300),
(11, 5, 2, 1, 300),
(12, 6, 4, 1, 0),
(13, 7, 7, 1, 30),
(14, 7, 8, 3, 90),
(15, 8, 7, 1, 30),
(16, 8, 9, 2, 60),
(17, 9, 10, 1, 35),
(18, 9, 8, 1, 30),
(19, 9, 9, 1, 30),
(20, 10, 3, 1, 250),
(21, 11, 3, 1, 50),
(22, 12, 1, 1, 1500),
(23, 13, 7, 2, 60),
(24, 13, 8, 1, 30),
(25, 13, 5, 1, 25);

-- --------------------------------------------------------

--
-- Table structure for table `invoice_type`
--

CREATE TABLE `invoice_type` (
  `type_id` int(1) NOT NULL,
  `type_name` varchar(50) COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `invoice_type`
--

INSERT INTO `invoice_type` (`type_id`, `type_name`) VALUES
(1, 'CAFE'),
(2, 'ROOM'),
(3, 'MEMBER');

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `item_id` int(11) NOT NULL,
  `item_name` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `item_price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`item_id`, `item_name`, `item_price`) VALUES
(1, 'สมัครสมาชิกรายเดือน', 1500),
(2, 'ห้องประชุมเล็ก', 150),
(3, 'ห้องประชุมใหญ่', 250),
(4, 'รายบุคคล', 40),
(5, 'นมร้อน', 25),
(6, 'บราวนี่', 25),
(7, 'นมเย็น', 30),
(8, 'กาแฟร้อน', 30),
(9, 'น้ำผลไม้', 30),
(10, 'นมปั่น', 35),
(11, 'กาแฟเย็น', 35),
(12, 'เค้ก', 35);

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `r_id` int(11) NOT NULL,
  `roomType` int(11) NOT NULL,
  `reserved` tinyint(1) NOT NULL DEFAULT 0,
  `last_reserved_end` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`r_id`, `roomType`, `reserved`, `last_reserved_end`) VALUES
(1, 1, 0, NULL),
(2, 1, 0, NULL),
(3, 1, 0, NULL),
(4, 1, 0, NULL),
(5, 1, 0, NULL),
(6, 2, 0, NULL),
(7, 2, 0, NULL),
(8, 2, 0, NULL),
(9, 2, 0, NULL),
(10, 2, 0, NULL),
(11, 3, 0, NULL),
(12, 3, 0, NULL),
(13, 3, 0, NULL),
(14, 3, 0, NULL),
(15, 3, 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `roomtype`
--

CREATE TABLE `roomtype` (
  `type_id` int(11) NOT NULL,
  `type_name` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `maxSeat` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `roomtype`
--

INSERT INTO `roomtype` (`type_id`, `type_name`, `maxSeat`) VALUES
(1, 'SMALL', 5),
(2, 'LARGE', 10),
(3, 'INDIVIDUAL', 1);

-- --------------------------------------------------------

--
-- Structure for view `cafe_report`
--
DROP TABLE IF EXISTS `cafe_report`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `cafe_report`  AS SELECT `invoice`.`inv_date` AS `วันที่`, concat('รายการสั่งซื้ออาหาร') AS `รายการ`, (select count(0) from `invoice_items` `items` where `items`.`inv_id` = `invoice`.`inv_id`) AS `จำนวน`, `invoice`.`inv_total` AS `ยอดรวม` FROM (`invoice` join `invoice_items` `items` on(`invoice`.`inv_id` = `items`.`inv_id`)) WHERE `invoice`.`inv_type` = 1 AND date_format(`invoice`.`inv_date`,'%Y-%m') = date_format(`getYear`(),'%Y-%m') ORDER BY `invoice`.`inv_date` AS `DESCdesc` ASC  ;

-- --------------------------------------------------------

--
-- Structure for view `co_working_report`
--
DROP TABLE IF EXISTS `co_working_report`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `co_working_report`  AS SELECT `invoice`.`inv_date` AS `วันที่`, (select case when `invoice`.`inv_type` = 3 then 'รายการสมัครสมาชิก' when `invoice`.`inv_type` = 2 then 'รายการจองห้องทำงาน' else 'ผิดพลาด' end) AS `รายการ`, (select count(0) from `invoice_items` `it` where `it`.`inv_id` = `invoice`.`inv_id`) AS `จำนวน`, `invoice`.`inv_total` AS `ยอดรวม` FROM (`invoice` join `invoice_items` `items` on(`invoice`.`inv_id` = `items`.`inv_id`)) WHERE (`invoice`.`inv_type` = 2 OR `invoice`.`inv_type` = 3) AND date_format(`invoice`.`inv_date`,'%Y-%m') = date_format(`getYear`(),'%Y-%m') ORDER BY `invoice`.`inv_date` AS `DESCdesc` ASC  ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`phoneNum`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`inv_id`),
  ADD KEY `inv_type` (`inv_type`),
  ADD KEY `cus_phone` (`cus_phone`);

--
-- Indexes for table `invoice_items`
--
ALTER TABLE `invoice_items`
  ADD PRIMARY KEY (`inv_items_id`),
  ADD KEY `inv_id` (`inv_id`),
  ADD KEY `item_id` (`item_id`);

--
-- Indexes for table `invoice_type`
--
ALTER TABLE `invoice_type`
  ADD PRIMARY KEY (`type_id`);

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`item_id`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`r_id`),
  ADD KEY `roomType` (`roomType`);

--
-- Indexes for table `roomtype`
--
ALTER TABLE `roomtype`
  ADD PRIMARY KEY (`type_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `inv_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `invoice_items`
--
ALTER TABLE `invoice_items`
  MODIFY `inv_items_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `room`
--
ALTER TABLE `room`
  MODIFY `r_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `roomtype`
--
ALTER TABLE `roomtype`
  MODIFY `type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`inv_type`) REFERENCES `invoice_type` (`type_id`),
  ADD CONSTRAINT `invoice_ibfk_2` FOREIGN KEY (`cus_phone`) REFERENCES `customers` (`phoneNum`);

--
-- Constraints for table `invoice_items`
--
ALTER TABLE `invoice_items`
  ADD CONSTRAINT `invoice_items_ibfk_1` FOREIGN KEY (`inv_id`) REFERENCES `invoice` (`inv_id`),
  ADD CONSTRAINT `invoice_items_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`);

--
-- Constraints for table `room`
--
ALTER TABLE `room`
  ADD CONSTRAINT `room_ibfk_1` FOREIGN KEY (`roomType`) REFERENCES `roomtype` (`type_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
