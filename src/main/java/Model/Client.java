package Model;

import java.util.List;

public class Client {
    private int id;
    private String name;
    private List<Phone> phone;
    private String email;
    private String password;

    public void setId(int id){
        this.id=id;
    }
    
    public int getId(){
        return id;
    }
    
    public void setName(String name){
        this.name=name;
    }
    
    public String getName(){
        return name;
    }
    
    public List<Phone> getPhone() {
        return phone;
    }

    public void setPhone(List<Phone> phone) {
        this.phone = phone;
    }
    
    public void setEmail(String email){
        this.email=email;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setPassword(String password){
        this.password=password;
    }
    
    public String getPassword(){
        return password;
    }
    
}

