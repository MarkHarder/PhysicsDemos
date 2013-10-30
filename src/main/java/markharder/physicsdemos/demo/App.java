package markharder.physicsdemos.demo;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Constructor;

import markharder.physicsdemos.demo.demos.Demo;
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
        menuOptions.add("Launcher");
        menuOptions.add("Rain");
        menuOptions.add("Meteor");
        menuOptions.add("Rocket");
        menuOptions.add("Quit");

        mainMenu = new Menu(width, height, menuOptions);

        currentDemo = null;
    }

    public void quit() {
        WindowEvent wev = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
		frame.setVisible(false);
		frame.dispose();
		System.exit(0);
    }

    public void runDemo(String demoName) {
        Class demoClass = null;

        try {
            demoClass = Class.forName("markharder.physicsdemos.demo.demos." + demoName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (demoClass == null) {
            System.out.println("Demo not found.");
            return;
        }

        Class[] parameters = new Class[] {int.class, int.class};

        Constructor<Demo> demoConstructor = null;
        
        try {
            demoConstructor = demoClass.getConstructor(parameters);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        if (demoConstructor == null) {
            System.out.println("Constructor does not exist.");
            return;
        }

        try {
            currentDemo = demoConstructor.newInstance(new Object[] {width, height});
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not instantiate an object.");
            return;
        }

        currentDemo.start();
    }

    public void click() {
        if (inMenu()) {
            mainMenu.click();
        } else {
            currentDemo.click();
        }
    }

    public void keypress(char key) {
        if (key == KeyEvent.VK_ESCAPE) {
            currentDemo.quit();
            currentDemo = null;
        }
    }

    public void tick() {
        if (inMenu()) {
            mainMenu.tick();
        } else {
            currentDemo.tick();
        }
    }

    public void draw(Graphics g) {
        if (inMenu()) {
            mainMenu.draw(g);
        } else {
            currentDemo.draw(g);
        }
    }

    public static void main( String[] args ) {
        application = new App(500, 500);
    }

    public boolean inMenu() {
        return currentDemo == null;
    }
}
