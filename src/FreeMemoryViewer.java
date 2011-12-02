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
import java.io.*;
import javax.swing.*;

public class FreeMemoryViewer extends JFrame {
	private XYSeries used, total, free;
	
	public FreeMemoryViewer(String applicationTitle, String chartTitle) {
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
		updateChart();
	}
	
	private XYDataset createDataset() {
		used = new XYSeries("Used");
		total = new XYSeries("Total");
		free = new XYSeries("Free");
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(used);
		dataset.addSeries(total);
		dataset.addSeries(free);
		return dataset;
	}
	
	public double exec(String command) {
		StringBuffer buffer = new StringBuffer("");
		try {
			Process p = Runtime.getRuntime().exec(command);
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			while((line = in.readLine()) != null) {
				buffer.append(line);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return Double.parseDouble(buffer.toString());
	}
	
	private double getRam(String arg) {
		return exec("./util.rb "+arg);
	}
	
	private void updateChart() {
		int i = 0;
		while(true) {
			double used = getRam("used");
			double total = getRam("total");
			double free = getRam("free");
			this.used.add(i, used);
			this.total.add(i, total);
			this.free.add(i, free);
			try {
				Thread.sleep(1000);
			}
			catch(Exception e) {}
			++i;
		}
	}
    
       private JFreeChart createChart(XYDataset dataset, String title) {
		JFreeChart chart = ChartFactory.createXYLineChart(title, "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false);
		XYPlot plot = chart.getXYPlot();
		XYSplineRenderer renderer = new XYSplineRenderer(); // Curve
		plot.setRenderer(renderer);
		return chart;
	}
}
