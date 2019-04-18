package Model;

public class ItemService {
    private AdditionalService addService;
    private Running running;
    private String details;
    
    public AdditionalService getAddService() {
        return addService;
    }

    public void setAddService(AdditionalService addService) {
        this.addService = addService;
    }

    public Running getRunning() {
        return running;
    }

    public void setRunning(Running running) {
        this.running = running;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
