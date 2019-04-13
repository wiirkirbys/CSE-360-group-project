
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class CreateGUI {

	private JFrame frame;
	private JTextField descriptionField;
	private JTextField priorityField;
	private JTextField dueDateField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateGUI window = new CreateGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CreateGUI() {
		initialize();
		//addPanel();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 688, 406);
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
		buttonPanel.add(btnRemove);
		
		JButton btnUpdate = new JButton("Update Entry");
		buttonPanel.add(btnUpdate);
		
		JButton btnComplete = new JButton("Complete an Entry");
		buttonPanel.add(btnComplete);
		
		JButton btnDisplay = new JButton("Display To-Do List");
		buttonPanel.add(btnDisplay);
		
		JButton btnPrint = new JButton("Print Report");
		buttonPanel.add(btnPrint);
		
		JButton btnSave = new JButton("Save To-Do List");
		buttonPanel.add(btnSave);
		
		JButton btnRestore = new JButton("Restore To-Do List");
		buttonPanel.add(btnRestore);
		
		JButton btnSaveAndQuit = new JButton("Save and Quit");
		buttonPanel.add(btnSaveAndQuit);
		
//		JPanel removePanel = new JPanel();
//		removePanel.setBounds(301, 24, 361, 343);
//		frame.getContentPane().add(removePanel);
		
	}
	
	/**
	 * This method is called when the user presses the submit button on the add
	 * panel to add the entry to the list and remove the panel from the frame
	 * 
	 * @param panel the panel to pull data from
	 */
	public void addData(JPanel panel) {
		/*
		 * =============================================
		 * ADD CODE HERE TO TAKE INFORMATION ENTERED AND ADD TO LIST
		 * =============================================
		 */
		removeFrame(panel);
	}
	
	/**
	 * This method remove 'panel' from the frame.
	 * 
	 * @param panel the panel to be removed
	 */
	public void removeFrame(JPanel panel) {
		frame.remove(panel);
		frame.repaint();
	}
	/**
	 * This method renders a JPanel with text fields when the user presses the
	 * add entry button
	 */
	public void addPanel() {

		JPanel addPanel = new JPanel();
		addPanel.setBounds(301, 24, 361, 343);
		frame.getContentPane().add(addPanel);
		addPanel.setLayout(null);
		
		JPanel entries = new JPanel();
		entries.setBounds(0, 0, 361, 300);
		addPanel.add(entries);
		entries.setLayout(new GridLayout(4, 2, 0, 0));
		
		JLabel descriptionLabel = new JLabel("Description");
		descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		entries.add(descriptionLabel);
		
		descriptionField = new JTextField();
		entries.add(descriptionField);
		descriptionField.setColumns(10);
		
		JLabel priorityLabel = new JLabel("Priority #");
		priorityLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		priorityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		entries.add(priorityLabel);
		
		priorityField = new JTextField();
		entries.add(priorityField);
		priorityField.setColumns(10);
		
		JLabel dueDateLabel = new JLabel("Due Date");
		dueDateLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dueDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		entries.add(dueDateLabel);
		
		dueDateField = new JTextField();
		entries.add(dueDateField);
		dueDateField.setColumns(10);
		
		JLabel statusLabel = new JLabel("Status");
		statusLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		entries.add(statusLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Not Started", "In Progress", "Completed"}));
		entries.add(comboBox);
		
		JButton btnSubmitEntry = new JButton("Submit");
		btnSubmitEntry.setBounds(228, 311, 133, 23);
		btnSubmitEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addData(addPanel);
			}
		});
		addPanel.add(btnSubmitEntry);

		frame.revalidate();
		frame.repaint();
		
	}
}



