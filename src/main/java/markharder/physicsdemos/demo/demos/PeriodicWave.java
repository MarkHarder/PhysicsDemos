package markharder.physicsdemos.demo.demos;

import java.awt.Color;
import java.awt.Graphics;

import markharder.physicsdemos.demo.interfacing.Slider;
import markharder.physicsdemos.demo.input.Mouse;

public class PeriodicWave implements Demo {
    private boolean running;
    private int width, height;
	private long ticks;
    private Slider amplitude;
    private Slider period;
    private int[] ys;
    private boolean reverse;
    private int offset;

    private Slider amplitude2;
    private Slider period2;
    private int[] ys2;
    private boolean reverse2;
    private int offset2;
	
	public PeriodicWave(int width, int height) {
        this.width = width;
        this.height = height;
		
        ys = new int[300];
        ys2 = new int[300];
        for (int i = 0; i < 300; i++) {
            ys[i] = 0;
            ys2[i] = 0;
        }

		ticks = 0;
        running = false;

        amplitude = new Slider(400, 50, 150, 10, 50, 1.0, "Amp.");
        period = new Slider(450, 50, 150, 20, 100, 1.0, "Per.");
        reverse = false;
        offset = 0;

        amplitude2 = new Slider(400, 250, 150, 10, 50, 1.0, "Amp.");
        period2 = new Slider(450, 250, 150, 20, 100, 1.0, "Per.");
        reverse2 = false;
        offset2 = 0;
	}

    private int y(int x, double t) {
        t += offset;
        double k = 2 * Math.PI / (period.getValue());
        double w = 2 * Math.PI / period.getValue(); // w = omega
        return (int) (amplitude.getValue() * Math.cos(k * x - w * t));
    }

    private int y2(int x, double t) {
        t += offset2;
        double k = 2 * Math.PI / (period2.getValue());
        double w = 2 * Math.PI / period2.getValue(); // w = omega
        return (int) (amplitude2.getValue() * Math.cos(k * x - w * t));
    }

	@Override
	public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 30));
        g.fillRect(0, 0, width, height);

        for (int x = 0; x < 300; x++) {
            if (x < 299 && ys[x] != ys[x+1]) {
                g.setColor(Color.RED);
                g.drawLine(100 + x, 100 + ys[x], 100 + x + 1, 100 + ys[x + 1]);
            }
            g.setColor(Color.RED);
            g.fillRect(100 + x, 100 + ys[x], 1, 1);

            if (x < 299 && ys2[x] != ys2[x+1]) {
                g.setColor(Color.BLUE);
                g.drawLine(100 + x, 250 + ys2[x], 100 + x + 1, 250 + ys2[x + 1]);
            }
            g.setColor(Color.BLUE);
            g.fillRect(100 + x, 250 + ys2[x], 1, 1);

            if (x < 299 && ys[x] + ys2[x] != ys[x+1] + ys2[x+1]) {
                g.setColor(new Color(255, 0, 255));
                g.drawLine(100 + x, 400 + ys[x] + ys2[x], 100 + x + 1, 400 + ys[x] + ys2[x + 1]);
            }
            g.setColor(new Color(255, 0, 255));
            g.fillRect(100 + x, 400 + ys[x] + ys2[x], 1, 1);
        }

        amplitude.draw(g);
        period.draw(g);

        amplitude2.draw(g);
        period2.draw(g);
	}

	@Override
	public void tick() {
        if (amplitude.isActive()) {
            amplitude.tick();
        }
        if (period.isActive()) {
            period.tick();
        }
        if (amplitude2.isActive()) {
            amplitude2.tick();
        }
        if (period2.isActive()) {
            period2.tick();
        }

        if (running) {
            ticks++;

            int t = (int) (ticks / 1);

            if (reverse) {
                for (int i = 0; i < 299; i++) {
                    ys[i] = ys[i + 1];
                }
                ys[299] = y(0, t);
            } else {
                for (int i = 299; i >= 1; i--) {
                    ys[i] = ys[i - 1];
                }
                ys[0] = y(0, t);
            }

            if (reverse2) {
                for (int i = 0; i < 299; i++) {
                    ys2[i] = ys2[i + 1];
                }
                ys2[299] = y2(0, t);
            } else {
                for (int i = 299; i >= 1; i--) {
                    ys2[i] = ys2[i - 1];
                }
                ys2[0] = y2(0, t);
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
		ticks = 0;
        running = false;
    }
	
	public void click(int x, int y) {
        if (amplitude.contains(x, y)) {
            amplitude.click();
        } else if (period.contains(x, y)) {
            period.click();
        } else if (amplitude2.contains(x, y)) {
            amplitude2.click();
        } else if (period2.contains(x, y)) {
            period2.click();
        }
	}
    
    public void release(int x, int y) {
        if (amplitude.isActive()) {
            amplitude.release();
        } else if (period.isActive()) {
            period.release();
        } else if (amplitude2.isActive()) {
            amplitude2.release();
        } else if (period2.isActive()) {
            period2.release();
        }
    }

    public void keypress(char key) {
        if (key == '1') {
            reverse = !reverse;
        } else if (key == '2') {
            reverse2 = !reverse2;
        } else if (key == 'q') {
            offset--;
        } else if (key == 'w') {
            offset++;
        } else if (key == 'a') {
            offset2--;
        } else if (key == 's') {
            offset2++;
        }
    }
}
