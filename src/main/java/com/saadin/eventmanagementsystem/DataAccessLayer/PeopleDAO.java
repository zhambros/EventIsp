package com.saadin.eventmanagementsystem.DataAccessLayer;

import java.sql.*;
import com.saadin.eventmanagementsystem.DBConnection;
import com.saadin.eventmanagementsystem.Model.People;

public class PeopleDAO {
  public People ValidateLogin(String username, String password) {
    try {
      Connection con = DBConnection.createConnection();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt
          .executeQuery(
              "select * from people where email_address = '" + username + "' and people_password = '"
                  + password
                  + "'");

      if (rs.next()) {
        People people = new People();
        people.setPeopleName(rs.getString("people_name"));
        people.setEmailAddress(rs.getString("email_address"));
        people.setPeoplePassword(rs.getString("people_password"));
        people.setPhoneNumber(rs.getString("phone_number"));
        return people;
      }
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public int Register(People people) {
    try {
      Connection con = DBConnection.createConnection();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt
          .executeQuery(
              "select * from people where email_address = '" + people.getEmailAddress() + "'");
      if (rs.next())
        return -1;

      rs = stmt
          .executeQuery(
              "select * from people where phone_number = '" + people.getPhoneNumber() + "'");
      if (rs.next())
        return -2;

      stmt.executeUpdate(
          "insert into people (people_name, email_address, people_password, phone_number) values ('"
              + people.getPeopleName() + "', '" + people.getEmailAddress() + "', '"
              + people.getPeoplePassword() + "', '" + people.getPhoneNumber() + "');");
      con.close();
      return 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  public Boolean DeleteAccount(String email) {
    try {
      Connection con = DBConnection.createConnection();
      Statement stmt = con.createStatement();
      stmt.executeUpdate("delete from people where email_address = '" + email + "'");
      con.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public People GetPeople(String email) {
    try {
      Connection con = DBConnection.createConnection();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt
          .executeQuery(
              "select * from people where email_address = '" + email + "'");
      if (rs.next()) {
        People people = new People();
        people.setPeopleName(rs.getString("people_name"));
        people.setEmailAddress(rs.getString("email_address"));
        people.setPeoplePassword(rs.getString("people_password"));
        people.setPhoneNumber(rs.getString("phone_number"));
        return people;
      }
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Boolean UpdatePeople(People people) {
    try {
      Connection con = DBConnection.createConnection();
      Statement stmt = con.createStatement();
      stmt.executeUpdate(
          "update people set people_name = '" + people.getPeopleName() + "', email_address = '"
              + people.getEmailAddress() + "', people_password = '" + people.getPeoplePassword()
              + "', phone_number = '" + people.getPhoneNumber() + "' where email_address = '"
              + people.getEmailAddress() + "'");
      con.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
}
