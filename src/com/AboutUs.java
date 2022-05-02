package com;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AboutUs extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			AboutUs dialog = new AboutUs();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AboutUs() {
		this.setTitle("About Us");
		this.setIconImage(new ImageIcon(AboutUs.class.getResource("/com/assets/contact (1).png")).getImage());
		setBounds(100, 100, 557, 345);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 541, 247);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel credit = new JLabel("<html><div style='text-align: center;'>This Phone Book Management System is done by Group 2<br>\r\n<br>\r\nBeh Kah Kheng AI200292<br>\r\nLim Yan Yun AI200051<br>\r\nAyani Chong Binti Muhammad Amin Chong AI200311<br>\r\nDivya a/l Murugan AI200166<br>\r\nKhoo Wen Bin CI200009<br>\r\nNur Hilda Binti Zaidi CI200037</html>");
			credit.setHorizontalAlignment(SwingConstants.CENTER);
			credit.setFont(new Font("Tahoma", Font.PLAIN, 20));
			credit.setBounds(10, 10, 521, 237);
			contentPanel.add(credit);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(10, 257, 531, 50);
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
				okButton.setLocation(215, 10);
				okButton.setSize(100, 30);
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
