package view;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import java.util.ArrayList;
import model_control.*;

/*
	shift + a: add vertex
	shift + e: add edge / click
*/

public class ActionKeyboard extends KeyAdapter{
	private static ActionKeyboard unit;
	private ActionKeyboard(){}
	public static ActionKeyboard getUnit(){
		if(unit == null)
			unit = new ActionKeyboard();
		return unit;
	}// constructor

	String modifier;
	char key;
	String multiSelectionBuffer;

	public String getMultiSelectionBuffer(){
		return multiSelectionBuffer;
	}

	@Override
	public void keyTyped(KeyEvent e){
		System.out.println("VIADO PORRA HORA DO CHOU");
		key = e.getKeyChar();
		modifier = KeyEvent.getKeyModifiersText( e.getModifiers());
	}// keyPressed

        public String toString(){
            return "é um teclado porra";
        }
        
	public void keyPressed(KeyEvent e){
		key = e.getKeyChar();
		modifier = KeyEvent.getKeyModifiersText( e.getModifiers());
		multiSelectionBuffer = KeyEvent.getKeyModifiersText( e.getModifiers());

		if(modifier.equals("Shift")){
			MultiSelection.getUnit().setMulti(true);
			if(key == 'A'){
				Frame.getUnit().addVertex();
			}
			if(key == 'E'){
				addClick();
			}// if
		}// if
		Panel.getUnit().repaint();
	}

	public void addClick(){
		ArrayList<Vertex> box = MultiSelection.getUnit().getMultiBox();
		Graph graph = Graph.getUnit();
		for(Vertex x : box){
			for(Vertex y : box){
				if (!x.getName().equals(y.getName())){
					graph.addEdge(x, y);
				}// if
			}// for
		}// for
	}

	public void keyReleased(KeyEvent e){
		key = e.getKeyChar();
		modifier = KeyEvent.getKeyModifiersText( e.getModifiers());
		multiSelectionBuffer = null;
		MultiSelection.getUnit().setMulti(false);
	}
}// ActionKeyboard