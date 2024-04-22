import java.util.Scanner;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Main class to manage the Electronics Inventory Management System.
 */
public class Main 
{
    /** Scanner object for user input */
    public static Scanner scanner = new Scanner(System.in);

    /**
     * Main method to initiate the program.
     * 
     * @param args Command line arguments (not used)
     * @implNote Time complexity: O(1)
     */
    public static void main(String[] args) 
    {
        Inventory inventory = new Inventory();
        menuSystem(inventory);
    }

    /**
     * Displays the menu and handles user selections.
     * 
     * @param inventory Inventory object to operate on
     * @implNote Time complexity: O(1) to O(n), where n is the number of options in the menu
     */
    public static void menuSystem(Inventory inventory)
    {
        int selection;
        System.out.println("Welcome to the Electronics Inventory Management System!\n");

        do
        {
            try 
            {
                System.out.println("Please select the option:");
                System.out.println("1. Add a new device");
                System.out.println("2. Remove a device");
                System.out.println("3. Update device details");
                System.out.println("4. List all devices");
                System.out.println("5. Find the cheapest device");
                System.out.println("6. Sort devices by price");
                System.out.println("7. Calculate total inventory value");
                System.out.println("8. Restock a device");
                System.out.println("9. Export inventory report");
                System.out.println("0. Exit");

                selection = scanner.nextInt();
                scanner.nextLine();

                programFlow(selection, inventory);                
            }
            catch (InputMismatchException e)
            {
                System.out.println("\nInvalid input!. Please enter a valid integer.\n");
                scanner.nextLine();
                selection = -1;
            }
        }
        while(selection != 0);
    }

    /**
     * Performs actions based on the user's selection.
     * 
     * @param selection The user's selection
     * @param inventory Inventory object to operate on
     * @implNote Time complexity varies depending on the selected action
     */
    public static void programFlow(int selection, Inventory inventory)
    {
        /* 
            - Switching between different functionalities based on user selection
            - Each selection corresponds to a specific action
        */
        if (selection == 1) /* adding device */
        {
            System.out.print("\nEnter category name: ");
            String _Category = scanner.nextLine();
         
            System.out.print("Enter device name: ");
            String _Name = scanner.nextLine();
         
            System.out.print("Enter price: ");
            String _PriceStr = scanner.nextLine();

            if (_PriceStr.endsWith("$")) 
            {
                _PriceStr = _PriceStr.substring(0, _PriceStr.length() - 1);    
            }

            double _Price = 0.0;

            try 
            {
                _Price = Double.parseDouble(_PriceStr);
            }
            catch (NumberFormatException e)
            {
                System.out.println("\nInvalid price format! Please enter a valid number.\n");
                return;
            }
         
            System.out.print("Enter quantity: ");
            String _QuantityStr = scanner.nextLine();

            int _Quantity = 0;

            try 
            {
                _Quantity = Integer.parseInt(_QuantityStr);
            }
            catch (NumberFormatException e)
            {
                System.out.println("\nInvalid quantity format! Please enter a valid integer.\n");
                return;
            }

            if (_Quantity <= 0) 
            {
                System.out.println("\nEnter a valid value for quantity!\n");
                return;    
            }

            if (_Price < 0.0) 
            {
                System.out.println("\nEnter a valid value for price!\n");    
                return;
            }

            ElectronicDevice device = new ElectronicDevice(_Category, _Name, _Price, _Quantity);            
            inventory.addDevice(device);
        }

        else if (selection == 2)
        {
            System.out.print("\nEnter device name: ");
            String _Name = scanner.nextLine();

            inventory.removeDevice(_Name);
        }

        else if (selection == 3) 
        {
            System.out.print("\nEnter the name of the device to update: ");
            String _Name = scanner.nextLine();
         
            inventory.updateDevice(_Name);
        }

        else if (selection == 4) 
        {
            inventory.displayAllDevices();
        }

        else if (selection == 5)
        {
            inventory.findTheCheapestDevice();    
        }

        else if (selection == 6)
        {
            inventory.sortingDeviceByPrice();    
        }

        else if (selection == 7) 
        {
            inventory.calculateTotalInventory();    
        }

        else if (selection == 8)
        {
            inventory.restockingDevice();    
        }
 
        else if (selection == 9) 
        {
            inventory.exportingInventoryReport();    
        }
         
        else
        {
            if (selection != 0) 
            {
                System.out.println("\nInvalid number, enter [1-9]\n");            
            }
        } 
    }
}