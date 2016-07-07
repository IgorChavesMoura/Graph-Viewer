package view.input;

import view.panel.Panel;
import model.Graph;
import model.Point2D;
import model.Vertex;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import model.GraphMap;

public class MultiSelection extends MouseAdapter{

	private static MultiSelection unit;
	private MultiSelection(){}
	public static MultiSelection getUnit(){
		if(unit == null)
			unit = new MultiSelection();
		return unit;
	}// getUnit

	private boolean isMoving = false;
	private Point2D buffer, newCenter, vertex;
	private Map<String, Point2D> map;
	private boolean multi, isDragging;

	private ArrayList<Vertex> multiBox = new ArrayList<Vertex>();

	public boolean multiEmpty(){
		return multiBox.isEmpty();
	}
        
        public void addBox(Vertex u){
            multiBox.add(u);
            System.out.println(multiBox);
        }

	public ArrayList<Vertex> getMultiBox(){
		return multiBox;
	}
        
        public void clearBox(){
            multiBox.clear();
        }

	public boolean getMulti(){
		return multi;
	}

	public void setMulti(boolean b){
		multi = b;
	}

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
                for(Vertex a : multiBox){
                    vertex = map.get(a.getName());
                    a1 = (int)(radius*Math.sin(alpha)) + vertex.getX();
                    a2 = (int)(radius*Math.cos(alpha)) + vertex.getY();
                    GraphMap.getUnit().put(a, new Point2D(a1, a2));
                }
            } else{
                for(Vertex a : multiBox){
                    vertex = map.get(a.getName());
                    a1 = (int)-(radius*Math.sin(alpha)) + vertex.getX();
                    a2 = (int)-(radius*Math.cos(alpha)) + vertex.getY();
                    GraphMap.getUnit().put(a, new Point2D(a1, a2));
                }
            }

	}// setMoviment

	public void resetSelection(){
		GraphMap g = GraphMap.getUnit();
		Map<String, Integer> map = g.getMapSelection();
		Set<String> set = map.keySet();
		for(String x : set)
			g.put(Graph.getUnit().getVertex(x), 1);
	}

    public boolean isOnBox(Vertex u){
        return multiBox.contains(u);
    }
        
        @Override
    public void mouseClicked(MouseEvent e){
        String buf = ActionKeyboard.getUnit().getMultiSelectionBuffer();
        GraphMap g = GraphMap.getUnit();
        Vertex x = g.isOn(e.getX(), e.getY());

        if(multi){
            if(!g.isAlgorithmRunning()){
                if(x != null){
                    multiBox.add(x);
                    g.put(x, 2);
                }// if
            }// if
        } else if (!SelectionHandler.getUnit().isSelectedByClick()){
            System.out.println("AINDA GUY AQUI");
            multiBox.clear();
        }// if-else
        System.out.println(multiBox);
        Panel.getUnit().repaint();
    }

	public boolean isMoving(){
		return isMoving;
	}// isMoving

    public void substituteOnBox(MouseEvent e){
        GraphMap gm = GraphMap.getUnit();
        Vertex x;
        x = gm.isOn(e.getX(), e.getY());
        if(x != null && !multiBox.contains(x) && multi)
            multiBox.add(x);        
    }
        
    public void mouseDragged(MouseEvent e){
        String buf = ActionKeyboard.getUnit().getMultiSelectionBuffer();
        GraphMap gm = GraphMap.getUnit();
        ArrayList<Vertex> u;
        Vertex x = gm.isOn(e.getX(), e.getY());
        Point2D p, q;
        if((buf == null || multiEmpty()) && x == null){
            multiBox.clear();
        }
        if(!e.isMetaDown() && !e.isAltDown()){
            
            if(!isMoving){
                gm.setMultX(e.getX(), e.getY());
                isMoving = true;
                map = gm.getMapCopy();
                buffer = new Point2D(e.getX(), e.getY());
            }// if
            
            newCenter = new Point2D(e.getX(), e.getY());
            setMoviment();
            Panel.getUnit().repaint();
            
            
            substituteOnBox(e);
/*          
            if(isMoving){
                gm.setMultY(e.getX(), e.getY());
                p = gm.getMultX();
                q = gm.getMultY();

                u = gm.isOn(p.getX(), p.getY(), q.getX(), q.getY());
                if(u != null){
                        multiBox.addCollection(u);
                        gm.put(u, 2);
                }
            }
*/
        }// if
        Panel.getUnit().repaint();
        System.out.println(multiBox);
    }// mouseDragged

	public void mouseReleased(MouseEvent e){
		isMoving = false;
		GraphMap.getUnit().setMultX(0,0);
		GraphMap.getUnit().setMultY(0,0);
		Panel.getUnit().repaint();
	}// mouseReleased

}// MultiSelection