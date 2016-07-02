/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model_control;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import observer.ObservableGraphMap;
import observer.ObserverGraphMapPaint;
import view.GraphMap;
import view.Panel;
import view.StatusPanel;
import thread.ThreadAlgorithm;
import thread.ThreadPaint;

/**
 *
 * @author anderson
 */
public class ResidualGraph extends Graph{

    private static ResidualGraph unit;
    public static ResidualGraph getUnit(){
        if(unit == null)
            unit = new ResidualGraph(true);
        return unit;
    }
    
    ArrayList<Vertex> set = Graph.getUnit().getV();
    
    private boolean isAugmentedPath = true, isRunning = false;
    private int minimumCapacity = Integer.MAX_VALUE;
    Vertex end;
    
    public boolean isRunning(){
        return isRunning;
    }
    
    public void setRunning(boolean b){
        isRunning = b;
    }
    
    private ResidualGraph(boolean pdirected) {
        super(pdirected);
    }

    public void startFlow(){
        GraphMap.getUnit().setAlgorithmRunning(true);
        for(Vertex u : set){
            for (Vertex v : u.Adj) {
                u.putFlow(v,0);
            }
        }
    }
    private ThreadAlgorithm thread;
    
    private boolean interruption;
    
    @Override
    public void setInterruption(boolean b){
        interruption = b;
    }
    
    public void Edmonds_Karp(Vertex s, Vertex t) throws InterruptedException{
        startFlow();
       
        end = t;
        Vertex temp, pred;
        int capac;
        isRunning = true;
        
        while(isAugmentedPath){
            System.out.println("RUNNING");
            try{
                thread = new ThreadAlgorithm(s, 6);
                thread.getThread().join();
            } catch (InterruptedException ex){
                JOptionPane.showMessageDialog(null, "Error Thread");
            }
            
            if(interruption){
                interruption = !interruption;
                throw new InterruptedException();
            }
            temp = t;
            while(temp.getPredecessor() != null){
                pred = temp.getPredecessor();
                capac = pred.getWeight(temp) - pred.getFlow(temp);
                if(capac < minimumCapacity)
                    minimumCapacity = capac;
                temp = pred;
            }

            temp = t;
            while(temp.getPredecessor() != null){
                pred = temp.getPredecessor();
                pred.putFlow(temp, minimumCapacity + pred.getFlow(temp));
                temp = pred;                    
            }

        }// while
        resetVertexPainting();
        Panel.getUnit().repaint();
    }
    
    public void resetVertexPainting(){
        Map<String, Integer> map = GraphMap.getUnit().getMapSelection();
        Set<String> subset = map.keySet();
        isAugmentedPath = true;
        
        for(String s : subset){
            map.put(s, 1);
        }
    }
    
    @Override
    public void handleThread(ObserverGraphMapPaint o, ObservableGraphMap ob, int color, Vertex u)
    throws InterruptedException{
        o.setColor(color);
        ob.setState(u);
        ob.send();
        ThreadPaint.getUnit().run();
        if(interruption){
            throw new InterruptedException();
        }
    }            

    @Override
    public void BFS(Vertex s) throws InterruptedException{
        Vertex v, u;
        int i,j;

        ObservableGraphMap obs = new ObservableGraphMap(null);
        ObserverGraphMapPaint ob = new ObserverGraphMapPaint(3);
        obs.attach(ob);

        LinkedList<Vertex> F;
        GraphMap.getUnit().setAlgorithmRunning(true);

        for (Vertex x : set){
            x.predecessor = null;
            x.distance = -1; //-1 representa infinito
            x.color= 0;
            StatusPanel.getUnit().setAction("Color ["+ x +"] = white");
            handleThread(ob, obs, 3, x);
        }

        s.distance = 0;
        s.color = 2;
        StatusPanel.getUnit().setAction("Color ["+ s +"] = black");
        handleThread(ob, obs, 5, s);

        F = new LinkedList<Vertex>();
        F.add(s);

        try{
        int capacity;
        ListIterator iter;
        while(!F.isEmpty())
        {
            u = F.removeFirst();
            iter = u.Adj.listIterator();
            while(iter.hasNext())
            {
                v = (Vertex)iter.next();
                capacity = u.getWeight(v) - u.getFlow(v);
                if(v.color==0 && capacity > 0 && u.color != 0)
                {
                    v.color = 2;
                    v.predecessor = u;
                    v.distance = u.distance + 1;
                    F.add(v);
                    StatusPanel.getUnit().setAction("Color ["+ v +"] = black; d[" + v + "] = " + v.distance);
                    handleThread(ob, obs, 5, v);
                }
            }
            u.color = 2;
            StatusPanel.getUnit().setAction("Color ["+ u +"] = black");
            handleThread(ob, obs, 5, u);
        }
        
        if(end != null && end.color == 0){
            isAugmentedPath = false;
        } else{
            isAugmentedPath = true;            
        }
        
        } catch(NullPointerException ex){
            isAugmentedPath = false;
        }
        
    }

}
