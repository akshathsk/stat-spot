package com.uiuc.statspot.controller;

import com.uiuc.statspot.dto.AthleteMetadataDto;
import com.uiuc.statspot.dto.EmailDetails;
import com.uiuc.statspot.helper.email.IEmailService;
import com.uiuc.statspot.model.Athlete;
import com.uiuc.statspot.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class AthleteController {

  AthleteService athleteService;

  @Autowired IEmailService emailService;

  public AthleteController(AthleteService athleteService) {
    this.athleteService = athleteService;
  }

  @PostMapping("/athlete")
  public void createAthlete(@RequestBody Athlete athlete) {

    EmailDetails emailDetails = new EmailDetails();
    emailDetails.setRecipient("sk117@illinois.edu");
    emailDetails.setSubject("test");
    emailDetails.setMsgBody("test");
    emailService.sendMail(emailDetails);
    //    athleteService.createAthlete(athlete);
  }

  @PutMapping("/athlete/{athleteId}")
  public void updateAthlete(@PathVariable Integer athleteId, @RequestBody Athlete athlete) {
    System.out.println(athleteId);
    athleteService.updateAthlete(athlete, athleteId);
  }

  @DeleteMapping("/athlete/{athleteId}")
  public void deleteAthlete(@PathVariable String athleteId) {
    athleteService.deleteAthlete(athleteId);
  }

  @GetMapping("/athlete/metadata")
  public AthleteMetadataDto getAthleteMetadata() {
    return athleteService.getAthleteMetadata();
  }

  @GetMapping("/athlete/names")
  public List<String> getAthleteNames() {
    return athleteService.getAthleteNames();
  }
}
