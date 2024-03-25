public class Person
{
	private String name;
	private String surname;
	private String address;
	private String phone;
	private int ID;

	public Person(String name, String surname, String address, String phone, int ID)
	{
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.phone = phone;
		this.ID = ID;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return this.name;
	}

	public void setSurname(String surname)
	{
		this.surname = surname;
	}

	public String getSurname()
	{
		return this.surname;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getAddress()
	{
		return this.address;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getPhone()
	{
		return this.phone;
	}

	public void setID(int ID)
	{
		this.ID = ID;
	}

	public int getID()
	{
		return this.ID;
	}
}