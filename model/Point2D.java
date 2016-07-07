package model;

import java.io.*;

public class Point2D implements Serializable{

    // *** Constructor *** //
    public Point2D(int x, int y){
        setX(x);
        setY(y);
    }//Constructor

    public Point2D(){
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
    @Override
    public String toString(){
        return "[" + x + ", " + y + "]";
    }//toString

    public Point2D sum(Point2D P){
        return new Point2D(x + P.getX(), y + P.getY());
    }//soma

    public Point2D mult(int scalar){
        return new Point2D(x*scalar, y*scalar);
    }

    static final long serialVersionUID = 1L;
    private int x, y;        
}//PontoAnderson Oliveira Mesquita
