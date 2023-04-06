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

  public List<AthleteDto> getAthletes() {
    return statisticsRepository.getAthletes();
  }

  public List<Club> getClubs() {
    return statisticsRepository.getClubs();
  }

  public List<League> getLeagues() {
    return statisticsRepository.getLeagues();
  }

  public Statistics getStatistics() {
    List<AthleteDto> athletes = new ArrayList<>();
    athletes.addAll(getAthletes());

    List<Club> clubs = new ArrayList<>();
    clubs.addAll(getClubs());

    List<League> leagues = new ArrayList<>();
    leagues.addAll(getLeagues());

    return new Statistics(athletes, clubs, leagues);
  }

  public List<GameDto> getGameByAthleteId(Integer athleteId) {
    return statisticsRepository.getGameByAthleteId(athleteId);
  }
}
