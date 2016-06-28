package model_control;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.PriorityQueue;

import observer.*;
import thread.*;
import view.*;

import java.io.*;

import javax.swing.JOptionPane;

/** */
public class Graph implements Serializable{

	static final long serialVersionUID = 1L;
	private static Graph unit;

	public static Graph getUnit(){
		if(unit == null)
			unit = new Graph(true);
		return unit;
	}

	public static void setUnit(Graph g){
		unit = g;
	}

	/** */
	ArrayList<Vertex> V;

	/** */
	int n;

	/** */
	int time;

	/** */
	boolean directed;

	/** */
	boolean isWeighted;

	public void setWeighted(boolean b){
		isWeighted = b;
	}// setWeighted

	public boolean isWeighted(){
		return isWeighted;
	}// getWeighted

	public void clear(){
		V.clear();
	}// clear

	public boolean vertexEmpty(){
		return V.size() == 0;
	}

	public ArrayList<Vertex> getV(){
		return V;
	}

	public void random(){
		int size = (int)(Math.random()*20) + 5;

		for(Integer index = 0 ; index < size ; index++)
			addVertex(index.toString());

		Integer e1, e2;
		for(int index = 0 ; index < size*2 ; index++){
			e1 = (int)(Math.random()*100)%size;
			e2 = (int)(Math.random()*100)%size;
			addEdge(getVertex(e1.toString()), getVertex(e2.toString()));
			if(!directed)
				addEdge(getVertex(e2.toString()), getVertex(e1.toString()));
		}// for
	}// random


	private Graph(boolean pdirected)
	{
		n = 0;
		V = new ArrayList<Vertex>();
		directed = pdirected;
	}

	/** */
	private Graph(int n, boolean pdirected)
	{
		this(pdirected);
		Integer i;
		String nome;
		for(i=1;i<=n;i++){
			addVertex(i.toString());
		}
		System.out.println("Iniciado um grafo com "+ n +" Vertices.");
	}

	public boolean isDirected(){
		return directed;
	}// isDirected

	/** */
	public void addEdge(Vertex u, Vertex v)
	{
		if(!existsEdge(u, v)){
			u.Adj.addFirst(v);
			u.putWeight(v, 1);

			if(!directed){
				v.Adj.addFirst(u);
				v.putWeight(u, 1);
			}
			System.out.println("Inserida aresta ("+u.name+", "+v.name+")");
		}
	}

	public void addEdge(Vertex u, Vertex v, int weight){
		this.addEdge(u, v);
		u.putWeight(v, weight);
		if(!directed)
			v.putWeight(u, weight);
	}

	public boolean existsEdge(Vertex u, Vertex v){
		if(!directed)
			return getVertex(u.getName()).Adj.contains(getVertex(v.getName())) || getVertex(v.getName()).Adj.contains(getVertex(u.getName()));
		else
			return getVertex(u.getName()).Adj.contains(getVertex(v.getName()));
	}

	/** */
	public void removeEdge(Vertex u, Vertex v){
			u.Adj.remove(v);
		if(!directed)
			v.Adj.remove(u);
	}

	/** */
	public void addVertex(String pname)
	{
		Vertex v;
		v = new Vertex(pname);

		V.add(v);
		n = n + 1;

		// Observer tools
		Observable obs = new ObservableGraphMap(v);
		obs.attach(new ObserverGraphMap());
		obs.send();
		// Observer tools
	}

	/** */
	public void removeVertex(Vertex u)
	{
		for(Vertex v : V){
			if(v != u)
				removeEdge(v, u);
		}// for

		V.remove(u);
		n--;

		// Observer Tools
		Observable obs = new ObservableGraphMap(u);
		obs.attach(new ObserverGraphMapRemove());
		obs.send();
		// Observer Tools
	}

	public Vertex getVertex(String name){
		for(Vertex v : V)
			if(v.getName().equals(name))
				return v;
		return null;
	}

	// Observer Tools
	public void handleThread(ObserverGraphMapPaint o, ObservableGraphMap ob, int color, Vertex u)
	throws InterruptedException{
		ThreadPaint t = new ThreadPaint();
		o.setColor(color);
		ob.setState(u);
		ob.send();
		t.run();
	}

	/** */
	public void BFS(Vertex s) throws InterruptedException{
		Vertex v, u;
		int i,j;

		ObservableGraphMap obs = new ObservableGraphMap(null);
		ObserverGraphMapPaint ob = new ObserverGraphMapPaint(3);
		obs.attach(ob);

		LinkedList<Vertex> F;
		GraphMap.getUnit().setAlgorithmRunning(true);

		for (Vertex x : V){
			x.predecessor = null;
			x.distance = -1; //-1 representa infinito
			x.color= 0;
			StatusPanel.getUnit().setAction("Color ["+ x +"] = white");

			handleThread(ob, obs, 3, x);
		}

		s.distance = 0;
		s.color = 1;
		StatusPanel.getUnit().setAction("Color ["+ s +"] = gray");

		handleThread(ob, obs, 4, s);

		F = new LinkedList<Vertex>();
		F.add(s);

		F = new LinkedList<Vertex>();
		F.add(s);
		ListIterator iter;
		while(F.size()!=0)
		{
			u = F.removeFirst();
			iter = u.Adj.listIterator();
			while(iter.hasNext())
			{
				v = (Vertex)iter.next();
				if(v.color==0)
				{
					v.color = 1;
					v.predecessor = u;
					v.distance = u.distance + 1;
					F.add(v);
					StatusPanel.getUnit().setAction("Color ["+ v +"] = gray; d[" + v + "] = " + v.distance);
					handleThread(ob, obs, 4, v);
				}
			}
			u.color = 2;
			StatusPanel.getUnit().setAction("Color ["+ u +"] = black");
			handleThread(ob, obs, 5, u);
		}
	}

	/** */
	public void extraBFS(Vertex s) throws InterruptedException{
		Vertex v, u;
		int i,j;

		ObservableGraphMap obs = new ObservableGraphMap(null);
		ObserverGraphMapPaint ob = new ObserverGraphMapPaint(3);
		obs.attach(ob);

		LinkedList<Vertex> F;
		GraphMap.getUnit().setAlgorithmRunning(true);

		for (Vertex x : V){
			x.predecessor = null;
			x.distance = -1; //-1 representa infinito
			x.color= 0;
			StatusPanel.getUnit().setAction("Color ["+ x +"] = white");

			handleThread(ob, obs, 3, x);
		}

		s.distance = 0;
		s.color = 1;
		StatusPanel.getUnit().setAction("Color ["+ s +"] = gray");

		handleThread(ob, obs, 4, s);

		F = new LinkedList<Vertex>();
		F.add(s);

		F = new LinkedList<Vertex>();
		F.add(s);
		ListIterator iter;
		while(F.size()!=0)
		{
			u = F.removeFirst();
			iter = u.Adj.listIterator();
			while(iter.hasNext())
			{
				v = (Vertex)iter.next();
				if(v.color==0)
				{
					v.color = 1;
					v.predecessor = u;
					v.distance = u.distance + 1;
					F.add(v);
					StatusPanel.getUnit().setAction("Color ["+ v +"] = gray; d[" + v + "] = " + v.distance);

					handleThread(ob, obs, 4, v);
				} else if (v.color >= 1 && v.distance <= u.distance && !v.getName().equals(u.getName())){

					v.distance = u.distance + 1;
					v.predecessor = u;
					handleThread(ob, obs, 5, v);

					StatusPanel.getUnit().setAction("Color ["+ v +"] = black; d[" + v + "] = " + v.distance);
				}// if-else-if
			}
			u.color = 2;
			StatusPanel.getUnit().setAction("Color ["+ u +"] = black");
			handleThread(ob, obs, 5, u);
		}
	}


	/** */
	public void DFS() throws InterruptedException{
		ObservableGraphMap obs = new ObservableGraphMap(null);
		ObserverGraphMapPaint ob = new ObserverGraphMapPaint(3);
		obs.attach(ob);

		GraphMap.getUnit().setAlgorithmRunning(true);

		int i;

		for(Vertex u : V)
		{
			u.color = 0;
			u.predecessor = null;
			handleThread(ob, obs, 3, u);
			System.out.println("Vertex "+u.name+" virou BRANCO");
		}

		time = 0;

		for(Vertex u : V)
		{
			if(u.color == 0)
				DFS_Visit(u);
		}
	}

	/** */
	public void DFS_Visit(Vertex u)  throws InterruptedException
	{
		ListIterator iter;
		Vertex v;

		ObservableGraphMap obs = new ObservableGraphMap(null);
		ObserverGraphMapPaint ob = new ObserverGraphMapPaint(3);
		obs.attach(ob);

		u.color = 1;
		time++;
		u.discoveryTime = time;
		handleThread(ob, obs, 6, u);
		System.out.println("Vertex "+u.name+" virou CINZA com tempo de descoperta "+u.discoveryTime);
		iter = u.Adj.listIterator();
		while(iter.hasNext()){
			v = (Vertex)iter.next();
			if(v.color==0) {
				v.predecessor = u;
				DFS_Visit(v);
			}
		}

		u.color = 2;
		time++;
		u.finalTime = time;
		handleThread(ob, obs, 7, u);
		System.out.println("Vertex "+u.name+" virou PRETO com tempo de finalização "+u.finalTime);

	}
	
	public void initializeSingleSource(Vertex s){
		for(Vertex v: V){
			v.distance = Integer.MAX_VALUE; // representa infinito
			v.predecessor = null;
		}
		s.distance = 0;
	}
	
	public void relax(Vertex u, Vertex v){
		if(v.distance > u.distance + u.getWeight(v)){
			v.distance = u.distance + u.getWeight(v);
			v.predecessor = u;
		}
	}
 
	public void Dijkstra(Vertex s) {
		initializeSingleSource(s);
		PriorityQueue<Vertex> Q = new PriorityQueue<Vertex>(0, new DistanceComparator());
		for(Vertex u: V) 
			Q.add(u);
		while(Q.size()!=0){
			Vertex u = Q.poll();
			for(Vertex v: u.Adj){
				relax(u,v);
			}
		}

	}

	public void Prim(Vertex r)
	{
		for(Vertex u : V)
		{
			u.distance = Integer.MAX_VALUE;
			u.isInMST = false;
		}
			
		r.distance = 0;
		r.predecessor = null;
		
		PriorityQueue<Vertex> Q = new PriorityQueue<Vertex>(0, new DistanceComparator());
		
		for ( Vertex u : V)
			Q.add(u);
		
		Vertex u;
		int d;
				
		while ( Q.size() != 0)
		{
			u = Q.poll();
			u.isInMST = true;
			
			for (Vertex v : u.Adj)
			{				
				if ( !v.isInMST && u.getWeight(v)<v.distance )
				{
					v.distance = u.getWeight(v);
					v.predecessor = u;
				}
			}
		}
	}
	
	public void setDirection(boolean direct){
		directed = direct;
	}

	// DEBUG
	public void print(){
		for(Vertex v : V){
			System.out.print("[" + v.getName() + "]");
			v.print();
			System.out.println();
		}

	}

	public void saveGraph(File f){
		try{
			FileOutputStream fout = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(this);
			oos.close();
		} catch(Exception ex){
			ex.printStackTrace();
		}

	}
	public static Graph loadGraph(File f){
		 Graph graph = getUnit();
		 try{
		 	FileInputStream fin = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fin);

			graph = (Graph)ois.readObject();
		 	ois.close();
		 } catch (Exception ex){
		 	JOptionPane.showMessageDialog(null, "File not Found");
		 	System.out.println(ex);
		 }
		return graph;
	}

	public void showAll(){
		System.out.println("=======================All::All=======================");
		System.out.println("Vertex set: " + V);
		System.out.println("mapeamento: " + GraphMap.getUnit().getMap());
		System.out.println("mapeamento de seleção: " + GraphMap.getUnit().getMapSelection());
		System.out.println("Lista de ADJ:");

		print();
		System.out.println("=======================All::All=======================");
	}
}