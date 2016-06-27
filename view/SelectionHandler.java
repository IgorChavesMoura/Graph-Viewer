package view;

import model_control.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.util.*;

public class SelectionHandler extends MouseAdapter{

	private boolean isSelected = false;
	private Panel pan = Panel.getUnit();
	private Vertex u = null;

	public void mouseMoved(MouseEvent e){
		StatusPanel.getUnit().setCursor("[" + e.getX() + "," + e.getY() + "]");
	}

	public void mouseClicked(MouseEvent e){
		GraphMap g = GraphMap.getUnit();
		Vertex x = g.isOn(e.getX(), e.getY());
		if(!GraphMap.getUnit().isAlgorithmRunning()){
			resetSelection();
			if(x != null)
				g.put(x, 2);
		}
		pan.repaint();
	}

	public void resetSelection(){
		GraphMap g = GraphMap.getUnit();
		Map<String, Integer> map = g.getMapSelection();
		Set<String> set = map.keySet();
		for(String x : set)
			g.put(Graph.getUnit().getVertex(x), 1);
	}

	public void mouseDragged(MouseEvent e){
		GraphMap g = GraphMap.getUnit();
		if(!GraphMap.getUnit().isAlgorithmRunning())
			resetSelection();
		if(!e.isAltDown()){
			if(!isSelected){
				u = g.isOn(e.getX(), e.getY());
				isSelected = true;
			}
			if(u != null){
				if(!GraphMap.getUnit().isAlgorithmRunning())
					g.put(u, 2);
				g.setXY(u, e.getX() - Panel.SIZE/2, e.getY() - Panel.SIZE/2);
			}// if
			pan.repaint();
		}
	}

	public void mouseReleased(MouseEvent e){
		isSelected = false;
		u = null;
	}

	// Singleton
	private static SelectionHandler unit;

	private SelectionHandler(){}

	public static SelectionHandler getUnit(){
		if(unit == null)
			unit = new SelectionHandler();
		return unit;
	}

}// SelectionHandler