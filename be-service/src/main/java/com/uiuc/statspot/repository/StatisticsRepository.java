package com.uiuc.statspot.repository;

import com.uiuc.statspot.dto.AthleteDto;
import com.uiuc.statspot.dto.GameDto;
import com.uiuc.statspot.model.Club;
import com.uiuc.statspot.model.League;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.uiuc.statspot.repository.constants.SqlConstants.GET_ATHLETES;
import static com.uiuc.statspot.repository.constants.SqlConstants.GET_ATHLETES_FILTERED;
import static com.uiuc.statspot.repository.constants.SqlConstants.GET_CLUBS;
import static com.uiuc.statspot.repository.constants.SqlConstants.GET_CLUBS_FILTERED;
import static com.uiuc.statspot.repository.constants.SqlConstants.GET_LEAGUES;
import static com.uiuc.statspot.repository.constants.SqlConstants.GET_LEAGUES_FILTERED;
import static com.uiuc.statspot.repository.constants.SqlConstants.GET_STATS;

@Repository
public class StatisticsRepository {

  @Autowired private JdbcTemplate jdbcTemplate;

  private static RowMapper<AthleteDto> getAthletesRowMapper() {
    return (rs, rowNum) ->
        new AthleteDto(
            rs.getInt("athleteId"),
            rs.getInt("clubId"),
            rs.getInt("countryId"),
            rs.getDate("dateOfBirth"),
            rs.getDouble("marketValue"),
            rs.getString("name"),
            rs.getString("position"),
            rs.getString("sex"),
            rs.getInt("sportId"),
            rs.getString("clubName"),
            rs.getString("countryName"),
            rs.getString("sportName"));
  }

  private static RowMapper<Club> getClubRowMapper() {
    return (rs, rowNum) ->
        new Club(
            rs.getDouble("averageAge"),
            rs.getInt("clubId"),
            rs.getInt("established"),
            rs.getString("name"),
            rs.getString("owner"),
            rs.getInt("squadSize"),
            rs.getDouble("totalMarketValue"),
            rs.getInt("totalNationalTeamPlayers"));
  }

  private static RowMapper<League> getLeagueRowMapper() {
    return (rs, rowNum) ->
        new League(
            rs.getInt("leagueId"),
            rs.getString("name"),
            rs.getString("subType"),
            rs.getString("type"));
  }

  public List<AthleteDto> getAthletes(String search) {
    return jdbcTemplate.query(
        search.isEmpty() ? GET_ATHLETES : GET_ATHLETES_FILTERED.replace("replace_str", search),
        getAthletesRowMapper());
  }

  public List<Club> getClubs(String search) {

    return jdbcTemplate.query(
        search.isEmpty() ? GET_CLUBS : GET_CLUBS_FILTERED.replace("replace_str", search),
        getClubRowMapper());
  }

  public List<League> getLeagues(String search) {

    return jdbcTemplate.query(
        search.isEmpty() ? GET_LEAGUES : GET_LEAGUES_FILTERED.replace("replace_str", search),
        getLeagueRowMapper());
  }

  public List<GameDto> getGameByAthleteId(Integer athleteId) {

    return jdbcTemplate.query(
        GET_STATS,
        (rs, rowNum) ->
            new GameDto(
                rs.getInt("awayClubGoals"),
                rs.getInt("awayClubId"),
                rs.getDate("gameDate"),
                rs.getInt("gameId"),
                rs.getInt("homeClubGoals"),
                rs.getInt("homeClubId"),
                rs.getString("round"),
                rs.getString("season"),
                rs.getString("awayClubName"),
                rs.getString("homeClubName")),
        athleteId,
        athleteId);
  }
}
