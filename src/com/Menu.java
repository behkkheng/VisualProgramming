package com;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Menu extends JFrame {

	private JPanel contentPane;
	private JTextField searchtextField;
	private JTable table;

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
		this.setIconImage(new ImageIcon(AboutUs.class.getResource("/com/assets/contact (1).png")).getImage());
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
		titleOfSystem.setFont(new Font("Tahoma", Font.PLAIN, 36));
		titleOfSystem.setBounds(134, 10, 901, 100);
		bannerPanel.add(titleOfSystem);

		JPanel showPanel = new JPanel();
		showPanel.setBounds(250, 140, 956, 504);
		contentPane.add(showPanel);
		showPanel.setLayout(null);

		JScrollPane tabelPanel = new JScrollPane();
		tabelPanel.setBounds(10, 70, 936, 424);
		showPanel.add(tabelPanel);


		/*Operation operation = new Operation();
		LinkedList<Contact> phonebook = operation.createNewLinkedList();*/
		Contact contact = new Contact();
		contact.setAttribute("Beh","Male",11,9,2000,"01137504036","043328811","043238811","behkkheng@gmail.com","24, Lorong Kapal Off Jalan Chain Ferry, 12100, Butterworth",null);
		phonebook.add(contact);

		/*String column[]={"Name","Gender","DOB","Mobile Phone","Work Phone","Home Phone","Email","Address","id"};
		String[][] data = new String[phonebook.size()][10];
		// print array in rectangular form
		for (int i=0; i< phonebook.size(); i++) {
			data[i][0] = phonebook.get(i).getName();
			data[i][1] = phonebook.get(i).getGender();
			data[i][2] = phonebook.get(i).getDobDate();
			data[i][3] = phonebook.get(i).getMobilePhone();
			data[i][4] = phonebook.get(i).getWorkPhone();
			data[i][5] = phonebook.get(i).getHomePhone();
			data[i][6] = phonebook.get(i).getEmail();
			data[i][7] = phonebook.get(i).getAddress();
			data[i][8] = phonebook.get(i).getRemark();
			data[i][9] = phonebook.get(i).getId();
		}
		System.out.println("From Menu");
		for (int i=0; i< phonebook.size();i++){
			System.out.println(i+". "+phonebook.get(i).getName());
		}*/

		TableModel tableModel = new TableModel(phonebook);
		table = new JTable(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setRowSelectionAllowed(true);
		//TableColumnModel tableColumnModel = table.getColumnModel();
		//tableColumnModel.removeColumn(tableColumnModel.getColumn(8));
		tabelPanel.setViewportView(table);
		table.setAutoCreateRowSorter(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


		JPanel operationPanel = new JPanel();
		operationPanel.setBounds(10, 140, 230, 504);
		contentPane.add(operationPanel);
		operationPanel.setLayout(null);
		
		JButton createNewButton = new JButton("Create New");
		createNewButton.setToolTipText("Create a new contact.");
		createNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		createNewButton.setBounds(10, 10, 210, 45);
		operationPanel.add(createNewButton);
		CreateDialogBox createDialogBox = new CreateDialogBox();
		createNewButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				createDialogBox.main(phonebook);
				//debug
				/*System.out.println("From Mouse Listener");
				for (int i=0; i< phonebook.size();i++){
					System.out.println(i+". "+phonebook.get(i).getName());
				}*/
			}
		});

		LinkedList<Contact> result = createDialogBox.returnLinkedList();
		setPhonebook(result);
		
		JButton viewButton = new JButton("View");
		viewButton.setToolTipText("View a contact.");
		viewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		viewButton.setBounds(10, 65, 210, 45);
		operationPanel.add(viewButton);
		viewButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//get id of selected row
				int selected_row = table.getSelectedRow();
				System.out.println("row: "+selected_row);
				int id_column = 8;
				String row_id = (String) table.getValueAt(selected_row,id_column);
				DetailDialogBox.main(phonebook, Integer.valueOf(row_id));
			}
		});
		
		JButton editButton = new JButton("Edit");
		editButton.setToolTipText("Edit an existing contact.");
		editButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		editButton.setBounds(10, 120, 210, 45);
		operationPanel.add(editButton);
		editButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//debug
				System.out.println("From Menu");
				for (int i=0; i< phonebook.size();i++){
					System.out.println(i+". "+phonebook.get(i).getName());
				}
				//get id of selected row
				int selected_row = table.getSelectedRow();
				System.out.println("row: "+selected_row);
				int id_column = 8;
				String row_id = (String) table.getValueAt(selected_row,id_column);
				System.out.println("id: "+row_id);
				EditDialogBox.main(phonebook,Integer.valueOf(row_id));
			}
		});
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.setToolTipText("Delete an unwanted contact.");
		deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		deleteButton.setBounds(10, 175, 210, 45);
		operationPanel.add(deleteButton);

		JButton sortButton = new JButton("Refresh");
		sortButton.setToolTipText("Sort contacts according to attributes.");
		sortButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		sortButton.setBounds(10, 230, 210, 45);
		operationPanel.add(sortButton);
		sortButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tableModel.setRowCount();
				//debug
				//System.out.println("Number of row: "+tableModel.getRowCount());
				tableModel.fireTableStructureChanged();
			}
		});

		JButton importButton = new JButton("Import");
		importButton.setToolTipText("Import a txt or csv file.");
		importButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		importButton.setBounds(10, 285, 210, 45);
		operationPanel.add(importButton);
		importButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//debug
				;
			}
		});

		JButton exportButton = new JButton("Export");
		exportButton.setToolTipText("Export contacts to csv or txt file.");
		exportButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		exportButton.setBounds(10, 340, 210, 45);
		operationPanel.add(exportButton);
		exportButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//debug
				System.out.println("From MenuNow");
				for (int i=0; i< phonebook.size();i++){
					System.out.println(i+". "+phonebook.get(i).getName());
				}
			}
		});

		JButton aboutUsButton = new JButton("About Us");
		aboutUsButton.setToolTipText("Credits of this system.");
		aboutUsButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		aboutUsButton.setBounds(10, 395, 210, 45);
		operationPanel.add(aboutUsButton);
		aboutUsButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AboutUs.main(null);
			}
		});

		//Exit the system after click the button.
		JButton quitButton = new JButton("Quit");
		quitButton.setToolTipText("Exit the system.");
		quitButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		quitButton.setBounds(10, 450, 210, 45);
		operationPanel.add(quitButton);
		quitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
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

		JButton searchButton = new JButton("");
		searchButton.setToolTipText("Search contact.");
		searchButton.setIcon(new ImageIcon(Menu.class.getResource("/com/assets/search (1).png")));
		searchButton.setBounds(896, 0, 40, 40);

		searchPanel.add(searchButton);

		JLabel searchText = new JLabel("Search:");
		searchText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		searchText.setBounds(10, 0, 76, 40);
		searchPanel.add(searchText);



	}

	public void setPhonebook(LinkedList<Contact> phonebook){
		this.phonebook = phonebook;
	}

}
