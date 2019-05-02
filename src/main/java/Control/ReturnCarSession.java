/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.ClassCar;
import ModelDAO.ClassCarDAO;
import Util.MaskMonetary;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author eduan
 */
@WebServlet(name = "ReturnCarSession", urlPatterns = {"/ReturnCarSession"})
public class ReturnCarSession extends HttpServlet {

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
            PrintWriter out = response.getWriter();
            
            HttpSession session = request.getSession();
            String carSession = ""+session.getAttribute("classCar");
            ClassCar classCar = new ClassCar();
            
            ClassCarDAO classCarDAO = new ClassCarDAO();
            
            classCar.setId(carSession);
            
            classCar = classCarDAO.accessClass(classCar);
            
            BigDecimal bigPriceHour = new BigDecimal(""+classCar.getPriceHour());
            BigDecimal bigPriceKm = new BigDecimal(""+classCar.getPriceKm());
            
            String priceHour = bigPriceHour.toString();
            String priceKm = bigPriceKm.toString();
            
            String vetClass[] = {classCar.getId(),classCar.getName(),priceHour,priceKm};
            
            Gson gson = new Gson();
            String classGson = gson.toJson(vetClass);
            out.println(classGson);
            //out.println(session.getAttribute("classCar"));
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
