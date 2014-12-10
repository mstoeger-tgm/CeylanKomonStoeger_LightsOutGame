package lightsout;
/**
 * Interface welches anzeigt, welche Methoden dem Controller sicher zur Steuerung des Spiels zur Verfuegung stehen
 * @author Michael Stoeger
 * @version 10.12.2014
 */
public interface LightsGame {
	/**
	 * Schalter werden beginnend mit 0 in Schreibrichtung gezaehlt
	 * Aendert den Status des Schalters
	 * @param toSwitch
	 */
	public void switchState(int toSwitch);
	/**
	 * Gibt zurueck ob der gewuenschte Schalter eingeschalten ist
	 * @param switchOn
	 * @return isOn
	 */
	public boolean isOn(int switchOn);
	/**
	 * Initialisiert das Spiel
	 */
	public void init();
	/**
	 * Gibt die Laenge des Feldes zurueck
	 */
	public int getField();
	/**
	 * aktualisiert die Anzeigen des Spiels
	 * @param moves
	 */
	public void refresh(String moves);
}
