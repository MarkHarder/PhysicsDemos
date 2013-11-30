package markharder.physicsdemos.demo.demos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import markharder.physicsdemos.demo.interfacing.Slider;

public class Cylinder implements Demo {
    public boolean running;
    private int width, height;
	
    private Rectangle box;
    private double vy, ay;
    private double cylinderMass;
    private double boxMass;

    private Slider cylinderMassSlider;
    private Slider boxMassSlider;

    private int timer;
	
	public Cylinder(int width, int height) {
        this.width = width;
        this.height = height;
        running = false;

        timer = 0;

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
		
		g.setColor(new Color(0x3498DB));
        g.fillOval(300, 50, (int) (cylinderMass * 10), (int) (cylinderMass * 10));

        g.setColor(new Color(0xE74C3C));
        g.fillRect((int) (300 - box.getWidth() / 2), (int) (height - box.getY()), (int) box.getWidth(), (int) box.getHeight());

        g.setColor(new Color(0x2ECC71));
        g.drawLine(300, 50 + (int) (cylinderMass * 10 / 2), 300, (int) (height - box.getY()));

        cylinderMassSlider.draw(g);
        boxMassSlider.draw(g);

        g.setColor(new Color(0x2ECC71));
        g.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        String seconds = Integer.toString(timer % 60);
        if (seconds.length() == 1) {
            seconds = "0" + seconds;
        }
        g.drawString(Integer.toString(timer / 60) + "." + seconds + "s", 50, 50);
	}

	@Override
	public void tick() {
        if (cylinderMassSlider.isActive()) {
            cylinderMassSlider.tick();
            cylinderMass = cylinderMassSlider.getValue();
            box = new Rectangle((int) (300 - (boxMass * 2)), 400, (int) (boxMass * 4), 40);
            vy = 0;
            ay = 0;
            timer = 0;
        }
        if (boxMassSlider.isActive()) {
            boxMassSlider.tick();
            boxMass = boxMassSlider.getValue();
            box = new Rectangle((int) (300 - (boxMass * 2)), 400, (int) (boxMass * 4), 40);
            vy = 0;
            ay = 0;
            timer = 0;
        }

        if (running) {
            if (box.getY() > 40.001) {
                timer++;
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
        timer = 0;

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
        timer = 0;
        box = new Rectangle((int) (300 - (boxMass * 2)), 400, (int) (boxMass * 4), 40);
        vy = 0;
        ay = 0;
        running = true;
    }
}
