/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Model.Client;
import ModelDAO.ClientDAO;
import Util.CripPassword;
import Util.UniversallyUniqueIdentifier;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author eduan
 */
public class TestRecoveryPass {
    public static void main(String args[]) throws UnsupportedEncodingException, NoSuchAlgorithmException{
        Client client = new Client();
        
        client.setEmail("eduanlima@outlook.com");
        
        ClientDAO clientDAO = new ClientDAO();
        
        client = clientDAO.readEmailClient(client);
        
        if(client.getId()!= 0){
            System.out.println("ID--> "+client.getId());
            System.out.println("Name--> "+client.getName());
            System.out.println("Email--> "+client.getEmail());
            
            //Creating a new password
            String newpass = UniversallyUniqueIdentifier.getUUID();
            newpass = newpass.substring(0,7);
            
            System.out.println("Nova senha--> "+newpass);
            
            client.setPassword(CripPassword.encrypt(newpass));
            
            clientDAO.alterPassword(client);
            
        }else if(client.getId() == 0){
            System.out.println("Email não encontrado em nossos cadastros.");
        }
    }
}
