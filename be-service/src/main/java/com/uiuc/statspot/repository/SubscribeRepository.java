package com.uiuc.statspot.repository;

import com.uiuc.statspot.model.Report1;
import com.uiuc.statspot.model.Report2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.uiuc.statspot.repository.constants.SqlConstants.CALL_REPORT_PROC;
import static com.uiuc.statspot.repository.constants.SqlConstants.READ_REPORT_1;
import static com.uiuc.statspot.repository.constants.SqlConstants.READ_REPORT_2;

@Repository
public class SubscribeRepository {

  @Autowired private JdbcTemplate jdbcTemplate;

  public SubscribeRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public int updateReport(String athlete) {

    return jdbcTemplate.update(CALL_REPORT_PROC, athlete);
  }

  public List<Report1> getReport1() {

    return jdbcTemplate.query(
        READ_REPORT_1,
        (rs, rowNum) ->
            new Report1(
                rs.getString("season"),
                rs.getString("round"),
                rs.getDate("gameDate"),
                rs.getInt("homeClubGoals"),
                rs.getInt("awayClubGoals"),
                rs.getString("winner")));
  }

  public List<Report2> getReport2() {

    return jdbcTemplate.query(
        READ_REPORT_2,
        (rs, rowNum) ->
            new Report2(
                rs.getInt("clubId"), rs.getString("playerName"), rs.getDouble("totalMarketValue")));
  }
}
