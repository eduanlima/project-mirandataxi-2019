package ModelDAO;

import Model.AdditionalService;
import Util.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdditionalServiceDAO {
   private static final String SELECTSERVICE = "SELECT id, descricao FROM servicoadicional WHERE id = ?";
   
   public AdditionalService readAddService(AdditionalService addService){
       Connection connection = null;
       PreparedStatement query;
       
       try{
           connection = ConnectionDB.getConnection();
           query = connection.prepareStatement(SELECTSERVICE);
           query.setInt(1, addService.getId());
           ResultSet resultQuery = query.executeQuery();
           
           while(resultQuery.next()){
               addService.setId(resultQuery.getInt("id"));
               addService.setDescription(resultQuery.getString("descricao"));
           }

       }catch(Exception error){
           throw new RuntimeException(error);
       }finally{
           try{
               connection.close();
           }catch(SQLException error){
            throw new RuntimeException(error);
           }
       }
       return addService;
   }
}
