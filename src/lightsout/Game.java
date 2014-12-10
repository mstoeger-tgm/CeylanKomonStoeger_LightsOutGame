package lightsout;
import java.awt.event.*;
import javax.swing.JOptionPane;
/**
 * Controller Klasse fuer das LightsOut Spiel
 * Beinhaltet ActionListener fuer die Buttons
 * @author Patrick Komon, Michael Stoeger
 * @version 10.12.2014
 */
public class Game implements ActionListener{
	private GUI gui; //Speichert das GUI Objekt
	private int moves; //Zwischenspeicher fuer die Anzahl an vergangenen Zuegen
	/**
	 * Standardkonstruktor fuer LightsOut Spiel
	 * Erzeugt ein neues GUI Objekt und initialisiert die moves Variable
	 */
	public Game() {
		gui = new GUI(this);
		moves = 0;
	}
	/**
	 * Aendert den Zustand eines bestimmten und
	 * seiner umliegenden Schalter
	 * (links, rechts, darueber, darunter)
	 * @param switchIndex
	 */
	public void clicked(int switchIndex) {
		moves++; //Zuege um 1 erhoehen
		gui.refresh("Moves: "+Integer.toString(moves)); //Wert in der GUI anpassen
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
		boolean gewonnen = true; //Standardmaesig hat man gewonnen
		for(int i=0;i<GUI.FIELDS*GUI.FIELDS;i++) //Alle Schalter durchgehen
			if(gui.isOn(i)) //Wenn einer noch eingeschalten ist
				gewonnen=false; //Hat man noch nicht gewonnen
		if(gewonnen){ //Wenn man gewonnen hat
			gui.disableAll(); //Lichter und Zeitzaehler werden deaktiviert
			JOptionPane.showMessageDialog(gui, "Gewonnen"); //Meldung erscheint auf dem Bildschirm
		}
	}
	/**
	 * Programmstart
	 * Erzeugt ein neues Game Objekt
	 * @param args
	 */
	public static void main(String[] args) {
		new Game();
	}
	@Override
	/**
	 * ActionListener
	 * Ist fuer den "Neu" Button zustaendig
	 */
	public void actionPerformed(ActionEvent arg0) {
		gui.init(); //Initialisiert das Spiel
		moves=0; //Setzt die vergangenen Zuege zurueck
		gui.refresh("Moves: "+Integer.toString(moves)); //Schreibt den neuen Wert in die GUI
	}
}
