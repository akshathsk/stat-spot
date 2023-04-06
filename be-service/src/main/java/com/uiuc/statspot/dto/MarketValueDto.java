package com.uiuc.statspot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MarketValueDto {

  Integer clubId;
  String clubName;
  Double totalMarketValue;
}
