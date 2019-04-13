
/**
 * Write a description of class ToDoList here.
 *
 * @author Kevin McAllister
 */
import java.io.BufferedWriter;
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
    }
    
    public void updateEntry(String title) { //params can be changed to whatever
    	int index = find(title, list);
    	
    	if(index != -1) {
    		//start updating task
    	}
    	else {
    		//Print message saying the information is incorrect
    	}
    		
    }// we can also return an int to check if it updated correctly.
    
    public void completeEntry(String title) {
    	int index = find(title, list);
    	
    	if(index != -1) {
    		list.get(index).setStatus(3); //sets status to complete, we also prob gotta darken it out or something
    	} 
    	else {
    		//prints error
    	}
    }
    
    public String displayToDoList(ArrayList list) {// we'll prob have to return a string so that we can update it on the gui but i dont remember how that works
    	
    }
    
    public void printReport(ArrayList list) {
    	BufferedWriter fileWriter = new BufferedWriter(new FileWriter("TextfileName.txt"));
    	
    	fileWriter.write("stuff");
    	fileWriter.close();// we can implement this more when we talk about stuff
    }
    
    public void saveList(ArrayList list) {
    	BufferedWriter fileWriter = new BufferedWriter(new FileWriter("TextfileName.txt"));
    	String currentString;
    	
    	for(int i = 0; i < list.size(); i++) {
    		currentString = list.get(i).getDescription() + "\t";
    		currentString = currentString + list.get(i).getDueDate() + "\t";
    		currentString = currentString + list.get(i).getStatus() + "\t";		//so far im thinking about just separating info with tabs and entrys with lines
    		currentString = currentString + list.get(i).getPriority() + "\n";	//we can change this to something better if u guys have an idea
    		
    		fileWriter.write(currentString);
    	}
    	fileWriter.close();// we can implement this more when we talk about stuff
    }
    
    public void restoreList() {
    	Task currentTask;
    	String description;
    	String dueDate;
        int status;
        int priority;
        
        BufferedReader fileReader = new BufferedReader(new FileReader("TextFileName.txt"));
        
        //iterate through file line by line, parsing data with tabs
        	//set all variables to file's current line
        		//call currentTask's constructor and add the entry into the list.
        			//we gotta decide it it overwrites current list entries or adds it
        
    }
    
    public int find(String title, ArrayList list) {	// We can change the params later once we figure out how we'll search for tasks
    	int index = -1;
    	
    	for(int i = 0; i < list.size(); i++) {	//idk any algorithms that would help search names so im using a for loop for now
    		if(title.equals(list.get(i).description)) {
    			index = i;
    		}
    	}
    	
    	return index;	//returns -1 if the task isnt found
    }
	
}
