e<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Insert New Mobile</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="mb-4">Insert New Mobile</h2>

    <form action="MainController" method="post">
        <input type="hidden" name="action" value="insertMobile" />

        <% String error = (String) request.getAttribute("error"); %>
        <% if (error != null) { %>
            <div class="alert alert-danger"><%= error %></div>
        <% } %>

        <div class="mb-3">
            <label for="mobileId" class="form-label">Mobile ID</label>
            <input type="text" class="form-control" id="mobileId" name="mobileId" required>
        </div>

        <div class="mb-3">
            <label for="mobileName" class="form-label">Mobile Name</label>
            <input type="text" class="form-control" id="mobileName" name="mobileName" required>
        </div>

        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
        </div>

        <div class="mb-3">
            <label for="price" class="form-label">Price ($)</label>
            <input type="number" step="0.01" class="form-control" id="price" name="price" required>
        </div>

        <div class="mb-3">
            <label for="yearOfProduction" class="form-label">Year of Production</label>
            <input type="number" class="form-control" id="yearOfProduction" name="yearOfProduction" required>
        </div>

        <div class="mb-3">
            <label for="quantity" class="form-label">Quantity</label>
            <input type="number" class="form-control" id="quantity" name="quantity" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Not for Sale?</label>
            <select class="form-select" name="notSale">
                <option value="No">No</option>
                <option value="Yes">Yes</option>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Insert Mobile</button>
        <a href="MainController?action=listAll" class="btn btn-secondary">Cancel</a>
    </form>
</div>

</body>
</html>
