<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>People Dashboard | My Masjid</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>

<body>
<% if (request.getSession().getAttribute("p_email") != null) { %>
</br>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h3 class="text-center">Welcome <b><%= request.getSession().getAttribute("p_name") %></b>, To People Dashboard
            </h3>
        </div>
    </div>
</div>
</br>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <% if (request.getParameter("msg") != null) { %>
            <div class="alert alert-success">
                <%= request.getParameter("msg") %>
            </div>
            <% } %>
            <% if (request.getParameter("err") != null) { %>
            <div class="alert alert-danger">
                <%= request.getParameter("err") %>
            </div>
            <% } %>
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-12">
                            <a href="../p/UpdateAccount" class="btn btn-primary">Update Account</a>
                            <a href="../p/DeleteAccount" class="btn btn-primary">Delete Account</a>
                            <a href="../p/Logout" class="btn btn-primary">Logout</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<br>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-12">
                            <a href="../e/ViewEvent" class="btn btn-primary">View Event</a>
                            <a href="../e/RegisterParticipant" class="btn btn-primary">Register Participation</a>
                            <a href="../e/CancelParticipant" class="btn btn-primary">Cancel Participation</a>
                            <a href="../e/ViewParticipant" class="btn btn-primary">View Participations</a>
                            <a href="../e/GiveFeedback" class="btn btn-primary">Give Feedback</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<% } else {
    response.sendRedirect("../p/Login");
} %>

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