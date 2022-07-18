<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <% request.getSession().invalidate(); %>
    <html>

    <head>
      <title>Mosque Management | My Masjid</title>
      <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>

    <body>
      </br>
      <div class="container">
        <div class="row">
          <div class="col-md-6">
            <h2 class="text-center">Mosque Management Login</h2>
            <% if (request.getParameter("err") !=null) { %>
              <div class="alert alert-danger">
                <%= request.getParameter("err") %>
              </div>
              <% } %>
                <form action="../m/Login" method="post">
                  <div class="form-group">
                    <label for="username">Username</label>
                    <input required type="text" class="form-control" id="username" name="username"
                      placeholder="Enter Username">
                  </div>
                  <div class="form-group">
                    <label for="password">Password</label>
                    <input required type="password" class="form-control" id="password" name="password"
                      placeholder="Enter Password" minlength="4" maxlength="10">
                  </div>
                  <button type="submit" class="btn btn-primary mr-2">Login</button>
                  <a href="../" class="btn btn-danger">Go Back</a>
                </form>
          </div>
        </div>
      </div>

      <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
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