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

	private TableModel tableModel;

	private int index;

	/**
	 * Launch the application.
	 */
	public static void main(LinkedList<Contact> phonebook, int index, TableModel tableModel) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			EditDialogBox dialog = new EditDialogBox(phonebook, index, tableModel);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setPhonebook(phonebook);
			dialog.setIndex(index);
			dialog.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditDialogBox(LinkedList<Contact> phonebook, int index, TableModel tableModel) {
		//debug
		setPhonebook(phonebook);
		setIndex(index);
		this.setTitle("Edit Contact");
		this.setIconImage(new ImageIcon(EditDialogBox.class.getResource("/com/assets/contact (1).png")).getImage());
		setBounds(100, 100, 629, 681);
		setLocationRelativeTo(null);
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
		editLabel.setFont(new RobotoFont(36).boldRoboto());
		editLabel.setBounds(305, 19, 71, 51);
		titlePanel.add(editLabel);
		
		JPanel formPanel = new JPanel();
		formPanel.setBounds(10, 95, 605, 486);
		contentPanel.add(formPanel);
		formPanel.setLayout(null);
		
		nameTextField = new JTextField();
		nameTextField.setToolTipText("Name of the contacts.");
		nameTextField.setFont(new RobotoFont(16).mediumRoboto());
		nameTextField.setBounds(171, 10, 377, 30);
		formPanel.add(nameTextField);
		nameTextField.setColumns(10);
		nameTextField.setText(phonebook.get(index).getName());
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setFont(new RobotoFont(16).mediumRoboto());
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setBounds(104, 11, 57, 28);
		formPanel.add(nameLabel);
		
		JLabel genderLabel = new JLabel("Gender:");
		genderLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		genderLabel.setFont(new RobotoFont(16).mediumRoboto());
		genderLabel.setBounds(104, 51, 57, 28);
		formPanel.add(genderLabel);
		
		JComboBox genderComboBox = new JComboBox();
		genderComboBox.setToolTipText("Please choose a gender.");
		genderComboBox.setFont(new RobotoFont(16).mediumRoboto());
		genderComboBox.setModel(new DefaultComboBoxModel(new String[] {"Please choose a gender.", "Male", "Female"}));
		genderComboBox.setBounds(171, 50, 201, 30);
		formPanel.add(genderComboBox);
		if (phonebook.get(index).getGender().equals("Unknown")){
			genderComboBox.setSelectedIndex(0);
		} else {
			genderComboBox.setSelectedItem(phonebook.get(index).getGender());
		}

		int[] dayMonthYear = new int[3];
		String[] temp = new String[3];
		String splitBy = "/";

		temp = phonebook.get(index).getDobDate().split(splitBy);
		for (int i=0; i<3; i++){
			dayMonthYear[i] = Integer.parseInt(temp[i]);
		}
		int day_separated = dayMonthYear[0];
		int month_separated = dayMonthYear[1];
		int year_separated = dayMonthYear[2];


		
		JLabel dobLabel = new JLabel("DOB:");
		dobLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dobLabel.setFont(new RobotoFont(16).mediumRoboto());
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
		dayComboBox.setFont(new RobotoFont(16).mediumRoboto());
		dayComboBox.setBounds(171, 90, 79, 30);
		formPanel.add(dayComboBox);
		dayComboBox.setSelectedIndex(day_separated);
		
		JComboBox monthComboBox = new JComboBox();
		monthComboBox.setToolTipText("Month of Birth");
		monthComboBox.setFont(new RobotoFont(16).mediumRoboto());
		Vector month=new Vector();
        month.add("Month");
        for (int i = 1; i <= 12; i++) {
            month.add(i);
        }
        monthComboBox.setModel(new DefaultComboBoxModel(month));
		monthComboBox.setBounds(260, 90, 79, 30);
		formPanel.add(monthComboBox);
		monthComboBox.setSelectedIndex(month_separated);
		
		JComboBox yearComboBox = new JComboBox();  
		yearComboBox.setToolTipText("Year of Birth");
		yearComboBox.setFont(new RobotoFont(16).mediumRoboto());
        Vector year=new Vector();
        year.add("Year");
        for (int i = 2022; i >= 1930; i--) {
            year.add(i);
        }
        yearComboBox.setModel(new DefaultComboBoxModel(year));
		yearComboBox.setBounds(349, 90, 79, 30);
		formPanel.add(yearComboBox);;
		if (day_separated == 0 && month_separated == 0 && year_separated == 0){
			dayComboBox.setSelectedIndex(0);
			monthComboBox.setSelectedIndex(0);
			yearComboBox.setSelectedIndex(0);
		} else {
			yearComboBox.setSelectedIndex(2022-year_separated+1);
		}


		JLabel hpNo1Label = new JLabel("Mobile Phone No.:");
		hpNo1Label.setHorizontalAlignment(SwingConstants.RIGHT);
		hpNo1Label.setFont(new RobotoFont(16).mediumRoboto());
		hpNo1Label.setBounds(10, 131, 151, 28);
		formPanel.add(hpNo1Label);
		
		mobileTextField = new JTextField();
		mobileTextField.setToolTipText("Mobile phone number.");
		mobileTextField.setFont(new RobotoFont(16).mediumRoboto());
		mobileTextField.setColumns(10);
		mobileTextField.setBounds(171, 130, 377, 30);
		formPanel.add(mobileTextField);
		mobileTextField.setText(phonebook.get(index).getMobilePhone());
		
		JLabel workLabel = new JLabel("Work Phone No.:");
		workLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		workLabel.setFont(new RobotoFont(16).mediumRoboto());
		workLabel.setBounds(10, 170, 151, 28);
		formPanel.add(workLabel);
		
		workTextField = new JTextField();
		workTextField.setToolTipText("Work phone number");
		workTextField.setFont(new RobotoFont(16).mediumRoboto());
		workTextField.setColumns(10);
		workTextField.setBounds(171, 169, 377, 30);
		formPanel.add(workTextField);
		workTextField.setText(phonebook.get(index).getWorkPhone());
		
		JLabel homeLabel = new JLabel("Home Phone No.:");
		homeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		homeLabel.setFont(new RobotoFont(16).mediumRoboto());
		homeLabel.setBounds(10, 209, 151, 28);
		formPanel.add(homeLabel);
		
		homeTextField = new JTextField();
		homeTextField.setToolTipText("Home phone number.");
		homeTextField.setFont(new RobotoFont(16).mediumRoboto());
		homeTextField.setColumns(10);
		homeTextField.setBounds(171, 208, 377, 30);
		formPanel.add(homeTextField);
		homeTextField.setText(phonebook.get(index).getHomePhone());
		
		JLabel emailAddress = new JLabel("Email Address:");
		emailAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		emailAddress.setFont(new RobotoFont(16).mediumRoboto());
		emailAddress.setBounds(27, 248, 134, 28);
		formPanel.add(emailAddress);
		
		emailAddressTextField = new JTextField();
		emailAddressTextField.setToolTipText("Email address.");
		emailAddressTextField.setFont(new RobotoFont(16).mediumRoboto());
		emailAddressTextField.setColumns(10);
		emailAddressTextField.setBounds(171, 247, 377, 30);
		formPanel.add(emailAddressTextField);
		emailAddressTextField.setText(phonebook.get(index).getEmail());
		
		JLabel addressLabel = new JLabel("Address:");
		addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		addressLabel.setFont(new RobotoFont(16).mediumRoboto());
		addressLabel.setBounds(27, 287, 134, 28);
		formPanel.add(addressLabel);
		
		JTextArea addressTextArea = new JTextArea();
		addressTextArea.setToolTipText("Address of home.");
		addressTextArea.setLineWrap(true);
		addressTextArea.setFont(new RobotoFont(16).mediumRoboto());
		addressTextArea.setBounds(171, 287, 377, 90);
		formPanel.add(addressTextArea);
		addressTextArea.setText(phonebook.get(index).getAddress());
		
		JLabel remarkLabel = new JLabel("Remark:");
		remarkLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		remarkLabel.setFont(new RobotoFont(16).mediumRoboto());
		remarkLabel.setBounds(27, 387, 134, 28);
		formPanel.add(remarkLabel);
		
		JTextArea remarkTextArea = new JTextArea();
		remarkTextArea.setToolTipText("Remarks.");
		remarkTextArea.setLineWrap(true);
		remarkTextArea.setFont(new RobotoFont(16).mediumRoboto());
		remarkTextArea.setBounds(171, 387, 377, 90);
		formPanel.add(remarkTextArea);
		remarkTextArea.setText(phonebook.get(index).getRemark());

		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(10, 591, 610, 46);
		contentPanel.add(buttonPane);
		buttonPane.setLayout(null);

		JButton okButton = new JButton("OK");
		okButton.setFont(new RobotoFont(16).mediumRoboto());
		okButton.setBounds(185, 10, 100, 30);
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		okButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String name = nameTextField.getText();
				String gender = genderComboBox.getSelectedItem().toString();
				String dayString = dayComboBox.getSelectedItem().toString();
				String monthString = monthComboBox.getSelectedItem().toString();
				String yearString = yearComboBox.getSelectedItem().toString();
				int dayVariable = 0;
				int monthVariable = 0;
				int yearVariable = 0;
				String mobilePhone = mobileTextField.getText();
				String workPhone = workTextField.getText();
				String homePhone = homeTextField.getText();
				String email = emailAddressTextField.getText();
				String address = addressTextArea.getText();
				String remark = remarkTextArea.getText();
				int id = Integer.valueOf(phonebook.get(index).getId());

				if (name.equals("")){
					name = " ";
				}
				if (gender.equals("Please choose a gender.")){
					gender = "Unknown";
				}
				if ((!dayString.equals("Day"))){
					dayVariable = Integer.parseInt(dayString);
				}
				if ((!monthString.equals("Month"))){
					monthVariable = Integer.parseInt(monthString);
				}
				if ((!yearString.equals("Year"))){
					yearVariable = Integer.parseInt(yearString);
				}
				if (dayVariable == 0 || monthVariable == 0 || yearVariable == 0){
					dayVariable = 0;
					monthVariable = 0;
					yearVariable = 0;
				}
				if (mobilePhone.equals("")){
					mobilePhone = " ";
				}
				if (workPhone.equals("")){
					workPhone = " ";
				}
				if (homePhone.equals("")){
					homePhone = " ";
				}
				if (email.equals("")){
					email = " ";
				}
				if (address.equals("")){
					address = " ";
				}
				if (remark.equals("")){
					remark = " ";
				}

				if (name.equals(" ") && gender.equals("Unknown") && dayVariable==0 && monthVariable==0 && yearVariable==0 && mobilePhone.equals(" ") && workPhone.equals(" ") && homePhone.equals(" ") && email.equals(" ") && address.equals(" ") && remark.equals(" ")){
					JOptionPane.showMessageDialog(null,"The contact did not created because all attributes is empty.","Warning",JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}else {
					try{
						Operation operation = new Operation(phonebook);
						operation.editContact(index,name, gender, dayVariable,monthVariable,yearVariable, mobilePhone,workPhone,homePhone,email,address,remark,id);
						LinkedList<Contact> result = operation.returnLinkedList();
						setPhonebook(result);
						tableModel.setRowCount();
						tableModel.fireTableStructureChanged();
						dispose();
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null,"Error occured when contact is created, the contact is not created. Please try again.","Warning",JOptionPane.WARNING_MESSAGE);
						throw new RuntimeException(ex);
					}
				}

			}
		});


		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new RobotoFont(16).mediumRoboto());
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
		this.phonebook = phonebook;
	}

	public LinkedList<Contact> returnLinkedList(){
		return this.phonebook;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
