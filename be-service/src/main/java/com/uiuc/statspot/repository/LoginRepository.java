package com.uiuc.statspot.repository;

import com.uiuc.statspot.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.uiuc.statspot.repository.constants.SqlConstants.LOGIN;
import static com.uiuc.statspot.repository.constants.SqlConstants.USER_DETAILS_CREATE;

@Repository
public class LoginRepository {

  @Autowired private JdbcTemplate jdbcTemplate;

  public int createUserDetails(UserDetails userDetails) {

    return jdbcTemplate.update(
        USER_DETAILS_CREATE,
        userDetails.getUserName(),
        userDetails.getPassword(),
        userDetails.getEmail(),
        userDetails.getFirstName(),
        userDetails.getLastName());
  }

  public List<Long> login(UserDetails userDetails) {
    return jdbcTemplate.query(
        LOGIN
            .replace("<userName>", userDetails.getUserName())
            .replace("<password>", userDetails.getPassword()),
        (rs, rowNum) -> rs.getLong("userId"));
  }
}
