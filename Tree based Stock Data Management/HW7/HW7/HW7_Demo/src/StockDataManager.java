import java.util.HashMap;
import java.util.Map;

/**
 * The StockDataManager class manages stock data using an AVL tree for efficient
 * storage and retrieval of stock information. It provides methods to add, update,
 * remove, and search for stocks.
 */
public class StockDataManager 
{
    private AVLTree avlTree;

    /**
     * Constructs a StockDataManager object and initializes the AVL tree.
     */
    public StockDataManager() 
    {
        avlTree = new AVLTree();
    }

    /**
     * Adds a new stock or updates an existing stock in the AVL tree.
     *
     * @param symbol The stock symbol.
     * @param price The stock price.
     * @param volume The stock volume.
     * @param marketCap The stock market capitalization.
     */
    public void addOrUpdateStock(String symbol, double price, long volume, long marketCap) 
    {
        Stock existingStock = avlTree.search(symbol);

        if (existingStock != null) 
        {
            existingStock.setPrice(price);
            existingStock.setVolume(volume);
            existingStock.setMarketCap(marketCap);
        } 
        else 
        {
            Stock newStock = new Stock(symbol, price, volume, marketCap);
            avlTree.insert(newStock);
        }
    }

    /**
     * Removes a stock from the AVL tree.
     *
     * @param symbol The stock symbol to be removed.
     */
    public void removeStock(String symbol) 
    {
        avlTree.delete(symbol);
    }

    /**
     * Searches for a stock in the AVL tree.
     *
     * @param symbol The stock symbol to search for.
     * @return The Stock object if found, otherwise null.
     */
    public Stock searchStock(String symbol) 
    {
        return avlTree.search(symbol);
    }

    /**
     * Updates the details of an existing stock.
     *
     * @param symbol The stock symbol to be updated.
     * @param newPrice The new price of the stock.
     * @param newVolume The new volume of the stock.
     * @param newMarketCap The new market capitalization of the stock.
     */
    public void updateStock(String symbol, double newPrice, long newVolume, long newMarketCap) 
    {
        Stock stock = avlTree.search(symbol);

        if (stock != null) 
        {
            stock.setPrice(newPrice);
            stock.setVolume(newVolume);
            stock.setMarketCap(newMarketCap);
        }
    }

    /**
     * Retrieves all stocks in the AVL tree as a map.
     *
     * @return A map of stock symbols to Stock objects.
     */
    public Map<String, Stock> getAllStocks() 
    {
        Map<String, Stock> stockMap = new HashMap<>();
        for (Stock stock : avlTree.inOrderTraversal()) 
        {
            stockMap.put(stock.getSymbol(), stock);
        }
        return stockMap;
    }

    /**
     * Main method for testing the StockDataManager class.
     *
     * @param args Command line arguments.
     */
    /*
    public static void main(String[] args) 
    {
        StockDataManager manager = new StockDataManager();
        manager.addOrUpdateStock("AAPL", 150.0, 1000000, 2500000000L);
        manager.addOrUpdateStock("GOOGL", 2800.0, 500000, 1500000000L);
        System.out.println(manager.searchStock("AAPL"));
        manager.removeStock("AAPL");
        System.out.println(manager.searchStock("AAPL"));
    }
    */
}
