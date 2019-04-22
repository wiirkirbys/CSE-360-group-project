
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
    public String report;
    private JFrame frame;
    private JTextField descriptionField;
    private JTextField priorityField;
    private JTextField dueDateField;
    private JTextField statusField;
    private JTextField textField;
    private JComboBox comboBoxStatus;
    private JPanel rightPanel = new JPanel();
    private JButton btnRemove2;
    private JTextArea txtareaDisplay = new JTextArea();
    
    /**
     * Launch the application.
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
     * Constructor for objects of class ToDoList
     * 
     * @param There is no parameters
     * @return there is no return value
     */
    
    public ToDoList()
    {
        CreateGUI();
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


        updateReport("add", list.get(list.size()-1));
    }
    
    /**
     * Searches for a task by description, priority number, or date and deletes the entry from the list.
     * Shifts the priority of each remaining entry to a new priority if needed and updates the report.
     * 
     * @param desc desc holds information about a task that is to be deleted.
     * @return There is no return value
     */
    
    public void deleteTask(String desc)
    {
        int index = 0;
        for(int iterator = 0; iterator < list.size(); iterator++)
        {
            if(list.get(iterator).getDescription().equals(desc))
            {
                index = iterator;
            }
        }
        // increases priority of all lower priority tasks by 1
        for(int iterator = 0; iterator < list.size(); iterator++)
        {
            if(list.get(iterator).getPriority() > list.get(index).getPriority())
            {
                list.get(iterator).setPriority(list.get(iterator).getPriority() - 1);
            }
        }
        updateReport("delete", list.get(index));
        list.remove(index);
        
    }
    
    /**
     * This method searches for the task by description, priority number, or date and 
     * updates the entry from the list. The method also changes the report and organizes the
     * array list based on the information changed
     * 
     * @param desc desc contains information about the current task
     * @param dueDate dueDate stores the due date of the task
     * @param priority priority stores the priority number of the task
     * @param status status holds the current status of the task
     * @return there is no return value
     */
    
    public void updateTask(String desc, String dueDate, int priority, int status)
    {
        int index = 0;
        for(int iterator = 0; iterator < list.size(); iterator++)
        {
            if(list.get(iterator).getDescription().equals(desc))
            {
                index = iterator;
            }
        }
        
        if(priority <= 1)
        {
            priority = 1;
        }
        else if(priority > list.size())
        {
            priority = list.size();
        }
        
        for(int iterator = 0; iterator < list.size(); iterator++)
        {
            if(priority > list.get(index).getPriority())
            {
                if(list.get(iterator).getPriority() > list.get(index).getPriority() && list.get(iterator).getPriority() <= priority)
                {
                    list.get(iterator).setPriority(list.get(iterator).getPriority() - 1);
                }
            }
            else
            {
                if(list.get(iterator).getPriority() < list.get(index).getPriority() && list.get(iterator).getPriority() >= priority)
                {
                    list.get(iterator).setPriority(list.get(iterator).getPriority() + 1);
                }
            }
        }
        
        //report += Calendar.MONTH + "/" + Calendar.DAY_OF_MONTH + "/" + Calendar.YEAR + " @ " + Calendar.HOUR_OF_DAY + ":" + Calendar.MINUTE + ":" + Calendar.SECOND + "- ";
        report += "Task " + list.get(index).getDescription()+" updated from due date: " + list.get(index).getDueDate();
        report += ", priority: " + list.get(index).getPriority();
        report += " and status: "+list.get(index).getStatus();
        list.get(index).setDueDate(dueDate);
        list.get(index).setPriority(priority);
        list.get(index).setStatus(status);
        report += "to due date: " + list.get(index).getDueDate();
        report += ", priority: " + list.get(index).getPriority();
        report += " and status: "+list.get(index).getStatus() + ".\n";
    }
    
    /**
     * This method updates a task to the status of being completed and removes it from
     * the array list and keeps the list organized. It also updates the report.
     * 
     * @param desc desc stores the information about the task.
     * @return there is no return value
     */
    
    public void completeTask(String desc)
    {
        int index = 0;
        for(int iterator = 0; iterator < list.size(); iterator++)
        {
            if(list.get(iterator).getDescription().equals(desc))
            {
                index = iterator;
            }
        }
        // increases priority of all lower priority tasks by 1
        for(int iterator = 0; iterator < list.size(); iterator++)
        {
            if(list.get(iterator).getPriority() > list.get(index).getPriority())
            {
                list.get(iterator).setPriority(list.get(iterator).getPriority() - 1);
            }
        }
        updateReport("complete", list.get(index));
        list.remove(index);
    }
    
    /**
     * This method iterates through the array list and prints out every entry's information
     * @return there is no return value
     */
    
    public void displayList()
    {
        String display = "";
        for(int iterator = 0; iterator < list.size(); iterator++)
        {
            display += "Description: " + list.get(iterator).getDescription()+"\n";
            display += "Due Date: " + list.get(iterator).getDueDate()+"\n";
            display += "Priority: " + list.get(iterator).getPriority()+"\n";
            display += "Status: " + list.get(iterator).getStatus()+"\n";
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
    
    public void updateReport(String action, Task mutant)
    {
        //report += Calendar.MONTH + "/" + Calendar.DAY_OF_MONTH + "/" + Calendar.YEAR + " @ " + Calendar.HOUR_OF_DAY + ":" + Calendar.MINUTE + ":" + Calendar.SECOND + "- ";
        if(action.equals("delete"))
        {
            report += "Task " + mutant.getDescription() + " removed from list.\n";
        }
        else if(action.equals("add"))
        {
            report += "Task " + mutant.getDescription() + " added to list.\n";
        } 
        else if(action.equals("complete"))
        {
            report += "Task " + mutant.getDescription() + " completed and removed from list.\n";
        }
    }
    
    /**
     * This method calls the print function to print the list.
     * @return there is no return value
     */
    
    public void print() 
    {
        System.out.println(report);
    }
    
    /**
     * This method stores the current array list into a file.
     * 
     * @return there is no return value
     */
    
    public void save() 
    {

    }
    
    /**
     * This method loads a previously saved array list back into the program for further implementation.
     * 
     * @return there is no return value
     */
    
    public void load()
    {
        
    }

    /**
     * This method create the graphical user interface application.
     * 
     * @return there is no return value
     */

    public void CreateGUI() {

        initialize();

        //addPanel();

        //removePanel();

        //updateEntry();

        //completePanel();

    }

    /**
     * This method initialize the contents of the frame.
     * 
     * @return there is no return value
     */

    private void initialize() 
    {

        frame = new JFrame();

        frame.setBounds(100, 100, 720, 500);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().setLayout(null);

        

        JPanel buttonPanel = new JPanel();

        buttonPanel.setBounds(31, 24, 238, 300);

        frame.getContentPane().add(buttonPanel);

        buttonPanel.setLayout(new GridLayout(9, 1, 0, 0));

        

        JButton btnAdd = new JButton("Add an Entry");

        btnAdd.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent e) 
            {

                addPanel();

            }

        });

        buttonPanel.add(btnAdd);

        

        JButton btnRemove = new JButton("Remove an Entry");

        btnRemove.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent e) 
            {

                removePanel();

            }

        });

        buttonPanel.add(btnRemove);

        

        JButton btnUpdate = new JButton("Update Entry");

        btnUpdate.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent arg0) 
            {

                updateEntry();

            }

        });

        buttonPanel.add(btnUpdate);

        

        JButton btnComplete = new JButton("Complete an Entry");

        btnComplete.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent arg0) 
            {

                completePanel();

            }

        });

        buttonPanel.add(btnComplete);

        

        JButton btnDisplay = new JButton("Display To-Do List");

        btnDisplay.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent arg0) 
            {

                displayPanel();

            }

        });

        buttonPanel.add(btnDisplay);

        

        JButton btnPrint = new JButton("Print Report");

        btnPrint.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent arg0) 
            {

                printPanel();

            }

        });

        buttonPanel.add(btnPrint);

        

        JButton btnSave = new JButton("Save To-Do List");

        btnSave.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent arg0) 
            {

                savePanel();

            }

        });

        buttonPanel.add(btnSave);

        

        JButton btnRestore = new JButton("Restore To-Do List");

        btnRestore.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent arg0) 
            {

                loadPanel();

            }

        });

        buttonPanel.add(btnRestore);

        

        JButton btnSaveAndQuit = new JButton("Save and Quit");

        btnSaveAndQuit.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent arg0) 
            {

                save();

                System.exit(0);

            }

        });

        buttonPanel.add(btnSaveAndQuit);

    }

    

    /**
     * This method is called when the user presses the submit button on the add
     * panel to add the entry to the list and remove the panel from the frame
     * 
     * @param panel the panel to pull data from
     * @return there is no return value
     */

    public void addData(JTextField descTextBox, JTextField dueDateTextBox)
    {
        String desc = descTextBox.getText().trim();
        newTask(desc, dueDateTextBox.getText().trim());
        
        addPanel(); //just resets fields
        JOptionPane.showMessageDialog(null, "\"" + desc + "\" has been added.");
    }

    

    /**
     * This method is called when the user presses the 'Remove Entry' 
     * button to remove the entry from the list and remove the panel from 
     * the frame
     * 
     * @param panel the panel to pull data from
     * @return there is no return value
     */

    
    public void removeData(JTextField descTextBox) 
    {
        String desc = descTextBox.getText().trim();
        
        removePanel();  //resets the panel after pressing remove
        
        if(desc.equals("Item Not Found."))
            JOptionPane.showMessageDialog(null, "Item not found. Please search for an existing entry.");
        else if(desc.equals(""))
            JOptionPane.showMessageDialog(null, "Please search for an existing entry first.");
        else
        {
            deleteTask(descTextBox.getText().trim());
            JOptionPane.showMessageDialog(null, "\"" + desc + "\" has been removed.");
        }

    }

    

    public void updateData(JTextField descTextBox, JTextField dueTextBox, JTextField prioTextBox, JComboBox statusTextBox) 
    {
        String desc = descTextBox.getText().trim();
        
        updateEntry();
        
        if(desc.equals("Item Not Found."))
            JOptionPane.showMessageDialog(null, "Item not found. Please search for an existing entry.");
        else if(desc.equals(""))
            JOptionPane.showMessageDialog(null, "Please search for an existing entry first.");
        else
        {
            JOptionPane.showMessageDialog(null, "\"" + desc + "\" has been updated.");
            updateTask(desc, dueTextBox.getText().trim(), Integer.parseInt(prioTextBox.getText().trim()), statusTextBox.getSelectedIndex()); 
        }
    }


    /**
     * This method searches the list for an entry and displays its data in the relevant fields
     * 
     * @param panel
     * @return there is no return value
     */

    public void search(JRadioButton descButton, JRadioButton prioButton, JTextField keyword) 
    {
        int itemIndex = -1;
        if(descButton.isSelected() == true)
        {
            for(int iterator = 0; iterator < list.size(); iterator++)
            {
                if(list.get(iterator).getDescription().equals(keyword.getText()))
                {
                    itemIndex = iterator;
                }
            }
        }

        else if(prioButton.isSelected() == true)
        {
            for(int iterator = 0; iterator < list.size(); iterator++)
            {
                if(list.get(iterator).getPriority() == Integer.parseInt(keyword.getText()))
                {
                    itemIndex = iterator;
                }
            }
        }
        if(itemIndex == -1)
        {
            descriptionField.setText("Item Not Found.");
            dueDateField.setText("Item Not Found.");
            priorityField.setText("Item Not Found.");
        }
        else
        {
            descriptionField.setText(list.get(itemIndex).getDescription());
            dueDateField.setText(list.get(itemIndex).getDueDate());
            priorityField.setText(Integer.toString(list.get(itemIndex).getPriority()));
            statusField.setText(list.get(itemIndex).getStatus());  
        }
    }
    
    public void search(JRadioButton descButton, JRadioButton prioButton, JTextField keyword, boolean comboBoxSearch)  //boolean designates whether it updates the status or not 
    {
        int itemIndex = -1;
        if(descButton.isSelected() == true)
        {
            for(int iterator = 0; iterator < list.size(); iterator++)
            {
                if(list.get(iterator).getDescription().equals(keyword.getText()))
                {
                    itemIndex = iterator;
                }
            }
        }

        else if(prioButton.isSelected() == true)
        {
            for(int iterator = 0; iterator < list.size(); iterator++)
            {
                if(list.get(iterator).getPriority() == Integer.parseInt(keyword.getText()))
                {
                    itemIndex = iterator;
                }
            }
        }
        if(itemIndex == -1)
        {
            descriptionField.setText("Item Not Found.");
            dueDateField.setText("Item Not Found.");
            priorityField.setText("Item Not Found.");
        }
        else
        {
            descriptionField.setText(list.get(itemIndex).getDescription());
            dueDateField.setText(list.get(itemIndex).getDueDate());
            priorityField.setText(Integer.toString(list.get(itemIndex).getPriority()));

             if(list.get(itemIndex).getStatus().equals("In Progress")) {    //if its a combo box, checks if its the second choice. else it just shows the first option as default.
                comboBoxStatus.setSelectedIndex(1);
            }
            else
            {
                comboBoxStatus.setSelectedIndex(0);
            }
        }
    }
    
    public void completeEntry(JTextField descTextBox, JTextField prioTextBox, JTextField dueTextBox)
    {
        String desc = descTextBox.getText().trim();
        
	completePanel();
        //updateEntry();
        
        if(desc.equals("Item Not Found."))
            JOptionPane.showMessageDialog(null, "Item not found. Please search for an existing entry.");
        else if(desc.equals(""))
            JOptionPane.showMessageDialog(null, "Please search for an existing entry first.");
        else
        {
            JOptionPane.showMessageDialog(null, "\"" + desc + "\" has been updated.");
            updateTask(desc, dueTextBox.getText().trim(), Integer.parseInt(prioTextBox.getText().trim()), 2);
            completeTask(desc);
        }
    }

    



    /**
     * This method renders a JPanel with text fields when the user presses the
     * add entry button
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

        btnSubmitEntry.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent e) 
            {

                addData(descriptionField, dueDateField);

            }

        });

        rightPanel.add(btnSubmitEntry);



        frame.revalidate();

        frame.repaint();

        

    }


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

        btnSearch.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent arg0) 
            {

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

        btnRemove.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent e) 
            {

                removeData(descriptionField);

            }

        });

        displayPanel.add(btnRemove);

        

        frame.revalidate();

        frame.repaint();

    }



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

        //JRadioButton rdbtnDueDate = new JRadioButton("Due Date");

        

        ButtonGroup searchType = new ButtonGroup();

        searchType.add(rdbtnDescription);

        searchType.add(rdbtnPriority);

        //searchType.add(rdbtnDueDate);

        

        rdbtnDescription.setBounds(81, 22, 90, 23);

        rightPanel.add(rdbtnDescription);

        rdbtnPriority.setBounds(180, 22, 70, 23);

        rightPanel.add(rdbtnPriority);

        //rdbtnDueDate.setBounds(229, 22, 79, 23);

        //rightPanel.add(rdbtnDueDate);

        

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
        
        btnSearch.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent arg0) 
            {

                search(rdbtnDescription, rdbtnPriority, textField, true);

            }

        });

        

        JButton btnRemove;

        btnRemove = new JButton("Update Entry");

        btnRemove.setBounds(199, 222, 142, 23);

        btnRemove.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent e) 
            {

                updateData(descriptionField, dueDateField, priorityField, comboBoxStatus);

            }

        });

        displayPanel.add(btnRemove);

        

        frame.revalidate();

        frame.repaint();

    }

    

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

        //JRadioButton rdbtnDueDate = new JRadioButton("Due Date");

        

        ButtonGroup searchType = new ButtonGroup();

        searchType.add(rdbtnDescription);

        searchType.add(rdbtnPriority);

        //searchType.add(rdbtnDueDate);

        

        rdbtnDescription.setBounds(81, 22, 90, 23);

        rightPanel.add(rdbtnDescription);

        rdbtnPriority.setBounds(180, 22, 70, 23);

        rightPanel.add(rdbtnPriority);

        //rdbtnDueDate.setBounds(229, 22, 79, 23);

        //rightPanel.add(rdbtnDueDate);

        

        textField = new JTextField();

        textField.setBounds(10, 50, 243, 20);

        rightPanel.add(textField);

        textField.setColumns(10);

        

        JButton btnSearch = new JButton("Search");

        btnSearch.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent arg0) 
            {

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

        btnComplete.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent e) 
            {

                completeEntry(descriptionField, dueDateField, priorityField);

            }

        });

        displayPanel.add(btnComplete);

        

        frame.revalidate();

        frame.repaint();

    }

    

    public void displayPanel() {

        

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
        displayList();

    }

    

    public void printPanel() {

        

        frame.remove(rightPanel);

        frame.repaint();

        

        rightPanel = new JPanel();

        rightPanel.setBounds(301, 24, 361, 319);

        frame.getContentPane().add(rightPanel);

        rightPanel.setLayout(null);

        

        txtareaDisplay = new JTextArea();

        txtareaDisplay.setText("Report printed to report.txt");

        txtareaDisplay.setEditable(false);

        txtareaDisplay.setBounds(0, 0, 361, 319);

        rightPanel.add(txtareaDisplay);
        
        print();

    }

    

    public void savePanel() {

        

        frame.remove(rightPanel);

        frame.repaint();

        

        rightPanel = new JPanel();

        rightPanel.setBounds(301, 24, 361, 319);

        frame.getContentPane().add(rightPanel);

        rightPanel.setLayout(null);

        

        txtareaDisplay = new JTextArea();

        txtareaDisplay.setText("Report saved to list.txt");

        txtareaDisplay.setEditable(false);

        txtareaDisplay.setBounds(0, 0, 361, 319);

        rightPanel.add(txtareaDisplay);
        
        save();

    }

    

    public void loadPanel() {

        

        frame.remove(rightPanel);

        frame.repaint();

        

        rightPanel = new JPanel();

        rightPanel.setBounds(301, 24, 361, 319);

        frame.getContentPane().add(rightPanel);

        rightPanel.setLayout(null);

        

        txtareaDisplay = new JTextArea();

        //load(rightPanel);

        txtareaDisplay.setEditable(false);

        txtareaDisplay.setBounds(0, 0, 361, 319);

        rightPanel.add(txtareaDisplay);
        
        load();

    }
}

    


   
