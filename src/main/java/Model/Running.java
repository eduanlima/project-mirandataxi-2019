package Model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

public class Running {
    private int id;
    private Client client;
    private ClassCar classCar;
    private String namePassenger; 
    private long dataHour;
    private String hour;
    private String data;
    private String numFlight;
    private BigDecimal hourCont;
    private BigDecimal kmCont;
    private String origin;
    private String destination;
    private BigDecimal valueAddKm;
    private BigDecimal totalKmAdd;
    private BigDecimal totalPartial;
    private BigDecimal total;
    private int status;
    private String typeRun;
    private BigDecimal valueRun;
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
    
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    public void setClassCar(ClassCar classCar){
        this.classCar = classCar;
    }
    
    public ClassCar getClassCar(){
        return classCar;
    }
    
    public void setNamePassenger(String namePassenger){
        this.namePassenger = namePassenger;
    }
    
    public String getNamePassenger(){
        return namePassenger;
    }
    
    public long getDataHour() {
        return dataHour;
    }

    public void setDataHour(long dataHour) {
        this.dataHour = dataHour;
    }
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    public String getNumFlight() {
        return numFlight;
    }

    public void setNumFlight(String numFlight) {
        this.numFlight = numFlight;
    }
    
    public BigDecimal getHourCont() {
        return hourCont;
    }

    public void setHourCont(BigDecimal hourCont) {
        this.hourCont = hourCont;
    }
    
    public BigDecimal getKmCont() {
        return kmCont;
    }

    public void setKmCont(BigDecimal kmCont) {
        this.kmCont = kmCont;
    }
    
    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
    
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    public void setHour(String hour){
        this.hour = hour;
    }
    
    public String getHour(){
        return hour;
    }
    
    public void setTypeRun(String typeRun){
        this.typeRun = typeRun;
    }
    
    public String getTypeRun(){
        return typeRun;
    }
    
    public void setValueRun(BigDecimal valueRun){
        this.valueRun = valueRun;
    }
    
    public BigDecimal getValueRun(){
        return valueRun;
    }
    
    public BigDecimal getValueAddKm() {
        return valueAddKm;
    }

    public void setValueAddKm(BigDecimal valueAddKm) {
        this.valueAddKm = valueAddKm;
    }
    
    public void setTotalKmAdd(BigDecimal totalKmAdd) {
        this.totalKmAdd = totalKmAdd;
    }

    public void setTotalPartial(BigDecimal totalPartial) {
        this.totalPartial = totalPartial;
    }
    
    public BigDecimal getTotal(){
        return total;
    }
    
    public void setTotal(BigDecimal total){
        this.total = total;
    }
    
    public BigDecimal getTotalKmAdd(){
        return totalKmAdd;
    }
    
    public BigDecimal getTotalPartial() {
        return totalPartial;
    }
    
    public void setStatus(int status){
        this.status = status;
    }
    
    public int getStatus(){
        return status;
    }

    public void calculateTotal(ClassCar classCar){
        BigDecimal bigHour = new BigDecimal(""+classCar.getPriceHour());
        BigDecimal bigKm = new BigDecimal(""+classCar.getPriceKm());
        
        switch (typeRun){
            case "h":
                    total = valueRun.multiply(bigHour).setScale(2,RoundingMode.FLOOR);
                    totalPartial = total;
                    totalKmAdd = bigKm.multiply(valueAddKm);
                    total = total.add(totalKmAdd).setScale(2,RoundingMode.FLOOR);
                    hourCont = valueRun;
                    kmCont = BigDecimal.valueOf(0);
                break;
                
            case "t":
                    total = valueRun.multiply(bigHour).setScale(2,RoundingMode.FLOOR);
                    totalPartial = total;
                    totalKmAdd = bigKm.multiply(valueAddKm);
                    hourCont = valueRun;
                    kmCont = BigDecimal.valueOf(0);
                break;
                
            case "k": 
                    total = valueRun.multiply(bigKm).setScale(2,RoundingMode.FLOOR);
                    totalPartial = total;
                    totalKmAdd = bigKm.multiply(valueAddKm).setScale(2,RoundingMode.FLOOR);
                    total = total.add(totalKmAdd).setScale(2,RoundingMode.FLOOR);
                    hourCont = BigDecimal.valueOf(0);
                    kmCont = valueRun;
                break;
        }
    }

}
