import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * Main class to run the Stock Data Manager application.
 * It reads commands from an input file and performs add, search, and remove operations,
 * then displays the performance graph using GUIVisualization.
 */
public class Main 
{
    /**
     * The main method to start the application.
     * 
     * @param args the command line arguments, expecting the input file path as the only argument
     */
    public static void main(String[] args) 
    {
        if (args.length != 1) 
        {
            System.out.println("Usage: java Main <input_file>");
            return;
        }

        String inputFile = args[0];
        StockDataManager manager = new StockDataManager();

        List<Long> addTimes = new ArrayList<>();
        List<Long> searchTimes = new ArrayList<>();
        List<Long> removeTimes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) 
        {
            String line;

            while ((line = br.readLine()) != null) 
            {
                processCommand(line, manager, addTimes, searchTimes, removeTimes);
            }
        }

        catch (IOException e) 
        {
            e.printStackTrace();
        }

        printAverageTimes(addTimes, searchTimes, removeTimes);
        SwingUtilities.invokeLater(() -> createAndShowGUI(manager, addTimes, searchTimes, removeTimes));
    }

    /**
     * Processes a single command from the input file.
     * 
     * @param line the command line
     * @param manager the stock data manager
     * @param addTimes the list to record add operation times
     * @param searchTimes the list to record search operation times
     * @param removeTimes the list to record remove operation times
     */
    private static void processCommand(String line, StockDataManager manager, List<Long> addTimes, List<Long> searchTimes, List<Long> removeTimes) 
    {
        String[] parts = line.split(" ");
        String command = parts[0];
        String symbol = parts[1];
        long startTime, endTime;

        switch (command) 
        {
            case "ADD":

                double price = Double.parseDouble(parts[2]);
                long volume = Long.parseLong(parts[3]);
                long marketCap = Long.parseLong(parts[4]);
                startTime = System.nanoTime();
                manager.addOrUpdateStock(symbol, price, volume, marketCap);
                endTime = System.nanoTime();
                addTimes.add(endTime - startTime);
                break;

            case "SEARCH":

                startTime = System.nanoTime();
                Stock result = manager.searchStock(symbol);
                endTime = System.nanoTime();
                searchTimes.add(endTime - startTime);

                if (result != null) 
                {
                    System.out.println(result);
                } 

                else 
                {
                    System.out.println("Stock not found: " + symbol);
                }
                break;

            case "REMOVE":

                startTime = System.nanoTime();
                manager.removeStock(symbol);
                endTime = System.nanoTime();
                removeTimes.add(endTime - startTime);
                break;
        }
    }

    /**
     * Prints the average times for add, search, and remove operations.
     * 
     * @param addTimes the list of add operation times
     * @param searchTimes the list of search operation times
     * @param removeTimes the list of remove operation times
     */
    private static void printAverageTimes(List<Long> addTimes, List<Long> searchTimes, List<Long> removeTimes) 
    {
        System.out.println("Average ADD time: " + addTimes.stream().mapToLong(Long::longValue).average().orElse(0.0) + " ns");
        System.out.println("Average SEARCH time: " + searchTimes.stream().mapToLong(Long::longValue).average().orElse(0.0) + " ns");
        System.out.println("Average REMOVE time: " + removeTimes.stream().mapToLong(Long::longValue).average().orElse(0.0) + " ns");
    }

    /**
     * Creates and shows the GUI for the performance graph visualization.
     * 
     * @param stockDataManager the stock data manager
     * @param addTimes the list of add operation times
     * @param searchTimes the list of search operation times
     * @param removeTimes the list of remove operation times
     */
    private static void createAndShowGUI(StockDataManager stockDataManager, List<Long> addTimes, List<Long> searchTimes, List<Long> removeTimes) 
    {
        String plotType = "line";
        GUIVisualization visualization = new GUIVisualization(plotType, stockDataManager, addTimes, searchTimes, removeTimes);
        visualization.setVisible(true);
    }
}
