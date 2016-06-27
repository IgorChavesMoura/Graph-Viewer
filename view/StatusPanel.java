package view;

import java.awt.*;
import javax.swing.*;

public class StatusPanel extends JPanel{

	private JLabel info = new JLabel("info"), action = new JLabel("action"), cursor = new JLabel("cursor");

	// Singleton
	private static StatusPanel unit;
	public static StatusPanel getUnit(){
		if(unit == null)
			unit = new StatusPanel();
		return unit;
	} // getUnit
	private StatusPanel(){
		setLayout(new GridLayout(0, 3));
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


}// StatusPanel