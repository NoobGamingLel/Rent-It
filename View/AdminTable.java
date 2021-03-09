package View;

/** This class is a Jframe used to display information from the AdminManager
 * Provides a button to add new admins.
 * This class is mainly for Administrative uses.
 */

import Model.*;
import Controller.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AdminTable extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static JTextField usernameField;
	private static JTextField passwordField;
	private static JTextField branchField;

	/**
	 * Launch the application.
	 */

	/**
	 * 
	 * @param Amanager The admin manager
	 * @return the AdminTable object
	 */
	public AdminTable(AdminManager Amanager) {
		setTitle("Admin Table");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 53, 378, 200);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Username", "Branch" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(125);
		table.getColumnModel().getColumn(1).setPreferredWidth(125);
		scrollPane.setViewportView(table);

		for (int i = 0; i < Amanager.getAdminList().size(); i++) {
			String username = Amanager.getAdmin(i).getUsername();
			String branch = Amanager.getAdmin(i).getBranch();

			Object[] data = { username, branch };
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.addRow(data);
		}

		JLabel lblNewUsernameField = new JLabel("New admin field");
		lblNewUsernameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewUsernameField.setBounds(33, 269, 125, 17);
		contentPane.add(lblNewUsernameField);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(33, 305, 76, 17);
		contentPane.add(lblNewLabel);

		usernameField = new JTextField();
		usernameField.setBounds(119, 305, 175, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(33, 346, 76, 14);
		contentPane.add(lblPassword);

		passwordField = new JTextField();
		passwordField.setBounds(119, 345, 175, 20);
		contentPane.add(passwordField);
		passwordField.setColumns(10);

		JLabel lblBranch = new JLabel("Branch");
		lblBranch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBranch.setBounds(33, 387, 76, 14);
		contentPane.add(lblBranch);

		branchField = new JTextField();
		branchField.setBounds(119, 386, 175, 20);
		contentPane.add(branchField);
		branchField.setColumns(10);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final String adminFilepath = "./Database/Admin/AdminList.csv";
				String username = usernameField.getText();
				String password = passwordField.getText();
				String branch = branchField.getText();
				boolean emp;
				boolean exist1 = DatabaseTools.uniqueUsernameCheck(adminFilepath, username);
				boolean exist2 = Amanager.uniqueAdminUsername(username);
				if (username.equals("") || password.equals("") || branch.equals(""))
					emp = true;
				else
					emp = false;

				if (emp)
					JOptionPane.showMessageDialog(null, "Make sure to fill all boxes before proceeding.", "Error",
							JOptionPane.WARNING_MESSAGE);
				else {
					if (exist1 || exist2) {
						JOptionPane.showMessageDialog(null, "Username taken.", "Error", JOptionPane.WARNING_MESSAGE);
					} else {
						boolean stat = DatabaseTools.register(username, password, branch);
						if (stat) {
							JOptionPane.showMessageDialog(null, "Account created.", "Error",
									JOptionPane.INFORMATION_MESSAGE);
							Admin hold = new Admin(username, password, branch);
							Amanager.addAdmin(hold);
							AdminTable.emptyFields();

							AdminTable.showAll(table, Amanager);
						} else
							JOptionPane.showMessageDialog(null, "Account cannot be created.", "Error",
									JOptionPane.WARNING_MESSAGE);

					}

				}

			}
		});

		btnAdd.setBounds(317, 264, 89, 23);
		contentPane.add(btnAdd);

		JLabel lblAdminList = new JLabel("Admin list");
		lblAdminList.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAdminList.setBounds(175, 11, 89, 31);
		contentPane.add(lblAdminList);
		setVisible(true);
	}

	public static void showAll(JTable table, AdminManager Amanager) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		for (int i = 0; i < Amanager.getAdminList().size(); i++) {
			String username = Amanager.getAdmin(i).getUsername();
			String branch = Amanager.getAdmin(i).getBranch();

			Object[] data = { username, branch };
			model.addRow(data);
		}
	}

	public static void emptyFields() {
		usernameField.setText("");
		passwordField.setText("");
		branchField.setText("");
	}
}
