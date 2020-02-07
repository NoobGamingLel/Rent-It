/* This framed is called when the user clicks to register for a new account
 * This fill have the fields for the user to fill in and save it to a file.
 */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class SignUpPane extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JTextField passwordField;
	private JTextField lastNameField;
	private JTextField firstNameField;
	private JTextField ageField;
	private JTextField emailField;
	final static String customerFilepath = "./Database/Customer/CustomerList.csv";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpPane frame = new SignUpPane();
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
	public SignUpPane() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Sign up");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblUsername.setBounds(187, 11, 78, 25);
		contentPane.add(lblUsername);
		
		JLabel lblUsername_1 = new JLabel("Username");
		lblUsername_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername_1.setBounds(22, 60, 84, 25);
		contentPane.add(lblUsername_1);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(22, 109, 84, 25);
		contentPane.add(lblPassword);
		
		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLastName.setBounds(22, 158, 84, 14);
		contentPane.add(lblLastName);
		
		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFirstName.setBounds(22, 201, 84, 20);
		contentPane.add(lblFirstName);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAge.setBounds(22, 243, 84, 25);
		contentPane.add(lblAge);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(22, 295, 84, 14);
		contentPane.add(lblEmail);
		
		usernameField = new JTextField();
		usernameField.setBounds(145, 64, 242, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(145, 113, 242, 20);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		lastNameField = new JTextField();
		lastNameField.setBounds(145, 157, 242, 20);
		contentPane.add(lastNameField);
		lastNameField.setColumns(10);
		
		firstNameField = new JTextField();
		firstNameField.setBounds(145, 203, 242, 20);
		contentPane.add(firstNameField);
		firstNameField.setColumns(10);
		
		ageField = new JTextField();
		ageField.setBounds(145, 247, 242, 20);
		contentPane.add(ageField);
		ageField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setBounds(145, 294, 242, 20);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
			    String password = passwordField.getText();
			    String lastName = lastNameField.getText();
			    String firstName = firstNameField.getText();
			    String email = emailField.getText();
			    String ageStr = ageField.getText();
			    boolean emp = false;
			    boolean exist = DatabaseTools.uniqueUsernameCheck(customerFilepath, username);
			    if(username.equals("")||password.equals("")||lastName.equals("")||firstName.equals("")||email.equals("")||ageStr.equals(""))
			        emp = true;
			    if(emp)
			    	JOptionPane.showMessageDialog(null, "Make sure to fill all boxes before proceeding.", "Error", JOptionPane.WARNING_MESSAGE);
			    else {
			    	if(username.toLowerCase().contains("admin")) {
			    		JOptionPane.showMessageDialog(null, "Username invalid.", "Error", JOptionPane.WARNING_MESSAGE);
			    		usernameField.setText("");
			    	} else if(exist) {
			    		JOptionPane.showMessageDialog(null, "Username taken.", "Error", JOptionPane.WARNING_MESSAGE);
			    	} else {
			    		try {
			    		       int age = Integer.parseInt(ageStr);
			    		       usernameField.setText("");
				    			passwordField.setText("");
				    			lastNameField.setText("");
				    			firstNameField.setText("");
				    			ageField.setText("");
				    			emailField.setText("");
			    		       boolean stat = DatabaseTools.register(username, password, lastName, firstName, age, email);
			    		       if(stat)
			    		    	   JOptionPane.showMessageDialog(null, "Account created.", "Error", JOptionPane.INFORMATION_MESSAGE);
			    		       else
			    		    	   JOptionPane.showMessageDialog(null, "Account cannot be created.", "Error", JOptionPane.WARNING_MESSAGE);
			    		      } catch (NumberFormatException o)
			    		      {
			    		       JOptionPane.showMessageDialog(null, "Please enter a number in Age.", "Error", JOptionPane.WARNING_MESSAGE);
			    		       ageField.setText("");
			    		      }

			    	}
			    	
			    }
			}
		});
		btnCreate.setBounds(176, 347, 89, 23);
		contentPane.add(btnCreate);
		setVisible(true);
	}
}
