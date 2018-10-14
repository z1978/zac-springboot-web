-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2018-10-14 04:35:12
-- 服务器版本： 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `spring-thymeleaf`
--

-- --------------------------------------------------------

--
-- 表的结构 `t_product`
--

CREATE TABLE IF NOT EXISTS `t_product` (
  `product_id` int(11) NOT NULL,
  `creator_id` int(11) NOT NULL,
  `status` smallint(6) DEFAULT NULL,
  `name` text NOT NULL,
  `note` text,
  `description_list` text,
  `description_detail` text,
  `search_word` text,
  `free_area` text,
  `del_flg` smallint(6) NOT NULL DEFAULT '0',
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `t_product`
--

INSERT INTO `t_product` (`product_id`, `creator_id`, `status`, `name`, `note`, `description_list`, `description_detail`, `search_word`, `free_area`, `del_flg`, `create_date`, `update_date`) VALUES
(1, 1, 1, 'ディナーフォーク', NULL, NULL, 'セットで揃えたいディナー用のカトラリー。\n定番の銀製は、シルバー特有の美しい輝きと柔らかな曲線が特徴です。適度な重みと日本人の手に合いやすいサイズ感で長く愛用いただけます。\n最高級プラチナフォークは、贈り物としても人気です。', NULL, NULL, 0, '2017-07-13 00:59:04', '2017-07-13 00:59:04'),
(2, 1, 1, 'パーコレーター', NULL, NULL, '\nパーコレーターはコーヒーの粉をセットして直火にかけて抽出する器具です。\nアウトドアでも淹れたてのコーヒーをお楽しみいただけます。\nいまだけ、おいしい淹れ方の冊子つきです。', NULL, NULL, 0, '2017-07-13 00:59:04', '2017-07-13 00:59:04'),

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
