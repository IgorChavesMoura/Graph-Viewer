package model;

import java.util.*;

public class Vertex{

	private ArrayList<Vertex> adj = new ArrayList<>();
	private int id;
	private int x,y;

	public Vertex(int id){
		this.id = id;
		x = (int)(Math.random()*500);
		y = (int)(Math.random()*500);
	}// constructor

	public ArrayList<Vertex> getAdj(){
		return adj;
	}

	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}

	public void add(Vertex v){
		adj.add(v);
	}

	public Vertex get(int index){
		return adj.get(index);
	}

	public int getId(){
		return id;
	}

	// View
	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	// Debug
	public void print(){
		for(Vertex a : adj){
			System.out.print("→ [" + a.getId() + "]");
		}
	}
}