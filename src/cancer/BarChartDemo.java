package cancer;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.util.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a bar chart.
 *
 */
public class BarChartDemo extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public BarChartDemo(final String title,ArrayList ar) {

        super(title);
	final CategoryDataset dataset = createDataset(ar);
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * Returns a sample dataset.
     * 
     * @return The dataset.
     */
    private CategoryDataset createDataset(ArrayList arr) {
        
        // row keys...
        final String series1 = "Cancer";
        final String series2 = "Noncancer";
        //final String series3 = "Third";

        // column keys...
        final String category1 = "2";
        final String category2 = "4";
        final String category3 = "6-8";
        final String category4 = "8-10";
        final String category5 = "12";
		String cat="";
		

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for(int kk=0;kk<arr.size();kk++)
		{
		
		String str1=(String)arr.get(kk);
		
		String res[]=str1.split("#");
		
		if(kk==0)
		{
			cat="1-2";
		}
		else if(kk==1)
		{
			cat="3-4";
		}
		else if(kk==2)
		{
			cat="5-6";
		}
		else if(kk==3)
		{
			cat="7-8";
		}
			dataset.addValue(Float.parseFloat(res[0]), series1, cat);
			dataset.addValue(Float.parseFloat(res[1]), series2, cat);
		
			
		
			/*dataset.addValue(3.0, series2, category1);
			dataset.addValue(2.5, series2, category2);
			dataset.addValue(0.0, series2, category3);*/
			
			//dataset.addValue(8.0, series2, category4);
			//dataset.addValue(4.0, series2, category5);

			/*dataset.addValue(4.0, series3, category1);
			dataset.addValue(3.0, series3, category2);
			dataset.addValue(2.0, series3, category3);
			dataset.addValue(3.0, series3, category4);
			dataset.addValue(6.0, series3, category5);*/
		}
		
        return dataset;
        
    }
    
    /**
     * Creates a sample chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return The chart.
     */
    private JFreeChart createChart(final CategoryDataset dataset) {
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createBarChart(
            "Degree Analysis",         // chart title
            "Category",               // domain axis label
            "Value",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        
        // set up gradient paints for series...
        final GradientPaint gp0 = new GradientPaint(
            0.0f, 0.0f, Color.blue, 
            0.0f, 0.0f, Color.lightGray
        );
        final GradientPaint gp1 = new GradientPaint(
            0.0f, 0.0f, Color.green, 
            0.0f, 0.0f, Color.lightGray
        );
        final GradientPaint gp2 = new GradientPaint(
            0.0f, 0.0f, Color.red, 
            0.0f, 0.0f, Color.lightGray
        );
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);

        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );
        // OPTIONAL CUSTOMISATION COMPLETED.
        
        return chart;
        
    }
    
    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    * 
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {

		/*ArrayList ar=new ArrayList();
		
		ar.add("1#2");
		ar.add("3#2");
		ar.add("4#5");
		ar.add("3#3");
	
        final BarChartDemo demo = new BarChartDemo("Bar Chart Demo",ar);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);*/

    }

}
