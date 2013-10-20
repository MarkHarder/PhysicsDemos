package markharder.physicsdemos.demo.interfacing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import markharder.physicsdemos.demo.input.Mouse;

/**
 * A simple java button
 *
 * @author Mark Harder
 * @version 1.0
 */
public class Button extends Rectangle {
    private String name;
    private int index;
    private boolean hover;

    public Button(String name, int index) {
        super(50, 50 + 60 * index, 300, 50);
        this.name = name;
        this.index = index;
        this.hover = false;
    }

    public void tick() {
        hover = false;

        if (contains(Mouse.location)) {
            hover = true;
        }
    }

    public void draw(Graphics g) {
        if (hover) {
            g.setColor(new Color(0, 0, 255, 200));
        } else {
            g.setColor(Color.BLUE);
        }
        g.fillRect(x, y, width, height);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        g.drawString(name, 60, 90 + 60 * index);
    }

    public String getName() {
        return name;
    }
}

