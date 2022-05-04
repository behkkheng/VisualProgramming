package com;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Menu extends JFrame {

	private JPanel contentPane;
	private JTextField searchtextField;
	private LinkedList<Contact> phonebook;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
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
		LinkedList<Contact> phonebook = new LinkedList<>();

		this.setTitle("Phone Book Management System");
		this.setIconImage(new ImageIcon(Menu.class.getResource("/com/assets/contact (1).png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1220, 685);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel bannerPanel = new JPanel();
		bannerPanel.setBounds(10, 10, 1196, 120);
		contentPane.add(bannerPanel);
		bannerPanel.setLayout(null);
		
		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(Menu.class.getResource("/com/assets/contact (1).png")));
		icon.setBounds(50, 10, 100, 100);
		bannerPanel.add(icon);
		
		JLabel titleOfSystem = new JLabel("     Phone Book Management System");
		titleOfSystem.setFont(new RobotoFont(51).boldRoboto());
		titleOfSystem.setBounds(134, 10, 901, 100);
		bannerPanel.add(titleOfSystem);

		JPanel showPanel = new JPanel();
		showPanel.setBounds(250, 140, 956, 504);
		contentPane.add(showPanel);
		showPanel.setLayout(null);

		JScrollPane tabelPanel = new JScrollPane();
		tabelPanel.setBounds(10, 70, 936, 424);
		showPanel.add(tabelPanel);

		TableModel tableModel = new TableModel(phonebook);
		JTable table = new JTable(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setRowSelectionAllowed(true);
		tabelPanel.setViewportView(table);
		table.setAutoCreateRowSorter(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tableModel);
		table.setRowSorter(rowSorter);
		table.getColumnModel().getColumn(8).setPreferredWidth(0);
		table.setFont(new RobotoFont(12).lightRoboto());
		table.setRowHeight(20);
		try{
			Operation operation = new Operation(phonebook);
			operation.importContact(".\\src\\com\\save data\\autosave.txt",tableModel);
			tableModel.setRowCount();
			tableModel.fireTableStructureChanged();
		} catch (Exception e) {
			;
		}

		JPanel operationPanel = new JPanel();
		operationPanel.setBounds(10, 140, 230, 504);
		contentPane.add(operationPanel);
		operationPanel.setLayout(null);
		
		JButton createNewButton = new JButton("Create New");
		createNewButton.setToolTipText("Create a new contact.");
		createNewButton.setFont(new RobotoFont(20).mediumRoboto());
		createNewButton.setBounds(10, 10, 210, 45);
		operationPanel.add(createNewButton);
		CreateDialogBox createDialogBox = new CreateDialogBox(phonebook, tableModel);
		createNewButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				createDialogBox.main(phonebook,tableModel);
			}
		});
		
		JButton viewButton = new JButton("View");
		viewButton.setToolTipText("View a contact.");
		viewButton.setFont(new RobotoFont(20).mediumRoboto());
		viewButton.setBounds(10, 65, 210, 45);
		operationPanel.add(viewButton);
		viewButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int selected_row = table.getSelectedRow();
				int id_column = 0;
				String row_id = (String) table.getValueAt(selected_row,id_column);
				DetailDialogBox.main(phonebook, Integer.valueOf(row_id));
			}
		});
		
		JButton editButton = new JButton("Edit");
		editButton.setToolTipText("Edit an existing contact.");
		editButton.setFont(new RobotoFont(20).mediumRoboto());
		editButton.setBounds(10, 120, 210, 45);
		operationPanel.add(editButton);
		editButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int selected_row = table.getSelectedRow();
				int id_column = 0;
				String row_id = (String) table.getValueAt(selected_row,id_column);
				EditDialogBox.main(phonebook,Integer.valueOf(row_id), tableModel);
				tableModel.setRowCount();
				tableModel.fireTableStructureChanged();
			}
		});
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.setToolTipText("Delete an unwanted contact.");
		deleteButton.setFont(new RobotoFont(20).mediumRoboto());
		deleteButton.setBounds(10, 175, 210, 45);
		operationPanel.add(deleteButton);
		deleteButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//get id of selected row
				int selected_row = table.getSelectedRow();
				int id_column = 0;
				int row_id = Integer.parseInt((String) table.getValueAt(selected_row,id_column));
				int deleteDialog = JOptionPane.showConfirmDialog(null,"Are you sure want to delete "+phonebook.get(row_id).getName()+"?", "Delete Contact",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
				try{
					if (deleteDialog == JOptionPane.OK_OPTION){
						Operation operation = new Operation(phonebook);
						operation.deleteContact(row_id);
						tableModel.setRowCount();
						tableModel.fireTableStructureChanged();
					}
				} catch (Exception ex) {
					JOptionPane.showConfirmDialog(null,"There is an error when delete the file. Please try again.","Warning",JOptionPane.OK_OPTION,JOptionPane.WARNING_MESSAGE);
					throw new RuntimeException(ex);
				}
			}
		});

		JButton deleteAllButton = new JButton("Delete All");
		deleteAllButton.setToolTipText("Delete all contacts.");
		deleteAllButton.setFont(new RobotoFont(20).mediumRoboto());
		deleteAllButton.setBounds(10, 230, 210, 45);
		operationPanel.add(deleteAllButton);
		deleteAllButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (phonebook.size() < 1){
					JOptionPane.showMessageDialog(null,"There are no contacts to delete.");
				}else {
					int deleteAllDialog = JOptionPane.showConfirmDialog(null,"Are you sure want to delete all contacts?", "Success!",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
					if (deleteAllDialog == JOptionPane.OK_OPTION){
						Operation operation = new Operation(phonebook);
						operation.deleteAllContact();
						tableModel.setRowCount();
						tableModel.fireTableStructureChanged();
						JOptionPane.showMessageDialog(null,"All contacts are deleted.", "Delete Contact",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});

		JButton importButton = new JButton("Import");
		importButton.setToolTipText("Import a txt or csv file.");
		importButton.setFont(new RobotoFont(20).mediumRoboto());
		importButton.setBounds(10, 285, 210, 45);
		operationPanel.add(importButton);
		importButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try{;
					if (phonebook.size() != 0){
						Object[] options = {"Replace",
								"Append"};
						int n = JOptionPane.showOptionDialog(table,
								"Would you like to append the existing contacts or replace the old contacts with imported contacts?",
								"Import Contacts",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null,     //do not use a custom Icon
								options,  //the titles of buttons
								options[0]); //default button title
						if (n == JOptionPane.YES_OPTION){
							Operation operation = new Operation(phonebook);
							operation.deleteAllContact();
						}
					}

					JFileChooser chooser = new JFileChooser();
					chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					int returnVal = chooser.showOpenDialog(importButton);
					if(returnVal == JFileChooser.APPROVE_OPTION) {
						String fileLocation = chooser.getSelectedFile().getAbsolutePath();
						Operation operation = new Operation(phonebook);
						operation.importContact(fileLocation, tableModel);
					}
				} catch (HeadlessException ex) {
					JOptionPane.showConfirmDialog(null,"There is an error when import the file. Please import the file again.","Warning",JOptionPane.OK_OPTION,JOptionPane.WARNING_MESSAGE);
					throw new RuntimeException(ex);
				}
			}
		});

		JButton exportButton = new JButton("Export");
		exportButton.setToolTipText("Export contacts to csv or txt file.");
		exportButton.setFont(new RobotoFont(20).mediumRoboto());
		exportButton.setBounds(10, 340, 210, 45);
		operationPanel.add(exportButton);
		exportButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ExportDialogBox.main(phonebook);
			}
		});

		JButton aboutUsButton = new JButton("About Us");
		aboutUsButton.setToolTipText("Credits of this system.");
		aboutUsButton.setFont(new RobotoFont(20).mediumRoboto());
		aboutUsButton.setBounds(10, 395, 210, 45);
		operationPanel.add(aboutUsButton);
		aboutUsButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AboutUs.main(null);
			}
		});

		//Exit and save the system after click the button.
		JButton quitButton = new JButton("Save and Quit");
		quitButton.setToolTipText("Save and exit the system.");
		quitButton.setFont(new RobotoFont(20).mediumRoboto());
		quitButton.setBounds(10, 450, 210, 45);
		operationPanel.add(quitButton);
		quitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int exitDialog = JOptionPane.showConfirmDialog(null,"Are you sure want to exit the system?", "Quit",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
				if (exitDialog == JOptionPane.YES_OPTION){

					try {
						Operation operation = new Operation(phonebook);
						operation.exportContact("txt", ".\\src\\com\\save data\\autosave.txt");

						System.exit(0);
					} catch (IOException ex) {
						JOptionPane.showConfirmDialog(null,"Error happen when data are auto-saved. Please try again later or export your contacts first.","Warning",JOptionPane.OK_OPTION,JOptionPane.WARNING_MESSAGE);
						throw new RuntimeException(ex);
					}

				}
				System.exit(0);
			}
		});

		JPanel searchPanel = new JPanel();
		searchPanel.setBounds(10, 10, 936, 50);
		searchPanel.setBorder(null);
		showPanel.add(searchPanel);
		searchPanel.setLayout(null);

		searchtextField = new JTextField();
		searchtextField.setBounds(86, 0, 800, 40);
		searchPanel.add(searchtextField);
		searchtextField.setColumns(10);
		searchtextField.setFont(new RobotoFont(15).lightRoboto());

		JButton searchButton = new JButton("");
		searchButton.setToolTipText("Search contact.");
		searchButton.setIcon(new ImageIcon(Menu.class.getResource("/com/assets/search (1).png")));
		searchButton.setBounds(896, 0, 40, 40);
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = searchtextField.getText();
				if (text.length() == 0){
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter(text));
				}
			}
		});
		searchPanel.add(searchButton);

		JLabel searchText = new JLabel("Search:");
		searchText.setFont(new RobotoFont(20).lightRoboto());
		searchText.setBounds(10, 0, 76, 40);
		searchPanel.add(searchText);

	}
}
