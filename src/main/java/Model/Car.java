/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


public class Car {
    private int id;
    private String brand;
    private String model;
    private ClassCar clasCar;
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    public String getBrand() {
        return brand;
    }

    public void setModel(String model) {
        this.model = model;
    }
    
    public String getModel() {
        return model;
    }

    public void setClasCar(ClassCar clasCar) {
        this.clasCar = clasCar;
    }
    
    public ClassCar getClasCar() {
        return clasCar;
    }
    
}
