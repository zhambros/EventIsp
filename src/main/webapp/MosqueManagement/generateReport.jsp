<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <html>

    <head>
      <title>Generate Report | My Masjid</title>
      <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
      <style>
        input::-webkit-outer-spin-button,
        input::-webkit-inner-spin-button {
          -webkit-appearance: none;
          margin: 0;
        }

        input[type=number] {
          -moz-appearance: textfield;
        }
      </style>
    </head>

    <body>
      <% if (request.getSession().getAttribute("mm_username") !=null && request.getSession().getAttribute("mm_staffId")
        !="" ) { %>
        </br>
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <h3 class="text-center">GENERATE REPORT</h3>
            </div>
          </div>
        </div>
        </br>

        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="card">
                <div class="card-body">
                  <form action="../m/GenerateReport" method="post">
                    <div class="form-group">
                      <label for="eventId">Enter Event ID</label>
                      <input type="text" class="form-control" id="eventId" name="eventId"
                        placeholder="Provide Event ID To Generate Report!">
                    </div>
                    <button type="submit" class="btn btn-primary">Generate</button>
                    <button type="reset" class="btn btn-danger">Reset</button>
                    <a href="../m/Dashboard" class="btn btn-secondary">Go Back</a>
                  </form>
                  <table class="table table-striped">
                    <thead>
                      <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Event Name</th>
                        <th scope="col">Date</th>
                        <th scope="col">Time</th>
                        <th scope="col">Total Capacity</th>
                        <th scope="col">Joined Participants</th>
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
                            <c:out value="${event.noOfParticipant}" />
                          </td>
                          <td>
                            <c:out value="${event.joined}" />
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
        </br>
        <div class="container">
          <div class="row">
            <div class="col-md-6">
              <div class="card">
                <div class="card-header">
                  <h3 class="card-title">Participant Details</h3>
                </div>
                <div class="card-body">
                  <table class="table table-striped">
                    <thead>
                      <tr>
                        <th scope="col">Fullname</th>
                        <th scope="col">Email</th>
                        <th scope="col">Phone Number</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="people" items="${peopleList}">
                        <tr>
                          <th>
                            <c:out value="${people.peopleName}" />
                          </th>
                          <td>
                            <c:out value="${people.emailAddress}" />
                          </td>
                          <td>
                            <c:out value="${people.phoneNumber}" />
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
        <% } else { response.sendRedirect("../m/Login"); } %>

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