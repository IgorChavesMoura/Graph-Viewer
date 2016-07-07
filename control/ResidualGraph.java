/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Graph;
import model.Vertex;
import observer.ObservableGraphMap;
import observer.ObserverGraphMapPaint;
import model.GraphMap;
import view.panel.Panel;
import view.panel.PanelStatus;
import thread.ThreadAlgorithm;
import thread.ThreadPaint;

/**
 *
 * @author anderson
 */
public class ResidualGraph{

    public static ResidualGraph getUnit(){
        if(unit == null)
            unit = new ResidualGraph();
        return unit;
    }
    
    public boolean isRunning(){
        return isRunning;
    }
    
    public void setRunning(boolean b){
        isRunning = b;
    }
    
    private ResidualGraph() {

    }

    public void startFlow(){
        GraphMap.getUnit().setAlgorithmRunning(true);
        for(Vertex u : set){
            for (Vertex v : u.getAdj()) {
                u.putFlow(v,0);
            }
        }
    }
    
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
            minimumCapacity = Integer.MAX_VALUE;
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
                System.out.print(capac + " →");
                if(capac < minimumCapacity)
                    minimumCapacity = capac;
                temp = pred;
            }
            System.out.println();
            temp = t;
            while(temp.getPredecessor() != null){
                pred = temp.getPredecessor();
                pred.putFlow(temp, minimumCapacity + pred.getFlow(temp));
                temp = pred;                    
            }

        }// while
        resetVertexPainting();
        getMaximumFlowValue(s);
        Panel.getUnit().repaint();
    }
    
    public void getMaximumFlowValue(Vertex u){
        PanelStatus sp = PanelStatus.getUnit();
        int flow = 0;
        for(Vertex x : u.getAdj()){
            flow += u.getFlow(x);
        }
        sp.setAction("Maximum Flow Value = " + flow);
    }
    
    public void resetVertexPainting(){
        Map<String, Integer> map = GraphMap.getUnit().getMapSelection();
        Set<String> subset = map.keySet();
        isAugmentedPath = true;
        
        for(String s : subset){
            map.put(s, 1);
        }
    }
    
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

    public void BFS(Vertex s) throws InterruptedException{
        Vertex v, u;
        int i,j;

        ObservableGraphMap obs = new ObservableGraphMap(null);
        ObserverGraphMapPaint ob = new ObserverGraphMapPaint(3);
        obs.attach(ob);

        LinkedList<Vertex> F;
        GraphMap.getUnit().setAlgorithmRunning(true);

        for (Vertex x : set){
            x.setPredecessor(null);
            x.setDistance(-1); //-1 representa infinito
            x.setColor(0);
            PanelStatus.getUnit().setAction("Color ["+ x +"] = white");
            handleThread(ob, obs, 3, x);
        }

        s.setDistance(0);
        s.setColor(2);
        PanelStatus.getUnit().setAction("Color ["+ s +"] = black");
        handleThread(ob, obs, 5, s);

        F = new LinkedList<Vertex>();
        F.add(s);

        try{
        int capacity;
        ListIterator iter;
        while(!F.isEmpty())
        {
            u = F.removeFirst();
            iter = u.getAdj().listIterator();
            while(iter.hasNext())
            {
                v = (Vertex)iter.next();
                capacity = u.getWeight(v) - u.getFlow(v);
                if(v.getColor()==0 && capacity > 0 && u.getColor() != 0)
                {
                    v.setColor(2);
                    v.setPredecessor(u);
                    v.setDistance(u.getDistance() + 1);
                    F.add(v);
                    PanelStatus.getUnit().setAction("Color ["+ v +"] = black; d[" + v + "] = " + v.getDistance());
                    handleThread(ob, obs, 5, v);
                }
            }
            u.setColor(2);
            PanelStatus.getUnit().setAction("Color ["+ u +"] = black");
            handleThread(ob, obs, 5, u);
        }
        
        if(end != null && end.getColor() == 0){
            isAugmentedPath = false;
        } else{
            isAugmentedPath = true;            
        }
        
        } catch(NullPointerException ex){
            isAugmentedPath = false;
        }
        
    }

    private static ResidualGraph unit;    
    ArrayList<Vertex> set = Graph.getUnit().getV();
    private boolean isAugmentedPath = true, isRunning = false;
    private int minimumCapacity = Integer.MAX_VALUE;
    private Vertex end;
    private ThreadAlgorithm thread;    
    private boolean interruption;    
}
