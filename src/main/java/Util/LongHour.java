package Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class LongHour {
    public static long getDataHourLong(String hourText) throws ParseException{
        SimpleDateFormat formatD = new SimpleDateFormat("dd/MM/yyyy HH:m:ss");         
        long hour = formatD.parse(hourText).getTime();      
        return hour;
    } 
    
    public static long calculateHour(long hour){
        hour = (hour - System.currentTimeMillis()) / 3600000; 
        return hour;
    }
}
