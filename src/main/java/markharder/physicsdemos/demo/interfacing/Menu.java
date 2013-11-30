package markharder.physicsdemos.demo.interfacing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import markharder.physicsdemos.demo.App;

/**
 * A simple menu interface with buttons to perform actions
 *
 * @author Mark Harder
 * @version 1.0
 */
public class Menu {
    private List<Button> buttons;
    private int width, height;
    private int scrollIndex;

    public Menu(int width, int height, List<String> menuOptions) {
        this.width = width;
        this.height = height;
        scrollIndex = 0;

        buttons = new ArrayList<Button>();

        int buttonIndex = 1;
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

        g.setColor(new Color(0xE67E22));
        g.fillRect(100, 30, 200, 50);
        int[] x = {150, 200, 250};
        int[] y = {70, 50, 70};
        g.setColor(new Color(0xD34500));
        g.fillPolygon(x, y, 3);

        g.setColor(new Color(0xE67E22));
        g.fillRect(100, 30 + 60 * 6, 200, 50);
        int[] x2 = {150, 200, 250};
        int[] y2 = {50 + 60 * 6, 70 + 60 * 6, 50 + 60 * 6};
        g.setColor(new Color(0xD35400));
        g.fillPolygon(x2, y2, 3);
    }

    public void click(int mouseX, int mouseY) {
        for (Button b : buttons) {
            if (b.getIndex() < 6 && b.getIndex() > 0 && b.contains(new Point(mouseX, mouseY))) {
                if (b.getName().equals("Quit")) {
                    App.application.quit();
                } else {
                    App.application.runDemo(b.getName());
                }
            }
        }

        if (mouseX > 100 && mouseX < 300 && mouseY > 30 && mouseY < 80 && scrollIndex < 0) {
            scrollIndex++;
            for (Button b : buttons) {
                b.scrollUp();
            }
        } else if (mouseX > 100 && mouseX < 300 && mouseY > 30 + 60 * 6 && mouseY < 80 + 60 * 6 && scrollIndex > -4) {
            scrollIndex--;
            for (Button b : buttons) {
                b.scrollDown();
            }
        }
    }
}
