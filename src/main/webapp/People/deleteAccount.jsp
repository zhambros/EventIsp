<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <html>

  <head>
    <title>Delete Account | My Masjid</title>
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
            <h3 class="text-center">DELETE ACCOUNT</h3>
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
                    <form action="../p/DeleteAccount" method="post">
                      <div class="form-group">
                        <label for="emailAddress">Enter Email Address To Confirm:</label>
                        <input required type="email" class="form-control" id="emailAddress" name="emailAddress"
                          placeholder="Current Email Address">
                      </div>
                      <div class="container">
                        <div class="row">
                          <button type="submit" class="btn btn-primary ml-2">Delete</button>
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