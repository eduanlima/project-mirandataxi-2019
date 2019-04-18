package Util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    public static Connection getConnection(){
        Connection connection = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://mysql06-farm62.kinghost.net/mirandataxi","mirandataxi","developerboss4991");
            //System.out.println("Conectado");
            
        }catch(ClassNotFoundException errorOne){
            throw new RuntimeException(errorOne);
            
        }catch(SQLException errorTwo){
            throw new RuntimeException(errorTwo);
        }
        return connection;
    }
}
