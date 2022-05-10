package com;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class Menu extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//start the menu and set the style of dialog box to Windows style, report error of exception occur
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					Menu frame = new Menu();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		//create a new phonebook linked list, node is Contact type (see Contact class)
		LinkedList<Contact> phonebook = new LinkedList<>();

		//set the frame and content panel of the dialog box
		this.setTitle("Phone Book Management System");
		this.setIconImage(new ImageIcon(Objects.requireNonNull(Menu.class.getResource("/com/assets/contact (1).png"))).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1220, 685);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel bannerPanel = new JPanel();
		bannerPanel.setBounds(10, 10, 1196, 120);
		contentPane.add(bannerPanel);
		bannerPanel.setLayout(null);

		//image beside the title of menu
		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(Objects.requireNonNull(Menu.class.getResource("/com/assets/contact (1).png"))));
		icon.setBounds(50, 10, 100, 100);
		bannerPanel.add(icon);

		//title of the menu
		JLabel titleOfSystem = new JLabel("     Phone Book Management System");
		titleOfSystem.setFont(new RobotoFont(51).boldRoboto());
		titleOfSystem.setBounds(134, 10, 901, 100);
		bannerPanel.add(titleOfSystem);

		JPanel showPanel = new JPanel();
		showPanel.setBounds(250, 140, 956, 504);
		contentPane.add(showPanel);
		showPanel.setLayout(null);

		//scroll pane of table
		JScrollPane tablePanel = new JScrollPane();
		tablePanel.setBounds(10, 70, 936, 424);
		showPanel.add(tablePanel);

		//set JTable and table model
		TableModel tableModel = new TableModel(phonebook);
		JTable table = new JTable(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setRowSelectionAllowed(true);
		tablePanel.setViewportView(table);
		table.setAutoCreateRowSorter(true);
		table.setFont(new RobotoFont(12).lightRoboto());
		table.setRowHeight(20);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//define row sorter so that table can provide search and sort function
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tableModel);
		table.setRowSorter(rowSorter);
		//when first time open then import data from auto-save txt file in \src\com\save data
		try{
			Operation operation = new Operation(phonebook);
			operation.importContact(".\\src\\com\\save data\\autosave.txt",tableModel);
			//after import refresh table
			tableModel.setRowCount();
			tableModel.fireTableStructureChanged();
		} catch (Exception ignored) {

		}

		JPanel operationPanel = new JPanel();
		operationPanel.setBounds(10, 140, 230, 504);
		contentPane.add(operationPanel);
		operationPanel.setLayout(null);

		//create new button
		JButton createNewButton = new JButton("Create New");
		createNewButton.setToolTipText("Create a new contact.");
		createNewButton.setFont(new RobotoFont(20).mediumRoboto());
		createNewButton.setBounds(10, 10, 210, 45);
		operationPanel.add(createNewButton);
		//if clicked, then open create dialog box
		createNewButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				CreateDialogBox.main(phonebook,tableModel);
			}
		});

		//view button
		JButton viewButton = new JButton("View");
		viewButton.setToolTipText("View a contact.");
		viewButton.setFont(new RobotoFont(20).mediumRoboto());
		viewButton.setBounds(10, 65, 210, 45);
		operationPanel.add(viewButton);
		//if row is selected and view button is clicked, get the id of the selected row and open their respective detail dialog box
		viewButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//get id of selected row
				int selected_row = table.getSelectedRow();
				int id_column = 0;
				String row_id = (String) table.getValueAt(selected_row,id_column);
				DetailDialogBox.main(phonebook, Integer.parseInt(row_id));
			}
		});

		//edit button
		JButton editButton = new JButton("Edit");
		editButton.setToolTipText("Edit an existing contact.");
		editButton.setFont(new RobotoFont(20).mediumRoboto());
		editButton.setBounds(10, 120, 210, 45);
		operationPanel.add(editButton);
		//if row is selected and edit button is clicked, get the id of the selected row and open their respective edit dialog box
		editButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//get id of selected row
				int selected_row = table.getSelectedRow();
				int id_column = 0;
				String row_id = (String) table.getValueAt(selected_row,id_column);
				EditDialogBox.main(phonebook,Integer.parseInt(row_id), tableModel);
			}
		});

		//delete button
		JButton deleteButton = new JButton("Delete");
		deleteButton.setToolTipText("Delete an unwanted contact.");
		deleteButton.setFont(new RobotoFont(20).mediumRoboto());
		deleteButton.setBounds(10, 175, 210, 45);
		operationPanel.add(deleteButton);
		//if row is selected and delete button is clicked, get the id of the selected row and confirm with users, if yes then delete the contact and refresh the table
		deleteButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//get id of selected row
				int selected_row = table.getSelectedRow();
				int id_column = 0;
				int row_id = Integer.parseInt((String) table.getValueAt(selected_row,id_column));
				//show confirm message box
				int deleteDialog = JOptionPane.showConfirmDialog(null,"Are you sure want to delete "+phonebook.get(row_id).getName()+"?", "Delete Contact",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
				try{
					if (deleteDialog == JOptionPane.OK_OPTION){ //if user confirm
						//delete the selected contact by using deleteContact from operation class and refresh the table
						Operation operation = new Operation(phonebook);
						operation.deleteContact(row_id);
						tableModel.setRowCount();
						tableModel.fireTableStructureChanged();
					}
				} catch (Exception ex) {
					//if failed then inform user
					JOptionPane.showConfirmDialog(null,"There is an error when delete the file. Please try again.","Warning",JOptionPane.OK_OPTION,JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		//delete all button
		JButton deleteAllButton = new JButton("Delete All");
		deleteAllButton.setToolTipText("Delete all contacts.");
		deleteAllButton.setFont(new RobotoFont(20).mediumRoboto());
		deleteAllButton.setBounds(10, 230, 210, 45);
		operationPanel.add(deleteAllButton);
		//if delete all button is clicked
		deleteAllButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (phonebook.size() < 1){
					//if there are no contact
					JOptionPane.showMessageDialog(null,"There are no contacts to delete.");
				}else {
					//if there are contact then show a message dialog box to confirm with user
					int deleteAllDialog = JOptionPane.showConfirmDialog(null,"Are you sure want to delete all contacts?", "Success!",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
					if (deleteAllDialog == JOptionPane.OK_OPTION){ //if user confirm
						//delete all contact by using deleteAllContact in operation class
						Operation operation = new Operation(phonebook);
						operation.deleteAllContact();
						tableModel.setRowCount();
						//refresh JTable in menu
						tableModel.fireTableStructureChanged();
						//message dialog box to inform contact is deleted
						JOptionPane.showMessageDialog(null,"All contacts are deleted.", "Delete Contact",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});

		//import button
		JButton importButton = new JButton("Import");
		importButton.setToolTipText("Import a contact file.");
		importButton.setFont(new RobotoFont(20).mediumRoboto());
		importButton.setBounds(10, 285, 210, 45);
		operationPanel.add(importButton);
		//option pane pop out ask user want to append or replace, if append then system will import the contact after the existing contact, if replace system will delete all the contacts and import the contacts
		importButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				boolean isReplace = false;

				try{
					if (phonebook.size() != 0){ //if at least one contact exists
						Object[] options = {"Replace",
								"Append"};
						//let user choose want to append or replace the existing contacts
						int n = JOptionPane.showOptionDialog(table,
								"Would you like to append the existing contacts or replace the old contacts with imported contacts?",
								"Import Contacts", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,options, options[0]);
						//if user choose replace then delete all the contacts.
						if (n == JOptionPane.YES_OPTION){
							isReplace = true;
						}
					}

					//open a file chooser to choose file location
					JFileChooser chooser = new JFileChooser();
					chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					int returnVal = chooser.showOpenDialog(null);
					//if file location is selected
					if(returnVal == JFileChooser.APPROVE_OPTION) {
						//if user choose to replace then delete all contacts
						if (isReplace){
							Operation operation = new Operation(phonebook);
							operation.deleteAllContact();
						}
						//import contacts by using importContact in operation class
						String fileLocation = chooser.getSelectedFile().getAbsolutePath();
						Operation operation = new Operation(phonebook);
						operation.importContact(fileLocation, tableModel);
					}
				} catch (HeadlessException ex) {
					//show message if import failed
					JOptionPane.showMessageDialog(null,"There is an error when import the file. Please import the file again.","Warning",JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		//export button
		JButton exportButton = new JButton("Export");
		exportButton.setToolTipText("Export contacts to csv or txt file.");
		exportButton.setFont(new RobotoFont(20).mediumRoboto());
		exportButton.setBounds(10, 340, 210, 45);
		operationPanel.add(exportButton);
		//if export button is click then open the export dialog box
		exportButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ExportDialogBox.main(phonebook);
			}
		});

		//about us button
		JButton aboutUsButton = new JButton("About Us");
		aboutUsButton.setToolTipText("Credits of this system.");
		aboutUsButton.setFont(new RobotoFont(20).mediumRoboto());
		aboutUsButton.setBounds(10, 395, 210, 45);
		operationPanel.add(aboutUsButton);
		//if about us button is clicked then open about us dialog window
		aboutUsButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AboutUs.main(null);
			}
		});

		//save and quit button
		JButton quitButton = new JButton("Save and Quit");
		quitButton.setToolTipText("Save and exit the system.");
		quitButton.setFont(new RobotoFont(20).mediumRoboto());
		quitButton.setBounds(10, 450, 210, 45);
		operationPanel.add(quitButton);
		//Exit and save the system after click the exit button
		quitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//show dialog box to exit system
				int exitDialog = JOptionPane.showConfirmDialog(null,"Are you sure want to exit the system?", "Quit",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
				if (exitDialog == JOptionPane.YES_OPTION){
					//if user confirm to exit
					try {
						//auto-save the contacts in txt format, located at \src\com\save data
						Operation operation = new Operation(phonebook);
						operation.exportContact("txt", ".\\src\\com\\save data\\autosave.txt");
						//system end
						System.exit(0);
					} catch (IOException ex) {
						//if saved failed
						JOptionPane.showConfirmDialog(null,"Error happen when data are auto-saved. Please try again later or export your contacts first.","Warning",JOptionPane.OK_OPTION,JOptionPane.WARNING_MESSAGE);
					}

				}
			}
		});

		JPanel searchPanel = new JPanel();
		searchPanel.setBounds(10, 10, 936, 50);
		searchPanel.setBorder(null);
		showPanel.add(searchPanel);
		searchPanel.setLayout(null);

		//search text field
		JTextField searchTextField = new JTextField();
		searchTextField.setBounds(86, 0, 800, 40);
		searchPanel.add(searchTextField);
		searchTextField.setColumns(10);
		searchTextField.setFont(new RobotoFont(15).lightRoboto());

		//search button
		JButton searchButton = new JButton("");
		searchButton.setToolTipText("Search contact.");
		searchButton.setIcon(new ImageIcon(Objects.requireNonNull(Menu.class.getResource("/com/assets/search (1).png"))));
		searchButton.setBounds(896, 0, 40, 40);
		//after clicked it will search the text in search text field
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = searchTextField.getText();
				if (text.length() == 0){
					//if nothing is entered in search text field then do nothing
					rowSorter.setRowFilter(null);
				} else {
					//return result by using key word in search text field
					rowSorter.setRowFilter(RowFilter.regexFilter(text));
				}
			}
		});
		searchPanel.add(searchButton);

		//label for search text
		JLabel searchText = new JLabel("Search:");
		searchText.setFont(new RobotoFont(20).mediumRoboto());
		searchText.setBounds(10, 0, 76, 40);
		searchPanel.add(searchText);

	}
}
