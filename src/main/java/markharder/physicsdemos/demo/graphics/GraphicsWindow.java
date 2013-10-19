/*
 * Mark Harder
 * 2013.10.19
 * 
 * GraphicsWindow
 * ----------
 * An abstract class that can be extended.
 * Brings up and displays a window on the
 * screen. Calling draw and tick methods
 * controls the application.
 */

package markharder.physicsdemos.demo.graphics;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import markharder.physicsdemos.demo.input.Mouse;

public abstract class GraphicsWindow extends Canvas implements Runnable {
	private boolean running = false;
	private Thread thread;
    protected Mouse mouse;
	protected JFrame frame;
	protected int width, height;
	
    /*
     *   Create a new object by setting up width and height
     * along with other basic options before starting the
     * application.
     */
	public GraphicsWindow(int w, int h) {
		width = w;
		height = h;
		
		frame = new JFrame();
		frame.add(this);
		frame.pack();
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

        mouse = new Mouse();
        addMouseMotionListener(mouse);
		addMouseListener(mouse);
		frame.requestFocus();	

		start();
	}

    // start the thread
	public void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
    // stop the thread
	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 *   Start the main loop that calls the draw(Graphics) method
	 * as quickly as possible and the tick() method 60 times per second
	 */
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int ticks = 0;
		
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while (delta >= 1) {
				tick();
				ticks++;
				delta--;
			}
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 10) {
				timer += 1000;
				System.out.println(ticks + "tps, " + frames + "fps");
				ticks = 0;
				frames = 0;
			}
		}
	}
	
    /*
     * Set everything up to be rendered onto the screen
     * - Create a buffer strategy if one doesn't exist
     * - Get the graphics image to use
     * - Call the draw method
     */
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		draw(g);
		
		g.dispose();
		bs.show();
	}
	
	public abstract void draw(Graphics g);
	
	public abstract void tick();
}
