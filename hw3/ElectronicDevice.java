import java.util.Objects;

/**
 * The ElectronicDevice class represents an electronic device.
 * It implements the Device interface and provides methods to access and modify device properties.
 * This class has no methods with explicit time complexity considerations.
 */
class ElectronicDevice implements Device 
{
	/** The category of the electronic device */
	private String category;
	/** The name of the electronic device */
	private String name;
	/** The price of the electronic device */
	private double price;
	/** The quantity of the electronic device */
	private int quantity;

    /**
     * Constructs a new ElectronicDevice with the given properties.
     * This constructor runs in constant time O(1).
     * @param category The category of the device
     * @param name The name of the device
     * @param price The price of the device
     * @param quantity The quantity of the device
     */
	public ElectronicDevice(String category, String name, double price, int quantity)
	{
		this.category = category;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

    /**
     * Gets the category of the device.
     * This method runs in constant time O(1).
     * @return The category of the device
     */
	@Override
	public String getCategory()
	{
		return this.category;
	}

    /**
     * Gets the name of the device.
     * This method runs in constant time O(1).
     * @return The name of the device
     */
	@Override
	public String getName()
	{
		return this.name;
	}

	/**
     * Gets the price of the device.
     * This method runs in constant time O(1).
     * @return The price of the device
     */
	@Override
	public double getPrice()
	{
		return this.price;
	}

	/**
     * Gets the quantity of the device.
     * This method runs in constant time O(1).
     * @return The quantity of the device
     */
	@Override
	public int getQuantity()
	{
		return this.quantity;
	}

	/**
     * Sets the price of the device.
     * This method runs in constant time O(1).
     * @param price The new price of the device
     */
	@Override
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	/**
     * Sets the quantity of the device.
     * This method runs in constant time O(1).
     * @param quantity The new quantity of the device
     */
	@Override
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	/**
     * Checks if this device is equal to another object.
     * Two devices are considered equal if they have the same name.
     * This method runs in constant time O(1) assuming hash code computation takes constant time.
     * @param o The object to compare
     * @return True if the devices are equal, false otherwise
     */
	@Override
    public boolean equals(Object o) 
    {
    	boolean eqFlag = false;

        if (this == o)
        {
            return true;
        }

        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        
        ElectronicDevice that = (ElectronicDevice) o;
        
        return Objects.equals(this.name, that.name);
    }

    /**
     * Generates a hash code for this device.
     * This method runs in constant time O(1).
     * @return The hash code for this device
     */
    @Override
    public int hashCode() 
    {
        return Objects.hash(name);
    }

    /**
     * Prints the details of the device.
     * This method runs in constant time O(1).
     */
    public void printDevice()
	{
		System.out.print("Category: " + this.category + ", ");
		System.out.print("Name: " + this.name + ", ");
		System.out.print("Price: " + this.price + "$, ");
		System.out.println("Quantity: " + this.quantity);
	}
}