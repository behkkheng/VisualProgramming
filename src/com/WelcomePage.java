package com;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class WelcomePage extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//create frame of welcome page and execute the constructor, if failed then print error
					WelcomePage frame = new WelcomePage();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
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
		//set the frame and content panel of welcome page
		this.setTitle("Phone Book Management System");
		this.setIconImage(new ImageIcon(Objects.requireNonNull(WelcomePage.class.getResource("/com/assets/contact (1).png"))).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1220, 685);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//The text in welcome page
		JLabel welcomeText = new JLabel("<html><div style='text-align: center;'>Welcome to PhoneBook Management System<br>\r\n<br>\r\n<div style='text-align: center; font-size: 20px;'>Click anywhere to continue...</small><html>");
		welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeText.setForeground(Color.WHITE);
		welcomeText.setFont(new RobotoFont(41).boldRoboto());
		welcomeText.setBounds(279, 202, 675, 239);
		contentPane.add(welcomeText);

		//background of the phone book management system
		JLabel background = new JLabel("");
		background.setHorizontalAlignment(SwingConstants.RIGHT);
		background.setIcon(new ImageIcon(Objects.requireNonNull(WelcomePage.class.getResource("/com/assets/telephone (1).jpg"))));
		background.setBounds(0, 0, 1206, 648);
		contentPane.add(background);
		//if background is clicked then it will close and redirect to menu
		background.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Menu.main(null);
				dispose();
			}
		});
		
	}

}
