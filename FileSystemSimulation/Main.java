import java.util.Scanner;
import java.util.LinkedList;
import java.sql.Timestamp;
import java.util.Date;
import java.util.InputMismatchException;

/**
 * Main class provides a user interface menu for interacting with the file system.
 */
public class Main 
{
    /** Scanner object for reading user input */
    public static Scanner scanner = new Scanner(System.in);

    /**
     * Main method to start the program.
     * 
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) 
    {
        FileSystem fileSystem = new FileSystem();
        Directory currentDir = fileSystem.getRoot();

        currentDir = systemMenu(fileSystem, currentDir);
    }

    /**
     * Displays the system menu and handles user input.
     * 
     * @param fileSystem the FileSystem object
     * @param currentDir the current directory
     * @return the updated current directory after menu interaction
     */
    public static Directory systemMenu(FileSystem fileSystem, Directory currentDir) // User Interface Menu
    {
        int selection;

        do
        {
            try
            {
                System.out.println("===== File System Managament Menu =====");
                System.out.println("1. Change directory");
                System.out.println("2. List directory contents");
                System.out.println("3. Create file/directory");
                System.out.println("4. Delete file/directory");
                System.out.println("5. Move file/directory");
                System.out.println("6. Search file/directory");
                System.out.println("7. Print directory tree");
                System.out.println("8. Sorts content by date created");
                System.out.println("9. Exit");
                System.out.print("Please select an option: ");

                selection = scanner.nextInt();
                scanner.nextLine();
                currentDir = redirectFlow(selection, fileSystem, currentDir); // If we change the current directory in the menu take new directory
            }

            catch (InputMismatchException e)
            {
                System.out.println("\nInvalid input!. Please enter a valid integer.\n");
                scanner.nextLine();
                selection = -1;
            }
        }
        while(selection != 9);

        return currentDir;
    }

    /**
     * Redirects the flow of the program based on user input.
     * 
     * @param selection the user's menu selection
     * @param fileSystem the FileSystem object
     * @param currentDir the current directory
     * @return the updated current directory after action
     */
    public static Directory redirectFlow(int selection, FileSystem fileSystem, Directory currentDir)
    {
        if (selection == 1) 
        {
            currentDir = fileSystem.changeDirectory(currentDir); // If we change the current directory in the function take new directory
        }

        else if (selection == 2) 
        {
            fileSystem.listDirectoryContents(currentDir);
        }

        else if (selection == 3) 
        {
            fileSystem.create(currentDir);
        }

        else if (selection == 4) 
        {
            fileSystem.delete(currentDir);
        }

        else if (selection == 5) 
        {
            fileSystem.move(currentDir);
        }

        else if (selection == 6) 
        {
            fileSystem.search(currentDir);
        }

        else if (selection == 7) 
        {
            fileSystem.printDirectoryTree();
        }

        else if (selection == 8) 
        {
            fileSystem.sortContentsByDateCreated(currentDir);
        }

        else if (selection == 9) 
        {
            System.exit(0);
        }

        else
        {
            System.out.println("Invalid input for menu, please enter only [1-9]");
        }
        return currentDir;
    }
}