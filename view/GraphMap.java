package view;

import java.util.*;
import model_control.*;
import java.io.*;

public class GraphMap implements Serializable{

	static final long serialVersionUID = 1L;

	/** Static Member Variables */
	private static GraphMap unit;
	private Map<String, Ponto> map = new HashMap<String, Ponto>();
	private Map<String, Integer> mapSelection = new HashMap<String, Integer>();
	boolean isAlgorithmRunning = false,
			showPredecessor = false;
	private Ponto multx = new Ponto(0,0), multy = new Ponto(0,0);

	public static GraphMap getUnit(){
		if (unit == null)
			unit = new GraphMap();
		return unit;
	}// getUnit

	public static void setUnit(GraphMap gm){
		unit = gm;
	}

	public void clear(){
		map.clear();
		mapSelection.clear();
		isAlgorithmRunning = false;
		showPredecessor = false;
	}

	public void setMultX(int w, int x){
		multx = new Ponto(w,x);
	}

	public void setMultY(int w, int x){
		multy = new Ponto(w,x);
	}

	public Ponto getMultX(){
		return multx;
	}

	public Ponto getMultY(){
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

	public void put(Vertex u, Ponto p){
		map.put(u.getName(), p);
	}//put

	public void put(Vertex u, int x){
		mapSelection.put(u.getName(),x);
	}// put

	public Ponto get(Vertex u){
		return map.get(u.getName());
	}

	public Map<String, Ponto> getMapCopy(){
		Map<String, Ponto> ret = new HashMap<String, Ponto>();
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

	public Map<String, Ponto> getMap(){
		return map;
	}// getMap

	public Map<String, Integer> getMapSelection(){
		return mapSelection;
	}// getMapSelection

	public Vertex isOn(int x, int y){
		Set<String> key = map.keySet();
		for(String u : key){
			Ponto p = map.get(u);
			if(x >= p.getX() && x <= p.getX() + Panel.SIZE && y >= p.getY() && y <= p.getY() + Panel.SIZE)
				return Graph.getUnit().getVertex(u);
		}// for
		return null;
	}// isOn

	public void setXY(Vertex v, int x, int y){
		map.put(v.getName(), new Ponto(x,y));
	}// setXY

	private GraphMap(){} // constructor

}//GraphMap