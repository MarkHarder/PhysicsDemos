package markharder.physicsdemos.demo.demos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import markharder.physicsdemos.demo.interfacing.Slider;

public class Rocket implements Demo {
    private static double EXHAUST_VELOCITY = -5.0;
    private static double FUEL_MASS = 0.2;

    public boolean running;
    private int width, height;
	private int ticks;
	
    private Rectangle rocket;
    private double vy, ay;
    private int fuel;
    private double mass = 5.0;

    private Slider fuelSlider;
    private Slider massSlider;
	
	public Rocket(int width, int height) {
        this.width = width;
        this.height = height;
        running = false;

		ticks = 0;

        rocket = new Rectangle(230, 0, 40, 60);
        vy = 0;
        ay = 0;
        fuel = 60 / 3;
        mass = 5.0;

        fuelSlider = new Slider(400, 50, 300, 5, 30, 0.0);
        massSlider = new Slider(450, 50, 300, 1, 10, 0.5);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		g.setColor(Color.BLUE);
        g.fillRect((int) rocket.getX(), (int) (height - (rocket.getY() + rocket.getHeight())), (int) rocket.getWidth(), (int) rocket.getHeight());
        if (fuel > 0) {
            g.setColor(Color.RED);
            g.fillRect((int) (rocket.getX() + rocket.getWidth() / 2 - 2.5), (int) (height - rocket.getY()), 5, 5);
        }
        fuelSlider.draw(g);
        massSlider.draw(g);
	}

	@Override
	public void tick() {
        if (fuelSlider.isActive()) {
            fuelSlider.tick();
        }
        if (massSlider.isActive()) {
            massSlider.tick();
        }

        if (running) {
            if (fuel > 0) {
                ay = -EXHAUST_VELOCITY / mass();
                fuel--;
            } else {
                ay = 0;
            }
            if (rocket.getY() > 0) {
                ay -= (9.8 / 60); // gravity
            }
            vy += ay;
            rocket.setBounds((int) rocket.getX(), (int) (rocket.getY() + vy), (int) rocket.getWidth(), (int) rocket.getHeight());
            if (rocket.getY() < 0) {
                rocket.setBounds((int) rocket.getX(), 0, (int) rocket.getWidth(), (int) rocket.getHeight());
            }
        }
	}

    public double mass() {
        return mass + FUEL_MASS * fuel;
    }

    public void start() {
    }

    public void pause() {
        running = false;
    }

    public void quit() {
    }

    public void restart() {
        running = false;
		ticks = 0;
        rocket = new Rectangle(230, 0, 40, 60);
        fuel = 20;
        mass = 5.0;
        vy = 0;
        ay = 0;
        fuelSlider = new Slider(400, 50, 300, 5, 30, 0.0);
        massSlider = new Slider(450, 50, 300, 1, 10, 0.5);
    }
	
	public void click(int x, int y) {
        if (fuelSlider.contains(x, y)) {
            fuelSlider.click();
        } else if (massSlider.contains(x, y)) {
            massSlider.click();
        }
	}
    
    public void release(int x, int y) {
        if (fuelSlider.isActive()) {
            fuelSlider.release();
        } else if (massSlider.isActive()) {
            massSlider.release();
        }
    }

    public void keypress(char key) {
		ticks = 0;
        mass = massSlider.getValue();
        rocket = new Rectangle(230, 0, (int) (mass * 8), 60);
        fuel = (int) fuelSlider.getValue();
        vy = 0;
        ay = 0;
        running = true;
    }
}
