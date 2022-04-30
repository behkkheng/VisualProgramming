package com;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class SortDialogBox extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SortDialogBox dialog = new SortDialogBox();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SortDialogBox() {
		setBounds(100, 100, 640, 325);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel titlePanel = new JPanel();
			titlePanel.setBounds(10, 10, 610, 85);
			contentPanel.add(titlePanel);
			titlePanel.setLayout(null);
			{
				JLabel sortIcon = new JLabel("");
				sortIcon.setIcon(new ImageIcon(SortDialogBox.class.getResource("/com/assets/sort-down (1).png")));
				sortIcon.setBounds(235, 10, 60, 60);
				titlePanel.add(sortIcon);
			}
			{
				JLabel sortLabel = new JLabel("Sort");
				sortLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
				sortLabel.setBounds(305, 10, 76, 60);
				titlePanel.add(sortLabel);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBounds(10, 93, 610, 140);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel sortLabel = new JLabel("Sort according to: ");
				sortLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				sortLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
				sortLabel.setBounds(105, 10, 190, 50);
				panel.add(sortLabel);
			}
			{
				JLabel orderLabel = new JLabel("Order by: ");
				orderLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				orderLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
				orderLabel.setBounds(105, 70, 190, 50);
				panel.add(orderLabel);
			}
			
			JComboBox sortComboBox = new JComboBox();
			sortComboBox.setToolTipText("Item to sort.");
			sortComboBox.setBounds(305, 25, 167, 28);
			panel.add(sortComboBox);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setToolTipText("Order");
			comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Ascending Order", "Descending Order"}));
			comboBox.setBounds(305, 85, 167, 28);
			panel.add(comboBox);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(10, 234, 610, 46);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton okButton = new JButton("OK");
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
				okButton.setBounds(325, 10, 100, 30);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
				cancelButton.setBounds(185, 10, 100, 30);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
