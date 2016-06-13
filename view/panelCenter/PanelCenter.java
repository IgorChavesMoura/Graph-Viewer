package view.panelCenter;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

// OL
import view.Panel;
import model.*;
import view.panelSouth.PanelSouth;

public class PanelCenter extends Panel{

	private Graph graph = Graph.getUnit();
	private static final int SIZE = 50;
	private int bufferX1=0, bufferY1=0, bufferX2 = 0, bufferY2 = 0;

	public PanelCenter(){
		JLabel center = new JLabel("center Panel");
		add(center);

		MouseHandler handler = new MouseHandler();
		addMouseMotionListener(handler);
	}// Constructor

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		ArrayList<Vertex> set = graph.getSet();
		for(Vertex a: set){
			g.fillOval(a.getX(), a.getY(), SIZE, SIZE);
			g.setColor(Color.WHITE);

			g.drawString("" + a.getId(), a.getX() + SIZE/2, a.getY() + SIZE/2);
			g.setColor(Color.BLACK);

			ArrayList<Vertex> adj = a.getAdj();
			for(Vertex b : adj)
				g.drawLine(a.getX() + SIZE/2, a.getY() + SIZE/2, b.getX() + SIZE/2, b.getY() + SIZE/2);
			g.drawLine(bufferX1, bufferY1, bufferX2, bufferY2);
		}
	}// paintComponent

	private class MouseHandler extends MouseAdapter{
		private boolean drag = false;
		Vertex v;
		public void mouseMoved(MouseEvent event){
			PanelSouth.setLabel("[" + event.getX() + "-" + event.getY() + "]");
		}

		public void mouseDragged(MouseEvent e){
			if(!drag)
				v = graph.isOn(e.getX(), e.getY());
			if(v != null){
				drag = true;
				if(e.isAltDown()){
					PanelSouth.setLabel("[" + e.getX() + "-" + e.getY() + "] Middle");
					setBuffer(v.getX() + 25, v.getY() + 25, e.getX(), e.getY());
				}else{
					v.setXY(e.getX() - 25, e.getY() - 25);
				}// else
				repaint();
			}// if
			if(e.getButton() == e.MOUSE_RELEASED)
				drag = false;
		}// mouseDragged

		public void mouseReleased(MouseEvent e){
			drag = false;
			PanelSouth.setLabel("Mouse Released");
		}

	}



	public void setBuffer(int x1, int y1, int x2, int y2){
		bufferX1 = x1;
		bufferY1 = y1;
		bufferX2 = x2;
		bufferY2 = y2;
	}

}// Panel