package stoeger;

import java.text.DateFormat;

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
	private int start;
	/**
	 * Erzeugt einen neuen Zeitzaehler
	 * @param g
	 */
	public TimeCounter(GUI g){
		this.g=g; //GUI speichern
		stop=false; //nicht stoppen
		start = timeToIntInSec();
		
	}
	public int timeToIntInSec(){
		String toConvert = DateFormat.getTimeInstance(DateFormat.MEDIUM).toString();
		String tmp = ""+toConvert.charAt(0)+toConvert.charAt(1);
		int std = Integer.parseInt(tmp);
		tmp = ""+toConvert.charAt(3)+toConvert.charAt(4);
		int min = Integer.parseInt(tmp);
		tmp = ""+toConvert.charAt(6)+toConvert.charAt(7);
		int sec = Integer.parseInt(tmp);
		while(std!=0){
			min+=60;
			std--;
		}
		while(min!=0){
			sec+=60;
			min--;
		}
		return sec;
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
			int diff = timeToIntInSec()-start;
			int min =0;
			for(;diff<60;diff-=60, min++){}
			int std=0;
			for(;min<60;min-=60, std++){}
			g.setTime(Integer.toString(std)+":"+Integer.toString(min)+":"+Integer.toString(diff)); //Zeit in JLabel in GUI schreiben
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
