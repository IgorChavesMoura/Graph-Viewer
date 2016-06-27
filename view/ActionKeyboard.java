package view;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class ActionKeyboard extends KeyAdapter{
	private static ActionKeyboard unit;
	private ActionKeyboard(){}
	public static ActionKeyboard getUnit(){
		if(unit == null)
			unit = new ActionKeyboard();
		return unit;
	}// constructor

	public void keyTyped(KeyEvent e){
		Frame.set("" + e.getKeyChar());
	}// keyPressed

	public void keyPressed(KeyEvent e){
		System.out.println("HUEHUEHUE");
	}
	public void keyReleased(KeyEvent e){
		System.out.println("HUEHUEHUE");
	}
}// ActionKeyboard