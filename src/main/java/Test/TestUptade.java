/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Model.Client;
import Model.Phone;
import ModelDAO.ClientDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GRAFIX
 */
public class TestUptade {
    public static void main(String args[]){
        Client client = new Client();
        client.setId(18);
        client.setName("Eduan Lima");
        client.setEmail("ed_lima@outlook.com");
        
        List<Phone> listPhone = new ArrayList<>();
        
        Phone phone;
        
        phone = new Phone("1145889875");
        listPhone.add(phone);
        
        phone = new Phone("1140001111");
        listPhone.add(phone);
        
        phone = new Phone("1145889000");
        listPhone.add(phone);
        
        client.setPhone(listPhone);
        
        ClientDAO clientDAO = new ClientDAO();
        
        System.out.println("Resposta: "+clientDAO.updateClient(client));
        
    }
}
