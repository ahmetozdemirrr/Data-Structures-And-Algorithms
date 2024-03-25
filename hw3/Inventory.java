import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/**
 * The Inventory class represents a collection of electronic devices.
 * It provides methods to add, remove, update, display, and manage electronic devices.
 */
public class Inventory
{
	/** A linked list to hold lists of electronic devices */
	private LinkedList<ArrayList<Device>> deviceLists;
	/** A set to keep track of categories */
    private HashSet<String> categories;

	/** 
     * Constructs an empty inventory.
     */
	public Inventory()
	{
		deviceLists = new LinkedList<>();
		categories = new HashSet<>();

		categories.add("Laptop");
		categories.add("VRGlass");
		categories.add("Monitor");
		categories.add("Smartphone");
		categories.add("TV");
	}

	/**
	 * Adds a new device to the inventory.
	 * 
	 * @param device The electronic device to add
	 * @param signal Flag indicating whether to print information about the added device
	 * @implNote Time complexity: O(n), where n is the total number of devices in the inventory
	 */
	public void addDevice(Device device) 
	{
	    if (device == null) 
	    {
	        System.out.println("\nA device cannot be null!\n");
	        return;
	    }
	    
	    String deviceCategory = device.getCategory();

	    if (!categories.contains(deviceCategory)) 
	    {
	        System.out.println("\nCategory does not exist! Please enter a valid category (Laptop, VRGlass, Smartphone, Monitor, TV).\n");
	        return;
	    }

	    boolean categoryFound = false;

	    for (ArrayList<Device> list : deviceLists) 
	    {
	        if (list.get(0).getCategory().equals(deviceCategory)) 
	        {
	            Device newDevice;

	            switch(deviceCategory) 
	            {
	                case "Laptop":
	                    newDevice = new Laptop(device.getName(), device.getPrice(), device.getQuantity());
	                    break;

	                case "VRGlass":
	                    newDevice = new VRGlass(device.getName(), device.getPrice(), device.getQuantity());
	                    break;

	                case "Smartphone":
	                    newDevice = new Smartphone(device.getName(), device.getPrice(), device.getQuantity());
	                    break;

	                case "Monitor":
	                    newDevice = new Monitor(device.getName(), device.getPrice(), device.getQuantity());
	                    break;

	                case "TV":
	                    newDevice = new TV(device.getName(), device.getPrice(), device.getQuantity());
	                    break;

	                default:
	                    System.out.println("Invalid device category!");
	                    return;
	            }
	            list.add(newDevice);
	            categoryFound = true;
	            System.out.println(newDevice.getCategory() + ", " + newDevice.getName() + ", " + newDevice.getPrice() + "$, " + newDevice.getQuantity() + " amount added...\n");
	            break;
	        }
	    }

	    if (!categoryFound) 
	    {
	        ArrayList<Device> newList = new ArrayList<>();
	        newList.add(device);
	        deviceLists.add(newList);
	        System.out.println(device.getCategory() + ", " + device.getName() + ", " + device.getPrice() + "$, " + device.getQuantity() + " amount added...\n");
	    }
	}

	/**
	 * Removes a device from the inventory based on its name.
	 * 
	 * @param deviceName The name of the device to remove
	 * @implNote Time complexity: O(n), where n is the total number of devices in the inventory
	 */
	public void removeDevice(String deviceName) 
	{
	    if (!deviceLists.isEmpty()) 
	    {
	        if (deviceName == null || deviceName.isEmpty()) 
	        {
	            System.out.println("\nCannot remove a null object!\n");
	            return;
	        }

	        for (ArrayList<Device> list : deviceLists) 
	        {
	            Iterator<Device> iterator = list.iterator();

	            while (iterator.hasNext()) 
	            {
	                Device device = iterator.next();

	                if (device.getName().equals(deviceName)) 
	                {
	                    iterator.remove();
	                    System.out.println("Device with name " + deviceName + " removed successfully!\n");
	                    return;
	                }
	            }
	        }
	        System.out.println("\nDevice with name " + deviceName + " does not exist!\n"); /* Eğer listede bulunamazsa */
	    } 

	    else 
	    {
	    	System.out.println("\nDevice list is empty!\n");
	    }  
	}

	/**
	 * Updates the details of a device in the inventory.
	 * 
	 * @param deviceName The name of the device to update
	 * @implNote Time complexity: O(n), where n is the total number of devices in the inventory
	 */
	public void updateDevice(String deviceName) 
	{
	    if (!deviceLists.isEmpty()) 
	    {
	        if (deviceName == null || deviceName.isEmpty()) 
	        {
	            System.out.println("\nCannot update a null object!\n");
	            return;
	        }

	        boolean deviceFound = false;

	        for (ArrayList<Device> list : deviceLists) 
	        {
	            for (Device device : list) 
	            {
	                if (device.getName().equals(deviceName)) 
	                {
	                    System.out.print("Enter new price (leave blank to keep current price): ");
	                    String _PriceStr = Main.scanner.nextLine();

	                    if (!_PriceStr.trim().isEmpty()) 
	                    {
	                        if (!isDouble(_PriceStr)) 
	                        {
	                            System.out.println("Invalid price format, enter only double!");
	                            return;
	                        }

	                        double _Price = Double.parseDouble(_PriceStr);
	                        device.setPrice(_Price);
	                    }

	                    System.out.print("Enter new quantity (leave blank to keep current quantity): ");
	                    String _QuantityStr = Main.scanner.nextLine();

	                    if (!_QuantityStr.trim().isEmpty()) 
	                    {
	                        if (!isInteger(_QuantityStr)) 
	                        {
	                            System.out.println("Invalid quantity format, enter only integer!");
	                            return;
	                        }

	                        int _Quantity = Integer.parseInt(_QuantityStr);
	                        device.setQuantity(_Quantity);
	                    }
	                    System.out.println(deviceName + " details updated: Price - " + device.getPrice() + "$, Quantity - " + device.getQuantity() + "\n");
	                    deviceFound = true;
	                    break;
	                }
	            }
	            if (deviceFound) {
	                break;
	            }
	        }

	        if (!deviceFound) 
	        {
	            System.out.println("Device with name " + deviceName + " does not exist!"); // Eğer listede bulunamazsa
	        }
	    }

	    else 
	    {
	    	System.out.println("\nDevice list is empty!\n");
	    }
	}

	/**
	 * Displays all devices in the inventory.
	 * 
	 * @implNote Time complexity: O(n), where n is the total number of devices in the inventory
	 */
	public void displayAllDevices()
	{
		if (!deviceLists.isEmpty()) 
		{
			int lineCount = 1;

		    System.out.println("\nDevice List:");

		    for (ArrayList<Device> arrayList : deviceLists) 
		    {
		        for (Device device : arrayList) 
		        {
		            System.out.print(lineCount + ". Category: ");
		            System.out.print(device.getCategory());
		            System.out.print(", Name: ");
		            System.out.print(device.getName());
		            System.out.print(", Price: ");
		            System.out.print(device.getPrice() + "$");
		            System.out.print(", Quantity: ");
		            System.out.println(device.getQuantity());
		            lineCount++;
		        }
		    }
		    System.out.println();
		}
	    
	    else 
	    {
	    	System.out.println("\nDevice list is empty!\n");
	    }
	}

	/**
	 * Finds the cheapest device in the inventory and prints its details.
	 * 
	 * @implNote Time complexity: O(n), where n is the total number of devices in the inventory
	 */
	public void findTheCheapestDevice()
	{
	    if (!deviceLists.isEmpty()) 
	    {
	        double cheapestPrice = Double.MAX_VALUE;
	        Device cheapestDevice = null;

	        for (ArrayList<Device> list : deviceLists) 
	        {
	            for (Device device : list) 
	            {
	                if (device.getPrice() < cheapestPrice) 
	                {
	                    cheapestPrice = device.getPrice();
	                    cheapestDevice = device; 			
	                } 		
	            } 	
	        } 

	        if (cheapestDevice != null) 
	        {
	            System.out.println("\nThe cheapest device is: ");
	            cheapestDevice.printDevice();
	            System.out.println();
	        }
	    }

	    else 
	    {
	    	System.out.println("\nDevice list is empty!\n");
	    }   
	}

	/**
	 * Sorts all devices in the inventory by price and prints them.
	 * 
	 * @implNote Time complexity: O(n log n), where n is the total number of devices in the inventory
	 */
	public void sortingDeviceByPrice()
	{
	    if (!deviceLists.isEmpty()) 
	    {
	        ArrayList<Device> allDevices = new ArrayList<>();

	        for (ArrayList<Device> list : deviceLists) 
	        {
	            allDevices.addAll(list);	
	        }

	        Collections.sort(allDevices, new Comparator<Device>() 
	        {
	            @Override
	            public int compare(Device device1, Device device2) 
	            {
	                return Double.compare(device1.getPrice(), device2.getPrice());
	            }
	        });

	        System.out.println("\nDevices sorted by price:");
	        int lineCount = 1;

	        for (Device device : allDevices) 
	        {
	            System.out.print(lineCount + ". Category: " + device.getCategory());
	            System.out.print(", Name: " + device.getName());
	            System.out.print(", Price: " + device.getPrice() + "$");
	            System.out.println(", Quantity: " + device.getQuantity());
	            lineCount++;
	        }
	        System.out.println();
	    }

	    else 
	    {
	    	System.out.println("\nDevice list is empty!\n");
	    }   
	}

	/**
	 * Calculates the total value of the inventory and prints it.
	 * 
	 * @implNote Time complexity: O(n), where n is the total number of devices in the inventory
	 */
	public void calculateTotalInventory()
	{
	    if (!deviceLists.isEmpty()) 
	    {
	        double totalInventoryValue = 0.0;

	        for (ArrayList<Device> list : deviceLists) 
	        {
	            for (Device device : list) 
	            {
	                totalInventoryValue += device.getPrice();	            
	            }
	        }
	        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
	        String formattedTotal = decimalFormat.format(totalInventoryValue);
	        System.out.println("\nTotal inventory value: $" + formattedTotal + "\n");
	    }  

	    else 
	    {
	    	System.out.println("\nDevice list is empty!\n");
	    } 
	}

	/**
	 * Restocks a device in the inventory by adding or removing stock.
	 * 
	 * @implNote Time complexity: O(n), where n is the total number of devices in the inventory
	 */
	public void restockingDevice()
	{
	    if (!deviceLists.isEmpty()) 
	    {
	        System.out.print("\nEnter the name of the device to restock: ");
	        String _Name = Main.scanner.nextLine();
	        
	        System.out.print("Do you want to add or remove stock? (Add/Remove): ");
	        String _Choice = Main.scanner.nextLine();

	        if (!_Choice.equals("Add") && !_Choice.equals("Remove")) 
	        {
	            System.out.println("\nEnter only \"Add\" or \"Remove\"!\n");
	            return;
	        }

	        if (_Name == null || _Name.isEmpty()) 
	        {
	            System.out.println("\nCannot remove a null object!\n");
	            return;
	        }

	        for (ArrayList<Device> list : deviceLists) 
	        {
	            for (Device device : list) 
	            {
	                if (device.getName().equals(_Name)) 
	                {
	                    System.out.print("Enter quantity to " + (_Choice.equals("Add") ? "add" : "remove") + ": ");
	                    String _QuantityStr = Main.scanner.nextLine();

	                    if (!isInteger(_QuantityStr)) 
	                    {
	                        System.out.println("\nQuantity must be an integer!\n");
	                        return;    
	                    }
	                    int _Quantity = Integer.parseInt(_QuantityStr);

	                    if (_Choice.equals("Add")) 
	                    {
	                        device.setQuantity(device.getQuantity() + _Quantity);
	                        System.out.println(_Name + " restocked. New quantity: " + device.getQuantity() + "\n");                                       
	                    } 
	                    else if (_Choice.equals("Remove")) 
	                    {
	                        if (_Quantity > device.getQuantity()) 
	                        {
	                            System.out.println("\nCannot remove more quantity than available!\n");
	                            return;
	                        }
	                        device.setQuantity(device.getQuantity() - _Quantity);    
	                        System.out.println(_Name + " stock reduced. New quantity: " + device.getQuantity() + "\n");
	                    }
	                    return;    
	                }    
	            }
	        }
	        System.out.println("\nThis device does not exist!\n"); /* Eğer listede bulunamazsa */    
	    } 

	    else 
	    {
	    	System.out.println("\nDevice list is empty!\n");
	    }  
	}

	/**
	 * Exports an inventory report with details of all devices.
	 * 
	 * @implNote Time complexity: O(n), where n is the total number of devices in the inventory
	 */
	public void exportingInventoryReport()
	{
	    LocalDate currentDate = LocalDate.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");

	    int totalDeviceCount = 1;
	    double totalInventoryValue = 0.0;

	    System.out.println("\nElectronics Shop Inventory Report");
	    System.out.println("Generated on: " + formatter.format(currentDate) + "\n");
	    System.out.println("------------------------------------------------------------------------");
	    System.out.println("| No. |   Category   |          Name          |   Price   |  Quantity  |");
	    System.out.println("------------------------------------------------------------------------");

	    for (ArrayList<Device> list : deviceLists) 
	    {
	        for (Device device : list) 
	        {
	            System.out.printf("| %-4d| %-13s| %-23s| %-10.2f| %-11d|%n", totalDeviceCount, device.getCategory(), device.getName(), device.getPrice(), device.getQuantity());
	            totalInventoryValue += device.getPrice();
	            totalDeviceCount++;
	        }
	    }

	    DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
	    String formattedTotal = decimalFormat.format(totalInventoryValue);

	    System.out.println("------------------------------------------------------------------------");
	    System.out.println("\nSummary:");
	    System.out.println("- Total Number of Devices: " + (totalDeviceCount - 1));
	    System.out.println("- Total inventory Value: $" + formattedTotal);
	    System.out.println("\nEnd of Report\n");
	}

	/**
	 * Checks if a given string can be parsed into a double.
	 * This method has a time complexity of O(n), where n is the length of the input string.
	 * @param str The string to check
	 * @return True if the string can be parsed into a double, false otherwise
	 */
	private boolean isDouble(String str) 
	{
        try 
        {
            Double.parseDouble(str);
            return true;
        } 
        catch (NumberFormatException e) 
        {
            return false;
        }
    }

	/**
	 * Checks if a given string can be parsed into an integer.
	 * This method has a time complexity of O(n), where n is the length of the input string.
	 * @param str The string to check
	 * @return True if the string can be parsed into an integer, false otherwise
	 */
    private boolean isInteger(String str) 
    {
        try 
        {
            Integer.parseInt(str);
            return true;
        } 
        catch (NumberFormatException e) 
        {
            return false;
        }
    }
}