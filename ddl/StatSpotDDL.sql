CREATE DATABASE IF NOT EXISTS `stat-spot-db`;
USE `stat-spot-db`;

CREATE TABLE IF NOT EXISTS League (
leagueId INT, 
type VARCHAR(128), 
subType VARCHAR(128),
name VARCHAR(128),
PRIMARY KEY(leagueId)
);


CREATE TABLE IF NOT EXISTS Country (
countryId INT, 
name VARCHAR(32), 
continent VARCHAR(32),
PRIMARY KEY(countryId)
);


CREATE TABLE IF NOT EXISTS Tournament (
tournamentId INT, 
name VARCHAR(32), 
year INT, 
description VARCHAR(256),
PRIMARY KEY(tournamentId)
);


CREATE TABLE IF NOT EXISTS Club (
clubId INT, 
name VARCHAR(128), 
owner VARCHAR(128), 
established INT, 
totalMarketValue REAL, 
squadSize INT, 
averageAge REAL, 
totalNationalTeamPlayers INT,
PRIMARY KEY(clubId)
);


CREATE TABLE `Game` (
`gameId` int NOT NULL,
`season` varchar(128) DEFAULT NULL,
`round` varchar(128) DEFAULT NULL,
`gameDate` date DEFAULT NULL,
`homeClubId` int DEFAULT NULL,
`awayClubId` int DEFAULT NULL,
`homeClubGoals` int DEFAULT NULL,
`awayClubGoals` int DEFAULT NULL,
PRIMARY KEY (`gameId`),
CONSTRAINT `GameToClub-fk1` FOREIGN KEY (`homeClubId`) REFERENCES `Club` (`clubId`),
CONSTRAINT `GameToClub-fk2` FOREIGN KEY (`awayClubId`) REFERENCES `Club` (`clubId`)
);


CREATE TABLE IF NOT EXISTS UserDetail (
userId INT, 
userName VARCHAR(64), 
password VARCHAR(32), 
email VARCHAR(32),
firstName VARCHAR(32), 
lastName VARCHAR(32),
PRIMARY KEY(userId)
);


CREATE TABLE IF NOT EXISTS Sport (
sportId INT, 
name VARCHAR(128), 
description VARCHAR(256), 
playerCount INT, 
duration REAL,
PRIMARY KEY(sportId)
);


CREATE TABLE IF NOT EXISTS Coach (
coachId INT, 
name VARCHAR(128), 
countryId INT, 
dateOfBirth DATE, 
clubId INT,
PRIMARY KEY(coachId),
CONSTRAINT `CocahToClub-fk1` FOREIGN KEY (`clubId`) REFERENCES `Club` (`clubId`),
CONSTRAINT `CocahToCountry-fk2` FOREIGN KEY (`countryId`) REFERENCES `Country` (`countryId`)
);


CREATE TABLE IF NOT EXISTS Athlete (
athleteId INT, 
name VARCHAR(128), 
dateOfBirth DATE, 
position VARCHAR(128), 
marketValue REAL, 
sex VARCHAR(12), 
sportId INT, 
countryId INT, 
clubId INT,
PRIMARY KEY(athleteId),
CONSTRAINT `AthleteToSport-fk1` FOREIGN KEY (`sportId`) REFERENCES `Sport` (`sportId`),
CONSTRAINT `AthleteToCountry-fk2` FOREIGN KEY (`countryId`) REFERENCES `Country` (`countryId`),
CONSTRAINT `AthleteToClub-fk3` FOREIGN KEY (`clubId`) REFERENCES `Club` (`clubId`)
);


CREATE TABLE IF NOT EXISTS Train (
athleteId INT, 
coachId INT,
PRIMARY KEY(athleteId, coachId),
CONSTRAINT `TrainToAthlete-fk1` FOREIGN KEY (`athleteId`) REFERENCES `Athlete` (`athleteId`),
CONSTRAINT `TrainToCoach-fk2` FOREIGN KEY (`coachId`) REFERENCES `Coach` (`coachId`)
);


CREATE TABLE IF NOT EXISTS Compete (
tournamentId INT, 
athleteId INT,
PRIMARY KEY(tournamentId, athleteId),
CONSTRAINT `CompeteToTournament-fk1` FOREIGN KEY (`tournamentId`) REFERENCES `Tournament` (`tournamentId`),
CONSTRAINT `CompeteToAthlete-fk2` FOREIGN KEY (`athleteId`) REFERENCES `Athlete` (`athleteId`)
);


CREATE TABLE IF NOT EXISTS PartOf (
clubId INT, 
leagueId INT,
PRIMARY KEY(clubId, leagueId),
CONSTRAINT `PartOfToClub-fk1` FOREIGN KEY (`clubId`) REFERENCES `Club` (`clubId`),
CONSTRAINT `PartOfToLeague-fk2` FOREIGN KEY (`leagueId`) REFERENCES `League` (`leagueId`)
);


CREATE TABLE IF NOT EXISTS Participate (
clubId INT, 
gameId INT,
PRIMARY KEY(clubId, gameId),
CONSTRAINT `ParticipateToClub-fk1` FOREIGN KEY (`clubId`) REFERENCES `Club` (`clubId`),
CONSTRAINT `ParticipateToLeague-fk2` FOREIGN KEY (`gameId`) REFERENCES `Game` (`gameId`)
);


CREATE TABLE IF NOT EXISTS Subscribe (
gameId INT, 
userId INT, 
pay REAL,
PRIMARY KEY(gameId, userId),
CONSTRAINT `SubscribeToGame-fk1` FOREIGN KEY (`gameId`) REFERENCES `Game` (`gameId`),
CONSTRAINT `SubscribeToUser-fk2` FOREIGN KEY (`userId`) REFERENCES `UserDetail` (`userId`)
);


CREATE TABLE IF NOT EXISTS Medals (
medalId INT, 
tournamentId INT, 
type VARCHAR(12), 
athleteId INT,
PRIMARY KEY(medalId, tournamentId),
CONSTRAINT `MedalsToGame-fk1` FOREIGN KEY (`tournamentId`) REFERENCES `Tournament` (`tournamentId`) ON DELETE CASCADE,
CONSTRAINT `MedalsToAthlete-fk2` FOREIGN KEY (`athleteId`) REFERENCES `Athlete` (`athleteId`)
);