-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 31, 2022 at 08:36 AM
-- Server version: 5.7.24
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gestion_stock`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `email` varchar(20) NOT NULL,
  `mdp` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `nom`, `email`, `mdp`) VALUES
(1, 'folan', 'admin@gmail.com', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `nom` varchar(25) NOT NULL,
  `description` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `nom`, `description`) VALUES
(1, 'Legume', 'les produits des Legume'),
(2, 'Fruit', 'les produits des fruit'),
(3, 'Boisson', 'Les boissons');

-- --------------------------------------------------------

--
-- Table structure for table `clients`
--

CREATE TABLE `clients` (
  `id` int(11) NOT NULL,
  `email` varchar(60) NOT NULL,
  `mdp` varchar(60) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `tel` varchar(8) NOT NULL,
  `sexe` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `clients`
--

INSERT INTO `clients` (`id`, `email`, `mdp`, `nom`, `tel`, `sexe`) VALUES
(1, 'ahmed@gmail.com', 'azert', 'ahmed ben ali', '54852564', 'Homme'),
(2, 'marwa@gmail.com', '', 'marwa aliss', '17892546', 'Femme');

-- --------------------------------------------------------

--
-- Table structure for table `commandes`
--

CREATE TABLE `commandes` (
  `id` int(11) NOT NULL,
  `produits` varchar(30) NOT NULL,
  `userid` int(11) NOT NULL,
  `quantitie` double NOT NULL,
  `mtt` double NOT NULL,
  `statut` varchar(20) NOT NULL DEFAULT 'non confirmé'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `commandes`
--

INSERT INTO `commandes` (`id`, `produits`, `userid`, `quantitie`, `mtt`, `statut`) VALUES
(12, 'Friez', 1, 4, 4.5, 'confirmé'),
(13, 'Banane', 2, 2, 6.5, 'livré'),
(14, 'Friez', 1, 5, 4.5, 'non confirmé'),
(15, 'Tomate', 1, 2, 1.3, 'non confirmé'),
(16, 'Fanta (1.5L)', 1, 1, 2.3, 'non confirmé'),
(17, 'Friez', 1, 2, 4.5, 'non confirmé'),
(18, 'Tomate', 2, 2, 1.3, 'non confirmé'),
(19, 'Banane', 2, 1, 6.5, 'non confirmé');

-- --------------------------------------------------------

--
-- Table structure for table `produits`
--

CREATE TABLE `produits` (
  `id` int(11) NOT NULL,
  `designation` varchar(60) NOT NULL,
  `categorie` varchar(25) NOT NULL,
  `quantite` double NOT NULL,
  `prix` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `produits`
--

INSERT INTO `produits` (`id`, `designation`, `categorie`, `quantite`, `prix`) VALUES
(1, 'Fanta (1.5L)', 'Boisson', 495, 2.3),
(2, 'Coca Cola (1.5L)', 'Boisson', 199, 2.3),
(3, 'Banane', 'Fruit', 332, 6.5),
(4, 'Friez', 'Fruit', 348, 4.5),
(5, 'Tomate', 'Legume', 388, 1.3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `commandes`
--
ALTER TABLE `commandes`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `commandes`
--
ALTER TABLE `commandes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
