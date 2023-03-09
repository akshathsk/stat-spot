package org.uiuc;

import java.util.HashMap;
import java.util.Map;

public interface AppConstants {

  String CLUB_INSERT =
      " INSERT INTO `stat-spot-db`.`Club`(`clubId`,`name`,`owner`,`established`,`totalMarketValue`,`squadSize`,`averageAge`,`totalNationalTeamPlayers`) "
          + " VALUES ({clubId},'{name}','{owner}',{established},{totalMarketValue},{squadSize},{averageAge},{totalNationalTeamPlayers}); ";

  String COUNTRY_INSERT =
      " INSERT INTO `stat-spot-db`.`Country` (`countryId`, `name`, `continent`) VALUES ({countryId}, '{name}', '{continent}'); ";

  String SPORT_INSERT =
      " INSERT INTO `stat-spot-db`.`Sport` (`sportId`, `name`, `description`, `playerCount`, `duration`) VALUES "
          + "({sportId}, '{name}', '{description}', {playerCount}, {duration}); ";

  String ATHLETE_INSERT =
      " INSERT INTO `stat-spot-db`.`Athlete` (`athleteId`, `name`, `dateOfBirth`, `position`, `marketValue`, `sex`, `sportId`, `countryId`, `clubId`) VALUES "
          + "({athleteId}, '{name}', '{dateOfBirth}', '{position}', {marketValue}, '{sex}', {sportId}, {countryId}, {clubId}); ";

  String LEAGUE_INSERT =
      "INSERT INTO `stat-spot-db`.`League` (`leagueId`, `type`, `subType`, `name`) VALUES "
          + "({leagueId}, '{type}', '{subType}', '{name}'); ";

  String USER_DETAILS_INSERT =
      "INSERT INTO `stat-spot-db`.`UserDetail` (`userId`, `userName`, `password`, `email`, `firstName`, `lastName`) VALUES "
          + "({userId}, '{userName}', '{password}', '{email}', '{firstName}', '{lastName}'); ";

  String TOURNAMENT_INSERT =
      "INSERT INTO `stat-spot-db`.`Tournament` "
          + "(`tournamentId`, "
          + "`name`, "
          + "`year`, "
          + "`description`) "
          + "VALUES "
          + "(1, "
          + "'Olympics in Tokyo', "
          + "2021, "
          + "'The 2020 Summer Olympics, officially the Games of the XXXII Olympiad and also known as Tokyo 2020, was an international multi-sport event held from 23 July to 8 August 2021 in Tokyo, Japan, with some preliminary events that began on 21 July 2021.'); ";

  String COACH_INSERT =
      " INSERT INTO `stat-spot-db`.`Coach` (`coachId`, `name`, `countryId`, `dateOfBirth`, `clubId`) VALUES "
          + " ({coachId}, '{name}', {countryId}, '{dateOfBirth}', {clubId}); ";

  String GAME_INSERT =
      " INSERT INTO `stat-spot-db`.`Game` (`gameId`, `season`, `round`, `gameDate`, `homeClubId`, `awayClubId`, `homeClubGoals`, `awayClubGoals`) VALUES "
          + "({gameId}, '{season}', '{round}', '{gameDate}', {homeClubId}, {awayClubId}, {homeClubGoals}, {awayClubGoals}); ";

  String TRAIN_INSERT =
      "INSERT INTO `stat-spot-db`.`Train` (`athleteId`, `coachId`) VALUES ({athleteId}, {coachId});";

  String COMPLETE_INSERT =
      "INSERT INTO `stat-spot-db`.`Compete` (`tournamentId`, `athleteId`) VALUES ({tournamentId}, {athleteId});";

  String PART_OF_INSERT =
      "INSERT INTO `stat-spot-db`.`PartOf` (`clubId`, `leagueId`) VALUES ({clubId}, {leagueId});";

  String SUBSCRIBE_INSERT =
      "INSERT INTO `stat-spot-db`.`Subscribe` (`gameId`, `userId`, `pay`) VALUE ({gameId}, {userId}, {pay});";

  String MEDALS_INSERT =
      "INSERT INTO `stat-spot-db`.`Medals` (`medalId`, `tournamentId`, `type`, `athleteId`)"
          + "VALUES"
          + "({medalId}, {tournamentId}, '{type}', {athleteId});";
}
