package markharder.physicsdemos.demo.physics;

import java.awt.Color;
import java.awt.Graphics;

/**
 * A particle in three dimensions
 *
 * @author Mark Harder
 * @version 1.0
 */
public class Particle2D {
    protected double x, y, vx, vy, ax, ay;

    public Particle2D(double x, double y, double vx, double vy) {
        // set position
        this.x = x;
        this.y = y;

        // set velocities
        this.vx = vx;
        this.vy = vy;

        // set acceleration
        this.ax = 0.0;
        this.ay = -9.8 / 60;
    }

    public void tick() {
        x += vx;
        y += vy;

        vx += ax;
        vy += ay;
    }

    public void draw(Graphics g, int width, int height) {
        g.setColor(Color.BLUE);
        g.fillOval((int) x, (int) (height - y), 5, 5);
    }

    public void setAX(double ax) {
        this.ax = ax;
    }

    public void setAY(double ay) {
        this.ay = ay;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
