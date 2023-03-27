package com.uiuc.statspot.controller;

import com.uiuc.statspot.service.DefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

  @Autowired DefaultService defaultService;

  @GetMapping("/helloWorld")
  public String helloWorld() {
    return defaultService.helloWorld();
  }
}
