/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

// URRA! ANDERSON\IGOR, COMENTEM AQUI.

/** 
  *
  */
public class GraphFile implements Serializable{
 
    public static GraphFile getUnit(){
        if(unit == null)
            unit = new GraphFile();
        return unit;
    }
    private GraphFile(){}
    public static void setUnit(GraphFile os){
        unit = os;
    } 
    public void load(){
        Graph.setUnit(g);
        GraphMap.setUnit(gm);
    }

    static final long serialVersionUID = 1L;
    private static GraphFile unit;
    private Graph g = Graph.getUnit();
    private GraphMap gm = GraphMap.getUnit();
    
}
