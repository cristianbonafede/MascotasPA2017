CREATE SCHEMA `Mascotas` DEFAULT CHARACTER SET utf8 ;

use Mascotas;

CREATE TABLE `usuario` (
	  `login` varchar(128) NOT NULL,
	  `email` varchar(128) DEFAULT NULL,
	  `finVigencia` date DEFAULT NULL,
	  `inicioVigencia` date NOT NULL,
	  `nombre` varchar(128) NOT NULL,
	  `password` varchar(255) NOT NULL,
	  PRIMARY KEY (`login`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `rol` (
	  `id` int(11) NOT NULL AUTO_INCREMENT,
	  `nombre` varchar(255) DEFAULT NULL,
	  `usuarioId` varchar(255) DEFAULT NULL,
	  PRIMARY KEY (`id`),
	  KEY `FK141AFAB062235` (`usuarioId`),
	  CONSTRAINT `FK141AFAB062235` FOREIGN KEY (`usuarioId`) REFERENCES `usuario` (`login`)
	) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


INSERT INTO `usuario` (`login`,`email`,`finVigencia`,`inicioVigencia`,`nombre`,`password`) VALUES ('admin','admin@mascotas.com',NULL,'1999-12-31','Administrador','ISMvKXpXpadDiUoOSoAfww==');
INSERT INTO `rol` (`id`,`nombre`,`usuarioId`) VALUES (1,'USER','admin');
INSERT INTO `rol` (`id`,`nombre`,`usuarioId`) VALUES (2,'ADMIN','admin');


CREATE TABLE `provincia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;



INSERT INTO `provincia` (`id`,`nombre`) VALUES (1, 'Mendoza');
INSERT INTO `provincia` (`id`,`nombre`) VALUES (2, 'San Juan');
INSERT INTO `provincia` (`id`,`nombre`) VALUES (3, 'San Luis');
INSERT INTO `provincia` (`id`,`nombre`) VALUES (4, 'La Rioja');

