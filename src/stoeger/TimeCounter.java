package stoeger;
/**
 * Zeitzaehler fuer das LightsOut Spiel
 * Muss in Thread verpackt werden, da bei blossem Aufrufen der run() Methode das Programm fuer 24h einfrieren wuerde
 * @author Michael Stoeger
 * @version 10.12.2014
 */
public class TimeCounter implements Runnable{
	private GUI g; //Gespeichertes GUI Objekt
	private boolean stop; //Zeigt an ob der Zaehler gestoppt werden soll
	private int sec, min, hour; //Variablen fuer Sekunde, Minute, Stunde
	/**
	 * Erzeugt einen neuen Zeitzaehler
	 * @param g
	 */
	public TimeCounter(GUI g){
		this.g=g; //GUI speichern
		stop=false; //nicht stoppen
	}
	/**
	 * Standardkonstruktor blockieren, da sonst das Programm abstuerzt
	 */
	private TimeCounter(){}
	@Override
	/**
	 * Diese Methode wird beim Threadstart ausgefuehrt
	 * Methode schlaeft immer eine Sekunde und zaehlt dann weiter(soll spaeter von Systemzeit abhaengen)
	 * Zusammengesetzt Uhrzeit wird dann in JLabel in der GUI geschrieben
	 */
	public void run() {
		while(!stop){ //Solange die Methode nicht stoppen soll
			try {
				Thread.sleep(1000); //Eine Sekunde schlafen
			} catch (InterruptedException e) { //Muss gecatcht werden, da Thread.sleep() unterbrochen werden kann
				System.err.println("Sleep interrupted"); //Fehlermeldung in Konsole ausgeben
			}
			sec++; //Sekunde hochzaehlen
			if(sec==60){ //Nach einer Minute
				sec=0; //Sekunde wieder auf 0 setzen
				min++; //Minuten erhoehen
			}
			if(min==60){ //Nach einer Stunde
				min=0; //Minuten auf 0 setzen
				hour++; //Stunden erhoehen
			}if(hour==24){ //Nach 24 Stunden
				stop(); //Thread stoppen
			}
			String time = Integer.toString(hour)+":"+Integer.toString(min)+":"+Integer.toString(sec); //Zeit zusammensetzen
			g.setTime(time); //Zeit in JLabel in GUI schreiben
		}
	}
	/**
	 * Zeitzaehler zuruecksetzen
	 */
	public void init(){
		stop=false; //Thread weiterlaufen lassen
		sec=0; //Sekunden zuruecksetzen
		min=0; //Minuten zuruecksetzen
		hour=0; //Stunden zuruecksetzen
	}
	/**
	 * Thread stoppen
	 */
	public void stop(){
		stop=true; //Stoppt die Schleife nach spaetestens einer Sekunde
	}
}
