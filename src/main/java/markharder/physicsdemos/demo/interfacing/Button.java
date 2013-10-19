package markharder.physicsdemos.demo.interfacing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * A simple java button
 *
 * @author Mark Harder
 * @version 1.0
 */
public class Button {
    private String name;
    private int index;

    public Button(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public void tick() {
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(50, 50 + 60 * index, 300, 50);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        g.drawString(name, 60, 90 + 60 * index);
    }
}

