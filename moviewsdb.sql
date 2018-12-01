-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 01, 2018 at 05:03 PM
-- Server version: 10.1.25-MariaDB
-- PHP Version: 7.1.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `moviewsdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `movies`
--

CREATE TABLE `movies` (
  `id_mov` varchar(10) NOT NULL,
  `title` varchar(50) NOT NULL,
  `sinopsis` text,
  `release` date DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `director` varchar(30) DEFAULT NULL,
  `studio` varchar(30) DEFAULT NULL,
  `ratingfilm` double NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `movies`
--

INSERT INTO `movies` (`id_mov`, `title`, `sinopsis`, `release`, `duration`, `director`, `studio`, `ratingfilm`) VALUES
('MOV1', 'Iron Man', 'Tony Stark. Genius, billionaire, playboy, philanthropist. Son of legendary inventor and weapons contractor Howard Stark.', '1988-01-02', 124, 'jon favreaus', 'marvel interprises', 3),
('MOV2', 'venom', 'When Eddie Brock acquires the powers of a symbiote, he will have to release his alter-ego \"Venom\" to save his life.', '2018-08-05', 112, 'Ruben Fleischer', 'columbia pictures corporation', 3),
('MOV3', 'kimi no nawa', 'Mitsuha is the daughter of the mayor of a small mountain town.', '2016-04-07', 106, 'makoto shinkai', 'comix wave film', 5),
('MOV4', 'the nun', 'When a young nun at a cloistered abbey in Romania takes her own life, a priest with a haunted past and a novitiate on the threshold of her final vows are sent by the Vatican to investigate. ', '1988-01-05', 96, 'corin hardy', 'atomic monster', 5),
('MOV5', 'Captain America: Civil War', 'With many people fearing the actions of super heroes,', '1988-01-01', 247, 'anthony russo', 'marvel studio', 4);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` varchar(10) NOT NULL,
  `nama_lengkap` varchar(50) NOT NULL,
  `tipe` char(1) NOT NULL,
  `jenis_kelamin` char(1) NOT NULL,
  `password` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `tempat_lahir` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `nama_lengkap`, `tipe`, `jenis_kelamin`, `password`, `username`, `tgl_lahir`, `tempat_lahir`) VALUES
('USER1', 'Muhammad Ahsan Athallah', 'A', 'L', '123', 'ahsanatha', '1999-10-11', 'Jakarta'),
('USER2', 'muhamad zikri syahbani', 'A', 'L', '1234', 'zikrisy', '1998-12-18', 'bandung'),
('USER3', 'aldrin marbun', 'R', 'L', '123', 'aljpmarbun', '1999-02-07', 'jakarta'),
('USER4', 'maman abdulrahman', 'R', 'L', '123', 'rahmanabdul', '2000-07-08', 'garut'),
('USER5', 'sinta cyntia', 'R', 'P', '123', 'icaica', '1998-03-24', 'padang');

-- --------------------------------------------------------

--
-- Table structure for table `usermovies`
--

CREATE TABLE `usermovies` (
  `id_ratrev` varchar(10) NOT NULL,
  `id_mov` varchar(10) NOT NULL,
  `id_user` varchar(10) NOT NULL,
  `rating_user` double NOT NULL,
  `review_user` mediumtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usermovies`
--

INSERT INTO `usermovies` (`id_ratrev`, `id_mov`, `id_user`, `rating_user`, `review_user`) VALUES
('RR1', 'MOV1', 'USER3', 4, 'mantap djiwa bosque'),
('RR10', 'MOV4', 'USER2', 5, 'De best!'),
('RR2', 'MOV1', 'USER4', 2, 'kurang memuaskan'),
('RR3', 'MOV1', 'USER5', 5, 'rame banget ini film'),
('RR4', 'MOV2', 'USER1', 4, 'Keren Banget!'),
('RR5', 'MOV3', 'USER1', 5, 'Favorite Anime Movie!'),
('RR6', 'MOV4', 'USER1', 1, 'ga suka film horor.'),
('RR7', 'MOV5', 'USER1', 3, 'meh'),
('RR8', 'MOV1', 'USER2', 1, 'biasa aja'),
('RR9', 'MOV5', 'USER2', 4, 'BAGUS PARAH. WAJIB NONTON.');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `movies`
--
ALTER TABLE `movies`
  ADD PRIMARY KEY (`id_mov`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `id_user` (`id_user`);

--
-- Indexes for table `usermovies`
--
ALTER TABLE `usermovies`
  ADD PRIMARY KEY (`id_ratrev`),
  ADD UNIQUE KEY `id_ratrev` (`id_ratrev`),
  ADD KEY `FK_usermovies_movies` (`id_mov`),
  ADD KEY `FK_usermovies_user` (`id_user`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `usermovies`
--
ALTER TABLE `usermovies`
  ADD CONSTRAINT `FK_usermovies_movies` FOREIGN KEY (`id_mov`) REFERENCES `movies` (`id_mov`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_usermovies_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
