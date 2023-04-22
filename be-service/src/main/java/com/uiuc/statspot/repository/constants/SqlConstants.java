package com.uiuc.statspot.repository.constants;

public interface SqlConstants {

  String GET_ATHLETES =
      "SELECT "
          + " A.*, "
          + " C.name as clubName, "
          + " Co.name as countryName, "
          + " S.name as sportName "
          + "FROM "
          + " Athlete A "
          + " INNER JOIN Club C on A.clubId = C.clubId "
          + " INNER JOIN Country Co on A.countryId = Co.countryId "
          + " INNER JOIN Sport S on A.sportId = S.sportId "
          + " ORDER BY "
          + " A.name "
          + "LIMIT 1000";

  String GET_ATHLETES_FILTERED =
      "SELECT "
          + " A.*,  "
          + " C.name as clubName,  "
          + " Co.name as countryName,  "
          + " S.name as sportName  "
          + " FROM  "
          + " Athlete A  "
          + " INNER JOIN Club C on A.clubId = C.clubId  "
          + " INNER JOIN Country Co on A.countryId = Co.countryId  "
          + " INNER JOIN Sport S on A.sportId = S.sportId "
          + " where A.name like \"%replace_str%\" "
          + " ORDER BY "
          + " A.name "
          + " LIMIT 1000";

  String GET_CLUBS = "select * from Club;";

  String GET_CLUBS_FILTERED = "select * from Club C where C.name like \"%replace_str%\";";

  String GET_LEAGUES = "select * from League;";

  String GET_LEAGUES_FILTERED = "select * from League L where L.type like \"%replace_str%\";";

  String GET_STATS =
      "select  "
          + "G.*, "
          + "    C.name as awayClubName, "
          + "    C1.name as homeClubName "
          + "from  "
          + "Athlete A "
          + "INNER JOIN Game G on A.clubId = G.homeClubId "
          + "        INNER JOIN Club C on C.clubId = G.awayClubId "
          + "        INNER JOIN Club C1 on C1.clubId = G.homeClubId "
          + "where A.athleteId = ? "
          + "union  "
          + "select  "
          + "G.*, "
          + "    C.name as awayClubName, "
          + "    C1.name as homeClubName "
          + "from  "
          + "Athlete A "
          + "INNER JOIN Game G on A.clubId = G.awayClubId "
          + "        INNER JOIN Club C on C.clubId = G.awayClubId "
          + "        INNER JOIN Club C1 on C1.clubId = G.homeClubId "
          + "where A.athleteId = ? ;";

  String YOUNG_ATTACK_PLAYER_MKT_VALUE =
      "SELECT C.clubId, "
          + "C.name, "
          + "sum(A.marketValue) as TotalMarketValue FROM "
          + "`stat-spot-db`.Club C "
          + "LEFT JOIN `stat-spot-db`.Athlete A on C.clubId = A.clubId "
          + "WHERE "
          + "A.position = 'Attack' "
          + "and "
          + "A.dateOfBirth > '<dateOfBirth>' GROUP BY "
          + "C.clubId, "
          + "C.name ORDER BY "
          + "TotalMarketValue desc LIMIT 15;";

  String LEAGUES_LOWEST_HOME_GOALS =
      "SELECT L.leagueId, "
          + "L.type, "
          + "sum(G.homeClubGoals) as HomeGoalsTotal FROM "
          + "`stat-spot-db`.League L "
          + "INNER JOIN PartOf P on P.leagueId = L.leagueId INNER JOIN Club C on C.clubId = P.clubId "
          + "INNER JOIN Game G on G.homeClubId = C.clubId "
          + "WHERE "
          + "G.season = '<season>' "
          + "and "
          + "L.name not in ('fifa_club_world_cup') GROUP BY "
          + "L.leagueId, "
          + "L.type ORDER BY "
          + "HomeGoalsTotal LIMIT 15;";

  String INSERT_ATHLETE =
      "INSERT INTO Athlete "
          + "(name, dateOfBirth, position, marketValue, sex, sportId, countryId, clubId) "
          + "VALUES "
          + "(?, ?, ?, ?, ?, ?, ?, ?);";

  String UPDATE_ATHLETE =
      "UPDATE Athlete "
          + "SET "
          + "name = ?, dateOfBirth = ?, position = ?, marketValue = ?, sex = ?, sportId = ?, countryId = ?, clubId = ? "
          + "WHERE "
          + "athleteId = ?;";

  String DELETE_ATHLETE = "DELETE FROM Athlete WHERE athleteId = ?;";

  String GET_CLUBS_METADATA = "SELECT clubId, name FROM Club;";

  String GET_COUNTRIES_METADATA = "SELECT countryId, name FROM Country;";

  String GET_SPORTS_METADATA = "SELECT sportId, name FROM Sport;";

  String CALL_REPORT_PROC = "call ReportProc(?);";

  String READ_REPORT_1 =
      "select season, round, gameDate, homeClubGoals, awayClubGoals, winner from FinalReportTable;";

  String READ_REPORT_2 = "select clubId, playerName, TotalMarketValue from FinalReportTable2;";

  String ATHLETE_NAME = "select name from Athlete order by name asc limit 1000;";

  String USER_DETAILS_CREATE =
      "INSERT INTO UserDetail "
          + "(userName, password, email, firstName, lastName) "
          + "VALUES "
          + "(?,?,?,?,?);";

  String LOGIN = "SELECT userId FROM UserDetail where userName = '<userName>' and password = '<password>';";
}
