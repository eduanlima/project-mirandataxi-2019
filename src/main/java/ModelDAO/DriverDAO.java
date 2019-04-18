/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import Model.Driver;
import Util.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author eduan
 */
public class DriverDAO {
    private static final String READDRIVER = "SELECT motorista.id AS id FROM motorista WHERE motorista.login = ? and motorista.senha = ? and motorista.situacao = true";
    
    public Driver readDriver(Driver driver){
        driver.setId(0);
        Connection connection = null;
        PreparedStatement query;
        
        try{
            connection = ConnectionDB.getConnection();
            query = connection.prepareStatement(READDRIVER);
            query.setString(1, driver.getLogin());
            query.setString(2, driver.getPassword());
            ResultSet result = query.executeQuery();
            
            while(result.next()){
                driver.setId(result.getInt("id"));
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
        return driver;
    }
}
