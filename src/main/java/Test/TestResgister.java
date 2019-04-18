package Test;

import Model.Client;
import Model.Phone;
import ModelDAO.ClientDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eduan
 */
public class TestResgister {
    
    public static void main(String[] args){
        Client client = new Client();
        Phone phoneone = new Phone("2411-2338");
        Phone phonetwo = new Phone("2411-2356");
        Phone phonetree = new Phone("2411-2390");
        
        List<Phone> listphone = new ArrayList<>();
        listphone.add(0, phoneone);
        listphone.add(1, phonetwo);
        listphone.add(2, phonetree);
      
        
        client.setName("Eduan");
        client.setPhone(listphone);
        client.setEmail("eduan@outlook.com");
        client.setPassword("123");
        
        ClientDAO clientDAO = new ClientDAO();
        
        if(clientDAO.registerClient(client)){
            System.out.println("Cadastrado!");
        }
        
        /*
        client.getPhone().stream().forEach((ph) -> {
         System.out.println("Fone........: "+ph.getNumber());
        });
        */

        /*
        ClientDAO clientDAO = new ClientDAO();
        
        if(clientDAO.registerClient(client)){
            System.out.println("Success on register!");
        }else{
            System.out.println("Error occurred.");
        
        }*/
    }
}
