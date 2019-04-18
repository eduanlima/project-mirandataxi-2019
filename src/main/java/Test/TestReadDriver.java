/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Model.Driver;
import ModelDAO.DriverDAO;
import Util.CripPassword;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 *
 * @author eduan
 */
public class TestReadDriver {
    
    public static void main(String args[]) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        Scanner sc = new Scanner(System.in);
        
        Driver driver = new Driver();
        DriverDAO driverDAO = new DriverDAO();
               
        driver.setLogin(sc.next());
        driver.setPassword(CripPassword.encrypt(sc.next()));
        
        driver = driverDAO.readDriver(driver);
        
        if(driver.getId() != 0){
            System.out.println("1");
        }else{
            System.out.println("0");
        }
        
        sc.close();
    }
    
}
