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
        
        Running running = new Running();
        running.setId(41);
        
        Client client = new Client();
        ClientDAO clientDAO = new ClientDAO();
        
        client = clientDAO.readClientRun(running);
        
        Email emailTest = new Email();
        emailTest.setDestinatary(client.getEmail());

        emailTest.setSubject("Serviço concluído (Não responda este e-mail).");
        
        StringBuilder text = new StringBuilder();
        text.append("<div style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; position: absolute; width: 600px; height: 600px; background-image: url('http://mirandataxi.com.br/finish-service.jpg');\">\n" +
                    "<table style=\"margin: 0; padding: 0; border: 0; font-size: 100%; font: inherit; vertical-align: baseline; border-collapse: collapse; border-spacing: 0; width: 600px; font-family: 'Arial', 'Calibri'; display: block; border-collapse: collapse;\">\n" +
                    "<tr><th colspan=\"2\" style= \"width: 600px; text-align: left;\"><img style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; display: inline-block; width: 50px; float: left; margin-left: 170px; padding-top: 180px;\" src=\"http://mirandataxi.com.br/icon-approved.jpg\"/>\n" +
                    "<h1 style=\"margin: 0;padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: bold; font-size: 25px; padding-top: 190px;\">Serviço concluído</h1>\n" +
                    "<h1 style=\"margin: 0;padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: bold; font-size: 14px; padding-top: 1px; text-align: center;\">Código: #"+running.getId()+"</h1></th></tr>\n" +
                    "<tr><td colspan=\"2\" style= \"width: 600px; text-align: center;\"><p style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: normal; padding-top: 8px; font-size: 14px; color: #000000;\">Obrigado por contratar nossos serviços, "+client.getName()+".\n" +
                    "<br>Estaremos sempre a sua disposição, até mais.</p>\n" +
                    "<tr><td colspan=\"2\" style= \"width: 600px; text-align: center;\">\n" +
                    "<h1 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: bold; font-size: 20px; color: #ffffff; padding-top: 218px;\">CONTATOS</h1></td></tr>\n" +
                    "<tr><td style=\"text-align: right; width: 48%; padding-right: 2%;\"><a href=\"https://api.whatsapp.com/send?phone=5511998965109\" style=\"float: right; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: regular; font-size: 85%; color: #ffffff;\">(11) 99896-5109</a>\n" +
                    "<img src=\"http://www.mirandataxi.com.br/icon-whats-email.png\" style=\"margin-right: 3px; display: inline-block; float: right; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; position: relative; width: 16px; height: 16px;\"></img> <br> \n" +
                    "<h2 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: regular; font-size: 12px; color: #ffffff;\">contato@mirandataxi.com</h2></td>\n" +
                    "<td style=\"border-left: 1px solid white; text-align: left; padding-left: 2%;\"><h3 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: regular; font-size: 8px; float: left; color: #ffffff;\">SIGA-NOS NAS <br> REDES SOCIAIS</h3>\n" +
                    "<a href=\"https://www.facebook.com/\" style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; display: inline-block; float: left; width: 20px; height: 20px; margin-left: 5px; background-image: url('http://www.mirandataxi.com.br/icon-face-email.png'); background-repeat: no-repeat;\"></a>\n" +
                    "<a href=\"https://www.instagram.com/?hl=pt-br\" style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; display: inline-block; float: left; margin-left: 5px; width: 20px; height: 20px; background-image: url('http://www.mirandataxi.com.br/icon-inst-email.png'); background-repeat: no-repeat;\"></a><br>\n" +
                    "<h4 style=\"margin: 0; padding: 0; border: 0; font-size: 0px; font: inherit; vertical-align: baseline; font-weight: regular; font-size: 7px; margin-top: 4px; color: #ffffff;\">© MirandaTaxi. All rights reserved.</h4></td></tr>\n" +
                    "</table></div>");
        
        emailTest.setMensagem(text.toString());
        
        if(emailTest.sendEmail()){
            System.out.println("The email has sent!");
        }else{
            System.out.println("The email hasn't sent!");
        }
    }
}
