package ModelDAO;

import Model.AdditionalService;
import Model.ItemService;
import Model.Running;
import Util.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemServiceDAO {
    private static final String INSERTITEMSERVICE = "INSERT INTO itemservico VALUES (?,?,?);";
    private static final String READITEMSERVICE = "SELECT itemservico.corrida AS corrida,servicoadicional.id AS servico, "
                                                  + "servicoadicional.descricao AS descricao, itemservico.detalhe AS detalhe "
                                                  + "FROM itemservico, servicoadicional, corrida "
                                                  + "WHERE corrida.id = ? AND itemservico.corrida = corrida.id AND "
                                                  + "itemservico.servicoadicional = servicoadicional.id "
                                                  + "ORDER BY servicoadicional.id";
    
    public ItemService registerItemService(ItemService itemService){
        Connection connection = null;
        PreparedStatement query;
    
        try{
            connection = ConnectionDB.getConnection();
            query = connection.prepareStatement(INSERTITEMSERVICE);
            query.setInt(1, itemService.getAddService().getId());
            query.setInt(2, itemService.getRunning().getId());
            query.setString(3, itemService.getDetails());
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
        
        return itemService;
    }
    
    public List<ItemService> readItemService(Running running){
        List<ItemService> listItemService = new ArrayList<>();
        Connection connection = null;
        PreparedStatement query;
        
        try{
            connection = ConnectionDB.getConnection();
            query = connection.prepareStatement(READITEMSERVICE);
            query.setInt(1, running.getId());
            ResultSet queryResult = query.executeQuery();
            
            AdditionalService addService;
            ItemService itemService;
            
            while(queryResult.next()){
                addService = new AdditionalService();
                //addService.setId(queryResult.getInt("servico"));
                addService.setDescription(queryResult.getString("descricao"));
                //running.setId(queryResult.getInt("corrida"));
                itemService = new ItemService();
                itemService.setAddService(addService);
                //itemService.setRunning(running);
                itemService.setDetails(queryResult.getString("detalhe"));
                
                listItemService.add(itemService);
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
        
        return listItemService;
    }
}
