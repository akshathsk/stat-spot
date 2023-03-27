package com.uiuc.statspot.repository;

import com.uiuc.statspot.repository.pool.TcpConnectionPoolFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class DefaultRepository {

  public void helloWorld() {
    String SQL_QUERY = "select * from Athlete";
    try {
      Connection con = TcpConnectionPoolFactory.createConnectionPool().getConnection();
      PreparedStatement pst = con.prepareStatement(SQL_QUERY);
      ResultSet rs = pst.executeQuery();
      System.out.println(rs);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
