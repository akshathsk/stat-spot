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
public class Athlete {

  protected int athleteId;
  protected int clubId;
  protected int countryId;
  protected Date dateOfBirth;
  protected Double marketValue;
  protected String name;
  protected String position;
  protected String sex;
  protected int sportId;
}
