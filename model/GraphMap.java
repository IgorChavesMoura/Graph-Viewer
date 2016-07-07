package model;

import view.panel.Panel;
import model.Graph;
import model.Point2D;
import model.Vertex;
import java.util.*;
import java.io.*;

public class GraphMap implements Serializable{

	/** Static Member Variables */
        static final long serialVersionUID = 1L;
	private static GraphMap unit;
	private Map<String, Point2D> map = new HashMap<String, Point2D>();
	private Map<String, Integer> mapSelection = new HashMap<String, Integer>();
	boolean isAlgorithmRunning = false;
	boolean showPredecessor = false;
	private Point2D multx = new Point2D(0,0), multy = new Point2D(0,0);

	public static GraphMap getUnit(){
		if (unit == null)
			unit = new GraphMap();
		return unit;
	}// getUnit

	public static void setUnit(GraphMap gm){
		unit = gm;
	}
        
        public void substitute(String a, String b){
            Point2D p = map.get(a);
            map.remove(a);
            map.put(b, p);
            
            Integer n = mapSelection.get(a);
            mapSelection.remove(a);
            mapSelection.put(b, n);
        }

	public void clear(){
		map.clear();
		mapSelection.clear();
		isAlgorithmRunning = false;
		showPredecessor = false;
	}

	public void setMultX(int w, int x){
		multx = new Point2D(w,x);
	}

	public void setMultY(int w, int x){
		multy = new Point2D(w,x);
	}

	public Point2D getMultX(){
		return multx;
	}

	public Point2D getMultY(){
		return multy;
	}

	public boolean isAlgorithmRunning(){
		return isAlgorithmRunning;
	} // isAlgorithmRunning

	public void setAlgorithmRunning(boolean b){
		isAlgorithmRunning = b;
	}// setAlgorithRunning

	public boolean getShowPredecessor(){
		return showPredecessor;
	}// getShowPredecessor

	public void setShowPredecessor(boolean b){
		showPredecessor = b;
	}// setShowPredecessor

	public void put(Vertex u, Point2D p){
		map.put(u.getName(), p);
	}//put

	public void put(Vertex u, int x){
		mapSelection.put(u.getName(),x);
	}// put

	public Point2D get(Vertex u){
		return map.get(u.getName());
	}

	public Map<String, Point2D> getMapCopy(){
		Map<String, Point2D> ret = new HashMap<String, Point2D>();
		Graph g = Graph.getUnit();
		for(Vertex a : g.getV()){
			ret.put(a.getName(), map.get(a.getName()));
		}
		return ret;
	}

	public void rem(Vertex u){
		map.remove(u.getName());
		mapSelection.remove(u.getName());
	}// rem

	public Map<String, Point2D> getMap(){
		return map;
	}// getMap

	public Map<String, Integer> getMapSelection(){
		return mapSelection;
	}// getMapSelection

	public Vertex isOn(int x, int y){
		Set<String> key = map.keySet();
		Point2D p;
		for(String u : key){
			p = map.get(u);
			if(x >= p.getX() && x <= p.getX() + Panel.SIZE && y >= p.getY() && y <= p.getY() + Panel.SIZE)
				return Graph.getUnit().getVertex(u);
		}// for
		return null;
	}// isOn

	public void setXY(Vertex v, int x, int y){
		map.put(v.getName(), new Point2D(x,y));
	}// setXY

	private GraphMap(){} // constructor

}//GraphMap