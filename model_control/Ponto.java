package model_control;

import java.io.*;

public class Ponto implements Serializable{

	static final long serialVersionUID = 1L;
	private int x, y;

	// *** Constructor *** //
	public Ponto(int x, int y){
		setX(x);
		setY(y);
	}//Constructor

	public Ponto(){
		this(100,100);
	}//Constructor

	// *** Setters *** //
	public void setX(int x){
		this.x = x;
	}//setX

	public void setY(int y){
		this.y = y;
	}//setY

	// *** Getters *** //
	public int getX(){
		return x;
	}//getX

	public int getY(){
		return y;
	}//getY

	// *** Functions *** //
	// POLIMORFISMO INCLUSÃO
	public String toString(){
		return "[" + x + ", " + y + "]";
	}//toString

	public Ponto soma(Ponto P){
		return new Ponto(x + P.getX(), y + P.getY());
	}//soma

	public Ponto mult(int scalar){
		return new Ponto(x*scalar, y*scalar);
	}
}//PontoAnderson Oliveira Mesquita
