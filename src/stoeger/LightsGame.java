package stoeger;

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
	public void getField();
}
