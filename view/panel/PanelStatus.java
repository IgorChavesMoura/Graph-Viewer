package view.panel;

import java.awt.*;
import javax.swing.*;

public class PanelStatus extends JPanel{

	private JLabel info = new JLabel("info"), action = new JLabel("action"), cursor = new JLabel("cursor");

	// Singleton
	private static PanelStatus unit;
	public static PanelStatus getUnit(){
		if(unit == null)
			unit = new PanelStatus();
		return unit;
	} // getUnit
	private PanelStatus(){
		setLayout(new GridLayout(0, 3));
                setFocusable(true);
		add(info);
		add(action);
		add(cursor);
	}
	// Singleton

	public void setInfo(String a){
		info.setText(a);
	}// setInfo

	public void setAction(String a){
		action.setText(a);
	}// setAction

	public void setCursor(String a){
		cursor.setText(a);
	}// setCursor


}// PanelStatus