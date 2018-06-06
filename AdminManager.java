/* This class is mainly used to store and manipulate Objects of type Admin
 * Inside of an array list of object
 */
import java.util.ArrayList;
public class AdminManager {
	private ArrayList<Admin> adminList = new ArrayList<Admin>() ;
	
	public AdminManager() {
	}
	
	public AdminManager(ArrayList<Admin> input) {
		adminList=input;
	}
	
	public Admin getAdmin(int i) {
		return this.adminList.get(i);
	}
	
	
	public ArrayList<Admin> getAdminList(){
		return this.adminList;
	}
	
	public void setAdminList(ArrayList<Admin> input) {
		this.adminList = input;		
	}
	
	public void addAdmin(Admin x) {
		this.adminList.add(x);
	}
	
	/*This method makes sure each username is unique
	 * Returns true if a same name is found in the database
	 * False if it did not
	 */
	public boolean uniqueAdminUsername(String username) {
		int arrSize = this.adminList.size();
		for(int i=0; i<arrSize; i++) {
			String databaseUsername = this.adminList.get(i).getUsername();
			if(username.equals(databaseUsername)) {
				return true;
			}
		}
		return false;
	}
	
}
