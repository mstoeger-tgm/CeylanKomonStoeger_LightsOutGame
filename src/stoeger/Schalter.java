package stoeger;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Schalter extends JPanel implements MouseListener{
	private boolean isOn, mouseOn;
	private int num;
	private GUI g;
	public void switchState(){
		if(isOn)
			isOn=false;
		else
			isOn=true;
		repaint();
	}
	public Schalter(boolean isOn, int num, GUI g){
		this.isOn=isOn;
		this.g=g;
		this.num=num;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}
	@Override
	/**
	 * Gibt dem Feld eine leichte Markierung wenn die Maus auf dem Feld ist
	 * @param arg0
	 */
	public void mouseEntered(MouseEvent arg0) {
		mouseOn=true;
		repaint();
	}
	@Override
	/**
	 * Setzt das Feld auf die Standardfarbe zurueck wenn die Maus das Feld verlaesst
	 * @param arg0
	 */
	public void mouseExited(MouseEvent arg0) {
		mouseOn = false;
		repaint();
	}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	
}
