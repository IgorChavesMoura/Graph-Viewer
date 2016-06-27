package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

import model_control.*;

public class Panel extends JPanel{

	private static Panel unit;
	private SelectionEdgeHandler seh = SelectionEdgeHandler.getUnit();
	private boolean isLoop = false;

	public static final int SIZE = 50;

	public static Panel getUnit(){
		if(unit == null)
			unit = new Panel();
		return unit;
	}// getUnit

	private Panel(){
		setBackground(new Color(88, 188, 120));
	}

	public void setIsLoop(boolean b){
		isLoop = b;
	}// setIsLoop

	public void drawLoop(Graphics g, Vertex x){
		if(x != null){
			int radius = 50;
			Ponto p = GraphMap.getUnit().get(x);
			g.drawOval(p.getX() + SIZE/2, p.getY() - SIZE/2, radius, radius);
		}
	}

	public void drawParallelEdge(Graphics g, int x1, int y1, int x2, int y2){
		g.drawLine(x1, y1 - 10, x2, y2 - 10);
	}

	public void drawOrientedLoop(Graphics g, Vertex x){
		drawLoop(g, x);
		Ponto p = GraphMap.getUnit().get(x);
		drawTriangle(g, p.getX() + SIZE, p.getY(), p.getX() - 50 + SIZE , p.getY());
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		GraphMap gMap = GraphMap.getUnit();
		Map<String, Ponto> map = gMap.getMap();

		Set<String> set = map.keySet();
		Ponto p, q;

		// Edge Buffer
		if(!isLoop){
			g.drawLine(seh.getEx1(), seh.getEy1(),seh.getEx2(),seh.getEy2());
		} else {
			drawLoop(g, seh.getBuffer());
		}// if-else

		// Edge Draw
		if(!GraphMap.getUnit().getShowPredecessor()){
			g.setColor(Color.BLACK);
			for(String x : set){
				drawEdge(g, Graph.getUnit().getVertex(x));
			}
		}// ifi

		// Predecessor
		if(GraphMap.getUnit().getShowPredecessor())
			for(String x : set)
				if (Graph.getUnit().getVertex(x).getPredecessor() != null)
					drawEdgePredecessor(g, Graph.getUnit().getVertex(x));

		// Vertex Draw
		for(String x : set)
			drawVertex(g, Graph.getUnit().getVertex(x));

		// Selection Box
		p = gMap.getMultX();
		q = gMap.getMultY();
		if(MultiSelection.getUnit().isMoving()){
			g.setColor(Color.BLACK);
			g.drawRect(p.getX(), p.getY(), q.getX() - p.getX(), q.getY() - p.getY());
		}

	}// paintComponent

	public void drawVertex(Graphics g, Vertex x){
		GraphMap gMap = GraphMap.getUnit();
		Map<String, Ponto> map = gMap.getMap();
		Ponto p;
		Color color = new Color(40,40,40);
		int state = (int)gMap.getMapSelection().get(x.getName());
		p = map.get(x.getName()); // get coordinate
		switch(state){
			case 1 : g.setColor(color);
				break;
			case 2 : g.setColor(Color.BLUE);
				break;
			case 3 : g.setColor(Color.WHITE);
				break;
			case 4 : g.setColor(Color.GRAY);
				break;
			case 5 : g.setColor(color);
				break;
			case 6 : g.setColor(Color.GRAY);
				break;
			case 7 : g.setColor(color);
				break;
		}// switch
		g.fillOval(p.getX(), p.getY(), SIZE, SIZE); // Draw Vertice

		if(state == 3){
			g.setColor(Color.BLACK);
			g.drawString("∞", p.getX() + SIZE/2 - 15, p.getY() + SIZE/2);
		}else if(state == 4 || state == 6){
			g.setColor(Color.BLACK);
			if(state == 4)
				g.drawString("" + x.getDistance(), p.getX() + SIZE/2 - 15, p.getY() + SIZE/2);
			else
				g.drawString("" + x.getInit() + "/", p.getX() + SIZE/2 - 15, p.getY() + SIZE/2);
		} else if (state == 5 || state == 7){
			g.setColor(Color.WHITE);
			if(state == 5)
				g.drawString("" + x.getDistance(), p.getX() + SIZE/2 - 15, p.getY() + SIZE/2);
			else
				g.drawString("" + x.getInit() + "/" + x.getFinal(), p.getX() + SIZE/2 - 15, p.getY() + SIZE/2);
		} // if-else
		g.setColor(Color.BLACK);
		g.drawOval(p.getX(), p.getY() , SIZE, SIZE);
		g.drawOval(p.getX() - 1, p.getY(), SIZE+1, SIZE+1);
		g.drawOval(p.getX() , p.getY() - 1, SIZE+1, SIZE+1);

		g.drawString(x.getName(), p.getX(), p.getY() - 10); // Draw Vertice Name
	}

	public void drawEdge(Graphics g, Vertex x){
		GraphMap gMap = GraphMap.getUnit();
		Map<String, Ponto> map = gMap.getMap();
		Ponto p,q;
		p = map.get(x.getName()); // get coordinate


		for(Vertex y : x.getAdj()){
			if(x.getName().equals(y.getName())){
				if(!Graph.getUnit().isDirected()){
					drawLoop(g, x);
				} else {
					drawOrientedLoop(g, x);
				}
			} else {
				g.setColor(Color.BLACK);
				q = map.get(y.getName());
				g.drawLine(p.getX() + SIZE/2, p.getY() + SIZE/2, q.getX() + SIZE/2, q.getY() + SIZE/2); // Draw Edge
				if(Graph.getUnit().isDirected())
					drawTriangle(g, p.getX(), p.getY(), q.getX(), q.getY());
				if(Graph.getUnit().isWeighted())
					g.drawString("" + x.getWeight(y), (p.getX() + q.getX())/2, (p.getY() + q.getY())/2);
			}
		}// for
	}// drawEdge

	public void drawEdgePredecessor(Graphics g, Vertex x){
		GraphMap gMap = GraphMap.getUnit();
		Map<String, Ponto> map = gMap.getMap();
		Ponto p,q;
		p = map.get(x.getName()); // get coordinate
		g.setColor(Color.RED);
		q = map.get(x.getPredecessor().getName());
		g.drawLine(q.getX() + SIZE/2, q.getY() + SIZE/2, p.getX() + SIZE/2, p.getY() + SIZE/2); // Draw Edge
		if(Graph.getUnit().isDirected())
			drawTriangle(g, q.getX(), q.getY(), p.getX(), p.getY());
	}

	public void drawTriangle(Graphics g, int x1, int y1, int x2, int y2){
		double m = (double)(x2 - x1)/(y2 - y1);
		double a = Math.atan(m);

		int a1, b1, t1, t2, a2, b2, a3, b3;

		int teste = (int)(SIZE/2*Math.sin(a));

		if(y1 > y2){
			a1 = (int)(SIZE/2*Math.sin(a)) + x2 + SIZE/2;
			b1 = (int)(SIZE/2*Math.cos(a)) + y2 + SIZE/2;
			t1 = (int)((SIZE/2 + 10)*Math.sin(a)) + x2 + SIZE/2;
			t2 = (int)((SIZE/2 + 10)*Math.cos(a)) + y2 + SIZE/2;
		} else {
			a1 = (int)-(SIZE/2*Math.sin(a)) + x2 + SIZE/2;
			b1 = (int)-(SIZE/2*Math.cos(a)) + y2 + SIZE/2;
			t1 = (int)-((SIZE/2 + 10)*Math.sin(a)) + x2 + SIZE/2;
			t2 = (int)-((SIZE/2 + 10)*Math.cos(a)) + y2 + SIZE/2;
		}

		double aRect = a + Math.PI/2;

		a2 = (int)(5*Math.sin(aRect)) + t1;
		b2 = (int)(5*Math.cos(aRect)) + t2;
		a3 = (int)-(5*Math.sin(aRect)) + t1;
		b3 = (int)-(5*Math.cos(aRect)) + t2;


		int boxX[] = {a1, a2, a3};
		int boxY[] = {b1, b2, b3};

		g.fillPolygon(boxX, boxY, 3);
	}// DrawTriangle
}// Panel