package com;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class AboutUs extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			//start the about us dialog frame and set the ui style to Windows, report error if exception is met
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			AboutUs dialog = new AboutUs();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
			dialog.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AboutUs() {
		//set up the frame and content panel
		this.setTitle("About Us");
		this.setIconImage(new ImageIcon(Objects.requireNonNull(AboutUs.class.getResource("/com/assets/contact (1).png"))).getImage());
		setBounds(100, 100, 557, 345);
		getContentPane().setLayout(null);
		JPanel contentPanel = new JPanel();
		contentPanel.setBounds(0, 0, 541, 247);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			//the credits text
			JLabel credit = new JLabel("<html><div style='text-align: center;'>This Phone Book Management System is done by Group 2<br>\r\n<br>\r\nBeh Kah Kheng AI200292<br>\r\nLim Yan Yun AI200051<br>\r\nAyani Chong Binti Muhammad Amin Chong AI200311<br>\r\nDivya a/l Murugan AI200166<br>\r\nKhoo Wen Bin CI200009<br>\r\nNur Hilda Binti Zaidi CI200037</html>");
			credit.setHorizontalAlignment(SwingConstants.CENTER);
			credit.setVerticalAlignment(SwingConstants.CENTER);
			credit.setFont(new RobotoFont(19).boldRoboto());
			credit.setBounds(10, 10, 521, 237);
			contentPanel.add(credit);
		}
		{
			//button panel and its button
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(10, 257, 531, 50);
			getContentPane().add(buttonPane);
			{
				//ok button
				JButton okButton = new JButton("OK");
				okButton.setFont(new RobotoFont(16).mediumRoboto());
				okButton.setLocation(215, 10);
				okButton.setSize(100, 30);
				//if ok button is clicked then the frame is closed
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.setLayout(null);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
