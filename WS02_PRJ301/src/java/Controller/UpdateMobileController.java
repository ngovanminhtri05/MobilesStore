/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Models.DAO.MobileDAO;
import Models.DTO.Mobiles;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tri Minh
 */
@WebServlet(name = "UpdateMobileController", urlPatterns = {"/UpdateMobileController"})
public class UpdateMobileController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateMobileController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateMobileController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String id = request.getParameter("id");
        try {
            MobileDAO dao = new MobileDAO();
            Mobiles mobile = dao.getMobileById(id);  

            if (mobile != null) {
                request.setAttribute("mobile", mobile);
                request.getRequestDispatcher("updateMobile.jsp").forward(request, response);
            } else {
                response.sendRedirect("MainController?action=listAll");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("MainController?action=listAll");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String mobileId = request.getParameter("mobileId");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String mobileName = request.getParameter("mobileName");
        String yearOfProduction = request.getParameter("yearOfProduction");
        String quantity = request.getParameter("quantity");
        String notSale = request.getParameter("notSale");

        try {
            Mobiles mobile = new Mobiles();
            mobile.setMobileId(mobileId);
            mobile.setDescription(description);
            mobile.setPrice(Float.parseFloat(price));
            mobile.setMobileName(mobileName);
            mobile.setYearOfProduction(Integer.parseInt(yearOfProduction));
            mobile.setQuantity(Integer.parseInt(quantity));
            mobile.setNotSale("Yes".equalsIgnoreCase(notSale));

            MobileDAO dao = new MobileDAO();
            boolean success = dao.updateMobile(mobile); 

            if (success) {
                response.sendRedirect("MainController?action=listAll");
            } else {
                request.setAttribute("error", "Update failed. Please try again.");
                request.setAttribute("mobile", mobile);
                request.getRequestDispatcher("updateMobile.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Invalid input.");
            request.getRequestDispatcher("updateMobile.jsp").forward(request, response);
        }
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
