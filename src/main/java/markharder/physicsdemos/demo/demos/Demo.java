package markharder.physicsdemos.demo.demos;

import java.awt.Graphics;

/*
 * Mark Harder
 * 2013.10.19
 * 
 * Demo
 * ----------
 * An interface for the demos
 * @author Mark Harder
 * @version 1.0
 */
public interface Demo {
    public void restart();

    public void start();

    public void pause();

    public void quit();

    public void tick();

    public void draw(Graphics g);

    public void click(int x, int y);

    public void release(int x, int y);

    public void keypress(char key);
}
