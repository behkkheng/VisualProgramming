package com;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Vector;

public class DetailDialogBox extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private LinkedList<Contact> phonebook;

	/**
	 * Launch the application.
	 */
	public static void main(LinkedList<Contact> phonebook, int index) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			DetailDialogBox dialog = new DetailDialogBox(phonebook, index);
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
	public DetailDialogBox(LinkedList<Contact> phonebook, int index) {
		this.phonebook = phonebook;

		this.setTitle("Detail of Contact");
		this.setIconImage(new ImageIcon(DetailDialogBox.class.getResource("/com/assets/contact (1).png")).getImage());
		setBounds(100, 100, 551, 760);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(10, 663, 531, 50);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton okButton = new JButton("OK");
				okButton.setFont(new RobotoFont(16).mediumRoboto());
				okButton.setBounds(215, 10, 100, 30);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						dispose();
					}
				});
			}
		}
		{
			JPanel titlePanel = new JPanel();
			titlePanel.setBounds(10, 10, 610, 85);
			contentPanel.add(titlePanel);
			titlePanel.setLayout(null);
			
			JLabel icon = new JLabel("");
			icon.setIcon(new ImageIcon(DetailDialogBox.class.getResource("/com/assets/list (1).png")));
			icon.setBounds(120, 10, 60, 60);
			titlePanel.add(icon);
			
			JLabel detailLabel = new JLabel("Detail of Contact");
			detailLabel.setFont(new RobotoFont(36).boldRoboto());
			detailLabel.setBounds(190, 19, 287, 51);
			titlePanel.add(detailLabel);
			
			JPanel formPanel = new JPanel();
			formPanel.setBounds(10, 95, 517, 570);
			contentPanel.add(formPanel);
			formPanel.setLayout(null);
			
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
			
			JLabel dobLabel = new JLabel("DOB:");
			dobLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			dobLabel.setFont(new RobotoFont(16).mediumRoboto());
			dobLabel.setBounds(104, 91, 57, 28);
			formPanel.add(dobLabel);
			Vector day=new Vector();
	        day.add("Day");
	        for (int i = 1; i <= 31; i++) {
	            day.add(i);
	        }
			Vector month=new Vector();
	        month.add("Month");
	        for (int i = 1; i <= 12; i++) {
	            month.add(i);
	        }
	        Vector year=new Vector();
	        year.add("Year");
	        for (int i = 2022; i >= 1930; i--) {
	            year.add(i);
	        }
			
			JLabel hpNo1Label = new JLabel("Mobile Phone No.:");
			hpNo1Label.setHorizontalAlignment(SwingConstants.RIGHT);
			hpNo1Label.setFont(new RobotoFont(16).mediumRoboto());
			hpNo1Label.setBounds(10, 131, 151, 28);
			formPanel.add(hpNo1Label);
			
			JLabel workLabel = new JLabel("Work Phone No.:");
			workLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			workLabel.setFont(new RobotoFont(16).mediumRoboto());
			workLabel.setBounds(10, 170, 151, 28);
			formPanel.add(workLabel);
			
			JLabel homeLabel = new JLabel("Home Phone No.:");
			homeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			homeLabel.setFont(new RobotoFont(16).mediumRoboto());
			homeLabel.setBounds(10, 209, 151, 28);
			formPanel.add(homeLabel);
			
			JLabel emailAddress = new JLabel("Email Address:");
			emailAddress.setHorizontalAlignment(SwingConstants.RIGHT);
			emailAddress.setFont(new RobotoFont(16).mediumRoboto());
			emailAddress.setBounds(27, 248, 134, 28);
			formPanel.add(emailAddress);
			
			JLabel addressLabel = new JLabel("Address:");
			addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			addressLabel.setFont(new RobotoFont(16).mediumRoboto());
			addressLabel.setBounds(27, 287, 134, 28);
			formPanel.add(addressLabel);
			
			JLabel remarkLabel = new JLabel("Remark:");
			remarkLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			remarkLabel.setFont(new RobotoFont(16).mediumRoboto());
			remarkLabel.setBounds(27, 405, 134, 28);
			formPanel.add(remarkLabel);
			
			JLabel showName = new JLabel(phonebook.get(index).getName());
			showName.setFont(new RobotoFont(16).mediumRoboto());
			showName.setBounds(171, 11, 336, 28);
			formPanel.add(showName);
			
			JLabel showGender = new JLabel(phonebook.get(index).getGender());
			showGender.setFont(new RobotoFont(16).mediumRoboto());
			showGender.setBounds(171, 51, 336, 28);
			formPanel.add(showGender);

			String dob = phonebook.get(index).getDobDate();
			if (phonebook.get(index).getDobDate().equals("0/0/0")){
				dob = " ";
			}
			JLabel showDOB = new JLabel(dob);
			showDOB.setFont(new RobotoFont(16).mediumRoboto());
			showDOB.setBounds(171, 91, 336, 28);
			formPanel.add(showDOB);
			
			JLabel showMobilePhone = new JLabel(phonebook.get(index).getMobilePhone());
			showMobilePhone.setFont(new RobotoFont(16).mediumRoboto());
			showMobilePhone.setBounds(171, 131, 336, 28);
			formPanel.add(showMobilePhone);
			
			JLabel showWorkPhone = new JLabel(phonebook.get(index).getWorkPhone());
			showWorkPhone.setFont(new RobotoFont(16).mediumRoboto());
			showWorkPhone.setBounds(171, 170, 336, 28);
			formPanel.add(showWorkPhone);
			
			JLabel showHomePhone = new JLabel(phonebook.get(index).getHomePhone());
			showHomePhone.setFont(new RobotoFont(16).mediumRoboto());
			showHomePhone.setBounds(171, 209, 336, 28);
			formPanel.add(showHomePhone);
			
			JLabel showEmailAddress = new JLabel(phonebook.get(index).getEmail());
			showEmailAddress.setFont(new RobotoFont(16).mediumRoboto());
			showEmailAddress.setBounds(171, 248, 336, 28);
			formPanel.add(showEmailAddress);
			
			JLabel showAddress = new JLabel(phonebook.get(index).getAddress());
			showAddress.setFont(new RobotoFont(16).mediumRoboto());
			showAddress.setBounds(171, 293, 336, 108);
			showAddress.setHorizontalAlignment(SwingConstants.LEFT);
			showAddress.setVerticalAlignment(SwingConstants.TOP);
			formPanel.add(showAddress);
			
			JLabel showRemark = new JLabel(phonebook.get(index).getRemark());
			showRemark.setFont(new RobotoFont(16).mediumRoboto());
			showRemark.setBounds(171, 411, 336, 155);
			showRemark.setHorizontalAlignment(SwingConstants.LEFT);
			showRemark.setVerticalAlignment(SwingConstants.TOP);
			formPanel.add(showRemark);
		}
	}

}
