-- phpMyAdmin SQL Dump
-- version 4.9.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- 생성 시간: 21-05-28 15:43
-- 서버 버전: 10.3.24-MariaDB
-- PHP 버전: 7.3.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 데이터베이스: `hotelDB`
--
CREATE DATABASE IF NOT EXISTS `hotelDB` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `hotelDB`;

-- --------------------------------------------------------

--
-- 테이블 구조 `hotelInfo`
--

CREATE TABLE `hotelInfo` (
  `hotelID` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `location` varchar(200) DEFAULT NULL,
  `facility` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 테이블의 덤프 데이터 `hotelInfo`
--

INSERT INTO `hotelInfo` (`hotelID`, `name`, `location`, `facility`) VALUES
(1, 'a호텔', '동작구', '~~'),
(2, 'b호텔', '동작구', '~~'),
(3, 'c호텔', '충청북도 청주시', '수영장');

-- --------------------------------------------------------

--
-- 테이블 구조 `loginTable`
--

CREATE TABLE `loginTable` (
  `id` varchar(50) NOT NULL,
  `pw` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `is_admin` tinyint(4) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 테이블의 덤프 데이터 `loginTable`
--

INSERT INTO `loginTable` (`id`, `pw`, `name`, `is_admin`, `phone`) VALUES
('lee0521', 'abcd', 'yeongseok', 0, '01064217148');

-- --------------------------------------------------------

--
-- 테이블 구조 `noticeBoard`
--

CREATE TABLE `noticeBoard` (
  `idx` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `title` varchar(70) DEFAULT NULL,
  `content` varchar(1500) DEFAULT NULL,
  `wdate` datetime DEFAULT NULL,
  `hotelID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 테이블의 덤프 데이터 `noticeBoard`
--

INSERT INTO `noticeBoard` (`idx`, `name`, `title`, `content`, `wdate`, `hotelID`) VALUES
(1, 'Aman', '첫공지', 'test test', NULL, 1);

-- --------------------------------------------------------

--
-- 테이블 구조 `reservation`
--

CREATE TABLE `reservation` (
  `roomID` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `isMeal` int(8) DEFAULT NULL,
  `numGuests` int(8) DEFAULT NULL,
  `status` int(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 테이블의 덤프 데이터 `reservation`
--

INSERT INTO `reservation` (`roomID`, `name`, `startTime`, `endTime`, `isMeal`, `numGuests`, `status`) VALUES
(101, 'Aman', '2021-05-27 00:00:00', '2021-05-27 00:00:00', 1, 5, 1),
(201, 'Lee', '2021-06-01 00:00:00', '2021-06-03 00:00:00', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- 테이블 구조 `reviewBoard`
--

CREATE TABLE `reviewBoard` (
  `idx` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `title` varchar(70) DEFAULT NULL,
  `content` varchar(1500) DEFAULT NULL,
  `wdate` datetime DEFAULT NULL,
  `hotelID` int(11) DEFAULT NULL,
  `roomID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 테이블의 덤프 데이터 `reviewBoard`
--

INSERT INTO `reviewBoard` (`idx`, `name`, `title`, `content`, `wdate`, `hotelID`, `roomID`) VALUES
(1, 'Lee', 'update Test', NULL, NULL, NULL, NULL),
(2, 'test0002', '테스트 리뷰', '123456abc', '2021-05-27 14:40:54', 1, 10101);

-- --------------------------------------------------------

--
-- 테이블 구조 `roomInfo`
--

CREATE TABLE `roomInfo` (
  `hotelID` int(11) DEFAULT NULL,
  `roomID` int(11) DEFAULT NULL,
  `nowStatus` int(8) DEFAULT 0,
  `costPerDay` int(50) DEFAULT NULL,
  `mealInfo` varchar(500) DEFAULT NULL,
  `maxGuests` int(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 테이블의 덤프 데이터 `roomInfo`
--

INSERT INTO `roomInfo` (`hotelID`, `roomID`, `nowStatus`, `costPerDay`, `mealInfo`, `maxGuests`) VALUES
(2, 201, 0, 60000, '중식 석식', 4),
(1, 101, 0, 10000, '조식: \n중식: \n석식:', 5);

--
-- 덤프된 테이블의 인덱스
--

--
-- 테이블의 인덱스 `loginTable`
--
ALTER TABLE `loginTable`
  ADD PRIMARY KEY (`id`);

--
-- 테이블의 인덱스 `noticeBoard`
--
ALTER TABLE `noticeBoard`
  ADD PRIMARY KEY (`idx`);

--
-- 테이블의 인덱스 `reviewBoard`
--
ALTER TABLE `reviewBoard`
  ADD PRIMARY KEY (`idx`);

--
-- 덤프된 테이블의 AUTO_INCREMENT
--

--
-- 테이블의 AUTO_INCREMENT `noticeBoard`
--
ALTER TABLE `noticeBoard`
  MODIFY `idx` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- 테이블의 AUTO_INCREMENT `reviewBoard`
--
ALTER TABLE `reviewBoard`
  MODIFY `idx` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
