package Controller.Cart;

import Models.DAO.MobileDAO;
import Models.DTO.CartItem;
import Models.DTO.Mobiles;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UpdateCartController", urlPatterns = {"/UpdateCartController"})
public class UpdateCartController extends HttpServlet {
    private final String cartController = "CartController";

    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = cartController;
        String message;
        String itemId;
        int newQuantity;
        CartItem item = null;
        HashMap<String, CartItem> cart = null;

        try {
            itemId = request.getParameter("ItemId");
            newQuantity = Integer.parseInt(request.getParameter("quantity"));

            if (itemId != null) {
                HttpSession sessionCart = request.getSession();
                cart = (HashMap<String, CartItem>) sessionCart.getAttribute("Cart");

                if (cart != null) {
                    item = cart.get(itemId);

                    if (item != null) {
                        // Load latest quantity from database
                        MobileDAO dao = new MobileDAO();
                        Mobiles mobile = dao.getMobileById(itemId);

                        if (mobile != null) {
                            int availableStock = mobile.getQuantity();
                            if (newQuantity <= availableStock) {
                                item.setQuantity(newQuantity);
                                message = "Your cart has been updated successfully.";
                                request.setAttribute("MESSAGE_TYPE", "success");
                            } else {
                                // If requested quantity > stock, set maximum possible
                                item.setQuantity(availableStock);
                                message = "Only " + availableStock + " items available. Quantity adjusted.";
                                request.setAttribute("MESSAGE_TYPE", "error");
                            }
                        } else {
                            message = "Mobile not found.";
                            request.setAttribute("MESSAGE_TYPE", "error");
                        }
                    } else {
                        message = "Item not found in cart.";
                        request.setAttribute("MESSAGE_TYPE", "error");
                    }
                } else {
                    message = "Cart is empty.";
                    request.setAttribute("MESSAGE_TYPE", "error");
                }
            } else {
                message = "Invalid item.";
                request.setAttribute("MESSAGE_TYPE", "error");
            }
            request.setAttribute("Message", "<h4>" + message + "</h4>");
            url = cartController + "?action=ViewCart";
        } catch (Exception ex) {
            log("UpdateCartController error: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Update Cart Controller";
    }
}
