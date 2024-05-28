import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.List;

/**
 * GUIVisualization is a JFrame that displays a graph of performance times for add, search, and remove operations.
 * It visualizes these times as lines on a graph.
 */
public class GUIVisualization extends JFrame 
{
    private List<Long> addTimes;
    private List<Long> searchTimes;
    private List<Long> removeTimes;
    private String plotType;
    int startIndex = 2;

    /**
     * Constructs a new GUIVisualization window.
     *
     * @param plotType         the type of plot to display
     * @param stockDataManager the stock data manager object
     * @param addTimes         a list of times for add operations
     * @param searchTimes      a list of times for search operations
     * @param removeTimes      a list of times for remove operations
     */
    public GUIVisualization(String plotType, StockDataManager stockDataManager, List<Long> addTimes, List<Long> searchTimes, List<Long> removeTimes) 
    {
        this.plotType = plotType;
        this.addTimes = addTimes;
        this.searchTimes = searchTimes;
        this.removeTimes = removeTimes;

        setTitle("Performance Graph Visualization");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /**
     * Paints the component, drawing the graph.
     *
     * @param g the Graphics object to protect
     */
    @Override
    public void paint(Graphics g) 
    {
        super.paint(g);
        drawGraph(g);
    }

    /**
     * Draws the graph on the component.
     *
     * @param g the Graphics object to protect
     */
    private void drawGraph(Graphics g) 
    {
        int width = getWidth();
        int height = getHeight();
        int padding = 50;
        int labelPadding = 20;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, width - 2 * padding - labelPadding, height - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);

        int numberYDivisions = 10;

        for (int i = 0; i < numberYDivisions + 1; i++) 
        {
            int x0 = padding + labelPadding;
            int x1 = width - padding;
            int y0 = height - ((i * (height - padding * 2 - labelPadding)) / numberYDivisions + padding);
            int y1 = y0;

            g2.setColor(Color.LIGHT_GRAY);
            g2.drawLine(padding + labelPadding + 1 + labelPadding, y0, x1, y1);
            g2.setColor(Color.BLACK);
            String yLabel = ((int) Math.pow(10, i)) + " ns";
            FontMetrics metrics = g2.getFontMetrics();
            int labelWidth = metrics.stringWidth(yLabel);
            g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
        }

        for (int i = startIndex; i < Math.max(addTimes.size(), Math.max(searchTimes.size(), removeTimes.size())); i++) 
        {
            if (i % ((int) ((Math.max(addTimes.size(), Math.max(searchTimes.size(), removeTimes.size())) / 20.0)) + 1) == 0) 
            {
                int x0 = (i - startIndex) * (width - padding * 2 - labelPadding) / (Math.max(addTimes.size(), Math.max(searchTimes.size(), removeTimes.size())) - startIndex - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = height - padding - labelPadding;
                int y1 = y0 - 4;
                g2.setColor(Color.LIGHT_GRAY);
                g2.drawLine(x0, height - padding - labelPadding - 1 - labelPadding, x1, padding);
                g2.setColor(Color.BLACK);
                String xLabel = (i + 1) + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(xLabel);
                g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
            }
        }
        g2.drawLine(padding + labelPadding, height - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, height - padding - labelPadding, width - padding, height - padding - labelPadding);

        drawLines(g2, addTimes, Color.BLUE);
        drawLines(g2, searchTimes, Color.RED);
        drawLines(g2, removeTimes, Color.GREEN);
    }

    /**
     * Draws lines connecting the performance times on the graph.
     *
     * @param g2    the Graphics2D object to protect
     * @param times the list of times to plot
     * @param color the color of the lines
     */
    private void drawLines(Graphics2D g2, List<Long> times, Color color) 
    {
        int width = getWidth();
        int height = getHeight();
        int padding = 50;
        int labelPadding = 20;

        g2.setColor(color);
        g2.setStroke(new BasicStroke(2f));

        for (int i = startIndex; i < times.size() - 1; i++) 
        {
            int x1 = (i - startIndex) * (width - padding * 2 - labelPadding) / (times.size() - startIndex - 1) + padding + labelPadding;
            int y1 = height - padding - labelPadding - (int) ((Math.log10(times.get(i)) * 1.0) / Math.log10(getMaxYValue()) * (height - padding * 2 - labelPadding));
            int x2 = (i + 1 - startIndex) * (width - padding * 2 - labelPadding) / (times.size() - startIndex - 1) + padding + labelPadding;
            int y2 = height - padding - labelPadding - (int) ((Math.log10(times.get(i + 1)) * 1.0) / Math.log10(getMaxYValue()) * (height - padding * 2 - labelPadding));
            g2.drawLine(x1, y1, x2, y2);
        }
    }

    /**
     * Returns the maximum Y value from the times lists.
     *
     * @return the maximum Y value
     */
    private long getMaxYValue() 
    {
        long max = Long.MIN_VALUE;

        for (Long y : addTimes) 
        {
            max = Math.max(max, y);
        }

        for (Long y : searchTimes) 
        {
            max = Math.max(max, y);
        }

        for (Long y : removeTimes) 
        {
            max = Math.max(max, y);
        }
        return max;
    }
}
