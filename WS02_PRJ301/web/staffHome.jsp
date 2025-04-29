<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Models.DTO.User" %>
<%@page import="Models.DTO.Mobiles" %>
<%@page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null || user.getRole() != 1) {
        response.sendRedirect("login.jsp");
        return;
    }
    List<Mobiles> mobiles = (List<Mobiles>) request.getAttribute("mobiles");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Staff Home</title>
    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <span class="navbar-brand">Staff Home</span>
    <div class="d-flex">
      <a class="btn btn-outline-light" href="MainController?action=logout">Logout</a>
    </div>
  </div>
</nav>

<!-- Main Content -->
<div class="container my-5">
    <div class="text-center mb-4">
        <h2>Welcome, <%= user.getFullName() %>!</h2>
    </div>
    <form action="MainController" method="get">
    <input type="hidden" name="action" value="searchMobile" />
    <input type="text" name="keyword" placeholder="Enter mobile ID or name..." required />
    <button type="submit" class="btn btn-primary">Search</button>
</form>


    <div class="card shadow-lg">
        <div class="card-body">
            <h4 class="card-title mb-4">Mobile List</h4>
                <form action="MainController" method="get">
                    <input type="hidden" name="action" value="insertMobile" />        
                    <button type="submit" class="btn btn-success">Create New</button>
                </form>
            <div class="table-responsive">
                <table class="table table-striped table-hover align-middle">
                    <thead class="table-primary">
                        <tr>
                            <th>ID</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Name</th>
                            <th>Year</th>
                            <th>Quantity</th>
                            <th>Not For Sale</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Mobiles m : mobiles) { %>
                            <tr>
                                <td><%= m.getMobileId() %></td>
                                <td><%= m.getDescription() %></td>
                                <td>$<%= m.getPrice() %></td>
                                <td><%= m.getMobileName() %></td>
                                <td><%= m.getYearOfProduction() %></td>
                                <td><%= m.getQuantity() %></td>
                                <td><%= m.isNotSale() ? "Yes" : "No" %></td>
                                <td>
                                    <a href="MainController?action=updateMobile&id=<%= m.getMobileId() %>" class="btn btn-sm btn-warning">Update</a>
                                    <a href="MainController?action=deleteMobile&id=<%= m.getMobileId() %>" class="btn btn-sm btn-danger">Delete</a>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>

        </div>
    </div>
</div>

<!-- Footer -->
<footer class="text-center mt-5 mb-3 text-muted">
    &copy; WS02
</footer>

<!-- Bootstrap 5 JS (Optional, if you want dropdowns etc) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
