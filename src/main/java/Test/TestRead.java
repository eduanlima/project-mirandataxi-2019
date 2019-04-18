/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Model.Client;
import Model.Phone;
import ModelDAO.ClientDAO;

/**
 *
 * @author GRAFIX
 */
public class TestRead {
    public static void main(String args[]){
        Client client = new Client();
        
        client.setId(18);
        
        ClientDAO clientDAO = new ClientDAO();
        
        client = clientDAO.readClient(client);
        
        System.out.println("Nome: "+client.getName());
        System.out.println("E-mail: "+client.getEmail());
        
        for (Phone phone: client.getPhone()){
            System.out.println("Telefone: "+phone.getNumber());
        }
    }
}
