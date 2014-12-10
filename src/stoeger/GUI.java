package stoeger;

import javax.swing.*;

/**
 * GUI fuer LightsOut Spiel
 * 
 * @author michael
 *
 */
public class GUI extends JFrame implements LightsGame{
	private Game g;
	public final static int FIELDS = 5;
	private JLabel time, moves;
	private GUI(){}
	public GUI (Game g){
		this.g=g;
	}
	public void clicked(int i){
		g.clicked(i);
	}
	@Override
	public void switchState(int toSwitch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isOn(int switchOn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getField() {
		return FIELDS;
	}

	@Override
	public void refresh(String moves) {
		// TODO Auto-generated method stub
		
	}
	
}