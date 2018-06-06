/* This class is mainly used to store and manipulate Objects of type Item
 * Inside of an array list of objects
 * Provides method to search and sort depending on a selected criteria
 */
import java.util.ArrayList;
public class ItemManager {
	private ArrayList<Item> itemList = new ArrayList<Item>();
	public ItemManager() {
	}

	public ItemManager(ArrayList<Item> input) {
		itemList=input;
	}

	public void addItem(Item x) {
		this.itemList.add(x);
	}

	public Item getItem(int n) {
		return itemList.get(n);
	}

	public void removeItem(int n) {
		this.itemList.remove(n);
	}

	public void clearItemList() {
		this.itemList.clear();
	}

	public ArrayList<Item> getItemList(){
		return this.itemList;
	}

	public void setItemList(ArrayList<Item> input) {
		this.itemList = input;
	}

	public ArrayList<Integer> searchByName(String name){
		ArrayList<Integer> result = new ArrayList<Integer>();
		if(name.equals(""))
			return result;
		final int arraySize = this.itemList.size();
		for(int i=0; i<arraySize; i++)
		{
			if(this.itemList.get(i).getName().toLowerCase().contains(name.toLowerCase()))
			{
				result.add(i);
			}

		}
		return result;
	}

	public ArrayList<Integer> searchByColor(String color){
		ArrayList<Integer> result = new ArrayList<Integer>();
		if(color.equals(""))
			return result;
		final int arraySize = this.itemList.size();
		for(int i=0; i<arraySize; i++)
		{
			if(this.itemList.get(i).getColor().toLowerCase().contains(color.toLowerCase()))
			{
				result.add(i);
			}

		}
		return result;
	}

	public ArrayList<Integer> searchByManufacturer(String manufacturer){
		ArrayList<Integer> result = new ArrayList<Integer>();
		if(manufacturer.equals(""))
			return result;
		final int arraySize = this.itemList.size();
		for(int i=0; i<arraySize; i++)
		{
			if(this.itemList.get(i).getManufacturer().toLowerCase().contains(manufacturer.toLowerCase()))
			{
				result.add(i);
			}

		}
		return result;
	}

	public ArrayList<Integer> searchByCategory(String category){
		ArrayList<Integer> result = new ArrayList<Integer>();
		if(category.equals(""))
			return result;
		final int arraySize = this.itemList.size();
		for(int i=0; i<arraySize; i++)
		{
			if(this.itemList.get(i).getCategory().toLowerCase().contains(category.toLowerCase()))
			{
				result.add(i);
			}

		}
		return result;
	}

	public ArrayList<Integer> searchByOwnerUsername(String customerUsername){
		ArrayList<Integer> result = new ArrayList<Integer>();
		if(customerUsername.equals(""))
			return result;
		final int arraySize = this.itemList.size();
		for(int i=0; i<arraySize; i++)
		{
			if(this.itemList.get(i).getOwnerUsername().toLowerCase().contains(customerUsername.toLowerCase()))
			{
				result.add(i);
			}

		}
		return result;
	}



	public ArrayList<Integer> searchByPrice(String price){
		ArrayList<Integer> result = new ArrayList<Integer>();
		try
		{
			int p = Integer.parseInt(price);
			final int arraySize = this.itemList.size();
			for(int i=0; i<arraySize; i++) {
				if(this.itemList.get(i).getPricePerHour()==p)
					result.add(i);
			}
		} catch(NumberFormatException e) {

		}
		return result;
		//		if(category.equals(""))
		//			return result;
		//		final int arraySize = this.itemList.size();
		//		for(int i=0; i<arraySize; i++)
		//		{
		//			if(this.itemList.get(i).getCustomerName().toLowerCase().contains(category.toLowerCase()))
		//			{
		//				result.add(i);
		//			}
		//			
		//		}
		//		return result;
	}

	public void sortByName() {
		int iPos;
		int iMin;
		for(iPos=0; iPos<itemList.size(); iPos++) {
			iMin=iPos;
			for(int i = iPos+1; i<itemList.size(); i++) {
				String s1 = itemList.get(i).getName().toLowerCase();
				String s2 = itemList.get(iMin).getName().toLowerCase();
				char first = s1.charAt(0);
				char second = s2.charAt(0);

				if(first<second) {
					iMin=i;
				}
			}
			if(iMin!=iPos) {
				Item tmp = itemList.get(iPos);
				itemList.set(iPos, itemList.get(iMin));
				itemList.set(iMin, tmp);
			}

		}
	}

	public void sortByColor() {
		int iPos;
		int iMin;
		for(iPos=0; iPos<itemList.size(); iPos++) {
			iMin=iPos;
			for(int i = iPos+1; i<itemList.size(); i++) {
				String s1 = itemList.get(i).getColor().toLowerCase();
				String s2 = itemList.get(iMin).getColor().toLowerCase();
				char first = s1.charAt(0);
				char second = s2.charAt(0);

				if(first<second) {
					iMin=i;
				}
			}
			if(iMin!=iPos) {
				Item tmp = itemList.get(iPos);
				itemList.set(iPos, itemList.get(iMin));
				itemList.set(iMin, tmp);
			}

		}
	}

	public void sortByManufacturer() {
		int iPos;
		int iMin;
		for(iPos=0; iPos<itemList.size(); iPos++) {
			iMin=iPos;
			for(int i = iPos+1; i<itemList.size(); i++) {
				String s1 = itemList.get(i).getManufacturer().toLowerCase();
				String s2 = itemList.get(iMin).getManufacturer().toLowerCase();
				char first = s1.charAt(0);
				char second = s2.charAt(0);

				if(first<second) {
					iMin=i;
				}
			}
			if(iMin!=iPos) {
				Item tmp = itemList.get(iPos);
				itemList.set(iPos, itemList.get(iMin));
				itemList.set(iMin, tmp);
			}

		}
	}

	public void sortByCategory() {
		int iPos;
		int iMin;
		for(iPos=0; iPos<itemList.size(); iPos++) {
			iMin=iPos;
			for(int i = iPos+1; i<itemList.size(); i++) {
				String s1 = itemList.get(i).getCategory().toLowerCase();
				String s2 = itemList.get(iMin).getCategory().toLowerCase();
				char first = s1.charAt(0);
				char second = s2.charAt(0);

				if(first<second) {
					iMin=i;
				}
			}
			if(iMin!=iPos) {
				Item tmp = itemList.get(iPos);
				itemList.set(iPos, itemList.get(iMin));
				itemList.set(iMin, tmp);
			}

		}
	}

	public void sortByPrice() {
		int iPos;
		int iMin;
		for(iPos=0; iPos<itemList.size(); iPos++) {
			iMin=iPos;
			for(int i = iPos+1; i<itemList.size(); i++) {
				int i1 = itemList.get(i).getPricePerHour();
				int i2 = itemList.get(iMin).getPricePerHour();

				if(i1<i2) {
					iMin=i;
				}
			}
			if(iMin!=iPos) {
				Item tmp = itemList.get(iPos);
				itemList.set(iPos, itemList.get(iMin));
				itemList.set(iMin, tmp);
			}

		}
	}
	
	/* This is a recursive method that calculates the total income
	 * In a situation where all items are rented.
	 */
	public static int estimateIncome(int element, ArrayList<Item> itemList) {
		int cap = itemList.size();
		if(element==cap)
			return 0;
		else
			return itemList.get(element).getPricePerHour() + estimateIncome(++element, itemList);
	}
	
	/* This class makes sure that each username
	 * Can only have 1 item with a unique name
	 * Returns true if it finds an existed item name for the current owner
	 * False if it did not
	 */
	public boolean uniqueItemNamePerUser(String ownerUsername, String itemName) {
		int arrSize = this.itemList.size();
		for(int i=0; i<arrSize; i++) {
			String databaseOwnerUsername = this.itemList.get(i).getOwnerUsername();
			String databaseItemName = this.itemList.get(i).getName();
			if(ownerUsername.equals(databaseOwnerUsername) && itemName.equals(databaseItemName)) {
				return true;
			}
		}
		return false;
	}
	/* Returns items with the owner name 
	 * equals to the current username
	 */
	public ArrayList<Integer> searchByMyItem(String currentUsername){
		ArrayList<Integer> result = new ArrayList<Integer>();
		if(currentUsername.equals(""))
			return result;
		final int arraySize = this.itemList.size();
		for(int i=0; i<arraySize; i++)
		{
			if(this.itemList.get(i).getOwnerUsername().toLowerCase().equals(currentUsername.toLowerCase()))
			{
				result.add(i);
			}	
		}
		return result;
	}






}
