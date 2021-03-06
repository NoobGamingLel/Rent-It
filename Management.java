/*This is the main class that the authentication process
 * If the user is a customer then display only the Item Table
 * If the user is an admin then display another frame called Menu
 * Provides a button to register for a new account
 */

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

@SuppressWarnings("serial")
public class Management extends JFrame{

	private JTextField usernameField;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	private final String customerFilepath = "./Database/Customer/CustomerList.csv";
	private final String adminFilepath = "./Database/Admin/AdminList.csv";
	
	public Management() {
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 450, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setTitle("Login");
		
		JLabel lblRentitLoginSystem = new JLabel("Login System");
		lblRentitLoginSystem.setForeground(Color.BLACK);
		lblRentitLoginSystem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRentitLoginSystem.setBounds(161, 162, 124, 25);
		getContentPane().add(lblRentitLoginSystem);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblUsername.setBounds(75, 188, 87, 36);
		getContentPane().add(lblUsername);
		
		usernameField = new JTextField();
		usernameField.setBounds(182, 198, 179, 20);
		getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPassword.setBounds(75, 235, 87, 24);
		getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(182, 240, 179, 20);
		getContentPane().add(passwordField);
		
		JButton btnCustomer = new JButton("Customer");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = usernameField.getText();
				char[] password = passwordField.getPassword();
				boolean isEmptyPassword = password.length==0;
				
				
				if(username.equals("")|| isEmptyPassword) {
					JOptionPane.showMessageDialog(null, "Please fill in both fields.", "Login error", JOptionPane.WARNING_MESSAGE);
				} else if(DatabaseTools.loginCheck(customerFilepath, username, password)) {
					usernameField.setText("");
					passwordField.setText("");
					setVisible(false);
					JOptionPane.showMessageDialog(null, "Welcome " + username, "Login successful.", JOptionPane.INFORMATION_MESSAGE);
					ItemManager Imanager = new ItemManager();
					 Imanager.setItemList(DatabaseTools.loadItemData());
					 final String currentUsername = username;
					 new ItemTable(Imanager, currentUsername);
				} else {
					JOptionPane.showMessageDialog(null, "Your username and password is not correct.", "Login error", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		btnCustomer.setBounds(73, 308, 99, 23);
		getContentPane().add(btnCustomer);
		
		JLabel lblLoginAs = new JLabel("Login as:");
		lblLoginAs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLoginAs.setBounds(24, 286, 75, 25);
		getContentPane().add(lblLoginAs);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				char[] password = passwordField.getPassword();
				boolean isEmptyPassword = password.length==0;
				
				if(username.equals("")|| isEmptyPassword) {
					JOptionPane.showMessageDialog(null, "Please fill in both fields.", "Login error", JOptionPane.WARNING_MESSAGE);
				} else if(DatabaseTools.loginCheck(adminFilepath, username, password)) {
					usernameField.setText("");
					passwordField.setText("");
					JOptionPane.showMessageDialog(null, "Welcome " + username, "Login successful.", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					new Menu(username);
					
					} else {
					JOptionPane.showMessageDialog(null, "Your username and password is not correct.", "Login error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnAdmin.setBounds(264, 308, 97, 23);
		getContentPane().add(btnAdmin);
		
		JLabel lblDontHaveAn = new JLabel("Don't have an account? Create one:");
		lblDontHaveAn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDontHaveAn.setBounds(24, 365, 247, 20);
		getContentPane().add(lblDontHaveAn);
		
		JButton btnSignUp = new JButton("Sign up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SignUpPane();
			}
		});
		btnSignUp.setBounds(283, 366, 89, 23);
		getContentPane().add(btnSignUp);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("./Database/logo.jpg"));
		label.setBounds(42, 38, 350, 93);
		getContentPane().add(label);
		setVisible(true);
		System.out.println("RentIt by David Phan");
	}
}
