package com.uiuc.statspot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Sport {

  String description;
  Double duration;
  String name;
  Integer playerCount;
  Integer sportId;
}
