/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Control.RegisterClient;
import Model.Client;
import ModelDAO.ClientDAO;
import Util.CripPassword;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GRAFIX
 */
public class TestAlterPass {
    public static void main(String args[]){
        Client client = new Client();
        
        String password = "";
        client.setId(23);
        /*** Senha atual ***/
        password = "444";
        
        try {
            client.setPassword(CripPassword.encrypt(password));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RegisterClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RegisterClient.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        /*** Inserindo a nova senha ***/
        ClientDAO clientDAO = new ClientDAO();
        
        if(clientDAO.loginClientPass(client)){
            /*** Novo password ***/
            password = "555";
            
            try {
                client.setPassword(CripPassword.encrypt(password));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(RegisterClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(RegisterClient.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(clientDAO.alterPassword(client)){
                System.out.println("Alterado!");
            }
        }else{
            System.out.println("Cliente não existe");
        }
      
    }
}
