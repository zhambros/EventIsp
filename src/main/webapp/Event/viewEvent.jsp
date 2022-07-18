<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <html>

    <head>
      <title>View Event | My Masjid</title>
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
      <% if (request.getSession().getAttribute("mm_username") !=null || request.getSession().getAttribute("p_email")
        !=null ) { %>
        </br>
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <h3 class="text-center">VIEW EVENT</h3>
            </div>
          </div>
        </div>
        </br>

        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="card">
                <div class="card-body">
                  <form action="../e/ViewEvent" method="post">
                    <div class="form-group">
                      <label for="search">Search</label>
                      <input type="text" class="form-control" id="search" name="search" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-primary">Search</button>
                    <button type="reset" class="btn btn-danger">Reset</button>
                    <% if (request.getSession().getAttribute("mm_username") !=null) { %>
                      <a href="../m/Dashboard" class="btn btn-secondary">Go Back</a>
                      <% } else if (request.getSession().getAttribute("p_email") !=null) { %>
                        <a href="../p/Dashboard" class="btn btn-secondary">Go Back</a>
                        <% } %>
                  </form>
                  <table class="table table-striped">
                    <thead>
                      <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Event Name</th>
                        <th scope="col">Date</th>
                        <th scope="col">Time</th>
                        <th scope="col">No. Of Participants</th>
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
                        </tr>
                      </c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>



        <% } else { response.sendRedirect("../"); } %>

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