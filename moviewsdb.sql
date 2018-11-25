-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 25, 2018 at 03:16 PM
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
-- Table structure for table `listmovie`
--

CREATE TABLE `listmovie` (
  `id_list` varchar(10) NOT NULL,
  `list_name` varchar(30) NOT NULL,
  `list_rating` double NOT NULL,
  `id_user` varchar(10) NOT NULL
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
('USER1', 'Muhammad Ahsan Athallah', 'A', 'L', '12345', 'ahsanatha', '1999-10-11', 'Jakarta'),
('USER2', 'Rahma Safitri', 'R', 'P', '123', 'rahmasaf', '2001-01-01', 'Duri');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `listmovie`
--
ALTER TABLE `listmovie`
  ADD PRIMARY KEY (`id_list`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `id_user` (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
