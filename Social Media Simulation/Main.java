import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Main class to interact with the social network analysis system.
 * Provides a command-line interface for users to add/remove people,
 * add/remove friendships, find shortest paths, suggest friends, and count clusters.
 */
public class Main 
{
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        SocialNetworkGraph socialNetwork = new SocialNetworkGraph();

        while (true) 
        {
            System.out.println("===== Social Network Analysis Menu =====");
            System.out.println("1. Add person");
            System.out.println("2. Remove person");
            System.out.println("3. Add friendship");
            System.out.println("4. Remove friendship");
            System.out.println("5. Find shortest path");
            System.out.println("6. Suggest friends");
            System.out.println("7. Count clusters");
            System.out.println("8. Exit");
            System.out.print("Please select an option: ");

            int choice = getIntInput(scanner, "Invalid input. Please enter a number between 1 and 8.");

            switch (choice) 
            {
                case 1:
                    addPerson(scanner, socialNetwork);
                    break;

                case 2:
                    removePerson(scanner, socialNetwork);
                    break;

                case 3:
                    addFriendship(scanner, socialNetwork);
                    break;

                case 4:
                    removeFriendship(scanner, socialNetwork);
                    break;

                case 5:
                    findShortestPath(scanner, socialNetwork);
                    break;

                case 6:
                    suggestFriends(scanner, socialNetwork);
                    break;

                case 7:
                    socialNetwork.countClusters();
                    break;

                case 8:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid input, please try again.");
            }
        }
    }

    /* gerçek fonksiyonları çağırma ve inputlrı alma kullancııdan */
    
    /**
     * Adds a person to the social network.
     * 
     * @param scanner the scanner to read user input
     * @param socialNetwork the social network graph
     */
    private static void addPerson(Scanner scanner, SocialNetworkGraph socialNetwork) 
    {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        int age = getIntInput(scanner, "Enter age: ", "Invalid input. Please enter a valid age.");

        System.out.print("Enter hobbies (comma-separated): ");
        String hobbiesInput = scanner.nextLine();
        List<String> hobbies = Arrays.asList(hobbiesInput.split(","));
        LocalDateTime timestamp = LocalDateTime.now();
        
        socialNetwork.addPerson(name, age, hobbies, timestamp);
    }

    /**
     * Removes a person from the social network.
     * 
     * @param scanner the scanner to read user input
     * @param socialNetwork the social network graph
     */
    private static void removePerson(Scanner scanner, SocialNetworkGraph socialNetwork) 
    {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter timestamp (yyyy-MM-dd HH:mm:ss): ");
        String timestampInput = scanner.nextLine();
        
        LocalDateTime timestampToRemove = LocalDateTime.parse(timestampInput, formatter);
        
        socialNetwork.removePerson(name, timestampToRemove);
    }

    /**
     * Adds a friendship between two people in the social network.
     * 
     * @param scanner the scanner to read user input
     * @param socialNetwork the social network graph
     */
    private static void addFriendship(Scanner scanner, SocialNetworkGraph socialNetwork) 
    {
        System.out.print("Enter first person’s name: ");
        String firstFriend = scanner.nextLine();

        System.out.print("Enter first person’s timestamp (yyyy-MM-dd HH:mm:ss): ");
        String timestamp1Input = scanner.nextLine();
        LocalDateTime timestamp1 = LocalDateTime.parse(timestamp1Input, formatter);

        System.out.print("Enter second person’s name: ");
        String secondFriend = scanner.nextLine();

        System.out.print("Enter second person’s timestamp (yyyy-MM-dd HH:mm:ss): ");
        String timestamp2Input = scanner.nextLine();
        LocalDateTime timestamp2 = LocalDateTime.parse(timestamp2Input, formatter);

        socialNetwork.addFriendship(firstFriend, timestamp1, secondFriend, timestamp2);
    }

    /**
     * Removes a friendship between two people in the social network.
     * 
     * @param scanner the scanner to read user input
     * @param socialNetwork the social network graph
     */
    private static void removeFriendship(Scanner scanner, SocialNetworkGraph socialNetwork) 
    {
        System.out.print("Enter first person’s name: ");
        String firstFriend = scanner.nextLine();

        System.out.print("Enter first person’s timestamp (yyyy-MM-dd HH:mm:ss): ");
        String timestamp1Input = scanner.nextLine();
        LocalDateTime timestamp1 = LocalDateTime.parse(timestamp1Input, formatter);

        System.out.print("Enter second person’s name: ");
        String secondFriend = scanner.nextLine();

        System.out.print("Enter second person’s timestamp (yyyy-MM-dd HH:mm:ss): ");
        String timestamp2Input = scanner.nextLine();
        LocalDateTime timestamp2 = LocalDateTime.parse(timestamp2Input, formatter);

        socialNetwork.removeFriendship(firstFriend, timestamp1, secondFriend, timestamp2);
    }

    /**
     * Finds the shortest path between two people in the social network.
     * 
     * @param scanner the scanner to read user input
     * @param socialNetwork the social network graph
     */
    private static void findShortestPath(Scanner scanner, SocialNetworkGraph socialNetwork) 
    {
        System.out.print("Enter first person’s name: ");
        String startName = scanner.nextLine();

        System.out.print("Enter second person’s name: ");
        String endName = scanner.nextLine();

        socialNetwork.findShortestPath(startName, endName);
    }

    /**
     * Suggests friends for a person in the social network.
     * 
     * @param scanner the scanner to read user input
     * @param socialNetwork the social network graph
     */
    private static void suggestFriends(Scanner scanner, SocialNetworkGraph socialNetwork) 
    {
        System.out.print("Enter person’s name: ");
        String name = scanner.nextLine();

        System.out.print("Enter person’s timestamp (yyyy-MM-dd HH:mm:ss): ");
        String timestampInput = scanner.nextLine();
        LocalDateTime timestamp = LocalDateTime.parse(timestampInput, formatter);

        int maxSuggestions = getIntInput(scanner, "Enter maximum number of friends to suggest: ", "Invalid input. Please enter a valid number.");

        socialNetwork.suggestFriends(name, timestamp, maxSuggestions);
    }

    /**
     * Reads an integer input from the user with validation.
     * 
     * @param scanner the scanner to read user input
     * @param prompt the prompt message to display
     * @param errorMessage the error message to display for invalid input
     * @return the integer input from the user
     */
    private static int getIntInput(Scanner scanner, String prompt, String errorMessage) 
    {
        int input;

        while (true) 
        {
            System.out.print(prompt);
            try 
            {
                input = scanner.nextInt();
                scanner.nextLine();
                break;
            } 
            catch (InputMismatchException e) 
            {
                System.out.println(errorMessage);
                scanner.nextLine();
            }
        }
        return input;
    }

    /**
     * Reads an integer input from the user with validation.
     * 
     * @param scanner the scanner to read user input
     * @param errorMessage the error message to display for invalid input
     * @return the integer input from the user
     */
    private static int getIntInput(Scanner scanner, String errorMessage) 
    {
        int input;

        while (true) 
        {
            try 
            {
                input = scanner.nextInt();
                scanner.nextLine();
                break;
            } 
            catch (InputMismatchException e) 
            {
                System.out.println(errorMessage);
                scanner.nextLine();
            }
        }
        return input;
    }
}
