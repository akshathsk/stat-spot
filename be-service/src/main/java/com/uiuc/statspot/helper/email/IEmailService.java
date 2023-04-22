package com.uiuc.statspot.helper.email;

import com.uiuc.statspot.dto.EmailDetails;

public interface IEmailService {

  String sendMail(EmailDetails details);
}
