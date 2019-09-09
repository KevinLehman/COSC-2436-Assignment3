/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa3_kevin_lehman;
import java.util.Date;
import java.util.Comparator;

/**
 *
 * @author user
 */
public class DateComparator implements Comparator<ToDoList>{
    private Date date1, date2;
    
    @Override
    public int compare(ToDoList t1, ToDoList t2){
        
        date1 = new Date(t1.getDate().getYear(), t1.getDate().getMonth(), t1.getDate().getDay());
        date2 = new Date(t2.getDate().getYear(), t2.getDate().getMonth(), t2.getDate().getDay());
        
        if(date1.after(date2))
        {
            return 1;
        }
        else if(date1.before(date2))
        {
            return -1;
        }
        else
        {
            return 0;
        }    
      
    }
}    
