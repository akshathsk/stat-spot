package com.uiuc.statspot.dto;

import com.uiuc.statspot.model.Athlete;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AthleteDto extends Athlete {

  String clubName;
  String countryName;
  String sportName;

  public AthleteDto(
      int athleteId,
      int clubId,
      int countryId,
      Date dateOfBirth,
      Double marketValue,
      String name,
      String position,
      String sex,
      int sportId,
      String clubName,
      String countryName,
      String sportName) {

    super(athleteId, clubId, countryId, dateOfBirth, marketValue, name, position, sex, sportId);
    this.clubName = clubName;
    this.countryName = countryName;
    this.sportName = sportName;
  }
}
