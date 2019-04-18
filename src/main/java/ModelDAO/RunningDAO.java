package ModelDAO;

import Model.ClassCar;
import Model.Client;
import Model.PaymentMain;
import Model.Running;
import Util.ConnectionDB;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RunningDAO {
    private static final String INSERTRUNNING = "INSERT INTO corrida (cliente, classe, nomepassageiro, datahora, hora, datac, numerovoo, horacontratada, "
                                                + "kmcontratado, origem, destino, kmadicional, totalkmadicional, totalparcial, total, situacao)\n" 
                                                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    
    private static final String READRUNNING = "SELECT cliente.id AS cliente, corrida.id AS corrida, classe.id AS classe, corrida.nomepassageiro AS passageiro, corrida.situacao AS sitcorrida, "
                                              + "corrida.datahora AS datahora, corrida.datac AS datac, corrida.hora AS hora, corrida.numerovoo AS numvoo, "
                                              + "corrida.origem AS origem, corrida.destino AS destino, corrida.horacontratada AS horacont, "
                                              + "corrida.kmcontratado AS kmcont, corrida.kmadicional AS kmadd, corrida.totalkmadicional AS kmaddtot, "
                                              + "corrida.totalparcial AS subtotal, corrida.total AS total FROM cliente, corrida, classe "
                                              + "WHERE cliente.id = ? AND corrida.cliente = cliente.id AND corrida.classe = classe.id ORDER BY corrida.id DESC";
    
    private static final String READRUNNINGID = "SELECT cliente.id AS cliente, cliente.nome AS nome, corrida.id AS corrida, classe.id AS classe, corrida.nomepassageiro AS passageiro, corrida.situacao AS sitcorrida, "
                                                + "corrida.datahora AS datahora, corrida.datac AS datac, corrida.hora AS hora, corrida.numerovoo AS numvoo, corrida.origem AS origem, corrida.destino AS destino,"
                                                + " corrida.horacontratada AS horacont, corrida.kmcontratado AS kmcont, corrida.kmadicional AS kmadd, corrida.totalkmadicional AS kmaddtot, corrida.totalparcial AS subtotal,"
                                                + " corrida.total AS total FROM cliente, corrida, classe WHERE corrida.id =  ? AND corrida.cliente = cliente.id AND corrida.classe = classe.id ORDER BY corrida.id DESC";
    
    private static final String READRUNNINGPASSENGER = "SELECT cliente.id AS cliente, cliente.nome AS nome, corrida.id AS corrida, classe.id AS classe, corrida.nomepassageiro AS passageiro, corrida.situacao AS sitcorrida, "
                                                       + "corrida.datahora AS datahora, corrida.datac AS datac, corrida.hora AS hora, corrida.numerovoo AS numvoo, corrida.origem AS origem, corrida.destino AS destino, "
                                                       + "corrida.horacontratada AS horacont, corrida.kmcontratado AS kmcont, corrida.kmadicional AS kmadd, corrida.totalkmadicional AS kmaddtot, corrida.totalparcial AS subtotal, "
                                                       + "corrida.total AS total FROM cliente, corrida, classe WHERE corrida.nomepassageiro = ? AND corrida.cliente = cliente.id AND corrida.classe = classe.id ORDER BY corrida.id DESC";
    
    private static final String READRUNNINGPASSENGERCLASS = "SELECT cliente.id AS cliente, cliente.nome AS nome, corrida.id AS corrida, classe.id AS classe, corrida.nomepassageiro AS passageiro, corrida.situacao AS sitcorrida, corrida.datahora AS datahora, "
                                                            + "corrida.datac AS datac, corrida.hora AS hora, corrida.numerovoo AS numvoo, corrida.origem AS origem, corrida.destino AS destino, corrida.horacontratada AS horacont, corrida.kmcontratado AS kmcont, "
                                                            + "corrida.kmadicional AS kmadd, corrida.totalkmadicional AS kmaddtot, corrida.totalparcial AS subtotal, corrida.total AS total FROM cliente, corrida, classe " 
                                                            + "WHERE corrida.nomepassageiro = ? AND corrida.classe = ? AND corrida.cliente = cliente.id AND corrida.classe = classe.id ORDER BY corrida.id DESC";
    
    private static final String READRUNNINGCLIENT = "SELECT cliente.id AS cliente, cliente.nome AS nome, corrida.id AS corrida, classe.id AS classe, corrida.nomepassageiro AS passageiro, corrida.situacao AS sitcorrida, corrida.datahora AS datahora, corrida.datac AS datac, "
                                                    + "corrida.hora AS hora, corrida.numerovoo AS numvoo, corrida.origem AS origem, corrida.destino AS destino, corrida.horacontratada AS horacont, corrida.kmcontratado AS kmcont, corrida.kmadicional AS kmadd, "
                                                    + "corrida.totalkmadicional AS kmaddtot, corrida.totalparcial AS subtotal, corrida.total AS total FROM cliente, corrida, classe "
                                                    + "WHERE cliente.nome = ? AND corrida.cliente = cliente.id AND corrida.classe = classe.id ORDER BY corrida.id DESC";
    
    private static final String READRUNNINGCLIENTCLASS = "SELECT cliente.id AS cliente, cliente.nome AS nome, corrida.id AS corrida, classe.id AS classe, corrida.nomepassageiro AS passageiro, corrida.situacao AS sitcorrida, corrida.datahora AS datahora, "
                                                       + "corrida.datac AS datac, corrida.hora AS hora, corrida.numerovoo AS numvoo, corrida.origem AS origem, corrida.destino AS destino, corrida.horacontratada AS horacont, corrida.kmcontratado AS kmcont, "
                                                       + "corrida.kmadicional AS kmadd, corrida.totalkmadicional AS kmaddtot, corrida.totalparcial AS subtotal, corrida.total AS total FROM cliente, corrida, classe " 
                                                       + "WHERE cliente.nome = ? AND corrida.classe = ? AND corrida.cliente = cliente.id AND corrida.classe = classe.id ORDER BY corrida.id DESC";
    
    private static final String READRUNNINGDATE = "SELECT cliente.id AS cliente, cliente.nome AS nome, corrida.id AS corrida, classe.id AS classe, corrida.nomepassageiro AS passageiro, corrida.situacao AS sitcorrida, "
                                                   + "corrida.datahora AS datahora, corrida.datac AS datac, corrida.hora AS hora, corrida.numerovoo AS numvoo, corrida.origem AS origem, corrida.destino AS destino, "
                                                   + "corrida.horacontratada AS horacont, corrida.kmcontratado AS kmcont, corrida.kmadicional AS kmadd, corrida.totalkmadicional AS kmaddtot, corrida.totalparcial AS subtotal, "
                                                   + "corrida.total AS total FROM cliente, corrida, classe WHERE corrida.datac = ? AND corrida.cliente = cliente.id AND corrida.classe = classe.id ORDER BY corrida.id DESC";
    
    private static final String READRUNNINGDATECLASS = "SELECT cliente.id AS cliente, cliente.nome AS nome, corrida.id AS corrida, classe.id AS classe, corrida.nomepassageiro AS passageiro, corrida.situacao AS sitcorrida, corrida.datahora AS datahora, "
                                                       + "corrida.datac AS datac, corrida.hora AS hora, corrida.numerovoo AS numvoo, corrida.origem AS origem, corrida.destino AS destino, corrida.horacontratada AS horacont, corrida.kmcontratado AS kmcont, "
                                                       + "corrida.kmadicional AS kmadd, corrida.totalkmadicional AS kmaddtot, corrida.totalparcial AS subtotal, corrida.total AS total FROM cliente, corrida, classe " 
                                                       + "WHERE corrida.datac = ? AND corrida.classe = ? AND corrida.cliente = cliente.id AND corrida.classe = classe.id ORDER BY corrida.id DESC";
    
    private static final String READRUNNINGPAY = "SELECT corrida.id AS id, corrida.nomepassageiro AS passageiro FROM corrida, pagamento WHERE pagamento.id = ? AND pagamento.corrida = corrida.id;";
    
    private static final String UPDATESTATUS = "UPDATE corrida SET situacao = 2 WHERE corrida.id = ?;";
    
    public Running registerRunning(Running running){
        Connection connection = null;
        PreparedStatement query;
        
        try{
            connection = ConnectionDB.getConnection();
            connection.setAutoCommit(false);

            query = connection.prepareStatement(INSERTRUNNING, PreparedStatement.RETURN_GENERATED_KEYS);
            query.setInt(1, running.getClient().getId());
            query.setString(2, running.getClassCar().getId());
            query.setString(3, running.getNamePassenger());
            query.setLong(4, running.getDataHour());
            query.setString(5, running.getHour());
            query.setString(6, running.getData());
            query.setString(7, running.getNumFlight());
            query.setDouble(8, Double.parseDouble(running.getHourCont().toString()));
            query.setDouble(9, Double.parseDouble(running.getKmCont().toString()));
            query.setString(10, running.getOrigin());
            query.setString(11,running.getDestination());
            query.setDouble(12, Double.parseDouble(running.getValueAddKm().toString()));
            query.setDouble(13, Double.parseDouble(running.getTotalKmAdd().toString()));
            query.setDouble(14, Double.parseDouble(running.getTotalPartial().toString()));
            query.setDouble(15, Double.parseDouble(running.getTotal().toString()));
            query.setInt(16,running.getStatus());
            query.execute();

            ResultSet resultQuery = query.getGeneratedKeys();

            if(resultQuery.next()){
                running.setId(resultQuery.getInt(1));
            }else{
                running.setId(0);
            }

            connection.commit();
        
        }catch(Exception error){
            throw new RuntimeException(error);
        }finally{
            try{
                connection.close();
            }catch(SQLException error){
                throw new RuntimeException(error);
            }
        }
        return running;
    }
    
    public List<Running> readRunning(Client client){
        List<Running> listRunning = new ArrayList();
        Connection connection = null;
        PreparedStatement query;
        
        try{
            connection = ConnectionDB.getConnection();
            query = connection.prepareStatement(READRUNNING);
            query.setInt(1, client.getId());
            ResultSet queryResult = query.executeQuery();
            
            Running running = null;
            ClassCar classCar = null;
            
            while(queryResult.next()){
                running = new Running();
                classCar = new ClassCar();
                
                client.setId(queryResult.getInt("cliente"));
                running.setClient(client);
                running.setId(queryResult.getInt("corrida"));
                classCar.setId(queryResult.getString("classe"));
                running.setClassCar(classCar);
                running.setNamePassenger(queryResult.getString("passageiro"));
                running.setStatus(queryResult.getInt("sitcorrida"));
                running.setDataHour(queryResult.getLong("datahora"));
                running.setData(queryResult.getString("datac"));
                running.setHour(queryResult.getString("hora"));
                running.setNumFlight(queryResult.getString("numvoo"));
                running.setOrigin(queryResult.getString("origem"));
                running.setDestination(queryResult.getString("destino"));
                running.setHourCont(BigDecimal.valueOf(queryResult.getDouble("horacont")));
                running.setKmCont(BigDecimal.valueOf(queryResult.getDouble("kmcont")));
                running.setValueAddKm(BigDecimal.valueOf(queryResult.getDouble("kmadd")));
                running.setTotalKmAdd(BigDecimal.valueOf(queryResult.getDouble("kmaddtot")));
                running.setTotalPartial(BigDecimal.valueOf(queryResult.getDouble("subtotal")));
                running.setTotal(BigDecimal.valueOf(queryResult.getDouble("total")));
                
                listRunning.add(running);
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
        
        
        return listRunning;
    }
    
    public List<Running> readRunning(Client client, Running running){
        List<Running> listRunning = new ArrayList();
        Connection connection = null;
        PreparedStatement query = null;
        
        try{
            connection = ConnectionDB.getConnection();
            
            if ((running.getClassCar().getId().equals("none")) && (running.getData().equals("none")) && (running.getNamePassenger().equals("none"))){
                
                if(!client.getName().equals("none")){
                    query = connection.prepareStatement(READRUNNINGCLIENT);
                    query.setString(1, client.getName());
                
                }else{
                    query = connection.prepareStatement(READRUNNINGID);
                    query.setInt(1, running.getId());
                }
               
            }else if(!running.getClassCar().getId().equals("none")){
                
                if(!client.getName().equals("none")){
                    query = connection.prepareStatement(READRUNNINGCLIENTCLASS);
                    query.setString(1, client.getName());
                    query.setString(2, running.getClassCar().getId());
                
                }else if(!running.getData().equals("none")){
                    query = connection.prepareStatement(READRUNNINGDATECLASS);
                    query.setString(1, running.getData());
                    query.setString(2, running.getClassCar().getId());
                
                }else if(!running.getNamePassenger().equals("none")){
                    query = connection.prepareStatement(READRUNNINGPASSENGERCLASS);
                    query.setString(1, running.getNamePassenger());
                    query.setString(2, running.getClassCar().getId());
                }
            
            }else if(running.getClassCar().getId().equals("none")){
                 
                if((running.getNamePassenger().equals("none")) && (!running.getData().equals("none"))){
                    query = connection.prepareStatement(READRUNNINGDATE);
                    query.setString(1, running.getData());
                
                }else if((!running.getNamePassenger().equals("none")) && (running.getData().equals("none"))){
                    query = connection.prepareStatement(READRUNNINGPASSENGER);
                    query.setString(1, running.getNamePassenger());
                }
            }
            
            ResultSet queryResult = query.executeQuery();
            ClassCar classCar = null;
            
            while(queryResult.next()){
                running = new Running();
                classCar = new ClassCar();
                
                client.setId(queryResult.getInt("cliente"));
                client.setName(queryResult.getString("nome"));
                running.setClient(client);
                running.setId(queryResult.getInt("corrida"));
                classCar.setId(queryResult.getString("classe"));
                running.setClassCar(classCar);
                running.setNamePassenger(queryResult.getString("passageiro"));
                running.setStatus(queryResult.getInt("sitcorrida"));
                running.setDataHour(queryResult.getLong("datahora"));
                running.setData(queryResult.getString("datac"));
                running.setHour(queryResult.getString("hora"));
                running.setNumFlight(queryResult.getString("numvoo"));
                running.setOrigin(queryResult.getString("origem"));
                running.setDestination(queryResult.getString("destino"));
                running.setHourCont(BigDecimal.valueOf(queryResult.getDouble("horacont")));
                running.setKmCont(BigDecimal.valueOf(queryResult.getDouble("kmcont")));
                running.setValueAddKm(BigDecimal.valueOf(queryResult.getDouble("kmadd")));
                running.setTotalKmAdd(BigDecimal.valueOf(queryResult.getDouble("kmaddtot")));
                running.setTotalPartial(BigDecimal.valueOf(queryResult.getDouble("subtotal")));
                running.setTotal(BigDecimal.valueOf(queryResult.getDouble("total")));
                
                listRunning.add(running);
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
        
        
        return listRunning;
    }
    
    public Running readRunning(Running running, PaymentMain paymentMain){
        Connection connection = null;
        PreparedStatement query;
        
        try{
            connection = ConnectionDB.getConnection();
            query = connection.prepareStatement(READRUNNINGPAY);
            query.setString(1, paymentMain.getId());
            ResultSet result = query.executeQuery();
            
            while(result.next()){
                running.setId(result.getInt("id"));
                running.setNamePassenger(result.getString("passageiro"));
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
        
        return running;
    }
    
    public boolean finishRunning(Running running){
        boolean answer = false;
        Connection connection = null;
        PreparedStatement query;
        
        try{
            connection = ConnectionDB.getConnection();
            query = connection.prepareStatement(UPDATESTATUS);
            query.setInt(1, running.getId());
            query.execute();
            answer = true;
            
        }catch(Exception error){
            throw new RuntimeException(error);
        }finally{
            try{
                connection.close();
            }catch(Exception error){
                throw new RuntimeException(error);
            }
        }
        return answer;
    }
}
