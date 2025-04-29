<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update Mobile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f2f2;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .container {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
            width: 400px;
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }
        label {
            display: block;
            margin-top: 15px;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"], select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            margin-top: 20px;
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            border: none;
            border-radius: 5px;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .back-link {
            display: block;
            text-align: center;
            margin-top: 15px;
            color: #4CAF50;
            text-decoration: none;
        }
        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Update Mobile</h2>
        <form action="UpdateMobileController" method="post">
            <input type="hidden" name="mobileId" value="${mobile.mobileId}"/>

            <label for="description">Description:</label>
            <input type="text" id="description" name="description" value="${mobile.description}" required/>

            <label for="price">Price:</label>
            <input type="text" id="price" name="price" value="${mobile.price}" required/>

            <label for="mobileName">Mobile Name:</label>
            <input type="text" id="mobileName" name="mobileName" value="${mobile.mobileName}" required/>

            <label for="yearOfProduction">Year of Production:</label>
            <input type="text" id="yearOfProduction" name="yearOfProduction" value="${mobile.yearOfProduction}" required/>

            <label for="quantity">Quantity:</label>
            <input type="text" id="quantity" name="quantity" value="${mobile.quantity}" required/>

            <label for="notSale">Not for Sale:</label>
            <select name="notSale" id="notSale">
                <option value="Yes" ${mobile.notSale ? 'selected' : ''}>Yes</option>
                <option value="No" ${!mobile.notSale ? 'selected' : ''}>No</option>
            </select>

            <input type="submit" value="Update Mobile">
        </form>
        <a href="MainController?action=listAll" class="back-link">Back to All Mobiles</a>
    </div>
</body>
</html>
