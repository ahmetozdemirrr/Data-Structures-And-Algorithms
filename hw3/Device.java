/**
 * The Device interface defines methods to access and modify properties of a device.
 * Any class that implements this interface must provide implementations for these methods.
 * 
 * This interface does not contain any methods with time complexity considerations, 
 * as the time complexity of implementing classes' methods may vary depending on their implementations.
 */
interface Device /* Device interface definition */
{
	/**
     * Gets the category of the device.
     * @return The category of the device
     */
	String getCategory();

	/**
     * Gets the name of the device.
     * @return The name of the device
     */
	String getName();
	
	/**
     * Gets the price of the device.
     * @return The price of the device
     */
	double getPrice();
	
	/**s
     * Gets the quantity of the device.
     * @return The quantity of the device
     */
	int getQuantity();

	/**
     * Sets the price of the device.
     * @param price The new price of the device
     */
	void setPrice(double price);

	/**
     * Sets the quantity of the device.
     * @param quantity The new quantity of the device
     */
	void setQuantity(int quantity);

	 /**
     * Prints the details of the device.
     * This method runs in constant time O(1).
     */
    void printDevice();
}