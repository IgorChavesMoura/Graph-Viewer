package view;

import model.GraphMap;
import view.panel.PanelStatus;
import view.panel.PanelMenu;
import view.input.ActionKeyboard;
import view.input.SelectionHandler;
import view.input.MoveAllHandler;
import view.input.SelectionEdgeHandler;
import view.panel.PanelLeft;
import view.panel.Panel;
import control.Algorithm;
import control.GraphOperation;
import control.OpenSave;
import model.Graph;
import control.ResidualGraph;
import model.Vertex;
import java.awt.*;
import javax.swing.*;
import java.io.*;

import thread.*;
import view.panel.PanelLog;

public class Frame extends JFrame{
	
    public static Frame getUnit(){
        if(unit == null)
            unit = new Frame();
        return unit;
    }// getUnit

    public static Image 
        createFaviIcon (){ 
        Image faviIcon = Toolkit.getDefaultToolkit().getImage("resources/iconProgram.png");  
        return faviIcon;
    }

        
    private Frame(){
            super("Grapho - Beta Version");
            setIconImage(createFaviIcon());

            Container c = getContentPane();
            c.setLayout(new BorderLayout());                

            c.add(pan, BorderLayout.CENTER);
            c.add(sp, BorderLayout.SOUTH);
            c.add(pl, BorderLayout.WEST);
            c.add(dp, BorderLayout.EAST);
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
            //menuBar();
            setJMenuBar(PanelMenu.getUnit());

            setVisible(true);
            setLocation(300,100);
            setSize(900,500);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }// Constructor

    public void open(){
            final JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int state = fc.showOpenDialog(Frame.this);
            if(state == JFileChooser.APPROVE_OPTION){
                    File f = fc.getSelectedFile();

                    PanelStatus.getUnit().setAction("loaded: " + f.getName());

                    Graph.setUnit(null);
                    GraphMap.setUnit(null);

                    OpenSave.loadGraph(f);
                    OpenSave.getUnit().setSaved(true);

                    PanelMenu.getUnit().disableAlgorithms();
                    interruptAlgorithm();

                    PanelLeft.getUnit().disableButtons();
            }
    }// open

    public void save(){
            final JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int state = fc.showSaveDialog(unit);
            if(state == JFileChooser.APPROVE_OPTION){
                    File f = fc.getSelectedFile();

                    PanelStatus.getUnit().setAction("saved: " + f.getName());

                    OpenSave.saveGraph(f);
                    OpenSave.getUnit().setSaved(true);
            }
    }// save

    public void fileNewFrame(){
            if(!OpenSave.getUnit().isSaved()){
                    new FrameSave(0);
            } else {
                    new FrameNewGraph();
            }
    }// fileNewFrame

    public void prim(){
            String newName = JOptionPane.showInputDialog("Choose the Root");
            if(newName != null){
                    Vertex v = Graph.getUnit().getVertex(newName);
                    if(v == null){
                            JOptionPane.showMessageDialog(null, newName + " doesn't exists");
                    } else{
                            t = new ThreadAlgorithm(v, 3);
                            PanelMenu.getUnit().disableButtons();
                            PanelLeft.getUnit().disableButtonsAlgorithm(false);
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
                            PanelMenu.getUnit().disableButtons();
                            PanelLeft.getUnit().disableButtonsAlgorithm(false);
                    }// if-else
            }// newName
    }

    public void fileNew(){
            PanelMenu.getUnit().enableButtons();
            Graph.setUnit(null);
            GraphMap.setUnit(null);
            PanelStatus.getUnit().setAction("new graph");
    }

    public void configRandomFrame(){
            if(!OpenSave.getUnit().isSaved()){
                    new FrameSave(1);
            } else {
                    new FrameRandGraph();
            }
    }

    public void configRandom(boolean oriented, boolean weighted, boolean randomWeight){
            Graph.setUnit(null);
            GraphMap.setUnit(null);

            Graph.getUnit();
            GraphMap.getUnit();

            Graph.getUnit().setDirection(oriented);
            Graph.getUnit().setWeighted(weighted);
            Graph.getUnit().setRandWeightValues(randomWeight);              

            GraphOperation.random();


            PanelLeft.getUnit().disableButtons();


            interruptAlgorithm();
            PanelStatus.getUnit().setAction("new random graph generated");
    }

    public void addVertex(){
            Graph graph = Graph.getUnit();
            String newName = JOptionPane.showInputDialog("Set the vertex name");
            newName = removeSpace(newName);
            if(newName != null){

                    Vertex v = graph.getVertex(newName);
                    if(v == null){
                            graph.addVertex(newName);
                            PanelStatus.getUnit().setAction("added vertex: " + newName);
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
                    PanelStatus.getUnit().setAction("removed vertex: " + newName);
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

    public void configSpeed(){
        new FrameSpeed();
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
                        PanelMenu.getUnit().disableButtons();
                        PanelLeft.getUnit().disableButtonsAlgorithm(false);
                }// if-else
        }// newName            
    }

    public void dfs(){
        t = new ThreadAlgorithm();
        PanelLeft.getUnit().disableButtonsAlgorithm(false);
        PanelMenu.getUnit().disableButtons();            
    }

    public void flow(Vertex u, Vertex v){
        PanelMenu.getUnit().disableButtons();
        t = new ThreadAlgorithm(u, v);
        PanelLeft.getUnit().disableButtonsAlgorithm(false);
    }

    public void digraph(){
        Graph graph = Graph.getUnit();
        if(graph.isDirected()){
            PanelStatus.getUnit().setAction("set to simple graph");
            GraphOperation.orientedSimple(!graph.isDirected());
        } else{
            PanelStatus.getUnit().setAction("set to oriented graph");
            GraphOperation.simpleOriented(!graph.isDirected());
        }//if-else            
    }

    public void weighted(){
        Graph graph = Graph.getUnit();
        graph.setWeighted(!graph.isWeighted());
        PanelMenu.getUnit().disableAlgorithms();
        PanelLeft.getUnit().disableButtons();
    }

    public void interruptAlgorithm(){
        if(t != null && t.isAlive()){
            Algorithm.getUnit().setInterruption(true);
            ResidualGraph.getUnit().setInterruption(true);
        }            
    }
        
    private static Frame unit;
    private Panel pan = Panel.getUnit();
    private PanelStatus sp = PanelStatus.getUnit();
    private PanelLeft pl = PanelLeft.getUnit();        
    private PanelLog dp = PanelLog.getUnit();  
    private ThreadAlgorithm t;
    
}// Frame