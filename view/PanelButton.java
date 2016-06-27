package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PanelButton extends JPanel{

	private static PanelButton unit;
	private JButton buttonBox[] = new JButton[8];
	private String labelBox[] = {"add vertice", "remove vertice", "add edge", "remove edge", "play BFS",
									"play DFS", "get random graph", "graph / digraph"};

	public static PanelButton getUnit(){
		if(unit == null)
			unit = new PanelButton();
		return unit;
	}// getUnit

	private PanelButton(){
		setBackground(Color.LIGHT_GRAY);

		for(int index = 0 ; index < labelBox.length ; index++)
			buttonBox[index] = new JButton(labelBox[index]);

		GroupLayout gl =new GroupLayout(this);
		gl.setVerticalGroup(gl.createSequentialGroup()
			.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE))
			.addComponent(buttonBox[0])
			.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE))
			.addComponent(buttonBox[1])
			.addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING))
		);

	}

}