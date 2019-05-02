/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.ClassCar;
import Model.Running;
import ModelDAO.ClassCarDAO;
import Util.MaskMetric;
import Util.MaskMonetary;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
@WebServlet(name = "CalculateRunning", urlPatterns = {"/CalculateRunning"})
public class CalculateRunning extends HttpServlet {

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
        
        String classSelected = (String) session.getAttribute("classCar");
        String typeRun = request.getParameter("typeRun");
        String valueRun = request.getParameter("valueRun");
        String valueAddKm = request.getParameter("valueKmAdd");
        
        Running running = new Running();
        ClassCar classCar = new ClassCar();
        
        classCar.setId(classSelected);
        
        ClassCarDAO classCarDAO = new ClassCarDAO();
        classCarDAO.accessClass(classCar);
        
        BigDecimal bigValueRun = new BigDecimal(valueRun);
        BigDecimal bigValueAddKm = new BigDecimal(valueAddKm);
       
        running.setTypeRun(typeRun);
        running.setValueRun(bigValueRun);
        running.setValueAddKm(bigValueAddKm);
        
        running.calculateTotal(classCar);
        
        String vetResult[] = new String[4];
        
        if(typeRun.equals("k")){
            vetResult[0] = running.getTotal().toString();
            vetResult[1] = running.getValueRun().toString();
            vetResult[2] = running.getValueAddKm().toString();
            vetResult[3] = running.getTotalKmAdd().toString();
            session.setAttribute("total", running.getTotal());
            
        }else if((typeRun.equals("h")) || typeRun.equals("t")){
            vetResult[0] = running.getTotal().toString();
            vetResult[1] = "0";
            vetResult[2] = running.getValueAddKm().toString();
            vetResult[3] = running.getTotalKmAdd().toString();
            session.setAttribute("total", running.getTotal());
        }
        
        session.setAttribute("valuesClass","{\"class\":\""+classSelected+"\",\"hrcont\":"+ Double.parseDouble(running.getHourCont().setScale(2,RoundingMode.FLOOR).toString()) 
                                        +",\"kmcont\":"+ Double.parseDouble(running.getKmCont().setScale(3,RoundingMode.FLOOR).toString())
                                        +",\"kmaddtotal\":"+ Double.parseDouble(running.getTotalKmAdd().setScale(2,RoundingMode.FLOOR).toString())
                                        +",\"kmadd\":"+ Double.parseDouble(running.getValueAddKm().setScale(3,RoundingMode.FLOOR).toString())
                                        +",\"partinal\":"+Double.parseDouble(running.getTotalPartial().setScale(2,RoundingMode.FLOOR).toString())+"}");
        
        Gson gson = new Gson();
        String result = gson.toJson(vetResult);
        
        out.println(result);
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
