package stoeger;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Schalter fuer das LightsOut Spiel
 * @author Michael Stoeger
 * @version 10.12.2014
 */
public class Schalter extends JPanel implements MouseListener {
	private boolean isOn, mouseOn; // isOn <- Status des Schalters, mouseOn <- ob die Maus ueber dem Schalter schwebt
	private int num; // Nummer des Schalters
	private GUI g; // Gespeichertes GUI Objekt
	private boolean enabled;
	/**
	 * Aendert den Status des Schaltes
	 */
	public void switchState() {
		if (isOn) // Wenn der Schalter an ist
			isOn = false; // Ausschalten
		else // Wenn der Schalter aus ist
			isOn = true; // Einschalten
		repaint(); // Neu zeichnen um Farbtoene zu uebernehmen
	}
	/**
	 * Konstruktor fuer den Schalter Benoetigt als Paramter: isOn <- ob der
	 * Schalter beim erstellen eingeschalten sein soll num <- Fortlaufende
	 * Nummerierung der Schalter
	 * 
	 * @param isOn
	 * @param num
	 * @param g
	 */
	public Schalter(boolean isOn, int num, GUI g) {
		this.isOn = isOn; // Variablen speichern
		this.g = g;
		this.num = num;
		enabled = true; // Schalter ist Standardmaessig aktiviert
		addMouseListener(this); // MouseListener hinzufuegen
		repaint(); // Farbe zeichnen
	}
	/**
	 * Gibt zurueck ob der Schalter aktiviert ist
	 * 
	 * @return boolean
	 */
	public boolean isOn() {
		return isOn;
	}
	@Override
	/**
	 * Meldet an die GUI das der Schalter gedrueckt wurde
	 * @param arg0
	 */
	public void mouseClicked(MouseEvent arg0) {
		if(enabled)
			g.clicked(num);
	}
	@Override
	/**
	 * Gibt dem Feld eine leichte Markierung wenn die Maus auf dem Feld ist
	 * @param arg0
	 */
	public void mouseEntered(MouseEvent arg0) {
		mouseOn = true; // Boolean wird auf true gesetzt
		repaint(); // Damit die paintComponent Methode die Farbe des Schalters richtig zeichnet
	}
	@Override
	/**
	 * Setzt das Feld auf die Standardfarbe zurueck wenn die Maus das Feld verlaesst
	 * @param arg0
	 */
	public void mouseExited(MouseEvent arg0) {
		mouseOn = false; // Maus nicht mehr auf Schalter
		repaint(); // Farbe wird neu gezeichnet
	}
	/**
	 * Nicht benoetigte Methode des MouseListener Interfaces
	 */
	public void mousePressed(MouseEvent arg0) {}
	/**
	 * Nicht benoetigte Methode des MouseListener Interfaces
	 */
	public void mouseReleased(MouseEvent arg0) {}
	/**
	 * Zeichnet die Farben des Feldes
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // Um Grafikfehler zu vermeiden wird die paintComponent Methode der Superklasse aufgerufen
		Color c; // Temporaerer Zwischenspeicher fuer die Schalterfarbe
		if (isOn && mouseOn) // Wenn die Maus ueber einem eingeschaltenen und ausgewaehlten Button ist
			c = new Color(00, 255, 00); // Farbe ganz Gruen setzen
		else if (isOn) // Wenn der Schalter nur ausgewaehlt ist
			c = new Color(00, 200, 00); // Farbe fast ganz Gruen
		else if (!isOn && mouseOn) // Wenn der Schalter nicht aktiviert, aber die Maus ueber dem Schalter ist
			c = new Color(00, 50, 00); // Farbe ganz schwach Grün leuchtend machen
		else // Wenn der Schalter weder ausgewaehlt noch die Maus darueber ist
			c = new Color(00, 20, 00); // Gruen fast nicht sichtbar machen
		g.setColor(c); // Gewuenschte Farbe setzen
			g.fillRect(0, 0, getWidth(), getHeight()); // Schalter anmalen
		g.setColor(Color.BLACK); // Farbe fuer schwarzen Rahmen setzen
		g.drawRect(0, 0, getWidth(), getHeight()); // Rahmen zeichnen
	}
	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}