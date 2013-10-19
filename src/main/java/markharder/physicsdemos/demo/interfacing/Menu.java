package markharder.physicsdemos.demo.interfacing;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple menu interface with buttons to perform actions
 *
 * @author Mark Harder
 * @version 1.0
 */
public class Menu {
    private List<Button> buttons;
    private int width, height;

    public Menu(int width, int height, List<String> menuOptions) {
        this.width = width;
        this.height = height;

        buttons = new ArrayList<Button>();

        int buttonIndex = 0;
        for (String s : menuOptions) {
            buttons.add(new Button(s, buttonIndex++));
        }
    }

    public void tick() {
        for (Button b : buttons) {
            b.tick();
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        for (Button b : buttons) {
            b.draw(g);
        }
    }
}
