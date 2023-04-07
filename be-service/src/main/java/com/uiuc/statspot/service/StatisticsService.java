package com.uiuc.statspot.service;

import com.uiuc.statspot.dto.AthleteDto;
import com.uiuc.statspot.dto.GameDto;
import com.uiuc.statspot.dto.Statistics;
import com.uiuc.statspot.model.Club;
import com.uiuc.statspot.model.League;
import com.uiuc.statspot.repository.StatisticsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsService {

  StatisticsRepository statisticsRepository;

  public StatisticsService(StatisticsRepository statisticsRepository) {
    this.statisticsRepository = statisticsRepository;
  }

  public List<AthleteDto> getAthletes(String search) {
    return statisticsRepository.getAthletes(search);
  }

  public List<Club> getClubs(String search) {
    return statisticsRepository.getClubs(search);
  }

  public List<League> getLeagues(String search) {
    return statisticsRepository.getLeagues(search);
  }

  public Statistics getStatistics(String search) {
    List<AthleteDto> athletes = new ArrayList<>();
    athletes.addAll(getAthletes(search));

    List<Club> clubs = new ArrayList<>();
    clubs.addAll(getClubs(search));

    List<League> leagues = new ArrayList<>();
    leagues.addAll(getLeagues(search));

    return new Statistics(athletes, clubs, leagues);
  }

  public List<GameDto> getGameByAthleteId(Integer athleteId) {
    return statisticsRepository.getGameByAthleteId(athleteId);
  }
}
