public class Customer extends Person 
{
	private int operator_ID;
	private Order[] orders;

	public Customer(String name, String surname, String address, String phone, int ID, int operator_ID)
	{
		super(name,surname,address,phone,ID);
		this.operator_ID = operator_ID;
		this.orders = new Order[100];
	}

	protected void companyName() // A function only for corporate customers
	{
		// ...
	}

	public void print_customer()
	{
		System.out.println("Name & Surname: " + getName() + " " + getSurname());
		System.out.println("Address: " + getAddress());
		System.out.println("Phone: " + getPhone());
		System.out.println("ID: " + getID());
		System.out.println("Operator ID: " + this.operator_ID);
		companyName();
		print_orders();
	}

	public void print_orders()
	{
		if (orders != null && orders.length > 0) 
		{
			for (int a = 0; a < orders.length; a++) 
			{
				Order order = orders[a];

				if (order != null) 
				{
					System.out.print("Order #" + (a + 1) + " => ");
					order.print_order();
				}
				
				else
				{
					break;
				}
			}
		}

		else
		{
			// System.out.println("No orders available!");
		}
	}

	public void define_orders(Order[] orders)
    {
    	if (orders != null)
    	{
    		for (Order order : orders) 
	        {
	            if (order != null && order.getCustomerID() == this.getID()) 
	            {
	                // If the customer_id of the order is equal to the ID of the current customer, add
	                for (int i = 0; i < this.orders.length; i++) 
	                {
	                    if (this.orders[i] == null) 
	                    {
	                        this.orders[i] = order;
	                        break;
	                    }
	                }
	            }
	        }
    	}
    }

	public int getOperatorID()
	{
		return this.operator_ID;
	}

	public String getCustomerType()
	{
		return "null";
	}
}