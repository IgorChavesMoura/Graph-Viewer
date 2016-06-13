package view.panelWest;

import java.awt.*;
import javax.swing.*;

public class ButtonSubset1 extends JPanel{

	private JButton addV = new JButton("add vertice"),
					remV = new JButton("remove vertice"),
					addE = new JButton("add edge"),
					remE = new JButton("remove edge");

	public ButtonSubset1(){
		setLayout(new GridLayout(2,2));

		add(addV);
		add(remV);
		add(addE);
		add(remE);

	}//

}// ButtonSubset1