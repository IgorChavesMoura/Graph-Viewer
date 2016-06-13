package model;

import java.util.*;

public class Graph{
	private static Graph unit;
	private ArrayList<Vertex> vertexSet = new ArrayList<>();
	private int top = 0;

	public static Graph getUnit(){
		if(unit == null)
			unit = new Graph();
		return unit;
	}// getUnit

	private Graph(){}

	// operations
	public void add(){
		vertexSet.add(new Vertex(top));
		top++;
	}// add

	public Vertex get(int index){
		return vertexSet.get(index);
	}// get

	public int order(){
		return vertexSet.size();
	}

	public ArrayList<Vertex> getSet(){
		return vertexSet;
	}

	public void addAdj(int index1, int index2){
		vertexSet.get(index1).add(vertexSet.get(index2));
	}

	public Vertex isOn(int x, int y){
		Vertex v;
		for(int index = 0 ; index < order(); index++){
			v = get(index);
			if(x >= v.getX() && x <= v.getX() + 50 && y >= v.getY() && y <= v.getY() + 50)
				return v;
		}
		return null;

	}

	//Debug
	public void print(){
		for(Vertex a : vertexSet){
			System.out.print("[" + a.getId() + "]");
			a.print();
			System.out.println();
		}

	}
}