package com;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Vector;

public class EditDialogBox extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(LinkedList<Contact> phonebook, int index, TableModel tableModel) {
		//start the edit dialog box and set the style of dialog box to Windows style, report error of exception occur
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			EditDialogBox dialog = new EditDialogBox(phonebook, index, tableModel);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditDialogBox(LinkedList<Contact> phonebook, int index, TableModel tableModel) {

		//set the frame and content panel of the dialog box
		this.setTitle("Edit Contact");
		this.setIconImage(new ImageIcon(Objects.requireNonNull(EditDialogBox.class.getResource("/com/assets/contact (1).png"))).getImage());
		setBounds(100, 100, 629, 681);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(10, 10, 610, 85);
		contentPanel.add(titlePanel);
		titlePanel.setLayout(null);

		//image beside the title of create dialog box
		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(Objects.requireNonNull(EditDialogBox.class.getResource("/com/assets/pencil (1).png"))));
		icon.setBounds(235, 10, 60, 60);
		titlePanel.add(icon);

		//title of the create dialog box
		JLabel editLabel = new JLabel("Edit");
		editLabel.setFont(new RobotoFont(36).boldRoboto());
		editLabel.setBounds(305, 19, 71, 51);
		titlePanel.add(editLabel);
		
		JPanel formPanel = new JPanel();
		formPanel.setBounds(10, 95, 605, 486);
		contentPanel.add(formPanel);
		formPanel.setLayout(null);

		//set the name in the text field
		JTextField nameTextField = new JTextField();
		nameTextField.setToolTipText("Name of the contacts.");
		nameTextField.setFont(new RobotoFont(16).mediumRoboto());
		nameTextField.setBounds(171, 10, 377, 30);
		formPanel.add(nameTextField);
		nameTextField.setColumns(10);
		nameTextField.setText(phonebook.get(index).getName());

		//label for the name
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setFont(new RobotoFont(16).mediumRoboto());
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setBounds(104, 11, 57, 28);
		formPanel.add(nameLabel);

		//label for the gender
		JLabel genderLabel = new JLabel("Gender:");
		genderLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		genderLabel.setFont(new RobotoFont(16).mediumRoboto());
		genderLabel.setBounds(104, 51, 57, 28);
		formPanel.add(genderLabel);

		//combo box for the gender
		JComboBox genderComboBox = new JComboBox();
		genderComboBox.setToolTipText("Please choose a gender.");
		genderComboBox.setFont(new RobotoFont(16).mediumRoboto());
		genderComboBox.setModel(new DefaultComboBoxModel(new String[] {"Please choose a gender.", "Male", "Female"}));
		genderComboBox.setBounds(171, 50, 201, 30);
		formPanel.add(genderComboBox);
		//if the gender is unknown then set combo box to "Please choose a gender."
		if (phonebook.get(index).getGender().equals("Unknown")){
			genderComboBox.setSelectedIndex(0);
		} else {
			genderComboBox.setSelectedItem(phonebook.get(index).getGender());
		}

		//separate the date into day, month and year
		int[] dayMonthYear = new Operation(phonebook).dateSeparator(phonebook.get(index).getDobDate());
		int day_separated = dayMonthYear[0];
		int month_separated = dayMonthYear[1];
		int year_separated = dayMonthYear[2];

		//label for the birthday date
		JLabel dobLabel = new JLabel("DOB:");
		dobLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dobLabel.setFont(new RobotoFont(16).mediumRoboto());
		dobLabel.setBounds(104, 91, 57, 28);
		formPanel.add(dobLabel);

		//combo box for birthday day
		JComboBox dayComboBox = new JComboBox();
		dayComboBox.setToolTipText("Day of Birth");
		//vector that contain number 1-31
		Vector day=new Vector();
        day.add("Day");
        for (int i = 1; i <= 31; i++) {
            day.add(i);
        }
		//vector that contain 1-31 is inserted into the day combo box
        dayComboBox.setModel(new DefaultComboBoxModel(day));
		dayComboBox.setFont(new RobotoFont(16).mediumRoboto());
		dayComboBox.setBounds(171, 90, 79, 30);
		formPanel.add(dayComboBox);
		//set the combo box with day of birth
		dayComboBox.setSelectedIndex(day_separated);

		//combo box for birthday month
		JComboBox monthComboBox = new JComboBox();
		monthComboBox.setToolTipText("Month of Birth");
		monthComboBox.setFont(new RobotoFont(16).mediumRoboto());
		//vector that contain number 1-12
		Vector month=new Vector();
        month.add("Month");
        for (int i = 1; i <= 12; i++) {
            month.add(i);
        }
        monthComboBox.setModel(new DefaultComboBoxModel(month));
		monthComboBox.setBounds(260, 90, 79, 30);
		formPanel.add(monthComboBox);
		//set the birthday month
		monthComboBox.setSelectedIndex(month_separated);

		//combo box for birthday year
		JComboBox yearComboBox = new JComboBox();  
		yearComboBox.setToolTipText("Year of Birth");
		yearComboBox.setFont(new RobotoFont(16).mediumRoboto());
		//vector that contain number 1930-2022
        Vector year=new Vector();
        year.add("Year");
        for (int i = 2022; i >= 1930; i--) {
            year.add(i);
        }
		//vector that contain 1930-2022 is inserted into the year combo box
        yearComboBox.setModel(new DefaultComboBoxModel(year));
		yearComboBox.setBounds(349, 90, 79, 30);
		formPanel.add(yearComboBox);

		//if the dob date is not set then set day, month and year combo box to index 0 of the combo box
		if (day_separated == 0 && month_separated == 0 && year_separated == 0){
			dayComboBox.setSelectedIndex(0);
			monthComboBox.setSelectedIndex(0);
			yearComboBox.setSelectedIndex(0);
		} else {
			yearComboBox.setSelectedIndex(2022-year_separated+1);
		}

		//label for mobile phone number
		JLabel hpNo1Label = new JLabel("Mobile Phone No.:");
		hpNo1Label.setHorizontalAlignment(SwingConstants.RIGHT);
		hpNo1Label.setFont(new RobotoFont(16).mediumRoboto());
		hpNo1Label.setBounds(10, 131, 151, 28);
		formPanel.add(hpNo1Label);

		//text field for mobile phone number
		JTextField mobileTextField = new JTextField();
		mobileTextField.setToolTipText("Mobile phone number.");
		mobileTextField.setFont(new RobotoFont(16).mediumRoboto());
		mobileTextField.setColumns(10);
		mobileTextField.setBounds(171, 130, 377, 30);
		formPanel.add(mobileTextField);
		//set the mobile phone number in text field
		mobileTextField.setText(phonebook.get(index).getMobilePhone());

		//label for work phone number
		JLabel workLabel = new JLabel("Work Phone No.:");
		workLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		workLabel.setFont(new RobotoFont(16).mediumRoboto());
		workLabel.setBounds(10, 170, 151, 28);
		formPanel.add(workLabel);

		//text field for work phone number
		JTextField workTextField = new JTextField();
		workTextField.setToolTipText("Work phone number");
		workTextField.setFont(new RobotoFont(16).mediumRoboto());
		workTextField.setColumns(10);
		workTextField.setBounds(171, 169, 377, 30);
		formPanel.add(workTextField);
		//set the work phone number in text field
		workTextField.setText(phonebook.get(index).getWorkPhone());

		//label for home phone number
		JLabel homeLabel = new JLabel("Home Phone No.:");
		homeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		homeLabel.setFont(new RobotoFont(16).mediumRoboto());
		homeLabel.setBounds(10, 209, 151, 28);
		formPanel.add(homeLabel);

		//text field for home phone number
		JTextField homeTextField = new JTextField();
		homeTextField.setToolTipText("Home phone number.");
		homeTextField.setFont(new RobotoFont(16).mediumRoboto());
		homeTextField.setColumns(10);
		homeTextField.setBounds(171, 208, 377, 30);
		formPanel.add(homeTextField);
		//set the home phone number in text field
		homeTextField.setText(phonebook.get(index).getHomePhone());

		//label for the email
		JLabel emailAddress = new JLabel("Email Address:");
		emailAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		emailAddress.setFont(new RobotoFont(16).mediumRoboto());
		emailAddress.setBounds(27, 248, 134, 28);
		formPanel.add(emailAddress);

		//email address text field
		JTextField emailAddressTextField = new JTextField();
		emailAddressTextField.setToolTipText("Email address.");
		emailAddressTextField.setFont(new RobotoFont(16).mediumRoboto());
		emailAddressTextField.setColumns(10);
		emailAddressTextField.setBounds(171, 247, 377, 30);
		formPanel.add(emailAddressTextField);
		//set the email address in text field
		emailAddressTextField.setText(phonebook.get(index).getEmail());

		//label of address
		JLabel addressLabel = new JLabel("Address:");
		addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		addressLabel.setFont(new RobotoFont(16).mediumRoboto());
		addressLabel.setBounds(27, 287, 134, 28);
		formPanel.add(addressLabel);

		//text area of address
		JTextArea addressTextArea = new JTextArea();
		addressTextArea.setToolTipText("Address of home.");
		addressTextArea.setLineWrap(true);
		addressTextArea.setFont(new RobotoFont(16).mediumRoboto());
		addressTextArea.setBounds(171, 287, 377, 90);
		formPanel.add(addressTextArea);
		//set the address in the text area
		addressTextArea.setText(phonebook.get(index).getAddress());

		//label of remark
		JLabel remarkLabel = new JLabel("Remark:");
		remarkLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		remarkLabel.setFont(new RobotoFont(16).mediumRoboto());
		remarkLabel.setBounds(27, 387, 134, 28);
		formPanel.add(remarkLabel);

		//text area of remark
		JTextArea remarkTextArea = new JTextArea();
		remarkTextArea.setToolTipText("Remarks.");
		remarkTextArea.setLineWrap(true);
		remarkTextArea.setFont(new RobotoFont(16).mediumRoboto());
		remarkTextArea.setBounds(171, 387, 377, 90);
		formPanel.add(remarkTextArea);
		//set the remark in the text area
		remarkTextArea.setText(phonebook.get(index).getRemark());

		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(10, 591, 610, 46);
		contentPanel.add(buttonPane);
		buttonPane.setLayout(null);

		//ok button
		JButton okButton = new JButton("OK");
		okButton.setFont(new RobotoFont(16).mediumRoboto());
		okButton.setBounds(185, 10, 100, 30);
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		//listener for mouse click at ok button
		okButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Operation operation = new Operation(phonebook);
				//get the value of all text field, text area and combo box
				String name = nameTextField.getText();
				String gender = genderComboBox.getSelectedItem().toString();
				String dayString = dayComboBox.getSelectedItem().toString();
				String monthString = monthComboBox.getSelectedItem().toString();
				String yearString = yearComboBox.getSelectedItem().toString();
				//day, month and year default value is 0
				int dayVariable = 0;
				int monthVariable = 0;
				int yearVariable = 0;
				String mobilePhone = mobileTextField.getText();
				String workPhone = workTextField.getText();
				String homePhone = homeTextField.getText();
				String email = emailAddressTextField.getText();
				String address = addressTextArea.getText();
				String remark = remarkTextArea.getText();

				//name set to " " if not filled
				if (name.equals("")){
					name = " ";
				}
				//gender set to unknown if not selected
				if (gender.equals("Please choose a gender.")){
					gender = "Unknown";
				}
				//dob day set to 0 if not selected
				if ((!dayString.equals("Day"))){
					dayVariable = Integer.parseInt(dayString);
				}
				//dob month set to 0 if not selected
				if ((!monthString.equals("Month"))){
					monthVariable = Integer.parseInt(monthString);
				}
				//dob year set to 0 if not selected
				if ((!yearString.equals("Year"))){
					yearVariable = Integer.parseInt(yearString);
				}
				if (dayVariable == 0 || monthVariable == 0 || yearVariable == 0){
					dayVariable = 0;
					monthVariable = 0;
					yearVariable = 0;
				}
				//mobile phone set to " " if not filled
				if (mobilePhone.equals("")){
					mobilePhone = " ";
				}
				//work phone set to " " if not filled
				if (workPhone.equals("")){
					workPhone = " ";
				}
				//home phone set to " " if not filled
				if (homePhone.equals("")){
					homePhone = " ";
				}
				//email set to " " if not filled
				if (email.equals("")){
					email = " ";
				}
				//address set to " " if not filled
				if (address.equals("")){
					address = " ";
				}
				//remark set to " " if not filled
				if (remark.equals("")){
					remark = " ";
				}

				//check if all attributes is not filled or selected, a notification will show to warn no data is filled or selected
				if (name.equals(" ") && gender.equals("Unknown") && dayVariable==0 && monthVariable==0 && yearVariable==0 && mobilePhone.equals(" ") && workPhone.equals(" ") && homePhone.equals(" ") && email.equals(" ") && address.equals(" ") && remark.equals(" ")){
					JOptionPane.showMessageDialog(null,"All attributes of edited contact cannot be empty.","Warning",JOptionPane.INFORMATION_MESSAGE);
				}else { //if one or more attributes is filled or selected
					if (operation.checkDate(dayVariable,monthVariable,yearVariable)){ //check the date is valid or not
						//if date is valid then create new contact
						try{
							//change the content in contact by using editContact method in operation class
							operation.editContact(index,name, gender, dayVariable,monthVariable,yearVariable, mobilePhone,workPhone,homePhone,email,address,remark);
							//after create then refresh the JTable in menu
							tableModel.setRowCount();
							tableModel.fireTableStructureChanged();
							dispose();
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null,"Error occurred when contact is edited, the contact is not created. Please try again.","Warning",JOptionPane.WARNING_MESSAGE);
							throw new RuntimeException(ex);
						}
					} else {
						//if date is not valid then show option pane to warn user
						JOptionPane.showMessageDialog(null,"The date is not valid.","Warning",JOptionPane.INFORMATION_MESSAGE);
					}

				}

			}
		});

		//cancel button of dialog box
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new RobotoFont(16).mediumRoboto());
		cancelButton.setBounds(325, 10, 100, 30);
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		//if cancel button is clicked then the frame close
		cancelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});

	}

}