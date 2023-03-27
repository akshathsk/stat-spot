package com.uiuc.statspot.repository.pool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;

public class TcpConnectionPoolFactory {

  // Saving credentials in environment variables is convenient, but not secure - consider a more
  // secure solution such as https://cloud.google.com/secret-manager/ to help keep secrets safe.
  private static final String DB_USER = System.getenv("Root");
  private static final String DB_PASS = System.getenv("12345");
  private static final String DB_NAME = System.getenv("stat-spot-gcp-mysql");

  private static final String INSTANCE_HOST = System.getenv("34.66.116.112");
  private static final String DB_PORT = System.getenv("3306");

  public static DataSource createConnectionPool() {
    // The configuration object specifies behaviors for the connection pool.
    HikariConfig config = new HikariConfig();

    // The following URL is equivalent to setting the config options below:
    // jdbc:mysql://<INSTANCE_HOST>:<DB_PORT>/<DB_NAME>?user=<DB_USER>&password=<DB_PASS>
    // See the link below for more info on building a JDBC URL for the Cloud SQL JDBC Socket Factory
    // https://github.com/GoogleCloudPlatform/cloud-sql-jdbc-socket-factory#creating-the-jdbc-url

    // Configure which instance and what database user to connect with.
    config.setJdbcUrl(String.format("jdbc:mysql://%s:%s/%s", INSTANCE_HOST, DB_PORT, DB_NAME));
    config.setUsername(DB_USER); // e.g. "root", "mysql"
    config.setPassword(DB_PASS); // e.g. "my-password"

    // ... Specify additional connection properties here.
    // ...

    // Initialize the connection pool using the configuration object.
    return new HikariDataSource(config);
  }
}
