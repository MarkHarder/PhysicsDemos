package markharder.physicsdemos.demo.demos;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import markharder.physicsdemos.demo.physics.Line;
import markharder.physicsdemos.demo.physics.Particle2D;
import markharder.physicsdemos.demo.physics.Point;

public class Meteor implements Demo {
    public boolean running;
    private int width, height;
	private int ticks;
	
	private Point endPoint;
	
	private ArrayList<MeteorParticle2D> particles;
	private ArrayList<Line> lines;
	
	public Meteor(int width, int height) {
        this.width = width;
        this.height = height;
        running = false;

		ticks = 0;
		particles = new ArrayList<MeteorParticle2D>();
		lines = new ArrayList<Line>();
		
		endPoint = null;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		g.setColor(Color.GREEN);
		for (int i = 0; i < lines.size(); i++) {
			lines.get(i).draw(g, width, height);
		}
		
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).draw(g, width, height);
		}
		
		if (endPoint != null) {
			g.setColor(Color.GREEN);
			g.fillOval((int) (endPoint.getX() - 2.5), (int) (height - (endPoint.getY() - 2.5)), 5, 5);
		}

	}

	@Override
	public void tick() {
        if (running) {
            ArrayList<Integer> delete = new ArrayList<Integer>();
            
            for (int i = 0; i < particles.size(); i++) {
                MeteorParticle2D p = particles.get(i);
                for (int j = 0; j < lines.size(); j++) {
                    if (lines.get(j).intersects(p.getVelocityVector())) {
                        p.reflect(lines.get(j));
                    }
                }
                if (p.getLastY() < 0) {
                    delete.add(i);
                }
                p.tick();
                
            }
            
            for (int i = 0; i < delete.size(); i++) {
                particles.remove(delete.get(i) - i);
            }
            
            ticks++;
            if (ticks % 120 == 0) {
                particles.add(new MeteorParticle2D(240, 470, -2, 3));
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
        running = false;
		ticks = 0;
		particles.clear();
		lines.clear();
		
		endPoint = null;
    }
	
	public void click(int x, int y) {
		if (endPoint == null) {
			endPoint = new Point(x, height - y);
		} else {
			if (endPoint.getX() < x) {
				lines.add(new Line(endPoint.getX(), endPoint.getY(), x, height - y));
			} else {
				lines.add(new Line(x, height - y, endPoint.getX(), endPoint.getY()));
			}
			endPoint = null;
		}
	}

    public void release(int x, int y) {
    }

    public void keypress(char key) {
    }


    private class MeteorParticle2D extends Particle2D {
        private Point[] trail;

        public MeteorParticle2D(int x, int y, int vx, int vy) {
            super(x, y, vx, vy);
            trail = new Point[20];

            for (int i = 0; i < trail.length; i++) {
                trail[i] = new Point(x, y);
            }
        }

        public void tick() {
            for (int i = trail.length - 1; i >= 1; i--) {
                trail[i] = trail[i - 1];
            }

            vy += ay;
            x += vx;
            y += vy;
            trail[0] = new Point(x, y);
        }

        public void draw(Graphics g, int width, int height) {
            Point p = trail[15];
            if (p != null) {
                g.setColor(Color.WHITE);
                g.fillOval((int) (p.getX() - 5.0 / 2), (int) (height - (p.getY() + 5.0 / 2)), 5, 5);
            }

            p = trail[10];
            if (p != null) {
                g.setColor(Color.YELLOW);
                g.fillOval((int) (p.getX() - 10.0 / 2), (int) (height - (p.getY() + 10.0 / 2)), 10, 10);
            }

            p = trail[5];
            if (p != null) {
                g.setColor(Color.ORANGE);
                g.fillOval((int) (p.getX() - 15.0 / 2), (int) (height - (p.getY() + 15.0 / 2)), 15, 15);
            }

            p = trail[0];		
            if (p != null) {
                g.setColor(Color.BLUE);
                g.fillOval((int) (p.getX() - 20.0 / 2), (int) (height - (p.getY() + 20.0 / 2)), 20, 20);
            }
        }

        public double getLastY() {
            for (int i = trail.length - 1; i <= 0; i--) {
                if (trail[i] != null) {
                    return trail[i].getY();
                }
            }

            return 0.0;
        }
    }
}
