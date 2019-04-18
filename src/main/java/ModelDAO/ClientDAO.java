package ModelDAO;
import Model.Client;
import Model.PaymentMain;
import Model.Phone;
import Model.Running;
import Util.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdk.nashorn.api.scripting.JSObject;
import org.codehaus.jettison.json.JSONObject;

public class ClientDAO {
    private static final String INSERTCLIENT = "INSERT INTO cliente (nome,email,senha,situacao) VALUES (?,?,?,1);";
    private static final String INSERTPHONE = "INSERT INTO telefone VALUES (?,?);";
    private static final String ACCESS = "SELECT id, nome, email FROM cliente WHERE senha = ? AND email = ?";
    private static final String UPTADECLIENT = "UPDATE cliente SET nome = ?, email = ? WHERE id = ?;";
    private static final String READCLIENT = "SELECT nome, email FROM cliente WHERE id = ?;";
    private static final String READPHONE = "SELECT numero FROM telefone WHERE cliente = ?";
    private static final String DELETEPHONE = "DELETE FROM telefone WHERE cliente = ?;";
    private static final String ACCESSUPDATEPASS = "SELECT id, nome FROM cliente WHERE senha = ? AND id = ?";
    private static final String UPDATEPASS = "UPDATE cliente SET senha = ? WHERE id = ?;";
    private static final String READEMAIL = "SELECT id, nome, email FROM cliente WHERE email = ?";
    private static final String READEMAILPAY = "SELECT cliente.nome AS nome, cliente.email AS email FROM cliente, pagamento WHERE pagamento.id = ? and pagamento.cliente = cliente.id;";
    private static final String READCLIENTRUN = "SELECT cliente.nome AS nome, cliente.email AS email FROM cliente, corrida WHERE corrida.id = ? AND corrida.cliente = cliente.id;";
    
    public boolean registerClient(Client client){
        Connection connection = null;
        PreparedStatement query;
        PreparedStatement queryPhone;
        int idClient;
        boolean answer = false;
        
        try{
            connection = ConnectionDB.getConnection();
            connection.setAutoCommit(false);
            
            query = connection.prepareStatement(INSERTCLIENT, PreparedStatement.RETURN_GENERATED_KEYS);
            query.setString(1, client.getName());
            query.setString(2, client.getEmail());
            query.setString(3, client.getPassword());
            query.execute();
            
            ResultSet resultQuery = query.getGeneratedKeys();
            
            if(resultQuery.next()){
                idClient = resultQuery.getInt(1);
                queryPhone = connection.prepareStatement(INSERTPHONE);
                
                List<Phone> phoneAll = client.getPhone();
                 
                for(Phone phone: phoneAll){
                    try{
                        queryPhone.setInt(1, idClient);
                        queryPhone.setString(2, phone.getNumber());
                        queryPhone.execute();
                    }catch(SQLException erro){
                        System.out.println("Ocorreu um erro");
                    }
                };
            }
            
            connection.commit();
            answer = true;
            
        }catch(Exception error){
            throw new RuntimeException(error);
        }finally{
            try{
                connection.close();
            }catch(SQLException error){
                throw new RuntimeException(error);
            }
        }
        return answer;
    }
    
    public boolean updateClient(Client client){
        Connection connection = null;
        PreparedStatement query;
        PreparedStatement queryPhone;
        PreparedStatement queryPhoneDelete;
        
        boolean answer = false;
        
        try{
            connection = ConnectionDB.getConnection();
            connection.setAutoCommit(false);
            
            query = connection.prepareStatement(UPTADECLIENT);
            query.setString(1, client.getName());
            query.setString(2, client.getEmail());
            query.setInt(3, client.getId());
            query.execute();
            
            queryPhoneDelete = connection.prepareStatement(DELETEPHONE);
            queryPhoneDelete.setInt(1,client.getId());
            queryPhoneDelete.execute();
            
            //This part will need to be changed
            if (client.getPhone() != null){
                for (Phone phone: client.getPhone()){
                    queryPhone = connection.prepareStatement(INSERTPHONE);
                    queryPhone.setInt(1, client.getId());
                    queryPhone.setString(2, phone.getNumber());
                    queryPhone.execute();
                }
            }

            connection.commit();
            answer = true;
            
        }catch(Exception error){
            throw new RuntimeException(error);
        }finally{
            try{
                connection.close();
            }catch(SQLException error){
                throw new RuntimeException(error);
            }
        }
        return answer;
    }
    
    public Client readClient(Client client){
        Connection connection = null;
        PreparedStatement query;
        PreparedStatement queryPhone;
        
        try{
            connection = ConnectionDB.getConnection();
            query = connection.prepareStatement(READCLIENT);
            query.setInt(1,client.getId());
            ResultSet resultQuery = query.executeQuery();
            
            while (resultQuery.next()){
                client.setName(resultQuery.getString("nome"));
                client.setEmail(resultQuery.getString("email"));
            }
            
            Phone phone = null;
            List<Phone> listPhone = new ArrayList<>();
            
            queryPhone = connection.prepareStatement(READPHONE);
            queryPhone.setInt(1,client.getId());
            ResultSet resultQueryPhone = queryPhone.executeQuery();
            
            while(resultQueryPhone.next()){
                phone = new Phone(resultQueryPhone.getString("numero"));
                listPhone.add(phone);
            }
            
            client.setPhone(listPhone);
            

        }catch(Exception error){
            throw new RuntimeException(error);
        }finally{
            try{
                connection.close();
            }catch(SQLException error){
                throw new RuntimeException(error);
            }
        }
        
        
        return client;
    }
    
    public Client loginClient(Client client){
        Connection connection = null;
        
        try{
            connection = ConnectionDB.getConnection();
            PreparedStatement query = connection.prepareStatement(ACCESS);
            query.setString(1, client.getPassword());
            query.setString(2, client.getEmail());
            ResultSet result = query.executeQuery();
            
            if(result.next()){
                client.setId(result.getInt("id"));
                client.setName(result.getString("nome"));
                client.setEmail(result.getString("email"));
            }
            
        }catch(Exception error){
            throw new RuntimeException(error);
        }finally{
            try{
                connection.close();
            }catch(SQLException error){
                new RuntimeException(error);
            }
        }
        return client;
    }
    
    public boolean loginClientPass(Client client){
        Connection connection = null;
        boolean answer = false;
        
        try{
            connection = ConnectionDB.getConnection();
            PreparedStatement query = connection.prepareStatement(ACCESSUPDATEPASS);
            query.setString(1, client.getPassword());
            query.setInt(2, client.getId());
            ResultSet result = query.executeQuery();
            
            if(result.next()){
                answer = true;
            }
            
        }catch(Exception error){
            throw new RuntimeException(error);
        }finally{
            try{
                connection.rollback();
                connection.close();
            }catch(SQLException error){
                new RuntimeException(error);
            }
        }
        return answer;
    }
    
   
    public boolean alterPassword(Client client){
        Connection connection = null;
        PreparedStatement query;
        boolean answer = false;
        
        try{
            connection = ConnectionDB.getConnection();
            query = connection.prepareStatement(UPDATEPASS);
            query.setString(1, client.getPassword());
            query.setInt(2, client.getId());
            query.execute();
        
            answer = true;
            
        }catch(Exception error){
            throw new RuntimeException(error);
        }finally{
            try{
                connection.close();
            }catch(SQLException error){
                throw new RuntimeException(error);
            }
        }
        return answer;
    }
    
    public Client readEmailClient(Client client){
        Connection connection = null;
        PreparedStatement query;
        client.setId(0);
        
        try{
            connection = ConnectionDB.getConnection();
            query = connection.prepareStatement(READEMAIL);
            query.setString(1, client.getEmail());
            ResultSet result = query.executeQuery();
            
            if (result.next()){
                client.setId(result.getInt("id"));
                client.setName(result.getString("nome"));
                client.setEmail(result.getString("email"));
            }
        
        }catch(Exception error){
            throw new RuntimeException(error);
        }finally{
            try{
                connection.close();
            }catch(Exception error){
                throw new RuntimeException(error);
            }
        }
        
        return client;
    }
    
    public Client readEmail(Client client, PaymentMain paymentMain){
        Connection connection = null;
        PreparedStatement query;
        
        try{
            connection = ConnectionDB.getConnection();
            query = connection.prepareStatement(READEMAILPAY);
            query.setString(1, paymentMain.getId());
            ResultSet result = query.executeQuery();
            
            while(result.next()){
                client.setName(result.getString("nome"));
                client.setEmail(result.getString("email"));
            }
            
        }catch(Exception error){
            throw new RuntimeException(error);
        }finally{
            try{
                connection.close();
            }catch(Exception error){
                throw new RuntimeException(error);
            }
        }
        return client;
    }
    
    public Client readClientRun(Running running){
        Client client = new Client();
        client.setName("");
        Connection connection = null;
        PreparedStatement query;
        
        try{
            connection = ConnectionDB.getConnection();
            query = connection.prepareStatement(READCLIENTRUN);
            query.setInt(1, running.getId());
            ResultSet result = query.executeQuery();
            
            while(result.next()){
                client.setName(result.getString("nome"));
                client.setEmail(result.getString("email"));
            }
            
        }catch(Exception error){
            throw new RuntimeException(error);
        }finally{
            try{
                connection.close();
            }catch(Exception error){
                throw new RuntimeException(error);
            }
        }        
        return client;
    }
}
