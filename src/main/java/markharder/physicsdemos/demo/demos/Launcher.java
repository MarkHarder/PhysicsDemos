package markharder.physicsdemos.demo.demos;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import markharder.physicsdemos.demo.physics.Particle2D;
import markharder.physicsdemos.demo.input.Mouse;

public class Launcher implements Demo {
    private boolean running;
    private int width, height;
	private boolean trails;
	private List<Particle2D> particles;
	private long ticks;
	
	public Launcher(int width, int height) {
        this.width = width;
        this.height = height;
		
		trails = true;
		particles = new ArrayList<Particle2D>();
		ticks = 0;
        running = false;
	}
	
	@Override
	public void draw(Graphics g) {
		if (trails) {
			g.setColor(new Color(0, 0, 0, 30));
			g.fillRect(0, 0, width, height);
			
			g.setColor(new Color(20, 20, 20));
			for (int i = 0; i < width; i += 50) {
				for (int j = 0; j < height; j += 50) {
					g.fillRect(i, j, 5, 5);
				}
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
            
            if (ticks % 60 == 0) {
                particles.add(new Particle2D(0.0, height - 50.0, 5.0, 0.0));
                particles.add(new Particle2D(0.0, height - 100.0, 6.66, 0.0));
                particles.add(new Particle2D(0.0, height - 150.0, 8.33, 0.0));
                particles.add(new Particle2D(0.0, height - 200.0, 10.0, 0.0));
            }
            
            List<Particle2D> delete = new ArrayList<Particle2D>();
            
            for (Particle2D p: particles) {
                double xdist = p.getX() - Mouse.location.x;
                double ydist = p.getY() - (height - Mouse.location.y);

                if (xdist * xdist + ydist * ydist <= 1600) {
                    if (xdist < 0) {
                        p.setAX(0.25);
                    } else {
                        p.setAX(-0.25);
                    }
                    if (ydist < 0) {
                        p.setAY(-9.8 / 60 + 0.25);
                    } else {
                        p.setAY(-9.8 / 60 - 0.25);
                    }
                } else {
                    p.setAX(0.0);
                    p.setAY(-9.8 / 60);
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

    public void start() {
        running = true;
    }

    public void pause() {
        running = false;
    }

    public void quit() {
    }

    public void restart() {
		trails = true;
		particles.clear();
		ticks = 0;
        running = false;
    }
	
	public void click() {
		trails = !trails;
	}

    public void keypress(char key) {
    }
}
