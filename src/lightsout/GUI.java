package lightsout;
import java.awt.*;
import java.util.*;
import javax.swing.*;
/**
 * GUI fuer LightsOut Spiel
 * Enthaelt Zeitzaehler
 * @author Michael Stoeger
 * @version 10.12.2014
 */
public class GUI extends JFrame implements LightsGame{
	private Game g; //Controller
	public final static int FIELDS = 5; //Konstante fuer groesse des Spiels
	private JLabel time, moves; //Time <- zeigt verstrichene Zeit im aktuellen Spiel an
	//moves <- Zeigt die vergangenen Zuege an, MUSS im Controller gesetzt werden
	private Thread tc; //Thread fuer Zeitzaehler
	private TimeCounter tco; //Zeitzaehlerobjekt
	private LinkedList<Schalter> schalter; //Enthaelt alle Schalter
	private JPanel mitte; //Container fuer die Schalter
	private JButton neu; //Erzeugt ein neues Spiel
	/**
	 * Standardkonstruktor blockiert
	 */
	private GUI(){}
	/**
	 * Konstruktor fuer das Spiel
	 * Benoetigt als Parameter ein Kontrollerobjekt
	 * @param g
	 */
	public GUI (Game g){
		super("LightsOut Game");
		this.g=g; //Controller sichern
		schalter = new LinkedList<Schalter>();
		tco = new TimeCounter(this); //TimeCounter initialisieren
		tc = new Thread(tco); //TimeCounter in Thread verpacken
		time = new JLabel("0:0:0"); //Label fuer Zeitzaehler initialisieren
		moves = new JLabel("Moves: 0"); //Label fuer Moves initialisieren
		mitte = new JPanel(new GridLayout(FIELDS, FIELDS,0,0)); //JPanel in der mitte initialisieren und GridLayout setzen
		neu = new JButton("Neu"); //JButton fuer neues Spiel initialisieren
		JPanel unten = new JPanel(new GridLayout(1,3,10,10)); //JPanel als Container fuer Objekte im unteren Bereich des Fensters
		unten.add(moves); //Objekte zum unteren Container hinzufuegen
		unten.add(time);
		unten.add(neu);
		add(unten, BorderLayout.SOUTH); //Unteren Container zum Frame hinzufuegen
		add(mitte); //Mittleren Container zum Frame hinzufuegen
		init(); //Methode zum Spiel initialisieren aufrufen
		neu.addActionListener(g); //ActionListener setzen(Controller Objekt)
		setDefaultCloseOperation(EXIT_ON_CLOSE); //Damit sich das Programm beim Schliessen des Fensters beendet
		setSize(500,500); //Groesse des Fensters setzen
		setVisible(true); //Fenster sichtbar machen
	}
	/**
	 * Wird aufgerufen wenn ein Schalter gedrueckt wird
	 * Wird vom Schalter aus aufgerufen und ruft die entsprechende Methode im Controller auf
	 * Benoetigt die Nummer des gedrueckten Schalters als Parameter
	 * @param i
	 */
	public void clicked(int i){
		g.clicked(i);
	}
	@Override
	/**
	 * Veraendert den Zustand eines Schalters
	 * Wird vom Controller aufgerufen und ruft die entsprechende Methode im Schalter auf
	 * Benoetigt die Nummer des zu aendernden Schalters als Parameter
	 * @param toSwitch
	 */
	public void switchState(int toSwitch) {
		schalter.get(toSwitch).switchState();
	}
	@Override
	/**
	 * Gibt zurueck ob der spezifizierte Schalter aktiviert ist oder nicht
	 * Fragt den Status beim entsprechenden Schalter ab
	 * Benoetigt den abzufragenden Schalter als Parameter
	 */
	public boolean isOn(int switchOn) {
		return schalter.get(switchOn).isOn();
	}
	@Override
	/**
	 * Initialisiert das Spiel
	 * Erzeugt neue Schalter
	 * und startet den Zeitzaehler neu
	 */
	public void init() {
		tco.stop(); //Stoppt den aktuellen Zeitzaehler
		tco = new TimeCounter(this); //Erzeugt einen neuen Zeitzaehler
		tc = new Thread(tco); //Erzeugt einen neuen Thread
		schalter.clear(); //Loescht alle Schalter aus der Liste
		mitte.removeAll(); //Loescht alle Schalter aus dem Panel in der Mitte
		
		Random random = new Random();
		boolean isOn;
		for(int i=0;i<FIELDS*FIELDS;i++){ //Neue Schleife fuer alle Plaetze im Spielfeld
			isOn=random.nextBoolean();
			schalter.add(new Schalter(isOn, i, this)); //Schalter zur Liste hinzufuegen
			mitte.add(schalter.get(i)); //Schalter zum Panel in der Mitte hinzufuegen
		}
		enableAll(); //Aktiviert wieder alle Elemente in der GUI
		try{
			tc.start(); //Zeitzaehler neu starten
		}catch(Exception e){} //Thread wirft eine Exception, da er nicht ordnungsgemaess beendet wurde
		validate();
	}
	@Override
	/**
	 * Gibt die Laenge des Spielfelds zurueck
	 * Kann auch ueber die Konstante GUI.FIELDS aufgerufen werden
	 */
	public int getField() {
		return FIELDS;
	}
	@Override
	/**
	 * Aktualisiert die Anzahl der aktuellen Zuege
	 * Muss vom Controller augerufen werden
	 * Benoetigt die Anzahl der Zuege als Parameter(String)
	 * @param moves
	 */
	public void refresh(String moves) {
		this.moves.setText(moves);
	}
	/**
	 * Setzt die Zeit neu
	 * Wird vom Zeitzaehler Objekt aufgerufen
	 * Benoetigt die verstrichene Zeit als Parameter(String)
	 * @param time
	 */
	public void setTime(String t){
		time.setText(t);
	}
	/**
	 * Deaktiviert alle Elemente der GUI ausser des "Neu" Buttons
	 * Soll verwendet werden, wenn der Spieler gewinnt
	 */
	public void disableAll(){
		for(Schalter a:schalter) //Fuer alle Schalter
			a.setEnabled(false); //Deaktivieren
		tco.stop(); //Zeitzaehler anhalten
	}
	/**
	 * Aktiviert alle Schalter wieder
	 * Wird bei init() aufgerufen
	 */
	public void enableAll(){
		for(Schalter a:schalter) //Fuer alle Schalter
			a.setEnabled(true); //Aktivieren
	}
}