package markharder.physicsdemos.demo.demos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import markharder.physicsdemos.demo.interfacing.Slider;

public class Cylinder implements Demo {
    public boolean running;
    private int width, height;
	private int ticks;
	
    private Rectangle box;
    private double vy, ay;
    private double cylinderMass;
    private double boxMass;

    private Slider cylinderMassSlider;
    private Slider boxMassSlider;
	
	public Cylinder(int width, int height) {
        this.width = width;
        this.height = height;
        running = false;

		ticks = 0;

        vy = 0;
        ay = 0;
        cylinderMass = 5.0;
        boxMass = 5.0;
        box = new Rectangle((int) (300 - (boxMass * 2)), 400, (int) (boxMass * 4), 40);

        cylinderMassSlider = new Slider(400, 50, 320, 1, 10, 0.5, "Cylinder");
        boxMassSlider = new Slider(450, 70, 300, 1, 20, 0.5, "Box");
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		g.setColor(Color.BLUE);
        g.fillOval(300, 50, (int) (cylinderMass * 10), (int) (cylinderMass * 10));

        g.setColor(Color.RED);
        g.fillRect((int) (300 - box.getWidth() / 2), (int) (height - box.getY()), (int) box.getWidth(), (int) box.getHeight());

        g.setColor(Color.GREEN);
        g.drawLine(300, 50 + (int) (cylinderMass * 10 / 2), 300, (int) (height - box.getY()));

        cylinderMassSlider.draw(g);
        boxMassSlider.draw(g);
	}

	@Override
	public void tick() {
        if (cylinderMassSlider.isActive()) {
            cylinderMassSlider.tick();
            cylinderMass = cylinderMassSlider.getValue();
            box = new Rectangle((int) (300 - (boxMass * 2)), 400, (int) (boxMass * 4), 40);
            vy = 0;
            ay = 0;
        }
        if (boxMassSlider.isActive()) {
            boxMassSlider.tick();
            boxMass = boxMassSlider.getValue();
            box = new Rectangle((int) (300 - (boxMass * 2)), 400, (int) (boxMass * 4), 40);
            vy = 0;
            ay = 0;
        }

        if (running) {
            if (box.getY() > 40.001) {
                ay = -9.8 / 60 / (1 + cylinderMass / (2 * boxMass));
                vy += ay;
                box.setBounds((int) box.getX(), (int) (box.getY() + vy), (int) box.getWidth(), (int) box.getHeight());
            }
            if (box.getY() < 40.001) {
                box.setBounds((int) box.getX(), 40, (int) box.getWidth(), (int) box.getHeight());
            }
        }
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

        vy = 0;
        ay = 0;
        cylinderMass = 5.0;
        boxMass = 5.0;
        box = new Rectangle((int) (300 - (boxMass * 2)), 400, (int) (boxMass * 4), 40);

        cylinderMassSlider = new Slider(400, 50, 320, 5, 30, 0.0, "Cylinder");
        boxMassSlider = new Slider(450, 70, 300, 1, 20, 0.5, "Box");
    }
	
	public void click(int x, int y) {
        if (cylinderMassSlider.contains(x, y)) {
            cylinderMassSlider.click();
        } else if (boxMassSlider.contains(x, y)) {
            boxMassSlider.click();
        }
	}
    
    public void release(int x, int y) {
        if (cylinderMassSlider.isActive()) {
            cylinderMassSlider.release();
        } else if (boxMassSlider.isActive()) {
            boxMassSlider.release();
        }
    }

    public void keypress(char key) {
		ticks = 0;
        box = new Rectangle((int) (300 - (boxMass * 2)), 400, (int) (boxMass * 4), 40);
        vy = 0;
        ay = 0;
        running = true;
    }
}
