import java.util.Scanner;
import java.util.LinkedList;
import java.sql.Timestamp;
import java.util.Date;

/**
 * File class represents a file in the file system.
 */
public class File extends FileSystemElement
{
	/**
     * Constructs a new File object with the specified name, creation timestamp, and parent directory.
     * 
     * @param name        the name of the file
     * @param dataCreated the creation timestamp of the file
     * @param parent      the parent directory of the file
     */
	public File(String name, Timestamp dataCreated, FileSystemElement parent)
	{
		super(name, dataCreated, parent);
	}
}
