package View;

/** This class is a Jframe used to display information from the ItemManager
 * Provides buttons to add and remove Items
 * Fields to add and edit item's information
 * Search and sort items
 * Button to save the data.
 * Button to book and a button to return an item
 * Button to log out
 * This class is mainly for business uses.
 */

import Model.*;
import Controller.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

@SuppressWarnings("serial")
public class ItemTable extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static JTextField searchField;
	private static JTextField nameField;
	private static JTextField colorField;
	private static JTextField manufacturerField;
	private static JTextField priceField;
	private static JTextField categoryField;

	//Lanch application

	//Create frame
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ItemTable(ItemManager Imanager, String currentUsername) {
		setTitle("Item Manager");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 52, 738, 167);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Name", "Color", "Manufacturer", "Category", "Owner", "Price", "Status", "Rentor" }) {
			boolean[] columnEditables = new boolean[] { false, true, true, false, false, false, true, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(65);
		table.getColumnModel().getColumn(2).setPreferredWidth(85);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.getColumnModel().getColumn(5).setPreferredWidth(60);
		table.getColumnModel().getColumn(6).setPreferredWidth(60);
		table.getColumnModel().getColumn(7).setPreferredWidth(90);
		scrollPane.setViewportView(table);

		ItemTable.showAll(table, Imanager);

		JButton btnClearTable = new JButton("Clear");
		btnClearTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);

			}
		});
		btnClearTable.setBounds(785, 52, 89, 23);
		if (currentUsername.equals(""))
			contentPane.add(btnClearTable);

		JLabel lblSortBy = new JLabel("Sort by:");
		lblSortBy.setBounds(424, 15, 46, 14);
		contentPane.add(lblSortBy);

		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(
				new DefaultComboBoxModel(new String[] { "Name", "Color", "Manufacturer", "Category", "Price" }));
		comboBox.setBounds(467, 11, 119, 23);
		comboBox.setSelectedIndex(-1);
		contentPane.add(comboBox);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int selectedRowIndex = table.getSelectedRow();
				String name = model.getValueAt(selectedRowIndex, 0).toString();
				int x = Imanager.searchByName(name).get(0);
				model.removeRow(selectedRowIndex);
				Imanager.removeItem(x);

			}
		});
		btnRemove.setBounds(785, 86, 89, 23);
		if (currentUsername.equals(""))
			contentPane.add(btnRemove);

		searchField = new JTextField();
		searchField.setBounds(37, 11, 183, 20);
		contentPane.add(searchField);
		searchField.setColumns(10);

		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(
				new String[] { "Name", "Color", "Manufacturer", "Category", "Owner", "Price" }));
		comboBox_1.setBounds(288, 11, 119, 23);
		comboBox_1.setSelectedIndex(-1);
		contentPane.add(comboBox_1);

		JLabel lblSearchBy = new JLabel("Search by:");
		lblSearchBy.setBounds(230, 14, 64, 14);
		contentPane.add(lblSearchBy);

		JButton btnShowAll = new JButton("Show all");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ItemTable.showAll(table, Imanager);
			}
		});
		btnShowAll.setBounds(785, 120, 89, 23);
		contentPane.add(btnShowAll);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				boolean stat = DatabaseTools.saveItemData(Imanager.getItemList());
				if (stat)
					JOptionPane.showMessageDialog(null, "Changes applied.", "Status", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Changes were not applied.", "Error",
							JOptionPane.ERROR_MESSAGE);

			}
		});
		btnSave.setBounds(785, 154, 89, 23);
		contentPane.add(btnSave);

		nameField = new JTextField();
		nameField.setBounds(123, 270, 125, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblName.setBounds(37, 273, 62, 23);
		contentPane.add(lblName);

		JLabel lblColor = new JLabel("Color");
		lblColor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblColor.setBounds(37, 307, 62, 14);
		contentPane.add(lblColor);

		colorField = new JTextField();
		colorField.setBounds(123, 301, 125, 20);
		contentPane.add(colorField);
		colorField.setColumns(10);

		JLabel lblNewItemForm = new JLabel("New Item Form");
		lblNewItemForm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewItemForm.setBounds(37, 230, 137, 23);
		contentPane.add(lblNewItemForm);

		JLabel lblManufacturer = new JLabel("Manufacturer");
		lblManufacturer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblManufacturer.setBounds(37, 338, 79, 14);
		contentPane.add(lblManufacturer);

		manufacturerField = new JTextField();
		manufacturerField.setBounds(123, 332, 125, 20);
		contentPane.add(manufacturerField);
		manufacturerField.setColumns(10);

		priceField = new JTextField();
		priceField.setBounds(398, 270, 125, 20);
		contentPane.add(priceField);
		priceField.setColumns(10);

		JLabel lblPrice = new JLabel("Price*");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPrice.setBounds(338, 276, 64, 14);
		contentPane.add(lblPrice);

		JButton btnAdd = new JButton("Add");

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String color = colorField.getText();
				String manufacturer = manufacturerField.getText();
				String category = categoryField.getText();
				String OwnerUsername = currentUsername;
				String pricePerHour = priceField.getText();

				boolean emp;
				boolean exist1 = DatabaseTools.uniqueItemNamePerUser(currentUsername, name);
				boolean exist2 = Imanager.uniqueItemNamePerUser(currentUsername, name);

				if (name.equals("") || color.equals("") || manufacturer.equals("") || category.equals("")
						|| OwnerUsername.equals("") || pricePerHour.equals(""))
					emp = true;
				else
					emp = false;

				if (emp)
					JOptionPane.showMessageDialog(null, "Make sure to fill all boxes before proceeding.", "Error",
							JOptionPane.WARNING_MESSAGE);
				else if (exist1 || exist2)
					JOptionPane.showMessageDialog(null, "Each account cannot have more than 1 item with the same name.",
							"Error", JOptionPane.WARNING_MESSAGE);
				else {
					try {
						int price = Integer.parseInt(pricePerHour);
						Item hold = new Item(name, color, manufacturer, category, OwnerUsername, price);
						Imanager.addItem(hold);
						JOptionPane.showMessageDialog(null, "Item added.", "Status", JOptionPane.INFORMATION_MESSAGE);
						ItemTable.emptyFields();
						ItemTable.showAll(table, Imanager);
					} catch (NumberFormatException o) {
						JOptionPane.showMessageDialog(null, "Please enter a number in Price.", "Error",
								JOptionPane.WARNING_MESSAGE);
						priceField.setText("");
					}
				}
			}
		});
		btnAdd.setBounds(438, 232, 89, 23);
		if (!currentUsername.equals(""))
			contentPane.add(btnAdd);

		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCategory.setBounds(338, 307, 62, 14);
		contentPane.add(lblCategory);

		categoryField = new JTextField();
		categoryField.setBounds(398, 301, 125, 20);
		contentPane.add(categoryField);
		categoryField.setColumns(10);

		JLabel lblDispUsername = new JLabel(currentUsername);
		lblDispUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDispUsername.setBounds(718, 15, 89, 26);
		contentPane.add(lblDispUsername);

		JLabel lblCurrentUser = new JLabel("Current user:");
		lblCurrentUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCurrentUser.setBounds(616, 13, 94, 28);
		contentPane.add(lblCurrentUser);

		JButton btnSignOut = new JButton("Sign out");
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Thank you for using RentIt.", "Good bye",
						JOptionPane.INFORMATION_MESSAGE);
				new Management();
				dispose();
			}
		});
		btnSignOut.setBounds(785, 382, 89, 23);
		if (!currentUsername.equals(""))
			contentPane.add(btnSignOut);

		JLabel lblpriceIsCalculated = new JLabel("*Price is calculated dollars/hour");
		lblpriceIsCalculated.setBounds(338, 385, 185, 17);
		contentPane.add(lblpriceIsCalculated);

		JButton btnShowMyItems = new JButton("Show my items");
		btnShowMyItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Integer> results = Imanager.searchByMyItem(currentUsername);
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);

				if (results.size() == 0) {
					JOptionPane.showMessageDialog(null, "It seems like you don't have any items yet.", "Oops",
							JOptionPane.INFORMATION_MESSAGE);
					ItemTable.showAll(table, Imanager);
				}
				for (int i = 0; i < Imanager.getItemList().size(); i++) {
					for (int j = 0; j < results.size(); j++) {
						if (i == results.get(j)) {
							String name = Imanager.getItem(i).getName();
							String color = Imanager.getItem(i).getColor();
							String manufacturer = Imanager.getItem(i).getManufacturer();
							String category = Imanager.getItem(i).getCategory();
							String ownerUsername = Imanager.getItem(i).getOwnerUsername();
							int pricePerHour = Imanager.getItem(i).getPricePerHour();
							boolean status = Imanager.getItem(i).getRentStatus();
							String rentStatus;
							if (status)
								rentStatus = "Available";
							else
								rentStatus = "Rented";
							String rentorUsername = Imanager.getItem(i).getRentorUsername();

							Object[] data = { name, color, manufacturer, category, ownerUsername, pricePerHour,
									rentStatus, rentorUsername };
							model.addRow(data);
						}
					}
				}

			}
		});
		btnShowMyItems.setBounds(651, 298, 125, 23);
		contentPane.add(btnShowMyItems);

		JButton btnClearText = new JButton("Clear text");
		btnClearText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ItemTable.emptyFields();
			}
		});
		btnClearText.setBounds(159, 230, 89, 23);
		if (!currentUsername.equals(""))
			contentPane.add(btnClearText);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int selectedRowIndex = table.getSelectedRow();
				String name = model.getValueAt(selectedRowIndex, 0).toString();
				String newName = nameField.getText();
				String color = colorField.getText();
				String manufacturer = manufacturerField.getText();
				String category = categoryField.getText();
				String priceStr = priceField.getText();

				boolean emp;
				boolean isOwner;

				if (name.equals("") || color.equals("") || manufacturer.equals("") || category.equals("")
						|| priceStr.equals(""))
					emp = true;
				else
					emp = false;
				if (emp)
					JOptionPane.showMessageDialog(null, "Make sure to fill all boxes before proceeding.", "Error",
							JOptionPane.WARNING_MESSAGE);
				else {
					try {
						int price = Integer.parseInt(priceStr);
						ArrayList<Integer> x = Imanager.searchByName(name);
						ArrayList<Integer> y = Imanager.searchByOwnerUsername(currentUsername);
						if (x.size() != 0 && y.size() != 0) {
							int z = ItemTable.findIntercept(x, y);
							isOwner = model.getValueAt(selectedRowIndex, 4).toString().equals(currentUsername);
							if (isOwner && (z != -1)) {
								model.removeRow(selectedRowIndex);
								Item hold = new Item(newName, color, manufacturer, category, currentUsername, price);
								Imanager.removeItem(z);
								Imanager.addItem(hold);
								ItemTable.emptyFields();
								JOptionPane.showMessageDialog(null,
										"Edit applied. Make sure to save before signing out.", "Saved.",
										JOptionPane.INFORMATION_MESSAGE);
							} else
								JOptionPane.showMessageDialog(null,
										"Either you do not have permission to edit this item or it does not exist in our database.",
										"Error", JOptionPane.WARNING_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "This item does not exist in our database.", "Error",
									JOptionPane.WARNING_MESSAGE);
						}
						ItemTable.showAll(table, Imanager);

					} catch (NumberFormatException o) {
						JOptionPane.showMessageDialog(null, "Please enter a number in price.", "Error",
								JOptionPane.WARNING_MESSAGE);
						priceField.setText("");
					}
				}
			}
		});
		btnEdit.setBounds(309, 230, 89, 23);
		if (!currentUsername.equals(""))
			contentPane.add(btnEdit);

		JLabel lblProjectedIncomePer = new JLabel("Projected income per hour if all items are rent:");
		lblProjectedIncomePer.setBounds(398, 230, 271, 14);
		if (currentUsername.equals(""))
			contentPane.add(lblProjectedIncomePer);

		int projectedIncome = ItemManager.estimateIncome(0, Imanager.getItemList());

		JLabel lblNewLabel = new JLabel("$" + projectedIncome + "/hour.");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(668, 230, 107, 14);
		if (currentUsername.equals(""))
			contentPane.add(lblNewLabel);

		JButton btnBook = new JButton("Book");
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int selectedRowIndex = table.getSelectedRow();
				boolean stat;
				boolean isOwner;
				String statStr = model.getValueAt(selectedRowIndex, 6).toString();
				String name = model.getValueAt(selectedRowIndex, 0).toString();
				String color = model.getValueAt(selectedRowIndex, 1).toString();
				String manufacturer = model.getValueAt(selectedRowIndex, 2).toString();
				String category = model.getValueAt(selectedRowIndex, 3).toString();
				String owner = model.getValueAt(selectedRowIndex, 4).toString();
				int pricePerHour = Integer.parseInt(model.getValueAt(selectedRowIndex, 5).toString());

				int x = Imanager.searchByName(name).get(0);
				isOwner = owner.equals(currentUsername);
				if (statStr.equals("Available"))
					stat = true;
				else
					stat = false;

				if (stat && (!isOwner)) {
					Item hold = new Item(name, color, manufacturer, category, owner, pricePerHour, false,
							currentUsername);
					Imanager.getItemList().set(x, hold);
					JOptionPane.showMessageDialog(null, "Item booked successfully.", "Booked",
							JOptionPane.INFORMATION_MESSAGE);
					ItemTable.showAll(table, Imanager);
				} else if (isOwner == true)
					JOptionPane.showMessageDialog(null, "You cannot rent your own item.", "Error",
							JOptionPane.WARNING_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "This item is rented.", "Error", JOptionPane.WARNING_MESSAGE);
			}
		});
		btnBook.setBounds(785, 188, 89, 23);
		if (!currentUsername.equals(""))
			contentPane.add(btnBook);

		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedRowIndex = table.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				String name = (model.getValueAt(selectedRowIndex, 0).toString());
				String color = (model.getValueAt(selectedRowIndex, 1).toString());
				String manufacturer = (model.getValueAt(selectedRowIndex, 2).toString());
				String category = (model.getValueAt(selectedRowIndex, 3).toString());
				int x = Imanager.searchByName(name).get(0);

				boolean stat;
				boolean isOwner;

				stat = Imanager.getItemList().get(x).getRentStatus();
				isOwner = model.getValueAt(selectedRowIndex, 4).toString().equals(currentUsername);

				if (isOwner) {
					String priceStr = (model.getValueAt(selectedRowIndex, 5).toString());
					int price = Integer.parseInt(priceStr);
					Item hold = new Item(name, color, manufacturer, category, currentUsername, price);
					Imanager.getItemList().set(x, hold);
					JOptionPane.showMessageDialog(null, "Item returned successfully.", "Status",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (stat)
					JOptionPane.showMessageDialog(null, "This item is available for rent.", "Error",
							JOptionPane.INFORMATION_MESSAGE);
				else if (!isOwner)
					JOptionPane.showMessageDialog(null, "This can only be confirmed by the owner.", "Error",
							JOptionPane.WARNING_MESSAGE);

				ItemTable.showAll(table, Imanager);
			}
		});
		btnReturn.setBounds(785, 222, 89, 23);
		if (!currentUsername.equals(""))
			contentPane.add(btnReturn);

		JButton btnRemoveMyItem = new JButton("Remove my item");
		btnRemoveMyItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean isOwner;
				int selectedRowIndex = table.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				String owner = (model.getValueAt(selectedRowIndex, 4).toString());
				String name = (model.getValueAt(selectedRowIndex, 0).toString());
				int x = Imanager.searchByName(name).get(0);
				isOwner = owner.equals(currentUsername);
				if (isOwner == true) {
					model.removeRow(selectedRowIndex);
					Imanager.removeItem(x);
					JOptionPane.showMessageDialog(null, "Item removed successfully.", "Status",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (isOwner == false)
					JOptionPane.showMessageDialog(null, "You do not own this item.", "Error",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		btnRemoveMyItem.setBounds(638, 232, 137, 23);
		if (!currentUsername.equals(""))
			contentPane.add(btnRemoveMyItem);
		setVisible(true);

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Object selected = comboBox.getSelectedItem();
				if (selected.toString().equals("Name")) {
					Imanager.sortByName();
				} else if (selected.toString().equals("Color")) {
					Imanager.sortByColor();
				} else if (selected.toString().equals("Manufacturer")) {
					Imanager.sortByManufacturer();
				} else if (selected.toString().equals("Category")) {
					Imanager.sortByCategory();
				} else if (selected.toString().equals("Price")) {
					Imanager.sortByPrice();
				}

				ItemTable.showAll(table, Imanager);
			}
		});

		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String query = searchField.getText().trim();

				Object selected_1 = comboBox_1.getSelectedItem();
				ArrayList<Integer> results = new ArrayList<Integer>();

				if (selected_1.toString().equals("Name")) {
					results = Imanager.searchByName(query);
				} else if (selected_1.toString().equals("Color")) {
					results = Imanager.searchByColor(query);
				} else if (selected_1.toString().equals("Manufacturer")) {
					results = Imanager.searchByManufacturer(query);
				} else if (selected_1.toString().equals("Category")) {
					results = Imanager.searchByCategory(query);
				} else if (selected_1.toString().equals("Owner")) {
					if (!query.isEmpty() || !query.equals(""))
						results = Imanager.searchByOwnerUsername(query);
				} else if (selected_1.toString().equals("Price")) {
					if (!query.isEmpty() || !query.equals(""))
						results = Imanager.searchByPrice(query);
				}

				if (results.isEmpty() || searchField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "No results. Please fill in the search bar if you did not.",
							"Error", JOptionPane.WARNING_MESSAGE);
					ItemTable.showAll(table, Imanager);
				} else {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					for (int i = 0; i < Imanager.getItemList().size(); i++) {
						for (int j = 0; j < results.size(); j++) {
							if (i == results.get(j)) {
								String name = Imanager.getItem(i).getName();
								String color = Imanager.getItem(i).getColor();
								String manufacturer = Imanager.getItem(i).getManufacturer();
								String category = Imanager.getItem(i).getCategory();
								String ownerUsername = Imanager.getItem(i).getOwnerUsername();
								int pricePerHour = Imanager.getItem(i).getPricePerHour();
								boolean status = Imanager.getItem(i).getRentStatus();
								String rentStatus;
								if (status)
									rentStatus = "Available";
								else
									rentStatus = "Rented";
								String rentorUsername = Imanager.getItem(i).getRentorUsername();

								Object[] data = { name, color, manufacturer, category, ownerUsername, pricePerHour,
										rentStatus, rentorUsername };
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
				DefaultTableModel model = (DefaultTableModel) table.getModel();

				nameField.setText(model.getValueAt(selectedRowIndex, 0).toString());
				colorField.setText(model.getValueAt(selectedRowIndex, 1).toString());
				manufacturerField.setText(model.getValueAt(selectedRowIndex, 2).toString());
				categoryField.setText(model.getValueAt(selectedRowIndex, 3).toString());
				priceField.setText(model.getValueAt(selectedRowIndex, 5).toString());

			}
		});
	}

	public static void showAll(JTable table, ItemManager Imanager) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		for (int i = 0; i < Imanager.getItemList().size(); i++) {
			String name = Imanager.getItem(i).getName();
			String color = Imanager.getItem(i).getColor();
			String manufacturer = Imanager.getItem(i).getManufacturer();
			String category = Imanager.getItem(i).getCategory();
			String ownerUsername = Imanager.getItem(i).getOwnerUsername();
			int pricePerHour = Imanager.getItem(i).getPricePerHour();
			boolean status = Imanager.getItem(i).getRentStatus();
			String rentStatus;
			if (status)
				rentStatus = "Available";
			else
				rentStatus = "Rented";
			String rentorUsername = Imanager.getItem(i).getRentorUsername();

			Object[] data = { name, color, manufacturer, category, ownerUsername, pricePerHour, rentStatus,
					rentorUsername };
			model.addRow(data);
		}
	}

	public static void emptyFields() {
		nameField.setText("");
		colorField.setText("");
		priceField.setText("");
		manufacturerField.setText("");
		categoryField.setText("");
	}

	// Find the intercept between 2 array lists
	public static int findIntercept(ArrayList<Integer> x, ArrayList<Integer> y) {
		for (int i = 0; i < x.size(); i++) {
			for (int j = 0; j < y.size(); j++) {
				if (x.get(i) == y.get(j))
					return x.get(i);
			}
		}
		return -1;
	}
}
