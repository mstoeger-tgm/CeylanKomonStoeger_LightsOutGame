package stoeger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * 
 * @author Patrick Komon
 * @version 10-12-2014
 */
public class Game implements ActionListener{

	private GUI gui;
	private int moves;
	public Game() {
		gui = new GUI(this);
		moves = 0;
	}
	
	/**
	 * �ndert den Zustand eines bestimmten und
	 * seiner umliegenden Schalter
	 * (links, rechts, dar�ber, darunter)
	 * @param switchIndex
	 */
	public void clicked(int switchIndex) {
		moves++;
		gui.refresh("Moves: "+Integer.toString(moves));
		gui.switchState(switchIndex);
		if(switchIndex%GUI.FIELDS!=0){//wenn der Schalter nicht am linken Ende der Zeile ist
			gui.switchState(switchIndex-1);
		}
		if(switchIndex%GUI.FIELDS!=4){//wenn der Schalter nicht am rechten Ende der Zeile ist
			gui.switchState(switchIndex+1);
		}
		if(switchIndex>GUI.FIELDS){ //wenn der Schalter nicht am oberen Ende des Spielfelds ist
			gui.switchState(switchIndex-GUI.FIELDS);
		}
		if(switchIndex<GUI.FIELDS*GUI.FIELDS-GUI.FIELDS){ //wenn der Schalter nicht am unteren Ende des Spielfelds ist
			gui.switchState(switchIndex+GUI.FIELDS);
		}
		boolean gewonnen = true;
		for(int i=0;i<GUI.FIELDS*GUI.FIELDS;i++)
			if(gui.isOn(i))
				gewonnen=false;
		if(gewonnen){
			gui.disableAll();
			JOptionPane.showMessageDialog(gui, "Gewonnen");
		}
	}

	public static void main(String[] args) {
		new Game();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		gui.init();
		moves=0;
		gui.refresh("Moves: "+Integer.toString(moves));
	}
}
