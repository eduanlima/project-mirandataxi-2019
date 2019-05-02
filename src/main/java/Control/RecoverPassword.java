/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Client;
import ModelDAO.ClientDAO;
import Util.CripPassword;
import Util.Email;
import Util.UniversallyUniqueIdentifier;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eduan
 */
@WebServlet(name = "RecoverPassword", urlPatterns = {"/RecoverPassword"})
public class RecoverPassword extends HttpServlet {

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
            throws ServletException, IOException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String email =  request.getParameter("nEmailRecover");
        
        Client client = new Client();
        client.setEmail(email);
        ClientDAO clientDAO = new ClientDAO();
        
        client = clientDAO.readEmailClient(client);
        
        if(client.getId()!= 0){

            //Creating a new password
            String newpass = UniversallyUniqueIdentifier.getUUID();
            newpass = newpass.substring(0,6);
            client.setPassword(CripPassword.encrypt(newpass));
            clientDAO.alterPassword(client);
            
            //Sending email
            Email emailRecover = new Email();
            emailRecover.setDestinatary(client.getEmail());
            emailRecover.setSubject("Recuperação de senha (Não responda este e-mail).");
            
            StringBuilder text = new StringBuilder();
            text.append("<div style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; position: relative; width: 600px; height: 590px; background-image: url('https://mirandataxi.com.br/img/background/rec-senha.jpg');\">\n" +
                        "<div style=\" margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline;position: relative; width: 100%; height: 140px; padding-top: 140px; text-align: center; font-family: 'Arial', 'Calibri'; color: #000000; display: block;\">\n" +
                        "<h1 style=\"margin: 0;padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: bold; font-size: 25px;\">Olá, "+client.getName()+"</h1>\n" +
                        "<p style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: normal; font-size: 20px;\">Utilize esta nova senha para acessar sua conta:</p>\n" +
                        "<table style=\"margin: 0; padding: 0; border: 0; font-size: 100%; font: inherit; vertical-align: baseline; border-collapse: collapse; border-spacing: 0; width: 300px; border: 2px solid #000000; margin-left: 149px; margin-top: 15px; padding: 5px; text-align: right; font-family: 'Arial', 'Calibri'; color: #ffffff; display: block;\">\n" +
                        "<tr style=\"height: 100px;\"><td style=\"width: 100px; background-image: url('https://mirandataxi.com.br/img/icon/icon-key.png'); background-repeat: no-repeat;\"><td/> <td style=\"width: 185px;\"><h2 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: bold; font-size: 55px; margin-top: 8px; color: #000000;\">"+newpass+"<h2/><td/></tr></table>\n" +
                        "<h2 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: bold; font-size: 14px; margin-top: 5px;\">ATENÇÃO: Após o acesso de sua conta, crie uma <br> nova senha para acessos posteriores.</h2>\n" +
                        "<a href='https://www.mirandataxi.com.br/'>CLIQUE AQUI</a></div>\n" +
                        "<table style=\"margin: 0; padding: 0; border: 0; font-size: 100%; font: inherit; vertical-align: baseline; border-collapse: collapse; border-spacing: 0; width: 200px; margin-top: 140px; margin-left: 20px; text-align: right; font-family: 'Arial', 'Calibri'; color: #ffffff; display: block;\">\n" +
                        "<tr><th style= \"width: 200px;\"><h1 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; margin-top: 10px; font-weight: bold; font-size: 30px;\">CONTATOS</h1></th></tr>\n" +
                        "<tr><td><a href=\"https://api.whatsapp.com/send?phone=5511998965109\" style=\"float: right; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: regular; font-size: 85%; color: #ffffff;\">(11) 99896-5109</a><img src=\"https://mirandataxi.com.br/img/icon/icon-whats-email.png\" style=\"margin-right: 3px; display: inline-block; float: right; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; position: relative; width: 16px; height: 16px;\"></img></td></tr>\n" +
                        "<tr><td><h2 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: regular; font-size: 12px;\">contato@mirandataxi.com</h2></td></tr>\n" +
                        "<tr><td><h3 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: regular; font-size: 10px;\">SIGA-NOS NA REDES SOCIAIS</h3></td></tr>\n" +
                        "<tr><td>\n" +
                        "<a href=\"https://www.instagram.com/?hl=pt-br\" style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; display: inline-block; float: right; margin-left: 8px; width: 20px; height: 20px; background-image: url('https://mirandataxi.com.br/img/icon/icon-inst-email.png'); background-repeat: no-repeat;\"></a>\n" +
                        "<a href=\"https://www.facebook.com/\" style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; display: inline-block; float: right; width: 20px; height: 20px; background-image: url('https://mirandataxi.com.br/img/icon/icon-face-email.png'); background-repeat: no-repeat;\"></a></td></tr>\n" +
                        "<tr><td><h4 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: regular; font-size: 9px;\">© MirandaTaxi. All rights reserved.</h4></td></tr>\n" +
                        "</table></div> ");
            emailRecover.setMensagem(text.toString());
                        
            if(emailRecover.sendEmail()){
                out.println("Nova senha enviada em seu email com sucesso!");
            }else{
                out.println("Ocorreu um erro ao enviar o email. Tente novamente mais tarde.");
            }
            
        }else if(client.getId() == 0){
            out.println("Email não encontrado em nossos cadastros.");
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RecoverPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RecoverPassword.class.getName()).log(Level.SEVERE, null, ex);
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
