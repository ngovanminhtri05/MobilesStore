/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Cart;

import Controller.Cart.CartUtil;
import Models.DAO.MobileDAO;
import Models.DTO.CartItem;
import Models.DTO.Mobiles;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tri Minh
 */
@WebServlet(name = "AddToCartController", urlPatterns = {"/AddToCartController"})
public class AddToCartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
         protected void processRequest(HttpServletRequest request,
                                   HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = null;
        String message = "", bookId;
        Mobiles selectedMobile = null;
        CartItem item = null;
        HashMap<String, CartItem> itemsInCart = null;

        try {
            MobileDAO mobileDAO = new MobileDAO();
            HttpSession shoppingCart = request.getSession();
            itemsInCart = (HashMap<String, CartItem>) shoppingCart.getAttribute("Cart");
            bookId = request.getParameter("mobileId");
            selectedMobile = mobileDAO.getMobileById(bookId);

if (selectedMobile == null) {
    message = "Mobile not found.";
    request.setAttribute("MESSAGE_TYPE", "error");
} else if (selectedMobile.isNotSale()) {
    message = "This mobile is not for sale.";
    request.setAttribute("MESSAGE_TYPE", "error");
} else {
    if (itemsInCart == null) {
        itemsInCart = new HashMap<>();
        shoppingCart.setAttribute("Cart", itemsInCart);
    }
    item = itemsInCart.get(selectedMobile.getMobileId());

    if (item == null) {
        // First time adding to cart
        if (selectedMobile.getQuantity() > 0) {
            item = new CartItem(
                selectedMobile.getMobileId(),
                selectedMobile.getMobileName(),
                1,
                selectedMobile.getPrice()
            );
            itemsInCart.put(item.getItemId(), item);
            message = "The mobile " + item.getItemName() + " has been added to cart successfully.";
            request.setAttribute("MESSAGE_TYPE", "success");
        } else {
            message = "Out of stock.";
            request.setAttribute("MESSAGE_TYPE", "error");
        }
    } else {
        // Already in cart, check if adding exceeds available quantity
        int newQuantity = item.getQuantity() + 1;
        if (newQuantity <= selectedMobile.getQuantity()) {
            item.setQuantity(newQuantity);
            message = "The cart has been updated successfully.";
            request.setAttribute("MESSAGE_TYPE", "success");
        } else {
            message = "Not enough stock available. Only " + selectedMobile.getQuantity() + " items in stock.";
            request.setAttribute("MESSAGE_TYPE", "error");
        }
    }
}
request.setAttribute("ERROR_MESSAGE", message);

            url = "MainController?action=home";
            request.setAttribute("ERROR_MESSAGE", message); // use ERROR_MESSAGE attribute
        } catch (Exception ex) {
            log("AddToCartController has error: " + ex.getMessage() );
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          processRequest(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
