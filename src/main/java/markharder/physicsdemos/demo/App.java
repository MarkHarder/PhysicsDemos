package markharder.physicsdemos.demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Constructor;

import markharder.physicsdemos.demo.demos.Demo;
import markharder.physicsdemos.demo.graphics.GraphicsWindow;
import markharder.physicsdemos.demo.interfacing.Menu;
import markharder.physicsdemos.demo.input.Mouse;

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

    private Rectangle resume;
    private Rectangle returnToMenu;

    public App(int width, int height) {
        super(width, height);

        List<String> menuOptions = new ArrayList<String>();
        menuOptions.add("Fireworks");
        menuOptions.add("Launcher");
        menuOptions.add("Rain");
        menuOptions.add("Meteor");
        menuOptions.add("Rocket");
        menuOptions.add("Cylinder");
        menuOptions.add("PeriodicWave");
        menuOptions.add("Gravitation");
        menuOptions.add("Quit");

        mainMenu = new Menu(width, height, menuOptions);

        currentDemo = null;

        resume = new Rectangle(125, 150, 250, 50);
        returnToMenu = new Rectangle(125, 220, 250, 50);
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
            mainMenu.click(Mouse.location.x, Mouse.location.y);
        } else if (currentDemo != null) {
            if (currentDemo.running()) {
                currentDemo.click(Mouse.location.x, Mouse.location.y);
            } else {
                if (resume.contains(Mouse.location)) {
                    currentDemo.start();
                } else if (returnToMenu.contains(Mouse.location)) {
                    currentDemo.quit();
                    currentDemo = null;
                }
            }
        }
    }

    public void release() {
        if (currentDemo != null) {
            currentDemo.release(Mouse.location.x, Mouse.location.y);
        }
    }

    public void keypress(char key) {
        if (currentDemo != null) {
            if (key == KeyEvent.VK_ESCAPE) {
                if (currentDemo.running()) {
                    currentDemo.pause();
                } else {
                    currentDemo.start();
                }
            } else {
                currentDemo.keypress(key);
            }
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

            if (currentDemo != null && !currentDemo.running()) {
                g.setColor(new Color(0, 0, 0, 200));
                g.fillRect(0, 0, width, height);

                // Resume game button
                if (resume.contains(Mouse.location)) {
                    g.setColor(new Color(0x2980B9));
                } else {
                    g.setColor(new Color(0x3498DB));
                }
                g.fillRect(resume.x, resume.y, resume.width, resume.height);

                g.setColor(new Color(0xECF0F1));
                g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
                g.drawString("Resume Game", resume.x + 35, resume.y + 35);
                
                // Return to main menu button
                if (returnToMenu.contains(Mouse.location)) {
                    g.setColor(new Color(0x2980B9));
                } else {
                    g.setColor(new Color(0x3498DB));
                }
                g.fillRect(returnToMenu.x, returnToMenu.y, returnToMenu.width, returnToMenu.height);

                g.setColor(new Color(0xECF0F1));
                g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
                g.drawString("Return to Menu", returnToMenu.x + 30, returnToMenu.y + 35);
            }
        }
    }

    public static void main( String[] args ) {
        application = new App(500, 500);
    }

    public boolean inMenu() {
        return currentDemo == null;
    }
}
