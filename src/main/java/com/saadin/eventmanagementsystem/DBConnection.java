package com.saadin.eventmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
  public static Connection createConnection() {
    Connection con = null;
    String url = "jdbc:mysql://us-cdbr-east-06.cleardb.net/heroku_10248fa359e6e82";
    String username = "b848aaa4db1172";
    String password = "5e845f03";

    try {
      try {
        Class.forName("com.mysql.jdbc.Driver");
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
      con = DriverManager.getConnection(url, username, password);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return con;
  }
}
