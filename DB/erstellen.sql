https://wdbase.de/wdb/sql-datenbanken-erstellen-und-verwalten/

-- Erstellen der Datenbank
CREATE DATABASE Belegarbeit;
USE Belegarbeit;

-- Erstellen der Tabellen

-- Tabelle Kunde
CREATE TABLE `Kunde` ( 
`K_id` INT(5)  PRIMARY KEY AUTO_INCREMENT ,
`Name` VARCHAR(255) NOT NULL,
`Vorname` VARCHAR(255) NOT NULL,
`O_id` INT(5)  NOT NULL,
`Strasse` VARCHAR(255) NOT NULL,
`Hausnummer` VARCHAR(5) NOT NULL,
`Mitglied` ENUM('Mitglied', 'bekannt', 'unbekannt') NOT NULL 
);

-- Tabelle Geräte
CREATE TABLE `Geraet` ( 
`G_id` INT(5)  PRIMARY KEY AUTO_INCREMENT ,
`Bezeichnung` VARCHAR(255) NOT NULL ,
`Anschaffungspreis` DOUBLE NOT NULL ,
`Anschaffungsdatum` DATE NOT NULL ,
`Mietpreisklasse1` DOUBLE NOT NULL ,
`Mietpreisklasse2` DOUBLE NOT NULL ,
`Mietpreisklasse3` DOUBLE NOT NULL ,
`Zustand` VARCHAR(255) NOT NULL ,
`Produktgruppe` ENUM('Licht', 'Ton', 'Video', 'Kabel', 'Sonstiges') NOT NULL 
);

-- Tabelle Miete 
CREATE TABLE `Mietvertrag` ( 
`M_id` INT(5) PRIMARY KEY AUTO_INCREMENT ,
`G_id` INT(5) NOT NULL,
`K_id` INT(5) NOT NULL,
`R_id` INT(5) ,
`Abgabe` DATE NOT NULL,
`Rueckgabe` DATE NOT NULL,
`Status` BOOLEAN
);


-- Tabelle Rechnung
CREATE TABLE `Rechnung` ( 
`R_id` INT(5) PRIMARY KEY AUTO_INCREMENT ,
`Kundenname` VARCHAR(255),
`Kundenvorname` VARCHAR(255),
`Strasse` VARCHAR(255),
`Hausnummer` VARCHAR(5),
`PLZ` VARCHAR(5),
`Ort` VARCHAR(255),
`Rechnungsdatum` DATE,
`Preis` DOUBLE,
`Status` BOOLEAN
);

 

 
-- Tabelle Ort
CREATE TABLE `Ort` ( 
`O_id` INT(5)  PRIMARY KEY AUTO_INCREMENT ,
`PLZ` VARCHAR(5) NOT NULL,
`Ort` VARCHAR(255) NOT NULL
);

 /*
-- Tabelle Bestellung`
CREATE TABLE `Bestellung` ( 
`B_id` INT(5) PRIMARY KEY AUTO_INCREMENT ,
`G_id` INT(5) NOT NULL,
`K_id` INT(5) NOT NULL,
`V_id` INT(5) NOt NULL,
`vorAbgabe` DATE NOT NULL,
`vorRückgabe` DATE NOT NULL
);
 */




-- Beziehungen

-- Beziehungen Mietevertrag Gerät
Alter Table Mietvertrag Add constraint beinhaltet foreign key(G_id) references Geraet(G_id);

-- Beziehungen Mietvertrag Kunde
Alter Table Mietvertrag Add constraint bekommt foreign key(K_id) references Kunde(K_id);

-- Beziehungen Mietvertrag Rechnung
ALTER TABLE Mietvertrag ADD CONSTRAINT gehoert foreign key(R_id) REFERENCES Rechnung(R_id);

-- Beziehungen Kunde Wohnt Ort
Alter Table Kunde Add constraint wohnt foreign key(O_id) references Ort(O_id);


-- Benutzer Admin erstellt
-- CREATE USER 'Admin'@'%' IDENTIFIED VIA mysql_native_password USING '47114711';GRANT ALL PRIVILEGES ON *.* TO 'Admin'@'%' REQUIRE NONE WITH GRANT OPTION MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0; 

-- Ort
INSERT INTO `ort`(`O_id`, `PLZ`, `Ort`) VALUES ('0','0','-');
-- Hier jetzt die ortsdaein Insert



-- Geraete
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (1,'Gereat01',619.08,'2001-07-10',0,9.78,97.84,'Gut',2);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (2,'Gereat02',289.74,'2001-07-11',0,9.74,97.40,'Gut',1);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (3,'Gereat03',943.82,'2001-07-12',0,1.19,11.92,'Gut',2);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (4,'Gereat04',507.16,'2001-07-13',0,9.31,93.10,'Gut',2);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (5,'Gereat05',288.21,'2001-07-14',0,2.34,23.39,'Gut',4);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (6,'Gereat06',728.87,'2001-07-15',0,9.19,91.87,'Gut',1);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (7,'Gereat07',486.55,'2001-07-16',0,8.76,87.56,'Gut',4);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (8,'Gereat08',314.50,'2001-07-17',0,0.31,3.09,'Gut',1);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (9,'Gereat09',808.40,'2001-07-18',0,4.46,44.62,'Gut',4);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (10,'Gereat10',182.50,'2001-07-19',0,4.50,45.04,'Gut',2);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (11,'Gereat11',527.58,'2001-07-20',0,1.25,12.50,'Gut',1);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (12,'Gereat12',462.45,'2001-07-21',0,7.44,74.42,'Gut',3);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (13,'Gereat13',633.31,'2001-07-22',0,5.77,57.67,'Gut',1);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (14,'Gereat14',414.56,'2001-07-23',0,5.10,50.95,'Gut',2);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (15,'Gereat15',357.71,'2001-07-24',0,3.62,36.21,'Gut',4);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (16,'Gereat16',93.18,'2001-07-25',0,7.40,73.97,'Gut',3);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (17,'Gereat17',87.13,'2001-07-26',0,0.96,9.62,'Gut',1);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (18,'Gereat18',58.09,'2001-07-27',0,3.55,35.53,'Gut',1);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (19,'Gereat19',232.63,'2001-07-28',0,2.35,23.53,'Gut',2);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (20,'Gereat20',465.09,'2001-07-29',0,0.46,4.58,'Gut',2);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (21,'Gereat21',696.83,'2001-07-30',0,3.22,32.17,'Gut',1);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (22,'Gereat22',188.74,'2001-07-31',0,5.29,52.92,'Gut',4);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (23,'Gereat23',829.61,'2001-08-01',0,9.92,99.19,'Gut',1);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (24,'Gereat24',252.06,'2001-08-02',0,3.04,30.36,'Gut',2);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (25,'Gereat25',853.84,'2001-08-03',0,9.48,94.82,'Gut',3);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (26,'Gereat26',878.07,'2001-08-04',0,8.41,84.15,'Gut',3);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (27,'Gereat27',234.09,'2001-08-05',0,4.78,47.77,'Gut',2);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (28,'Gereat28',277.29,'2001-08-06',0,1.24,12.36,'Gut',3);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (29,'Gereat29',745.91,'2001-08-07',0,9.02,90.20,'Gut',2);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (30,'Gereat30',119.24,'2001-08-08',0,1.55,15.50,'Gut',4);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (31,'Gereat31',34.17,'2001-08-09',0,3.49,34.91,'Gut',1);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (32,'Gereat32',609.77,'2001-08-10',0,2.20,21.97,'Gut',3);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (33,'Gereat33',974.46,'2001-08-11',0,9.64,96.44,'Gut',2);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (34,'Gereat34',467.48,'2001-08-12',0,8.79,87.88,'Gut',2);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (35,'Gereat35',414.12,'2001-08-13',0,6.65,66.48,'Gut',2);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (36,'Gereat36',331.82,'2001-08-14',0,4.09,40.95,'Gut',1);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (37,'Gereat37',391.83,'2001-08-15',0,0.12,1.17,'Gut',2);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (38,'Gereat38',542.78,'2001-08-16',0,6.32,63.21,'Gut',1);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (39,'Gereat39',411.84,'2001-08-17',0,0.03,0.26,'Gut',2);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (40,'Gereat40',962.95,'2001-08-18',0,0.78,7.76,'Gut',3);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (41,'Gereat41',676.87,'2001-08-19',0,3.54,35.44,'Gut',2);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (42,'Gereat42',603.61,'2001-08-20',0,3.25,32.54,'Gut',3);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (43,'Gereat43',798.79,'2001-08-21',0,4.85,48.54,'Gut',1);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (44,'Gereat44',937.67,'2001-08-22',0,6.18,61.81,'Gut',2);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (45,'Gereat45',731.30,'2001-08-23',0,9.07,90.70,'Gut',1);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (46,'Gereat46',191.34,'2001-08-24',0,1.15,11.47,'Gut',2);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (47,'Gereat47',35.61,'2001-08-25',0,3.56,35.58,'Gut',2);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (48,'Gereat48',749.98,'2001-08-26',0,6.79,67.87,'Gut',3);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (49,'Gereat49',567.98,'2001-08-27',0,4.54,45.40,'Gut',4);
INSERT INTO geraet (G_id, Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES (50,'Gereat50',783.86,'2001-08-28',0,2.36,23.62,'Gut',2);


-- Kunden

Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(1,'Nachname1','Vorname1',78,'Strasse1','1a','2');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(2,'Nachname2','Vorname2',89,'Strasse2','2a','1');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(3,'Nachname3','Vorname3',80,'Strasse3','3a','3');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(4,'Nachname4','Vorname4',36,'Strasse4','4a','2');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(5,'Nachname5','Vorname5',43,'Strasse5','5a','3');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(6,'Nachname6','Vorname6',29,'Strasse6','6a','1');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(7,'Nachname7','Vorname7',68,'Strasse7','7a','3');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(8,'Nachname8','Vorname8',9,'Strasse8','8a','2');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(9,'Nachname9','Vorname9',70,'Strasse9','9a','2');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(10,'Nachname10','Vorname10',36,'Strasse10','10a','1');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(11,'Nachname11','Vorname11',6,'Strasse11','11a','1');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(12,'Nachname12','Vorname12',38,'Strasse12','12a','1');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(13,'Nachname13','Vorname13',73,'Strasse13','13a','1');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(14,'Nachname14','Vorname14',88,'Strasse14','14a','3');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(15,'Nachname15','Vorname15',41,'Strasse15','15a','2');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(16,'Nachname16','Vorname16',12,'Strasse16','16a','2');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(17,'Nachname17','Vorname17',97,'Strasse17','17a','1');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(18,'Nachname18','Vorname18',27,'Strasse18','18a','2');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(19,'Nachname19','Vorname19',24,'Strasse19','19a','2');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(20,'Nachname20','Vorname20',61,'Strasse20','20a','3');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(21,'Nachname21','Vorname21',58,'Strasse21','21a','3');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(22,'Nachname22','Vorname22',64,'Strasse22','22a','2');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(23,'Nachname23','Vorname23',91,'Strasse23','23a','3');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(24,'Nachname24','Vorname24',72,'Strasse24','24a','3');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(25,'Nachname25','Vorname25',61,'Strasse25','25a','1');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(26,'Nachname26','Vorname26',7,'Strasse26','26a','1');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(27,'Nachname27','Vorname27',86,'Strasse27','27a','3');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(28,'Nachname28','Vorname28',18,'Strasse28','28a','3');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(29,'Nachname29','Vorname29',30,'Strasse29','29a','2');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(30,'Nachname30','Vorname30',63,'Strasse30','30a','1');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(31,'Nachname31','Vorname31',57,'Strasse31','31a','3');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(32,'Nachname32','Vorname32',79,'Strasse32','32a','2');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(33,'Nachname33','Vorname33',78,'Strasse33','33a','3');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(34,'Nachname34','Vorname34',14,'Strasse34','34a','2');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(35,'Nachname35','Vorname35',87,'Strasse35','35a','2');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(36,'Nachname36','Vorname36',13,'Strasse36','36a','3');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(37,'Nachname37','Vorname37',40,'Strasse37','37a','2');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(38,'Nachname38','Vorname38',58,'Strasse38','38a','2');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(39,'Nachname39','Vorname39',5,'Strasse39','39a','3');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(40,'Nachname40','Vorname40',8,'Strasse40','40a','2');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(41,'Nachname41','Vorname41',91,'Strasse41','41a','2');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(42,'Nachname42','Vorname42',96,'Strasse42','42a','2');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(43,'Nachname43','Vorname43',89,'Strasse43','43a','2');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(44,'Nachname44','Vorname44',98,'Strasse44','44a','1');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(45,'Nachname45','Vorname45',23,'Strasse45','45a','1');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(46,'Nachname46','Vorname46',59,'Strasse46','46a','1');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(47,'Nachname47','Vorname47',99,'Strasse47','47a','1');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(48,'Nachname48','Vorname48',13,'Strasse48','48a','1');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(49,'Nachname49','Vorname49',87,'Strasse49','49a','3');
Insert into Kunde (K_id, name, vorname, o_id, strasse, hausnummer, Mitglied) Values(50,'Nachname50','Vorname50',69,'Strasse50','50a','2');





-- Rechnung

Insert into Rechnung  (R_id, Kundenname, Kundenvorname, Strasse, Hausnummer, PLZ, Ort, Rechnungsdatum, preis, Status ) Values(1,'Nachname1','Vorname1','Strasse1','1a','24996','Ahneby','2020-07-20',46.87 ,true);
Insert into Rechnung  (R_id, Kundenname, Kundenvorname, Strasse, Hausnummer, PLZ, Ort, Rechnungsdatum, preis, Status ) Values(2,'Nachname8','Vorname8','Strasse8','8a','52074','Aachen','2020-08-01',0,true);
Insert into Rechnung  (R_id, Kundenname, Kundenvorname, Strasse, Hausnummer, PLZ, Ort, Rechnungsdatum, preis, Status ) Values(3,'Nachname7','Vorname7','Strasse7','7a','27367','Ahausen','2020-08-01',0,true);
Insert into Rechnung  (R_id, Kundenname, Kundenvorname, Strasse, Hausnummer, PLZ, Ort, Rechnungsdatum, preis, Status ) Values(4,'Nachname49','Vorname49','Strasse49','49a','23623','Ahrensb?k','2020-08-13',0,true);
Insert into Rechnung  (R_id, Kundenname, Kundenvorname, Strasse, Hausnummer, PLZ, Ort, Rechnungsdatum, preis, Status ) Values(5,'Nachname4','Vorname4','Strasse4','4a','55767','Achtelsbach','2020-09-01',0,true);
Insert into Rechnung  (R_id, Kundenname, Kundenvorname, Strasse, Hausnummer, PLZ, Ort, Rechnungsdatum, preis, Status ) Values(6,'Nachname7','Vorname7','Strasse7','7a','27367','Ahausen','2020-09-19',0,true);
Insert into Rechnung  (R_id, Kundenname, Kundenvorname, Strasse, Hausnummer, PLZ, Ort, Rechnungsdatum, preis, Status ) Values(7,'Nachname8','Vorname8','Strasse8','8a','52074','Aachen','2020-10-10',0,false);
Insert into Rechnung  (R_id, Kundenname, Kundenvorname, Strasse, Hausnummer, PLZ, Ort, Rechnungsdatum, preis, Status ) Values(8,'Nachname3','Vorname3','Strasse3','3a','31708','Ahnsen','2020-10-10',100,false);



-- Mietvertrag

Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(1,2,1,1,'2020-07-20','2020-07-30',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(2,7,1,1,'2020-07-20','2020-07-30',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(3,6,1,1,'2020-07-20','2020-07-30',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(4,11,1,1,'2020-07-20','2020-07-30',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(5,27,1,1,'2020-07-22','2020-07-30',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(6,46,1,1,'2020-07-22','2020-07-30',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(7,45,1,1,'2020-07-22','2020-07-30',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(8,15,1,1,'2020-07-22','2020-07-30',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(9,9,8,2,'2020-08-01','2020-08-11',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(10,19,8,2,'2020-08-01','2020-08-11',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(11,8,8,2,'2020-08-01','2020-08-11',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(12,18,8,2,'2020-08-01','2020-08-11',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(13,17,8,2,'2020-08-01','2020-08-11',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(14,49,7,3,'2020-08-01','2020-08-11',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(15,4,7,3,'2020-08-01','2020-08-11',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(16,48,7,3,'2020-08-01','2020-08-11',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(17,5,7,3,'2020-08-01','2020-08-11',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(18,13,7,3,'2020-08-01','2020-08-11',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(19,48,7,3,'2020-08-01','2020-08-11',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(20,34,49,4,'2020-08-13','2020-08-25',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(21,33,49,4,'2020-08-13','2020-08-25',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(22,30,49,4,'2020-08-13','2020-08-25',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(23,22,49,4,'2020-08-13','2020-08-25',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(24,22,49,4,'2020-08-13','2020-08-25',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(25,10,49,4,'2020-08-13','2020-08-25',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(26,1,49,4,'2020-08-13','2020-08-25',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(27,9,49,4,'2020-08-13','2020-08-25',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(28,46,49,4,'2020-08-13','2020-08-25',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(29,41,49,4,'2020-08-13','2020-08-25',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(30,18,49,4,'2020-08-13','2020-08-25',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(31,3,49,4,'2020-08-13','2020-08-25',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(32,1,49,4,'2020-08-13','2020-08-25',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(33,4,4,5,'2020-09-01','2020-09-04',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(34,41,7,6,'2020-09-19','2020-09-22',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(35,39,7,6,'2020-09-19','2020-09-22',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(36,20,7,6,'2020-09-19','2020-09-22',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(37,38,7,6,'2020-09-19','2020-09-22',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(38,10,7,6,'2020-09-19','2020-09-22',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(39,9,7,6,'2020-09-19','2020-10-01',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(40,24,7,6,'2020-09-19','2020-10-01',true);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(41,40,8,7,'2020-10-10','2020-10-22',false);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(42,36,8,7,'2020-10-10','2020-10-22',false);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(43,6,8,7,'2020-10-10','2020-10-22',false);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(44,12,8,7,'2020-10-10','2020-10-22',false);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(45,5,8,7,'2020-10-10','2020-10-22',false);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(46,33,8,7,'2020-10-10','2020-10-22',false);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(47,32,3,7,'2020-10-10','2020-10-22',false);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(48,40,3,8,'2020-10-10','2020-10-22',false);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(49,24,3,8,'2020-10-10','2020-10-22',false);
Insert into Mietvertrag (M_id, G_id, K_id, R_id, Abgabe, Rueckgabe, Status) Values(50,30,3,8,'2020-10-10','2020-10-22',false);

