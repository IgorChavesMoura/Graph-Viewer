package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

import model_control.*;
import thread.*;

public class Frame extends JFrame{

	private static Frame unit;
	public static Frame getUnit(){
		if(unit == null)
			unit = new Frame();
		return unit;
	}// getUnit


	private Panel pan = Panel.getUnit();
	private StatusPanel sp = StatusPanel.getUnit();
	private PanelLeft pl = PanelLeft.getUnit();

	private Frame(){
		super("Grapho - LILA");

		Container c = getContentPane();
		c.setLayout(new BorderLayout());                
                
		c.add(pan, BorderLayout.CENTER);
		c.add(sp, BorderLayout.SOUTH);
		c.add(pl, BorderLayout.WEST);
                setFocusable(true);
                
                addKeyListener(ActionKeyboard.getUnit());

		pan.addMouseListener(SelectionHandler.getUnit());
		pan.addMouseMotionListener(SelectionHandler.getUnit());
		pan.addMouseListener(SelectionEdgeHandler.getUnit());
		pan.addMouseMotionListener(SelectionEdgeHandler.getUnit());
		pan.addMouseListener(MoveAllHandler.getUnit());
		pan.addMouseMotionListener(MoveAllHandler.getUnit());
/*                
		pan.addMouseListener(MultiSelection.getUnit());
		pan.addMouseMotionListener(MultiSelection.getUnit());
  */              
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
		alg2_1.addActionListener(h);
		alg2_2.addActionListener(h);
                alg2_3.addActionListener(h);
		alg3.addActionListener(h);
		alg4.addActionListener(h);
		config1.addActionListener(h);
		config2.addActionListener(h);
		config3.addActionListener(h);
		config4.addActionListener(h);

		setVisible(true);
		setLocation(300,100);
		setSize(900,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}// Constructor
        
        public void initKeyboard(){
            addKeyListener(ActionKeyboard.getUnit());            
        }

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
					config4 = new JMenuItem("set algorithm speed"),
					// extra menu
					alg3 = new JMenuItem("show / hide predecessor subgraph"),
					alg4 = new JMenuItem("finish algorithm");

		private JMenuBar bar = new JMenuBar();

	public void menuBar(){
		file.add(file1); file.add(file2); file.add(file3); file.add(file4);
		edit.add(edit1); edit.add(edit2); edit.add(edit3); edit.add(edit4);
		algorithm.add(alg1); algorithm.add(alg2); algorithm.add(alg2_1); algorithm.add(alg2_2); algorithm.add(alg2_3);

		if(!Graph.getUnit().isWeighted()){
			alg2_1.setEnabled(false); alg2_2.setEnabled(false); alg2_3.setEnabled(false);
		}
		configuration.add(config1); configuration.add(config2); configuration.add(config3); configuration.add(config4);

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
//			OpenSave.loadGraphMap(f);
			Graph.getUnit().setSaved(true);

			disableAlgorithms();
                        PanelLeft.getUnit().disableButtons(!Graph.getUnit().isWeighted());
		}
	}// open

	public void save(){
		final JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int state = fc.showSaveDialog(unit);
		if(state == JFileChooser.APPROVE_OPTION){
			File f = fc.getSelectedFile();

			StatusPanel.getUnit().setAction("saved: " + f.getName());

			OpenSave.saveGraph(f);
//			OpenSave.saveGraphMap(f);
			Graph.getUnit().setSaved(true);
		}
	}// save

	public void fileNewFrame(){
		if(!Graph.getUnit().isSaved()){
			new SaveFrame(0);
		} else {
			fileNew();
		}
	}

	public void prim(){
		String newName = JOptionPane.showInputDialog("Choose the Root");
		if(newName != null){
			Vertex v = Graph.getUnit().getVertex(newName);
			if(v == null){
				JOptionPane.showMessageDialog(null, newName + " doesn't exists");
			} else{
				t = new ThreadAlgorithm(v, 3);
				disableButtons();
			}// if-else
		}// newName
	}

	public void dijkstra(){
		String newName = JOptionPane.showInputDialog("Choose the Root");
		if(newName != null){
			Vertex v = Graph.getUnit().getVertex(newName);
			if(v == null){
				JOptionPane.showMessageDialog(null, newName + " doesn't exists");
			} else{
				t = new ThreadAlgorithm(v, 4);
				disableButtons();
			}// if-else
		}// newName
	}

	public void fileNew(){
		Graph.setUnit(null);
		GraphMap.setUnit(null);
		StatusPanel.getUnit().setAction("new graph");
	}

	public void configRandomFrame(){
		if(!Graph.getUnit().isSaved()){
			new SaveFrame(1);
		} else {
			configRandom();
		}
	}

	public void configRandom(){
		Graph.setUnit(null);
		GraphMap.setUnit(null);
		GraphMap.getUnit();
		Graph.getUnit().random();
		StatusPanel.getUnit().setAction("new random graph generated");
	}

	public void addVertex(){
		Graph graph = Graph.getUnit();
		String newName = JOptionPane.showInputDialog("Set the vertex name");
		newName = removeSpace(newName);
		if(newName != null){

			Vertex v = graph.getVertex(newName);
			if(v == null){
				graph.addVertex(newName);
				StatusPanel.getUnit().setAction("added vertex: " + newName);
			} else{
				JOptionPane.showMessageDialog(null, "Vertex " + newName + " already exists");
			}//if-else
		}
	}//addVertex
        
        public boolean validNameVertex(String s){
            Graph g = Graph.getUnit();
            String t = removeSpace(s);
            return s != null && g.getVertex(s) == null
                    && t != null && !t.equals("");
        }

	public void removeVertex(){
		Graph graph = Graph.getUnit();
		String newName = JOptionPane.showInputDialog("Set the vertex name");

		if(newName != null)
			newName = removeSpace(newName);

		Vertex v = graph.getVertex(newName);
		if( v == null){
			if(newName != null){
				if(newName.equals(""))
					JOptionPane.showMessageDialog(null, "Please give a non-empty name");
				else
					JOptionPane.showMessageDialog(null, newName + " doesn't exist");
			}
		} else{
			graph.removeVertex(v);
			StatusPanel.getUnit().setAction("removed vertex: " + newName);
		}// if-else
	}

	public String removeSpace(String x){
		if(x == null)
			return null;
		int index = 0;
		System.out.println(x);
		while(index < x.length() && x.charAt(index) == ' ')
			index++;
		if (index == x.length())
			return null;
		System.out.println(x.substring(index));
		return x.substring(index);
	}

	public void disableButtons(){
		edit.setEnabled(false); configuration.setEnabled(false);
		algorithm.add(alg3); algorithm.add(alg4);
	}

	public void configSpeed(){
		String speed = JOptionPane.showInputDialog("Set the speed (0 - 5000)");
		int velocity;

		try{
			velocity = Integer.parseInt(speed);
			ThreadPaint.getUnit().setSpeed(velocity);
			if(velocity < 0 || velocity > 5000)
				throw new NumberFormatException();
		} catch (NumberFormatException ex){
			JOptionPane.showMessageDialog(null, "invalid input");
		}

	}

        public void bfs(){
            Graph graph = Graph.getUnit();
            String newName = JOptionPane.showInputDialog("Choose the Root");
            if(newName != null){
                    Vertex v = graph.getVertex(newName);
                    if(v == null){
                            JOptionPane.showMessageDialog(null, newName + " doesn't exists");
                    } else{
                            t = new ThreadAlgorithm(v, 0);
                            disableButtons();
                    }// if-else
            }// newName            
        }
        
        public void dfs(){
            t = new ThreadAlgorithm();
            disableButtons();            
        }
        
        public void flow(Vertex u, Vertex v){
            t = new ThreadAlgorithm(u, v);
        }
        
	private ThreadAlgorithm t;
        
        public void threadWait(){
            try{
            t.wait();
            } catch (InterruptedException wx){
                JOptionPane.showMessageDialog(null, "error while thread waiting");
            }
        }

	private class Hand implements ActionListener{
                @Override
		public void actionPerformed(ActionEvent e){
			Graph graph = Graph.getUnit();
			//file
			if(e.getSource() == file1){
				fileNewFrame();
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
				addVertex();
			}// edit1

			if(e.getSource() == edit2){
				removeVertex();
			}// edit2

			if(e.getSource() == edit3){
				new FrameEdge(1);
			}// edit3

			if(e.getSource() == edit4){
				new FrameEdge(2);
			}// edit4


			// alg
			if(e.getSource() == alg1){
                            bfs();
			}
			if(e.getSource() == alg2){
                            dfs();
			}

			if(e.getSource() == alg2_1)
				prim();

			if(e.getSource() == alg2_2)
				dijkstra();
                        if(e.getSource() == alg2_3){
                            disableButtons();
                            new FrameEdge(3);   
                        }

			if(e.getSource() == alg3){
				GraphMap.getUnit().setShowPredecessor(!GraphMap.getUnit().getShowPredecessor());
			}
			if(e.getSource() == alg4){
				algorithm.remove(alg3); algorithm.remove(alg4);
				edit.setEnabled(true); configuration.setEnabled(true);
				GraphMap.getUnit().setAlgorithmRunning(false);
                                ResidualGraph.getUnit().setRunning(false);
				GraphMap.getUnit().setShowPredecessor(false);
				SelectionHandler.getUnit().resetSelection();

				if(t.isAlive()){
					Graph.getUnit().setInterruption(true);
                                        ResidualGraph.getUnit().setInterruption(true);
				}

			}

			// config
			if(e.getSource() == config1){
				configRandomFrame();
			}
			if(e.getSource() == config2){
                            digraph();
			}
			if(e.getSource() == config3){
                            weighted();
			}
			if(e.getSource() == config4){
				configSpeed();
			}

			pan.repaint();
			Graph g = Graph.getUnit();
			g.showAll();

			if(e.getSource() != file2 || e.getSource() != file3)
				Graph.getUnit().setSaved(false);


		}// actionPerformed
	}// private class

	public void disableAlgorithms(){
		if(Graph.getUnit().isWeighted()){
			alg1.setEnabled(false); alg2.setEnabled(false);
			alg2_1.setEnabled(true); alg2_2.setEnabled(true); alg2_3.setEnabled(true);
			StatusPanel.getUnit().setAction("weighted graph");
		} else {
			alg1.setEnabled(true); alg2.setEnabled(true);
			alg2_1.setEnabled(false); alg2_2.setEnabled(false); alg2_3.setEnabled(false);
			StatusPanel.getUnit().setAction("nonweighted graph");
		}
	}

        public void digraph(){
            Graph graph = Graph.getUnit();
            if(graph.isDirected()){
                StatusPanel.getUnit().setAction("set to simple graph");
                graph.orientedSimple(!graph.isDirected());
            } else{
                StatusPanel.getUnit().setAction("set to oriented graph");
                graph.simpleOriented(!graph.isDirected());
            }//if-else            
        }
        
        public void weighted(){
            Graph graph = Graph.getUnit();
            graph.setWeighted(!graph.isWeighted());
            disableAlgorithms();            
        }
        
}// Frame