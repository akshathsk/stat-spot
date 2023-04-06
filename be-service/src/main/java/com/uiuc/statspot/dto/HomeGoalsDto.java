package com.uiuc.statspot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HomeGoalsDto {

  Integer leagueId;
  String leagueName;
  Double homeGoalsTotal;
}
