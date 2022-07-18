package com.saadin.eventmanagementsystem.DomainLayer;

import javax.servlet.*;
import javax.servlet.http.*;

import com.saadin.eventmanagementsystem.DataAccessLayer.MosqueManagementDAO;
import com.saadin.eventmanagementsystem.Model.CommunityService;
import com.saadin.eventmanagementsystem.Model.Competition;
import com.saadin.eventmanagementsystem.Model.Event;
import com.saadin.eventmanagementsystem.Model.Lecture;
import com.saadin.eventmanagementsystem.Model.People;

import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "MosqueManagementController", value = "/m/*")
public class MosqueManagementController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getRequestURI().split("/m/")[1];
    switch (action) {
      case "Login":
        response.sendRedirect("../MosqueManagement/login.jsp");
        break;
      case "Logout":
        request.getSession().invalidate();
        response.sendRedirect("../MosqueManagement/login.jsp");
        break;
      case "Dashboard":
        response.sendRedirect("../MosqueManagement/dashboard.jsp");
        break;
      case "AddEvent":
        response.sendRedirect("../MosqueManagement/addEvent.jsp");
        break;
      case "EditEvent":
        response.sendRedirect("../MosqueManagement/editEvent_.jsp");
        break;
      case "DeleteEvent":
        response.sendRedirect("../MosqueManagement/deleteEvent.jsp");
        break;
      case "GenerateReport":
        response.sendRedirect("../MosqueManagement/generateReport.jsp");
        break;
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getRequestURI().split("/m/")[1];
    switch (action) {
      case "Login":
        login(request, response);
        break;
      case "Logout":
        request.getSession().invalidate();
        response.sendRedirect("../MosqueManagement/login.jsp");
        break;
      case "Dashboard":
        dashboard(request, response);
        break;
      case "AddEvent":
        addEvent(request, response);
        break;
      case "EditEvent":
        editEvent(request, response);
        break;
      case "DeleteEvent":
        deleteEvent(request, response);
        break;
      case "GenerateReport":
        generateReport(request, response);
        break;
    }
  }

  private void generateReport(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    if (request.getSession().getAttribute("mm_username") == null) {
      response.sendRedirect("../MosqueManagement/login.jsp");
      return;
    }

    String eventId = request.getParameter("eventId");
    if (eventId == null)
      eventId = "";

    List<Event> eventList = new MosqueManagementDAO().GenerateReport(eventId);
    if (eventList.size() > 0) {
      List<People> peopleList = new MosqueManagementDAO().GetParticipants(eventId);
      request.setAttribute("peopleList", peopleList);
      request.setAttribute("eventList", eventList);
      RequestDispatcher dispatcher = request.getRequestDispatcher("../MosqueManagement/generateReport.jsp");
      dispatcher.forward(request, response);
    } else {
      response.sendRedirect("../MosqueManagement/dashboard.jsp?msg=No Event Found!");
    }
  }

  private void deleteEvent(HttpServletRequest request, HttpServletResponse response) throws IOException {
    if (request.getSession().getAttribute("mm_username") == null) {
      response.sendRedirect("../MosqueManagement/login.jsp");
      return;
    }

    int eventId = Integer.parseInt(request.getParameter("eventId"));
    MosqueManagementDAO dao = new MosqueManagementDAO();
    if (dao.IsValidEventID(eventId)) {
      int deleteResponse = dao.DeleteEvent(eventId);
      if (deleteResponse == 1) {
        response.sendRedirect("../MosqueManagement/dashboard.jsp?msg=Event Deleted Successfully!");
      } else {
        response.sendRedirect("../MosqueManagement/dashboard.jsp?msg=Event Deletion Failed!");
      }
    } else {
      response.sendRedirect("../MosqueManagement/deleteEvent.jsp?err=Invalid Event ID");
    }
  }

  private void editEvent(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    if (request.getSession().getAttribute("mm_username") == null) {
      response.sendRedirect("../MosqueManagement/login.jsp");
      return;
    }

    if (request.getParameter("eventId") != null) {
      response.sendRedirect("../MosqueManagement/editEvent.jsp?id=" + request.getParameter("eventId"));
      return;
    } else {
      MosqueManagementDAO dao = new MosqueManagementDAO();
      Event event = dao.GetEvent(Integer.parseInt(request.getParameter("id")));
      event.setEventName(request.getParameter("eventName"));
      String eventDate = request.getParameter("eventDate");
      String eventTime = request.getParameter("eventTime");
      event.setNoOfParticipant(Integer.parseInt(request.getParameter("noOfParticipant")));
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      Date eventDateObj = null;

      try {
        eventDateObj = format.parse(eventDate);
      } catch (ParseException e) {
        e.printStackTrace();
      }

      eventTime = eventTime.split(" ")[0].split(":")[0] + ":" + eventTime.split(" ")[0].split(":")[1] + ":00";

      event.setEventDate(eventDateObj);
      event.setEventTime(Time.valueOf(eventTime));

      if (request.getParameter("competitionPrize") != null) {
        Competition competition = new Competition();
        competition.setPrize(Double.parseDouble(request.getParameter("competitionPrize")));
        event.setCompetition(competition);
      } else if (request.getParameter("organizationName") != null && request.getParameter("typeOfService") != null) {
        CommunityService communityService = new CommunityService();
        communityService.setNameOfOrganization(request.getParameter("organizationName"));
        communityService.setTypeOfService(request.getParameter("typeOfService"));
        event.setCommunityService(communityService);
      } else if (request.getParameter("lectureTopic") != null && request.getParameter("lecturerName") != null) {
        Lecture lecture = new Lecture();
        lecture.setLectureTopic(request.getParameter("lectureTopic"));
        lecture.setLecturerName(request.getParameter("lecturerName"));
        event.setLecture(lecture);
      }

      int updateResponse = dao.UpdateEvent(event);
      if (updateResponse == -1) {
        response.sendRedirect("../MosqueManagement/editEvent.jsp?err=Failed to Update Event!");
      } else {
        response.sendRedirect("../MosqueManagement/dashboard.jsp?msg=Event Updated Successfully!");
      }
    }
  }

  private void addEvent(HttpServletRequest request, HttpServletResponse response) throws IOException {
    if (request.getSession().getAttribute("mm_username") == null) {
      response.sendRedirect("../MosqueManagement/login.jsp");
      return;
    }

    String eventName = request.getParameter("eventName");
    String eventDate = request.getParameter("eventDate");
    String eventTime = request.getParameter("eventTime");
    String eventType = request.getParameter("eventType");
    int noOfParticipant = Integer.parseInt(request.getParameter("noOfParticipant"));
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Date eventDateObj = null;

    try {
      eventDateObj = format.parse(eventDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    eventTime = eventTime.split(" ")[0].split(":")[0] + ":" + eventTime.split(" ")[0].split(":")[1] + ":00";

    Event event = new Event(eventName, eventDateObj, Time.valueOf(eventTime), noOfParticipant);
    if (eventType.toString().equals("Competition")) {
      Competition competition = new Competition();
      competition.setPrize(Double.parseDouble(request.getParameter("competitionPrize")));
      event.setCompetition(competition);
    } else if (eventType.toString().equals("Community Service")) {
      CommunityService communityService = new CommunityService();
      communityService.setNameOfOrganization(request.getParameter("organizationName"));
      communityService.setTypeOfService(request.getParameter("typeOfService"));
      event.setCommunityService(communityService);
    } else if (eventType.toString().equals("Lecture")) {
      Lecture lecture = new Lecture();
      lecture.setLecturerName(request.getParameter("lecturerName"));
      lecture.setLectureTopic(request.getParameter("lectureTopic"));
      event.setLecture(lecture);
    }

    int eventResponse = new MosqueManagementDAO().AddEvent(event);
    if (eventResponse == 1) {
      response.sendRedirect("../MosqueManagement/dashboard.jsp?msg=Event Added Successfully!");
    } else {
      response.sendRedirect("../MosqueManagement/dashboard.jsp?err=Failed to Add Event!");
    }
  }

  private void dashboard(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    if (request.getSession().getAttribute("mm_username") == null) {
      response.sendRedirect("../MosqueManagement/login.jsp");
      return;
    }

    request.getRequestDispatcher("../MosqueManagement/dashboard.jsp").forward(request, response);
  }

  private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String username = request.getParameter("username").toLowerCase();
    String password = request.getParameter("password");

    int staffId = new MosqueManagementDAO().ValidateLogin(username, password);
    if (staffId != -1) {
      HttpSession session = request.getSession();
      session.setAttribute("mm_staffId", staffId);
      session.setAttribute("mm_username", username);
      response.sendRedirect("../MosqueManagement/dashboard.jsp");
    } else {
      response.sendRedirect("../MosqueManagement/login.jsp?err=Invalid Username OR Password!");
    }
  }
}
