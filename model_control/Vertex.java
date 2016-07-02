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

	boolean isInMST;
        boolean isParallel;

	/** */
	int distance;

	/** */
	int in = 0, out = 0;

	/** Attribute that means the vertex's color. 0 for WHITE, 1 for GRAY and 2 for BLACK. */
	int color;

	/** */
	String name;

	/** */
	Vertex predecessor;

	/** */
	LinkedList<Vertex> Adj;

	/** */
	Map<String, Integer> weight = new HashMap<String, Integer>();
        Map<String, Integer> flow = new HashMap<>();

	public void putFlow(Vertex u, int w){
		flow.put(u.getName(), w);
	}
	public int getFlow(Vertex u){
		return flow.get(u.getName());
	}        
        
	/** */
	Map<String, Boolean> parallel = new HashMap<String, Boolean>();

	public boolean getParallel(){
            return isParallel;
	}

	public void setParallel(boolean b){
            isParallel = b;
	}
        
        public void setName(String name){
            this.name = name;
        }

	/** */
	public Vertex()
	{
		Adj = new LinkedList<Vertex>();
	}



	public void putWeight(Vertex u, int w){
		weight.put(u.getName(), w);
	}

	public int getWeight(Vertex u){
		return weight.get(u.getName());
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

	public void setIn(int x){
		in = x;
	}

	public void setOut(int x){
		out = x;
	}

	public int getIn(){
            int n = 0;
            for(Vertex x : Graph.getUnit().getV()){
                if(x.getAdj().contains(Graph.getUnit().getVertex(getName())))
                    n++;
            }
            return n;
	}

	public int getOut(){
            return Adj.size();
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
