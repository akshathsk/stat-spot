package com.uiuc.statspot.controller;

import com.uiuc.statspot.dto.HomeGoalsDto;
import com.uiuc.statspot.dto.MarketValueDto;
import com.uiuc.statspot.service.HistoryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class HistoryController {

  HistoryService historyService;

  public HistoryController(HistoryService historyService) {
    this.historyService = historyService;
  }

  @GetMapping("/history/market-value/young")
  public List<MarketValueDto> getYoungPlayersMarketValue() {
    return historyService.getYoungPlayersMarketValue();
  }

  @GetMapping("/history/leagues/goals/home/lowest")
  public List<HomeGoalsDto> getLowestHomeGoalLeagues() {
    return historyService.getLowestHomeGoalLeagues();
  }
}
