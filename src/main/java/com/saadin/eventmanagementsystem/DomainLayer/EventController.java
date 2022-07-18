package com.saadin.eventmanagementsystem.DomainLayer;

import com.saadin.eventmanagementsystem.DataAccessLayer.EventDAO;
import com.saadin.eventmanagementsystem.Model.Event;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EventController", value = "/e/*")
public class EventController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getRequestURI().split("/e/")[1];
    switch (action) {
      case "ViewEvent":
        viewEvent(request, response);
        break;
      case "RegisterParticipant":
        registerParticipant(request, response);
        break;
      case "CancelParticipant":
        cancelParticipant(request, response);
        break;
      case "ViewParticipant":
        viewParticipant(request, response);
        break;
      case "GiveFeedback":
        giveFeedback(request, response);
        break;
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getRequestURI().split("/e/")[1];
    switch (action) {
      case "ViewEvent":
        viewEvent(request, response);
        break;
      case "RegisterParticipant":
        registerParticipant(request, response);
        break;
      case "CancelParticipant":
        cancelParticipant(request, response);
        break;
      case "ViewParticipant":
        viewParticipant(request, response);
        break;
      case "GiveFeedback":
        giveFeedback(request, response);
        break;
    }
  }

  private void giveFeedback(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    if (request.getSession().getAttribute("p_email") == null) {
      response.sendRedirect("../People/login.jsp");
      return;
    }

    if (request.getParameter("event_id") != null && request.getParameter("feedback") == null) {
      List<Event> eventList = new EventDAO()
          .GetAllRegisteredEvents(request.getSession().getAttribute("p_email").toString());
      if (eventList.size() > 0) {
        request.setAttribute("eventList", eventList);
        request.setAttribute("event_id", request.getParameter("event_id"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("../People/giveFeedback.jsp");
        dispatcher.forward(request, response);
      } else {
        response.sendRedirect("../People/dashboard.jsp?msg=No Event Found!");
      }
    } else if (request.getParameter("event_id") != null && request.getParameter("feedback") != null) {
      EventDAO dao = new EventDAO();
      if (dao.GiveFeedback(Integer.parseInt(request.getParameter("event_id")),
          request.getSession().getAttribute("p_email").toString(), request.getParameter("feedback").toString())) {
        response.sendRedirect("../People/dashboard.jsp?msg=Feedback Added Successfully!");
      } else {
        response.sendRedirect("../People/dashboard.jsp?err=Failed To Add Feedback!");
      }
      return;
    } else {
      List<Event> eventList = new EventDAO()
          .GetAllRegisteredEvents(request.getSession().getAttribute("p_email").toString());
      if (eventList.size() > 0) {
        request.setAttribute("eventList", eventList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("../People/giveFeedback.jsp");
        dispatcher.forward(request, response);
      } else {
        response.sendRedirect("../People/dashboard.jsp?msg=No Event Found!");
      }
    }
  }

  private void cancelParticipant(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    if (request.getSession().getAttribute("p_email") == null) {
      response.sendRedirect("../People/login.jsp");
      return;
    }

    if (request.getParameter("event_id") != null) {
      EventDAO dao = new EventDAO();
      if (dao.CancelEvent(Integer.parseInt(request.getParameter("event_id")),
          request.getSession().getAttribute("p_email").toString())) {
        response.sendRedirect("../People/dashboard.jsp?msg=Participation Cancelled Successfully!");
      } else {
        response.sendRedirect("../People/dashboard.jsp?err=Failed To Cancel Participation!");
      }
      return;
    } else {
      List<Event> eventList = new EventDAO()
          .GetAllRegisteredEvents(request.getSession().getAttribute("p_email").toString());
      if (eventList.size() > 0) {
        request.setAttribute("eventList", eventList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("../People/cancelParticipant.jsp");
        dispatcher.forward(request, response);
      } else {
        response.sendRedirect("../People/dashboard.jsp?msg=No Event Found!");
      }
    }
  }

  private void registerParticipant(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    if (request.getSession().getAttribute("p_email") == null) {
      response.sendRedirect("../People/login.jsp");
      return;
    }

    if (request.getParameter("event_id") != null) {
      EventDAO dao = new EventDAO();
      if (dao.RegisterEvent(Integer.parseInt(request.getParameter("event_id")),
          request.getSession().getAttribute("p_email").toString())) {
        response.sendRedirect("../People/dashboard.jsp?msg=Registered Successfully!");
      } else {
        response.sendRedirect("../People/dashboard.jsp?err=Registeration Failed! May Be Already Registered!");
      }
      return;
    } else {
      List<Event> eventList = new EventDAO()
          .GetAllEventForRegisteration(request.getSession().getAttribute("p_email").toString());
      if (eventList.size() > 0) {
        request.setAttribute("eventList", eventList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("../People/registerParticipant.jsp");
        dispatcher.forward(request, response);
      } else {
        response.sendRedirect("../People/dashboard.jsp?msg=No Event Found!");
      }
    }
  }

  private void viewEvent(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    String search = request.getParameter("search");
    if (search == null)
      search = "";

    List<Event> eventList = new EventDAO().GetAllEvents(search);
    if (eventList.size() > 0) {
      request.setAttribute("eventList", eventList);
      RequestDispatcher dispatcher = request.getRequestDispatcher("../Event/viewEvent.jsp");
      dispatcher.forward(request, response);
    } else {
      if (request.getSession().getAttribute("p_email") != null) {
        response.sendRedirect("../People/dashboard.jsp?msg=No Event Found!");
      } else if (request.getSession().getAttribute("mm_username") != null) {
        response.sendRedirect("../MosqueManagement/dashboard.jsp?msg=No Event Found!");
      } else {
        request.getSession().invalidate();
        response.sendRedirect("../");
      }
    }
  }

  private void viewParticipant(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    if (request.getSession().getAttribute("p_email") == null) {
      response.sendRedirect("../People/login.jsp");
      return;
    }

    List<Event> eventList = new EventDAO()
        .GetAllRegisteredEvents(request.getSession().getAttribute("p_email").toString());
    if (eventList.size() > 0) {
      request.setAttribute("eventList", eventList);
      RequestDispatcher dispatcher = request.getRequestDispatcher("../Event/viewParticipant.jsp");
      dispatcher.forward(request, response);
    } else {
      response.sendRedirect("../People/dashboard.jsp?msg=No Participation Found!");
    }
  }
}
