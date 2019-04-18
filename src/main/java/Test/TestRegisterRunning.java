package Test;

import Model.ClassCar;
import Model.Client;
import Model.Running;
import ModelDAO.RunningDAO;
import java.math.BigDecimal;

public class TestRegisterRunning {
    
    public static void main(String args[]){
        Client client = new Client();
        ClassCar classCar = new ClassCar();
        Running running = new Running();
        
        client.setId(1);
        classCar.setId("ecc");
        
        running.setClient(client);
        running.setClassCar(classCar);
        running.setDataHour(1568942);
        running.setHour("12:00");
        running.setData("30/04/1998");
        running.setNumFlight("TTT7");
        running.setHourCont(BigDecimal.valueOf(1.5));
        running.setKmCont(BigDecimal.valueOf(5));
        running.setOrigin("Rua 34");
        running.setDestination("Rua 56");
        running.setValueAddKm(BigDecimal.valueOf(6));
        running.setTypeRun("h");
        running.setValueRun(BigDecimal.valueOf(4));
        running.calculateTotal(classCar);
        
        RunningDAO runningDAO = new RunningDAO();
        
        running = runningDAO.registerRunning(running);
        
        if(running.getId()!= 0){
            System.out.println("Registrado: "+running.getId());
        }else if(running.getId() == 0){
            System.out.println("Ocooreu um erro ao registrar");
        }
        
    }
    
}
