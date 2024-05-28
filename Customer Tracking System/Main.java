import java.io.File;
import java.util.Scanner;

public class Main 
{
    public static class TotalNumbers // We keep the number of elements of all arrays (to be static) in this subclass. 
    {
        public int totalOrder;
        public int totalCustomer;
        public int totalOperator;

        public TotalNumbers(int totalOrder, int totalCustomer, int totalOperator)
        {
            this.totalOrder    = totalOrder;
            this.totalCustomer = totalCustomer;
            this.totalOperator = totalOperator;
        }
    }

    public static void main(String[] args) 
    {
        // Our arrays
        Order[] orders = new Order[100];
        Operator[] operators = new Operator[100];
        Customer[] customers = new Customer[100];

        TotalNumbers totalNumbers = new TotalNumbers(0,0,0);

        readFile("content.txt", orders, operators, customers, totalNumbers);
        addToArray(orders, customers, operators, totalNumbers);

        printAllData(orders, operators, customers, totalNumbers);

        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please enter your ID...");

        if (scanner.hasNextInt()) 
        {
            int id = scanner.nextInt();

            if (id >= 0)
            {
                checkID(id, orders, operators, customers, totalNumbers);
            } 

            else // Report an error if the input is less than zero.
            {
                System.out.println("Invalid input! ID must be a non-negative integer.");
            }
        } 

        else // Report an error if the input is not integer.
        {
            System.out.println("Invalid input! Please enter a valid integer for ID.");
        }
    }

    public static void readFile(String file, Order[] orders, Operator[] operators, Customer[] customers, TotalNumbers totalNumbers)
    {
        try
        {
            Scanner scanner = new Scanner(new File(file));

            while(scanner.hasNextLine())
            {
                int flag = 0; // for empty string we will use this flag, because when we write break in for loop only for loop will be finish
                
                String line = scanner.nextLine();
                String[] data = line.split(";",-1);

                for (String element : data) // If any string is empty (go line 78)
                {                    
                    if (element.trim().isEmpty()) 
                    {
                        flag = 1;
                    }
                }

                if (flag == 1) // ignore that line
                {
                    continue;   
                }

                if (("order".equals(data[0])) && (data.length == 6)) // extra & missing column 
                {
                    addToOrderArray(orders,data,totalNumbers);
                }

                else if (("retail_customer".equals(data[0])) && (data.length == 7)) // extra & missing column
                {
                    int idExists = 0;

                    for (Customer customer : customers) 
                    {
                        if (customer == null) 
                        {
                            continue;
                        }

                        if ((Integer.parseInt(data[5])) == (customer.getID())) 
                        {
                            // Customer ID already exists, no transaction
                            idExists = 1;
                            continue;
                        }
                    }

                    if (idExists == 0) 
                    {
                        for (Operator operator : operators) 
                        {
                            if (operator == null) 
                            {
                                continue;
                            }

                            if ((Integer.parseInt(data[5])) == (operator.getID())) 
                            {
                                // Operator ID already exists, no transaction
                                idExists = 1;
                                continue;
                            }
                        }
                    }

                    if (idExists == 0) 
                    {
                        // No customers or operators matching any ID, add
                        addToRetailCustomerArray(customers, data, orders, totalNumbers);
                    }
                }

                else if (("corporate_customer".equals(data[0])) && (data.length == 8)) // extra & missing column
                {
                    int idExists = 0;

                    for (Customer customer : customers) 
                    {
                        if (customer == null) 
                        {
                            continue;
                        }

                        if ((Integer.parseInt(data[5])) == (customer.getID())) 
                        {
                            // Customer ID already exists, no transaction
                            idExists = 1;
                            continue;
                        }
                    }

                    if (idExists == 0) 
                    {
                        for (Operator operator : operators) 
                        {
                            if (operator == null) 
                            {
                                continue;
                            }

                            if ((Integer.parseInt(data[5])) == (operator.getID())) 
                            {
                                // Operator ID already exists, no transaction
                                idExists = 1;
                                continue;
                            }
                        }
                    }

                    if (idExists == 0) 
                    {
                        // No customers or operators matching any ID, add
                        addToCorporateCustomerArray(customers,data,orders,totalNumbers);
                    }
                }

                else if (("operator".equals(data[0])) && (data.length == 7)) // extra & missing column 
                {
                    int idExists = 0;

                    for (Customer customer : customers) 
                    {
                        if (customer == null) 
                        {
                            continue;
                        }

                        if ((Integer.parseInt(data[5])) == (customer.getID())) 
                        {
                            // Customer ID already exists, no transaction
                            idExists = 1;
                            continue;
                        }
                    }

                    if (idExists == 0) 
                    {
                        for (Operator operator : operators) 
                        {
                            if (operator == null) 
                            {
                                continue;
                            }

                            if ((Integer.parseInt(data[5])) == (operator.getID())) 
                            {
                                // Operator ID already exists, no transaction
                                idExists = 1;
                                continue;
                            }
                        }
                    }

                    if (idExists == 0) 
                    {
                        // No customers or operators matching any ID, add
                        addToOperatorArray(operators,data,customers,totalNumbers);
                    }
                }

                else // unknown identifier
                {
                    continue;
                }
            }
            scanner.close();
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    public static void addToOrderArray(Order[] orders, String[] data, TotalNumbers totalNumbers)
    {
        String product_name = data[1];
        int count;
        int total_price;
        int status;
        int customer_ID;

        try // Check whether the String is a number and whether it is translatable
        {
            count = Integer.parseInt(data[2]);
            total_price = Integer.parseInt(data[3]);
            status = Integer.parseInt(data[4]);
            customer_ID = Integer.parseInt(data[5]);

            if (count <= 0 || total_price < 0 || status < 0 || status > 3 || customer_ID < 0) 
            {
                // System.out.println("Invalid data in the file. Skipping the line.");
                return;
            }
        } 

        catch (NumberFormatException e) 
        {
            // System.out.println("Invalid data format in the file. Skipping the line.");
            return;
        }
        Order newOrder = new Order(count, status, total_price, customer_ID, product_name);
        orders[totalNumbers.totalOrder++] = newOrder;   
    }

    public static void addToRetailCustomerArray(Customer[] customers, String[] data, Order[] orders, TotalNumbers totalNumbers)
    {
        String name = data[1];
        String surname = data[2];
        String address = data[3];
        String phone = data[4];
        int ID;
        int operator_ID;

        try 
        {
            ID = Integer.parseInt(data[5]);
            operator_ID = Integer.parseInt(data[6]);

            if ((ID < 0) || (operator_ID < 0)) 
            {
                // System.out.println("Invalid data in the file. Skipping the line.");
                return;
            }
        }

        catch (NumberFormatException e)
        {
            // System.out.println("Invalid data format in the file. Skipping the line.");
            return;
        }
        retail_customer newRetailCustomer = new retail_customer(name, surname, address, phone, ID, operator_ID);
        customers[totalNumbers.totalCustomer++] = newRetailCustomer;
    }

    public static void addToCorporateCustomerArray(Customer[] customers, String[] data, Order[] orders, TotalNumbers totalNumbers)
    {
        String name = data[1];
        String surname = data[2];
        String address = data[3];
        String phone = data[4];
        int ID;
        int operator_ID;
        String company_name = data[7];

        try 
        {
            ID = Integer.parseInt(data[5]);
            operator_ID = Integer.parseInt(data[6]);

            if ((ID < 0) || (operator_ID < 0)) 
            {
                // System.out.println("Invalid data in the file. Skipping the line.");
                return;
            }
        }

        catch (NumberFormatException e)
        {
            // System.out.println("Invalid data format in the file. Skipping the line.");
            return;
        }
        corporate_customer newCorporateCustomer = new corporate_customer(name,surname,address,phone,ID,operator_ID,company_name);
        customers[totalNumbers.totalCustomer++] = newCorporateCustomer;
    }

    public static void addToOperatorArray(Operator[] operators, String[] data, Customer[] customers, TotalNumbers totalNumbers)
    {
        String name = data[1];
        String surname = data[2];
        String address = data[3];
        String phone = data[4];
        int ID;
        int wage;

        try 
        {
            ID = Integer.parseInt(data[5]);
            wage = Integer.parseInt(data[6]);

            if ((ID < 0) || (wage < 0)) 
            {
                // System.out.println("Invalid data in the file. Skipping the line.");
                return;
            }
        }

        catch (NumberFormatException e)
        {
            // System.out.println("Invalid data format in the file. Skipping the line.");
            return;
        }
        Operator newOperator = new Operator(name,surname,address,phone,ID,wage);
        operators[totalNumbers.totalOperator++] = newOperator;
    }

    /* We assign all arrays to class objects at the end so that there is no confusion when reading the file. */
    public static void addToArray(Order[] orders, Customer[] customers, Operator[] operators, TotalNumbers totalNumbers)
    {
        for (int a = 0; a < totalNumbers.totalCustomer; a++) 
        {
            customers[a].define_orders(orders);
        }

        for (int a = 0; a < totalNumbers.totalOperator; a++) 
        {
            operators[a].define_customers(customers);   
        }
    } 

    public static void checkID(int ID, Order[] orders, Operator[] operators, Customer[] customers, TotalNumbers totalNumbers)
    { 
        int isExists = 0;

        for (int a = 0; a < totalNumbers.totalCustomer; a++) 
        {            
            if (ID == customers[a].getID()) 
            {
                isExists = 1;
                System.out.println("*** Customer Screen ***");
                customers[a].print_customer();
            }
        }

        for (int a = 0; a < totalNumbers.totalOperator; a++)
        {
            if (ID == operators[a].getID()) 
            {
                isExists = 1;
                System.out.println("*** Operator Screen ***");
                operators[a].print_operator();    
            }
        }

        if (isExists == 0) 
        {
            System.out.println("No operator/customer was found with ID " + ID + ". Please try again.");
        }
    }

    public static void printAllData(Order[] orders, Operator[] operators, Customer[] customers, TotalNumbers totalNumbers) 
{
        System.out.println("*** Orders ***");
        for (int i = 0; i < totalNumbers.totalOrder; i++) {
            orders[i].print_order();
        }

        System.out.println("*** Customers ***");
        for (int i = 0; i < totalNumbers.totalCustomer; i++) {
            customers[i].print_customer();
        }

        System.out.println("*** Operators ***");
        for (int i = 0; i < totalNumbers.totalOperator; i++) {
            operators[i].print_operator();
        }
    }
}

