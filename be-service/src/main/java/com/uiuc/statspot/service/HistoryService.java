package com.uiuc.statspot.service;

import com.uiuc.statspot.dto.HomeGoalsDto;
import com.uiuc.statspot.dto.MarketValueDto;
import com.uiuc.statspot.repository.HistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

  HistoryRepository historyRepository;

  public HistoryService(HistoryRepository historyRepository) {
    this.historyRepository = historyRepository;
  }

  public List<MarketValueDto> getYoungPlayersMarketValue() {
    return historyRepository.getYoungPlayersMarketValue();
  }

  public List<HomeGoalsDto> getLowestHomeGoalLeagues() {
    return historyRepository.getLowestHomeGoalLeagues();
  }
}
