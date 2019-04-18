package Model;


public class ClassCar {
    private String id;
    private String name;
    private double priceHour;
    private double priceKm;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriceHour() {
        return priceHour;
    }

    public void setPriceHour(double priceHour) {
        this.priceHour = priceHour;
    }
    
    public void setPriceKm(double precoKm){
        this.priceKm = precoKm;
    }
    
    public double getPriceKm(){
        return priceKm;
    }

}
