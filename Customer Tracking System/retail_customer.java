public class retail_customer extends Customer
{
	public retail_customer(String name, String surname, String address, String phone, int ID, int operator_ID)
	{
		super(name,surname,address,phone,ID,operator_ID);
	}

	@Override
    public String getCustomerType() 
    {
        return "retail";
    }
}