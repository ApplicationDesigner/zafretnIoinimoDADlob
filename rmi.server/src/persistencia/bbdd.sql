CREATE DATABASE dda;

DROP TABLE IF EXISTS `dda`.`jugador`;

CREATE TABLE `dda`.`jugador` (
  `nickName` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `saldoInicial` FLOAT NULL,
  `saldo` FLOAT NULL,
  PRIMARY KEY (`nickName`));
  
DROP TABLE IF EXISTS `dda`.`partida`;

  CREATE TABLE `dda`.`partida` (
  `numero_partida` INT NOT NULL,
  `duracion` INT NULL,
  `total_apostado` FLOAT NULL,
  PRIMARY KEY (`numero_partida`));
  
  
DROP TABLE IF EXISTS `dda`.`partida_jugador`;

  CREATE TABLE `dda`.`partida_jugador` (
  `nickName` INT NOT NULL,
  `numero_partida` INT NOT NULL,
  `ganador` CHAR(1),
  PRIMARY KEY (`nickName`, `numero_partida`));
  
  DROP TABLE IF EXISTS `dda`.`casino`;
  CREATE TABLE `dda`.`casino` (
  `ganancias` FLOAT NULL);
  
  DROP TABLE IF EXISTS `dda`.`configuracion`;
  CREATE TABLE `dda`.`configuracion` (
  `timeout` INT NOT NULL);