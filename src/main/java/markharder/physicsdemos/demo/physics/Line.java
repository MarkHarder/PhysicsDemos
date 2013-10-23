package markharder.physicsdemos.demo.physics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Line {
	public double x1, y1, x2, y2;
	
	public Line(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public void draw(Graphics g, int width, int height) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g2.drawLine((int) x1, (int) (height - y1), (int) x2, (int) (height - y2)); 
	}
}
