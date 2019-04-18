/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Model.Client;
import ModelDAO.ClientDAO;

/**
 *
 * @author eduan
 */
public class TestReadEmail {
    public static void main(String args[]){
        Client client = new Client();
        client.setEmail("eduan@outlook.com");
        
        ClientDAO clientDAO = new ClientDAO();
        client = clientDAO.readEmailClient(client);
        
        if(client.getId() == 0){
            System.out.println("Email válido");
            
        }else if(client.getId() != 0){
            System.out.println("Já existe um cadastro com este e-mail em nosso sistema.");
        }
    }
}
