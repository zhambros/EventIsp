package com.saadin.eventmanagementsystem.DataAccessLayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.saadin.eventmanagementsystem.DBConnection;
import com.saadin.eventmanagementsystem.Model.CommunityService;
import com.saadin.eventmanagementsystem.Model.Competition;
import com.saadin.eventmanagementsystem.Model.Event;
import com.saadin.eventmanagementsystem.Model.Lecture;
import com.saadin.eventmanagementsystem.Model.People;

public class MosqueManagementDAO {
  public int ValidateLogin(String username, String password) {
    try {
      Connection con = DBConnection.createConnection();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt
          .executeQuery(
              "select * from mosque_management where staff_username = '" + username + "' and staff_password = '"
                  + password
                  + "'");

      if (rs.next())
        return rs.getInt("staff_id");
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  public int AddEvent(Event event) {
    try {
      Connection con = DBConnection.createConnection();
      Statement stmt = con.createStatement();
      stmt.executeUpdate(
          "insert into event (event_name, event_date, event_time, no_of_participants) values ('" + event.getEventName()
              + "', '"
              + (new java.sql.Date(event.getEventDate().getTime())).toString() + "', '" + event.getEventTime() + "', "
              + event.getNoOfParticipant() + ")");

      ResultSet rs = stmt.executeQuery("select event_id from event ORDER BY event_id DESC LIMIT 1");
      if (rs.next())
        event.setEventId(rs.getInt("event_id"));

      Competition competition = event.getCompetition();
      CommunityService communityService = event.getCommunityService();
      Lecture lecture = event.getLecture();

      if (competition != null) {
        stmt.executeUpdate(
            "insert into competition (competition_prize, event_id) values (" + competition.getPrize() + ", "
                + event.getEventId() + ")");
      } else if (communityService != null) {
        stmt.executeUpdate("insert into community_service (type_of_service, organization_name, event_id) values ("
            + communityService.getTypeOfService() + ", '" + communityService.getNameOfOrganization() + "', "
            + event.getEventId() + ")");
      } else if (lecture != null) {
        stmt.executeUpdate("insert into lecture (lecture_topic, lecturer_name, event_id) values ('"
            + lecture.getLectureTopic() + "', '" + lecture.getLecturerName() + "', " + event.getEventId() + ")");
      }
      con.close();
      return 1;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  public boolean IsValidEventID(int id) {
    try {
      Connection con = DBConnection.createConnection();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select * from event where event_id = " + id);
      if (rs.next())
        return true;
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public Event GetEvent(int id) {
    try {
      Connection con = DBConnection.createConnection();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select * from event where event_id = " + id);
      if (rs.next()) {
        Event event = new Event();
        event.setEventId(rs.getInt("event_id"));
        event.setEventName(rs.getString("event_name"));
        event.setEventDate(rs.getDate("event_date"));
        event.setEventTime(rs.getTime("event_time"));
        event.setNoOfParticipant(rs.getInt("no_of_participants"));

        ResultSet rs2 = stmt.executeQuery("select * from competition where event_id = " + id);
        if (rs2.next()) {
          Competition competition = new Competition();
          competition.setPrize(rs2.getInt("competition_prize"));
          event.setCompetition(competition);
        }

        ResultSet rs3 = stmt.executeQuery("select * from community_service where event_id = " + id);
        if (rs3.next()) {
          CommunityService communityService = new CommunityService();
          communityService.setTypeOfService(rs3.getString("type_of_service"));
          communityService.setNameOfOrganization(rs3.getString("organization_name"));
          event.setCommunityService(communityService);
        }

        ResultSet rs4 = stmt.executeQuery("select * from lecture where event_id = " + id);
        if (rs4.next()) {
          Lecture lecture = new Lecture();
          lecture.setLectureTopic(rs4.getString("lecture_topic"));
          lecture.setLecturerName(rs4.getString("lecturer_name"));
          event.setLecture(lecture);
        }
        con.close();
        return event;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public int UpdateEvent(Event event) {
    try {
      Connection con = DBConnection.createConnection();
      Statement stmt = con.createStatement();
      stmt.executeUpdate(
          "update event set event_name = '" + event.getEventName() + "', event_date = '"
              + (new java.sql.Date(event.getEventDate().getTime())).toString() + "', event_time = '"
              + event.getEventTime() + "', no_of_participants = " + event.getNoOfParticipant() + " where event_id = "
              + event.getEventId());

      Competition competition = event.getCompetition();
      CommunityService communityService = event.getCommunityService();
      Lecture lecture = event.getLecture();

      if (competition != null) {
        stmt.executeUpdate(
            "update competition set competition_prize = " + competition.getPrize() + " where event_id = "
                + event.getEventId());
      } else if (communityService != null) {
        stmt.executeUpdate("update community_service set type_of_service = " + communityService.getTypeOfService()
            + ", organization_name = '" + communityService.getNameOfOrganization() + "' where event_id = "
            + event.getEventId());
      } else if (lecture != null) {
        stmt.executeUpdate("update lecture set lecture_topic = '" + lecture.getLectureTopic() + "', lecturer_name = '"
            + lecture.getLecturerName() + "' where event_id = " + event.getEventId());
      }

      con.close();
      return 1;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  public int DeleteEvent(int id) {
    try {
      Connection con = DBConnection.createConnection();
      Statement stmt = con.createStatement();
      stmt.executeUpdate("delete from event where event_id = " + id);
      con.close();
      return 1;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  public List<Event> GenerateReport(String eventId) {
    List<Event> events = new ArrayList<Event>();
    try {
      Connection con = DBConnection.createConnection();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(
          "SELECT *, (SELECT COUNT(*) FROM `participant` WHERE participant.event_id = " + eventId
              + ") AS `joined` FROM `event` WHERE event_id = " + eventId);
      while (rs.next()) {
        Event event = new Event();
        event.setEventId(rs.getInt("event_id"));
        event.setEventName(rs.getString("event_name"));
        event.setEventDate(rs.getDate("event_date"));
        event.setEventTime(rs.getTime("event_time"));
        event.setNoOfParticipant(rs.getInt("no_of_participants"));
        event.setJoined(rs.getInt("joined"));
        events.add(event);
      }
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return events;
  }

  public List<People> GetParticipants(String eventId) {
    List<People> people = new ArrayList<People>();
    try {
      Connection con = DBConnection.createConnection();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(
          "SELECT `people`.`people_name`, `people`.`email_address`, `people`.`phone_number` FROM `participant` INNER JOIN `event` ON `event`.`event_id` = `participant`.`event_id` INNER JOIN `people` ON `people`.`email_address` = `participant`.`email_address` WHERE `participant`.`event_id` = "
              + eventId);

      while (rs.next()) {
        People person = new People();
        person.setPeopleName(rs.getString("people_name"));
        person.setEmailAddress(rs.getString("email_address"));
        person.setPhoneNumber(rs.getString("phone_number"));
        people.add(person);
      }
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return people;
  }

  public String GetMangerName(String username) {
    String name = null;
    try {
      Connection con = DBConnection.createConnection();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(
          "SELECT `staff_name` FROM `mosque_management` WHERE `staff_id` = (SELECT `manager_id` FROM `mosque_management` WHERE `staff_username` = '"+ username + "');");
      if (rs.next()) {
        name = rs.getString("staff_name");
      }
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return name;
  }
}
