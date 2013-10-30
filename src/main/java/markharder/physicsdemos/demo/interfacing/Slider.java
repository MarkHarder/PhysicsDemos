package markharder.physicsdemos.demo.interfacing;

import java.awt.Color;
import java.awt.Graphics;

import markharder.physicsdemos.demo.input.Mouse;

/**
 * A simple slider to get input
 *
 * @author Mark Harder
 * @version 1.0
 */
public class Slider {
    private boolean active;
    private double x, y, height, min, max, pos;

    public Slider(double x, double y, double height, double min, double max, double pos) {
        active = false;
        this.x = x;
        this.y = y;
        this.height = height;
        this.min = min;
        this.max = max;
        this.pos = pos;
    }

    public double getValue() {
        return pos * (max - min) + min;
    }

    public boolean contains(int x, int y) {
        return x - this.x <= 5 && x - this.x >= -5 && y - this.y - height <= 5 && y - this.y >= -5;
    }

    public void click() {
        active = true;
    }

    public void release() {
        active = false;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawLine((int) x, (int) y, (int) x, (int) (y + height));
        g.fillRect((int) (x - 5), (int) (y + height - height * pos) - 5, 10, 10);
    }

    public void tick() {
        if (Mouse.location.y < y) {
            pos = 1.0;
        } else if (Mouse.location.y > y + height) {
            pos = 0.0;
        } else {
            pos = 1 - (Mouse.location.y - y) / height;
        }
    }

    public boolean isActive() {
        return active;
    }
}
