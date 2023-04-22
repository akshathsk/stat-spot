package com.uiuc.statspot.controller;

import com.uiuc.statspot.dto.SubscribeDto;
import com.uiuc.statspot.service.SubscribeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class SubscribeController {

  SubscribeService subscribeService;

  public SubscribeController(SubscribeService subscribeService) {
    this.subscribeService = subscribeService;
  }

  @PostMapping("/subscribe")
  public void subscribe(@RequestBody SubscribeDto subscribeDto) {
    subscribeService.subscribe(subscribeDto);
  }
}
