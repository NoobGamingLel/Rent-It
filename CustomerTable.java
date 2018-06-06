/* This class is a Jframe used to display information from the CustomerManager
 * Provides buttons to add and remove Customers
 * Fields to add and edit customer's information
 * Search and sort customers
 * Button to save the data.
 * This class is mainly for Administrative uses.
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.awt.Font;

@SuppressWarnings("serial")
class CustomerTable extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static JTextField searchField;
	private static JTextField usernameField;
	private static JTextField passwordField;
	private static JTextField emailField;
	private static JTextField lastNameField;
	private static JTextField firstNameField;
	private static JTextField ageField;
	private final static String customerFilepath = "E:/David Phan/ICS4U Final project/Database/Customer/CustomerList.csv";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CustomerTable(CustomerManager Cmanager) {
		setTitle("Customer Manager");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 637, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 52, 459, 167);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Username", "Last Name", "First Name", "Age", "Email"
				}
				) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(55);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		scrollPane.setViewportView(table);

		CustomerTable.showAll(table, Cmanager);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				model.setRowCount(0);

			}
		});
		btnClear.setBounds(506, 55, 89, 23);
		contentPane.add(btnClear);

		JLabel lblSortBy = new JLabel("Sort by:");
		lblSortBy.setBounds(420, 15, 46, 14);
		contentPane.add(lblSortBy);

		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Username", "First Name", "Last Name", "Age"}));
		comboBox.setBounds(465, 11, 119, 23);
		comboBox.setSelectedIndex(-1);
		contentPane.add(comboBox);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int selectedRowIndex = table.getSelectedRow();
				String username = model.getValueAt(selectedRowIndex, 0).toString();
				int x = Cmanager.searchByUsername(username).get(0);
				model.removeRow(selectedRowIndex);
				Cmanager.removeCustomer(x);
			}
		});
		btnRemove.setBounds(506, 89, 89, 23);
		contentPane.add(btnRemove);

		searchField = new JTextField();
		searchField.setBounds(37, 11, 183, 23);
		contentPane.add(searchField);
		searchField.setColumns(10);

		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Username", "First Name", "Last Name", "Age"}));
		comboBox_1.setBounds(291, 11, 119, 23);
		comboBox_1.setSelectedIndex(-1);
		contentPane.add(comboBox_1);

		JLabel lblSearchBy = new JLabel("Search by:");
		lblSearchBy.setBounds(230, 14, 64, 14);
		contentPane.add(lblSearchBy);

		JButton btnShowAll = new JButton("Show all");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CustomerTable.showAll(table, Cmanager);
			}
		});
		btnShowAll.setBounds(506, 123, 89, 23);
		contentPane.add(btnShowAll);


		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				boolean stat = DatabaseTools.saveCustomerData(Cmanager.getCustomerList());
				if(stat)
					JOptionPane.showMessageDialog(null, "Changes applied.", "Status", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Changes were not applied.", "Error", JOptionPane.ERROR_MESSAGE);

			}
		});
		btnSave.setBounds(506, 157, 89, 23);
		contentPane.add(btnSave);

		usernameField = new JTextField();
		usernameField.setBounds(95, 270, 125, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsername.setBounds(37, 273, 62, 23);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(37, 307, 62, 14);
		contentPane.add(lblPassword);

		passwordField = new JTextField();
		passwordField.setBounds(95, 301, 125, 20);
		contentPane.add(passwordField);
		passwordField.setColumns(10);

		JLabel lblNewCustomerForm = new JLabel("New customer form");
		lblNewCustomerForm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewCustomerForm.setBounds(37, 230, 137, 23);
		contentPane.add(lblNewCustomerForm);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmail.setBounds(37, 338, 52, 14);
		contentPane.add(lblEmail);

		emailField = new JTextField();
		emailField.setBounds(95, 332, 125, 20);
		contentPane.add(emailField);
		emailField.setColumns(10);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLastName.setBounds(328, 273, 64, 14);
		contentPane.add(lblLastName);

		lastNameField = new JTextField();
		lastNameField.setBounds(402, 270, 125, 20);
		contentPane.add(lastNameField);
		lastNameField.setColumns(10);

		firstNameField = new JTextField();
		firstNameField.setBounds(402, 301, 125, 20);
		contentPane.add(firstNameField);
		firstNameField.setColumns(10);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFirstName.setBounds(328, 308, 64, 14);
		contentPane.add(lblFirstName);

		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAge.setBounds(328, 339, 64, 14);
		contentPane.add(lblAge);

		ageField = new JTextField();
		ageField.setBounds(402, 336, 125, 20);
		contentPane.add(ageField);
		ageField.setColumns(10);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText().trim();
				String password = passwordField.getText().trim();
				String lastName = lastNameField.getText().trim();
				String firstName = firstNameField.getText().trim();
				String email = emailField.getText().trim();
				String ageStr = ageField.getText().trim();

				boolean emp;
				boolean exist1 = DatabaseTools.uniqueUsernameCheck(customerFilepath, username);
				boolean exist2 = Cmanager.uniqueCustomerUsername(username);
				if(username.equals("")||password.equals("")||lastName.equals("")||firstName.equals("")||email.equals("")||ageStr.equals(""))
					emp = true;
				else 
					emp = false;

				if(emp)
					JOptionPane.showMessageDialog(null, "Make sure to fill all boxes before proceeding.", "Error", JOptionPane.WARNING_MESSAGE);
				else 
				{
					if(username.toLowerCase().contains("admin")) {
						JOptionPane.showMessageDialog(null, "Username cannot contain keyword 'Admin'.", "Error", JOptionPane.WARNING_MESSAGE);
						usernameField.setText("");
					}
					else if(exist1||exist2)
					{
						JOptionPane.showMessageDialog(null, "Username taken.", "Error", JOptionPane.WARNING_MESSAGE);
					} else {
						try {
							int age = Integer.parseInt(ageStr);
							Customer hold = new Customer(username, password, lastName, firstName, age, email);
							Cmanager.addCustomer(hold);
							CustomerTable.emptyFields();
							CustomerTable.showAll(table, Cmanager);
						} catch (NumberFormatException o)
						{
							JOptionPane.showMessageDialog(null, "Please enter a number in Age.", "Error", JOptionPane.WARNING_MESSAGE);
							ageField.setText("");
						}
					}
				}
			}
		});
		btnAdd.setBounds(506, 232, 89, 23);
		contentPane.add(btnAdd);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int selectedRowIndex = table.getSelectedRow();
				String username = model.getValueAt(selectedRowIndex, 0).toString();
				String newUsername = usernameField.getText().trim();
				String password = passwordField.getText().trim();
				String lastName = lastNameField.getText().trim();
				String firstName = firstNameField.getText().trim();
				String ageStr = ageField.getText().trim();
				String email = emailField.getText().trim();
				int x = Cmanager.searchByUsername(username).get(0);


				boolean emp;

				if(username.equals("")||password.equals("")||lastName.equals("")||firstName.equals("")||email.equals("")||ageStr.equals(""))
					emp = true;
				else 
					emp = false;
				if(emp)
					JOptionPane.showMessageDialog(null, "Make sure to fill all boxes before proceeding", "Error", JOptionPane.WARNING_MESSAGE);
				else {
					try {
						int age = Integer.parseInt(ageStr);
						model.removeRow(selectedRowIndex);
						Customer hold = new Customer(newUsername, password, lastName, firstName, age, email);
						Cmanager.removeCustomer(x);
						Cmanager.addCustomer(hold);
						CustomerTable.emptyFields();
						JOptionPane.showMessageDialog(null, "Edit applied. Make sure to save before signing out.", "Saved.", JOptionPane.INFORMATION_MESSAGE);


						CustomerTable.showAll(table, Cmanager);

					} catch (NumberFormatException o)
					{
						JOptionPane.showMessageDialog(null, "Please enter a number in Age.", "Error", JOptionPane.WARNING_MESSAGE);
						ageField.setText("");
					}
				}



			}
		});
		btnEdit.setBounds(407, 232, 89, 23);
		contentPane.add(btnEdit);
		setVisible(true);

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Object selected = comboBox.getSelectedItem();
				if(selected.toString().equals("Username")) {
					Cmanager.sortByUsername();
				} else if(selected.toString().equals("First Name")) {
					Cmanager.sortByFirstName();
				} else if(selected.toString().equals("Last Name")) {
					Cmanager.sortByLastName();
				} else if(selected.toString().equals("Age")) {
					Cmanager.sortByAge();
				}

				CustomerTable.showAll(table, Cmanager);


			}
		});

		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String query= searchField.getText().trim();

				Object selected_1 = comboBox_1.getSelectedItem();
				ArrayList<Integer> results = new ArrayList<Integer>();

				if(selected_1.toString().equals("Username")) {
					results = Cmanager.searchByUsername(query);
				} else if(selected_1.toString().equals("First Name")) {
					results = Cmanager.searchByFirstName(query);
				} else if(selected_1.toString().equals("Last Name")) {
					results = Cmanager.searchByLastName(query);
				} else if(selected_1.toString().equals("Age")) {
					if(!query.isEmpty() || !query.equals(""))
						results = Cmanager.searchByAge(query);
				}

				if(results.isEmpty() || searchField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "No results. Please fill in the search bar if you did not.", "Error", JOptionPane.WARNING_MESSAGE);
					CustomerTable.showAll(table, Cmanager);
				} else {

					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.setRowCount(0);

					for(int i = 0; i<Cmanager.getCustomerList().size(); i++) {
						for(int j=0; j<results.size(); j++) {
							if(i==results.get(j)) {
								String username = Cmanager.getCustomer(i).getUsername();
								String lname = Cmanager.getCustomer(i).getLastName();
								String fname = Cmanager.getCustomer(i).getFirstName();     
								int age = Cmanager.getCustomer(i).getAge();
								String email = Cmanager.getCustomer(i).getEmail();

								Object[] data = {username, lname, fname, age, email};
								model.addRow(data);
							}
						}
					}
				}
			}



		});
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int selectedRowIndex = table.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel)table.getModel();

				usernameField.setText(model.getValueAt(selectedRowIndex, 0).toString());
				lastNameField.setText(model.getValueAt(selectedRowIndex, 1).toString());
				firstNameField.setText(model.getValueAt(selectedRowIndex, 2).toString());
				emailField.setText(model.getValueAt(selectedRowIndex, 4).toString());
				ageField.setText(model.getValueAt(selectedRowIndex, 3).toString());


			}
		});
	}

	public static void showAll( JTable table,CustomerManager Cmanager) {
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.setRowCount(0);

		for(int i = 0; i<Cmanager.getCustomerList().size(); i++) {
			String username = Cmanager.getCustomer(i).getUsername();
			String lname = Cmanager.getCustomer(i).getLastName();
			String fname = Cmanager.getCustomer(i).getFirstName();    
			int age = Cmanager.getCustomer(i).getAge();
			String email = Cmanager.getCustomer(i).getEmail();

			Object[] data = {username, lname, fname, age, email};
			model.addRow(data); 
		}
	}

	public static void emptyFields() {
		usernameField.setText("");
		passwordField.setText("");
		lastNameField.setText("");
		firstNameField.setText("");
		emailField.setText("");
		ageField.setText("");
	}

}
