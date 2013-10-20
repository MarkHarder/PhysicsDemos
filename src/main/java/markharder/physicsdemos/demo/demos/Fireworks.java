package markharder.physicsdemos.demo.demos;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import markharder.physicsdemos.demo.physics.Particle3D;
import markharder.physicsdemos.demo.input.Mouse;

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
    private List<Particle3D> particles;

	private Color[] colors = {Color.ORANGE, Color.GREEN, Color.BLUE};

    private Random gen;

    public Fireworks(int width, int height) {
        running = false;
        particles = new ArrayList<Particle3D>();

        this.width = width;
        this.height = height;

        gen = new Random();
    }

    public void start() {
        running = true;
    }

    public void restart() {
        running = false;
        particles.clear();
    }

    public void quit() {
    }

    public void pause() {
        running = false;
    }

    public void tick() {
        if (running) {
            List<Integer> delete = new ArrayList<Integer>();

            for (int i = 0; i < particles.size(); i++) {
                Particle3D p = particles.get(i);
                if (p.getY() < 0) {
                    delete.add(i);
                }
                p.tick();
            }

            for (int i = 0; i < delete.size(); i++) {
                particles.remove(delete.get(i) - i);
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

    public void click() {
        explodeFirework(Mouse.location.getX(), height - Mouse.location.getY());
    }

    public void keypress(char key) {
    }

    private void explodeFirework(double x, double y) {
        Color c = colors[gen.nextInt(colors.length)];
        for (int i = 0; i < 24; i++) {
            particles.add(new Particle3D(x, y, 4.0, gen.nextDouble() * 3 - 1.5, -2 + 9.8 / (gen.nextDouble() * 4 + 1.5), gen.nextDouble() * 0.1 - 0.05, c));
        }
    }

    public boolean isRunning() {
        return running;
    }
}
