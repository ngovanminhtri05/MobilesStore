<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Results</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
</head>
<body class="container py-4">

    <h2 class="mb-4">Search Results for "<span class="text-primary">${searchKeyword}</span>"</h2>

    <c:choose>
        <c:when test="${not empty resultList}">
            <table class="table table-bordered table-striped">
                <thead class="table-dark">
                    <tr>
                        <th>Mobile ID</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Year</th>
                        <th>Quantity</th>
                        <th>Not Sale</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="m" items="${resultList}">
                        <tr>
                            <td>${m.mobileId}</td>
                            <td>${m.mobileName}</td>
                            <td>${m.description}</td>
                            <td>${m.price}</td>
                            <td>${m.yearOfProduction}</td>
                            <td>${m.quantity}</td>
                            <td><c:out value="${m.notSale ? 'Yes' : 'No'}"/></td>
                            <td>
                                <form action="MainController" method="get" class="d-inline">
                                    <input type="hidden" name="action" value="updateMobile"/>
                                    <input type="hidden" name="id" value="${m.mobileId}"/>
                                    <button class="btn btn-sm btn-warning">Update</button>
                                </form>
                                <form action="MainController" method="get" class="d-inline ms-1">
                                    <input type="hidden" name="action" value="deleteMobile"/>
                                    <input type="hidden" name="id" value="${m.mobileId}"/>
                                    <button class="btn btn-sm btn-danger">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <div class="alert alert-warning">No results found for "${searchKeyword}".</div>
        </c:otherwise>
    </c:choose>

    <a href="MainController?action=listAll" class="btn btn-secondary mt-3">Back to List</a>

</body>
</html>
