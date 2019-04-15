
/**
 * Write a description of class ToDoList here.
 *
 * @author Kevin McAllister
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
public class ToDoList
{
    
    // instance variables - replace the example below with your own
    public ArrayList<Task> list = new ArrayList();
    
    public String report;
    
    private JFrame frame;
    
    private JTextField descriptionField;
    
    private JTextField priorityField;

    private JTextField dueDateField;

    private JTextField textField;

    private JTextField textField_1;

    private JTextField textField_2;

    private JTextField textField_3;

    private JTextField textField_4;

    private JPanel rightPanel = new JPanel();

    private JButton btnRemove2;

    JTextArea txtareaDisplay = new JTextArea();
    
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


        updateReport("add", list.get(list.size()));

        /*
         * the GUI takes in all info: desc, priorit, dueDate, and status (NotStarted and InProgress)
         * so the params of this method might need to take in all values. unless u want to add then call a setStatus() and setPriority().
         */


        /*
         * the GUI takes in all info: desc, priorit, dueDate, and status (NotStarted and InProgress)
         * so the params of this method might need to take in all values. unless u want to add then call a setStatus() and setPriority().
         */

    }
    


    public void deleteTask(int index)
    {
        updateReport("delete", list.get(index));
        list.remove(index);
    }
    
    public void updateTask(int index, String dueDate, int priority, int status)
    {
        report += Calendar.MONTH + "/" + Calendar.DAY_OF_MONTH + "/" + Calendar.YEAR + " @ " + Calendar.HOUR_OF_DAY + ":" + Calendar.MINUTE + ":" + Calendar.SECOND + "- ";
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
    
    public void updateReport(String action, Task mutant)
    {
        report += Calendar.MONTH + "/" + Calendar.DAY_OF_MONTH + "/" + Calendar.YEAR + " @ " + Calendar.HOUR_OF_DAY + ":" + Calendar.MINUTE + ":" + Calendar.SECOND + "- ";
        if(action.equals("delete"))
        {
            report += "Task " + mutant.getDescription() + "removed from list.\n";
        }
        else if(action.equals("add"))
        {
            report += "Task " + mutant.getDescription() + "added to list.\n";
        }
    }

    /**

     * Create the application.

     */

    public void CreateGUI() {

        initialize();

        //addPanel();

        //removePanel();

        //updateEntry();

        //completePanel();

    }

    /**

     * Initialize the contents of the frame.

     */

    private void initialize() 
    {

        frame = new JFrame();

        frame.setBounds(100, 100, 688, 406);

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

     */

    public void addData(JTextField descTextBox, JTextField dueDateTextBox)
    {
        newTask(descTextBox.getText(),dueDateTextBox.getText());
		
		addPanel();	//just resets fields
		JOptionPane.showMessageDialog(null, "\"" + descTextBox.getText().trim() + "\" has been added.");
    }

    

    /**

     * This method is called when the user presses the 'Remove Entry' 

     * button to remove the entry from the list and remove the panel from 

     * the frame

     * 

     * @param panel the panel to pull data from

     */

    public void removeData(JPanel panel) {

        /*

         * =============================================

         * ADD CODE HERE TO TAKE INFORMATION ENTERED AND REMOVE FROM LIST

         * =============================================

         */

        

        //add code to clear fields after entry is removed
    	removePanel();	//resets the panel after pressing remove
		JOptionPane.showMessageDialog(null, "\"" + descriptionField.getText().trim() + "\" has been removed.");

    }

    

    public void updateData(JPanel panel) {

        /*

         * =============================================

         * ADD CODE HERE TO TAKE INFORMATION ENTERED AND UPDATE LIST

         * =============================================

         */
    	
    	updateEntry();
		JOptionPane.showMessageDialog(null, "\"" + descriptionField.getText().trim() + "\" has been updated.");

    }

    

    public void completeEntry(JPanel panel) {

        /*

         * =============================================

         * ADD CODE HERE TO TAKE INFORMATION ENTERED AND COMPLETE ENTRY ON LIST

         * =============================================

         */

        

        //add code to clear fields after item is completed and removed
    	completePanel();
		JOptionPane.showMessageDialog(null, "\"" + descriptionField.getText().trim() + "\" has been completed.");
    }

    

    public void print() {

        /*

         * =============================================

         * ADD CODE HERE TO PRINT REPORT TO FILE

         * =============================================

         */

    }

    

    public void save() {

        /*

         * =============================================

         * ADD CODE HERE TO SAVE TO FILE

         * =============================================

         */

    }

    

    public void load(JPanel panel) {

        /*

         * =============================================

         * ADD CODE HERE TO READ FROM FILE AND CHANGE panel.txtareaDisplay 

         * 

         * if(file exists) txtareaDisplay.setText("List loaded from list.txt")

         * else txtareaDisplay.setText("ERROR: list.txt does not exist, please add an entry to begin");

         * =============================================

         */

        txtareaDisplay.setText("hello");

    }

    

    /**

     * This method searches the list for an entry and outputs it

     * 

     * @param panel

     */

    public void search(JRadioButton descButton, /*JRadioButton dueDateButton,*/ JRadioButton prioButton, JTextField keyword) 
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
        /*else if(dueDateButton.isSelected() == true)		//Removing due date search function because different tasks could have same due date
        {
            for(int iterator = 0; iterator < list.size(); iterator++)
            {
                if(list.get(iterator).getDueDate().equals(keyword.getText()))
                {
                    itemIndex = iterator;
                }
            }
        }*/
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
            descriptionField.setText("item not found");
            dueDateField.setText("item not found");
            priorityField.setText("item not found");
        }
        else
        {
            descriptionField.setText(list.get(itemIndex).getDescription());
            dueDateField.setText(list.get(itemIndex).getDueDate());
            priorityField.setText(Integer.toString(list.get(itemIndex).getPriority()));
        }
    }

    



    /**

     * This method renders a JPanel with text fields when the user presses the

     * add entry button

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

        //JRadioButton rdbtnDueDate = new JRadioButton("Due Date");

        

        ButtonGroup searchType = new ButtonGroup();

        searchType.add(rdbtnDescription);

        searchType.add(rdbtnPriority);

        //searchType.add(rdbtnDueDate);

        /*
         * Original values incase we want to add it back:
         			rdbtnDescription.setBounds(81, 22, 79, 23);
        			rdbtnPriority.setBounds(162, 22, 65, 23);
         */

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

                search(rdbtnDescription, /*rdbtnDueDate,*/ rdbtnPriority, textField);

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

        

        textField_4 = new JTextField();

        textField_4.setEditable(false);

        entryPanel.add(textField_4);

        textField_4.setColumns(10);

        

        JButton btnRemove = new JButton("Remove Entry");

        btnRemove.setBounds(199, 222, 142, 23);

        btnRemove.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent e) 
            {

                removeData(rightPanel);

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

        btnSearch.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent arg0) 
            {

                search(rdbtnDescription, /*rdbtnDueDate,*/ rdbtnPriority, textField);

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

        

        JComboBox comboBoxStatus = new JComboBox();

        comboBoxStatus.setModel(new DefaultComboBoxModel(new String[] {"Not Started", "In Progress"}));

        comboBoxStatus.setEditable(true);

        entryPanel.add(comboBoxStatus);

        

        JButton btnRemove;

        btnRemove = new JButton("Update Entry");

        btnRemove.setBounds(199, 222, 142, 23);

        btnRemove.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent e) 
            {

                updateData(rightPanel);

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

                search(rdbtnDescription, /*rdbtnDueDate,*/ rdbtnPriority, textField);

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

        

        JComboBox comboBoxStatus = new JComboBox();

        comboBoxStatus.setModel(new DefaultComboBoxModel(new String[] {"Not Started", "In Progress"}));

        entryPanel.add(comboBoxStatus);

        

        JButton btnComplete;

        btnComplete = new JButton("Complete Entry");

        btnComplete.setBounds(199, 222, 142, 23);

        btnComplete.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent e) 
            {

                completeEntry(rightPanel);

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

    }

    

    public void loadPanel() {

        

        frame.remove(rightPanel);

        frame.repaint();

        

        rightPanel = new JPanel();

        rightPanel.setBounds(301, 24, 361, 319);

        frame.getContentPane().add(rightPanel);

        rightPanel.setLayout(null);

        

        txtareaDisplay = new JTextArea();

        load(rightPanel);

        txtareaDisplay.setEditable(false);

        txtareaDisplay.setBounds(0, 0, 361, 319);

        rightPanel.add(txtareaDisplay);

    }
}

	


   