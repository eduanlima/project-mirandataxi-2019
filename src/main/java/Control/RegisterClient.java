/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Client;
import Model.Phone;
import ModelDAO.ClientDAO;
import Util.CripPassword;
import Util.Email;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "RegisterClient", urlPatterns = {"/RegisterClient"})
public class RegisterClient extends HttpServlet {

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
        Client client;
        Phone phone;
        List<Phone> listPhone = new ArrayList<>();
            
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String vetPhone[] = {request.getParameter("phone-one"), request.getParameter("phone-two"), request.getParameter("phone-tree")};
        
        PrintWriter out = response.getWriter();
        
        client = new Client();
        ClientDAO clientDAO = new ClientDAO();
        
        client.setEmail(email);
        
        client = clientDAO.readEmailClient(client);
        
        
        if(client.getId() == 0){
            client.setName(name+" ");
            
            try {
                client.setPassword(CripPassword.encrypt(password));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(RegisterClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(RegisterClient.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (int i = 0; i < 3; i++){
                if(vetPhone[i] != null){
                    phone = new Phone(vetPhone[i]);
                    listPhone.add(i, phone);
                }
            }

            client.setPhone(listPhone);

            if(clientDAO.registerClient(client)){
                //Send email
                Email emailRegister = new Email();
                emailRegister.setDestinatary(email);
                emailRegister.setSubject("Confirmação de cadastro (Não responda este e-mail).");

                StringBuilder text = new StringBuilder();
                text.append("<div style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; width: 600px; height: 600px; background-image: url('https://mirandataxi.com.br/img/background/conf-cad.jpg');\">\n" +
                            "<table style=\"margin: 0; padding: 0; border: 0; font-size: 100%; font: inherit; vertical-align: baseline; border-collapse: collapse; border-spacing: 0; width: 600px; font-family: 'Arial', 'Calibri'; display: block; border-collapse: collapse;\">\n" +
                            "<tr><th colspan=\"2\" style= \"width: 600px; text-align: center;\"><h1 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: bold; font-size: 16px; color: #000000;\">\n" +
                            "<h1 style=\"margin: 0;padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: bold; font-size: 20px; padding-top: 165px;\">Olá, "+client.getName()+"</h1></h1></th></tr>\n" +
                            "<tr><td colspan=\"2\" style= \"width: 600px; text-align: center;\"><p style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: normal; font-size: 12px; color: #000000;\">Obrigado por realizar seu cadastro em nosso site. \n" +
                            "<br>Agora está tudo pronto para nossos serviços serem utilizados.</p>\n" +
                            "<a style=\"font-size: 12px;\" href='https://www.mirandataxi.com.br/'>CLIQUE AQUI</a></td></tr>\n" +
                            "<tr><td colspan=\"2\" style= \"width: 600px; text-align: center;\">\n" +
                            "<h1 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: bold; font-size: 25px; color: #ffffff; padding-top: 255px;\">CONTATOS</h1></td></tr>\n" +
                            "<tr><td style=\"text-align: right; width: 48%; padding-right: 2%;\"><a href=\"https://api.whatsapp.com/send?phone=5511998965109\" style=\"float: right; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: regular; font-size: 85%; color: #ffffff;\">(11) 99896-5109</a>\n" +
                            "<img src=\"https://mirandataxi.com.br/img/icon/icon-whats-email.png\" style=\"margin-right: 3px; display: inline-block; float: right; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; position: relative; width: 16px; height: 16px;\"></img> <br> \n" +
                            "<h2 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: regular; font-size: 12px; color: #ffffff;\">contato@mirandataxi.com</h2></td>\n" +
                            "<td style=\"border-left: 1px solid white; text-align: left; padding-left: 2%;\"><h3 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: regular; font-size: 8px; float: left; color: #ffffff;\">SIGA-NOS NAS <br> REDES SOCIAIS</h3>\n" +
                            "<a href=\"https://www.facebook.com/\" style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; display: inline-block; float: left; width: 20px; height: 20px; margin-left: 5px; background-image: url('https://mirandataxi.com.br/img/icon/icon-face-email.png'); background-repeat: no-repeat;\"></a>\n" +
                            "<a href=\"https://www.instagram.com/?hl=pt-br\" style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; display: inline-block; float: left; margin-left: 5px; width: 20px; height: 20px; background-image: url('https://mirandataxi.com.br/img/icon/icon-inst-email.png'); background-repeat: no-repeat;\"></a><br>\n" +
                            "<h4 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: regular; font-size: 7px; margin-top: 4px; color: #ffffff;\">© MirandaTaxi. All rights reserved.</h4></td></tr>\n" +
                            "</table></div>");

                emailRegister.setMensagem(text.toString());
  
                try{
                    if(emailRegister.sendEmail()){
                        System.out.println("First email has sent.");
                        
                        //Envio de e-mail para Miranda Taxi                
                        emailRegister.setDestinatary("contato@mirandataxi.com.br");
                        emailRegister.setSubject("Novo cliente");

                        text = new StringBuilder();
                        text.append("<table style=\"margin: 0; padding: 0; border: 0; font-size: 100%; font: inherit; vertical-align: baseline; border-collapse: collapse; border-spacing: 0; width: 500px; border: 1px solid #000000; text-align: center; font-family: 'Arial', 'Calibri';\">\n" +
                                    "<tr><th colspan=\"2\" style=\"background-color: #000000; font-weight: bold; color:#ffffff\"><h1 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: bold; font-size: 20px;\">"+client.getName()+"</h1></th></tr>\n" +
                                    "<tr><td colspan=\"2\" style=\"border: 1px solid black;\"><h2 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: bold; font-size: 15px;\">Fez seu cadastro em nosso site.</h2></td><tr>\n" +
                                    "</table>");

                        emailRegister.setMensagem(text.toString());

                        if(emailRegister.sendEmail()){
                            System.out.println("Second email has sent.");
                        }else{
                            System.out.println("Second email hasn't sent");
                        }
                    }else{
                        System.out.println("First email hasn't sent");
                    }
                }catch(Exception erro){
                    throw new RuntimeException(erro);
                }finally{
                    out.println(2);
                }
                
            }else{
                out.println(0);
            }
            
        }else if(client.getId() != 0){
            out.println(1);
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
