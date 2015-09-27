# --------------------------------------------------------
# Host:                         127.0.0.1
# Server version:               5.5.45
# Server OS:                    Win64
# HeidiSQL version:             6.0.0.3603
# Date/time:                    2015-09-27 13:46:49
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

# Dumping database structure for monitoramentopaciente
CREATE DATABASE IF NOT EXISTS `monitoramentopaciente` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `monitoramentopaciente`;


# Dumping structure for table monitoramentopaciente.alertas
CREATE TABLE IF NOT EXISTS `alertas` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `sinalVitalDesconforme` varchar(30) NOT NULL,
  `batimentos` int(11) NOT NULL,
  `pressao` int(11) NOT NULL,
  `glicose` int(11) NOT NULL,
  `data` datetime NOT NULL,
  `medicacao` varchar(50) NOT NULL,
  `dosagem` smallint(6) NOT NULL,
  `pacienteId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK__paciente` (`pacienteId`),
  CONSTRAINT `FK__paciente` FOREIGN KEY (`pacienteId`) REFERENCES `paciente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Data exporting was unselected.


# Dumping structure for table monitoramentopaciente.paciente
CREATE TABLE IF NOT EXISTS `paciente` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `emailResponsavel` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Data exporting was unselected.
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
