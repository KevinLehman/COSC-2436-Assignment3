/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa3_kevin_lehman;

import java.util.Scanner;
import java.util.Collections;
import java.util.LinkedList;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;


/**
 *
 * @author user
 */
public class PA3_Kevin_Lehman implements Serializable{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Member Definitions, case6count keeps track of the number of times option 6 is selected to filter in and out
        int choice = 0, i, i1, i2, case6count = 0;
        Scanner scan = new Scanner(System.in);
        ToDoList list = new ToDoList();
        //cList is used as a list to hold the complete items filtered in option 6
        LinkedList<ToDoList> cList = new LinkedList<>();
        //iList is used as a list to hold the incomplete items filtered in option 6
        LinkedList<ToDoList> iList = new LinkedList<>();
        String string;
        //parts is used to parse date input from string to 3 ints i, i1, i2
        String[] parts;
        LinkedList<ToDoList> tdList = new LinkedList<>();
        do{   
            //Prints Current List after each loop
            list.printList(tdList);
            System.out.println();
            //Menu System for running program
            System.out.println("*********TO DO LIST MENU*********");
            System.out.println("\t" + "1. Add a new task");
            System.out.println("\t" + "2. Modify a task");
            System.out.println("\t" + "3. Remove a task");
            System.out.println("\t" + "4. Display tasks by priority");
            System.out.println("\t" + "5. Display tasks by due date");
            System.out.println("\t" + "6. Filter/Unfilter complete tasks");
            System.out.println("\t" + "7. Save To Do List");
            System.out.println("\t" + "8. Load To Do List");
            System.out.println("\t" + "9. Quit Program");
            System.out.print("Enter Option (1-9):");
            choice = scan.nextInt();
            //To remove newline character
            scan.nextLine();
            switch(choice){
                case 1:
                    //Local ToDoList to store current case 1 iteration and add to tdList
                    ToDoList list2 = new ToDoList();
                    DateTime date = new DateTime();
                    
                    //String input for Task name
                    System.out.println("Add a task to the list");
                    System.out.print("Enter the text for the task:");
                    list2.setDescription(scan.nextLine());
                    
                    //Takes a string input and parses it into a date that meets DateTime format
                    System.out.print("Enter month day and year that task is due as integers i.e. (1 1 2018):");
                    string = scan.nextLine();
                    parts = string.split(" ");
                    
                    i = Integer.parseInt(parts[0]);
                    i1 = Integer.parseInt(parts[1]);
                    i2 = Integer.parseInt(parts[2]);
                    
                    date.setMonth(i);
                    date.setDay(i1);
                    date.setYear(i2);
                    list2.setDate(date);
                    
                    //Sets new ToDoList Priority from an int
                    System.out.print("Enter task priority (0-Low, 1-Moderate, 2-High, 3-Urgent):");
                    list2.setPriority(scan.nextInt());
                    scan.nextLine();
                    
                    //Set status to incomplete for all new items
                    list2.setStatus(0);
                    
                    //Add new Task to LinkedList
                    tdList.add(list2);
                    break;
                case 2:                  
                    //Case to Modify an exsisting Task and add it to the end of the list
                    System.out.println("Modify a task");
                    System.out.print("Select a task to modify (0 to not make a modification):");
                    i = scan.nextInt();
                    scan.nextLine();
                    
                    //Checks for skip case
                    if(i > 0){
                        ToDoList list3 = new ToDoList(tdList.get(i-1));
                        tdList.remove(i-1);
                        
                        System.out.println("Current Task: " + list3.getDescription());
                        System.out.print("Enter new task description(Enter for no change):");
                        string = scan.nextLine();
                        
                        //Checks for skip case
                        if(string != null && !string.isEmpty()){                       
                            list3.setDescription(string);
                        }
                        
                        System.out.println("Current Priority: " + list3.getPriority());
                        System.out.print("Enter new task priority (0-Low, 1-Moderate, 2-High, 3-Urgent, 4-No Change):");
                        i = scan.nextInt();
                        scan.nextLine();
                        
                        //Checks for skip case
                        if(i >= 0 && i < 4){
                            list3.setPriority(i);
                        }                        
                        System.out.println();
                        
                        date = list3.getDate();
                        System.out.println("Current due date:" + date.toString());
                        System.out.print("Enter new month day and year that task is due as integers i.e. (1 1 2018) or 0 0 0 for no change:");
                        string = scan.nextLine();
                        parts = string.split(" ");
                        
                        i = Integer.parseInt(parts[0]);
                        i1 = Integer.parseInt(parts[1]);
                        i2 = Integer.parseInt(parts[2]);
                        
                        //Checks for skip case
                        if(i != 0){
                            date.setMonth(i);
                            date.setDay(i1);
                            date.setYear(i2);
                            list3.setDate(date);
                        }
                        
                        System.out.println("Current completion status:" + list3.getStatus());
                        System.out.print("Set new completion status(0-Incomplete, 1-Complete:)");
                        i = scan.nextInt();
                        scan.nextLine();
                        list3.setStatus(i);
                        
                        //Adds modified Task to the end of the LinkedList
                        tdList.add(list3);
                        break;                                                    
                    }
                    else{
                        break;
                    }                        
                case 3:
                    //Checks for ToDoList in LinkedList
                    if(tdList.isEmpty()){
                        System.out.println("There are no tasks to remove.");
                        break;
                    }
                    else{
                        System.out.print("Enter a task to remove(1-" + (tdList.size()) + "):");
                        i = scan.nextInt();
                        scan.nextLine();
                        
                        list = tdList.get(i-1);
                        System.out.println();
                        tdList.remove(i-1);
                        
                        System.out.println("Task " + i + " has been removed.");
                        break;
                    }
                case 4:
                    //Calls Collections.sort on LinkedList using custom Comparator
                    Collections.sort(tdList, new PriorityComparator());
                    System.out.println("Sorted To Do List");
                    break;
                case 5:
                    //Calls Collections.sort on LinkedList using custom Comparator
                    Collections.sort(tdList, new DateComparator());
                    System.out.println("Sorted To Do List");
                    break;
                case 6:
                    //Clears list so as not to repeatedly add same elements        
                    iList.clear();
                    
                    //If its the first iteration or an even iteration it will remove completed tasks
                    //Even counts will add completed task back to the list
                    if(case6count == 0 || (case6count%2) == 0){                        
                        for(i = 0; i < tdList.size(); i++){
                            if(tdList.get(i).getStatusInt() == 0){
                               iList.add(tdList.get(i));
                            }
                            else{
                               cList.add(tdList.get(i)); 
                               tdList.remove(i);
                            }                            
                        }
                        System.out.println("Completed Tasks Filtered");
                        case6count++;
                        break;
                    }
                    else{
                        System.out.println("Completed Tasks Unfiltered");
                        tdList.addAll(cList);
                        case6count++;
                        break;        
                    }                        
                case 7:
                    //Saves the linked list to a file
                    System.out.println("Save Tasks to a File");
                    System.out.print("Enter target file location name:(default set to tasks.txt)");
                    string = scan.nextLine();
                    if(string == null && string.isEmpty()){
                        string = "tasks.txt";
                    }
                    try(FileOutputStream fout = new FileOutputStream(string);
                        ObjectOutputStream oos = new ObjectOutputStream(fout);){
                        oos.writeObject(tdList); 
                        System.out.println("Tasks successfully saved to file: " + string);
                        oos.close();
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                    break;
                case 8:
                    //Clears the current list if loading a list from running the program
                    //Loads a LinkedList from a saved file
                    tdList.clear();
                    System.out.println("Load Tasks from a File");
                    System.out.print("Enter name of target file to load: ");
                    string = scan.nextLine();
                    System.out.println();
                    ObjectInputStream ois;
                    try{
                        FileInputStream streamIn = new FileInputStream(string);
                        ois = new ObjectInputStream(streamIn);
                        LinkedList<ToDoList> readCase;
                        do{
                            readCase = (LinkedList) ois.readObject();
                            if(readCase != null){
                                tdList.addAll(readCase);
                            }
                            
                        }while(readCase!=null);
                        System.out.println("File Loaded Successfully.");                        
                        ois.close();
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }   
                    break;
            }
        }while(choice != 9);
        
        
    }
}


