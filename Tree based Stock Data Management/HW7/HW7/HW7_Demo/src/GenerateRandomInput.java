import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * The GenerateRandomInput class generates random input commands for stock management
 * and writes them to a file. The commands include adding, removing, searching, and
 * updating stocks with randomly generated properties.
 */
public class GenerateRandomInput 
{
    // Random object to be used to generate random numbers
    private static final Random RANDOM = new Random(System.currentTimeMillis()); 
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
    /* output directory and file */
    private static final String OUTPUT_DIR = "../out/production/HW7_Demo";
    private static final String OUTPUT_FILE = OUTPUT_DIR + "/input.txt";
    // for command number minimum and maksimum values
    private static final int MIN_COMMANDS = 100;
    private static final int MAX_COMMANDS = 500;
    // companys name length
    private static final int SYMBOL_LENGTH = 4;

    // Set for comapnys name we 
    private static final Set<String> symbols = new HashSet<>();

    /*
        note: we keep these names here because creating random 
        names all the time and never using them makes the 
        search, remove and update commands ineffective
    */
    public static void main(String[] args) 
    {
        // determines randomly, commands number
        int numCommands = RANDOM.nextInt((MAX_COMMANDS - MIN_COMMANDS) + 1) + MIN_COMMANDS;

        try 
        {
            // create output directıry
            Files.createDirectories(Paths.get(OUTPUT_DIR));

            // start the writing output file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) 
            {
                for (int i = 0; i < numCommands; i++) // randomly creating command and add to the file
                {
                    String command = generateCommand();
                    writer.write(command);
                    writer.newLine();
                }
                System.out.println("Random input file generated with " + numCommands + " commands: " + OUTPUT_FILE);
            }
        } 

        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    /**
     * Generates a random command for stock management.
     *
     * @return A string representing the command.
     */
    private static String generateCommand() // method for generating random command
    {
        String[] commands = {"ADD", "REMOVE", "SEARCH", "UPDATE"}; // command types
        String command = commands[RANDOM.nextInt(commands.length)]; //randomly selecting a command

        switch (command) 
        {
            case "ADD":

                String symbolToAdd = generateUniqueSymbol();
                symbols.add(symbolToAdd);

                return String.format("%s %s %.2f %d %d", command, symbolToAdd, generatePrice(), generateVolume(), generateMarketCap());

            case "REMOVE":

                return String.format("%s %s", command, getRandomExistingSymbol());

            case "SEARCH":

                return String.format("%s %s", command, getRandomExistingSymbol());

            case "UPDATE":

                return String.format("%s %s %.2f %d %d", command, getRandomExistingSymbol(), generatePrice(), generateVolume(), generateMarketCap());

            default:

                return "";
        }
    }

    /**
     * Generates a unique stock symbol.
     *
     * @return A unique stock symbol.
     */
    private static String generateUniqueSymbol() // method to create unique symbol
    {
        String symbol;

        do 
        {
            symbol = generateSymbol();
        } 
        while (symbols.contains(symbol));

        return symbol;
    }

    /**
     * Generates a random stock symbol.
     *
     * @return A random stock symbol.
     */
    private static String generateSymbol() 
    {
        StringBuilder symbol = new StringBuilder(SYMBOL_LENGTH);

        // Rastgele sembol karakterleri oluşturakım
        for (int i = 0; i < SYMBOL_LENGTH; i++) 
        {
            symbol.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return symbol.toString();
    }

    /**
     * Retrieves a random existing stock symbol from the set of symbols.
     *
     * @return A random existing stock symbol.
     */
    private static String getRandomExistingSymbol() // for utility; search, remove and update commands
    {
        if (symbols.isEmpty()) 
        {
            return generateUniqueSymbol();
        }
        int randomIndex = RANDOM.nextInt(symbols.size());

        return symbols.toArray(new String[0])[randomIndex];
    }

    /***************** generating random properties of stock *****************/

    /**
     * Generates a random stock price.
     *
     * @return A random stock price.
     */
    private static double generatePrice() 
    {
        return 10 + (1000 - 10) * RANDOM.nextDouble();
    }

    /**
     * Generates a random stock volume.
     *
     * @return A random stock volume.
     */
    private static long generateVolume() 
    {
        return 1000 + RANDOM.nextInt(1000000 - 1000);
    }

    /**
     * Generates a random stock market capitalization.
     *
     * @return A random stock market capitalization.
     */
    private static long generateMarketCap() 
    {
        return 1000000 + (long) (RANDOM.nextDouble() * (1000000000 - 1000000));
    }
    /*************************************************************************/
}
