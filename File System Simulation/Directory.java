import java.util.Scanner;
import java.util.LinkedList;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Directory class represents a directory in the file system.
 */
public class Directory extends FileSystemElement
{
	/** List of child elements (files and sub-directories) */
	private LinkedList<FileSystemElement> children;

	/**
     * Constructs a new Directory object with the specified name, creation timestamp, and parent directory.
     * 
     * @param name        the name of the directory
     * @param dataCreated the creation timestamp of the directory
     * @param parent      the parent directory of the directory
     */
	public Directory(String name, Timestamp dataCreated, FileSystemElement parent)
	{
		super(name, dataCreated, parent);
		this.children = new LinkedList<> ();
	}

	/**
     * Checks if the directory contains a child element with the specified name.
     * 
     * @param name the name of the child element to check
     * @return true if the directory contains a child element with the specified name, otherwise false
     */
	public boolean containsChildren(String name) 
	{
	    for (FileSystemElement element : children) 
	    {
	        if (element instanceof Directory && ((Directory) element).getName().equals(name)) 
	        {
	            return true;
	        }
	        
	        if (element instanceof File && ((File) element).getName().equals(name)) 
	        {
	            return true;
	        }
	    }
	    return false;
	}

	/**
     * Adds a child element to the directory.
     * 
     * @param element the child element to add
     */
	public void addChildren(FileSystemElement element)
	{
		children.add(element);
	}

	/**
     * Removes a child element from the directory.
     * 
     * @param element the child element to remove
     */
	public void removeChildren(FileSystemElement element)
	{
		children.remove(element);
	}

	/**
     * Retrieves the list of child elements of the directory.
     * 
     * @return the list of child elements
     */
	public LinkedList<FileSystemElement> getChildren()
	{
		return children;
	}
}
