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
public class Game {

  protected int awayClubGoals;
  protected int awayClubId;
  protected Date gameDate;
  protected int gameId;
  protected int homeClubGoals;
  protected int homeClubId;
  protected String round;
  protected String season;
}
