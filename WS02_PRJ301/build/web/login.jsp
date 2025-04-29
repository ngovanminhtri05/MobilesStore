<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f0f0f0;
        }
        .login-box {
            background: white;
            width: 300px;
            padding: 20px;
            margin: 100px auto;
            border-radius: 10px;
            box-shadow: 0 0 10px #aaa;
        }
        h2 {
            text-align: center;
        }
        input[type=text], input[type=password] {
            width: 100%;
            padding: 10px;
            margin: 5px 0 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[type=submit] {
            background: #007BFF;
            color: white;
            padding: 10px;
            width: 100%;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .error {
            color: red;
            text-align: center;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="login-box">
    <h2>Login</h2>
    <form action="MainController" method="post">
        <input type="hidden" name="action" value="Login">
        
        <label for="userID">User ID:</label>
        <input type="text" id="userID" name="userID" required>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        
        <input type="submit" value="Login">
        
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
    </form>
</div>
</body>
</html>
