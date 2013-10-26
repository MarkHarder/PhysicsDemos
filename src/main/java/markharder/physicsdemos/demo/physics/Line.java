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

    public boolean intersects(Line other) {
        double r_x = x2 - x1;
        double r_y = y2 - y1;
        double p_x = x1;
        double p_y = y1;
        double s_x = other.x2 - other.x1;
        double s_y = other.y2 - other.y1;
        double q_x = other.x1;
        double q_y = other.y1;

        double rCrossS = cross(r_x, r_y, s_x, s_y);

        if (rCrossS < 0.000001 && rCrossS > 0.000001) {
            return false;
        }

        double t = cross(q_x - p_x, q_y - p_y, s_x, s_y) / rCrossS;
        double u = cross(q_x - p_x, q_y - p_y, r_x, r_y) / rCrossS;

		return t >= 0.0f && t <= 1.0f && u >= 0.0f && u <= 1.0f;
    }

    private double cross(double a1, double b1, double a2, double b2) {
        return a1 * b2 - b1 * a2;
    }

    public double getAngle() {
        return Math.atan((y2 - y1) / (x2 - x1));
    }
}
