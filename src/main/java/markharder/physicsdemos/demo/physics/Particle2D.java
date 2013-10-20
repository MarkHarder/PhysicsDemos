package markharder.physicsdemos.demo.physics;

import java.awt.Color;
import java.awt.Graphics;

import markharder.physicsdemos.demo.input.Mouse;

/**
 * A particle in three dimensions
 *
 * @author Mark Harder
 * @version 1.0
 */
public class Particle2D {
    private double x, y, vx, vy, ax, ay;

    public Particle2D(double x, double y, double vx, double vy) {
        // set position
        this.x = x;
        this.y = y;

        // set velocities
        this.vx = vx;
        this.vy = vy;

        // set acceleration
        this.ax = 0.0;
        this.ay = -9.8 / 4 / 60;
    }

    public void tick(int width, int height) {
        double xdist = x - Mouse.location.x;
		double ydist = y - (height - Mouse.location.y);
		
		if (xdist * xdist + ydist * ydist <= 1600) {
			if (xdist < 0) {
				ax = 0.25;
			} else {
				ax = -0.25;
			}
			if (ydist < 0) {
				ay = -9.8 / 4/ 60 + 0.25;
			} else {
				ay = -9.8 / 4 / 60 - 0.25;
			}
		} else {
			ax = 0.0;
			ay = -9.8 / 4 / 60;
		}

        x += vx;
        y += vy;

        vy += ay;
    }

    public void draw(Graphics g, int width, int height) {
        g.setColor(Color.BLUE);
        g.fillOval((int) x, (int) (height - y), 5, 5);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
