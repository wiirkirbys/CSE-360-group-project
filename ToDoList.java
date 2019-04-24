package toDoList;

/**
 * <h1>To Do List </h1>
 * 
 * @author Alexander Bauman
 * @author Sonny Huynh
 * @author Kevin McAllister
 * @author Tony Tipton
 * 
 * @version Project 1.0
 * @since 4-22-2019
 * 
 * The To Do List program implements an application that creates a graphical user interface that allows the manipulation
 * of an array list of objects. The user may add an entry, remove an entry, update an entry, complete an entry, display the
 * currently stored entries, restore entries, load entries from a file, store the entries to a file, and save and exit the
 * program. Each operation is executed based on buttons within the graphical user interface which operates an array list which
 * stores the entries as objects.
 * <p>
 * 
 */

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.text.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
 * Class ToDoList declares and implements various operations of the array list as well as manages the
 * constructions of the graphical interface. The most notable operation defined are newTask() which adds an entry, 
 * removeTask()which removes an entry, updateTask()which updates an entry, completeTask() which changes the status 
 * to complete, displayList() which displays the current entries, updateReport() which updates the report, 
 * print() which prints the report, save() which saves the current entries to a file, load() which loads previously
 * saved entries to the program, and exit() which exits the program.
 */

public class ToDoList
{
    /**
     * The private variables used to store entries and create the graphical user interface
     */
	
    public ArrayList<Task> list = new ArrayList();
    public ArrayList<String> report = new ArrayList();
    private JFrame frame;
    private JTextField descriptionField;
    private JTextField priorityField;
    private JTextField dueDateField;
    private JTextField statusField;
    private JTextField textField;
    private JComboBox comboBoxStatus;
    private JPanel rightPanel = new JPanel();
    private JTextArea txtareaDisplay = new JTextArea();
    private int itemIndex = -1;
    private DateFormat day = new SimpleDateFormat("MM/dd/yyyy");
    private DateFormat time = new SimpleDateFormat("HH:mm");
    
    /**
     * Main launches the application by creating an ToDoList object,
     * then initializing the object with the function "ToDoList()".
     */

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            public void run() {

                try {

                    ToDoList window = new ToDoList();

                    window.frame.setVisible(true);

                } catch (Exception e) {

                    e.printStackTrace();

                }
            }
        });
    }

    /**
     * Constructor for objects of class ToDoList.
     * Calls the function CreateGUI().
     * 
     * @param There is no parameters
     * @return there is no return value
     */
    
    public ToDoList() {
    	
        CreateGUI();
        
    }

    /**
     * Creates a new task and adds it to the bottom of the list and adds it to the report.
     *
     * @param  description  a description of what the task entails
     * @param  dueDate  the date when the task is due
     * @return There is no return value.
     */
    
    public void newTask(String description, String dueDate) {
    	
        list.add(new Task(description, dueDate, list.size()));
        
        updateReport("add", list.get(list.size() - 1));
    }
    
    /**
     * Searches for a task by description or priority number and deletes the entry from the list.
     * Shifts the priority of each remaining entry to a new priority if needed and updates the report.
     * 
     * @param desc holds information about a task that is to be deleted.
     * @return There is no return value
     */
    
    public void deleteTask(String desc) {
    	
        int index = 0;
        
        for(int iterator = 0; iterator < list.size(); iterator++) {
        	
            if(list.get(iterator).getDescription().equals(desc)) {
            	
                index = iterator;
                
            }
        }
        
        for(int iterator = 0; iterator < list.size(); iterator++) {
        	
            if(list.get(iterator).getPriority() > list.get(index).getPriority()) {
            	
                list.get(iterator).setPriority(list.get(iterator).getPriority() - 1);
                
            }
        }
        
        updateReport("delete", list.get(index));
        list.remove(index);
        
    }
    
    /**
     * This method searches for a task by priority number or description and updates task information with
     * the parameters passed. 
     * The method also changes the report and organizes the array list based on the information changed
     * 
     * @param desc contains information about the current task
     * @param dueDate stores the due date of the task
     * @param priority stores the priority number of the task
     * @param status holds the current status of the task
     * @return there is no return value
     */
    
    public void updateTask(String desc, String dueDate, int priority, int status) {
        
        if(priority <= 0) {
        	
            priority = 0;
            
        }
        
        else if(priority > list.size()) {
        	
            priority = list.size() - 1;
            
        }
        
        for(int iterator = 0; iterator < list.size(); iterator++) {
            
        	if(priority > list.get(itemIndex).getPriority()) {
                
        		if(list.get(iterator).getPriority() > list.get(itemIndex).getPriority() && list.get(iterator).getPriority() <= priority) {
        			
                    list.get(iterator).setPriority(list.get(iterator).getPriority() - 1);
                    
                }
            }
        	
            else {
            	
                if(list.get(iterator).getPriority() < list.get(itemIndex).getPriority() && list.get(iterator).getPriority() >= priority) {
                	
                    list.get(iterator).setPriority(list.get(iterator).getPriority() + 1);
                    
                }
            }
        }
        
        String reportStr = "";
        
        reportStr += "Task updated from description: " + list.get(itemIndex).getDescription();
        reportStr +=  ", due date: " + list.get(itemIndex).getDueDate();
        reportStr += ", priority: " + list.get(itemIndex).getPriority();
        reportStr += " and status: " + list.get(itemIndex).getStatus();
        
        list.get(itemIndex).setDescription(desc);
        list.get(itemIndex).setDueDate(dueDate);
        list.get(itemIndex).setPriority(priority);
        list.get(itemIndex).setStatus(status);
        
        reportStr += " to description: " + list.get(itemIndex).getDescription();
        reportStr += ", due date: " + list.get(itemIndex).getDueDate();
        reportStr += ", priority: " + list.get(itemIndex).getPriority();
        reportStr += " and status: " + list.get(itemIndex).getStatus() + ".\n";
        
        report.add(reportStr);
        
    }
    
    /**
     * This method updates a task to the status of being completed and removes it from
     * the array list and keeps the list organized. It also updates the report.
     * 
     * @param desc desc stores the information about the task.
     * @return there is no return value
     */
    
    public void completeTask(String desc) {
    	
        int index = 0;
        
        for(int iterator = 0; iterator < list.size(); iterator++) {
        	
            if(list.get(iterator).getDescription().equals(desc)) {
            	
                index = iterator;
                
            }
        }
        
        for(int iterator = 0; iterator < list.size(); iterator++) {
        	
            if(list.get(iterator).getPriority() > list.get(index).getPriority()) {
            	
                list.get(iterator).setPriority(list.get(iterator).getPriority() - 1);
                
            }
        }
        
        updateReport("complete", list.get(index));
        list.remove(index);
    }
    
    /**
     * This method iterates through the array list and prints out every entry's
     *  information, sorted by priority.
     * @return there is no return value
     */
    
    public void displayListByPriority() {
    	
    	Task[] tasks = new Task[list.size()];
    	
    	for(int iterator = 0; iterator < list.size(); iterator++) {
    		
    		Task currentTask = list.get(iterator);
    		tasks[currentTask.getPriority()] = currentTask;
    		
    	}
    	
        String display = "";
        
        for(int iterator = 0; iterator < list.size(); iterator++) {
        	
        	display += "Description: " + tasks[iterator].getDescription() + "\n";
            display += "Due Date: " + tasks[iterator].getDueDate() + "\n";
            display += "Priority: " + tasks[iterator].getPriority() + "\n";
            display += "Status: " + tasks[iterator].getStatus() + "\n";
            display += "\n";
        	
        }
        
        txtareaDisplay.setText(display);
    }
    
    /**
     * This method iterates through the array list and prints out every entry's
     *  information, sorted by priority.
     * @return there is no return value
     */
    
    public void displayListByDescription() {
    	
    	Task[] tasks = new Task[list.size()];
    	
    	for(int iterator = 0; iterator < list.size(); iterator++) {
    		
    		tasks[iterator] = list.get(iterator);
    		
    	}
    	
    	for(int iterator = 0; iterator < list.size(); iterator++) {
    		
    		for(int innerIter = iterator + 1; innerIter < list.size(); innerIter++) {
    			
    			if(tasks[iterator].getDescription().toLowerCase().compareTo(tasks[innerIter].getDescription().toLowerCase()) > 0) {
    				
    				Task tempTask = tasks[iterator];
    				tasks[iterator] = tasks[innerIter];
    				tasks[innerIter] = tempTask;
    				
    			}
    		}
    	}
    	
        String display = "";
        
        for(int iterator = 0; iterator < list.size(); iterator++) {
        	
        	display += "Description: " + tasks[iterator].getDescription() + "\n";
            display += "Due Date: " + tasks[iterator].getDueDate() + "\n";
            display += "Priority: " + tasks[iterator].getPriority() + "\n";
            display += "Status: " + tasks[iterator].getStatus() + "\n";
            display += "\n";
        	
        }
        
        txtareaDisplay.setText(display);
    }
   
    /**
     * This method is a helper method which aids in updating the report to the necessary information
     * 
     * @param action action compares the action taken
     * @param mutant holds the information about the task to be changed
     * @return there is no return value
     */
    
    public void updateReport(String action, Task mutant) {
    	
    	String currentDay = day.format(new Date());
    	String currentTime = time.format(new Date());
    	
        if(action.equals("delete")) {
        	
        	report.add("Task " + mutant.getDescription() + " from list" + " on " 
        			+ currentDay + " at " + currentTime + ".\n");
        	
        }
        
        else if(action.equals("add")) {
        	
            report.add("Task " + mutant.getDescription() + ", with due date: "
            		+ mutant.getDueDate() + ", priority: " + mutant.getPriority()
            		+ ", and status: " + mutant.getStatus() + " added to list"
            		+ " on " + currentDay + " at " + currentTime + ".\n");
            
        }
        
        else if(action.equals("complete")) {
        	
            report.add("Task " + mutant.getDescription() + " completed and removed from list"
            		+ " on " + currentDay + " at " + currentTime + ".\n");
            
        }
    }
    
    /**
     * This method calls the print function to print the list into a text file.
     * @return there is no return value
     */
    
    public void print() {

    	File file = new File("report.txt");
    	if(file.delete());

    	try {
    		
			file.createNewFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			
	    	for(int iterator = 0; iterator < report.size(); iterator++) {
	    		
	    		bw.write(report.get(iterator));
	    		bw.newLine();
	    		
	    	}
			
	    	bw.close();
	    	
		} 
    	
    	catch (IOException e) {
    		
    		e.printStackTrace();
    		
		}
    }
    
    /**
     * This method stores the current array list into a file called "list.txt".
     * 
     * @return there is no return value
     */
    
    public void save() {
    	
    	File file = new File("list.txt");
    	
    	if(file.delete());
    	
    	try {
    		
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			
	    	writer.write(list.size() + "\n");
	    	
	    	for(int iterator = 0; iterator < list.size(); iterator++) {
	    		
	    		writer.write(list.get(iterator).getDescription() + "\n");
	    		writer.write(list.get(iterator).getDueDate() + "\n");
	    		writer.write(list.get(iterator).getPriority() + "\n");
	    		
	    		String statusStr = list.get(iterator).getStatus();
	    		int statusInt = -1;
	    		
	    		if(statusStr.equals("Not started")) {
	    			
	    			statusInt = 0;
	    			
	    		}
	    		
	    		else if(statusStr.equals("In Progress")) {
	    			
	    			statusInt = 1;
	    			
	    		}
	    		
	    		else if(statusStr.equals("Finished")) {
	    			
	    			statusInt = 2;
	    			
	    		}
	    		
	    		writer.write(statusInt + "\n");
	    		
	    	}
	    	
	    	String reportStr = "";
	    	
	    	for(int iterator = 0; iterator < report.size(); iterator++) {
	    		
	    		reportStr += report.get(iterator);
	    		
	    	}
	    	
	    	writer.write(reportStr);
	    	
	    	writer.close();
	    	
		} 
    	
    	catch (IOException e) {
    		
    		e.printStackTrace();
    		
		}
    }
    
    /**
     * This method loads a previously saved array list back into the program for further implementation.
     * 
     * @return there is no return value
     */
    
    public void load() {
    	
    	File file = new File("list.txt");
    	BufferedReader br;
    	
		try {
			
			br = new BufferedReader(new FileReader(file));
			int numEntries = -1;
			if((numEntries = Integer.parseInt(br.readLine())) != -1);
			
			
			for(int iterator = 0; iterator < numEntries; iterator++) {
				
				String description = br.readLine();
				String dueDate = br.readLine();
				int priority = Integer.parseInt(br.readLine());
				Task newTask = new Task(description, dueDate, priority);
				newTask.setStatus(Integer.parseInt(br.readLine()));
				list.add(newTask);
				
			}
			
			String line; 

	    	while ((line = br.readLine()) != null) {
	    		
	    	    report.add(line);
	    	    
	    	}
	    	
	    	txtareaDisplay.setText("To-do list loaded from list.txt");
	    	
	    	br.close();
	    	
		}
		
		catch (FileNotFoundException e) {
			
			txtareaDisplay.setText("Unable to load to-do list from list.txt");
			
		}
		
		catch (IOException e) {
			
		}   
    }

    /**
     * This method create the graphical user interface application.
     * Calls the initialize() function.
     * 
     * @return there is no return value
     */

    public void CreateGUI() {

        initialize();
        
    }

    /**
     * This method initialize the contents of the frame.
     * 
     * @return there is no return value
     */

    private void initialize() {

        frame = new JFrame();

        frame.setBounds(100, 100, 720, 500);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().setLayout(null);
        

        JPanel buttonPanel = new JPanel();

        buttonPanel.setBounds(31, 24, 238, 300);

        frame.getContentPane().add(buttonPanel);

        buttonPanel.setLayout(new GridLayout(9, 1, 0, 0));


        JButton btnAdd = new JButton("Add an Entry");

        btnAdd.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                addPanel();

            }
        });

        buttonPanel.add(btnAdd);


        JButton btnRemove = new JButton("Remove an Entry");

        btnRemove.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                removePanel();

            }
        });

        buttonPanel.add(btnRemove);


        JButton btnUpdate = new JButton("Update Entry");

        btnUpdate.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {

                updateEntry();

            }
        });

        buttonPanel.add(btnUpdate);


        JButton btnComplete = new JButton("Complete an Entry");

        btnComplete.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {

                completePanel();

            }
        });

        buttonPanel.add(btnComplete);


        JButton btnDisplay = new JButton("Display To-Do List");

        btnDisplay.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {

                displayPanel();

            }
        });

        buttonPanel.add(btnDisplay);


        JButton btnPrint = new JButton("Print Report");

        btnPrint.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {

                printPanel();

            }
        });

        buttonPanel.add(btnPrint);


        JButton btnSave = new JButton("Save To-Do List");

        btnSave.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {

                savePanel();

            }
        });

        buttonPanel.add(btnSave);


        JButton btnRestore = new JButton("Restore To-Do List");

        btnRestore.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {

                loadPanel();

            }
        });

        buttonPanel.add(btnRestore);


        JButton btnRestart = new JButton("Restart List");

        btnRestart.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {

                restartPanel();

            }
        });

        buttonPanel.add(btnRestart);

    }

    

    /**
     * This method is called when the user presses the submit button on the add
     * panel to add the entry to the list and remove the panel from the frame
     * 
     * @param panel the panel to pull data from
     * @return there is no return value
     */

    public void addData(JTextField descTextBox, JTextField dueDateTextBox) {
    	
        String desc = descTextBox.getText().trim();
        String date = dueDateTextBox.getText().trim();
        
        if(desc.equals("")) {
        	
        	JOptionPane.showMessageDialog(null, "Please enter a description.");
        	
        }
        
        else if (date.equals("")) {
        	
        	JOptionPane.showMessageDialog(null, "Please enter a due date.");
        	
        }
        
        else {
        	
        	boolean isUnique = uniqueDescription(desc, -1);
        	
        	if(isUnique) {
        		
        		newTask(desc, dueDateTextBox.getText().trim());
                
                addPanel();
                
                JOptionPane.showMessageDialog(null, "\"" + desc + "\" has been added.");
                
        	}
        	
        	else {
        		
        		JOptionPane.showMessageDialog(null, "That description is already"
        				+ " in use. Please enter a unique one.");
        		
        	}
        }  
    }

    /**
     * This method searches through the list when adding or updating an entry
     * to check if the description is unique.
     * 
     * @param description the description that the user is trying to enter
     * @param updateIndex index of the entry that is being updated, -1 if the 
     * user is adding a new entry
     * @return unique boolean value that will be true if description is unique
     */
    
    public boolean uniqueDescription(String description, int updateIndex) {
    	
    	boolean unique = true;
    	
    	for(int iterator = 0; iterator < list.size(); iterator++) {
    		
    		if(list.get(iterator).getDescription().equals(description) && updateIndex != iterator) {
    			
    			unique = false;
    			
    		}
    	}
    	
    	return unique;
    	
    }

    /**
     * This method is called when the user presses the 'Remove Entry' 
     * button to remove the entry from the list and remove the panel from 
     * the frame
     * 
     * @param descTextBox the textBox to check if the searched value is found
     * @return there is no return value
     */
    
    public void removeData(JTextField descTextBox) {
    	
        String desc = descTextBox.getText().trim();
        
        removePanel();
        
        if(desc.equals("Item Not Found."))
        	
            JOptionPane.showMessageDialog(null, "Item not found. Please search for an existing entry.");
        
        else if(desc.equals(""))
        	
            JOptionPane.showMessageDialog(null, "Please search for an existing entry first.");
        
        else {
        	
            deleteTask(descTextBox.getText().trim());
            JOptionPane.showMessageDialog(null, "\"" + desc + "\" has been removed.");
            
        }
    }

    /**
     * This method is called when the user updates information of a task.
     * This method checks whether
     * 
     * @param descTextBox 
     * @param dueTextBox
     * @param prioTextBox
     * @param statusTextBox
     */

    public void updateData(JTextField descTextBox, JTextField dueTextBox, JTextField prioTextBox, JComboBox statusTextBox) 
    {
    	
        String desc = descTextBox.getText().trim();
        
        updateEntry();
        
        if(desc.equals("Item Not Found."))
        	
            JOptionPane.showMessageDialog(null, "Item not found. Please search for an existing entry.");
        
        else if(desc.equals(""))
        	
            JOptionPane.showMessageDialog(null, "Please search for an existing entry first.");
        
        else {
        	
        	boolean isUnique = uniqueDescription(desc, itemIndex);
        	
        	if(isUnique) {
        		
        		
        		try {
        			updateTask(desc, dueTextBox.getText().trim(), Integer.parseInt(prioTextBox.getText().trim()), statusTextBox.getSelectedIndex()); 
        			JOptionPane.showMessageDialog(null, "\"" + desc + "\" has been updated.");
        		}
        		catch(NumberFormatException e) {
        			JOptionPane.showMessageDialog(null, "Please enter an integer for the priority.");
        		}
                
                
        	}
        	
        	else {
        		
        		JOptionPane.showMessageDialog(null, "That description is already"
        				+ "in use. Please enter a unique one.");
        		
        	}
        }
    }


    /**
     * This method searches the list for an entry and displays its data in the relevant fields
     * 
     * @param panel
     * @return there is no return value
     */

    public void search(JRadioButton descButton, JRadioButton prioButton, JTextField keyword) {
    	
        itemIndex = -1;
        
        if(descButton.isSelected() == true) {
        	
            for(int iterator = 0; iterator < list.size(); iterator++) 
            {
                
            	if(list.get(iterator).getDescription().equals(keyword.getText())) {
            		
                    itemIndex = iterator;
                
            	}
            }
        }

        else if(prioButton.isSelected() == true) {
        	
        	try{
        		
        		for(int iterator = 0; iterator < list.size(); iterator++) {
        			
                    if(list.get(iterator).getPriority() == Integer.parseInt(keyword.getText())) {
                    	
                        itemIndex = iterator;
                    
                    }
                }
        	}
        	
        	catch(NumberFormatException e) {
        		
        		JOptionPane.showMessageDialog(null, "Please enter an integer "
        				+ "or search by description.");
        		
        	}
        }
        
        if(itemIndex == -1) {
        	
            descriptionField.setText("Item Not Found.");
            dueDateField.setText("Item Not Found.");
            priorityField.setText("Item Not Found.");
            
        }
        
        else {
        	
            descriptionField.setText(list.get(itemIndex).getDescription());
            dueDateField.setText(list.get(itemIndex).getDueDate());
            priorityField.setText(Integer.toString(list.get(itemIndex).getPriority()));
            statusField.setText(list.get(itemIndex).getStatus());  
            
        }
    }
    
    public void search(JRadioButton descButton, JRadioButton prioButton, JTextField keyword, boolean comboBoxSearch) {
    	
        itemIndex = -1;
        
        if(descButton.isSelected() == true) {
        	
            for(int iterator = 0; iterator < list.size(); iterator++) {
            	
                if(list.get(iterator).getDescription().equals(keyword.getText())) {
                	
                    itemIndex = iterator;
                
                }
            }
        }

        else if(prioButton.isSelected() == true) {
        	
        	try {
        		 
        		for(int iterator = 0; iterator < list.size(); iterator++) {
        			
	                if(list.get(iterator).getPriority() == Integer.parseInt(keyword.getText())) {
	                	
	                	itemIndex = iterator;
	                
	                }
                }
        	}
        	
        	catch(NumberFormatException e) {
        		
        		JOptionPane.showMessageDialog(null, "Please enter an integer "
        				+ "or search by description.");
        		
        	}
        }
       
        if(itemIndex == -1) {
        	
            descriptionField.setText("Item Not Found.");
            dueDateField.setText("Item Not Found.");
            priorityField.setText("Item Not Found.");
            
        }
        
        else {
        	
            descriptionField.setText(list.get(itemIndex).getDescription());
            dueDateField.setText(list.get(itemIndex).getDueDate());
            priorityField.setText(Integer.toString(list.get(itemIndex).getPriority()));

            if(list.get(itemIndex).getStatus().equals("In Progress")) {
                
            	comboBoxStatus.setSelectedIndex(1);
            	
            }
            
            else {
            	
                comboBoxStatus.setSelectedIndex(0);
                
            }
        }  
    }
    
    /**
     * This method searches the list for an entry and gives the user an option
     * to change its status to completed and remove it from the list.
     * 
     * @param panel
     * @return there is no return value
     */
    
    public void completeEntry(JTextField descTextBox, JTextField prioTextBox, JTextField dueTextBox) {
    	
        String desc = descTextBox.getText().trim();
        
        completePanel();
        
        if(desc.equals("Item Not Found."))
        	
            JOptionPane.showMessageDialog(null, "Item not found. Please search for an existing entry.");
        
        else if(desc.equals(""))
        	
            JOptionPane.showMessageDialog(null, "Please search for an existing entry first.");
        
        else {
        	
            JOptionPane.showMessageDialog(null, "\"" + desc + "\" has been updated.");
            completeTask(desc);
            
        }
    }

    /**
     * This method renders a JPanel with text fields when the user presses the
     * add entry button to allow for an entry to be added.
     * 
     * @return there is no return value
     */
    
    public void addPanel() {

        frame.remove(rightPanel);

        frame.repaint();


        rightPanel = new JPanel();

        rightPanel.setBounds(301, 24, 361, 343);

        frame.getContentPane().add(rightPanel);

        rightPanel.setLayout(null);


        JPanel entries = new JPanel();

        entries.setBounds(0, 0, 361, 300);

        rightPanel.add(entries);

        entries.setLayout(new GridLayout(4, 2, 0, 0));


        JLabel lblDescription = new JLabel("Description");

        lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));

        lblDescription.setHorizontalAlignment(SwingConstants.CENTER);

        entries.add(lblDescription);


        descriptionField = new JTextField();

        entries.add(descriptionField);

        descriptionField.setColumns(10);


        JLabel lblDueDate = new JLabel("Due Date");

        lblDueDate.setFont(new Font("Tahoma", Font.PLAIN, 14));

        lblDueDate.setHorizontalAlignment(SwingConstants.CENTER);

        entries.add(lblDueDate);


        dueDateField = new JTextField();

        entries.add(dueDateField);

        dueDateField.setColumns(10);

        JButton btnSubmitEntry = new JButton("Submit");

        btnSubmitEntry.setBounds(228, 311, 133, 23);

        btnSubmitEntry.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                addData(descriptionField, dueDateField);

            }
        });

        rightPanel.add(btnSubmitEntry);


        frame.revalidate();

        frame.repaint();
        
    }

    /**
     * This method renders a JPanel with text fields when the user presses the
     * remove entry button to allow for an entry to be removed.
     * 
     * @return there is no return value
     */

    public void removePanel() {

        frame.remove(rightPanel);

        frame.repaint();
        

        rightPanel = new JPanel();

        rightPanel.setBounds(301, 24, 361, 343);

        frame.getContentPane().add(rightPanel);

        rightPanel.setLayout(null);
        

        JLabel lblSearchForAn = new JLabel("Search For an Entry to Remove");

        lblSearchForAn.setHorizontalAlignment(SwingConstants.CENTER);

        lblSearchForAn.setFont(new Font("Tahoma", Font.PLAIN, 14));

        lblSearchForAn.setBounds(0, 0, 361, 15);

        rightPanel.add(lblSearchForAn);
        

        JLabel lblSearchBy = new JLabel("Search By:");

        lblSearchBy.setFont(new Font("Tahoma", Font.PLAIN, 12));

        lblSearchBy.setBounds(10, 25, 65, 14);

        rightPanel.add(lblSearchBy);
        

        JRadioButton rdbtnDescription = new JRadioButton("Description", true);

        JRadioButton rdbtnPriority = new JRadioButton("Priority");
        

        ButtonGroup searchType = new ButtonGroup();

        searchType.add(rdbtnDescription);

        searchType.add(rdbtnPriority);


        rdbtnDescription.setBounds(81, 22, 90, 23);

        rightPanel.add(rdbtnDescription);

        rdbtnPriority.setBounds(180, 22, 70, 23);

        rightPanel.add(rdbtnPriority);
        

        textField = new JTextField();

        textField.setBounds(10, 50, 243, 20);

        rightPanel.add(textField);

        textField.setColumns(10);
        

        JButton btnSearch = new JButton("Search");

        btnSearch.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {

                search(rdbtnDescription, rdbtnPriority, textField);

            }

        });

        btnSearch.setBounds(263, 49, 89, 23);

        rightPanel.add(btnSearch);


        JPanel displayPanel = new JPanel();

        displayPanel.setBounds(10, 81, 341, 251);

        rightPanel.add(displayPanel);

        displayPanel.setLayout(null);
   

        JPanel entryPanel = new JPanel();

        entryPanel.setBounds(0, 0, 341, 211);

        displayPanel.add(entryPanel);

        entryPanel.setLayout(new GridLayout(4, 2, 0, 0));
        

        JLabel lblDescription = new JLabel("Description");

        lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));

        lblDescription.setHorizontalAlignment(SwingConstants.CENTER);

        entryPanel.add(lblDescription);
        

        descriptionField = new JTextField();

        descriptionField.setEditable(false);

        entryPanel.add(descriptionField);

        descriptionField.setColumns(10);
        

        JLabel lblPriority = new JLabel("Priority #");

        lblPriority.setFont(new Font("Tahoma", Font.PLAIN, 14));

        lblPriority.setHorizontalAlignment(SwingConstants.CENTER);

        entryPanel.add(lblPriority);
        

        priorityField = new JTextField();

        priorityField.setEditable(false);

        entryPanel.add(priorityField);

        priorityField.setColumns(10);
        

        JLabel lblDueDate = new JLabel("Due Date");

        lblDueDate.setFont(new Font("Tahoma", Font.PLAIN, 14));

        lblDueDate.setHorizontalAlignment(SwingConstants.CENTER);

        entryPanel.add(lblDueDate);
        

        dueDateField = new JTextField();

        dueDateField.setEditable(false);

        entryPanel.add(dueDateField);

        dueDateField.setColumns(10);
        

        JLabel lblStatus = new JLabel("Status");

        lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));

        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);

        entryPanel.add(lblStatus);
        

        statusField = new JTextField();

        statusField.setEditable(false);

        entryPanel.add(statusField);

        statusField.setColumns(10);
        

        JButton btnRemove = new JButton("Remove Entry");

        btnRemove.setBounds(199, 222, 142, 23);

        btnRemove.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                removeData(descriptionField);

            }
        });

        displayPanel.add(btnRemove);


        frame.revalidate();

        frame.repaint();

    }

    /**
     * This method renders a JPanel with text fields when the user presses the
     * update entry button to allow for an entry to be updated.
     * 
     * @return there is no return value
     */

    public void updateEntry() {        

        frame.remove(rightPanel);

        frame.repaint();


        rightPanel = new JPanel();

        rightPanel.setBounds(301, 24, 361, 343);

        frame.getContentPane().add(rightPanel);

        rightPanel.setLayout(null);
                

        JLabel lblSearchForAn = new JLabel("Search For an Entry to Update");

        lblSearchForAn.setHorizontalAlignment(SwingConstants.CENTER);

        lblSearchForAn.setFont(new Font("Tahoma", Font.PLAIN, 14));

        lblSearchForAn.setBounds(0, 0, 361, 15);

        rightPanel.add(lblSearchForAn);
        

        JLabel lblSearchBy = new JLabel("Search By:");

        lblSearchBy.setFont(new Font("Tahoma", Font.PLAIN, 12));

        lblSearchBy.setBounds(10, 25, 65, 14);

        rightPanel.add(lblSearchBy);
        

        JRadioButton rdbtnDescription = new JRadioButton("Description", true);

        JRadioButton rdbtnPriority = new JRadioButton("Priority");
 
        ButtonGroup searchType = new ButtonGroup();

        searchType.add(rdbtnDescription);

        searchType.add(rdbtnPriority);
   

        rdbtnDescription.setBounds(81, 22, 90, 23);

        rightPanel.add(rdbtnDescription);

        rdbtnPriority.setBounds(180, 22, 70, 23);

        rightPanel.add(rdbtnPriority);
        

        textField = new JTextField();

        textField.setBounds(10, 50, 243, 20);

        rightPanel.add(textField);

        textField.setColumns(10);
        

        JButton btnSearch = new JButton("Search");
        

        btnSearch.setBounds(263, 49, 89, 23);

        rightPanel.add(btnSearch);
        

        JPanel displayPanel = new JPanel();

        displayPanel.setBounds(10, 81, 341, 251);

        rightPanel.add(displayPanel);

        displayPanel.setLayout(null);
        

        JPanel entryPanel = new JPanel();

        entryPanel.setBounds(0, 0, 341, 211);

        displayPanel.add(entryPanel);

        entryPanel.setLayout(new GridLayout(4, 2, 0, 0));
        

        JLabel lblDescription = new JLabel("Description");

        lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));

        lblDescription.setHorizontalAlignment(SwingConstants.CENTER);

        entryPanel.add(lblDescription);
        

        descriptionField = new JTextField();

        entryPanel.add(descriptionField);

        descriptionField.setColumns(10);
        

        JLabel lblPriority = new JLabel("Priority #");

        lblPriority.setFont(new Font("Tahoma", Font.PLAIN, 14));

        lblPriority.setHorizontalAlignment(SwingConstants.CENTER);

        entryPanel.add(lblPriority);
        

        priorityField = new JTextField();

        entryPanel.add(priorityField);

        priorityField.setColumns(10);
        

        JLabel lblDueDate = new JLabel("Due Date");

        lblDueDate.setFont(new Font("Tahoma", Font.PLAIN, 14));

        lblDueDate.setHorizontalAlignment(SwingConstants.CENTER);

        entryPanel.add(lblDueDate);
        

        dueDateField = new JTextField();

        entryPanel.add(dueDateField);

        dueDateField.setColumns(10);
        

        JLabel lblStatus = new JLabel("Status");

        lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));

        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);

        entryPanel.add(lblStatus);
        

        comboBoxStatus = new JComboBox();

        comboBoxStatus.setModel(new DefaultComboBoxModel(new String[] {"Not Started", "In Progress"}));

        comboBoxStatus.setEditable(true);

        entryPanel.add(comboBoxStatus);
        
        btnSearch.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
            	
                search(rdbtnDescription, rdbtnPriority, textField, true);
            
            }
        });
        

        JButton btnRemove;

        btnRemove = new JButton("Update Entry");

        btnRemove.setBounds(199, 222, 142, 23);

        btnRemove.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	
                updateData(descriptionField, dueDateField, priorityField, comboBoxStatus);

            }
        });

        displayPanel.add(btnRemove);


        frame.revalidate();

        frame.repaint();

    }

    /**
     * This method renders a JPanel with text fields when the user presses the
     * complete entry button to allow for an entry to be completed.
     * 
     * @return there is no return value
     */

    public void completePanel() {

        frame.remove(rightPanel);

        frame.repaint();
        

        rightPanel = new JPanel();

        rightPanel.setBounds(301, 24, 361, 343);

        frame.getContentPane().add(rightPanel);

        rightPanel.setLayout(null);
                

        JLabel lblSearchForAn = new JLabel("Search For an Entry to Complete");

        lblSearchForAn.setHorizontalAlignment(SwingConstants.CENTER);

        lblSearchForAn.setFont(new Font("Tahoma", Font.PLAIN, 14));

        lblSearchForAn.setBounds(0, 0, 361, 15);

        rightPanel.add(lblSearchForAn);
        

        JLabel lblSearchBy = new JLabel("Search By:");

        lblSearchBy.setFont(new Font("Tahoma", Font.PLAIN, 12));

        lblSearchBy.setBounds(10, 25, 65, 14);

        rightPanel.add(lblSearchBy);
        

        JRadioButton rdbtnDescription = new JRadioButton("Description", true);

        JRadioButton rdbtnPriority = new JRadioButton("Priority");
        

        ButtonGroup searchType = new ButtonGroup();

        searchType.add(rdbtnDescription);

        searchType.add(rdbtnPriority);
        

        rdbtnDescription.setBounds(81, 22, 90, 23);

        rightPanel.add(rdbtnDescription);

        rdbtnPriority.setBounds(180, 22, 70, 23);

        rightPanel.add(rdbtnPriority);
        

        textField = new JTextField();

        textField.setBounds(10, 50, 243, 20);

        rightPanel.add(textField);

        textField.setColumns(10);
        

        JButton btnSearch = new JButton("Search");

        btnSearch.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {

                search(rdbtnDescription, rdbtnPriority, textField /*1*/);

            }
        });

        btnSearch.setBounds(263, 49, 89, 23);

        rightPanel.add(btnSearch);


        JPanel displayPanel = new JPanel();

        displayPanel.setBounds(10, 81, 341, 251);

        rightPanel.add(displayPanel);

        displayPanel.setLayout(null);
        

        JPanel entryPanel = new JPanel();

        entryPanel.setBounds(0, 0, 341, 211);

        displayPanel.add(entryPanel);

        entryPanel.setLayout(new GridLayout(4, 2, 0, 0));
        

        JLabel lblDescription = new JLabel("Description");

        lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));

        lblDescription.setHorizontalAlignment(SwingConstants.CENTER);

        entryPanel.add(lblDescription);
        

        descriptionField = new JTextField();

        descriptionField.setEditable(false);

        entryPanel.add(descriptionField);

        descriptionField.setColumns(10);
        

        JLabel lblPriority = new JLabel("Priority #");

        lblPriority.setFont(new Font("Tahoma", Font.PLAIN, 14));

        lblPriority.setHorizontalAlignment(SwingConstants.CENTER);

        entryPanel.add(lblPriority);
        

        priorityField = new JTextField();

        priorityField.setEditable(false);

        entryPanel.add(priorityField);

        priorityField.setColumns(10);
        

        JLabel lblDueDate = new JLabel("Due Date");

        lblDueDate.setFont(new Font("Tahoma", Font.PLAIN, 14));

        lblDueDate.setHorizontalAlignment(SwingConstants.CENTER);

        entryPanel.add(lblDueDate);
        

        dueDateField = new JTextField();

        dueDateField.setEditable(false);

        entryPanel.add(dueDateField);

        dueDateField.setColumns(10);
        

        JLabel lblStatus = new JLabel("Status");

        lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));

        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);

        entryPanel.add(lblStatus);
        

        statusField = new JTextField();

        statusField.setEditable(false);

        entryPanel.add(statusField);

        statusField.setColumns(10);
        

        JButton btnComplete;

        btnComplete = new JButton("Complete Entry");

        btnComplete.setBounds(199, 222, 142, 23);

        btnComplete.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                completeEntry(descriptionField, dueDateField, priorityField);

            }
        });

        displayPanel.add(btnComplete);


        frame.revalidate();

        frame.repaint();

    }

    /**
     * This method renders a JPanel that display the current to-do list
     * when the display button is pressed.
     * 
     * @return there is no return value
     */

    public void displayPanel() {

        frame.remove(rightPanel);

        frame.repaint();
        

        rightPanel = new JPanel();

        rightPanel.setBounds(301, 24, 361, 319);

        frame.getContentPane().add(rightPanel);

        rightPanel.setLayout(null);


        JLabel lblDisplayBy = new JLabel("Display By:");
        
		lblDisplayBy.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblDisplayBy.setBounds(10, 5, 65, 14);
		
		rightPanel.add(lblDisplayBy);
		
		
		JRadioButton rdbtnPriority = new JRadioButton("Priority", true);
		
		JRadioButton rdbtnDescription = new JRadioButton("Description");
		
		
		ButtonGroup displayType = new ButtonGroup();
		
		displayType.add(rdbtnPriority);
		
		displayType.add(rdbtnDescription);
		
		
		rdbtnPriority.setBounds(81, 2, 72, 23);
		
		rightPanel.add(rdbtnPriority);
		
		rdbtnDescription.setBounds(155, 2, 92, 23);
		
		rightPanel.add(rdbtnDescription);
		
		
		txtareaDisplay = new JTextArea();
		
		txtareaDisplay.setEditable(false);

		
		JScrollPane displayScroll = new JScrollPane(txtareaDisplay);

		displayScroll.setBounds(0, 30, 361, 289);

		rightPanel.add(displayScroll);
		
		
		JButton btnDisplay = new JButton("Display");
		
		btnDisplay.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				if(rdbtnPriority.isSelected() == true) {
					
					displayListByPriority();
					
		        }

		        else {
		        	
		        	displayListByDescription();
		           
		        }
			}
		});
		
		btnDisplay.setBounds(259, 2, 92, 23);
		
		rightPanel.add(btnDisplay);
		
        displayListByPriority();

    }

    /**
     * This method prints the report and renders a panel informing the user 
     * when the print report button is pressed.
     * 
     * @return there is no return value
     */

    public void printPanel() {

        frame.remove(rightPanel);

        frame.repaint();


        rightPanel = new JPanel();

        rightPanel.setBounds(301, 24, 361, 319);

        frame.getContentPane().add(rightPanel);

        rightPanel.setLayout(null);
        

        txtareaDisplay = new JTextArea();

        txtareaDisplay.setText("Report printed to report.txt.");

        txtareaDisplay.setEditable(false);

        txtareaDisplay.setBounds(0, 0, 361, 319);

        rightPanel.add(txtareaDisplay);
        
        print();

    }

    /**
     * This method saves the list to a text file and renders a panel informing 
     * the user when the save list button is pressed.
     * 
     * @return there is no return value
     */

    public void savePanel() {

        frame.remove(rightPanel);

        frame.repaint();


        rightPanel = new JPanel();

        rightPanel.setBounds(301, 24, 361, 319);

        frame.getContentPane().add(rightPanel);

        rightPanel.setLayout(null);
        

        txtareaDisplay = new JTextArea();

        txtareaDisplay.setText("To-do list saved to list.txt");

        txtareaDisplay.setEditable(false);

        txtareaDisplay.setBounds(0, 0, 361, 319);

        rightPanel.add(txtareaDisplay);
        
        save();

    }

    /**
     * This method loads the list from a text file and renders a panel informing 
     * the user when the load list button is pressed.
     * 
     * @return there is no return value
     */

    public void loadPanel() {

        frame.remove(rightPanel);

        frame.repaint();


        rightPanel = new JPanel();

        rightPanel.setBounds(301, 24, 361, 319);

        frame.getContentPane().add(rightPanel);

        rightPanel.setLayout(null);


        txtareaDisplay = new JTextArea();

        txtareaDisplay.setEditable(false);

        txtareaDisplay.setBounds(0, 0, 361, 319);

        rightPanel.add(txtareaDisplay);
        
        load();

    }

    /**
     * This method renders a panel that gives the user the option to delete
     * the list and start with a new one.
     * 
     * @return there is no return value
     */
    
    public void restartPanel() {
    	
    	frame.remove(rightPanel);
		frame.repaint();
		
		rightPanel = new JPanel();
		rightPanel.setBounds(301, 24, 361, 319);
		frame.getContentPane().add(rightPanel);
		rightPanel.setLayout(null);
		
		JButton btnRestart = new JButton("Restart");
		
		btnRestart.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				File file = new File("list.txt");
		    	if(file.delete());
		    	file = new File("report.txt");
		    	if(file.delete());
		    	list.clear();
		    	report.clear();
		    	JOptionPane.showMessageDialog(null, "List successfully cleared!");
		    	
			}
		});
		
		btnRestart.setBounds(239, 74, 112, 31);
		rightPanel.add(btnRestart);
		
		JTextPane txtpnRestart = new JTextPane();
		txtpnRestart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtpnRestart.setText("Do you want to restart with a new\nto-do list? This cannot be undone.");
		txtpnRestart.setBounds(10, 11, 341, 52);
		rightPanel.add(txtpnRestart);
    }
}