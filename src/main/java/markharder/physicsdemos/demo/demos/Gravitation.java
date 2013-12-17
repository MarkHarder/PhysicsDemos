package markharder.physicsdemos.demo.demos;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import markharder.physicsdemos.demo.physics.Satellite;
import markharder.physicsdemos.demo.physics.Planet;
import markharder.physicsdemos.demo.physics.Vector2D;
import markharder.physicsdemos.demo.input.Mouse;

public class Gravitation implements Demo {
    private boolean running;
    private int width, height;
	private List<Satellite> particles;
    private ArrayList<Planet> planets;
    private Planet drag;
	
	public Gravitation(int width, int height) {
        this.width = width;
        this.height = height;
		
		particles = new ArrayList<Satellite>();
        planets = new ArrayList<Planet>();

        setup();
	}

    public void setup() {
        particles.clear();
        planets.clear();
        drag = null;
        running = false;
    }
	
	@Override
	public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 30));
        g.fillRect(0, 0, width, height);

        g.setColor(new Color(20, 20, 20));
        for (int i = 0; i < width; i += 50) {
            for (int j = 0; j < height; j += 50) {
                g.fillRect(i, j, 5, 5);
            }
        }

        for (int i = 0; i < planets.size(); i++) {
            planets.get(i).draw(g, width, height);
        }

        if (drag != null) {
            double centerX = drag.getX() + drag.getRadius();
            double centerY = drag.getY() + drag.getRadius();
            drag.setRadius(Math.sqrt((Mouse.location.getX() - centerX) * (Mouse.location.getX() - centerX) + (Mouse.location.getY() - centerY) * (Mouse.location.getY() - centerY)));
            drag.setX(centerX - drag.getRadius());
            drag.setY(centerY - drag.getRadius());
            drag.draw(g, width, height);
        }

        for (int i = 0; i < particles.size(); i++) {
			particles.get(i).draw(g, width, height);
		}
	}

	@Override
	public void tick() {
        if (running) {
            for (int i = 0; i < particles.size(); i++) {
                Satellite s = particles.get(i);
                // adjust acceleration based on planets
                s.setAX(0);
                s.setAY(0);
                for (int j = 0; j < planets.size(); j++) {
                    Vector2D gravityForce = planets.get(j).gravityForce(s);
                    s.setAX(s.getAX() + gravityForce.getX());
                    s.setAY(s.getAY() + gravityForce.getY());
                }

                s.tick();
            }
        }
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
	
	public void click(int x, int y) {
        drag = new Planet(x, y, 0);
	}

    public void release(int x, int y) {
        if (drag != null) {
            planets.add(drag);
            drag = null;
        }
    }

    public void keypress(char key) {
        particles.add(new Satellite(200.0, 200.0, 1.0, 0.0));
    }
}
