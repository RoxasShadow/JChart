/*
*    Giovanni Capuano <webmaster@giovannicapuano.net>
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

import org.jfree.chart.*;
import org.jfree.data.xy.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import javax.swing.*;

public class Hyperbole extends JFrame {
	public Hyperbole(String applicationTitle, String chartTitle) {
		super(applicationTitle);
		setSize(500, 270);
		setVisible(true);
		setFocusable(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		XYDataset dataset = createDataset();
		JFreeChart chart = createChart(dataset, chartTitle);
		ChartPanel chartPanel = new ChartPanel(chart);
		setContentPane(chartPanel);
		pack();
	}
	
	private XYDataset createDataset() {
		XYSeries series = new XYSeries("Points");
		for(int i=0; i<100; ++i) {
			// x^2/12 - y^2/3 = -1
			double tot = (Math.pow(i, 2)/12) - (Math.pow(i, 2)/3) +1;
			series.add(i, tot);
		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		
		return dataset;
	}
    
       private JFreeChart createChart(XYDataset dataset, String title) {
		JFreeChart chart = ChartFactory.createXYLineChart(title, "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false);
		XYPlot plot = chart.getXYPlot();
		XYSplineRenderer renderer = new XYSplineRenderer(); // Curve
		plot.setRenderer(renderer);
		return chart;
	}
}
