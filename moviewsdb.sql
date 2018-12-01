-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 01, 2018 at 03:07 PM
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
('USER1', 'Muhammad Ahsan Athallah', 'A', 'L', '123', 'ahsanatha', '1999-10-11', 'Jakarta');

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
