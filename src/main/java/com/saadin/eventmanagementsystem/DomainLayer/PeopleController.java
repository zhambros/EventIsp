package com.saadin.eventmanagementsystem.DomainLayer;

import javax.servlet.*;
import javax.servlet.http.*;

import com.saadin.eventmanagementsystem.DataAccessLayer.PeopleDAO;
import com.saadin.eventmanagementsystem.Model.People;

import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PeopleController", value = "/p/*")
public class PeopleController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getRequestURI().split("/p/")[1];
    switch (action) {
      case "Login":
        response.sendRedirect("../People/login.jsp");
        break;
      case "Logout":
        request.getSession().invalidate();
        response.sendRedirect("../People/login.jsp");
        break;
      case "Register":
        response.sendRedirect("../People/register.jsp");
        break;
      case "Dashboard":
        response.sendRedirect("../People/dashboard.jsp");
        break;
      case "DeleteAccount":
        response.sendRedirect("../People/deleteAccount.jsp");
        break;
      case "UpdateAccount":
        response.sendRedirect("../People/updateAccount.jsp");
        break;
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getRequestURI().split("/p/")[1];
    switch (action) {
      case "Login":
        login(request, response);
        break;
      case "Logout":
        request.getSession().invalidate();
        response.sendRedirect("../People/login.jsp");
        break;
      case "Register":
        register(request, response);
        break;
      case "Dashboard":
        dashboard(request, response);
        break;
      case "DeleteAccount":
        deleteAccount(request, response);
        break;
      case "UpdateAccount":
        updateAccount(request, response);
        break;
    }
  }

  private void updateAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
    if (request.getSession().getAttribute("p_email") == null) {
      response.sendRedirect("../People/login.jsp");
      return;
    }

    String name = request.getParameter("fullname");
    String phone = request.getParameter("phone");
    String password = request.getParameter("password");

    People people = new People();
    people.setPeopleName(name);
    people.setPhoneNumber(phone);
    people.setPeoplePassword(password);
    people.setEmailAddress(request.getSession().getAttribute("p_email").toString());

    PeopleDAO peopleDAO = new PeopleDAO();
    if (peopleDAO.UpdatePeople(people)) {
      response.sendRedirect("../People/dashboard.jsp?msg=Account Updated Successfully!");
    } else {
      response.sendRedirect("../People/dashboard.jsp?err=Failed to Update Account!");
    }
  }

  private void deleteAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
    if (request.getSession().getAttribute("p_email") == null) {
      response.sendRedirect("../People/login.jsp");
      return;
    }

    String emailAddString = request.getParameter("emailAddress");
    if (emailAddString.equals(request.getSession().getAttribute("p_email").toString())) {
      PeopleDAO dao = new PeopleDAO();
      if (dao.DeleteAccount(emailAddString)) {
        request.getSession().invalidate();
        response.sendRedirect("../People/login.jsp");
      } else {
        response.sendRedirect("../People/dashboard.jsp?err=Something Went Wrong!");
      }
    } else {
      response.sendRedirect("../People/deleteAccount.jsp?err=Invalid Email Address!");
    }
  }

  private void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
    People people = new People();
    people.setPeopleName(request.getParameter("fullname"));
    people.setEmailAddress(request.getParameter("email").toLowerCase());
    people.setPeoplePassword(request.getParameter("password"));
    people.setPhoneNumber(request.getParameter("phone"));

    int registerResponse = new PeopleDAO().Register(people);
    if (registerResponse == 0) {
      response.sendRedirect("../People/login.jsp");
    } else if (registerResponse == -1) {
      response.sendRedirect("../People/register.jsp?err=Email Address Already Used!");
    } else if (registerResponse == -2) {
      response.sendRedirect("../People/register.jsp?err=Phone Number Already Used!");
    } else {
      response.sendRedirect("../People/register.jsp?err=Something Went Wrong!");
    }
  }

  private void dashboard(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    if (request.getSession().getAttribute("p_email") == null) {
      response.sendRedirect("../People/login.jsp");
      return;
    }

    request.getRequestDispatcher("../People/dashboard.jsp").forward(request, response);
  }

  private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String username = request.getParameter("username").toLowerCase();
    String password = request.getParameter("password");

    People peopleResponse = new PeopleDAO().ValidateLogin(username, password);
    if (peopleResponse != null) {
      HttpSession session = request.getSession();
      session.setAttribute("p_email", peopleResponse.getEmailAddress());
      session.setAttribute("p_name", peopleResponse.getPeopleName());
      response.sendRedirect("../People/dashboard.jsp");
    } else {
      response.sendRedirect("../People/login.jsp?err=Invalid Username OR Password!");
    }
  }
}
