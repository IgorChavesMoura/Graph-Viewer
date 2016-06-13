package view.panelSouth;

import java.awt.*;
import javax.swing.*;

// OL
import view.Panel;

public class PanelSouth extends Panel{
	private static JLabel south = new JLabel("south Panel");
	public PanelSouth(){

		add(south);
	}

	// DEBUG
	public static void setLabel(String text){
		south.setText(text);
	}

}// Panel