package markharder.physicsdemos.demo.physics;

import java.awt.Color;
import java.awt.Graphics;

/**
 * %Description%
 *
 * @author Mark Harder
 * @version 1.0
 */
public class Planet {
    private double x, y, radius;

    public Planet(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getMass() {
        return radius * 4;
    }

    public void draw(Graphics g, int width, int height) {
        g.setColor(new Color(0xE74C3C));
        g.fillOval((int) x, (int) y, (int) radius * 2, (int) radius * 2);
    }

    public Vector2D gravityForce(Satellite s) {
        double xdiff = s.getX() + 2.5 - x - radius;
        double ydiff = s.getY() + 2.5 - y - radius;
        double distance = Math.sqrt(xdiff * xdiff + ydiff * ydiff);

        if (distance < radius / 2) {
            return new Vector2D(0.0, 0.0);
        }

        double magnitude = getMass() / (distance * distance);

        double total = Math.abs(xdiff) + Math.abs(ydiff);
        
        return new Vector2D(-magnitude * xdiff / total, -magnitude * ydiff / total);
    }
}

