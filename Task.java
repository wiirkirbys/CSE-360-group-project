
/**
 * A task object that contains all relevent data.
 * Said data includes task description, priority, completion status, and due date.
 * 
 * @author Kevin McAllister
 */
public class Task
{
    private String description;
    private String dueDate;
    private int status;
    private int priority;
    /**
     * Constructor for objects of class Task.
     * @param desc the description of the new task.
     * @param due the due date of the new task.
     * @param prio the priority of the new task. Should always be the lowest.
     */
    public Task(String desc, String due, int prio)
    {
        description = desc;
        dueDate = due;
        priority = prio+1; //add 1 because it starts at 0 otherwise
        status = 0;
    }

    /**
     * Sets priority to the given value
     *
     * @param prio the new priority to be set
     */
    public void setPriority(int newPrio)
    {
        priority = newPrio;
    }
    
    /**
     * returns priority
     *
     * @return priority the current priority of the task
     */
    public int getPriority()
    {
        return priority;
    }
    
    /**
     * Sets completion status to the given value
     *
     * @param prio the new completion status to be set
     */
    public void setStatus(int newStatus)
    {
        status = newStatus;
    }
    
    /**
     * returns completion status
     *
     * @return status the current completion status of the task
     */
    public String getStatus()
    {
        if(status == 0)
        {
            return "Not started";
        }
        else if(status == 1)
        {
            return "In Progress";
        }
        else
        {
            return "Completed";
        }
    }
    
    /**
     * sets due date
     *
     * @param dueDate the new due date of the task to be set
     */
    public void setDueDate(String newDueDate)
    {
        dueDate = newDueDate;
    }
    
    /**
     * returns due date
     *
     * @return dueDate the due date of the task
     */
    public String getDueDate()
    {
        return dueDate;
    }
    
    /**
     * returns description 
     *
     * @return status the current description of the task
     */
    public String getDescription()
    {
        return description;
    }
}
