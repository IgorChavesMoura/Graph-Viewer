package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

import model_control.*;
import thread.*;

public class Frame extends JFrame{

	private Panel pan = Panel.getUnit();
	private StatusPanel sp = StatusPanel.getUnit();
	private PanelButton pb = PanelButton.getUnit();
	private static JLabel text = new JLabel("nothing");

	public static void set(String t){
		text.setText(t);
	}

	public Frame(){
		super("Graphanal");

		Container c = getContentPane();
		c.setLayout(new BorderLayout());

		c.add(pan, BorderLayout.CENTER);
		c.add(sp, BorderLayout.SOUTH);

		pan.addKeyListener(ActionKeyboard.getUnit());
		pan.addMouseListener(SelectionHandler.getUnit());
		pan.addMouseMotionListener(SelectionHandler.getUnit());
		pan.addMouseListener(SelectionEdgeHandler.getUnit());
		pan.addMouseMotionListener(SelectionEdgeHandler.getUnit());
		pan.addMouseListener(MoveAllHandler.getUnit());
		pan.addMouseMotionListener(MoveAllHandler.getUnit());
		pan.addMouseListener(MultiSelection.getUnit());
		pan.addMouseMotionListener(MultiSelection.getUnit());

		menuBar();
		Hand h = new Hand();

		file1.addActionListener(h);
		file2.addActionListener(h);
		file3.addActionListener(h);
		file4.addActionListener(h);
		edit1.addActionListener(h);
		edit2.addActionListener(h);
		edit3.addActionListener(h);
		edit4.addActionListener(h);
		alg1.addActionListener(h);
		alg2.addActionListener(h);
		alg3.addActionListener(h);
		alg4.addActionListener(h);
		alg5.addActionListener(h);
		config1.addActionListener(h);
		config2.addActionListener(h);
		config3.addActionListener(h);

		setVisible(true);
		setLocation(300,100);
		setSize(900,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}// Constructor

		private JMenu file = new JMenu("file"),
				edit = new JMenu("edit"),
				algorithm = new JMenu("algorithm"),
				configuration = new JMenu("configuration");

		private JMenuItem file1 = new JMenuItem("new graph"),
					file2 = new JMenuItem("save graph"),
					file3 = new JMenuItem("load graph"),
					file4 = new JMenuItem("exit"),
					edit1 = new JMenuItem("add vertice"),
					edit2 = new JMenuItem("remove vertice"),
					edit3 = new JMenuItem("add edge"),
					edit4 = new JMenuItem("remove edge"),
					alg1 = new JMenuItem("play BFS"),
					alg2 = new JMenuItem("play DFS"),
					alg2_1 = new JMenuItem("play Prim"),
					alg2_2 = new JMenuItem("play Dijkstra"),
					alg2_3 = new JMenuItem("play Edmonds-Karp"),
					config1 = new JMenuItem("generate random graph"),
					config2 = new JMenuItem("digraph / simple graph"),
					config3 = new JMenuItem("weighted / nonweighted graph"),
					// extra menu
					alg3 = new JMenuItem("show / hide predecessor subgraph"),
					alg4 = new JMenuItem("finish algorithm"),
					alg_ = new JMenuItem("---------------------------------------------------"),
					alg2_ = new JMenuItem("----------------------extras-----------------------"),
					alg5 = new JMenuItem("BFS*");

		private JMenuBar bar = new JMenuBar();

	public void menuBar(){
		file.add(file1); file.add(file2); file.add(file3); file.add(file4);
		edit.add(edit1); edit.add(edit2); edit.add(edit3); edit.add(edit4);
		algorithm.add(alg1); algorithm.add(alg2); algorithm.add(alg2_1); algorithm.add(alg2_2); algorithm.add(alg2_3);
		algorithm.add(alg2_);algorithm.add(alg5);
		alg2_.setEnabled(false);
		if(!Graph.getUnit().isWeighted()){
			alg2_1.setEnabled(false); alg2_2.setEnabled(false); alg2_3.setEnabled(false);
		}
		configuration.add(config1); configuration.add(config2); configuration.add(config3);

		setJMenuBar(bar);
		bar.add(file); bar.add(edit); bar.add(algorithm); bar.add(configuration);
	}

	public void open(){
		final JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int state = fc.showOpenDialog(Frame.this);
		if(state == JFileChooser.APPROVE_OPTION){
			File f = fc.getSelectedFile();

			StatusPanel.getUnit().setAction("loaded: " + f.getName());

			Graph.setUnit(null);
			GraphMap.setUnit(null);

			OpenSave.loadGraph(f);
			OpenSave.loadGraphMap(f);
		}
	}// open

	public void save(){
		final JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int state = fc.showSaveDialog(Frame.this);
		if(state == JFileChooser.APPROVE_OPTION){
			File f = fc.getSelectedFile();

			StatusPanel.getUnit().setAction("saved: " + f.getName());

			OpenSave.saveGraph(f);
			OpenSave.saveGraphMap(f);
		}
	}// save

	private class Hand implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Graph graph = Graph.getUnit();
			//file
			if(e.getSource() == file1){
				Graph.setUnit(null);
				GraphMap.setUnit(null);
				StatusPanel.getUnit().setAction("new graph");
			}
			if(e.getSource() == file2)
				save();
			if(e.getSource() == file3){
				open();
			}
			if(e.getSource() == file4){
				System.exit(0);
			}

			// edit
			if(e.getSource() == edit1){
				String newName = JOptionPane.showInputDialog("Set the vertex name");
				if(newName != null){
					Vertex v = graph.getVertex(newName);
					if(v == null){
						graph.addVertex(newName);
						StatusPanel.getUnit().setAction("added vertex: " + newName);
					} else{
						JOptionPane.showMessageDialog(null, "Vertex " + newName + " already exists");
					}//if-else
				} // cancel
			}// edit1

			if(e.getSource() == edit2){
				String newName = JOptionPane.showInputDialog("Set the vertex name");
				Vertex v = graph.getVertex(newName);
				if( v == null){
					JOptionPane.showMessageDialog(null, newName + " doesn't exist");
				} else{
					graph.removeVertex(v);
					StatusPanel.getUnit().setAction("removed vertex: " + newName);
				}// if-else
			}// edit2

			if(e.getSource() == edit3){
				new FrameEdge(2);
			}// edit3

			if(e.getSource() == edit4){
				new FrameEdge(1);
			}// edit4


			// alg
			if(e.getSource() == alg1){
				String newName = JOptionPane.showInputDialog("Choose the Root");
				if(newName != null){
					Vertex v = graph.getVertex(newName);
					if(v == null){
						JOptionPane.showMessageDialog(null, newName + " doesn't exists");
					} else{
						ThreadAlgorithm t = new ThreadAlgorithm(v, 0);
					}// if-else
				}// newName
			}
			if(e.getSource() == alg2){
				ThreadAlgorithm t = new ThreadAlgorithm();
			}

			if(e.getSource() == alg2 || e.getSource() == alg1 || e.getSource() == alg5){
				alg_.setEnabled(false);
				edit.setEnabled(false); configuration.setEnabled(false);
				algorithm.add(alg_); algorithm.add(alg3); algorithm.add(alg4);
			}
			if(e.getSource() == alg3){
				GraphMap.getUnit().setShowPredecessor(!GraphMap.getUnit().getShowPredecessor());
			}
			if(e.getSource() == alg4){
				algorithm.remove(alg_); algorithm.remove(alg3); algorithm.remove(alg4);
				edit.setEnabled(true); configuration.setEnabled(true);
				GraphMap.getUnit().setAlgorithmRunning(false);
				GraphMap.getUnit().setShowPredecessor(false);
				SelectionHandler.getUnit().resetSelection();
			}
			if(e.getSource() == alg5){
				String newName = JOptionPane.showInputDialog("Choose the Root");
				if(newName != null){
					Vertex v = graph.getVertex(newName);
					if(v == null){
						JOptionPane.showMessageDialog(null, newName + " doesn't exists");
					} else{
						ThreadAlgorithm t = new ThreadAlgorithm(v, 2);
					}// if-else
				}// newName
			}

			// config
			if(e.getSource() == config1){
				Graph.setUnit(null);
				GraphMap.setUnit(null);
				graph = Graph.getUnit();
				GraphMap.getUnit();
				graph.random();
				StatusPanel.getUnit().setAction("new random graph generated");
			}
			if(e.getSource() == config2){
				graph.setDirection(!graph.isDirected());
				if(!graph.isDirected()){
					StatusPanel.getUnit().setAction("set to simple graph");
				} else{
					StatusPanel.getUnit().setAction("set to oriented graph");
				}//if-else
			}
			if(e.getSource() == config3){
				if(!Graph.getUnit().isWeighted()){
					alg1.setEnabled(false); alg2.setEnabled(false);
					alg2_1.setEnabled(true); alg2_2.setEnabled(true); alg2_3.setEnabled(true);
					StatusPanel.getUnit().setAction("weighted graph");
				} else {
					alg1.setEnabled(true); alg2.setEnabled(true);
					alg2_1.setEnabled(false); alg2_2.setEnabled(false); alg2_3.setEnabled(false);
					StatusPanel.getUnit().setAction("nonweighted graph");
				}
				graph.setWeighted(!graph.isWeighted());
			}
			pan.repaint();
			Graph g = Graph.getUnit();
			g.showAll();
		}// actionPerformed
	}// private class

}// Frame