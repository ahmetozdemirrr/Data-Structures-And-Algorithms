public class corporate_customer extends Customer
{
	private String company_name;

	public corporate_customer(String name, String surname, String address, String phone, int ID, int operator_ID, String company_name)
	{
		super(name,surname,address,phone,ID,operator_ID);
		this.company_name = company_name;
	}

	@Override
    public String getCustomerType() 
    {
        return "corporate";
    }

    protected void companyName() // for print the customer's company
    {
    	System.out.println("Company name: " + company_name);
    }
} 