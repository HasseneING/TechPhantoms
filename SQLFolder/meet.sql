-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 04 mars 2021 à 18:20
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `pidev`
--

-- --------------------------------------------------------

--
-- Structure de la table `meet`
--

CREATE TABLE `meet` (
  `room_id` int(11) NOT NULL,
  `teacherID` int(11) DEFAULT NULL,
  `room_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `meet`
--

INSERT INTO `meet` (`room_id`, `teacherID`, `room_name`) VALUES
(1, NULL, 'ahlem'),
(3, NULL, ''),
(4, NULL, 'syrine'),
(5, NULL, 'Syrine3A13'),
(6, NULL, 'ahlem3A13'),
(8, NULL, 'PIDEV3A13'),
(9, NULL, 'PIDEV3A13');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `meet`
--
ALTER TABLE `meet`
  ADD PRIMARY KEY (`room_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `meet`
--
ALTER TABLE `meet`
  MODIFY `room_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
