import java.util.Scanner;
import java.util.LinkedList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Collections;
import java.util.Comparator;
import java.sql.Timestamp;

/**
 * The FileSystem class represents a basic file system with functionalities to navigate directories,
 * create, delete, move, search, list, and sort files and directories based on date created.
 * It allows users to interact with directories and files in a hierarchical manner.
 */
public class FileSystem
{
	/**
     * Represents the root directory of the file system.
     */
	private Directory root;
	/**
     * Scanner object for reading user input.
     */
	private Scanner scanner;

	/**
     * Constructs a FileSystem object with a root directory and initializes the scanner for user input.
     */
	public FileSystem()
	{
		root = new Directory("root", new Timestamp(System.currentTimeMillis()), null);
		scanner = new Scanner(System.in);
	}

	/**
     * Retrieves the root directory of the file system.
     *
     * @return The root directory.
     */
	public Directory getRoot()
	{
		return root;
	}

	/**
     * Changes the current directory to the specified directory.
     *
     * @param currentDir The current directory.
     * @return The new directory after the change.
     */
	public Directory changeDirectory(Directory currentDir) 
	{
	    System.out.print("Current directory: " + getCurrentDirectoryPath(currentDir));
	    
	    System.out.print("\nEnter new directory path: ");
	    String path = scanner.nextLine();

	    Directory target = readBranches(path);

	    if (target != null) 
	    {
	        System.out.println("Directory changed to: " + path);
	        return target;
	    } 

	    else 
	    {
	        System.out.println("Invalid path.");
	        return currentDir;
	    }
	}

	/**
     * Reads and resolves the directory path and returns the corresponding directory.
     *
     * @param path The directory path to resolve.
     * @return The resolved directory or null if the path is invalid.
     */
	public Directory readBranches(String path) 
	{
	    String[] directories = path.split("/");
	    Directory currentDirectory = root;

	    for (String directory : directories) 
	    {
	        if (directory.isEmpty()) 
	        {
	            continue; 
	        }

	        FileSystemElement element = currentDirectory.getChildren().stream()
	                .filter(child -> child.getName().equals(directory))
	                .findFirst()
	                .orElse(null);

	        if (element != null && element instanceof Directory) 
	        {
	            currentDirectory = (Directory) element;
	        } 

	        else 
	        {
	            System.out.println("Directory not found: " + directory);
	            return null;
	        }
	    }
	    return currentDirectory;
	}

	/**
     * Lists the contents of the specified directory.
     *
     * @param currentDir The directory whose contents to list.
     */
	public void listDirectoryContents(Directory currentDir) 
	{
	    System.out.println("Listing contents of " + currentDir.getName() + ":");

	    LinkedList<FileSystemElement> children = currentDir.getChildren();

	    for (FileSystemElement element : children) 
	    {
	        if (element instanceof Directory)
	        {
	            System.out.println("* " + element.getName() + "/");
	        } 

	        else 
	        {
	            System.out.println(element.getName());
	        }
	    }
	}

	/**
     * Creates a new file or directory based on user input.
     *
     * @param currentDir The current directory.
     */
	public void create(Directory currentDir)
	{		
		System.out.print("Current directory: " + getCurrentDirectoryPath(currentDir));
		System.out.print("\nCreate file or directory (f/d): ");
		String choice = scanner.nextLine();

		if (choice.equals("f")) 
		{
			createFile(currentDir);
		}

		else if (choice.equals("d")) 
		{
			createDirectory(currentDir);
		}

		else 
		{
			System.out.println("Invalid choice, please enter only f and d.");
		}
	}

	/**
     * Creates a new directory in the current directory.
     *
     * @param currentDir The current directory.
     */
	public void createDirectory(Directory currentDir)
	{
		System.out.print("Enter name for new directory: ");
		String newName = scanner.nextLine();

		if (currentDir.containsChildren(newName)) 
		{
			System.out.println("A directory with this name already exists.");	
		}

		else 
		{
			Directory newDirectory = new Directory(newName, new Timestamp(System.currentTimeMillis()), currentDir);
			currentDir.addChildren(newDirectory);
			System.out.println("Directory created: " + newName + "/");
		}		
	}

	/**
     * Creates a new file in the current directory.
     *
     * @param currentDir The current directory.
     */
	public void createFile(Directory currentDir)
	{
		System.out.print("Enter name for new file: ");
		String newName = scanner.nextLine();

		if (currentDir.containsChildren(newName)) 
		{
			System.out.println("A file with this name already exists.");	
		}

		else 
		{
			File newFile = new File(newName, new Timestamp(System.currentTimeMillis()), currentDir);
			currentDir.addChildren(newFile);
			System.out.println("File created: " + newName);
		}
	}

	/**
     * Deletes a file or directory from the current directory.
     *
     * @param currentDir The current directory.
     */
	public void delete(Directory currentDir)
	{
		System.out.print("Current directory: " + getCurrentDirectoryPath(currentDir));
		System.out.print("\nEnter name of file/directory to delete: ");
		String toDelete = scanner.nextLine();

		if (!currentDir.containsChildren(toDelete)) 
		{
			System.out.println("Cannot delete '" + toDelete + "': No such file or directory");	
		}

		else 
		{
			FileSystemElement elementToDelete = null;

			for (FileSystemElement element : currentDir.getChildren()) 
			{
				if (element.getName().equals(toDelete)) 
				{
					elementToDelete = element;
					break;		
				}	
			}

			if (elementToDelete instanceof Directory) // Eğer silinecek öğe klasörse recursive olarak sil
			{
				deleteDirectory((Directory) elementToDelete);	
			}
			currentDir.removeChildren(elementToDelete);
			System.out.println("File deleted: " + toDelete);
		}
	}

	/**
     * Recursively deletes a directory and its contents.
     *
     * @param directory The directory to delete.
     */
	public void deleteDirectory(Directory directory)
	{
		for (FileSystemElement element : directory.getChildren()) 
		{
			if (element instanceof Directory) 
			{
				deleteDirectory((Directory) element);		
			}	
		}
		directory.getParent().removeChildren(directory);
	}

	/**
     * Moves a file or directory to a new directory.
     *
     * @param currentDir The current directory.
     */
	public void move(Directory currentDir) 
	{
        System.out.println("Current directory: " + getCurrentDirectoryPath(currentDir));
        
        System.out.print("Enter the name of file/directory to move: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter new directory path: ");
        String newPath = scanner.nextLine();
    
        FileSystemElement toMove = null;

        for (FileSystemElement element : currentDir.getChildren()) 
        {
            if (element.getName().equals(name)) 
            {
                toMove = element;
                break;
            }
        }

        if (toMove == null) 
        {
            System.out.println("No such file or directory: " + toMove);
            return;
        }
        Directory newDirectory = readBranches(newPath);

        if (newDirectory == null) 
        {
            System.out.println("Invalid directory path: " + newDirectory);
            return;
        }
    
        if (newDirectory == currentDir) 
        {
            System.out.println("The new directory path is the same as the current directory.");
            return;
        }

        if (moveElementToNewDirectory(toMove, newDirectory)) 
        {
            System.out.println("Successfully moved '" + name + "' to '" + newPath + "'.");
        } 

        else 
        {
            System.out.println("Failed to move '" + name + "'.");
        }
    }

    /**
     * Moves a file or directory to a new directory.
     *
     * @param element The file or directory to move.
     * @param newDirectory The destination directory.
     * @return True if the move operation is successful, false otherwise.
     */
    public boolean moveElementToNewDirectory(FileSystemElement element, Directory newDirectory) 
    {
        if (element.getParent() != null && element.getParent() instanceof Directory) 
        {
            Directory parentDirectory = (Directory) element.getParent();
            parentDirectory.removeChildren(element);
        }
        element.setParent(newDirectory);
        newDirectory.addChildren(element);

        return true;
    }

    /**
     * Searches for a file or directory in the file system starting from the current directory.
     *
     * @param currentDir The current directory.
     */
	public void search(Directory currentDir) 
	{
		System.out.print("Search query: ");
	    String fileName = scanner.nextLine();

	    String searchResult = searchRecursive(fileName, root, "");

	    if (searchResult != null) 
	    {
	    	System.out.println("Searching from current directory...");	
	    	System.out.println(searchResult);
	    }

	    else 
	    {
	        System.out.println("File not found: " + fileName);
	    }
	}

	/**
     * Recursively searches for a file or directory in the file system.
     *
     * @param fileName The name of the file or directory to search for.
     * @param currentDir The current directory.
     * @param path The path traversed during the search.
     * @return The search result or null if the file or directory is not found.
     */
	private String searchRecursive(String fileName, Directory currentDir, String path) 
	{
		if (currentDir.getName().equals(fileName)) // Eğer mevcut dizin adı aranan dosya veya dizin adına eşitse
	    {
	        return "Found: /" + path + " (directory)";
	    }
	    LinkedList<FileSystemElement> children = currentDir.getChildren();

	    for (FileSystemElement element : children) 
	    {
	        if (element instanceof Directory) 
	        {
	            Directory subDirectory = (Directory) element;
	            String result = searchRecursive(fileName, subDirectory, path + subDirectory.getName() + "/");
	           
	            if (result != null)
	            {
	                return result;
	            }
	        } 

	        else 
	        {
	            if (element.getName().equals(fileName)) 
	            {
	                return "Found: /" + path + fileName;
	            }
	        }
	    }
	    return null;
	}

	/**
     * Prints the directory tree starting from the root directory.
     *
     * @param currentDir The current directory.
     */
	public void printDirectoryTree(Directory currentDir) 
	{
	    System.out.println("Path to current directory from root:");
	    System.out.println("* root/"); // Root dizini özel olarak bastırılıyor
	    printDirectoryTreeRecursive(root, "");
	}

	/**
	 * Recursively prints the directory tree starting from the specified directory.
	 *
	 * @param directory The directory whose tree to print.
	 * @param indent The indentation for each level of the directory tree.
	 */
	private void printDirectoryTreeRecursive(Directory directory, String indent) 
	{
	    LinkedList<FileSystemElement> children = directory.getChildren();

	    for (FileSystemElement element : children) 
	    {
	        if (element instanceof Directory) 
	        {
	            Directory subDirectory = (Directory) element;
	            System.out.println(indent + "    " + "* " + subDirectory.getName() + "/");
	            printDirectoryTreeRecursive(subDirectory, indent + "    ");
	        } 

	        else 
	        {
	            File file = (File) element;
	            System.out.println(indent + "    " + file.getName());
	        }
	    }
	}

	/**
     * Sorts the contents of a directory by date created.
     *
     * @param directory The directory whose contents to sort.
     */
	public void sortContentsByDateCreated(Directory directory) 
	{
	    LinkedList<FileSystemElement> children = directory.getChildren();

	    Collections.sort(children, new Comparator<FileSystemElement>() 
	    {
	        @Override
	        public int compare(FileSystemElement o1, FileSystemElement o2) 
	        {
	            Timestamp date1 = o1.getDataCreated();
	            Timestamp date2 = o2.getDataCreated();
	            return date1.compareTo(date2);
	        }
	    });

	    System.out.println("Sorted contents of " + directory.getName() + " by date created:");

	    for (FileSystemElement element : children) 
	    {
	        if (element instanceof Directory) 
	        {
	            Directory subDirectory = (Directory) element;
	            System.out.println("* " + subDirectory.getName() + "/ (" + subDirectory.getDataCreated() + ")");
	        } 

	        else 
	        {
	            System.out.println(element.getName() + " (" + element.getDataCreated() + ")");
	        }
	    }
	}

	/**
     * Constructs and returns the current directory path.
     *
     * @param currentDir The current directory.
     * @return The current directory path.
     */
	public String getCurrentDirectoryPath(Directory currentDir) 
	{
	    StringBuilder pathBuilder = new StringBuilder();
	    
	    if (currentDir.getParent() == null) 
	    {
	        return "/root";
	    }

	    else 
	    {
	    	while (currentDir != null) 
		    {
		        pathBuilder.insert(0, "/" + currentDir.getName());
		        currentDir = currentDir.getParent();
		    }
	    }
	    return pathBuilder.toString();
	}
}
