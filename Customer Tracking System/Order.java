public class Order
{
	private int count;
	private int status;
	private int total_price;
	private int customer_ID;
	private String product_name;

	public Order(int count, int status, int total_price, int customer_ID, String product_name)
	{
		this.count = count;
		this.status = status;
		this.total_price = total_price;
		this.customer_ID = customer_ID;
		this.product_name = product_name;
	}
	
	public void print_order()
	{
		System.out.print
		(
			"Product name: "   + this.product_name + " - Count: " + this.count + 
			" - Total price: " + this.total_price  + " - Status: "
		);

		if (this.status == 0) 
		{
			System.out.println("Initialized.");
		}

		else if (this.status == 1) 
		{
			System.out.println("Processing.");
		}
		
		else if (this.status == 2) 
		{
			System.out.println("Completed.");
		}

		else if (this.status == 3) 
		{
			System.out.println("Cancelled.");
		}
	}

	public int getCustomerID()
	{
		return this.customer_ID;
	}
}