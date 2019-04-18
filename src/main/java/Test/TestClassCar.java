package Test;

import Model.ClassCar;
import Model.Running;
import ModelDAO.ClassCarDAO;

public class TestClassCar {
    public static void main(String args[]){

        ClassCar classCar = new ClassCar();
        
        classCar.setId("ftc");
        
        ClassCarDAO classCarDAO = new ClassCarDAO();
        
        classCarDAO.accessClass(classCar);
        
        System.out.println("Classe: "+classCar.getId());
        System.out.println("Preco hora: "+classCar.getPriceHour());
        System.out.println("Preco km: "+classCar.getPriceKm());
        
        
        Running running = new Running();
        running.setTypeRun("h");
        //running.setValueRun(2);
        running.calculateTotal(classCar);
        
        System.out.println("Total do servviço: "+running.getTotal());
    }
        
}
