package markharder.physicsdemos.demo;

import java.awt.Color;
import java.awt.Graphics;

import markharder.physicsdemos.demo.graphics.GraphicsWindow;

/**
 *   Start the application. Brings up a menu with options for
 * demos.
 *
 */
public class App extends GraphicsWindow {
    public static App application;

    public App(int width, int height) {
        super(width, height);
    }

    public void tick() {
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
    }

    public static void main( String[] args ) {
        application = new App(500, 500);
    }
}
