package view;

import model_control.*;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class MoveAllHandler extends MouseAdapter{

	private static MoveAllHandler unit;
	private MoveAllHandler(){}
	public static MoveAllHandler getUnit(){
		if(unit == null)
			unit = new MoveAllHandler();
		return unit;
	}

	private boolean isMoving = false;
	private Ponto buffer, newCenter, vertex;
	private Map<String, Ponto> map = null;

	public void setMoviment(){

		double x,y;
		x = newCenter.getX() - buffer.getX();
		y = newCenter.getY() - buffer.getY();
		int a1=0, a2=0;
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
		}// if-else

	}// setMoviment

	public void mouseDragged(MouseEvent e){

		if(e.isMetaDown() && !Graph.getUnit().vertexEmpty()){
			if(!isMoving){
				isMoving = true;
				map = GraphMap.getUnit().getMapCopy();
				buffer = new Ponto(e.getX(), e.getY());
			}// isMoving
			newCenter = new Ponto(e.getX(), e.getY());
			if(map!=null)
				setMoviment();
			Panel.getUnit().repaint();
		}// isMetaDown
	}// mouseDragged

	public void mouseReleased(MouseEvent e){
		isMoving = false;
	}// mouseReleased



}
