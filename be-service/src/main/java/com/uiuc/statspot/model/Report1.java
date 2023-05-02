package com.uiuc.statspot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Report1 {

  private String awayClubName;
  private int homeClubGoals;
  private int awayClubGoals;
  private String winner;
}
