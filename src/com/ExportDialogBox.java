package com;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class ExportDialogBox extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private LinkedList<Contact> phonebook;
	private String fileFormat;
	private String fileLocation;

	/**
	 * Launch the application.
	 */
	public static void main(LinkedList<Contact> phonebook) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			ExportDialogBox dialog = new ExportDialogBox(phonebook);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ExportDialogBox(LinkedList<Contact> phonebook) {
		this.phonebook = phonebook;
		this.setTitle("Export Contact");
		this.setIconImage(new ImageIcon(AboutUs.class.getResource("/com/assets/contact (1).png")).getImage());
		this.setLocationRelativeTo(null);
		setBounds(100, 100, 640, 325);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(10, 10, 610, 85);
		contentPanel.add(titlePanel);
		titlePanel.setLayout(null);

		JLabel sortIcon = new JLabel("");
		sortIcon.setIcon(new ImageIcon(ExportDialogBox.class.getResource("/com/assets/export (1).png")));
		sortIcon.setBounds(224, 10, 60, 60);
		titlePanel.add(sortIcon);

		JLabel exportLabel = new JLabel("Export");
		exportLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
		exportLabel.setBounds(294, 10, 109, 60);
		titlePanel.add(exportLabel);

		JPanel panel = new JPanel();
		panel.setBounds(10, 93, 610, 140);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel formatLabel = new JLabel("Export format:");
		formatLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		formatLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		formatLabel.setBounds(105, 10, 190, 50);
		panel.add(formatLabel);


		JLabel exportLocationLabel = new JLabel("Export to:");
		exportLocationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		exportLocationLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		exportLocationLabel.setBounds(105, 70, 190, 50);
		panel.add(exportLocationLabel);

		JLabel validLabel = new JLabel("Location valid.");
		validLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		validLabel.setBounds(460, 82, 111, 30);
		panel.add(validLabel);
		validLabel.setVisible(false);

		JLabel tickLabel = new JLabel("");
		tickLabel.setIcon(new ImageIcon(ExportDialogBox.class.getResource("/com/assets/accept (1).png")));
		tickLabel.setBounds(420, 82, 30, 30);
		panel.add(tickLabel);
		tickLabel.setVisible(false);
			
		JComboBox fileFormatComboBox = new JComboBox();
		fileFormatComboBox.setToolTipText("Select a format for export file.");
		fileFormatComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fileFormatComboBox.setModel(new DefaultComboBoxModel(new String[] {"Select a format", "txt", "csv"}));
		fileFormatComboBox.setBounds(305, 25, 153, 28);
		panel.add(fileFormatComboBox);
			
		JButton browseButton = new JButton("Browse");
		browseButton.setToolTipText("Browse a location from computer.");
		browseButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		browseButton.setActionCommand("OK");
		browseButton.setBounds(305, 82, 100, 30);
		panel.add(browseButton);
		browseButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = chooser.showOpenDialog(browseButton);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					String fileLocation = chooser.getSelectedFile().getAbsolutePath();
					String fileFormat = (String) fileFormatComboBox.getSelectedItem();
					setFileFormat(fileFormat);
					setFileLocation(fileLocation);
					validLabel.setVisible(true);
					tickLabel.setVisible(true);
				}
			}
		});

		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(10, 234, 610, 46);
		contentPanel.add(buttonPane);
		buttonPane.setLayout(null);

		JButton okButton = new JButton("OK");
		okButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		okButton.setBounds(325, 10, 100, 30);
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		okButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Operation operation = new Operation(phonebook);
				try {
					operation.exportContact(fileFormat,fileLocation);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
				dispose();
			}
		});


		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cancelButton.setBounds(185, 10, 100, 30);
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		cancelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
}
