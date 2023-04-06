package com.uiuc.statspot.dto;

import com.uiuc.statspot.model.Game;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GameDto extends Game {

  String awayClubName;
  String homeClubName;

  public GameDto(
      int awayClubGoals,
      int awayClubId,
      Date gameDate,
      int gameId,
      int homeClubGoals,
      int homeClubId,
      String round,
      String season,
      String awayClubName,
      String homeClubName) {


    super(awayClubGoals, awayClubId, gameDate, gameId, homeClubGoals, homeClubId, round, season);
    this.awayClubName = awayClubName;
    this.homeClubName = homeClubName;
  }
}
