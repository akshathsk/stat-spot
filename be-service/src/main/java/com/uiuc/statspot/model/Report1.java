package com.uiuc.statspot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Report1 {

  private String season;
  private String round;
  private Date gameDate;
  private int homeClubGoals;
  private int awayClubGoals;
  private String winner;
}
