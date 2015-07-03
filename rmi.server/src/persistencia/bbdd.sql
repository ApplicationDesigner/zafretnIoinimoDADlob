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
  `nickName` VARCHAR(45) NOT NULL,
  `numero_partida` INT NOT NULL,
  `ganador` CHAR(1),
  PRIMARY KEY (`nickName`, `numero_partida`));
  
  DROP TABLE IF EXISTS `dda`.`casino`;
  CREATE TABLE `dda`.`casino` (
  `ganancias` FLOAT NULL);
  
  DROP TABLE IF EXISTS `dda`.`configuracion`;
  CREATE TABLE `dda`.`configuracion` (
  `timeout` INT NOT NULL);
  
  /*INSERTS*/
  USE DDA;
  
  INSERT INTO jugador VALUES ('n0','Prueba0','p0',500,500);
  INSERT INTO jugador VALUES ('n1','Prueba1','p1',500,500);
  INSERT INTO jugador VALUES ('n2','Prueba2','p2',500,500);
  INSERT INTO jugador VALUES ('n3','Prueba3','p3',500,500);
  INSERT INTO jugador VALUES ('n4','Prueba4','p4',500,500);
  
  INSERT INTO partida VALUES (10,30,1000);
  INSERT INTO partida VALUES (11,45,1500);
  
  INSERT INTO partida_jugador VALUES('n0',10,'F');
  INSERT INTO partida_jugador VALUES('n1',10,'F');
  INSERT INTO partida_jugador VALUES('n2',10,'F');
  INSERT INTO partida_jugador VALUES('n3',10,'T');
  
  INSERT INTO partida_jugador VALUES('n0',11,'F');
  INSERT INTO partida_jugador VALUES('n1',11,'T');
  INSERT INTO partida_jugador VALUES('n2',11,'F');
  INSERT INTO partida_jugador VALUES('n3',11,'F');
  
  INSERT INTO casino VALUES(2500);