package ModelDAO;

import Model.ClassCar;
import Model.Running;
import Util.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassCarDAO {
    private static final String SELECTCLASS = "SELECT * FROM classe WHERE id = ?;";
    private static final String READCLASS = "SELECT classe.descricao AS descricao, classe.precohora AS hrpreco, classe.precokm AS kmpreco FROM classe WHERE classe.id = ?";
    
    public ClassCar accessClass(ClassCar classCar){
        Connection connection = null;
        
        try{
            connection = ConnectionDB.getConnection();
            PreparedStatement query = connection.prepareStatement(SELECTCLASS);
            query.setString(1,classCar.getId());
            ResultSet result = query.executeQuery();
            
            if(result.next()){
                classCar.setName(result.getString("descricao"));
                classCar.setPriceHour(result.getDouble("precohora"));
                classCar.setPriceKm(result.getDouble("precokm"));
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
        return classCar;
    }
    
    public ClassCar readClassCar(Running running){
        ClassCar classCar = new ClassCar();
        Connection connection = null;
        PreparedStatement query;
        
        try{
            connection = ConnectionDB.getConnection();
            query = connection.prepareStatement(READCLASS);
            query.setString(1,running.getClassCar().getId());
            ResultSet queryResult = query.executeQuery();
            
            while(queryResult.next()){
                classCar.setName(queryResult.getString("descricao"));
                classCar.setPriceHour(queryResult.getDouble("hrpreco"));
                classCar.setPriceKm(queryResult.getDouble("kmpreco"));
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
        
        return classCar;
    }
    
}
