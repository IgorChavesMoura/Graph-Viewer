package view;

import model_control.*;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class MultiSelection extends MouseAdapter{

	private static MultiSelection unit;
	private MultiSelection(){}
	public static MultiSelection getUnit(){
		if(unit == null)
			unit = new MultiSelection();
		return unit;
	}// getUnit

	private boolean isMoving = false;
	private Ponto buffer, newCenter, vertex;
	private Map<String, Ponto> map;

	public void setMoviment(){

		double x,y;
		x = newCenter.getX() - buffer.getX();
		y = newCenter.getY() - buffer.getY();
		int a1, a2;
		double x1 = (newCenter.getX() - buffer.getX())*(newCenter.getX() - buffer.getX()),
				x2 = (newCenter.getY() - buffer.getY())*(newCenter.getY() - buffer.getY());
		double slope = (double)(x/y),
				alpha = Math.atan(slope),
				radius = Math.sqrt(x1 + x2);

		if(y >= 0){
			for(Vertex a : Graph.getUnit().getV()){
				vertex = map.get(a.getName());
				a1 = (int)(radius*Math.sin(alpha)) + vertex.getX();
				a2 = (int)(radius*Math.cos(alpha)) + vertex.getY();
				GraphMap.getUnit().put(a, new Ponto(a1, a2));
			}
		} else{
			for(Vertex a : Graph.getUnit().getV()){
				vertex = map.get(a.getName());
				a1 = (int)-(radius*Math.sin(alpha)) + vertex.getX();
				a2 = (int)-(radius*Math.cos(alpha)) + vertex.getY();
				GraphMap.getUnit().put(a, new Ponto(a1, a2));
			}
		}

	}// setMoviment

	public boolean isMoving(){
		return isMoving;
	}// isMoving

	public void mouseDragged(MouseEvent e){
		if(!e.isMetaDown() && !e.isAltDown()){
			if(GraphMap.getUnit().isOn(e.getX(), e.getY()) == null && !isMoving){
				GraphMap.getUnit().setMultX(e.getX(), e.getY());
				isMoving = true;
			}// if
			if(isMoving){
				GraphMap.getUnit().setMultY(e.getX(), e.getY());
			}
		}// if
/*
		if(!Graph.getUnit().vertexEmpty()){
			if(!isMoving){
				isMoving = true;
				map = GraphMap.getUnit().getMapCopy();
				buffer = new Ponto(e.getX(), e.getY());
			}// isMoving
			newCenter = new Ponto(e.getX(), e.getY());
			setMoviment();
			Panel.getUnit().repaint();
		}// isMetaDown
*/
		Panel.getUnit().repaint();
	}// mouseDragged

	public void mouseReleased(MouseEvent e){
		isMoving = false;
		GraphMap.getUnit().setMultX(0,0);
		GraphMap.getUnit().setMultY(0,0);
		Panel.getUnit().repaint();
	}// mouseReleased

}// MultiSelection