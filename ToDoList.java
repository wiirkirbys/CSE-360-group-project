
/**
 * Write a description of class ToDoList here.
 *
 * @author Kevin McAllister
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.*;
public class ToDoList
{
    
    // instance variables - replace the example below with your own
    public ArrayList list = new ArrayList();

    /**
     * Constructor for objects of class ToDoList
     */
    public ToDoList()
    {
        // initialise instance variables
        //x = 0;
    }

    /**
     * Creates a new task and adds it to the bottom of the list.
     *
     * @param  description  a description of what the task entails
     * @param  dueDate  the date when the task is due
     */
    public void newTask(String description, String dueDate)	
    {
        list.add(new Task(description, dueDate, list.size()));
        /*
         * the GUI takes in all info: desc, priorit, dueDate, and status (NotStarted and InProgress)
         * so the params of this method might need to take in all values. unless u want to add then call a setStatus() and setPriority().
         */
    }
    
    public void updateEntry(String title) { 
    	/*
    	 * updateEntry lets you search for an entry using either the desc, duedate, or priority. we'll prob pass in a value that designates
    	 * what search to use. then it needs to show the values of the entry that is found in each textField and we will edit the values and
    	 * call an update method with the params (Desc, priority, dueDate, and Status(Not Started, In progess)
    	 */
    										
    		
    }
    
    public void completeEntry(String title) {
    	/*
    	 * Complete entry searches for an entry the same way as above and just changes the value of an entry to completed, and deletes it
    	 * I'm thinking that we shouldnt delete it and have it sit in a separate list for completed tasks, but we already made the layout so
    	 * we'll prob delete it.
    	 */
    }
    
    public String displayToDoList(ArrayList list) {
    	
    	return "";
    	/*
    	 * the gui just shows the list when the display button is pushed
    	 */
    }
    
    public void printReport(ArrayList list) {
    	//BufferedWriter fileWriter = new BufferedWriter(new FileWriter("TextfileName.txt"));
    	
    	/*
    	 * gui just calls method the minute the button is pressed
    	 */
    }
    
    public void saveList(ArrayList list) {
    	//BufferedWriter fileWriter = new BufferedWriter(new FileWriter("TextfileName.txt"));
    	
    	/*
    	 * gui just calls method the minute the button is pressed
    	 */
    }
    
    public void restoreList() {
        //BufferedReader fileReader = new BufferedReader(new FileReader("TextFileName.txt"));
        
    	/*
    	 * gui just calls method the minute the button is pressed
    	 */
    }
    
    public int findWithDesc(String title, ArrayList list) {	// we will prob need only 2 find methods, one for finding with desc and one for finding with dueDate
    														//idk if finding with priority is a good idea unless we make sure that priorities are unique.
    	int index = -1;
    	
    	for(int i = 0; i < list.size(); i++) {	//idk any algorithms that would help search names so im using a for loop for now
    		if(title.equals(((Task)list.get(i)).getDescription())) {
    			index = i;
    		}
    	}
    	
    	return index;	//returns -1 if the task isnt found
    }
	
}
