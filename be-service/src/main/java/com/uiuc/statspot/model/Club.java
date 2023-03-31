package com.uiuc.statspot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Club {

  Double averageAge;
  int clubId;
  int established;
  String name;
  String owner;
  int squadSize;
  Double totalMarketValue;
  int totalNationalTeamPlayers;
}
