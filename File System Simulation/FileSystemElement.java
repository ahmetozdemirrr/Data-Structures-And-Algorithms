import java.util.LinkedList;
import java.util.Scanner;
import java.sql.Timestamp;
import java.util.Date;
import java.util.InputMismatchException;

/**
 * Abstract class representing a file system element (either a file or a directory) in the file system.
 */
abstract class FileSystemElement
{
	/** The name of the file system element */
	protected String name;
	/** The creation timestamp of the file system element */
	protected Timestamp dataCreated;
	/** The parent directory of the file system element */
	protected FileSystemElement parent;

	/**
     * Constructs a new FileSystemElement object with the specified name, creation timestamp, and parent directory.
     * 
     * @param name        the name of the file system element
     * @param dataCreated the creation timestamp of the file system element
     * @param parent      the parent directory of the file system element
     */
	public FileSystemElement(String name, Timestamp dataCreated, FileSystemElement parent)
	{
		this.name = name;
		this.dataCreated = dataCreated;
		this.parent = parent;
	}

	/**
     * Retrieves the name of the file system element.
     * 
     * @return the name of the file system element
     */
	public String getName() 
	{
        return name;
    }

    /**
     * Retrieves the creation timestamp of the file system element.
     * 
     * @return the creation timestamp of the file system element
     */
    public Timestamp getDataCreated() 
    {
        return dataCreated;
    }

    /**
     * Retrieves the parent directory of the file system element.
     * 
     * @return the parent directory of the file system element, or null if the element has no parent
     */
    public Directory getParent() 
    {
        if (parent instanceof Directory) 
        {
	        return (Directory) parent;
	    } 

	    else 
	    {
	        return null;
	    }
    }

    /**
     * Sets the name of the file system element.
     * 
     * @param name the new name to set
     */
    public void setName(String name) 
	{
        this.name = name;
    }

    /**
     * Sets the creation timestamp of the file system element.
     * 
     * @param dataCreated the new creation timestamp to set
     */
    public void setDataCreated(Timestamp dataCreated) 
    {
        this.dataCreated = dataCreated;
    }

    /**
     * Sets the parent directory of the file system element.
     * 
     * @param parent the new parent directory to set
     */
    public void setParent(FileSystemElement parent) 
    {
        this.parent = parent;
    }

    /**
     * Checks if the current file system element is equal to another object.
     * 
     * @param object the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object object) 
    {
        if (this == object) 
        {
            return true;
        }

        if (object == null || getClass() != object.getClass()) 
        {
            return false;
        }
        FileSystemElement other = (FileSystemElement) object;
        return name.equals(other.name);
    }
}
