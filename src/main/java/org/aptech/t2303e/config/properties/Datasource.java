package org.aptech.t2303e.config.properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author : ad
 * @mailto : luunguyen301297@gmail.com
 * @created : 11/13/2023, Monday
 **/
public class Datasource {
  private static Connection conn;

  public static synchronized Connection getConn() {
    if (conn == null) {
      init();
    }
    return conn;
  }

  public static void init() {
    DatabaseProperties dbProps = new DatabaseProperties();
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection(dbProps.getUrl(), dbProps.getUsername(), dbProps.getPassword());
      System.err.println(conn != null ? "Hacking NASA using HTML..." : "FBI opening the door !");
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
  }

  public static void closeConn() {
    if (conn != null) {
      try {
        conn.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
