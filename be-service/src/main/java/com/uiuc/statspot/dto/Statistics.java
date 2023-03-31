package com.uiuc.statspot.dto;

import com.uiuc.statspot.model.Club;
import com.uiuc.statspot.model.League;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Statistics {

  List<AthleteDto> athletes;
  List<Club> clubs;
  List<League> leagues;
}
