package com;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class ExportDialogBox extends JDialog {

	private String fileLocation = "";

	/**
	 * Launch the application.
	 */
	public static void main(LinkedList<Contact> phonebook) {
		try {
			//start the export dialog box and set the style of dialog box to Windows style, report error of exception occur
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			ExportDialogBox dialog = new ExportDialogBox(phonebook);
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
	public ExportDialogBox(LinkedList<Contact> phonebook) {

		//set the frame and content panel of the dialog box
		this.setTitle("Export Contact");
		this.setIconImage(new ImageIcon(Objects.requireNonNull(ExportDialogBox.class.getResource("/com/assets/contact (1).png"))).getImage());
		setBounds(100, 100, 640, 325);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(10, 10, 610, 85);
		contentPanel.add(titlePanel);
		titlePanel.setLayout(null);

		//image beside the title of export dialog box
		JLabel sortIcon = new JLabel("");
		sortIcon.setIcon(new ImageIcon(Objects.requireNonNull(ExportDialogBox.class.getResource("/com/assets/export (1).png"))));
		sortIcon.setBounds(224, 10, 60, 60);
		titlePanel.add(sortIcon);

		//title of the create dialog box
		JLabel exportLabel = new JLabel("Export");
		exportLabel.setFont(new RobotoFont(36).boldRoboto());
		exportLabel.setBounds(294, 10, 109, 60);
		titlePanel.add(exportLabel);

		JPanel panel = new JPanel();
		panel.setBounds(10, 93, 610, 140);
		contentPanel.add(panel);
		panel.setLayout(null);

		//label for export format
		JLabel formatLabel = new JLabel("Export format:");
		formatLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		formatLabel.setFont(new RobotoFont(20).mediumRoboto());
		formatLabel.setBounds(105, 10, 190, 50);
		panel.add(formatLabel);

		//label for export to
		JLabel exportLocationLabel = new JLabel("Export to:");
		exportLocationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		exportLocationLabel.setFont(new RobotoFont(20).mediumRoboto());
		exportLocationLabel.setBounds(105, 70, 190, 50);
		panel.add(exportLocationLabel);

		//a hidden text mention location valid
		JLabel validLabel = new JLabel("Location valid.");
		validLabel.setFont(new RobotoFont(16).mediumRoboto());
		validLabel.setBounds(460, 82, 111, 30);
		panel.add(validLabel);
		validLabel.setVisible(false);

		// a hidden tick image
		JLabel tickLabel = new JLabel("");
		tickLabel.setIcon(new ImageIcon(Objects.requireNonNull(ExportDialogBox.class.getResource("/com/assets/accept (1).png"))));
		tickLabel.setBounds(420, 82, 30, 30);
		panel.add(tickLabel);
		tickLabel.setVisible(false);

		//combo box for file format
		JComboBox fileFormatComboBox = new JComboBox();
		fileFormatComboBox.setToolTipText("Select a format for export file.");
		fileFormatComboBox.setFont(new RobotoFont(16).mediumRoboto());
		fileFormatComboBox.setModel(new DefaultComboBoxModel(new String[] {"Select a format.", "txt", "csv"}));
		fileFormatComboBox.setBounds(305, 25, 153, 28);
		panel.add(fileFormatComboBox);

		//browse button
		JButton browseButton = new JButton("Browse");
		browseButton.setToolTipText("Browse a location from computer.");
		browseButton.setFont(new RobotoFont(16).mediumRoboto());
		browseButton.setActionCommand("OK");
		browseButton.setBounds(305, 82, 100, 30);
		panel.add(browseButton);
		browseButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//file chooser pop up and let user choose a location to save
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = chooser.showOpenDialog(null);
				//if location is valid
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					//get the file location and save it in file location instance variable
					String fileLocation = chooser.getSelectedFile().getAbsolutePath();
					setFileLocation(fileLocation);
					//hidden location valid and tick label are shown
					validLabel.setVisible(true);
					tickLabel.setVisible(true);
				}
			}
		});

		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(10, 234, 610, 46);
		contentPanel.add(buttonPane);
		buttonPane.setLayout(null);

		//ok button
		JButton okButton = new JButton("OK");
		okButton.setFont(new RobotoFont(16).mediumRoboto());
		okButton.setBounds(325, 10, 100, 30);
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		//export contact file after ok button is clicked
		okButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String fileFormat = (String) fileFormatComboBox.getSelectedItem();

				if (fileFormat.equals("Select a format.")){//if file format is not chosen, option pane will tell user to choose again
					JOptionPane.showMessageDialog(null,"Please select a valid file format.","Warning",JOptionPane.INFORMATION_MESSAGE);
				} else if (fileLocation.equals("")){//if file location is not chosen, option pane will tell user to choose again
					JOptionPane.showMessageDialog(null,"Please select a valid file location.","Warning",JOptionPane.INFORMATION_MESSAGE);
				} else {//if file format and location is set
					try {
						//export file by calling exportContact from operation class, show success option pane and close export dialog box.
						Operation operation = new Operation(phonebook);
						operation.exportContact(fileFormat,fileLocation);
						dispose();
						JOptionPane.showMessageDialog(null,"File export success!","Success",JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException ex) {
						//if failed then show warning message
						JOptionPane.showMessageDialog(null,"There is a problem when export the file.\nPlease try again.","Warning",JOptionPane.INFORMATION_MESSAGE);
						throw new RuntimeException(ex);
					}
				}
			}
		});

		//cancel button
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new RobotoFont(16).mediumRoboto());
		cancelButton.setBounds(185, 10, 100, 30);
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		//close the export dialog box if clicked
		cancelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
	}

	//set the instance variable file location
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
}
