package view.panelWest;

import java.awt.*;
import javax.swing.*;

// OL
import view.Panel;

public class PanelWest extends Panel{

	private static PanelWest unit;

	public static PanelWest getUnit(){
		if(unit == null)
			unit = new PanelWest();
		return unit;
	}

	private PanelWest(){
		setLayout(new GridLayout(3, 1));
		add(PWSU.getUnit("Editor"));
		add(new PWSM());
		add(new ButtonSubset0());
	}// Constructor

	public void setButton(int button){
		remove(2);
		switch(button){
			case 1 : add(new ButtonSubset1());
				break;
			case 2 : add(new ButtonSubset2());
				break;
			case 3 : add(new ButtonSubset3());
				break;
		}
	}

}// Panel