-- --------------------------------------------------------
-- Hôte:                         127.0.0.1
-- Version du serveur:           8.0.29 - MySQL Community Server - GPL
-- SE du serveur:                Win64
-- HeidiSQL Version:             12.0.0.6468
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Listage des données de la table nf28.artiste : ~5 rows (environ)
DELETE FROM `artiste`;
INSERT INTO `artiste` (`id`, `nom`, `prenom`, `email`, `isArtiste`, `isEmployer`, `profession`, `niveau`, `cv`, `oeuvre`, `mdp`) VALUES
	(1, 'batistella', 'gabriel', 'batistella.gabriel@gmail.com', 1, 0, 'guitariste', 'Debutant', NULL, NULL, '1234'),
	(2, 'bessonnet', 'louis', 'bessonnet.louis@gmail.com', 1, 1, 'chanteur', 'Debutant', '', '', '5678'),
	(4, 'tetard', 'pierre', 'tetard.pierre@gmail.com', 1, 0, 'DJ', 'Professionnelle', NULL, NULL, '1213'),
	(6, 'bob', 'ben', 'B@B', 1, 0, 'chanteur', 'Amateur', NULL, NULL, 'bbb'),
	(10, 'test', 'tt', 'Test@gmail.com', 1, 0, 'musicien', 'Amateur', '', '', NULL);

-- Listage des données de la table nf28.employer : ~3 rows (environ)
DELETE FROM `employer`;
INSERT INTO `employer` (`id`, `nom`, `prenom`, `email`, `isArtiste`, `isEmployer`, `type`, `certificat`, `mdp`) VALUES
	(5, 'alice', 'anne', 'A@A', 0, 1, 'Organisation', 'aaaaa', 'aaa'),
	(2, 'bessonnet', 'louis', 'bessonnet.louis@gmail.com', 1, 1, 'Particulier', '-', '5678'),
	(3, 'blanchard', 'louise', 'blanchard.louise@gmail.com', 0, 1, 'Entreprise', 'aaa', '9101');

-- Listage des données de la table nf28.offre : ~4 rows (environ)
DELETE FROM `offre`;
INSERT INTO `offre` (`id`, `titre`, `description`, `argent`, `nbCandidate`, `recurrence`, `durre`, `adresse`, `typeOffre`, `author`, `candidate`) VALUES
	(1, 'Boys band', 'groupe de jeunes musiciens', 2000, 2, '-', '1 soirée', 'Paris', 'Prestation', 2, '1;4;8;10'),
	(2, 'DJ pour mariage', 'style à la carte', 1500, 1, '-', '1 soirée', 'Compiegne', 'Prestation', 3, '4;2'),
	(3, 'Recherche soliste', 'soliste pour chorale amateure', 0, 0, '-', '1 an', 'Lille', 'Emploi', 3, '6;'),
	(4, 'Recherche guitariste', 'pour compléter groupe de rock', 0, 1, '-', '6 mois', 'Paris', 'Emploi', 2, '2');

-- Listage des données de la table nf28.user : ~7 rows (environ)
DELETE FROM `user`;
INSERT INTO `user` (`id`, `nom`, `prenom`, `email`, `isArtiste`, `isEmployer`, `mdp`) VALUES
	(1, 'batistella', 'gabriel', 'batistella.gabriel@gmail.com', 1, 0, '1234'),
	(2, 'bessonnet', 'louis', 'bessonnet.louis@gmail.com', 1, 1, '5678'),
	(3, 'blanchard', 'louise', 'blanchard.louise@gmail.com', 0, 1, '9101'),
	(4, 'tetard', 'pierre', 'tetard.pierre@gmail.com', 1, 0, '1213'),
	(5, 'alice', 'anne', 'A@A', 0, 1, 'aaa'),
	(6, 'bob', 'ben', 'B@B', 1, 0, 'bbb'),
	(10, 'test', 'tttt', 'Test@gmail.com', 1, 0, '123456');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
