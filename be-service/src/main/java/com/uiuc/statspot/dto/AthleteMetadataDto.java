package com.uiuc.statspot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AthleteMetadataDto {

  List<ClubDto> clubs;
  List<CountryDto> countries;
  List<SportDto> sports;
}
