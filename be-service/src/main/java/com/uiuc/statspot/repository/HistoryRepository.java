package com.uiuc.statspot.repository;

import com.uiuc.statspot.dto.HomeGoalsDto;
import com.uiuc.statspot.dto.MarketValueDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.uiuc.statspot.repository.constants.SqlConstants.LEAGUES_LOWEST_HOME_GOALS;
import static com.uiuc.statspot.repository.constants.SqlConstants.YOUNG_ATTACK_PLAYER_MKT_VALUE;

@Repository
public class HistoryRepository {

  @Autowired private JdbcTemplate jdbcTemplate;

  public HistoryRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<MarketValueDto> getYoungPlayersMarketValue() {

    return jdbcTemplate.query(
        YOUNG_ATTACK_PLAYER_MKT_VALUE,
        (rs, rowNum) ->
            new MarketValueDto(
                rs.getInt("clubId"), rs.getString("name"), rs.getDouble("TotalMarketValue")));
  }

  public List<HomeGoalsDto> getLowestHomeGoalLeagues() {

    return jdbcTemplate.query(
        LEAGUES_LOWEST_HOME_GOALS,
        (rs, rowNum) ->
            new HomeGoalsDto(
                rs.getInt("leagueId"), rs.getString("type"), rs.getDouble("HomeGoalsTotal")));
  }
}
