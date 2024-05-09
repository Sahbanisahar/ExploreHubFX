-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 08, 2024 at 08:44 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `explorehub`
--

-- --------------------------------------------------------

--
-- Table structure for table `blog`
--

CREATE TABLE `blog` (
  `id` int(11) NOT NULL,
  `cover` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `author` varchar(100) NOT NULL,
  `content` text DEFAULT NULL,
  `liked` tinyint(1) DEFAULT 0,
  `disliked` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `blog`
--

INSERT INTO `blog` (`id`, `cover`, `title`, `author`, `content`, `liked`, `disliked`) VALUES
(20, 'Cover.jpg', 'The Sweet Side of Tunisia', 'Sahar Sahbani', 'Tunisia, a country with a deep affection for confectionery, offers a myriad of traditional and Western sweets that are as rich in history as they are in flavor. From handmade sweets to crunchy cookies and delightful pastries', 0, 0),
(21, 'cover.jpg', 'Your Ultimate Guide to Visit El Jem ', 'Sahar Sahbani', 'As you scroll down on Instagram, you may have encountered those reels about guessing the destination from a compilation of photos. As for Tunisia-related content, this may startle you as it always features a Roman Colosseum like site that', 0, 0),
(22, 'Cover.jpg', 'The archaeological site of Sbeitla', 'Sahar Sahbani', 'Unlike the more frequented archaeological sites that dot the Mediterranean basin, Sbeitla, or Sufetula as it was known in antiquity, provides a distinct glimpse into the architectural and cultural melding that occurred in this', 0, 0),
(23, 'cover.jpg', 'Star Wars Filming Locations', 'Sahar Sahbani', 'It\'s a little-known fact that the planet Tatooine, one of the most iconic locations in the well-known Star Wars movies, owes its name to a real Tunisian city: Tataouine. ', 0, 0),
(24, 'cover.jpg', 'Discover Tunisia\'s Top Concept Stores', 'Sahar Sahbani', 'Concept stores guarantee a unique shopping experience where creativity and tradition converge. With our curated list, explore the fusion of contemporary design and authentic craftsmanship', 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `blog`
--
ALTER TABLE `blog`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `blog`
--
ALTER TABLE `blog`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
