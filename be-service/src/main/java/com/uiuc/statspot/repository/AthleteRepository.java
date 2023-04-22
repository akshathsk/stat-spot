package com.uiuc.statspot.repository;

import com.uiuc.statspot.dto.ClubDto;
import com.uiuc.statspot.dto.CountryDto;
import com.uiuc.statspot.dto.SportDto;
import com.uiuc.statspot.model.Athlete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.uiuc.statspot.repository.constants.SqlConstants.ATHLETE_NAME;
import static com.uiuc.statspot.repository.constants.SqlConstants.DELETE_ATHLETE;
import static com.uiuc.statspot.repository.constants.SqlConstants.GET_CLUBS_METADATA;
import static com.uiuc.statspot.repository.constants.SqlConstants.GET_COUNTRIES_METADATA;
import static com.uiuc.statspot.repository.constants.SqlConstants.GET_SPORTS_METADATA;
import static com.uiuc.statspot.repository.constants.SqlConstants.INSERT_ATHLETE;
import static com.uiuc.statspot.repository.constants.SqlConstants.UPDATE_ATHLETE;

@Repository
public class AthleteRepository {

  @Autowired private JdbcTemplate jdbcTemplate;

  public AthleteRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public int createAthlete(Athlete athlete) {

    return jdbcTemplate.update(
        INSERT_ATHLETE,
        athlete.getName(),
        athlete.getDateOfBirth(),
        athlete.getPosition(),
        athlete.getMarketValue(),
        athlete.getSex(),
        athlete.getSportId(),
        athlete.getCountryId(),
        athlete.getClubId());
  }

  public int updateAthlete(Athlete athlete, Integer athleteId) {

    return jdbcTemplate.update(
        UPDATE_ATHLETE,
        athlete.getName(),
        athlete.getDateOfBirth(),
        athlete.getPosition(),
        athlete.getMarketValue(),
        athlete.getSex(),
        athlete.getSportId(),
        athlete.getCountryId(),
        athlete.getClubId(),
        athleteId);
  }

  public int deleteAthlete(String athleteId) {

    return jdbcTemplate.update(DELETE_ATHLETE, athleteId);
  }

  public List<ClubDto> getClubsMetadata() {

    return jdbcTemplate.query(
        GET_CLUBS_METADATA, (rs, rowNum) -> new ClubDto(rs.getInt("clubId"), rs.getString("name")));
  }

  public List<CountryDto> getCountriesMetadata() {

    return jdbcTemplate.query(
        GET_COUNTRIES_METADATA,
        (rs, rowNum) -> new CountryDto(rs.getInt("countryId"), rs.getString("name")));
  }

  public List<SportDto> getSportsMetadata() {

    return jdbcTemplate.query(
        GET_SPORTS_METADATA,
        (rs, rowNum) -> new SportDto(rs.getInt("sportId"), rs.getString("name")));
  }

  public List<String> getAthleteNames() {
    return jdbcTemplate.query(ATHLETE_NAME, (rs, rowNum) -> rs.getString("name"));
  }
}
