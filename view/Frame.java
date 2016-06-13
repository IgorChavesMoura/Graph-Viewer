package view;

import java.awt.*;
import javax.swing.*;

// own library
import view.panelWest.PanelWest;
import view.panelSouth.PanelSouth;
import view.panelEast.PanelEast;
import view.panelCenter.PanelCenter;

public class Frame extends JFrame{

	private Panel west = PanelWest.getUnit();
	private Panel south = new PanelSouth();
	private Panel east = new PanelEast();
	private Panel center = new PanelCenter();

	public Frame(){
		super("Graphal - Graph Algorithms Editor - LILA Enterprise");
		Container c = getContentPane();
		c.setLayout(new BorderLayout());

		c.add(west, BorderLayout.WEST);
		c.add(south, BorderLayout.SOUTH);
		c.add(east, BorderLayout.EAST);
		c.add(center, BorderLayout.CENTER);

		menuBar();

		setVisible(true);
		setSize(500,500);
		setLocation(500,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}// Frame

	public void menuBar(){
		JMenu file = new JMenu("file"),
				edit = new JMenu("edit"),
				algorithm = new JMenu("algorithm"),
				configuration = new JMenu("configuration"),
				other = new JMenu("other tools");

		JMenuItem file1 = new JMenuItem("new graph"),
					file2 = new JMenuItem("save graph"),
					file3 = new JMenuItem("load graph"),
					file4 = new JMenuItem("exit"),
					edit1 = new JMenuItem("add vertice"),
					edit2 = new JMenuItem("remove vertice"),
					edit3 = new JMenuItem("add edge"),
					edit4 = new JMenuItem("remove edge"),
					alg1 = new JMenuItem("play BFS"),
					alg2 = new JMenuItem("play DFS"),
					config1 = new JMenuItem("generate random graph"),
					config2 = new JMenuItem("digraph");
		file.add(file1); file.add(file2); file.add(file3); file.add(file4);
		edit.add(edit1); edit.add(edit2); edit.add(edit3); edit.add(edit4);
		algorithm.add(alg1); algorithm.add(alg2);
		configuration.add(config1); configuration.add(config2);

		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);
		bar.add(file); bar.add(edit); bar.add(algorithm); bar.add(configuration); bar.add(other);
	}

}// Frame