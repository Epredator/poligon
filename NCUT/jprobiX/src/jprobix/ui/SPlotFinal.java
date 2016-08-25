/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package jprobix.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Shape;
import java.util.*;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.ShapeUtilities;




/**
*
* @author epredator
*/
public class SPlotFinal extends JPanel {
    
    
    public SPlotFinal(){
        super();
        JPanel jpanel = creteDemoPanel();
        jpanel.setPreferredSize(new Dimension(640, 480));
        add(jpanel);
    }
    
    

    public static JPanel creteDemoPanel() {
        JFreeChart jfreechart = ChartFactory.createScatterPlot
            (
                    "Scatter plot demo",
                    "X",
                    "Y",
                    samplexydataset2(),
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    false
            );
        
        Shape cross = ShapeUtilities.createDiagonalCross(3,2);
        XYPlot xyPlot = (XYPlot) jfreechart.getPlot();
        xyPlot.setDomainCrosshairVisible(true);
        xyPlot.setRangeCrosshairVisible(true);
        XYItemRenderer renderer = xyPlot.getRenderer();
       renderer.setSeriesShape(5, cross);
       renderer.setSeriesPaint(0, Color.YELLOW);
        
        XYDotRenderer xydotrenderer = new XYDotRenderer();
        xyPlot.setRenderer(xydotrenderer);
        xydotrenderer.setSeriesShape(0, cross);
        
        xyPlot.setDomainCrosshairVisible(true);
        xyPlot.setRangeCrosshairVisible(true);
        
        return new ChartPanel(jfreechart);
    }
    
    private static XYDataset samplexydataset2(){
        int cols = 20;
        int rows = 20;
        double[][]values = new double[cols][rows];
        
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        XYSeries series = new XYSeries("Random");
        
        Random rand = new Random();
        for(int i = 0 ; i < values.length ; i++){
            for(int j = 0 ; j<values.length; j++){
                double x = Math.round(rand.nextDouble() * 500 );
                double y = Math.round(rand.nextDouble() * 500);
                
                series.add(x,y);
            }
        }
        xySeriesCollection.addSeries(series);
        return xySeriesCollection;
        
    }
    
// public static void main(String args[]){
// SPlotFinal scatterplotdemo4 = new SPlotFinal("Scatter Plot Demo 4");
// scatterplotdemo4.pack();
// RefineryUtilities.centerFrameOnScreen(scatterplotdemo4);
// scatterplotdemo4.setVisible(true);
// }
    
}