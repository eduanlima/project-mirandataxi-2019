/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.AdditionalService;
import Model.ClassCar;
import Model.Client;
import Model.ItemService;
import Model.PaymentMain;
import Model.Running;
import ModelDAO.ItemServiceDAO;
import ModelDAO.PaymentMainDAO;
import ModelDAO.RunningDAO;
import Util.Email;
import Util.LongHour;
import Util.UniversallyUniqueIdentifier;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author GRAFIX
 */
@WebServlet(name = "CreatePayment", urlPatterns = {"/CreatePayment"})
public class CreatePayment extends HttpServlet {

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
            throws ServletException, IOException, JSONException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        String valuesRunning = session.getAttribute("valuesRunning").toString();
        String  valuesClass = session.getAttribute("valuesClass").toString();
        String name = session.getAttribute("userName").toString();
        String email = session.getAttribute("userEmail").toString();
        float amount = Float.parseFloat(session.getAttribute("total").toString());
        String token = request.getParameter("token");
        String flagCard = request.getParameter("paymentMethodId");
        String[] infoPay = new String[6];
        
        PaymentMain paymentMain = new PaymentMain();
        
        paymentMain.createPayment(amount, token, flagCard, email);
        
        int confPay = 2;
        String causeRej = "";
                
        if(paymentMain.getStatus().equals("approved")){
            confPay = 1;
            infoPay[0] = "Obrigado!";
            infoPay[1] = "Sua compra foi realizada com sucesso!";
            infoPay[2] = "Transação aprovada.";
            infoPay[3] = flagCard;
            infoPay[4] = paymentMain.getDescription();
            infoPay[5] = "icon-approved";
            
        }else if((paymentMain.getStatus().equals("in_process")) || (paymentMain.getStatus().equals("pending"))){
            confPay = 1;
            infoPay[0] = "Obrigado!";
            infoPay[1] = "Sua compra foi realizada com sucesso!";
            infoPay[2] = "Transação em processamento.";
            infoPay[3] = flagCard;
            infoPay[4] = paymentMain.getDescription();
            infoPay[5] = "icon-approved";
            
        }else if(paymentMain.getStatus().equals("rejected")){
            confPay = 0; 
            
            try{
                causeRej = paymentMain.getStatusDetail();
                
                if(causeRej.equals("cc_rejected_insufficient_amount")){
                causeRej = "Saldo insuficiente.";

                }else if(causeRej.equals("cc_rejected_bad_filled_card_number")){
                    causeRej = "Verifique o número do cartão.";

                }else if(causeRej.equals("cc_rejected_bad_filled_security_code")){
                    causeRej = "Verifique o código de segurança do cartão.";

                }else if(causeRej.equals("cc_rejected_bad_filled_date")){
                    causeRej = "Verifique a data de expiração.";

                }else if(causeRej.equals("cc_rejected_call_for_authorize")){
                    causeRej = "Você deve autorizar que seu cartão (payment_method_id) seja usado para pagamentos no MercadoPago.";

                }else if(causeRej.equals("cc_rejected_card_disabled")){
                    causeRej = "Entre em contato com a operadora (payment_method_id) para ativar seu cartão.";

                }else if(causeRej.equals("cc_rejected_blacklist")){
                    causeRej = "Não conseguimos precessar a transaçãp.";

                }else if(causeRej.equals("cc_rejected_bad_filled_other")){
                    causeRej = "Verifique os dados de seu cartão.";
                }
                
            }catch(NullPointerException error){
                    causeRej = "Ocorreu um erro em nossos servidores.";
                    infoPay[0] = "Não aprovada";
                    infoPay[1] = causeRej;
                    infoPay[2] = "Transação não aprovada.";
                    infoPay[3] = flagCard;
                    infoPay[4] = "MirandaTaxi";
                    infoPay[5] = "icon-rejected";

                    try(PrintWriter out = response.getWriter()){    

                    out.println("<!DOCTYPE html> \n" +
                                "<html> \n" +
                                "	<head> \n" +
                                "		<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> \n" +
                                "		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n" +
                                "		<title>Tela de pagamento</title> \n" +
                                "		<link rel=\"stylesheet\" href=\"style/fonts.css\"/> \n" +
                                "		<link rel=\"stylesheet\" href=\"style/style-payment.css\"/> \n" +
                                "	</head> \n" +
                                "	<body> \n" +
                                "		<div class=\"main\">\n" +
                                "			<img class=\"img-back\" src=\"img/icons/back.jpeg\"/>\n" +
                                "			<div class=\"main-elements\">\n" +
                                "				<img class=\"img-desc\" src=\"img/icons/"+infoPay[5]+".png\" alt=\"Aprovado\" class=\"icon\"/>\n" +
                                "				<span class=\"main-msg\">"+infoPay[0]+"</span><!--MENSAGEM DE RETORNO--> \n" +
                                "				<span class=\"sub-msg\">"+infoPay[1]+"</span><!--MENSAGEM DE APOIO-->\n" +
                                "				<span class=\"sub-msg-min\">"+infoPay[2]+"<br> Cartão: "+infoPay[3]+"<br>"+infoPay[4]+"</span><!--MENSAGEM DE APOIO-->\n" +
                                "				<!--<button class=\"btn-confirm\" id=\"btn-approved\">CLIQUE AQUI</button><br/><br/>--> \n" +
                                "				<a href=\"dashboard.jsp#races\" class=\"btn btn-confirm\" id=\"btn-approved\">CLIQUE AQUI</a>\n" +
                                "			</div>\n" +
                                "		<div> \n" +
                                "	</body> \n" +
                                "</html>");
                    }
                throw new RuntimeException("Error: "+error);
            }

        }else if(paymentMain.getStatus().equals("null")){
            confPay = 0;
            causeRej = "Falha no servidor de pagamento. Tente mais tarde.";
            
            infoPay[0] = "Erro.";
            infoPay[1] = causeRej;
            infoPay[2] = "Transação não realizada.";
            infoPay[3] = flagCard;
            infoPay[4] = "MirandaTaxi";
            infoPay[5] = "icon-rejected";
        }
        
        if(confPay == 1){
            JSONObject jsonRunning = new JSONObject(valuesRunning);
            JSONObject jsonClass = new JSONObject(valuesClass);

            Client client = new Client();
            client.setId(jsonRunning.getInt("idClient"));

            ClassCar classCar = new ClassCar();
            classCar.setId(jsonClass.getString("class"));

            Running running = new Running();
            running.setClient(client);
            running.setClassCar(classCar);
            running.setNamePassenger(jsonRunning.getString("passenger"));
            running.setHour(jsonRunning.getString("hour"));
            running.setData(jsonRunning.getString("data"));
            running.setNumFlight(jsonRunning.getString("numFlight"));
            running.setHourCont(BigDecimal.valueOf(jsonClass.getDouble("hrcont")));
            running.setKmCont(BigDecimal.valueOf(jsonClass.getDouble("kmcont")));
            running.setOrigin(jsonRunning.getString("origin"));
            running.setDestination(jsonRunning.getString("destination"));
            running.setValueAddKm(BigDecimal.valueOf(jsonClass.getDouble("kmadd")));
            running.setTotalKmAdd(BigDecimal.valueOf(jsonClass.getDouble("kmaddtotal")));
            running.setTotalPartial(BigDecimal.valueOf(jsonClass.getDouble("partinal")));
            running.setTotal(BigDecimal.valueOf(amount).setScale(2,RoundingMode.FLOOR));
            running.setStatus(1);

            String dataHour = jsonRunning.getString("data")+" "+jsonRunning.getString("hour")+":00";
            running.setDataHour(LongHour.getDataHourLong(dataHour));

            RunningDAO runningDAO = new RunningDAO();

            try{
                running = runningDAO.registerRunning(running);

                AdditionalService addService = new AdditionalService();

                ItemService itemServico = new ItemService();
                itemServico.setRunning(running);

                ItemServiceDAO itemServicoDAO = new ItemServiceDAO();
                // AdditionalServiceDAO addServiceDAO = new AdditionalServiceDAO();

                if(jsonRunning.getInt("babyChair") == 1){
                    addService.setId(jsonRunning.getInt("babyChair"));
                    itemServico.setAddService(addService);

                    itemServicoDAO.registerItemService(itemServico);
                }

                if(jsonRunning.getInt("wedding") == 2){
                    addService.setId(jsonRunning.getInt("wedding"));
                    itemServico.setAddService(addService);
                    itemServico.setDetails(jsonRunning.getString("nameWedding"));

                    itemServicoDAO.registerItemService(itemServico);
                }

                if(jsonRunning.getInt("reception") == 3){
                    addService.setId(jsonRunning.getInt("reception"));
                    itemServico.setAddService(addService);
                    itemServico.setDetails(jsonRunning.getString("nameReception"));

                    itemServicoDAO.registerItemService(itemServico);
                } 

            }catch(Exception error){
                throw new RuntimeException("Erro: servico: "+error);
            }

            try{
                paymentMain.setKeyRefund("NS");
                paymentMain.setAmount(amount);
                paymentMain.setClient(client);
                

                paymentMain.setRunning(running);

                PaymentMainDAO paymentMainDAO = new PaymentMainDAO();

                paymentMainDAO.registerPayment(paymentMain);
                
                //Envio do e-mail para cliente e Miranda Taxi
                Email emailRunning = new Email();
                emailRunning.setDestinatary(email);
                emailRunning.setSubject("Confirmação (Não responda este e-mail).");

                StringBuilder text = new StringBuilder();
                text.append("<div style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; position: relative; width: 600px; height: 750px; background-image: url('https://mirandataxi.com.br/img/background/conf-compra.jpg');\">"
                            + "<div style=\" margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline;position: relative; width: 100%; height: 140px; padding-top: 290px; text-align: center; font-family: 'Arial', 'Calibri'; color: #000000; display: block;\">"
                            + "<h1 style=\"margin: 0;padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: bold; font-size: 30px;\">Olá, "+name+"</h1>"
                            + "<p style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: normal; font-size: 18px; margin-top: 5px;\">Obrigado por contratar nossos serviços.<br><a href='https://www.mirandataxi.com.br/'>CLIQUE AQUI</a> para visualizar o status do serviço contratado.</p> "
                            + "<h2 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline;font-weight: bold; font-size: 22px;\">Código do serviço: "+running.getId()+"</h2> </div>"
                            + "<div style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; position: relative; margin-top: 20px; width: 100%; height: 100px; display: block; color: #000000; \"> "
                            + "<p style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-family: 'Arial', 'Calibri'; font-weight: hormal; font-size: 14px; text-align: justify; width: 500px; padding-top: 15px; padding-left: 50px;\">A partir de agora você poderá consultar o status do pagamento a qualquer momento acessando o menu “minhas corridas” "
                            + "no site www.mirandataxi.com.br. Para informações sobre o serviço (pagamento, cancelamento ou reembolso), entre em contato conosco através do site www.mirandataxi.com.br, ou nos envie uma mensagem via Whatasapp para (11) 99896-5109.</p></div>"
                            + "<table style=\"margin: 0; padding: 0; border: 0; font-size: 100%; font: inherit; vertical-align: baseline; border-collapse: collapse; border-spacing: 0; width: 200px;  margin-top: 30px; margin-left: 20px; text-align: right; font-family: 'Arial', 'Calibri'; color: #ffffff; display: block;\">"
                            + "<tr><th colspan=\"2\"><h1 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; margin-top: 10px; font-weight: bold; font-size: 30px;\">CONTATOS</h1></th></tr>"
                            + "<tr><td colspan=\"2\"><a href=\"https://api.whatsapp.com/send?phone=5511998965109\" style=\"float: right; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: regular; font-size: 85%; color: #ffffff;\">(11) 99896-5109</a><img src=\"https://mirandataxi.com.br/img/icon/icon-whats-email.png\" style=\"margin-right: 3px; display: inline-block; float: right; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; position: relative; width: 16px; height: 16px;\"></img></td></tr>"
                            + "<tr><td colspan=\"2\"><h2 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: regular; font-size: 12px;\">contato@mirandataxi.com.br</h2></td></tr>"
                            + "<tr><td colspan=\"2\"><h3 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: regular; font-size: 10px;\">SIGA-NOS NA REDES SOCIAIS</h3></td></tr>"
                            + "<tr><td colspan=\"2\"><a href=\"https://www.instagram.com/?hl=pt-br\" style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; display: inline-block; float: right; margin-left: 8px; width: 20px; height: 20px; background-image: url('https://mirandataxi.com.br/img/icon/icon-inst-email.png'); background-repeat: no-repeat;\"></a>"
                            + "<a href=\"https://www.facebook.com/\" style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; display: inline-block; float: right; width: 20px; height: 20px; background-image: url('https://mirandataxi.com.br/img/icon/icon-face-email.png'); background-repeat: no-repeat;\"></a></td></tr>"
                            + "<tr><td colspan=\"2\"><h4 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: regular; font-size: 9px;\">© MirandaTaxi. All rights reserved.</h4></td></tr>"
                            + "</table></div>");

                emailRunning.setMensagem(text.toString());
        
                if(emailRunning.sendEmail()){
                    System.out.println("First email has sent.");
                    
                    //Envio de e-mail para Miranda Taxi
                    emailRunning.setDestinatary("contato@mirandataxi.com.br");
                    emailRunning.setSubject("Agendamento");
                    
                    text = new StringBuilder();
                    text.append("<table style=\"margin: 0; padding: 0; border: 0; font-size: 100%; font: inherit; vertical-align: baseline; border-collapse: collapse; border-spacing: 0; width: 700px; border: 1px solid #000000; text-align: center; font-family: 'Arial', 'Calibri';\">\n" +
                                "<tr><th colspan=\"2\"><h1 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: bold; font-size: 20px;\">O(a) cliente "+name+" contratou uma corrida</h1></th></tr>\n" +
                                " <tr><td colspan=\"2\"><h2 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: bold; font-size: 15px;\">Agendamento: "+running.getData()+" Hora: "+running.getHour()+"</h2></td><tr>\n" +
                                "<tr style=\"background-color: #000000; font-weight: bold; color:#ffffff\"><td style=\"width: 250px;\">Código: "+running.getId()+"</td><td>Detalhes</td></tr>\n" +
                                "<tr><td colspan=\"2\" style=\"border: 1px solid #000000;\">"+
                                "<p style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-family: 'Arial', 'Calibri'; font-weight: hormal; font-size: 14px; text-align: justify; padding-top: 5px;\">"+
                                "Passageiro: "+running.getNamePassenger()+"<br>Origem: "+running.getOrigin()+"<br> Destino: "+running.getDestination()+"<br> Vôo: "+running.getNumFlight()+"   |   Horas contratadas: "+running.getHourCont()+ "   |   Km contratado: "+running.getKmCont()+"   |   Km add.: "+running.getValueAddKm()+
                                "<br>Total km add. (R$): "+running.getTotalKmAdd()+"   |   Total serviço (R$): "+running.getTotal()+
                                "</p></td><tr></table>");
                    
                    emailRunning.setMensagem(text.toString());
                    
                    if(emailRunning.sendEmail()){
                        System.out.println("Second email has sent.");
                    }else{
                        System.out.println("Second email hasn't sent");
                    }
                    
                }else{
                    System.out.println("First email hasn't sent");
                }
            }catch(Exception error){
                 throw new RuntimeException("Erro: pagamento: "+error);
            }
        }
        
        try(PrintWriter out = response.getWriter()){    

                out.println("<!DOCTYPE html> \n" +
                            "<html> \n" +
                            "	<head> \n" +
                            "		<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> \n" +
                            "		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n" +
                            "		<title>Tela de pagamento</title> \n" +
                            "		<link rel=\"stylesheet\" href=\"style/fonts.css\"/> \n" +
                            "		<link rel=\"stylesheet\" href=\"style/style-payment.css\"/> \n" +
                            "	</head> \n" +
                            "	<body> \n" +
                            "		<div class=\"main\">\n" +
                            "			<img class=\"img-back\" src=\"img/icons/back.jpeg\"/>\n" +
                            "			<div class=\"main-elements\">\n" +
                            "				<img class=\"img-desc\" src=\"img/icons/"+infoPay[5]+".png\" alt=\"Aprovado\" class=\"icon\"/>\n" +
                            "				<span class=\"main-msg\">"+infoPay[0]+"</span><!--MENSAGEM DE RETORNO--> \n" +
                            "				<span class=\"sub-msg\">"+infoPay[1]+"</span><!--MENSAGEM DE APOIO-->\n" +
                            "				<span class=\"sub-msg-min\">"+infoPay[2]+"<br> Cartão: "+infoPay[3]+"<br>"+infoPay[4]+"</span><!--MENSAGEM DE APOIO-->\n" +
                            "				<!--<button class=\"btn-confirm\" id=\"btn-approved\">CLIQUE AQUI</button><br/><br/>--> \n" +
                            "				<a href=\"dashboard.jsp#races\" class=\"btn btn-confirm\" id=\"btn-approved\">CLIQUE AQUI</a>\n" +
                            "			</div>\n" +
                            "		<div> \n" +
                            "	</body> \n" +
                            "</html>");

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
        } catch (Exception ex) {
            Logger.getLogger(CreatePayment.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception ex) {
            Logger.getLogger(CreatePayment.class.getName()).log(Level.SEVERE, null, ex);
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
