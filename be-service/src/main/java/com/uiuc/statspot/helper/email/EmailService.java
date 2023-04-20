package com.uiuc.statspot.helper.email;

import com.uiuc.statspot.dto.EmailDetails;
import freemarker.template.Configuration;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class EmailService implements IEmailService {

  @Autowired Configuration fmConfiguration;

  @Autowired private JavaMailSender javaMailSender;

  @Value("${spring.mail.username}")
  private String sender;

  @Value("${mail.enable}")
  private boolean enableEmail;

  public String sendMail(EmailDetails details) {

    if (!enableEmail) {
      return "Emails are disabled";
    }

    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    try {
      MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
      mimeMessageHelper.setSubject(details.getSubject());
      mimeMessageHelper.setFrom(sender);
      mimeMessageHelper.setTo(details.getRecipient().split(","));
      mimeMessageHelper.setText(details.getMsgBody(), true);
      mimeMessageHelper.addAttachment("details.xlsx", details.getDatasource());
      javaMailSender.send(mimeMessageHelper.getMimeMessage());
    } catch (Exception e) {
      log.info(e.getMessage());
      return "Failed to send email.";
    }
    return "Email sent successfully!";
  }
}
