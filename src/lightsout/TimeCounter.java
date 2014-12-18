package lightsout;
/**
 * Zeitzaehler fuer das LightsOut Spiel
 * Muss in Thread verpackt werden, da bei blossem Aufrufen der run() Methode das Programm fuer 24h einfrieren wuerde
 * @author Michael Stoeger
 * @version 10.12.2014
 */
public class TimeCounter implements Runnable{
	private GUI g; //Gespeichertes GUI Objekt
	private boolean stop; //Zeigt an ob der Zaehler gestoppt werden soll
	private long start; //Speichert die Uhrzeit beim Zeitzaehlerstart
	/**
	 * Erzeugt einen neuen Zeitzaehler
	 * @param g
	 */
	public TimeCounter(GUI g){
		this.g=g; //GUI speichern
		init(); //Initialisiert den Zeitzaehler
	}
	/**
	 * Standardkonstruktor blockieren, da sonst das Programm abstuerzt
	 */
	private TimeCounter(){}
	@Override
	/**
	 * Diese Methode wird beim Threadstart ausgefuehrt
	 * Methode versetzt den Thread mit Thread.sleep() 1 Sekunde in einen Schlafzustand und
	 * berechnet dann den Unterschied zur Startzeit
	 * Die zusammengesetzte Uhrzeit wird dann in ein JLabel in der GUI geschrieben
	 */
	public void run() {
		while(!stop){ //Solange die Methode nicht stoppen soll
			try {
				Thread.sleep(1000); //Eine Sekunde schlafen
			} catch (InterruptedException e) { //Muss gecatcht werden, da Thread.sleep() unterbrochen werden kann
				System.err.println("Sleep interrupted"); //Fehlermeldung in Konsole ausgeben
			}
			long diff = (System.currentTimeMillis()-start)/1000; //Differenz der 2 Zeitpunkte berechnen
			int min = (int) (diff/60);
			int std = min/60;
			if(std>=24) //Wenn 24 Stunden vergangen sind
				stop(); //Zaehler stoppen
			g.setTime(Integer.toString(std)+":"+Integer.toString(min)+":"+Long.toString(diff%60)); //Zeit in JLabel in GUI schreiben
			if(stop) //Wenn der Thread gestoppt werden soll
				g.setTime("0:0:0"); //Zeit auf 0 setzen
		}
	}
	/**
	 * Zeitzaehler zuruecksetzen
	 */
	public void init(){
		stop=false; //Thread weiterlaufen lassen
		start = System.currentTimeMillis(); //Startzeit festhalten
	}
	/**
	 * Thread stoppen
	 */
	public void stop(){
		stop=true; //Stoppt die Schleife nach spaetestens einer Sekunde
	}
}
