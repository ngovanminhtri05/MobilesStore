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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tri Minh
 */
@WebServlet(name = "InsertMobileController", urlPatterns = {"/InsertMobileController"})
public class InsertMobileController extends HttpServlet {

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
            out.println("<title>Servlet InsertMobileController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertMobileController at " + request.getContextPath() + "</h1>");
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
        // Forward to the insert form JSP
        request.getRequestDispatcher("insertMobile.jsp").forward(request, response);
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
            Mobiles newMobile = new Mobiles();
            newMobile.setMobileId(mobileId);
            newMobile.setDescription(description);
            newMobile.setPrice(Float.parseFloat(price));
            newMobile.setMobileName(mobileName);
            newMobile.setYearOfProduction(Integer.parseInt(yearOfProduction));
            newMobile.setQuantity(Integer.parseInt(quantity));
            newMobile.setNotSale("Yes".equalsIgnoreCase(notSale));

            MobileDAO dao = new MobileDAO();
            boolean success = dao.insertMobile(newMobile);

            if (success) {
                response.sendRedirect("MainController?action=listAll");
            } else {
                request.setAttribute("error", "Insert failed. Mobile ID might already exist.");
                request.getRequestDispatcher("insertMobile.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Duplicate ID");
            request.getRequestDispatcher("insertMobile.jsp").forward(request, response);
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
