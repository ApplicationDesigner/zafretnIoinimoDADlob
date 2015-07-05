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

INSERT INTO jugador VALUES ('j0','Jugador0','p0',500,500);
INSERT INTO jugador VALUES ('j1','Jugador1','p1',500,500);
INSERT INTO jugador VALUES ('j2','Jugador2','p2',500,500);
INSERT INTO jugador VALUES ('j3','Jugador3','p3',500,500);
INSERT INTO jugador VALUES ('j4','Jugador4','p4',500,500);
INSERT INTO jugador VALUES ('j5','Jugador5','p5',500,500);
INSERT INTO jugador VALUES ('j6','Jugador6','p6',500,500);
INSERT INTO jugador VALUES ('j7','Jugador7','p7',500,500);
INSERT INTO jugador VALUES ('j8','Jugador8','p8',500,500);
INSERT INTO jugador VALUES ('j9','Jugador9','p9',500,500);