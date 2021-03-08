package Controller;
/** This class provides tools to
 * Read data from the files and store it into an array list
 * Save data from the array list to the files
 * Registers and write new account information into files
 * Check for unique username and unique item name for each username
 * Reads username and password and compares it to database to authenticate users
 */

import Model.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
//import java.io.BufferedReader;
import java.io.BufferedWriter;
//import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
public class DatabaseTools {
	private static Scanner x;
	private static final String customerFilepath="./Database/Customer/CustomerList.csv";
	private static final String adminFilepath ="./Database/Admin/AdminList.csv";
	private static final String itemFilepath ="./Database/Item/ItemList.csv";;
	
	
	public static ArrayList<Customer> loadCustomerData(){
		String filepath = customerFilepath;
		String userNm;
		String pass;
		String lname;
		String fname;
		int age;
		String email;
		ArrayList<Customer> CustomerList = new ArrayList<Customer>();
		
		try
		{
			x= new Scanner(new File(filepath));
			x.useDelimiter("[,\n]");
			while(x.hasNext())
			{
				userNm = x.next();
				pass = x.next();
				lname = x.next();
				fname = x.next();
				age = x.nextInt();
				email = x.next();
				Customer placeHold = new Customer(userNm, pass, lname, fname, age, email);
				CustomerList.add(placeHold);
			}
			x.close();
		} catch (Exception e) {
			//Database does not exist.
		}
		return CustomerList;
	}
	
	public static ArrayList<Item> loadItemData(){
		String filepath = itemFilepath;
		String name;
		String color;
		String manufacturer;
		String category;
		String ownerUsername;
		int pricePerHour;
		boolean rentStatus;
		String rentorUsername;

		ArrayList<Item> ItemList = new ArrayList<Item>();
		
		try
		{
			x= new Scanner(new File(filepath));
			x.useDelimiter("[,\n]");
			while(x.hasNext())
			{
				name = x.next();
				color = x.next();
				manufacturer = x.next();
				category = x.next();
				ownerUsername = x.next();
				pricePerHour = x.nextInt();
				if(x.next().equals("Available"))
					rentStatus = true;
				else
					rentStatus = false;
				rentorUsername = x.next();
				Item placeHold = new Item(name, color, manufacturer, category, ownerUsername, pricePerHour, rentStatus, rentorUsername);
				ItemList.add(placeHold);
			}
			x.close();
		} catch (Exception e) {
			//Database does not exist.
		}
		return ItemList;
	}
	
	public static ArrayList<Admin> loadAdminData(){
		String filepath = adminFilepath;
		String name;
		String password;
		String branch;

		ArrayList<Admin> AdminList = new ArrayList<Admin>();
		
		try
		{
			x= new Scanner(new File(filepath));
			x.useDelimiter("[,\n]");
			while(x.hasNext())
			{
				name = x.next();
				password = x.next();
				branch = x.next();
	
				Admin placeHold = new Admin(name, password, branch);
				AdminList.add(placeHold);
			}
			x.close();
		} catch (Exception e) {
			//Database does not exist.
		}
		return AdminList;
	}
	
	public static boolean saveCustomerData(ArrayList<Customer> customerList) {
		String filepath = customerFilepath;
		String tempFile = "tmpCus.csv";
		
		File oldFile = new File(filepath);
		File newFile = new File(tempFile);
		
		String userNm;
		String pass;
		String lname;
		String fname;
		int age;
		String email;
		
		try
		{
			FileWriter fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
		    PrintWriter pw = new PrintWriter(bw);
			for(int i=0; i<customerList.size(); i++) {
				userNm = customerList.get(i).getUsername();
				pass = customerList.get(i).getPassword();
				lname = customerList.get(i).getLastName();
				fname = customerList.get(i).getFirstName();
				age = customerList.get(i).getAge();
				email = customerList.get(i).getEmail();
				
				pw.print(userNm);
				pw.print(",");
				pw.print(pass);
				pw.print(",");
				pw.print(lname);
				pw.print(",");
				pw.print(fname);
				pw.print(",");
				pw.print(age);
				pw.print(",");
				pw.print(email);
				pw.print("\n");
				
			}
			x.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dump = new File(filepath);
			newFile.renameTo(dump);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean saveItemData(ArrayList<Item> itemList) {
		String filepath = itemFilepath;
		String tempFile = "tmpItem.csv";
		
		File oldFile = new File(filepath);
		File newFile = new File(tempFile);
		
		String name;
		String color;
		String manufacturer;
		String category;
		String ownerUsername;
		int pricePerHour;
		boolean rentStatus;
		String rentorUsername;
		
		try
		{
			FileWriter fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
		    PrintWriter pw = new PrintWriter(bw);
			for(int i=0; i<itemList.size(); i++) {
				name = itemList.get(i).getName();
				color = itemList.get(i).getColor();
				manufacturer = itemList.get(i).getManufacturer();
				category = itemList.get(i).getCategory();
				ownerUsername = itemList.get(i).getOwnerUsername();
				pricePerHour = itemList.get(i).getPricePerHour();
				rentStatus = itemList.get(i).getRentStatus();
				rentorUsername = itemList.get(i).getRentorUsername();
				
				pw.print(name);
				pw.print(",");
				pw.print(color);
				pw.print(",");
				pw.print(manufacturer);
				pw.print(",");
				pw.print(category);
				pw.print(",");
				pw.print(ownerUsername);
				pw.print(",");
				pw.print(pricePerHour);
				pw.print(",");
				if(rentStatus==true)
					pw.print("Available");
				else
					pw.print("Rented");
				pw.print(",");
				pw.print(rentorUsername);
				pw.print("\n");
				
			}
			x.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dump = new File(filepath);
			newFile.renameTo(dump);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
//	public static boolean saveAdminData(String filepath, ArrayList<Admin> adminList) {
//		String tempFile = "tmp.csv";
//		File oldFile = new File(filepath);
//		File newFile = new File(tempFile);
//		
//		String username;
//		String password;
//		String branch;
//		
//		try
//		{
//			FileWriter fw = new FileWriter(tempFile, true);
//			BufferedWriter bw = new BufferedWriter(fw);
//		    PrintWriter pw = new PrintWriter(bw);
//			for(int i=0; i<adminList.size(); i++) {
//				username = adminList.get(i).getUsername();
//				password = adminList.get(i).getPassword();
//				branch = adminList.get(i).getBranch();
//				
//				pw.print(username);
//				pw.print(",");
//				pw.print(password);
//				pw.print(",");
//				pw.print(branch);
//				pw.print("\n");
//				
//			}
//			x.close();
//			pw.flush();
//			pw.close();
//			oldFile.delete();
//			File dump = new File(filepath);
//			newFile.renameTo(dump);
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}
	
	public static boolean uniqueUsernameCheck(String filepath, String username) {
		boolean found = false;
		String userNm;
		
		 try
	     {
	       x = new Scanner(new File(filepath));
	       x.useDelimiter("[\n,]");
	       while(x.hasNext() && !found)
	       {
	         userNm = x.next();
	         for(int i=0; i<5; i++) {
	        	 x.next();
	         }
     
	         if(username.equals(userNm))
	         {
	           found = true;
	         }
	         
	       }
	       x.close();
	       return found;
	     }
	     catch(Exception e)
	     {
	       return false;
	     }
		
	}
	
	public static boolean uniqueItemNamePerUser(String ownerUsername, String itemName) {
		String filepath = itemFilepath;
		boolean found = false;
		String ownerUsnm;
		String itemNm;
		
		 try
	     {
	       x = new Scanner(new File(filepath));
	       x.useDelimiter("[\n,]");
	       while(x.hasNext() && !found)
	       {
	         itemNm = x.next();
	         for(int i=0; i<3; i++) {
	        	 x.next();
	         }
	         ownerUsnm = x.next();
	         
	         for(int i=0; i<3; i++) {
	        	 x.next();
	         }
     
	         if(itemName.trim().equals(itemNm) && ownerUsername.trim().equals(ownerUsnm))
	         {
	           found = true;
	         }
	         
	       }
	       x.close();
	       return found;
	     }
	     catch(Exception e)
	     {
	       return false;
	     }
		
	}
	
	public static boolean loginCheck(String filepath, String usernameInput, char[]charPasswordInput) {
		boolean found = false;
	     String recUsername = ""; String recPassword = "";
	     String passwordInput = String.valueOf(charPasswordInput);
	     try
	     {
	       x = new Scanner(new File(filepath));
	       x.useDelimiter("[,\n]");
	       while(x.hasNext() && !found)
	       {
	         recUsername = x.next();
	         recPassword = x.next();
	         x.next();
	         if(recUsername.equals(usernameInput) && recPassword.equals(passwordInput))
	         {
	           found = true;
	         }
	       }
	       return found;
	       
	     }
	     catch(Exception e)
	     {
	    	 System.out.print("Catch error");
	       return false;
	     }
	 }
	
	public static boolean register(String userNm, String pass, String lname, String fname, int age, String email) {
		String filepath = customerFilepath;
		try
	     {
	       FileWriter fw = new FileWriter(filepath, true);
	       BufferedWriter bw = new BufferedWriter(fw);
	       PrintWriter pw = new PrintWriter(bw);
	       
	       pw.print(userNm);
			pw.print(",");
			pw.print(pass);
			pw.print(",");
			pw.print(lname);
			pw.print(",");
			pw.print(fname);
			pw.print(",");
			pw.print(age);
			pw.print(",");
			pw.print(email);
			pw.print("\n");
	       
	       pw.flush();
	       pw.close();
	       bw.close();
	       
	       return true;
	     }
	     catch(Exception e){
	       return false;
	     }
		
	}
	
	public static boolean register(String username, String password, String branch) {
		String filepath=adminFilepath;
		try
	     {
	       FileWriter fw = new FileWriter(filepath, true);
	       BufferedWriter bw = new BufferedWriter(fw);
	       PrintWriter pw = new PrintWriter(bw);
	       
	       pw.print(username);
			pw.print(",");
			pw.print(password);
			pw.print(",");
			pw.print(branch);
			pw.print("\n");
	       
	       pw.flush();
	       pw.close();
	       bw.close();
	       
	       return true;
	     }
	     catch(Exception e){
	       return false;
	     }
		
	}
	
	
	
	

}
