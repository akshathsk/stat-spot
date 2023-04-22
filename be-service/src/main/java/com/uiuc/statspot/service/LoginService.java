package com.uiuc.statspot.service;

import com.uiuc.statspot.model.UserDetails;
import com.uiuc.statspot.repository.LoginRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

  LoginRepository loginRepository;

  public LoginService(LoginRepository loginRepository) {
    this.loginRepository = loginRepository;
  }

  public void createUserDetails(UserDetails userDetails) {

    loginRepository.createUserDetails(userDetails);
  }

  public String login(UserDetails userDetails) {

    List<Long> userIds = loginRepository.login(userDetails);
    if (userIds.size() > 0) {
      return "Success";
    } else {
      return "Failed";
    }
  }
}
