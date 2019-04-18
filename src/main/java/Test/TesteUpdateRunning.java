/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Model.Running;
import ModelDAO.RunningDAO;

/**
 *
 * @author eduan
 */
public class TesteUpdateRunning {
    public static void main(String args[]){
        Running running = new Running();
        running.setId(43);
        
        RunningDAO runningDAO = new RunningDAO();
        
        if(runningDAO.finishRunning(running)){
            System.out.println("The run was finalizate!");
        }else{
            System.out.println("The run wasn't finalizate!");
        }
    }
    
}
