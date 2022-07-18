<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <% String event_id=null ; if(request.getParameter("event_id") !=null)
      event_id=request.getParameter("event_id").toString(); %>
      <html>

      <head>
        <title>Give Feedback | My Masjid</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
      </head>

      <body>
        <% if (request.getSession().getAttribute("p_email") !=null) { %>
          </br>
          <div class="container">
            <div class="row">
              <div class="col-md-12">
                <h3 class="text-center">GIVE FEEDBACK</h3>
              </div>
            </div>
          </div>
          </br>
          <% if (event_id !=null) { %>
            <div class="container">
              <div class="row">
                <div class="col-md-12">
                  <div class="card">
                    <div class="card-body">
                      <form action="../e/GiveFeedback" method="post">
                        <input type="hidden" name="event_id" value="<%= event_id %>">
                        <div class="form-group">
                          <label for="feedback">Feedback</label>
                          <textarea class="form-control" id="feedback" name="feedback" rows="3"></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                        <button type="reset" class="btn btn-danger">Reset</button>
                        <a href="../p/Dashboard" class="btn btn-secondary">Go Back</a>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            </br>
            <% } %>
              <div class="container">
                <div class="row">
                  <div class="col-md-12">
                    <div class="card">
                      <div class="card-body">
                        <table class="table table-striped">
                          <thead>
                            <tr>
                              <th scope="col">ID</th>
                              <th scope="col">Event Name</th>
                              <th scope="col">Date</th>
                              <th scope="col">Time</th>
                              <th scope="col">Feedback</th>
                            </tr>
                          </thead>
                          <tbody>
                            <c:forEach var="event" items="${eventList}">
                              <tr>
                                <th scope="row">
                                  <c:out value="${event.eventId}" />
                                </th>
                                <td>
                                  <c:out value="${event.eventName}" />
                                </td>
                                <td>
                                  <c:out value="${event.eventDate}" />
                                </td>
                                <td>
                                  <c:out value="${event.eventTime}" />
                                </td>
                                <td>
                                  <c:if test="${event.participant.eventFeedback == null}">
                                    <form method="POST" action="../e/GiveFeedback" style="margin: 0;">
                                      <input type="hidden" name="event_id" value="${event.eventId}">
                                      <button type="submit" class="btn btn-primary">Give Feedback</button>
                                    </form>
                                  </c:if>
                                  <c:if test="${event.participant.eventFeedback != null}">
                                    <c:out value="${event.participant.eventFeedback}" />
                                  </c:if>
                                </td>
                              </tr>
                            </c:forEach>
                          </tbody>
                        </table>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <% } else { response.sendRedirect("../p/Login"); } %>

                <script src=" https://code.jquery.com/jquery-3.4.1.slim.min.js"
                  integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
                  crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
                  integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
                  crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js"
                  integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
                  crossorigin="anonymous"></script>
      </body>

      </html>