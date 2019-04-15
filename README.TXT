------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all he/she needs to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE: To Do List
PURPOSE OF PROJECT: To create a virtual, manipulable list that can be used to store and organize tasks that the user has to do.
VERSION or DATE: 1.0 4/12/2019
HOW TO START THIS PROJECT: First download the program files from the repository https://github.com/wiirkirbys/CSE-360-group-project. Once                            downloaded, store the program files in a folder that will be used to store and load the program executable                                from. Before launching the executable jar file, download the latest version of java from                                                  https://www.java.com/en/download/. Once downloaded, double click the jar executable and proceed to execute the                            program. 
AUTHORS: Alexander Bauman, Sonny Huynh, Kevin Mcallister, Tony Tipton
USER INSTRUCTIONS:

Installation
  After downloading the program, the user should receive a file similar to the file shown above. The program’s file type is an executable jar file. To run these files, the user must make sure their Java is up to date, or if the user does not have Java installed, to install the latest version, which can be found at https://www.java.com/en/download/.
The user must confirm that he or she has the latest release of Java, the user can then double click to run the executable jar file. If the file does not run, the user must right click the executable jar file and select “Open With”, scroll down and choose “Look for another app on this PC”. The user must then look through “Program Files” or “Program Files x86” for a folder named “Java”. Once the user is inside the Java folder, double click the folder with “jre” in its name, and then the folder “bin”. Select “javaw.exe” and hit “Open”.

Getting Started
  Once the program is launched, the program will not contain any entries. If it is the first time the user is running the program or if the user has not saved any entries, the user must add entries to the database before being able to remove, update, complete, display, or print. However, if there is a saved to-do list, the user may choose to load in entries saved from a previous session. These entries contain information, such as a description, due date, status, and a priority number in which 0 is the highest priority. Added entries may then be modified at the user’s will. Once the program contains entries, the user may press the remove, update, complete, display, and save entries buttons. These buttons perform actions synonymous with their names. For example, removing an entry simply removes the entry, and updating an entry allows the user to change information of such entry. Once the user is finished adding or modifying the to-do list, the user may save the list and press the “Exit Program” button to close the program.

User Interface Overview & Example Runs
  When the program is launched, the starting screen will show the various functions that the user can perform to the left side of the screen. To the right of the screen is the display panel used for displaying and sorting current items that are within the list.
There will be nine total buttons that the user can use to operate the program when pressed. The buttons functionality are as follows
Add an Entry - Adds an entry with given details to the To Do List with the initial state being the entry has been started yet.

Remove an Entry - Searches and removes an entry from the To Do List.
Update Entry - Searches and update a pre-existing entry within the To Do List.
Complete an Entry - Searches and marks an entry as being completed.
Display List - Iterates through the To Do List and displays the current To Do List on the  
               right side of the screen based on description, priority number, due date, or 
               status of the entry.
Print Report - Writes a text file to the same folder the program is in of all the actions done 
within the program thus far.
Save To Do List - Saves the current To Do List to a text file in the same folder the 
                  program is in.
Restore To Do List - Uploads a text file from a source folder with a To Do List to the 
                     program.
Exit Program - Closes and exits the program.

To add an entry, the user must click on the button “Add an Entry” on the left side of the screen.This will prompt a separate screen which will ask the user for details necessary for adding an entry to the To Do List. The four details that will be asked for the user to enter are

Description - a short message that details the entry that is unique to the entry
Priority number: a unique number assigned to the entry with 0 being the highest priority.
Due Date: The date that the entry must be completed by.
Status: The current status of the entry. The three possible assigned states are 
“Not Started”, “In Progress” or “Finished”

For error checking, If the user inputs a field that is not acceptable, a notification will alert the user to input the specified parameters for the field. For example, if the user does not enter an accepted status, a pop up text box will alert the user to enter an acceptable status.The error checking will also alert if the user inputs a date that is in the past.

To Remove an entry, the user must press the “Remove an Entry” on the left side of the screen. This will then prompt the user to search for the entry to delete depending on information of description, priority number, due date, or status given

To update an entry, the user must press the “Update Entry” button on the left side of the screen. This will then prompt the user to search for the entry to update information on. The user can update the description, priority number, due date, and the status of an entry if desired. If the user inputs the incorrect information for an entry, an error will be thrown as a pop up text box alerting the user.

To complete an entry the user must press “Complete an Entry” on the left side of the screen. The user then searches for the entry that is completed.

To Display the current To Do List on the user interface, the user must press “Display To Do List” on the left side of the screen. When pressed, the right side of the screen will be updated with the current To Do List

To Print a report, the user must press the “Print Report” button that will write to a text file to the same folder the program is in the actions performed thus far within the program.

To Save the To Do List for future use, the user must press the “Save To Do List” button on the left side of the screen

To Restore a To Do List, the user must press the “Restore To Do List” button which will read from a text file to recover a previously saved To Do List. To check if the previously saved To Do List is restored, the user must press “Display To Do List” button 


Restarting
	To restart the program, the user must press the “Exit Program” button. Once pressed, the user must reopen the program by double-clicking the shortcut executable downloaded at the downloaded location. If the user wishes to clear an entire list and start a new one they must either clear or delete the “todolist.txt” file, which can be found in the same directory as the program.

Ending the Program
  To exit the program, the user must press the “Exit Program” button to close and exit the program.


