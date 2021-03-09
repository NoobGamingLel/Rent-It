package Model;

/**
 * This class is used as a blueprint to create Admin objects Uses variables such
 * as username and password from its super class Uses a variable called branch
 * to store which branch this employee is working a
 */
public class Admin extends User {
	private String branch;

	public Admin(String username, String password, String branch) {
		super.setUsername(username);
		super.setPassword(password);
		this.branch = branch;
	}

	/**
	 * @return the username of the admin
	 */
	public String getUsername() {
		return super.getUsername();
	}

	/**
	 * @return The password of the admin
	 */
	public String getPassword() {
		return super.getPassword();
	}

	/**
	 * @return The branch of the admin
	 */
	public String getBranch() {
		return this.branch;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		super.setUsername(username);
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		super.setPassword(password);
	}

	/**
	 * @param branch the branch to set
	 */
	public void setBranch(String branch) {
		this.branch = branch;
	}
}
