/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa3_kevin_lehman;
import java.util.Comparator;
/**
 *
 * @author user
 */
public class PriorityComparator implements Comparator<ToDoList>{
    
    @Override
    public int compare(ToDoList t1, ToDoList t2){
        if(t1.getPriorityInt()>t2.getPriorityInt()){
             return -1;
         }
         else if(t1.getPriorityInt()<t2.getPriorityInt()){
             return 1;
         }
         else{
             return 0;
         }
    }
}    
    

