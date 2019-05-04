package Util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
    private String destinatary;
    private String subject;
    private String message;
    
    public String getDestinatary() {
        return destinatary;
    }

    public void setDestinatary(String destinatary) {
        this.destinatary = destinatary;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMensagem(String message) {
        this.message = message;
    }

    public boolean sendEmail(){
        boolean answer = false;
        
        Properties props = new Properties();
        props.put("mail.smtp.host","smtp.mirandataxi.com.br");
        props.put("mail.smtp.socketFactory.port","587");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.auth","true");
        
        Session s = Session.getInstance(props,
                new javax.mail.Authenticator(){
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {

                        return new javax.mail.PasswordAuthentication("equipemirandataxi@mirandataxi.com.br","resposta5896"); 
                    }
                });
        
        try{
            MimeMessage message = new MimeMessage(s);
            message.setFrom(new InternetAddress("equipemirandataxi@mirandataxi.com.br"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.destinatary));
            message.setSubject(this.subject);
            message.setContent(this.message,"text/html; charset=utf-8");
            
            Transport.send(message);
            answer = true;
            
        }catch(MessagingException e){
            e.printStackTrace();
        }
        return answer;
    }
}
