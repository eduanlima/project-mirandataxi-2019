package Test;

import Model.AdditionalService;
import Model.ItemService;
import Model.Running;
import ModelDAO.AdditionalServiceDAO;
import ModelDAO.ItemServiceDAO;

public class TestItemService {
    
    public static void main(String args[]){
        AdditionalService addService = new AdditionalService();
        addService.setId(2);
        
        AdditionalServiceDAO addServiceDAO = new AdditionalServiceDAO();
        addService = addServiceDAO.readAddService(addService);
        
        Running running = new Running();
        running.setId(3);
        
        
        ItemService itemService = new ItemService();
        itemService.setAddService(addService);
        itemService.setRunning(running);
        itemService.setDetails("Eduan Lima");
        
        ItemServiceDAO itemServiceDAO = new ItemServiceDAO();
        itemServiceDAO.registerItemService(itemService);
    }
}
