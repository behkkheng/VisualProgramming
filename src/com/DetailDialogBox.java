package com;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class DetailDialogBox extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			DetailDialogBox dialog = new DetailDialogBox();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DetailDialogBox() {
		setBounds(100, 100, 551, 760);
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
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
				okButton.setBounds(215, 10, 100, 30);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
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
			detailLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
			detailLabel.setBounds(190, 19, 287, 51);
			titlePanel.add(detailLabel);
			
			JPanel formPanel = new JPanel();
			formPanel.setBounds(10, 95, 517, 570);
			contentPanel.add(formPanel);
			formPanel.setLayout(null);
			
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
			
			JLabel dobLabel = new JLabel("DOB:");
			dobLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			dobLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
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
			hpNo1Label.setFont(new Font("Tahoma", Font.PLAIN, 16));
			hpNo1Label.setBounds(10, 131, 151, 28);
			formPanel.add(hpNo1Label);
			
			JLabel workLabel = new JLabel("Work Phone No.:");
			workLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			workLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			workLabel.setBounds(10, 170, 151, 28);
			formPanel.add(workLabel);
			
			JLabel homeLabel = new JLabel("Home Phone No.:");
			homeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			homeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			homeLabel.setBounds(10, 209, 151, 28);
			formPanel.add(homeLabel);
			
			JLabel emailAddress = new JLabel("Email Address:");
			emailAddress.setHorizontalAlignment(SwingConstants.RIGHT);
			emailAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
			emailAddress.setBounds(27, 248, 134, 28);
			formPanel.add(emailAddress);
			
			JLabel addressLabel = new JLabel("Address:");
			addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			addressLabel.setBounds(27, 287, 134, 28);
			formPanel.add(addressLabel);
			
			JLabel remarkLabel = new JLabel("Remark:");
			remarkLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			remarkLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			remarkLabel.setBounds(27, 405, 134, 28);
			formPanel.add(remarkLabel);
			
			JLabel showName = new JLabel("");
			showName.setFont(new Font("Tahoma", Font.PLAIN, 16));
			showName.setBounds(171, 11, 336, 28);
			formPanel.add(showName);
			
			JLabel showGender = new JLabel("");
			showGender.setFont(new Font("Tahoma", Font.PLAIN, 16));
			showGender.setBounds(171, 51, 336, 28);
			formPanel.add(showGender);
			
			JLabel showDOB = new JLabel("");
			showDOB.setFont(new Font("Tahoma", Font.PLAIN, 16));
			showDOB.setBounds(171, 91, 336, 28);
			formPanel.add(showDOB);
			
			JLabel showMobilePhone = new JLabel("");
			showMobilePhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
			showMobilePhone.setBounds(171, 131, 336, 28);
			formPanel.add(showMobilePhone);
			
			JLabel showWorkPhone = new JLabel("");
			showWorkPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
			showWorkPhone.setBounds(171, 170, 336, 28);
			formPanel.add(showWorkPhone);
			
			JLabel showHomePhone = new JLabel("");
			showHomePhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
			showHomePhone.setBounds(171, 209, 336, 28);
			formPanel.add(showHomePhone);
			
			JLabel showEmailAddress = new JLabel("");
			showEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
			showEmailAddress.setBounds(171, 248, 336, 28);
			formPanel.add(showEmailAddress);
			
			JLabel showAddress = new JLabel("");
			showAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
			showAddress.setBounds(171, 287, 336, 108);
			formPanel.add(showAddress);
			
			JLabel showRemark = new JLabel("");
			showRemark.setFont(new Font("Tahoma", Font.PLAIN, 16));
			showRemark.setBounds(171, 405, 336, 155);
			formPanel.add(showRemark);
		}
	}

}
