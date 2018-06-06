/* Dislayed when the authenticated user is an Admin
 * This frames displays buttons to launch AdminTable, CustomerTable, ItemTable
 * Signout Button
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Menu extends JFrame {

	private JPanel contentPane;

	public Menu(String currentUsername) {
		setTitle("Admin Tools");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblWelcome.setBounds(131, 11, 96, 25);
		contentPane.add(lblWelcome);
		
		JLabel lblName = new JLabel(currentUsername);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(221, 11, 114, 24);
		contentPane.add(lblName);
		
		JButton btnCustomerManager = new JButton("Customer Manager");
		btnCustomerManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  CustomerManager Cmanager = new CustomerManager();
				  Cmanager.setCustomerList(DatabaseTools.loadCustomerData());
				  new CustomerTable(Cmanager);
			}
		});
		btnCustomerManager.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCustomerManager.setBounds(141, 47, 150, 23);
		contentPane.add(btnCustomerManager);
		
		JButton btnItemManager = new JButton("Item Manager");
		btnItemManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemManager Imanager = new ItemManager();
				Imanager.setItemList(DatabaseTools.loadItemData());
				final String currentUsername = "";
				new ItemTable(Imanager, currentUsername);
			}
		});
		btnItemManager.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnItemManager.setBounds(141, 88, 150, 23);
		contentPane.add(btnItemManager);
		
		JButton btnAdminManager = new JButton("Admin Manager");
		btnAdminManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminManager Amanager = new AdminManager();
				Amanager.setAdminList(DatabaseTools.loadAdminData());
				new AdminTable(Amanager);
			}
		});
		btnAdminManager.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdminManager.setBounds(141, 129, 150, 23);
		contentPane.add(btnAdminManager);
		
		JButton btnSignOut = new JButton("Sign Out");
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Thank you for using RentIt.", "Good bye", JOptionPane.INFORMATION_MESSAGE);
				new Management();
				dispose();
			}
		});
		btnSignOut.setBounds(335, 227, 89, 23);
		contentPane.add(btnSignOut);
		
		setVisible(true);
	}

}
