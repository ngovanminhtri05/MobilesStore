<%@page import="java.util.List"%>
<%@page import="Models.DTO.CartItem"%>
<%@page import="java.util.Map" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f9;
                margin: 0;
                padding: 20px;
            }

            h1 {
                text-align: center;
                color: #333;
            }

            table {
                width: 80%;
                margin: 0 auto;
                border-collapse: collapse;
                background-color: white;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            th, td {
                padding: 10px;
                text-align: center;
                border: 1px solid #ddd;
            }

            th {
                background-color: #4CAF50;
                color: white;
            }

            td input[type="number"] {
                width: 60px;
                padding: 5px;
                text-align: center;
            }

            .actions input[type="submit"] {
                padding: 5px 10px;
                background-color: #007BFF;
                border: none;
                color: white;
                border-radius: 4px;
                cursor: pointer;
            }

            .actions input[type="submit"]:hover {
                background-color: #0056b3;
            }

            .message {
                text-align: center;
                font-size: 1.2em;
                color: red;
            }

            .cart-summary {
                text-align: center;
                margin-top: 20px;
                font-size: 1.2em;
                font-weight: bold;
            }

            .cart-summary td {
                padding: 10px 15px;
            }

            .cart-buttons {
                text-align: center;
                margin-top: 20px;
            }

            .cart-buttons input[type="submit"] {
                padding: 10px 20px;
                background-color: #28a745;
                border: none;
                color: white;
                font-size: 1.1em;
                border-radius: 5px;
                cursor: pointer;
                margin: 0 10px;
            }

            .cart-buttons input[type="submit"]:hover {
                background-color: #218838;
            }

        </style>
    </head>
    <body>
        <h1>Your Cart</h1>
        <%
            double totalAmount = 0;
            int numberOfMobiles = 0;
            List<CartItem> itemsInCart = (List<CartItem>) request.getAttribute("Cart");
        %>
        <%
            if (itemsInCart == null || itemsInCart.size() == 0) {
        %>
        <div class="message">
            <h3>Cart is empty!!!</h3>
        </div>
        <%
        } else {
        %>
        <table>
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Id</th>
                    <th>Title</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>SubTotal</th>
                    <th colspan="2">Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (CartItem item : itemsInCart) {
                        totalAmount += item.getSubTotal();
                        numberOfMobiles += item.getQuantity();
                %>
          <form action="CartController" method="post">
    <tr>
        <td><%= (++count)%></td>
        <td><%= item.getItemId()%>
            <input type="hidden" value="<%= item.getItemId()%>" name="ItemId"/>
        </td>
        <td><%= item.getItemName()%></td>
        <td><%= item.getUnitPrice()%></td>
        <td>
            <input type="number" min="1" name="quantity" value="<%= item.getQuantity()%>"/>
        </td>
        <td><%= String.format("%.2f", item.getSubTotal())%></td>
        <td class="actions">
            <input type="submit" value="Update" name="action" />
            <input type="submit" value="Remove" name="action" />
        </td>
    </tr>
</form>

            <% } // end for %>
            </tbody>
        </table>
        <div class="cart-summary">
            <table>
                <tr>
                    <td>Total Amount</td>
                    <td><%= String.format("%.2f", totalAmount)%></td>
                </tr>
            </table>
            <h3>Number of mobiles in cart: <%= numberOfMobiles%></h3>
        </div>
        <% } %>
        <div class="cart-buttons">
            <form method="Post">
                <input type="submit" formaction="MainController?action=home" value="Continue" />
                <input type="submit" formaction="CartController?action=Save" value="Save Cart" />
            </form>
        </div>
        <% if (request.getAttribute("Message") != null) {%>
        <div class="message">
            <%= request.getAttribute("Message")%>
        </div>
        <% } %>
    </body>
</html>
