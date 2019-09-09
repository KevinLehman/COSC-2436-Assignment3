/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa3_kevin_lehman;
import java.util.GregorianCalendar;
import java.text.DateFormatSymbols;
import java.io.Serializable;
/**
 *
 * @author user
 */
public class DateTime implements Serializable{
    //Private members
    private int year, month, day;
    
    //Gregorian object to construct DateTime with current date
    GregorianCalendar date = new GregorianCalendar();
    
    //Public Constructor 
    public DateTime(){
        year = date.get(GregorianCalendar.YEAR);
        month = date.get(GregorianCalendar.MONTH)+1;
        day = date.get(GregorianCalendar.DATE);
    }
    
    public DateTime(DateTime date){
        this.year = date.getYear();
        this.month = date.getMonth();
        this.day = date.getDay();
    }
    
    //Getters and Setters
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
    
    //Method to convert numeric month to named month
    public String getMonths(int month){
        return new DateFormatSymbols().getMonths()[month-1];
    }
    
    //Overriden toString method to return Month Name, Date, Year format
    @Override
    public String toString(){
        return getMonths(getMonth()) + ", " + getDay() + ", " + getYear();
    }
    
}
