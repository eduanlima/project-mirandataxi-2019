/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author eduan
 */
public class CripPassword {
    
public static String encrypt(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{
    
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte passwordByte[] = algorithm.digest(password.getBytes("UTF-8"));
        
        StringBuilder passwordHex = new StringBuilder();
        
        for(byte b : passwordByte){
            passwordHex.append(String.format("%02X", 0xFF & b));
        }
        
        String passwordCrip = passwordHex.toString();
        return passwordCrip;

    }
    
}
