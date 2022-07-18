<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <%@ page import="com.saadin.eventmanagementsystem.Model.Event" %>
    <%@ page import="com.saadin.eventmanagementsystem.DataAccessLayer.MosqueManagementDAO" %>
      <%@ page import="java.text.SimpleDateFormat" %>
        <%@ page import="java.util.Date" %>
          <% Event event=new MosqueManagementDAO().GetEvent(Integer.parseInt(request.getParameter("id")));
            if(event==null){ response.sendRedirect("../MosqueManagement/editEvent_.jsp?err=Invaild Event ID, Try Again!"
            ); } else { SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); String
            date__=sdf.format(event.getEventDate()); %>
            <html>

            <head>
              <title>Edit Event | My Masjid</title>
              <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
                integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
                crossorigin="anonymous">
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
              <% if (request.getSession().getAttribute("mm_username") !=null &&
                request.getSession().getAttribute("mm_staffId") !="" ) { %>
                </br>
                <div class="container">
                  <div class="row">
                    <div class="col-md-6">
                      <h3 class="text-center">EDIT EVENT</h3>
                    </div>
                  </div>
                </div>
                </br>
                <div class="container">
                  <div class="row">
                    <div class="col-md-6">
                      <div class="card">
                        <div class="card-body">
                          <form action="../m/EditEvent" method="post">
                            <div class="form-group">
                              <label for="eventName">Event Name</label>
                              <input required type="text" class="form-control" id="eventName" name="eventName"
                                placeholder="Enter Event Name" value="<%= event.getEventName() %>">
                            </div>
                            <div class="form-group">
                              <label for="eventDate">Event Date</label>
                              <input required type="date" class="form-control" id="eventDate" name="eventDate"
                                placeholder="yyyy-mm-dd" value="<%= date__ %>">
                            </div>
                            <div class="form-group">
                              <label for="eventTime">Event Time</label>
                              <input required type="time" class="form-control" id="eventTime" name="eventTime"
                                placeholder="hh:mm:ss" value="<%= event.getEventTime() %>">
                            </div>
                            <div class="form-group">
                              <label for="noOfParticipant">Number of Participants</label>
                              <input required type="number" class="form-control" id="noOfParticipant"
                                name="noOfParticipant" min="0" placeholder="Enter Number of Participants"
                                value="<%=  event.getNoOfParticipant() %>">
                            </div>
                            <% if(event.getCompetition() !=null) {%>
                              <div class="form-group" id="competitionDiv">
                                <label for="competitionPrize">Competition Prize</label>
                                <input required type="number" class="form-control" id="competitionPrize"
                                  name="competitionPrize" value="<%= event.getCompetition().getPrize() %>">
                              </div>
                              <%} else if(event.getCommunityService() !=null) {%>
                                <div id="communityServiceDiv">
                                  <div class="form-group">
                                    <label for="organizationName">Organization Name</label>
                                    <input required type="text" class="form-control" id="organizationName"
                                      name="organizationName"
                                      value="<%= event.getCommunityService().getNameOfOrganization() %>">
                                  </div>
                                  <div class="form-group">
                                    <label for="typeOfService">Type of Service</label>
                                    <input required type="text" class="form-control" id="typeOfService"
                                      name="typeOfService"
                                      value="<%= event.getCommunityService().getTypeOfService() %>">
                                  </div>
                                </div>
                                <%} else if(event.getLecture() !=null) {%>
                                  <div id="lectureDiv">
                                    <div class="form-group">
                                      <label for="lectureTopic">Lecture Topic</label>
                                      <input required type="text" class="form-control" id="lectureTopic"
                                        name="lectureTopic" value="<%= event.getLecture().getLectureTopic() %>">
                                    </div>
                                    <div class="form-group">
                                      <label for="lecturerName">Lecturer Name</label>
                                      <input required type="text" class="form-control" id="lecturerName"
                                        name="lecturerName" value="<%= event.getLecture().getLecturerName() %>">
                                    </div>
                                  </div>
                                  <%}%>
                                    <input type="hidden" name="id" value="<%= event.getEventId() %>">
                                    <div class="container">
                                      <div class="row">
                                        <button type="submit" class="btn btn-primary ml-2">Update</button>
                                        <button type="reset" class="btn btn-danger ml-2">Reset</button>
                                        <a href="../m/Dashboard" class="btn btn-secondary ml-2">Cancel</a>
                                      </div>
                                    </div>
                          </form>
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
            <% }%>