package view.panelWest;

import java.awt.*;
import javax.swing.*;


public class ButtonSubset3 extends JPanel{

	private JButton rand = new JButton("Random Graph"),
		direct = new JButton("Digraph");

	public ButtonSubset3(){
		add(rand);
		add(direct);
	}

}