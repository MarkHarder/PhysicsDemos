package markharder.physicsdemos.demo.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import markharder.physicsdemos.demo.App;

public class Keyboard implements KeyListener {
	@Override
	public void keyPressed(KeyEvent e) {
		App.application.keypress(e.getKeyChar());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}

