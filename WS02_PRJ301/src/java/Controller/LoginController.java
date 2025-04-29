package Controller;

import Models.DAO.UserDAO;
import Models.DTO.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String userID = request.getParameter("userID");
        int password = Integer.parseInt(request.getParameter("password"));
        
        UserDAO dao = new UserDAO();
        User user = dao.checkLogin(userID, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("MainController?action=home");

        } else {
            request.setAttribute("error", "Invalid ID or Password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // Optional: redirect to login page if accessed by GET
        response.sendRedirect("login.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Handles user login.";
    }
}
