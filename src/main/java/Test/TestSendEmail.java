/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;
import Model.Client;
import Model.Running;
import ModelDAO.ClientDAO;
import Util.Email;

/**
 *
 * @author eduan
 */
public class TestSendEmail {
    public static void main(String args[]){
        
        Email emailTest = new Email();
        emailTest.setDestinatary("eduanlimaphone@gmail.com");

        emailTest.setSubject("Serviço concluído (Não responda este e-mail).");
        
        StringBuilder text = new StringBuilder();
        text.append("<div style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; position: relative; width: 600px; height: 750px; background-image: url('https://mirandataxi.com.br/img/background/conf-compra.jpg');\">"
                            + "<div style=\" margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline;position: relative; width: 100%; height: 140px; padding-top: 290px; text-align: center; font-family: 'Arial', 'Calibri'; color: #000000; display: block;\">"
                            + "<h1 style=\"margin: 0;padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: bold; font-size: 30px;\">Olá, Eduan</h1>"
                            + "<p style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: normal; font-size: 18px; margin-top: 5px;\">Obrigado por contratar nossos serviços.<br><a href='https://www.mirandataxi.com.br/'>CLIQUE AQUI</a> para visualizar o status do serviço contratado.</p> "
                            + "<h2 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline;font-weight: bold; font-size: 22px;\">Código do serviço: ghhjsdhj</h2> </div>"
                            + "<div style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; position: relative; margin-top: 20px; width: 100%; height: 100px; display: block; color: #000000; \"> "
                            + "<p style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-family: 'Arial', 'Calibri'; font-weight: hormal; font-size: 14px; text-align: justify; width: 500px; padding-top: 15px; padding-left: 50px;\">A partir de agora você poderá consultar o status do pagamento a qualquer momento acessando o menu “minhas corridas” "
                            + "no site www.mirandataxi.com.br. Para informações sobre o serviço (pagamento, cancelamento ou reembolso), entre em contato conosco através do site www.mirandataxi.com.br, ou nos envie uma mensagem via Whatsapp para (11) 99896-5109.</p></div>"
                            + "<table style=\"margin: 0; padding: 0; border: 0; font-size: 100%; font: inherit; vertical-align: baseline; border-collapse: collapse; border-spacing: 0; width: 200px;  margin-top: 30px; margin-left: 20px; text-align: right; font-family: 'Arial', 'Calibri'; color: #ffffff; background-color: #000000; display: block;\">"
                            + "<tr><th colspan=\"2\"><h1 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; margin-top: 10px; font-weight: bold; font-size: 30px;\">CONTATOS</h1></th></tr>"
                            + "<tr><td colspan=\"2\"><a href=\"https://api.whatsapp.com/send?phone=5511998965109\" style=\"float: right; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: regular; font-size: 85%; color: #ffffff;\">(11) 99896-5109</a><img src=\"https://mirandataxi.com.br/img/icon/icon-whats-email.png\" style=\"margin-right: 3px; display: inline-block; float: right; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; position: relative; width: 16px; height: 16px;\"></img></td></tr>"
                            + "<tr><td colspan=\"2\"><h2 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: regular; font-size: 12px;\">contato@mirandataxi.com.br</h2></td></tr>"
                            + "<tr><td colspan=\"2\"><h3 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: regular; font-size: 10px;\">SIGA-NOS NA REDES SOCIAIS</h3></td></tr>"
                            + "<tr><td colspan=\"2\"><a href=\"https://www.instagram.com/?hl=pt-br\" style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; display: inline-block; float: right; margin-left: 8px; width: 20px; height: 20px; background-image: url('https://mirandataxi.com.br/img/icon/icon-inst-email.png'); background-repeat: no-repeat;\"></a>"
                            + "<a href=\"https://www.facebook.com/\" style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; display: inline-block; float: right; width: 20px; height: 20px; background-image: url('https://mirandataxi.com.br/img/icon/icon-face-email.png'); background-repeat: no-repeat;\"></a></td></tr>"
                            + "<tr><td colspan=\"2\"><h4 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: regular; font-size: 9px;\">© MirandaTaxi. All rights reserved.</h4></td></tr>"
                            + "</table></div>");
        
        emailTest.setMensagem(text.toString());
        
        if(emailTest.sendEmail()){
            System.out.println("The email has sent!");
        }else{
            System.out.println("The email hasn't sent!");
        }
    }
}
