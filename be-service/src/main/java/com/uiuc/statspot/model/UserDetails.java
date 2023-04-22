package com.uiuc.statspot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {

  private String firstName;
  private String lastName;
  private String email;
  private String userName;
  private String password;
}
