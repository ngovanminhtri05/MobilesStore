/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Models.DAO.MobileDAO;
import Models.DTO.Mobiles;
import Models.DTO.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tri Minh
 */
@WebServlet(name = "SearchByPriceController", urlPatterns = {"/SearchByPriceController"})
public class SearchByPriceController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null || user.getRole() != 0) {
                response.sendRedirect("login.jsp");
                return;
            }

            // Get the min and max price from request parameters
            float minPrice = Float.parseFloat(request.getParameter("minPrice"));
            float maxPrice = Float.parseFloat(request.getParameter("maxPrice"));

            // Validate that the minimum price is less than or equal to the maximum price
            if (minPrice > maxPrice) {
                request.setAttribute("ERROR_MESSAGE", "Minimum price cannot be greater than maximum price.");
                request.getRequestDispatcher("userHome.jsp").forward(request, response);
                return; // Stop further processing
            }

            // Perform the search based on the price range
            MobileDAO dao = new MobileDAO();
            List<Mobiles> list = dao.searchMobileByPriceRange(minPrice, maxPrice);

            // Set the filtered list of mobiles in the request
            request.setAttribute("mobiles", list); 
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR_MESSAGE", "Something went wrong during search!");
        } finally {
            // Forward the request to the user home page with results or error message
            request.getRequestDispatcher("userHome.jsp").forward(request, response);
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
