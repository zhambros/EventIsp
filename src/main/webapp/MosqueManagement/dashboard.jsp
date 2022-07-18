<%@ page import="com.saadin.eventmanagementsystem.DataAccessLayer.MosqueManagementDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% MosqueManagementDAO dao = new MosqueManagementDAO();
    String
            name = dao.GetMangerName(request.getSession().getAttribute("mm_username").toString()); %>
<html>

<head>
    <title>Dashboard | My Masjid</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>

<body>
<% if (request.getSession().getAttribute("mm_username") != null &&
        request.getSession().getAttribute("mm_staffId") != "") { %>
</br>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h3 class="text-center">Welcome <b><%= request.getSession().getAttribute("mm_username") %></b>, To Mosque Management Dashboard
            </h3>
            <% if (name != null) { %>
            <h5 class="text-center">This user has <span style="color: red;"><%= name %></span> as manager!</h5>
            <%}%>
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
                            <a href="../m/AddEvent" class="btn btn-primary">Add Event</a>
                            <a href="../m/EditEvent" class="btn btn-primary">Edit Event</a>
                            <a href="../e/ViewEvent" class="btn btn-primary">View Event</a>
                            <a href="../m/DeleteEvent" class="btn btn-primary">Delete Event</a>
                            <a href="../m/GenerateReport" class="btn btn-primary">Generate Report</a>
                            <a href="../m/Logout" class="btn btn-primary">Logout</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<% } else {
    response.sendRedirect("../m/Login");
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