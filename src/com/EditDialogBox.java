package com;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Vector;

public class EditDialogBox extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nameTextField;
	private JTextField mobileTextField;
	private JTextField workTextField;
	private JTextField homeTextField;
	private JTextField emailAddressTextField;

	private LinkedList<Contact> phonebook;

	private int index;

	/**
	 * Launch the application.
	 */
	public static void main(LinkedList<Contact> phonebook, int index) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			EditDialogBox dialog = new EditDialogBox(phonebook, index);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setPhonebook(phonebook);
			//debug
				System.out.println("From EditDialogBox");
				for (int i=0; i< phonebook.size();i++){
					System.out.println(i+". "+phonebook.get(i).getName());
				}
			dialog.setIndex(index);
			System.out.println("index: "+index);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditDialogBox(LinkedList<Contact> phonebook, int index) {
		//debug
		setPhonebook(phonebook);
		setIndex(index);
		System.out.println("From EditDialogBox");
		for (int i=0; i< phonebook.size();i++){
			System.out.println(i+". "+phonebook.get(i).getName());
		}
		this.setTitle("Edit Contact");
		this.setIconImage(new ImageIcon(AboutUs.class.getResource("/com/assets/contact (1).png")).getImage());
		setLocationRelativeTo(null);
		setBounds(100, 100, 629, 681);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(10, 10, 610, 85);
		contentPanel.add(titlePanel);
		titlePanel.setLayout(null);
		
		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(EditDialogBox.class.getResource("/com/assets/pencil (1).png")));
		icon.setBounds(235, 10, 60, 60);
		titlePanel.add(icon);
		
		JLabel editLabel = new JLabel("Edit");
		editLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
		editLabel.setBounds(305, 19, 71, 51);
		titlePanel.add(editLabel);
		
		JPanel formPanel = new JPanel();
		formPanel.setBounds(10, 95, 605, 486);
		contentPanel.add(formPanel);
		formPanel.setLayout(null);
		
		nameTextField = new JTextField();
		nameTextField.setToolTipText("Name of the contacts.");
		nameTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nameTextField.setBounds(171, 10, 377, 30);
		formPanel.add(nameTextField);
		nameTextField.setColumns(10);
		nameTextField.setText(phonebook.get(index).getName());
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setBounds(104, 11, 57, 28);
		formPanel.add(nameLabel);
		
		JLabel genderLabel = new JLabel("Gender:");
		genderLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		genderLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		genderLabel.setBounds(104, 51, 57, 28);
		formPanel.add(genderLabel);
		
		JComboBox genderComboBox = new JComboBox();
		genderComboBox.setToolTipText("Please choose a gender.");
		genderComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		genderComboBox.setModel(new DefaultComboBoxModel(new String[] {"Please choose a gender", "Male", "Female"}));
		genderComboBox.setBounds(171, 50, 201, 30);
		formPanel.add(genderComboBox);
		genderComboBox.setSelectedItem(phonebook.get(index).getGender());
		
		JLabel dobLabel = new JLabel("DOB:");
		dobLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dobLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dobLabel.setBounds(104, 91, 57, 28);
		formPanel.add(dobLabel);
		
		JComboBox dayComboBox = new JComboBox();
		dayComboBox.setToolTipText("Day of Birth");
		Vector day=new Vector();
        day.add("Day");
        for (int i = 1; i <= 31; i++) {
            day.add(i);
        }
        dayComboBox.setModel(new DefaultComboBoxModel(day));
		dayComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dayComboBox.setBounds(171, 90, 79, 30);
		formPanel.add(dayComboBox);
		dayComboBox.setSelectedItem(phonebook.get(index).getDobDay());
		
		JComboBox monthComboBox = new JComboBox();
		monthComboBox.setToolTipText("Month of Birth");
		monthComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Vector month=new Vector();
        month.add("Month");
        for (int i = 1; i <= 12; i++) {
            month.add(i);
        }
        monthComboBox.setModel(new DefaultComboBoxModel(month));
		monthComboBox.setBounds(260, 90, 79, 30);
		formPanel.add(monthComboBox);
		monthComboBox.setSelectedItem(phonebook.get(index).getDobMonth());
		
		JComboBox yearComboBox = new JComboBox();  
		yearComboBox.setToolTipText("Year of Birth");
		yearComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
        Vector year=new Vector();
        year.add("Year");
        for (int i = 2022; i >= 1930; i--) {
            year.add(i);
        }
        yearComboBox.setModel(new DefaultComboBoxModel(year));
		yearComboBox.setBounds(349, 90, 79, 30);
		formPanel.add(yearComboBox);
		yearComboBox.setSelectedItem(phonebook.get(index).getDobYear());
		
		JLabel hpNo1Label = new JLabel("Mobile Phone No.:");
		hpNo1Label.setHorizontalAlignment(SwingConstants.RIGHT);
		hpNo1Label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		hpNo1Label.setBounds(10, 131, 151, 28);
		formPanel.add(hpNo1Label);
		
		mobileTextField = new JTextField();
		mobileTextField.setToolTipText("Mobile phone number.");
		mobileTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mobileTextField.setColumns(10);
		mobileTextField.setBounds(171, 130, 377, 30);
		formPanel.add(mobileTextField);
		mobileTextField.setText(phonebook.get(index).getMobilePhone());
		
		JLabel workLabel = new JLabel("Work Phone No.:");
		workLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		workLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		workLabel.setBounds(10, 170, 151, 28);
		formPanel.add(workLabel);
		
		workTextField = new JTextField();
		workTextField.setToolTipText("Work phone number");
		workTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		workTextField.setColumns(10);
		workTextField.setBounds(171, 169, 377, 30);
		formPanel.add(workTextField);
		workTextField.setText(phonebook.get(index).getWorkPhone());
		
		JLabel homeLabel = new JLabel("Home Phone No.:");
		homeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		homeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		homeLabel.setBounds(10, 209, 151, 28);
		formPanel.add(homeLabel);
		
		homeTextField = new JTextField();
		homeTextField.setToolTipText("Home phone number.");
		homeTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		homeTextField.setColumns(10);
		homeTextField.setBounds(171, 208, 377, 30);
		formPanel.add(homeTextField);
		homeTextField.setText(phonebook.get(index).getHomePhone());
		
		JLabel emailAddress = new JLabel("Email Address:");
		emailAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		emailAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailAddress.setBounds(27, 248, 134, 28);
		formPanel.add(emailAddress);
		
		emailAddressTextField = new JTextField();
		emailAddressTextField.setToolTipText("Email address.");
		emailAddressTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailAddressTextField.setColumns(10);
		emailAddressTextField.setBounds(171, 247, 377, 30);
		formPanel.add(emailAddressTextField);
		emailAddressTextField.setText(phonebook.get(index).getEmail());
		
		JLabel addressLabel = new JLabel("Address:");
		addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		addressLabel.setBounds(27, 287, 134, 28);
		formPanel.add(addressLabel);
		
		JTextArea addressTextArea = new JTextArea();
		addressTextArea.setToolTipText("Address of home.");
		addressTextArea.setLineWrap(true);
		addressTextArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		addressTextArea.setBounds(171, 287, 377, 90);
		formPanel.add(addressTextArea);
		addressTextArea.setText(phonebook.get(index).getAddress());
		
		JLabel remarkLabel = new JLabel("Remark:");
		remarkLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		remarkLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		remarkLabel.setBounds(27, 387, 134, 28);
		formPanel.add(remarkLabel);
		
		JTextArea remarkTextArea = new JTextArea();
		remarkTextArea.setToolTipText("Remarks.");
		remarkTextArea.setLineWrap(true);
		remarkTextArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		remarkTextArea.setBounds(171, 387, 377, 90);
		formPanel.add(remarkTextArea);
		remarkTextArea.setText(phonebook.get(index).getRemark());

		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(10, 591, 610, 46);
		contentPanel.add(buttonPane);
		buttonPane.setLayout(null);

		JButton okButton = new JButton("OK");
		okButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		okButton.setBounds(185, 10, 100, 30);
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		okButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Operation operation = new Operation(phonebook);
				operation.editContact(index,nameTextField.getText(), (String) genderComboBox.getSelectedItem(),(Integer) dayComboBox.getSelectedItem(),(Integer) monthComboBox.getSelectedItem(),(Integer)yearComboBox.getSelectedItem(), mobileTextField.getText(),workTextField.getText(),homeTextField.getText(),emailAddressTextField.getText(),addressTextArea.getText(),remarkTextArea.getText(), Integer.valueOf(phonebook.get(index).getId()) );
				LinkedList<Contact> result = operation.returnLinkedList();
				setPhonebook(result);
				dispose();
			}
		});


		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cancelButton.setBounds(325, 10, 100, 30);
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		cancelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});

	}

	public void setPhonebook(LinkedList<Contact> phonebook) {
		//debug
		System.out.println("From EditDialogBox setPhoneBefore");
		for (int i=0; i< phonebook.size();i++){
			System.out.println(i+". "+phonebook.get(i).getName());
		}
		this.phonebook = phonebook;
		//debug
		System.out.println("From EditDialogBox setPhoneAfter");
		for (int i=0; i< phonebook.size();i++){
			System.out.println(i+". "+phonebook.get(i).getName());
		}
	}

	public LinkedList<Contact> returnLinkedList(){
		return this.phonebook;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
