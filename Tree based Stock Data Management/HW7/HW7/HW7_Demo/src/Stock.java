/**
 * The Stock class represents a stock with a symbol, price, volume, and market capitalization.
 */
public class Stock 
{
    private String symbol;
    private double price;
    private long volume;
    private long marketCap;

    /**
     * Constructs a Stock object with the specified symbol, price, volume, and market capitalization.
     *
     * @param symbol The stock symbol.
     * @param price The stock price.
     * @param volume The stock volume.
     * @param marketCap The stock market capitalization.
     */
    public Stock(String symbol, double price, long volume, long marketCap) 
    {
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
        this.marketCap = marketCap;
    }

    /**
     * Gets the stock symbol.
     *
     * @return The stock symbol.
     */
    public String getSymbol() 
    {
        return symbol;
    }

    /**
     * Sets the stock symbol.
     *
     * @param symbol The new stock symbol.
     */
    public void setSymbol(String symbol) 
    {
        this.symbol = symbol;
    }

    /**
     * Gets the stock price.
     *
     * @return The stock price.
     */
    public double getPrice() 
    {
        return price;
    }

    /**
     * Sets the stock price.
     *
     * @param price The new stock price.
     */
    public void setPrice(double price) 
    {
        this.price = price;
    }

    /**
     * Gets the stock volume.
     *
     * @return The stock volume.
     */
    public long getVolume() 
    {
        return volume;
    }

    /**
     * Sets the stock volume.
     *
     * @param volume The new stock volume.
     */
    public void setVolume(long volume) 
    {
        this.volume = volume;
    }

    /**
     * Gets the stock market capitalization.
     *
     * @return The stock market capitalization.
     */
    public long getMarketCap() 
    {
        return marketCap;
    }

    /**
     * Sets the stock market capitalization.
     *
     * @param marketCap The new stock market capitalization.
     */
    public void setMarketCap(long marketCap) 
    {
        this.marketCap = marketCap;
    }

    /**
     * Returns a string representation of the stock.
     *
     * @return A string representation of the stock.
     */
    @Override
    public String toString() 
    {
        return "Stock [symbol=" + symbol + ", price=" + price + ", volume=" + volume + ", marketCap=" + marketCap + "]";
    }
}
