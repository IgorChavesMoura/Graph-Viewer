package view.input;

import view.panel.Panel;
import model.Graph;
import model.Vertex;

import java.awt.event.*;

import java.util.*;
import view.FrameVertex;
import model.GraphMap;
import view.panel.PanelStatus;

/** trata dos eventos de seleção do mouse */
public class SelectionHandler extends MouseAdapter{

    /** 
     * @return o vertice selecionado */
    public Vertex getBuffer(){
        return buffer;
    }

    @Override
    public void mouseMoved(MouseEvent e){
            PanelStatus.getUnit().setCursor("[" + e.getX() + "," + e.getY() + "]");
    }


    @Override
    public void mouseClicked(MouseEvent e){
        GraphMap g = GraphMap.getUnit();
        buffer = g.isOn(e.getX(), e.getY());
        if(!GraphMap.getUnit().isAlgorithmRunning()){
                resetSelection();
                if(buffer != null){
                    g.put(buffer, 2);

                    clicksCount = e.getClickCount();
                    if(clicksCount == 2)
                        new FrameVertex();
                }// if
               
        }// if
        bufferInfo();
        pan.repaint();
    }

    /** altera o valor de info no painel de status colocando o nome do vertice
     *  selecionado.
     */
    public void bufferInfo(){
            if(buffer != null){
                    PanelStatus.getUnit().setInfo("[" + buffer + "]");
            } else{
                    PanelStatus.getUnit().setInfo("info");
            }// if-else
    }

    /**
     * pinta todos os vertices de preto quando se clica em um lugar que nao e
     * em cima de um vertice
     */
    public void resetSelection(){
        GraphMap g = GraphMap.getUnit();
        Map<String, Integer> map = g.getMapSelection();
        Set<String> set = map.keySet();
        for(String x : set)
                g.put(Graph.getUnit().getVertex(x), 1);
    }

    @Override
    public void mouseDragged(MouseEvent e){
        GraphMap g = GraphMap.getUnit();
        if(!GraphMap.getUnit().isAlgorithmRunning()){
            resetSelection();
        }
        if(!e.isAltDown()){
            if(!isSelected){
                buffer = g.isOn(e.getX(), e.getY());
                isSelected = true;
            }
            bufferInfo();
            if(buffer != null){
                if(!GraphMap.getUnit().isAlgorithmRunning()){
                    g.put(buffer, 2);
                }// if
                g.setXY(buffer, e.getX() - Panel.SIZE/2, e.getY() - Panel.SIZE/2);
            }// if
            pan.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e){
            isSelected = false;
            buffer = null;
    }

    /** construtor privado */
    private SelectionHandler(){}

    /** 
     * @return retorna a unica instancia da classe */
    public static SelectionHandler getUnit(){
            if(unit == null)
                    unit = new SelectionHandler();
            return unit;
    }

    /** unica instancia da classe. */
    private static SelectionHandler unit;        
    /** flag que indica se um vertice esta seleciondado. */
    private boolean isSelected = false;
    /** painel central de desenho. */
    private Panel pan = Panel.getUnit();
    /** vertice que esta selecionado. */
    private Vertex buffer = null;
    /** guarda a quantidade de clicks durante uma sequencia consecutiva de clicks */
    private int clicksCount;        
}// SelectionHandler