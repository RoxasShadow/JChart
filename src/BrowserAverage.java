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
import org.jfree.data.general.*;
import org.jfree.chart.plot.*;
import org.jfree.util.Rotation;
import javax.swing.*;
import java.awt.event.*;

public class BrowserAverage extends JFrame {
	public BrowserAverage(String applicationTitle, String chartTitle) {
		super(applicationTitle);
		setSize(500, 270);
		setVisible(true);
		setFocusable(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		PieDataset dataset = createDataset();
		JFreeChart chart = createChart(dataset, chartTitle);
		ChartPanel chartPanel = new ChartPanel(chart);
		setContentPane(chartPanel);
		pack();
	}
	
	private PieDataset createDataset() {
		DefaultPieDataset result = new DefaultPieDataset();
		result.setValue("Internet Explorer", 21.7);
		result.setValue("Mozilla Firefox", 38.7);
		result.setValue("Google Chrome", 32.3);
		result.setValue("Apple Safari", 4.2);
		result.setValue("Opera", 2.4);
		return result;
	}
    
       private JFreeChart createChart(PieDataset dataset, String title) {
		JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, true, true, false);
		PiePlot3D plot = (PiePlot3D)chart.getPlot();
		plot.setStartAngle(0);
		plot.setDirection(Rotation.ANTICLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		new Rotator(plot, 2).start();
		return chart;
	}
}

class Rotator extends Timer implements ActionListener {
	private PiePlot3D plot;
	private int angle, speed;

	public Rotator(PiePlot3D plot, int speed) {
		super(100, null);
		this.plot = plot;
		angle = (int)plot.getStartAngle();
		this.speed = speed;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		plot.setStartAngle(angle);
		angle += speed;
		if(angle == 360)
			angle = 0;
	}
}
