package markharder.physicsdemos.demo;

import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

import markharder.physicsdemos.demo.demos.Demo;
import markharder.physicsdemos.demo.demos.Fireworks;
import markharder.physicsdemos.demo.graphics.GraphicsWindow;
import markharder.physicsdemos.demo.interfacing.Menu;

/**
 *   Start the application.
 *   Brings up a menu with options for demos.
 *   Clicking on a demo runs it
 *
 */
public class App extends GraphicsWindow {
    public static App application;

    private Menu mainMenu;
    private Demo currentDemo;

    public App(int width, int height) {
        super(width, height);

        List<String> menuOptions = new ArrayList<String>();
        menuOptions.add("Fireworks");
        menuOptions.add("Quit");

        mainMenu = new Menu(width, height, menuOptions);

        currentDemo = null;
    }

    public void click() {
    }

    public void keypress(char key) {
    }

    public void tick() {
        if (currentDemo == null) {
            mainMenu.tick();
        } else {
            currentDemo.tick();
        }
    }

    public void draw(Graphics g) {
        if (currentDemo == null) {
            mainMenu.draw(g);
        } else {
            currentDemo.draw(g);
        }
    }

    public static void main( String[] args ) {
        application = new App(500, 500);
    }
}
