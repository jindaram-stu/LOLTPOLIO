package com.loltpolio.gui.eventlistener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class WindowMoveListener implements MouseListener, MouseMotionListener {
	JFrame frame;
	static int current_x;
	static int current_y;
	
	public WindowMoveListener(JFrame frame) {
		this.frame = frame;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		int dif_x = current_x - e.getX();
		int dif_y = current_y - e.getY();
		frame.setLocation((int)frame.getLocation().getX() - dif_x,(int)frame.getLocation().getY() - dif_y);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		current_x = e.getX();
		current_y = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
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

}
