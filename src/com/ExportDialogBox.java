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

public class ExportDialogBox extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			ExportDialogBox dialog = new ExportDialogBox();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ExportDialogBox() {
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
				sortIcon.setIcon(new ImageIcon(ExportDialogBox.class.getResource("/com/assets/export (1).png")));
				sortIcon.setBounds(224, 10, 60, 60);
				titlePanel.add(sortIcon);
			}
			{
				JLabel exportLabel = new JLabel("Export");
				exportLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
				exportLabel.setBounds(294, 10, 109, 60);
				titlePanel.add(exportLabel);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBounds(10, 93, 610, 140);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel formatLabel = new JLabel("Export format:");
				formatLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				formatLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
				formatLabel.setBounds(105, 10, 190, 50);
				panel.add(formatLabel);
			}
			{
				JLabel exportLabel = new JLabel("Export from:");
				exportLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				exportLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
				exportLabel.setBounds(105, 70, 190, 50);
				panel.add(exportLabel);
			}
			
			JComboBox sortComboBox = new JComboBox();
			sortComboBox.setToolTipText("Select a format for export file.");
			sortComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
			sortComboBox.setModel(new DefaultComboBoxModel(new String[] {"Select a format", "txt", "csv"}));
			sortComboBox.setBounds(305, 25, 153, 28);
			panel.add(sortComboBox);
			
			JButton browseButton = new JButton("Browse");
			browseButton.setToolTipText("Browse a location from computer.");
			browseButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
			browseButton.setActionCommand("OK");
			browseButton.setBounds(305, 82, 100, 30);
			panel.add(browseButton);
			{
				JLabel validLabel = new JLabel("Location valid.");
				validLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				validLabel.setBounds(460, 82, 111, 30);
				panel.add(validLabel);
			}
			{
				JLabel tickLabel = new JLabel("");
				tickLabel.setIcon(new ImageIcon(ExportDialogBox.class.getResource("/com/assets/accept (1).png")));
				tickLabel.setBounds(420, 82, 30, 30);
				panel.add(tickLabel);
			}
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
