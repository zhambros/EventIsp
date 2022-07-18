<%@ page import="com.saadin.eventmanagementsystem.DataAccessLayer.PeopleDAO" %>
  <%@ page import="com.saadin.eventmanagementsystem.Model.People" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
      <% if (request.getSession().getAttribute("p_name")==null){ response.sendRedirect("../p/Dashboard"); return; } else
        { People people=new PeopleDAO().GetPeople(request.getSession().getAttribute("p_email").toString()); %>
        <html>

        <head>
          <title>Update Account | My Masjid</title>
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
          <% if (request.getSession().getAttribute("p_email") !=null) { %>
            </br>
            <div class="container">
              <div class="row">
                <div class="col-md-6">
                  <h3 class="text-center">UPDATE ACCOUNT</h3>
                </div>
              </div>
            </div>
            </br>

            <div class="container">
              <div class="row">
                <div class="col-md-6">
                  <% if (request.getParameter("err") !=null) { %>
                    <div class="alert alert-danger">
                      <%= request.getParameter("err") %>
                    </div>
                    <% } %>
                      <div class="card">
                        <div class="card-body">
                          <form action="../p/UpdateAccount" method="post">
                            <!-- Fullname -->
                            <div class="form-group">
                              <label for="fullname">Fullname</label>
                              <input required type="text" class="form-control" id="fullname" name="fullname"
                                value="<%= people.getPeopleName() %>">
                            </div>
                            <!-- Email -->
                            <div class="form-group">
                              <label for="email">Email</label>
                              <input disabled type="email" class="form-control" id="email" name="email"
                                value="<%= people.getEmailAddress() %>">
                            </div>
                            <!-- Password -->
                            <div class="form-group">
                              <label for="password">Password</label>
                              <input required type="password" class="form-control" id="password" name="password"
                                placeholder="Enter New Password">
                            </div>
                            <!-- Phone Number -->
                            <div class="form-group">
                              <label for="phone">Phone Number</label>
                              <input required type="number" class="form-control" id="phone" name="phone"
                                value="<%= people.getPhoneNumber() %>">
                            </div>
                            <div class="container">
                              <div class="row">
                                <button type="submit" class="btn btn-primary ml-2">Update</button>
                                <button type="reset" class="btn btn-danger ml-2">Reset</button>
                                <a href="../p/Dashboard" class="btn btn-secondary ml-2">Go Back</a>
                              </div>
                            </div>
                          </form>
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
        <%} %>