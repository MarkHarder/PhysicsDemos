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
        g.setColor(new Color(0x3498DB));
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

    public Line getVelocityVector() {
        if (vx >= 0) {
            return new Line(x, y, x + vx, y + vy);
        }
        return new Line(x + vx, y + vy, x, y);
    }

    public void reflect(Line wall) {
        Vector2D normal = wall.getNormal();
        Vector2D velocity = new Vector2D(vx, vy);
        vx -= 2.0 * normal.getX() * Vector2D.dot(velocity, normal);
        vy -= 2.0 * normal.getY() * Vector2D.dot(velocity, normal);
    }
}
