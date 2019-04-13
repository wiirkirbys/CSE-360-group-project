
/**
 * Write a description of class ToDoList here.
 *
 * @author Kevin McAllister
 */
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
}
