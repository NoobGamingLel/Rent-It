package Controller;

/** This class is mainly used to store and manipulate Objects of type Admin
 * Inside of an array list of object
 * Provides method to search and sort depending on a selected criteria
 */

import Model.*;

import java.util.ArrayList;

public class CustomerManager {
	private ArrayList<Customer> customerList = new ArrayList<Customer>();

	public CustomerManager() {

	}

	public CustomerManager(ArrayList<Customer> input) {
		customerList = input;
	}

	public Customer getCustomer(int n) {
		return customerList.get(n);
	}

	public void addCustomer(Customer x) {
		this.customerList.add(x);
	}

	public void removeCustomer(int n) {
		this.customerList.remove(n);
	}

	public void clearCustomerList() {
		this.customerList.clear();
	}

	public ArrayList<Customer> getCustomerList() {
		return this.customerList;
	}

	public void setCustomerList(ArrayList<Customer> input) {
		this.customerList = input;
	}

	public boolean uniqueCustomerUsername(String username) {
		int arrSize = this.customerList.size();
		for (int i = 0; i < arrSize; i++) {
			String databaseUsername = this.customerList.get(i).getUsername();
			if (username.equals(databaseUsername)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Integer> searchByUsername(String username) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (username.equals(""))
			return result;
		final int arraySize = this.customerList.size();
		for (int i = 0; i < arraySize; i++) {
			if (this.customerList.get(i).getUsername().toLowerCase().contains(username.toLowerCase())) {
				result.add(i);
			}

		}
		return result;
	}

	public ArrayList<Integer> searchByFirstName(String fname) {

		ArrayList<Integer> result = new ArrayList<Integer>();

		if (fname.equals(""))
			return result;
		final int arraySize = this.customerList.size();
		for (int i = 0; i < arraySize; i++) {
			if (this.customerList.get(i).getFirstName().toLowerCase().contains((fname).toLowerCase())) {
				result.add(i);
			}

		}
		return result;
	}

	public ArrayList<Integer> searchByLastName(String lname) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (lname.equals(""))
			return result;
		final int arraySize = this.customerList.size();
		for (int i = 0; i < arraySize; i++) {
			if (this.customerList.get(i).getLastName().toLowerCase().contains((lname).toLowerCase())) {
				result.add(i);
			}

		}
		return result;
	}

	public ArrayList<Integer> searchByAge(String a) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		try {
			int n = Integer.parseInt(a);

			final int arraySize = this.customerList.size();
			for (int i = 0; i < arraySize; i++) {
				if (this.customerList.get(i).getAge() == n) {
					result.add(i);
				}

			}
		} catch (NumberFormatException e) {

		}
		return result;
	}

	public void sortByUsername() {
		int iPos;
		int iMin;
		for (iPos = 0; iPos < customerList.size(); iPos++) {
			iMin = iPos;
			for (int i = iPos + 1; i < customerList.size(); i++) {
				String s1 = customerList.get(i).getUsername();
				String s2 = customerList.get(iMin).getUsername();
				char first = s1.charAt(0);
				char second = s2.charAt(0);

				if (first < second) {
					iMin = i;
				}
			}
			if (iMin != iPos) {
				Customer tmp = customerList.get(iPos);
				customerList.set(iPos, customerList.get(iMin));
				customerList.set(iMin, tmp);
			}

		}
	}

	public void sortByLastName() {
		int iPos;
		int iMin;
		for (iPos = 0; iPos < customerList.size(); iPos++) {
			iMin = iPos;
			for (int i = iPos + 1; i < customerList.size(); i++) {
				String s1 = customerList.get(i).getLastName().toLowerCase();
				String s2 = customerList.get(iMin).getLastName().toLowerCase();
				char first = s1.charAt(0);
				char second = s2.charAt(0);

				if (first < second) {
					iMin = i;
				}
			}
			if (iMin != iPos) {
				Customer tmp = customerList.get(iPos);
				customerList.set(iPos, customerList.get(iMin));
				customerList.set(iMin, tmp);
			}

		}
	}

	public void sortByFirstName() {
		int iPos;
		int iMin;
		for (iPos = 0; iPos < customerList.size(); iPos++) {
			iMin = iPos;
			for (int i = iPos + 1; i < customerList.size(); i++) {
				String s1 = customerList.get(i).getFirstName().toLowerCase();
				String s2 = customerList.get(iMin).getFirstName().toLowerCase();
				char first = s1.charAt(0);
				char second = s2.charAt(0);

				if (first < second) {
					iMin = i;
				}
			}
			if (iMin != iPos) {
				Customer tmp = customerList.get(iPos);
				customerList.set(iPos, customerList.get(iMin));
				customerList.set(iMin, tmp);
			}

		}
	}

	public void sortByAge() {
		int iPos;
		int iMin;
		for (iPos = 0; iPos < customerList.size(); iPos++) {
			iMin = iPos;
			for (int i = iPos + 1; i < customerList.size(); i++) {
				int i1 = customerList.get(i).getAge();
				int i2 = customerList.get(iMin).getAge();
				if (i1 < i2) {
					iMin = i;
				}
			}
			if (iMin != iPos) {
				Customer tmp = customerList.get(iPos);
				customerList.set(iPos, customerList.get(iMin));
				customerList.set(iMin, tmp);
			}

		}
	}

}
