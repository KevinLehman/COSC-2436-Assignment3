/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa3_kevin_lehman;
import java.util.*;
import java.io.Serializable;
/**
 *
 * @author user
 */
public class ToDoList implements Serializable{
    private String description;
    private DateTime date;
    private Priority priority;
    private Status status;
    
    
    public enum Priority {LOW, MODERATE, HIGH, URGENT};
    public enum Status {COMPLETE, INCOMPLETE};
    
    //Default Constructor
    public ToDoList(){
        this.description = "";
        this.date = new DateTime();
        this.priority = Priority.LOW;
        this.status = Status.INCOMPLETE;
    }
    
    public ToDoList(ToDoList t){
        this.description = t.getDescription();
        this.date = t.getDate();
        this.priority = t.getPriority();
        this.status = t.getStatus();
    }

    public Priority getPriority() {
        return priority;
    }
    
    //Returns an int value of priority for comparator
    public int getPriorityInt() {
        if(priority == Priority.LOW){
            return 0;
        }
        else if(priority == Priority.MODERATE){
            return 1;
        }
        else if(priority == Priority.HIGH){
            return 2;
        }
        else{
            return 3;
        }         
    }

    public void setPriority(int i) {
        switch(i){
            case 0:
                this.priority = Priority.LOW;
                break;
            case 1:
                this.priority = Priority.MODERATE;
                break;
            case 2:
                this.priority = Priority.HIGH;
                break;
            case 3:
                this.priority = Priority.URGENT;
                break;
        }
    }

    public Status getStatus() {
        return status;
    }
    
//Returns an int value of status for toggling by status
    public int getStatusInt() {
        if(status == Status.COMPLETE){
            return 1;
        }
        else{    
            return 0;
        }
    }

    public void setStatus(int i) {
        switch(i){
            case 0:
                this.status = Status.INCOMPLETE;
                break;
            case 1:
                this.status = Status.COMPLETE;
                break;
        }   
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }
    
    //Used to print the contents of a LinkedList
    public void printList(LinkedList<ToDoList> list){
        System.out.println("******** TO DO LIST ********");
        System.out.println();
        
        for(int i = 0; i < list.size(); i++){
            System.out.println(i+1 + ". " + list.get(i).toString());
        }
    }
    
    @Override
    public String toString(){
        return getDescription() + " | " + getPriority() + " | " + getDate().toString() + " | " + getStatus();
    }
}
