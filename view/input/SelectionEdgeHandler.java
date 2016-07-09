package view.input;

import control.FrameControl;
import view.panel.Panel;
import model.Graph;
import model.Point2D;
import model.Vertex;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import model.GraphMap;

/** trata os eventos de criar uma aresta
 *  implementa o padrao de projeto de software Singleton
 */
public class SelectionEdgeHandler extends MouseAdapter{

    /** construtor privado */
    private SelectionEdgeHandler(){}

    /** 
     * @return retorna a única instância da classe */
    public static SelectionEdgeHandler getUnit(){
            if(unit == null)
                    unit = new SelectionEdgeHandler();
            return unit;
    }// SelectionEdgeHandler    
    
    /**
     * @return retorna o vertice selecionado durante a operaçao de adicionar
     * aresta.
     */
    public Vertex getBuffer(){
            return u;
    }

    /** configura as posiçoes da aresta "preview" que aparece durante
     *  a adiçao de uma aresta.
     */
    public void setEdgeBuffer(int a, int b, int c, int d){
            ex1 = a;
            ey1 = b;
            ex2 = c;
            ey2 = d;
    }// setEdgeBuffer

    /**
     * @return coordenada x de uma extremidade */
    public int getEx1(){return ex1;}
    /**
     * @return coordenada x de uma extremidade */    
    public int getEx2(){return ex2;}
        /**
     * @return coordenada y de uma extremidade */
    public int getEy1(){return ey1;}
        /**
     * @return coordenada y de uma extremidade */
    public int getEy2(){return ey2;}            
    
    // *** Mouse Events ***
    @Override
    public void mouseDragged(MouseEvent e){
            GraphMap g = GraphMap.getUnit();
            Map<String, Point2D> map = g.getMap();
            Point2D p;
            Vertex loop = null;
            if(e.isAltDown() && !GraphMap.getUnit().isAlgorithmRunning()){
                    if(!isDragging){
                            u = g.isOn(e.getX(), e.getY());
                            isDragging = true;
                    }// is not dragging
                    if(u != null){
                            p = map.get(u.getName());
                            setEdgeBuffer(p.getX() + Panel.SIZE/2, p.getY() + Panel.SIZE/2, e.getX(), e.getY());

                            loop = g.isOn(e.getX(), e.getY());
                            if(loop != null && loop.getName().equals(u.getName())){
                                    Panel.getUnit().setIsLoop(true);
                            }else{
                                    Panel.getUnit().setIsLoop(false);
                            }// if cursor is over the edge's tail
                    }// null
            }// altDown
            Panel.getUnit().repaint();
    }// mouseDragged

    @Override
    public void mouseReleased(MouseEvent e){
            GraphMap g = GraphMap.getUnit();
            Graph g2 = Graph.getUnit();
            if(e.isAltDown() && !GraphMap.getUnit().isAlgorithmRunning()){
                    if( u != null){
                            v = g.isOn(e.getX(), e.getY());
                            if(v == null)
                                    setEdgeBuffer(0,0,0,0);
                            else{
                                if(!g2.existsEdge(v, u)){
                                    if(!g2.isWeighted()){
                                            g2.addEdge(u, v);
                                    } else {
                                            setWeight(u, v);
                                    }// if-else
                                }
                            }// if-else
                            Panel.getUnit().repaint();
                    }
                    // Reset
                    isDragging = false;
                    setEdgeBuffer(0,0,0,0);
                    u = null;
                    v = null;
            }// isAltDown
    }// mouseReleased

    /** caso o grafo seja ponderadom ele solicita o valor do peso da aresta
     * @param x extremidade da aresta
     * @param y extremidade da aresta
     */
    public void setWeight(Vertex x, Vertex y){
            Graph g2 = Graph.getUnit();
            String a = JOptionPane.showInputDialog("Set the weight");
            int b;

            try{
                b = getInt(a);
                if(!FrameControl.weightOverflow(b)){
                    g2.addEdge(x,y,b);
                } else {
                    JOptionPane.showMessageDialog(null, "invalid input");
                }
            } catch (NumberFormatException e){}
    }// setWeight

    /** trata de converter uma string para um inteiro
     *  caso o valor nao corresponda a um valor numerico, o metodo lança a
     *  exception NumberFormatException
     * @param a recebe a string a ser convertida
     * @return o valor em inteiro */
    public int getInt(String a) throws NumberFormatException{
            try{
                    return Integer.valueOf(a);
            } catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "unsuported value");
                    throw new NumberFormatException();
            }
    }

    /** única instância da classe */
    private static SelectionEdgeHandler unit;

    /** flag que verifica se o cursor está arrastando uma aresta que sai
     *  de um vértice
     */
    private boolean isDragging = false;
    /** vértices que recebem os valores das aextermidades das arestas */
    private Vertex u = null, v = null;

    /** coordenadas dos dois vertices que compõem a aresta */
    private int ex1 = 0, ex2 = 0, ey1 = 0, ey2 = 0;
        
}// SelectionEdgeHandler