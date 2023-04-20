package com.uiuc.statspot.service;

import com.uiuc.statspot.dto.EmailDetails;
import com.uiuc.statspot.dto.SubscribeDto;
import com.uiuc.statspot.helper.email.IEmailService;
import com.uiuc.statspot.helper.excel.ExcelService;
import com.uiuc.statspot.model.Report1;
import com.uiuc.statspot.model.Report2;
import com.uiuc.statspot.repository.SubscribeRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SubscribeService {

  IEmailService emailService;

  SubscribeRepository subscribeRepository;

  public SubscribeService(IEmailService emailService, SubscribeRepository subscribeRepository) {
    this.emailService = emailService;
    this.subscribeRepository = subscribeRepository;
  }

  public void subscribe(SubscribeDto subscribeDto) {

    subscribeRepository.updateReport(subscribeDto.getAthletes());
    List<Report1> report1List = subscribeRepository.getReport1();
    List<Report2> report2List = subscribeRepository.getReport2();

    jakarta.activation.DataSource ds = null;
    try {
      ds = ExcelService.generateExcel(report1List, report2List);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    EmailDetails emailDetails = new EmailDetails();
    emailDetails.setRecipient(subscribeDto.getEmailId());
    emailDetails.setSubject("Details for " + subscribeDto.getAthletes());
    emailDetails.setMsgBody(
        "Please find attached details for athletes " + subscribeDto.getAthletes());
    emailDetails.setDatasource(ds);
    emailService.sendMail(emailDetails);
  }
}
