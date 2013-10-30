package markharder.physicsdemos.demo.demos;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import markharder.physicsdemos.demo.physics.Particle3D;

/**
 * Fireworks demo
 *
 * Click to explode a firework
 *
 * @author Mark Harder
 * @version 1.0
 */
public class Fireworks implements Demo {
    // time between each particle spawn in 1/60th of seconds
    private final int PARTICLE_DELAY = 60;

    // if the demo is running or not
    private boolean running;
    // dimensions of the demo
    private int width, height;
    private int ticks;
    private ArrayList<Particle3D> particles;

    public Fireworks(int width, int height) {
        running = false;
        ticks = 0;
        particles = new ArrayList<Particle3D>();

        this.width = width;
        this.height = height;
    }

    public void start() {
        running = true;
    }

    public void restart() {
        running = false;
        ticks = 0;
        particles.clear();
    }

    public void quit() {
    }

    public void pause() {
        running = false;
    }

    public void tick() {
        if (running) {
            ticks++;

            if (ticks % PARTICLE_DELAY == 0) {
                particles.add(new Particle3D(width / 2, height, 10, 0, 0, 0));
                ticks -= PARTICLE_DELAY;
            }

            for (Particle3D p : particles) {
                p.tick();
            }
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).draw(g, width, height);
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void click(int x, int y) {
    }

    public void keypress(char key) {
    }
}
