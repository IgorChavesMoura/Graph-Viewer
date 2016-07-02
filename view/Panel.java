package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

import model_control.*;
import thread.*;

public class Panel extends JPanel{

	private static Panel unit;
	private SelectionEdgeHandler seh = SelectionEdgeHandler.getUnit();
	private boolean isLoop = false, isBufferLoop = false;
	private BufferedImage vertexPainting = null;
        
        public static final int SIZE = 50;

	private Graphics2D graphics, vertexGraphics;

	public static Panel getUnit(){
		if(unit == null)
			unit = new Panel();
		return unit;
	}// getUnit

	private Panel(){
		setBackground(new Color(88, 188, 120));
	}

	public BufferedImage loadSprite(String name){
		BufferedImage temp = null;
		try {
			// open
			temp = ImageIO.read(new File(name));
		} catch (IOException e) {
			System.out.println("Exception");
		}// try-catch
		return temp;
	}

	public void setIsLoop(boolean b){
		isLoop = b;
	}// setIsLoop

	public void drawLoop(Vertex x){
		if(x != null){
			int radius = 50;
			Ponto p = GraphMap.getUnit().get(x);
			graphics.drawOval(p.getX() + SIZE/2, p.getY() - SIZE/2, radius, radius);

			if(Graph.getUnit().isWeighted() && !isBufferLoop)
				graphics.drawString("" + x.getWeight(x), p.getX() + 3*SIZE/2, p.getY() - SIZE/2 - 10);
		}
	}

	public void drawParallelEdge(int x1, int y1, int x2, int y2, int factor){
		graphics.drawLine(x1, y1 + factor, x2, y2 + factor);
	}

	public void drawParallelOrientedEdge(int x1, int y1, int x2, int y2, int factor){
            double m = (double)(x2 - x1)/(y2 - y1);
            double a = Math.atan(m);            
            int a1 = 0, b1 = 0, a2 = 0, b2 = 0;
            
            if(y1 > y2){
                    a1 = (int)(factor*SIZE/2*Math.sin(a)) + x1 + SIZE/2;
                    b1 = (int)(factor*SIZE/2*Math.cos(a)) + y1 + SIZE/2;
            } else {
                    a1 = (int)-(factor*SIZE/2*Math.sin(a)) + x1 + SIZE/2;
                    b1 = (int)-(factor*SIZE/2*Math.cos(a)) + y1 + SIZE/2;
            }

            if(y1 > y2){
                    a2 = (int)(factor*(SIZE/2*Math.sin(a))) + x2 + SIZE/2;
                    b2 = (int)(factor*(SIZE/2*Math.cos(a))) + y2 + SIZE/2;
            } else {
                    a2 = (int)-(factor*(SIZE/2*Math.sin(a))) + x2 + SIZE/2;
                    b2 = (int)-(factor*(SIZE/2*Math.cos(a))) + y2 + SIZE/2;
            }
            
            graphics.drawLine(a1, b1, a2, b2);
            drawTriangle(x1, y1 + factor, x2, y2 + factor);
	}

	public void drawOrientedLoop(Vertex x){
		drawLoop(x);
		Ponto p = GraphMap.getUnit().get(x);
		drawTriangle(p.getX() + SIZE, p.getY(), p.getX() - 50 + SIZE , p.getY());
	}

        @Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		graphics = (Graphics2D)g;
		vertexGraphics = (Graphics2D)g;

		GraphMap gMap = GraphMap.getUnit();
		Map<String, Ponto> map = gMap.getMap();

		Set<String> set = map.keySet();
		Ponto p, q;

    		BasicStroke dashed = new BasicStroke(1.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f);

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setRenderingHints(rh);
		graphics.setStroke(dashed);

		edgeBuffer();
		drawAllEdges(set);
		drawAllPredecessors(set);
		drawAllVertex(set);
/*
		// Selection Box
		p = gMap.getMultX();
		q = gMap.getMultY();

		if(MultiSelection.getUnit().isMoving()){
			graphics.setColor(Color.BLACK);
			drawBoxSelection(p, q);
		}
*/
	}// paintComponent

	public void drawBoxSelection(Ponto p, Ponto q){
		// horizontal
		graphics.drawLine(p.getX(), p.getY(), q.getX(), p.getY());
		graphics.drawLine(p.getX(), q.getY(), q.getX(), q.getY());

		// vertical
		graphics.drawLine(p.getX(), p.getY(), p.getX(), q.getY());
		graphics.drawLine(q.getX(), p.getY(), q.getX(), q.getY());
	}

	public void drawAllVertex(Set<String> set){
		for(String x : set)
			drawVertex(Graph.getUnit().getVertex(x));
	}

	public void edgeBuffer(){
		isBufferLoop = true;
		if(!isLoop){
			graphics.drawLine(seh.getEx1(), seh.getEy1(),seh.getEx2(),seh.getEy2());
		} else {
			drawLoop(seh.getBuffer());
		}// if-else
		isBufferLoop = false;
	}

	public void drawAllEdges(Set<String> set){
		if(!GraphMap.getUnit().getShowPredecessor()){
			graphics.setColor(Color.BLACK);
			for(String x : set){
				drawEdge(Graph.getUnit().getVertex(x));
			}
		}// ifi
	}

	public void drawAllPredecessors(Set<String> set){
		if(GraphMap.getUnit().getShowPredecessor())
			for(String x : set)
				if (Graph.getUnit().getVertex(x).getPredecessor() != null)
					drawEdgePredecessor(Graph.getUnit().getVertex(x));
	}

	public void drawVertex(Vertex x){
		GraphMap gMap = GraphMap.getUnit();
		Map<String, Ponto> map = gMap.getMap();
		Ponto p;
		Color color = new Color(40,40,40);
		int state = (int)gMap.getMapSelection().get(x.getName());
		p = map.get(x.getName()); // get coordinate
		switch(state){
			case 1 : vertexGraphics.setColor(color);
				break;
			case 2 : vertexGraphics.setColor(Color.BLUE);
				break;
			case 3 : vertexGraphics.setColor(Color.WHITE);
				break;
			case 4 : vertexGraphics.setColor(Color.GRAY);
				break;
			case 5 : vertexGraphics.setColor(color);
				break;
			case 6 : vertexGraphics.setColor(Color.GRAY);
				break;
			case 7 : vertexGraphics.setColor(color);
				break;
		}// switch
		vertexGraphics.fillOval(p.getX(), p.getY(), SIZE, SIZE); // Draw Vertice

		if(state == 3){
			vertexGraphics.setColor(Color.BLACK);
			vertexGraphics.drawString("∞", p.getX() + SIZE/2 - 15, p.getY() + SIZE/2);
		}else if(state == 4 || state == 6){
			vertexGraphics.setColor(Color.BLACK);
			if(state == 4)
				vertexGraphics.drawString("" + x.getDistance(), p.getX() + SIZE/2 - 15, p.getY() + SIZE/2);
			else
				vertexGraphics.drawString("" + x.getInit() + "/", p.getX() + SIZE/2 - 15, p.getY() + SIZE/2);
		} else if (state == 5 || state == 7){
			vertexGraphics.setColor(Color.WHITE);
			if(state == 5){
				if(Graph.getUnit().isWeighted() && x.getDistance	() == Integer.MAX_VALUE){
					vertexGraphics.drawString("∞", p.getX() + SIZE/2 - 15, p.getY() + SIZE/2);
				}else{
					vertexGraphics.drawString("" + x.getDistance(), p.getX() + SIZE/2 - 15, p.getY() + SIZE/2);
				}//if-else
			}
			else
				vertexGraphics.drawString("" + x.getInit() + "/" + x.getFinal(), p.getX() + SIZE/2 - 15, p.getY() + SIZE/2);
		} // if-else
		vertexGraphics.setColor(Color.BLACK);

		vertexGraphics.drawOval(p.getX(), p.getY() , SIZE, SIZE);

		vertexGraphics.drawString(x.getName(), p.getX(), p.getY() - 10); // Draw Vertice Name
	}

	public void drawEdge(Vertex x){
            GraphMap gMap = GraphMap.getUnit();
            Graph graph = Graph.getUnit();
            Map<String, Ponto> map = gMap.getMap();
            Ponto p,q;

            p = map.get(x.getName()); // get coordinate

            if(Graph.getUnit().isDirected()){

                for(Vertex y : x.getAdj()){
                    q = map.get(y.getName());
                    if(x.getName().equals(y.getName())){
                        drawOrientedLoop(x);
                    } else {
                        graphics.setColor(Color.BLACK);

                        graphics.drawLine(p.getX() + SIZE/2, p.getY() + SIZE/2, q.getX() + SIZE/2, q.getY() + SIZE/2); // Draw Edge
                        drawTriangle(p.getX(), p.getY(), q.getX(), q.getY());

                        if(Graph.getUnit().isWeighted()){
                            if(!ResidualGraph.getUnit().isRunning()){
                                graphics.drawString("" + x.getWeight(y), (p.getX() + q.getX())/2, (p.getY() + q.getY())/2);
                            } else {
                                graphics.drawString("" + x.getFlow(y) + "/" + x.getWeight(y), (p.getX() + q.getX())/2, (p.getY() + q.getY())/2);
                            }// if-else
                        }
                    }// if-else
                }// for - adj
                graph.resetParalell();
            } else {
                for(Vertex y : x.getAdj()){
                    if(x.getName().equals(y.getName())){
                        drawLoop(x);
                    } else {
                        graphics.setColor(Color.BLACK);
                        q = map.get(y.getName());
                        graphics.drawLine(p.getX() + SIZE/2, p.getY() + SIZE/2, q.getX() + SIZE/2, q.getY() + SIZE/2); // Draw Edge

                        if(!ResidualGraph.getUnit().isRunning()){
                            graphics.drawString("" + x.getWeight(y), (p.getX() + q.getX())/2, (p.getY() + q.getY())/2);
                        } else {
                            graphics.drawString("" + x.getFlow(y) + "/" + x.getWeight(y), (p.getX() + q.getX())/2, (p.getY() + q.getY())/2);
                        }// if-else
                    }
                }//for
            }// if-else
	}// drawEdge

	public void drawEdgePredecessor(Vertex x){
		GraphMap gMap = GraphMap.getUnit();
		Map<String, Ponto> map = gMap.getMap();
		Ponto p,q;
		p = map.get(x.getName()); // get coordinate
		graphics.setColor(Color.RED);
		q = map.get(x.getPredecessor().getName());
		graphics.drawLine(q.getX() + SIZE/2, q.getY() + SIZE/2, p.getX() + SIZE/2, p.getY() + SIZE/2); // Draw Edge
		if(Graph.getUnit().isDirected())
			drawTriangle(q.getX(), q.getY(), p.getX(), p.getY());
	}

	public void drawTriangle(int x1, int y1, int x2, int y2){
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

		graphics.fillPolygon(boxX, boxY, 3);
	}// DrawTriangle
}// Panel