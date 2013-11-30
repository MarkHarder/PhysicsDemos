package markharder.physicsdemos.demo.physics;

import java.awt.Color;
import java.awt.Graphics;

/**
 * A particle in three dimensions
 *
 * @author Mark Harder
 * @version 1.0
 */
public class Satellite {
    protected double x, y, vx, vy, ax, ay;

    public Satellite(double x, double y, double vx, double vy) {
        // set position
        this.x = x;
        this.y = y;

        // set velocities
        this.vx = vx;
        this.vy = vy;

        // set acceleration
        this.ax = 0.0;
        this.ay = 0.0;
    }

    public void tick() {
        x += vx;
        y += vy;

        vx += ax;
        vy += ay;
    }

    public void draw(Graphics g, int width, int height) {
        g.setColor(new Color(0x3498DB));
        g.fillOval((int) x, (int) y, 5, 5);
    }

    public void setAX(double ax) {
        this.ax = ax;
    }

    public void setAY(double ay) {
        this.ay = ay;
    }

    public double getAX() {
        return ax;
    }

    public double getAY() {
        return ay;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
