package ModelDAO;

import Model.PaymentMain;
import Model.Running;
import Util.ConnectionDB;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMainDAO {
    private static final String INSERTPAY = "INSERT INTO pagamento VALUES (?,?,?,?,?,?,?,?,?,?);";
    private static final String READALLPAY = "SELECT pagamento.id AS pagamento, pagamento.situacao AS situacao, pagamento.bcartao AS cartao, pagamento.descfatura AS descricao "
                                          + "FROM pagamento, corrida WHERE corrida.id = ? AND pagamento.corrida = corrida.id;";
    private static final String READPAY = "SELECT pagamento.id AS pagamento, pagamento.total AS total, pagamento.situacao AS situacao FROM pagamento WHERE pagamento.id = ?";
    private static final String UPDATEPAY = "UPDATE pagamento SET situacao = ? WHERE id = ?;";
    private static final String UPDATEPAYCANCEL = "UPDATE pagamento SET situacao = ?, iddevolucao = ?, chdevolucao = ?, situacaodetalhe= ? WHERE id = ?;";
    private static final String UPDATERUNNING = "UPDATE corrida, pagamento SET corrida.situacao = false WHERE pagamento.id = ? AND corrida.id = pagamento.corrida;";
    
    public PaymentMain registerPayment(PaymentMain paymentMain){
        Connection connection = null;
        PreparedStatement query;
                
        try{
            connection = ConnectionDB.getConnection();
            query = connection.prepareStatement(INSERTPAY);
            query.setString(1, paymentMain.getId());
            query.setInt(2, paymentMain.getClient().getId());
            query.setInt(3, paymentMain.getRunning().getId());
            query.setString(4, paymentMain.getStatus());
            query.setDouble(5, paymentMain.getAmount());
            query.setString(6, paymentMain.getStatusDetail());
            query.setLong(7, paymentMain.getRefundId());
            query.setString(8, paymentMain.getKeyRefund());
            query.setString(9, paymentMain.getFlagCard());
            query.setString(10, paymentMain.getDescription());
            query.execute();
 
        }catch(Exception error){
            throw new RuntimeException(error);
        }finally{
            try{
                connection.close();
            }catch(SQLException error){
                throw new RuntimeException(error);
            }
        }
        return paymentMain;
    }
    
    public PaymentMain readPayment(Running running){
        PaymentMain payment = new PaymentMain();
        Connection connection = null;
        PreparedStatement query;
        
        try{
            connection = ConnectionDB.getConnection();
            query = connection.prepareStatement(READALLPAY);
            query.setInt(1, running.getId());
            ResultSet queryResult = query.executeQuery();
            
            while(queryResult.next()){
                payment.setId(queryResult.getString("pagamento"));
                payment.setStatus(queryResult.getString("situacao"));
                payment.setFlagCard(queryResult.getString("cartao"));
                payment.setDescription(queryResult.getString("descricao"));
            }
        
        }catch(Exception erro){
            throw new RuntimeException(erro);
        }finally{
            try{
                connection.close();
            }catch(SQLException erro){
                throw new RuntimeException(erro);
            }
        }
        return payment;
    } 
    
    public PaymentMain readPayment(PaymentMain paymentMain){
        Connection connection = null;
        PreparedStatement query;
        
        try{
            connection = ConnectionDB.getConnection();
            query = connection.prepareStatement(READPAY);
            query.setString(1, paymentMain.getId());
            ResultSet queryResult = query.executeQuery();
            
            while(queryResult.next()){
                paymentMain.setId(queryResult.getString("pagamento"));
                paymentMain.setAmount(queryResult.getFloat("total"));
                paymentMain.setStatus(queryResult.getString("situacao"));
            }
        
        }catch(Exception erro){
            throw new RuntimeException(erro);
        }finally{
            try{
                connection.close();
            }catch(SQLException erro){
                throw new RuntimeException(erro);
            }
        }
        return paymentMain;
    }
    
    public PaymentMain updatePayment(PaymentMain paymentMain){
        Connection connection = null;
        PreparedStatement query;
        
        try{
            connection = ConnectionDB.getConnection();
            query = connection.prepareStatement(UPDATEPAY);
            query.setString(1, paymentMain.getStatus());
            query.setString(2, paymentMain.getId());
            query.execute();
            
        }catch(Exception erro){
            throw new RuntimeException(erro);
        }finally{
            try{
                connection.close();
            }catch(SQLException erro){
                throw new RuntimeException(erro);
            }
        }     
        return paymentMain;
    }
    
    public PaymentMain updatePaymentCancel(PaymentMain paymentMain){
        Connection connection = null;
        PreparedStatement query;
        
        
        try{
            connection = ConnectionDB.getConnection();
            query = connection.prepareStatement(UPDATEPAYCANCEL);
            query.setString(1, paymentMain.getStatus());
            query.setLong(2, paymentMain.getRefundId());
            query.setString(3, paymentMain.getKeyRefund());
            query.setString(4, paymentMain.getStatusDetail());
            query.setString(5, paymentMain.getId());
            query.execute();
            
            query = connection.prepareStatement(UPDATERUNNING);
            query.setString(1, paymentMain.getId());
            query.execute();
            
        }catch(Exception erro){
            throw new RuntimeException(erro);
        }finally{
            try{
                connection.close();
            }catch(SQLException erro){
                throw new RuntimeException(erro);
            }
        }     
        return paymentMain;
    }
}
