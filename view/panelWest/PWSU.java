package view.panelWest;

import java.awt.*;
import javax.swing.*;

public class PWSU extends JPanel{
	private static PWSU unit;
	private JLabel label;

	public static PWSU getUnit(String label){
		if(unit == null)
			unit = new PWSU(label);
		return unit;
	}// getUnit

	private PWSU(String label){
		this.label = new JLabel(label);
		setBackground(Color.GRAY);
		setLayout(new FlowLayout());
		add(this.label);
	}// PWSU

	public void setText(String label){
		this.label.setText(label);
	}

}// PWSU