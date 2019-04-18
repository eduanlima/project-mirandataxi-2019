package Test;

import Model.Client;
import Model.PaymentMain;
import Model.Running;
import ModelDAO.PaymentMainDAO;

public class TestRegisterPayment {
    public static void main(String args[]){
        Client client = new Client();
        Running running = new Running();
        PaymentMain payment = new PaymentMain();
        
        payment.setId("456744486");
        payment.setKeyRefund("NS");
        
        client.setId(1);
        payment.setClient(client);
        
        running.setId(1);
        payment.setRunning(running);
        
        PaymentMainDAO paymentDAO = new PaymentMainDAO();
        
        try{
            payment = paymentDAO.registerPayment(payment);
            System.out.println("Registrado: " + payment.getId());
            
        }catch(Exception error){
            System.out.println("Não resgistrado: "+error);
        }
        
    }
}
