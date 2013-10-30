package markharder.physicsdemos.demo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import markharder.physicsdemos.demo.demos.Demo;
import markharder.physicsdemos.demo.demos.Fireworks;
import markharder.physicsdemos.demo.graphics.GraphicsWindow;
import markharder.physicsdemos.demo.input.Mouse;

/**
 *   Start the application. Brings up a menu with options for
 * demos.
 *
 */
public class App extends GraphicsWindow {
    public static App application;

    private Demo currentDemo;

    public App(int width, int height) {
        super(width, height);
        currentDemo = new Fireworks(width, height);

        currentDemo.start();
    }

    public void click() {
        if (currentDemo != null) {
            currentDemo.click(Mouse.location.x, Mouse.location.y);
        }
    }

    public void keypress(char key) {
        if (currentDemo != null) {
            if (key == KeyEvent.VK_ESCAPE) {
                currentDemo.quit();
                currentDemo = null;
            } else {
                currentDemo.keypress(key);
            }
        }
    }

    public void tick() {
        if (currentDemo != null) {
            currentDemo.tick();
        }
    }

    public void draw(Graphics g) {
        if (currentDemo == null) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, width, height);
        } else {
            currentDemo.draw(g);
        }
    }

    public static void main( String[] args ) {
        application = new App(500, 500);
    }
}
