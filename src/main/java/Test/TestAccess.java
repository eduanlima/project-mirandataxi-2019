package Test;

import Model.Client;
import ModelDAO.ClientDAO;

public class TestAccess {
    public static void main(String args[]){
        Client client = new Client();
        
        client.setEmail("eduan@outlook.com");
        client.setPassword("123");
        
        ClientDAO clientDAO = new ClientDAO();
        
        try{
            client = clientDAO.loginClient(client);
            
            if(client.getId() == 0){
                System.out.println("User not found.");
            }else{ 
                System.out.println("Welcome client!");
            }
            
        }catch(NullPointerException error){
            System.out.println("Error occurred." + error);
        }
    }
}
