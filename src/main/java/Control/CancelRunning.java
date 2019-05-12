/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Client;
import Model.PaymentMain;
import Model.Running;
import ModelDAO.ClientDAO;
import ModelDAO.PaymentMainDAO;
import ModelDAO.RunningDAO;
import Util.Email;
import Util.LongHour;
import Util.UniversallyUniqueIdentifier;
import com.mercadopago.exceptions.MPException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jettison.json.JSONException;

/**
 *
 * @author GRAFIX
 */
@WebServlet(name = "CancelRunning", urlPatterns = {"/CancelRunning"})
public class CancelRunning extends HttpServlet {

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
            throws ServletException, IOException, JSONException, UnsupportedEncodingException, MPException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String codPayment = request.getParameter("codigo");
        long dataHora = Long.parseLong(request.getParameter("dataHora"));
        String messageClient = "", messageDriver = "";
        
        out.println(dataHora);

        PaymentMain paymentMain = new PaymentMain();
        
        PaymentMainDAO paymentMainDAO = new PaymentMainDAO();
        paymentMain.setId(codPayment);   
        
        paymentMain = paymentMainDAO.readPayment(paymentMain);
       
        long hourRun = LongHour.calculateHour(dataHora);
        
        if(paymentMain.getStatus().equals("approved")){
                paymentMain.setKeyRefund(UniversallyUniqueIdentifier.getUUID());

                if(hourRun <= 24){
                    paymentMain.refundHalfPayment(paymentMain.getId(), paymentMain.getAmount() / 2);
                    //partially_refunded
                    messageClient = "ATENÇÃO: O serviço foi cancelado dentro do período de 24h que antecediam sua execução,<br>assim sendo, de acordo com os termos de uso do serviço, o valor devolvido ao<br>cartão de crédito será igual à 50% do valor total do serviço.";
                    messageDriver = "Valor parcial devolvido ao cliente.";
                }else if(hourRun > 24){
                    paymentMain.refundPayment(paymentMain.getId());
                    messageClient = "ATENÇÃO: O valor da compra do serviço será totalmente<br>devolvido ao cartão de crédito.";
                    messageDriver = "Valor total devolvido ao cliente.";
                }
        }else{
                paymentMain.cancelPayment(paymentMain.getId());
                messageClient = "Serviço cancelado com sucesso.";
                messageDriver = "O pagamento ainda não havia sido aprovado.";
        }

        try{
            //Send e-mail
            paymentMain.readPayment(paymentMain.getId());
            paymentMainDAO.updatePaymentCancel(paymentMain);
         
            Running running = new Running();
            RunningDAO runningDAO = new RunningDAO();

            running = runningDAO.readRunning(running, paymentMain);
            
            Client client = new Client();
            ClientDAO clientDAO = new ClientDAO();
            
            client = clientDAO.readEmail(client, paymentMain);
            
            Email emailCancel = new Email();
            emailCancel.setDestinatary(client.getEmail());
            //emailTest.setDestinatary("eduanlimaphone@gmail.com");
            emailCancel.setSubject("Confirmação de cancelamento (Não responda este e-mail).");

            StringBuilder text = new StringBuilder();
            text.append("<div style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; position: relative; width: 600px; height: 590px; background-image: url('https://mirandataxi.com.br/img/background/rec-senha.jpg'); background-repeat: no-repeat;\">\n" +
            " <table style=\"margin: 0; padding: 0; border: 0; font-size: 100%; font: inherit; vertical-align: baseline; border-collapse: collapse; border-spacing: 0; width: 550px; margin-left: 20px; text-align: center; font-family: 'Arial', 'Calibri'; color: #ffffff; display: block;\">\n" +
            " <tr><th colspan=\"2\"><h1 style=\"margin: 0;padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: bold; font-size: 20px; color: #000000;  margin-top: 145px; \">Olá, "+client.getName()+"</h1></th></tr>\n" +
            " <tr><td colspan=\"2\"><p style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: normal; font-size: 18px; color: #000000;\">Sua corrida foi cancelada com sucesso.</p></tr></td>\n" +
            " <tr><td colspan=\"2\" style=\"background-color: #000000;\"><h2 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: bold; font-size: 15px; margin-top: 5px;\">CÓDIGO DO SERVIÇO</h2></td></tr>\n" +
            " <tr><td colspan=\"2\" style=\"width:100%; text-align: center;\"><h2 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: bold; font-size: 30px; color: #000000;\">#"+running.getId()+"<h2/><td/></tr></table>\n" +
            " <h2 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: bold; font-size: 12px; color: #000000; text-align: center; margin-bottom: 105px; font-family: 'Arial', 'Calibri'; display: block;\">"+messageClient+"</h2>\n" +
            " <table style=\"margin: 0; padding: 0; border: 0; font-size: 100%; font: inherit; vertical-align: baseline; border-collapse: collapse; border-spacing: 0; width: 200px; margin-left: 20px; text-align: right; font-family: 'Arial', 'Calibri'; color: #ffffff; background-color: #000000; display: block;\">\n" +
            " <tr><th colspan=\"2\" style= \"width: 200px;\"><h1 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: bold; font-size: 20px;\">CONTATOS</h1></th></tr>\n" +
            " <tr><td colspan=\"2\"><a href=\"https://api.whatsapp.com/send?phone=5511998965109\" style=\"float: right; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: regular; font-size: 85%; color: #ffffff;\">(11) 99896-5109</a><img src=\"https://mirandataxi.com.br/img/icon/icon-whats-email.png\" style=\"margin-right: 3px; display: inline-block; float: right; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; position: relative; width: 16px; height: 16px;\"></img></td></tr>\n" +
            " <tr><td colspan=\"2\"><h2 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: regular; font-size: 10px;\">contato@mirandataxi.com</h2></td></tr>\n" +
            " <tr><td colspan=\"2\"><h3 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: regular; font-size: 8px;\">SIGA-NOS NA REDES SOCIAIS</h3></td></tr>\n" +
            " <tr><td colspan=\"2\">\n" +
            " <a href=\"https://www.instagram.com/?hl=pt-br\" style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; display: inline-block; float: right; margin-left: 8px; width: 20px; height: 20px; background-image: url('https://mirandataxi.com.br/img/icon/icon-inst-email.png'); background-repeat: no-repeat;\"></a>\n" +
            " <a href=\"https://www.facebook.com/\" style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; display: inline-block; float: right; width: 20px; height: 20px; background-image: url('https://mirandataxi.com.br/img/icon/icon-face-email.png'); background-repeat: no-repeat;\"></a></td></tr>\n" +
            " <tr><td colspan=\"2\"><h4 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: regular; font-size: 8px;\">© MirandaTaxi. All rights reserved.</h4></td></tr>\n" +
            " </table></div>");

            emailCancel.setMensagem(text.toString());
            
            if(emailCancel.sendEmail()){
                System.out.println("First email has sent.");

                //Envio de e-mail para Miranda Taxi
                emailCancel.setDestinatary("contato@mirandataxi.com.br");
                emailCancel.setSubject("Cancelamento");

                text = new StringBuilder();
                text.append("<table style=\"margin: 0; padding: 0; border: 0; font-size: 100%; font: inherit; vertical-align: baseline; border-collapse: collapse; border-spacing: 0; width: 700px; border: 1px solid #000000; text-align: center; font-family: 'Arial', 'Calibri';\">\n" +
                            " <tr><th colspan=\"2\"><h1 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: bold; font-size: 20px;\">O(a) cliente "+client.getName()+" cancelou uma corrida</h1></th></tr>\n" +
                            " <tr><td colspan=\"2\"><h2 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: bold; font-size: 15px;\">Nome do passageiro: "+running.getNamePassenger()+"</h2></td><tr>\n" +
                            " <tr style=\"background-color: #000000; font-weight: bold; color:#ffffff\"><td style=\"width: 250px;\">Código: #"+running.getId()+"</td><td>Detalhes</td></tr>\n" +
                            " <tr><td colspan=\"2\" style=\"border: 1px solid #000000;\"><p style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-family: 'Arial', 'Calibri'; font-weight: hormal; font-size: 14px; text-align: justify; padding-top: 5px;\">"+messageDriver+"</p></td><tr>\n" +
                            " </table>");

                emailCancel.setMensagem(text.toString());

                if(emailCancel.sendEmail()){
                    System.out.println("Second email has sent.");
                }else{
                    System.out.println("Second email hasn't sent");
                }

            }else{
                System.out.println("First email hasn't sent");
            }
           
            out.println(true);
           
        }catch(Exception erro){
                throw new RuntimeException(erro);
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
            throws ServletException, IOException, UnsupportedEncodingException {
        try {
            try {
                processRequest(request, response);
            } catch (MPException ex) {
                Logger.getLogger(CancelRunning.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (JSONException ex) {
            Logger.getLogger(CancelRunning.class.getName()).log(Level.SEVERE, null, ex);
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
            throws ServletException, IOException, UnsupportedEncodingException {
        try {
            try {
                processRequest(request, response);
            } catch (MPException ex) {
                Logger.getLogger(CancelRunning.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (JSONException ex) {
            Logger.getLogger(CancelRunning.class.getName()).log(Level.SEVERE, null, ex);
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
