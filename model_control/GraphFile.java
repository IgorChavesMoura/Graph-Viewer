/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model_control;

import view.GraphMap;
import java.io.Serializable;

/**
 *
 * @author anderson
 */
public class GraphFile implements Serializable{
 
    static final long serialVersionUID = 1L;    
    
    private static GraphFile unit;
    public static GraphFile getUnit(){
        if(unit == null)
            unit = new GraphFile();
        return unit;
    }
    private GraphFile(){}
    public static void setUnit(GraphFile os){
        unit = os;
    }    
    
    private Graph g = Graph.getUnit();
    private GraphMap gm = GraphMap.getUnit();
 
    public void load(){
        Graph.setUnit(g);
        GraphMap.setUnit(gm);
    }
    
}
