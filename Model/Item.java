package Model;

/**
 * This class is used as a blueprint to create Item objects Uses variables such
 * as name, color, manufacturer, category, .... To store information about the
 * items
 */
public class Item {
	private String name;
	private String color;
	private String manufacturer;
	private String category;
	private String ownerUsername;
	private int pricePerHour;
	private boolean rentStatus;
	private String rentorUsername;

	public Item(String name, String color, String manufacturer, String category, String ownerUsername,
			int pricePerHour) {
		this.name = name;
		this.color = color;
		this.manufacturer = manufacturer;
		this.category = category;
		this.ownerUsername = ownerUsername;
		this.pricePerHour = pricePerHour;
		this.rentStatus = true;
		this.rentorUsername = "None";
	}

	public Item(String name, String color, String manufacturer, String category, String ownerUsername, int pricePerHour,
			boolean rentStatus, String rentorUsername) {
		this.name = name;
		this.color = color;
		this.manufacturer = manufacturer;
		this.category = category;
		this.ownerUsername = ownerUsername;
		this.pricePerHour = pricePerHour;
		this.rentStatus = rentStatus;
		this.rentorUsername = rentorUsername;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @return the customer's username
	 */
	public String getOwnerUsername() {
		return this.ownerUsername;
	}

	public int getPricePerHour() {
		return this.pricePerHour;
	}

	public boolean getRentStatus() {
		return this.rentStatus;
	}

	public String getRentorUsername() {
		return this.rentorUsername;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setOwnerUsername(String ownerUsername) {
		this.ownerUsername = ownerUsername;
	}

	/**
	 * @param rentInformation the rentInformation to set
	 */

	public void setPricePerHour(int pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public void setRentStatus(boolean rentStatus) {
		this.rentStatus = rentStatus;
	}

	public void setRentorUsername(String rentorUsername) {
		this.rentorUsername = rentorUsername;
	}

}
