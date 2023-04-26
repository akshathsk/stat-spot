package com.uiuc.statspot.controller;

import com.uiuc.statspot.model.UserDetails;
import com.uiuc.statspot.service.LoginService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class LoginController {

  LoginService loginService;

  public LoginController(LoginService loginService) {
    this.loginService = loginService;
  }

  @PostMapping("/register")
  public void createUserDetails(@RequestBody UserDetails userDetails) {

    loginService.createUserDetails(userDetails);
  }

  @PostMapping("/login")
  public String login(@RequestBody UserDetails userDetails) {

    return loginService.login(userDetails);
  }
}
