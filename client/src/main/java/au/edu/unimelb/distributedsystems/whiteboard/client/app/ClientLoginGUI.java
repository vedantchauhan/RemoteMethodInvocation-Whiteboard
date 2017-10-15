package au.edu.unimelb.distributedsystems.whiteboard.client.app;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ClientLoginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField userName;
	private JTextField password;
	private JTextField ipAddress;
	private JTextField portNo;
	private JButton btnNewWhiteboard;
	private JButton btnJoinWhiteboard;
	private JButton btnCancel;
	public static PaintApplication paint; 

	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientLoginGUI frame = new ClientLoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public ClientLoginGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 371, 326);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(5, 5, 424, 0);
		contentPane.add(label);
		
		userName = new JTextField();
		userName.setBounds(148, 16, 178, 20);
		contentPane.add(userName);
		userName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(25, 19, 86, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(25, 51, 86, 14);
		contentPane.add(lblPassword);
		
		password = new JTextField();
		password.setBounds(148, 48, 178, 20);
		contentPane.add(password);
		password.setColumns(10);
		
		JLabel lblIpAddress = new JLabel("Ip Address");
		lblIpAddress.setBounds(25, 82, 86, 14);
		contentPane.add(lblIpAddress);
		
		ipAddress = new JTextField();
		ipAddress.setBounds(148, 79, 178, 20);
		contentPane.add(ipAddress);
		ipAddress.setColumns(10);
		
		JLabel lblPortNumber = new JLabel("Port Number");
		lblPortNumber.setBounds(25, 120, 86, 14);
		contentPane.add(lblPortNumber);
		
		portNo = new JTextField();
		portNo.setBounds(148, 117, 178, 20);
		contentPane.add(portNo);
		portNo.setColumns(10);
		
		JButton btnNewWhiteboard = new JButton("New Whiteboard");
		btnNewWhiteboard.setBounds(117, 159, 122, 23);
		btnNewWhiteboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					newWhiteboard();
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		});
		contentPane.add(btnNewWhiteboard);
		
		JButton btnJoinWhiteboard = new JButton("Join Whiteboard");
		btnJoinWhiteboard.setBounds(117, 198, 122, 23);
		btnJoinWhiteboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					joinWhiteboard();
				}  catch (Exception e) {
					e.printStackTrace();
				} 
			}
		});
		contentPane.add(btnJoinWhiteboard);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(117, 238, 122, 23);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					System.exit(0);
				}  catch (Exception e) {
					e.printStackTrace();
				} 
			}
		});
		contentPane.add(btnCancel);
	}
	
	private void newWhiteboard() {
		paint = new PaintApplication();
		this.setVisible(false);
	}
	
	private void joinWhiteboard() {
		paint = new PaintApplication(false);
		this.setVisible(false);
	}
}
