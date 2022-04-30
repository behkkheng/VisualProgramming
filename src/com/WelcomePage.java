package com;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class WelcomePage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomePage frame = new WelcomePage();
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
	public WelcomePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1220, 685);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel welcomeText = new JLabel("<html><div style='text-align: center;'>Welcome to PhoneBook Management System<br>\r\n<br>\r\n<div style='text-align: center; font-size: 20px;'>Click anywhere to continue...</small><html>");
		welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeText.setForeground(Color.WHITE);
		welcomeText.setFont(new Font("Tahoma", Font.BOLD, 41));
		welcomeText.setBounds(279, 202, 675, 239);
		contentPane.add(welcomeText);
		
		JLabel background = new JLabel("");
		background.setHorizontalAlignment(SwingConstants.RIGHT);
		background.setIcon(new ImageIcon(WelcomePage.class.getResource("/com/assets/telephone (1).jpg")));
		background.setBounds(0, 0, 1206, 648);
		contentPane.add(background);
		
		
	}

}
