package Controller;

import Models.DAO.MobileDAO;
import Models.DTO.Mobiles;
import Models.DTO.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ListAllController", urlPatterns = {"/ListAllController"})
public class ListAllController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
           HttpSession session = request.getSession(false);
User user = (session != null) ? (User) session.getAttribute("user") : null;

if (user == null) {
    response.sendRedirect("login.jsp");
    return;
}

MobileDAO dao = new MobileDAO();
List<Mobiles> mobiles = dao.getAllMobiles();
request.setAttribute("mobiles", mobiles);

// Forward based on role
if (user.getRole() == 1) {
    request.getRequestDispatcher("staffHome.jsp").forward(request, response);
} else {
    request.getRequestDispatcher("userHome.jsp").forward(request, response);
}

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "Error fetching mobiles.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
}