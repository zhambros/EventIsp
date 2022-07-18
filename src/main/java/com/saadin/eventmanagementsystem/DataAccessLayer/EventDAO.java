package com.saadin.eventmanagementsystem.DataAccessLayer;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.saadin.eventmanagementsystem.DBConnection;
import com.saadin.eventmanagementsystem.Model.Event;
import com.saadin.eventmanagementsystem.Model.Participant;

public class EventDAO {
  public List<Event> GetAllEvents(String search) {
    List<Event> events = new ArrayList<Event>();
    try {
      Connection con = DBConnection.createConnection();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select * from event where event_name like '%" + search + "%'");
      while (rs.next()) {
        Event event = new Event();
        event.setEventId(rs.getInt("event_id"));
        event.setEventName(rs.getString("event_name"));
        event.setEventDate(rs.getDate("event_date"));
        event.setEventTime(rs.getTime("event_time"));
        event.setNoOfParticipant(rs.getInt("no_of_participants"));
        events.add(event);
      }
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return events;
  }

  public List<Event> GetAllEventForRegisteration(String email) {
    List<Event> events = new ArrayList<Event>();
    try {
      Connection con = DBConnection.createConnection();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(
          "SELECT *, (event.no_of_participants - (SELECT COUNT(*) FROM participant WHERE participant.event_id = event.event_id)) AS 'available' from event LEFT JOIN participant ON participant.email_address <> '"
              + email + "' GROUP BY event.event_name;");
      while (rs.next()) {
        Event event = new Event();
        event.setEventId(rs.getInt("event_id"));
        event.setEventName(rs.getString("event_name"));
        event.setEventDate(rs.getDate("event_date"));
        event.setEventTime(rs.getTime("event_time"));
        event.setNoOfParticipant(rs.getInt("no_of_participants"));
        event.setJoined(rs.getInt("available"));
        if (event.getJoined() > 0)
          events.add(event);
      }
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return events;
  }

  public boolean RegisterEvent(int eventId, String email) {
    try {
      Connection con = DBConnection.createConnection();
      Statement stmt = con.createStatement();
      stmt.executeUpdate(
          "INSERT INTO participant (event_id, email_address) VALUES (" + eventId + ", '" + email + "');");
      con.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  public List<Event> GetAllRegisteredEvents(String email) {
    List<Event> events = new ArrayList<Event>();
    try {
      Connection con = DBConnection.createConnection();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(
          "SELECT * from event INNER JOIN participant ON participant.email_address = '"
              + email + "' WHERE event.event_id = participant.event_id GROUP BY event.event_name;");
      while (rs.next()) {
        Event event = new Event();
        event.setEventId(rs.getInt("event_id"));
        event.setEventName(rs.getString("event_name"));
        event.setEventDate(rs.getDate("event_date"));
        event.setEventTime(rs.getTime("event_time"));
        event.setNoOfParticipant(rs.getInt("no_of_participants"));
        Participant participant = new Participant();
        participant.setEventFeedback(rs.getString("event_feedback"));
        event.setParticipant(participant);
        events.add(event);
      }
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return events;
  }

  public boolean CancelEvent(int eventId, String email) {
    try {
      Connection con = DBConnection.createConnection();
      Statement stmt = con.createStatement();
      stmt.executeUpdate(
          "DELETE FROM participant WHERE event_id = " + eventId + " AND email_address = '" + email + "';");
      con.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  public boolean GiveFeedback(int eventId, String email, String feedback) {
    try {
      Connection con = DBConnection.createConnection();
      Statement stmt = con.createStatement();
      stmt.executeUpdate(
          "UPDATE participant SET event_feedback = '" + feedback + "' WHERE event_id = " + eventId
              + " AND email_address = '" + email + "';");
      con.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
}
