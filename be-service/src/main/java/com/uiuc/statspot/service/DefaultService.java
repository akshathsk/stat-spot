package com.uiuc.statspot.service;

import com.uiuc.statspot.repository.DefaultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultService {

  @Autowired DefaultRepository defaultRepository;

  public String helloWorld() {
    defaultRepository.helloWorld();
    return "Hello World";
  }
}
