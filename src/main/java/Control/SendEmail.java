/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Util.Email;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author GRAFIX
 */
@WebServlet(name = "SendEmail", urlPatterns = {"/SendEmail"})
public class SendEmail extends HttpServlet {

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
        
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String message = request.getParameter("message");
        
        PrintWriter out = response.getWriter();
        
        Email emailSite = new Email();
        emailSite.setDestinatary("contato@mirandataxi.com.br");
        emailSite.setSubject("Mensagem do cliente.");

        StringBuilder text = new StringBuilder();
        text.append("<table style=\"margin: 0; padding: 0; border: 0; font-size: 100%; font: inherit; vertical-align: baseline; border-collapse: collapse; border-spacing: 0; width: 500px; border: 1px solid #000000; text-align: center; font-family: 'Arial', 'Calibri';\">\n" +
                    "<tr><th colspan=\"2\" style=\"background-color: #000000; font-weight: bold; color:#ffffff\"><h1 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: bold; font-size: 20px;\">"+name+"</h1></th></tr>\n" +
                    "<tr><td colspan=\"2\" style=\"border: 1px solid black;\"><h2 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: bold; font-size: 15px;\">Cel./ Tel.: "+phone+"</h2></td><tr>\n" +
                    "<tr><td colspan=\"2\" style=\"border: 1px solid black;\"><h2 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: bold; font-size: 15px;\">E-mail: "+email+"</h2></td><tr>\n" +
                    "<tr style=\"background-color: #000000; font-weight: bold; color:#ffffff\"><td colspan=\"2\" style=\"border: 1px solid black; width: 250px;\">Mensagem</td></tr>\n" +
                    "<tr><td colspan=\"2\" style=\"border: 1px solid black;\"><p style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-family: 'Arial', 'Calibri'; font-weight: hormal; font-size: 14px; text-align: justify; width: 500px; padding-top: 5px;\">"+message+"</p></td><tr>\n" +
                    " </table>");

        emailSite.setMensagem(text.toString());
        
        if(emailSite.sendEmail()){
            out.print(true);
        }else{
            out.print(false);
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
