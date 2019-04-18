/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.util.UUID;

/**
 *
 * @author GRAFIX
 */
public class UniversallyUniqueIdentifier {
    public static String getUUID(){
       UUID uuid = UUID.randomUUID();
       String uuidString = uuid.toString();
        
        return uuidString;
    }
}
