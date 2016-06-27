package model_control;

import java.util.*;
import java.io.*;

/** */
public class Vertex implements Serializable
{

	static final long serialVersionUID = 1L;
	/** */
	int discoveryTime;

	/** */
	int finalTime;

	/** */
	int distance;

	/** Attribute that means the vertex's color. 0 for WHITE, 1 for GRAY and 2 for BLACK. */
	int color;

	/** */
	String name;

	/** */
	Vertex predecessor;
	
	boolean isInMST;

	/** */
	LinkedList<Vertex> Adj;

	/** */
	Map<String, Integer> weight = new HashMap<>();

	/** */
	public Vertex()
	{
		Adj = new LinkedList<Vertex>();
	}

	public void putWeight(Vertex u, int w){
		weight.put(u.getName(), w);
	}

	public int getWeight(Vertex u){
		return weight.get(u);
	}

	/** */
	Vertex(String name) {
		this();
		this.name = name;
	}

	public String getName(){
		return name;
	}//getName

	public int getInit(){
		return discoveryTime;
	}// getInit

	public int getFinal(){
		return finalTime;
	}

	public List<Vertex> getAdj(){
		return Adj;
	}

	public int getDistance(){
		return distance;
	}

	// DEBUG
	public void print(){
		for(Vertex v : Adj)
			System.out.print("→ [" + v.getName() + "]");
	}

	public String toString(){
		return getName();
	}// toString

	public Vertex getPredecessor(){
		return predecessor;
	}

	public int getDiscoveryTime(){
		return discoveryTime;
	}
}
