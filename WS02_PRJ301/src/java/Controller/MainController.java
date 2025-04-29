package Controller;

import Models.DTO.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";
    private static final String STAFF_HOME = "staffHome.jsp";
    private static final String USER_HOME = "userHome.jsp";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String ADD_TO_CART_CONTROLLER = "AddToCartController";
    private static final String DELETE_MOBILE_CONTROLLER = "DeleteMobileController";
    private static final String INSERT_MOBILE_CONTROLLER = "InsertMobileController";
    private static final String UPDATE_CART_CONTROLLER = "UpdateCartController";
    private static final String UPDATE_MOBILE_CONTROLLER = "UpdateMobileController";
    private static final String LIST_ALL_CONTROLLER = "ListAllController";  // Default list all

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        String url = LOGIN_PAGE;

        try {
            HttpSession session = request.getSession(false);
            User user = (session != null) ? (User) session.getAttribute("user") : null;
            int role = (user != null) ? user.getRole() : -1;
            System.out.println("User in session: " + user);
            System.out.println("Role: " + role);

            if (action == null || action.equals("Login")) {
                url = LOGIN_CONTROLLER;
            } else if (action.equals("logout")) {
                url = LOGOUT_CONTROLLER;
            } else if (action.equals("AddToCart")) {
                url = ADD_TO_CART_CONTROLLER;
            } else if (action.equals("deleteMobile")) {
                url = DELETE_MOBILE_CONTROLLER;
            } else if (action.equals("insertMobile")) {
                url = INSERT_MOBILE_CONTROLLER;
            } else if (action.equals("updateCart")) {
                url = UPDATE_CART_CONTROLLER;
            } else if (action.equals("searchMobile")) {
                url = "SearchMobileController";
            }
            else if (action.equals("searchByPrice")){
                url = "SearchByPriceController";
            }
                else if (action.equals("updateMobile")) {
                url = UPDATE_MOBILE_CONTROLLER;
            } 
            else if (action.equals("home")) {
    if (role == 1) {
        request.getRequestDispatcher("ListAllController").forward(request, response);
        return;
    } else if (role == 0) {
        request.getRequestDispatcher("ListAllController").forward(request, response);
        return;
    } else {
        request.setAttribute("ERROR", "Unauthorized access.");
        url = LOGIN_PAGE;
    }

            } else if (action.equals("listAll")) {  // Handle the listAll action for both staff and user
                // Forward to the ListAllController that will handle fetching and displaying the list of all mobiles
                url = LIST_ALL_CONTROLLER;
            } else {
                request.setAttribute("ERROR", "Invalid action.");
                url = LOGIN_PAGE;
            }

    }
    catch (Exception e

    
        ) {
            log("Error at MainController: " + e.toString());
        request.setAttribute("ERROR", "Internal server error.");
        url = LOGIN_PAGE;
    }

    
        finally {
            request.getRequestDispatcher(url).forward(request, response);
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
        return "MainController handles all action routing and role-based home redirection.";
    }
}
