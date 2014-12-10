package stoeger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * @author Patrick Komon
 * @version 10-12-2014
 */
public class Game implements ActionListener{

	private GUI gui;

	public Game() {
		gui = new GUI(this);
	}
	
	/**
	 * �ndert den Zustand eines bestimmten und
	 * seiner umliegenden Schalter
	 * (links, rechts, dar�ber, darunter)
	 * @param switchIndex
	 */
	public void clicked(int switchIndex) {
		gui.switchState(switchIndex);
		if(switchIndex%GUI.FIELDS!=0){//wenn der Schalter nicht am linken Ende der Zeile ist
			gui.switchState(switchIndex-1);
		}
		if(switchIndex%GUI.FIELDS!=4){//wenn der Schalter nicht am rechten Ende der Zeile ist
			gui.switchState(switchIndex+1);
		}
		
		
		
		
	}

	public static void main(String[] args) {
		new Game();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		gui.init();
	}
}
