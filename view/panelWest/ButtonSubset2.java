package view.panelWest;

import java.awt.*;
import javax.swing.*;


public class ButtonSubset2 extends JPanel{
	private JButton bfs = new JButton("BFS"),
		dfs = new JButton("DFS");

	public ButtonSubset2(){
		setLayout(new GridLayout(2,0));
		add(bfs);
		add(dfs);
	}
}// ButtonSubset2