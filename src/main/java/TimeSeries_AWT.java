import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RefineryUtilities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TimeSeries_AWT extends JFrame {

    public TimeSeries_AWT( final String title ) {
        super( title );
        final XYDataset dataset = createDataset( );
        final JFreeChart chart = createChart( dataset );
        final ChartPanel chartPanel = new ChartPanel( chart );
        chartPanel.getBounds().width=1000;
        chartPanel.getBounds().height=500;
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 370 ) );
        chartPanel.setMouseZoomable( true , false );
        setContentPane( chartPanel );
        saveChart(chart);

    }

    private XYDataset createDataset( ) {
        final TimeSeries series = new TimeSeries( "Random Data" );
        Second current = new Second( );
        double value = 100.0;

        for (int i = 0; i < 4000; i++) {

            try {
                value = value + Math.random( ) - 0.5;
                series.add(current, new Double( value ) );
                current = ( Second ) current.next( );
            } catch ( SeriesException e ) {
                System.err.println("Error adding to series");
            }
        }

        return new TimeSeriesCollection(series);
    }

    private JFreeChart createChart( final XYDataset dataset ) {
        return ChartFactory.createTimeSeriesChart(
                "Computing Test",
                "Seconds",
                "Value",
                dataset,
                false,
                false,
                false);
    }

    public static void main( final String[ ] args ) {
        final String title = "Time Series Management";
        final TimeSeries_AWT demo = new TimeSeries_AWT( title );
        demo.pack( );
        RefineryUtilities.positionFrameRandomly( demo );
        demo.setVisible( true );
    }

    public void saveImage(ChartPanel chartPanel) {
        File outputfile = new File("/Users/omerorhan/Documents/projects/JFreeChart/src/main/resources/saved.png");
        Rectangle rec = chartPanel.getBounds();
        BufferedImage img = new BufferedImage(560, 370, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        chartPanel.paint(g);
        try {
            ImageIO.write(img, "png", outputfile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void  saveChart(JFreeChart chart){
        try {
            ChartUtilities.saveChartAsPNG(new File("/Users/omerorhan/Documents/projects/JFreeChart/src/main/resources/soft3d.png"), chart, 400, 300);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}   