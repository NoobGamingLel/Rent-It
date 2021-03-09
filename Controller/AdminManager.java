package Controller;

/** This class is mainly used to store and manipulate Objects of type Admin
 * Inside of an array list of object
 */

import Model.*;

import java.util.ArrayList;

public class AdminManager {
	private ArrayList<Admin> adminList = new ArrayList<Admin>();

	/* Empty constructor */
	public AdminManager() {
	}

	/**
	 * Constructor for AdminManager
	 * @param input ArrayList of Admin
	 */
	public AdminManager(ArrayList<Admin> input) {
		adminList = input;
	}

	/**
	 * Method to get he Admin at index i
	 * @param i the index number of the Admin
	 * @return The admin at specified index
	 */
	public Admin getAdmin(int i) {
		return this.adminList.get(i);
	}

	/**
	 * Method to get the ArrayList of Admins
	 * @return The ArrayList of Admins
	 */
	public ArrayList<Admin> getAdminList() {
		return this.adminList;
	}

	/**
	 * Method to set the AdminList
	 * @param input The new ArrayList of Admins
	 */
	public void setAdminList(ArrayList<Admin> input) {
		this.adminList = input;
	}

	/**
	 * Method to add an Admin
	 * @param newAdmin the new Admin to be added
	 */
	public void addAdmin(Admin newAdmin) {
		this.adminList.add(newAdmin);
	}

	/**
	 * Method to check if usrename is unique by looking at the database
	 * @param username The username of the admin
	 * @return true if name already exists and false otherwise
	 */
	public boolean uniqueAdminUsername(String username) {
		int arrSize = this.adminList.size();
		for (int i = 0; i < arrSize; i++) {
			String databaseUsername = this.adminList.get(i).getUsername();
			if (username.equals(databaseUsername)) {
				return true;
			}
		}
		return false;
	}

}