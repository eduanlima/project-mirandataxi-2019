/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Client;
import ModelDAO.ClientDAO;
import Util.CripPassword;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author GRAFIX
 */
@WebServlet(name = "InputPasswordAlter", urlPatterns = {"/InputPasswordAlter"})
public class InputPasswordAlter extends HttpServlet {

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
        
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        
        String idClient = (String) session.getAttribute("userId"); /*** Pega o id que está na sessão ***/
        String passClientPrev = request.getParameter("current_psw"); /*** Aqui é setado o campo com a senha atual***/
        
        Client client = new Client();
        client.setId(Integer.parseInt(idClient));
        
        try {
            client.setPassword(CripPassword.encrypt(passClientPrev));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RegisterClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RegisterClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        /*** Processo para inserir a nova senha ***/
        ClientDAO clientDAO = new ClientDAO();
        
        if(clientDAO.loginClientPass(client)){
            out.println(1); /*** Usuário encontrado ***/
        }else{
            out.println(0); /*** Se o usuário não for encontrado***/
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
