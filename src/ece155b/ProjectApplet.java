package ece155b;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ProjectApplet {

	private JFrame frame;
	private JTextField companyName_textbox;
	private JTextField contactInfo_textbox;
	private JTextField address_textbox;
	private JTable soldToCust_textbox;
	private JTable itemNeeded_textbox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjectApplet window = new ProjectApplet();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ProjectApplet() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btnNewButton = new JButton("Add a row");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(55, 495, 115, 29);

		JLabel lblNewLabel = new JLabel("Company Information");
		lblNewLabel.setBounds(40, 16, 156, 20);

		JLabel lblCompanyName = new JLabel("Company Name : ");
		lblCompanyName.setBounds(40, 52, 129, 20);

		JLabel lblNewLabel_1 = new JLabel("Contact Info : ");
		lblNewLabel_1.setBounds(40, 88, 103, 20);

		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setBounds(40, 124, 68, 20);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnNewButton);
		frame.getContentPane().add(lblNewLabel);
		frame.getContentPane().add(lblCompanyName);
		frame.getContentPane().add(lblNewLabel_1);
		frame.getContentPane().add(lblAddress);

		companyName_textbox = new JTextField();
		companyName_textbox.setBounds(189, 49, 261, 23);
		frame.getContentPane().add(companyName_textbox);
		companyName_textbox.setColumns(10);

		contactInfo_textbox = new JTextField();
		contactInfo_textbox.setColumns(10);
		contactInfo_textbox.setBounds(189, 86, 261, 23);
		frame.getContentPane().add(contactInfo_textbox);

		address_textbox = new JTextField();
		address_textbox.setColumns(10);
		address_textbox.setBounds(189, 124, 510, 62);
		frame.getContentPane().add(address_textbox);

		JLabel lblNewLabel_2 = new JLabel("Items sold to customer");
		lblNewLabel_2.setBounds(15, 235, 269, 29);
		frame.getContentPane().add(lblNewLabel_2);

		soldToCust_textbox = new JTable();
		soldToCust_textbox.setBounds(25, 276, 443, 203);
		frame.getContentPane().add(soldToCust_textbox);

		JLabel lblNewLabel_3 = new JLabel("Items needed from providers  ");
		lblNewLabel_3.setBounds(582, 239, 238, 20);
		frame.getContentPane().add(lblNewLabel_3);

		JButton btnNewButton_1 = new JButton("Delete selected row");
		btnNewButton_1.setBounds(266, 495, 184, 29);
		frame.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Add a row");
		btnNewButton_2.setBounds(633, 495, 115, 29);
		frame.getContentPane().add(btnNewButton_2);

		itemNeeded_textbox = new JTable();
		itemNeeded_textbox.setBounds(582, 276, 521, 203);
		frame.getContentPane().add(itemNeeded_textbox);

		JButton btnDeleteSelectedRow = new JButton("Delete selected row");
		btnDeleteSelectedRow.setBounds(839, 495, 196, 29);
		frame.getContentPane().add(btnDeleteSelectedRow);

		JButton btnSaveInformation = new JButton("Save Information");
		btnSaveInformation.setBounds(40, 607, 174, 29);
		frame.getContentPane().add(btnSaveInformation);

		JButton btnLoadInformation = new JButton("Load Information");
		btnLoadInformation.setBounds(266, 607, 174, 29);
		frame.getContentPane().add(btnLoadInformation);
	}
}
