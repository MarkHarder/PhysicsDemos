package markharder.physicsdemos.demo.demos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Rocket implements Demo {
    private static double EXHAUST_VELOCITY = -5.0;
    private static double FUEL_MASS = 0.2;
    private static double ROCKET_MASS = 5.0;

    public boolean running;
    private int width, height;
	private int ticks;
	
    private Rectangle rocket;
    private double vy, ay;
    private int fuel;
	
	public Rocket(int width, int height) {
        this.width = width;
        this.height = height;
        running = false;

		ticks = 0;

        rocket = new Rectangle(230, 0, 40, 60);
        fuel = 60 / 3;
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
	}

	@Override
	public void tick() {
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
        return ROCKET_MASS + FUEL_MASS * fuel;
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
        rocket = new Rectangle(230, 0, 40, 60);
        fuel = 20;
    }
	
	public void click() {
	}

    public void keypress(char key) {
    }
}
