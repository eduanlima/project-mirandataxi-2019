/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Model.PaymentMain;
import Model.Running;
import ModelDAO.PaymentMainDAO;
import Util.LongHour;
import Util.UniversallyUniqueIdentifier;
import com.mercadopago.exceptions.MPException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.codehaus.jettison.json.JSONException;

/**
 *
 * @author GRAFIX
 */
public class TestCancelPayment {
    public static void main(String args[]) throws JSONException, UnsupportedEncodingException, IOException, MPException{        
        PaymentMain paymentMain = new PaymentMain();
        PaymentMain paymentMainAux = new PaymentMain();
        
        PaymentMainDAO paymentMainDAO = new PaymentMainDAO();
        paymentMain.setId("15525215");   
        
        paymentMain = paymentMainDAO.readPayment(paymentMain);
       
        Long dataHour;
        dataHour = new Long("1534633200000");
        
        long  hourRun;
        
        hourRun = LongHour.calculateHour(dataHour);
        
        if(paymentMain.getStatus().equals("approved")){
            paymentMain.setKeyRefund(UniversallyUniqueIdentifier.getUUID());
            
            if(hourRun <= 24){
                paymentMain.refundHalfPayment(paymentMain.getId(), paymentMain.getAmount() / 2);
                //partially_refunded
                
            }else if(hourRun > 24){
                paymentMain.refundPayment(paymentMain.getId());   
            }
        }else{
            paymentMain.cancelPayment(paymentMain.getId());
        }
        
        try{
           paymentMain.readPayment(paymentMain.getId());
           paymentMainDAO.updatePaymentCancel(paymentMain);
           System.out.println("Cancelado");
        }catch(Exception erro){
            throw new RuntimeException(erro);
        }
    }
    
}
