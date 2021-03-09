package Model;

/**
 * This class is used as a blueprint to create Customer objects Uses variables
 * such as username and password from its super class Uses variables such as
 * first and last name, age and email to store other information.
 */
public class Customer extends User {
	private String firstName;
	private String lastName;
	private int age;
	private String email;

	public Customer(String username, String password, String lastName, String firstName, int age, String email) {
		super.setUsername(username);
		super.setPassword(password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.email = email;
	}

	/**
	 * @return the username of hte customer
	 */
	public String getUsername() {
		return super.getUsername();
	}

	/**
	 * @return the password of the customer
	 */
	public String getPassword() {
		return super.getPassword();
	}

	/**
	 * @return the first name of the user
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the last name of the user
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the age of the user
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @return the email of the user
	 */
	public String getEmail() {
		return email;
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
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
