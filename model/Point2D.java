package model;

import java.io.*;

/** possui um par de coordenadas e operações sobre elas*/
public class Point2D implements Serializable{

    /** 
     * construtor básico que pega dois inteiros correspondentes as coordenadas
     * x e y.
     * @param x nova coordenada x
     * @param y nova coordenada y
     */
    public Point2D(int x, int y){
        setX(x);
        setY(y);
    }//Constructor

    /** altera o valor da coordenada x
     * @param x nova coordenada x
     */
    public void setX(int x){
        this.x = x;
    }//setX

    /**
     * altera o valor da coordenada y.
     * @param y nova coordenada y
     */
    public void setY(int y){
        this.y = y;
    }//setY

    /**
     * retorna a coordenada x
     * @return retorna a coordenada x
     */
    public int getX(){
        return x;
    }//getX

    /**
     * retorna a coordenada y
     * @return retorna a coordenada y
     */
    public int getY(){
        return y;
    }//getY


    @Override
    /** sobrescreve toString */
    public String toString(){
        return "[" + x + ", " + y + "]";
    }//toString

    static final long serialVersionUID = 1L;
    private int x, y;        
}//Point2D
