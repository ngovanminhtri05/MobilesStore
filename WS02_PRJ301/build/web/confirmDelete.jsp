<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Models.DTO.Mobiles" %>
<%
    Mobiles mobile = (Mobiles) request.getAttribute("mobile");
%>
<html>
<head>
    <title>Confirm Delete</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="p-4">
    <div class="container">
        <h2 class="mb-4">Are you sure you want to delete this mobile?</h2>
        <table class="table table-bordered w-50">
            <tr><th>ID</th><td><%= mobile.getMobileId() %></td></tr>
            <tr><th>Name</th><td><%= mobile.getMobileName() %></td></tr>
            <tr><th>Description</th><td><%= mobile.getDescription() %></td></tr>
            <tr><th>Price</th><td>$<%= mobile.getPrice() %></td></tr>
            <tr><th>Year of Production</th><td><%= mobile.getYearOfProduction() %></td></tr>
            <tr><th>Quantity</th><td><%= mobile.getQuantity() %></td></tr>
            <tr><th>Not for Sale</th><td><%= mobile.isNotSale() ? "Yes" : "No" %></td></tr>
        </table>

        <form action="DeleteMobileController" method="post">
            <input type="hidden" name="mobileId" value="<%= mobile.getMobileId() %>">
            <button type="submit" class="btn btn-danger">Yes, Delete</button>
            <a href="MainController?action=listAll" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
</body>
</html>
