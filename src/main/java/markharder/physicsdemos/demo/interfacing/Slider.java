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

    public Slider(double x, double y, double height double min, double max, double pos) {
        active = false;
        this.x = x;
        this.y = y;
        this.height = height;
        this.min = min;
        this.max = max;
        this.pos = pos;
    }

    public double getValue() {
        return pos * (max - min) + min
    }

    public double click() {
        active = true;
    }

    public void release(int y) {
        active = false;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawLine(x, y, x, y + height);
        g.fillRect(x - 5, (y + height - height * pos) - 5, 10, 10);
    }

    public void tick() {
        if (Mouse.location.y < y) {
            pos = y;
        } else if (Mouse.location.y > y + height) {
            pos = y + height;
        } else {
            y = Mouse.location.y;
        }
    }
}
