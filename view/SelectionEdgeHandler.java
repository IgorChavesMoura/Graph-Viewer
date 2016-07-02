package view;

import model_control.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class SelectionEdgeHandler extends MouseAdapter{

	// *** member variables *** //
	private boolean isDragging = false;
	private Vertex u = null, v = null;

	// *** Edge Buffer ***
	private int ex1 = 0, ex2 = 0, ey1 = 0, ey2 = 0;
	public int getEx1(){return ex1;}
	public int getEx2(){return ex2;}
	public int getEy1(){return ey1;}
	public int getEy2(){return ey2;}

	public Vertex getBuffer(){
		return u;
	}

	public void setEdgeBuffer(int a, int b, int c, int d){
		ex1 = a;
		ey1 = b;
		ex2 = c;
		ey2 = d;
	}// setEdgeBuffer

	// *** Mouse Events ***
	public void mouseDragged(MouseEvent e){
		GraphMap g = GraphMap.getUnit();
		Map<String, Ponto> map = g.getMap();
		Ponto p;
		Vertex loop = null;
		if(e.isAltDown() && !GraphMap.getUnit().isAlgorithmRunning()){
			if(!isDragging){
				u = g.isOn(e.getX(), e.getY());
				isDragging = true;
			}// is not dragging
			if(u != null){
				p = map.get(u.getName());
				setEdgeBuffer(p.getX() + Panel.SIZE/2, p.getY() + Panel.SIZE/2, e.getX(), e.getY());

				loop = g.isOn(e.getX(), e.getY());
				if(loop != null && loop.getName().equals(u.getName())){
					Panel.getUnit().setIsLoop(true);
				}else{
					Panel.getUnit().setIsLoop(false);
				}// if cursor is over the edge's tail
			}// null
		}// altDown
		Panel.getUnit().repaint();
	}// mouseDragged

	public void mouseReleased(MouseEvent e){
		GraphMap g = GraphMap.getUnit();
		Graph g2 = Graph.getUnit();
		if(e.isAltDown() && !GraphMap.getUnit().isAlgorithmRunning()){
			if( u != null){
				v = g.isOn(e.getX(), e.getY());
				if(v == null)
					setEdgeBuffer(0,0,0,0);
				else{
					if(!g2.isWeighted()){
						g2.addEdge(u, v);
					} else {
						setWeight(u, v);
					}// if-else
				}// if-else
				Panel.getUnit().repaint();
			}
			// Reset
			isDragging = false;
			setEdgeBuffer(0,0,0,0);
			u = null;
			v = null;
			//DEBUG
			Graph gra = Graph.getUnit();
			gra.showAll();
		}// isAltDown
	}// mouseReleased

	public void setWeight(Vertex x, Vertex y){
		Graph g2 = Graph.getUnit();
		String a = JOptionPane.showInputDialog("Set the weight");
		int b;

		try{
			b = getInt(a);
			g2.addEdge(x,y,b);
		} catch (NumberFormatException e){}
	}// setWeight

	public int getInt(String a) throws NumberFormatException{
		try{
			return Integer.valueOf(a);
		} catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "unsuported value");
			throw new NumberFormatException();
		}
	}

	// *** Singleton ***
	private static SelectionEdgeHandler unit;

	private SelectionEdgeHandler(){}

	public static SelectionEdgeHandler getUnit(){
		if(unit == null)
			unit = new SelectionEdgeHandler();
		return unit;
	}// SelectionEdgeHandler

}// SelectionEdgeHandler