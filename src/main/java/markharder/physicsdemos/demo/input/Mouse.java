package markharder.physicsdemos.demo.input;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import markharder.physicsdemos.demo.App;

public class Mouse implements MouseListener, MouseMotionListener {
	public static Point location;
	
	public Mouse() {
		location = new Point(0, 0);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		App.application.click();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
        App.application.release();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		location = e.getPoint();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		location = e.getPoint();
	}

}
