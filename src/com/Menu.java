package com;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Font;
import javax.swing.JScrollPane;

public class Menu extends JFrame {

	private JPanel contentPane;
	private JTextField searchtextField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					Menu frame = new Menu();
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
		
		JPanel operationPanel = new JPanel();
		operationPanel.setBounds(10, 140, 230, 504);
		contentPane.add(operationPanel);
		operationPanel.setLayout(null);
		
		JButton createNewButton = new JButton("Create New");
		createNewButton.setToolTipText("Create a new contact.");
		createNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		createNewButton.setBounds(10, 10, 210, 45);
		
		operationPanel.add(createNewButton);
		
		JButton viewButton = new JButton("View");
		viewButton.setToolTipText("View a contact.");
		viewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		viewButton.setBounds(10, 65, 210, 45);
		operationPanel.add(viewButton);
		
		JButton editButton = new JButton("Edit");
		editButton.setToolTipText("Edit an existing contact.");
		editButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		editButton.setBounds(10, 120, 210, 45);
		operationPanel.add(editButton);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.setToolTipText("Delete an unwanted contact.");
		deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		deleteButton.setBounds(10, 175, 210, 45);
		operationPanel.add(deleteButton);
		
		JButton sortButton = new JButton("Sort");
		sortButton.setToolTipText("Sort contacts according to attributes.");
		sortButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		sortButton.setBounds(10, 230, 210, 45);
		operationPanel.add(sortButton);
		
		JButton importButton = new JButton("Import");
		importButton.setToolTipText("Import a txt or csv file.");
		importButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		importButton.setBounds(10, 285, 210, 45);
		operationPanel.add(importButton);
		
		JButton exportButton = new JButton("Export");
		exportButton.setToolTipText("Export contacts to csv or txt file.");
		exportButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		exportButton.setBounds(10, 340, 210, 45);
		operationPanel.add(exportButton);
		
		JButton aboutUsButton = new JButton("About Us");
		aboutUsButton.setToolTipText("Credits of this system.");
		aboutUsButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		aboutUsButton.setBounds(10, 395, 210, 45);
		operationPanel.add(aboutUsButton);
		
		JButton quitButton = new JButton("Quit");
		quitButton.setToolTipText("Exit the system.");
		quitButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		quitButton.setBounds(10, 450, 210, 45);
		operationPanel.add(quitButton);
		
		JPanel showPanel = new JPanel();
		showPanel.setBounds(250, 140, 956, 504);
		contentPane.add(showPanel);
		showPanel.setLayout(null);
		
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
		
		JScrollPane tabelPanel = new JScrollPane();
		tabelPanel.setBounds(10, 70, 936, 424);
		showPanel.add(tabelPanel);
		
		table = new JTable();
		tabelPanel.setViewportView(table);
		
		
		
	}
}
