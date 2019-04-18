package Test;

import Model.AdditionalService;
import ModelDAO.AdditionalServiceDAO;

public class TestReadAddService {
    public static void main(String args[]){
        AdditionalService addService = new AdditionalService();
        
        addService.setId(3);
        
        AdditionalServiceDAO addServiceDAO = new AdditionalServiceDAO();
        
        addServiceDAO.readAddService(addService);
        
        System.out.println("Descrição: "+addService.getDescription());
        
    }
}
