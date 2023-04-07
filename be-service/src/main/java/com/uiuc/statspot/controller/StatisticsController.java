package com.uiuc.statspot.controller;

import com.uiuc.statspot.dto.AthleteDto;
import com.uiuc.statspot.dto.GameDto;
import com.uiuc.statspot.dto.Statistics;
import com.uiuc.statspot.model.Club;
import com.uiuc.statspot.model.League;
import com.uiuc.statspot.service.StatisticsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class StatisticsController {

  StatisticsService statisticsService;

  public StatisticsController(StatisticsService statisticsService) {
    this.statisticsService = statisticsService;
  }

  @GetMapping("/stats/athletes")
  public List<AthleteDto> getAthletes() {
    return statisticsService.getAthletes("");
  }

  @GetMapping("/stats/clubs")
  public List<Club> getClubs() {
    return statisticsService.getClubs("");
  }

  @GetMapping("/stats/leagues")
  public List<League> getLeagues() {
    return statisticsService.getLeagues("");
  }

  @GetMapping("/stats")
  public Statistics getStatistics() {
    return statisticsService.getStatistics("");
  }

  @GetMapping("/stats/game/{athleteId}")
  public List<GameDto> getGameByAthleteId(@PathVariable Integer athleteId) {
    return statisticsService.getGameByAthleteId(athleteId);
  }

  @GetMapping("/stats/{search}")
  public Statistics getFilteredResults(@PathVariable String search) {
    return statisticsService.getStatistics(search);
  }
}
