package markharder.physicsdemos.demo.demos;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import markharder.physicsdemos.demo.input.Mouse;
import markharder.physicsdemos.demo.physics.Particle2D;

public class Rain implements Demo {
    private int width, height;
    private boolean running;

	private List<Particle2D> particles;
	private long ticks;
	
	public Rain(int width, int height) {
        this.width = width;
        this.height = height;

		particles = new ArrayList<Particle2D>();

        setup();
	}

    public void setup() {
        running = true;
		
        particles.clear();
		ticks = 0;
    }
	
	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(0, 0, 0, 30));
		g.fillRect(0,  0,  width,  height);
		
		g.setColor(new Color(20, 20, 20));
		for (int i = 0; i < width; i += 50) {
			for (int j = 0; j < height; j += 50) {
				g.fillRect(i, j, 5, 5);
			}
		}
		
		for (Particle2D p : particles) {
			p.draw(g, width, height);
		}
	}

	@Override
	public void tick() {
        if (running) {
            ticks++;
            
            if (ticks % 10 == 0) {
                for (int i = 0; i < width; i += 20) {
                    particles.add(new Particle2D(i, height, 0.0, 0.0));
                }
            }
            
            List<Particle2D> delete = new ArrayList<Particle2D>();
            
            for (Particle2D p: particles) {
                double xdist = p.getX() - Mouse.location.x;
                double ydist = p.getY() - (height - Mouse.location.y);

                if (xdist * xdist + ydist * ydist <= 1600) {
                    if (xdist < 0) {
                        p.setAX(-0.25);
                    } else {
                        p.setAX(0.25);
                    }
                } else {
                    p.setAX(0.0);
                }

                p.tick();
                if (p.getX() < 0 || p.getX() > width || p.getY() > height) {
                    delete.add(p);
                }
            }
            
            for (Particle2D p : delete) {
                particles.remove(p);
            }
        }
	}
	
	public void click(int x, int y) {
	}

    public void release(int x, int y) {
    }

    public void keypress(char key) {
    }

    public void start() {
        running = true;
    }

    public void pause() {
        running = false;
    }

    public boolean running() {
        return running;
    }

    public void quit() {
    }

    public void restart() {
        setup();
    }
}
