<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    Models.DTO.User user = (Models.DTO.User) session.getAttribute("user");
    if (user == null || user.getRole() != 0) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Home</title>
    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to right, #e0f7fa, #e1bee7);
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }
        .card {
            border-radius: 1rem;
        }
        .navbar {
            border-bottom: 2px solid #ffffff55;
        }
        footer {
            margin-top: auto;
            padding: 1rem;
        }
    </style>
</head>

<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary shadow-sm">
    <div class="container-fluid">
        <span class="navbar-brand fw-bold">User Home</span>
        <div class="d-flex">
            <a class="btn btn-warning me-2" href="CartController?action=ViewCart">🛒 View Cart</a>
<a class="btn btn-outline-light" href="MainController?action=logout">Logout</a>
        </div>
    </div>
</nav>

<!-- Main Content -->
<div class="container my-5">
    <div class="text-center mb-5">
        <h2 class="fw-bold">👋 Hello, ${user.fullName}!</h2>
    </div>

    <div class="card shadow-lg p-4">
        <div class="card-body">
            <h4 class="card-title mb-4 text-primary">📱 Available Mobiles</h4>
<c:if test="${not empty ERROR_MESSAGE}">
    <div class="alert ${MESSAGE_TYPE eq 'success' ? 'alert-success' : 'alert-danger'} text-center">
        ${ERROR_MESSAGE}
    </div>
</c:if>


            <!-- Search Form -->
            <div class="mb-4">
                <form class="row g-3" action="MainController" method="get">
                    <input type="hidden" name="action" value="searchByPrice">
                    <div class="col-md-5">
                        <input type="number" step="0.01" class="form-control" name="minPrice" placeholder="Minimum Price" required>
                    </div>
                    <div class="col-md-5">
                        <input type="number" step="0.01" class="form-control" name="maxPrice" placeholder="Maximum Price" required>
                    </div>
                    <div class="col-md-2">
                        <button type="submit" class="btn btn-primary w-100">Search</button>
                    </div>
                </form>
            </div>

            <!-- Mobile List -->
            <c:choose>
                <c:when test="${not empty mobiles}">
                    <div class="table-responsive">
                        <table class="table table-striped table-hover align-middle">
                            <thead class="table-primary text-center">
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
                            <tbody class="text-center">
                                <c:forEach var="m" items="${mobiles}">
                                  <tr>
    <td>${m.mobileId}</td>
    <td>${m.description}</td>
    <td>$${m.price}</td>
    <td>${m.mobileName}</td>
    <td>${m.yearOfProduction}</td>
    <td>${m.quantity}</td>
    <td>
        <span class="badge bg-${m.notSale ? 'danger' : 'success'}">
            <c:out value="${m.notSale ? 'Yes' : 'No'}"/>
        </span>
    </td>
    <td>
        <form action="CartController" method="post" class="d-inline">
            <input type="hidden" name="action" value="Add">
            <input type="hidden" name="mobileId" value="${m.mobileId}">
            <input type="hidden" name="quantity" value="1">
            <button type="submit" class="btn btn-success btn-sm">➕ Add to Cart</button>
        </form>
    </td>
</tr>

                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:when>
                <c:otherwise>
                    <p class="text-muted text-center"> No mobiles available at the moment.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

<!-- Footer -->
<footer class="text-center text-muted small">
    &copy; WS02
</footer>

<!-- Bootstrap 5 JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
