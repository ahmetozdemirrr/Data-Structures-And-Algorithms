public class Operator extends Person
{
	private int wage;
	private Customer[] customers;

	public Operator(String name, String surname, String address, String phone, int ID, int wage)
	{
		super(name,surname,address,phone,ID);
		this.wage = wage;
		this.customers = new Customer[100];
	}

	public void print_operator()
	{
		System.out.println("----------------------------");
		System.out.println("Name & Surname: " + getName() + " " + getSurname());
		System.out.println("Address: " + getAddress());
		System.out.println("Phone: " + getPhone());
		System.out.println("ID: " + getID());
		System.out.println("Wage: " + this.wage);
		print_customers();
		System.out.println("----------------------------");
	}

	public void print_customers() 
	{
	    int isEmpty = 0; // By default we assume that all customers are empty

	    if (customers != null && customers.length > 0) 
	    {
	        for (int a = 0; a < customers.length; a++) 
	        {
	            Customer customer = customers[a];

	            if (customer != null) 
	            {
	                System.out.println("----------------------------");
	                System.out.print("Customer #" + (a + 1));

	                if (!customer.getName().trim().isEmpty()) 
	                {
	                    isEmpty = 1; // Set isEmpty to false when at least one customer is full
	                    String customerType = customer.getCustomerType();
	                    System.out.println(" (a " + customerType + " customer):");
	                    customer.print_customer();
	                }
	            }
	        }
	    }

	    if (isEmpty == 0) 
	    {
	    	System.out.println("----------------------------");
	        System.out.println("This operator doesn't have any customer.");
	    }
	}


	public void define_customers(Customer[] customers)
	{
		if (customers != null)
    	{
    		for (Customer customer : customers) 
	        {
	            if (customer != null && customer.getOperatorID() == this.getID()) 
	            {
	                for (int i = 0; i < this.customers.length; i++) 
	                {
	                    if (this.customers[i] == null) 
	                    {
	                        this.customers[i] = customer;
	                        break;
	                    }
	                }
	            }
	        }
    	}
	}
}