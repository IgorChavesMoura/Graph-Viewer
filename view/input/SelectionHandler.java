package view.input;

import view.panel.Panel;
import model.Graph;
import model.Vertex;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.util.*;
import view.FrameVertex;
import model.GraphMap;
import view.panel.PanelStatus;

public class SelectionHandler extends MouseAdapter{

	private boolean isSelected = false, isSelectedByClick;
	private Panel pan = Panel.getUnit();
	private Vertex buffer = null;
	private int clicksCount;

        public Vertex getBuffer(){
            return buffer;
        }
        
        @Override
	public void mouseMoved(MouseEvent e){
		PanelStatus.getUnit().setCursor("[" + e.getX() + "," + e.getY() + "]");
	}
        
        public boolean isSelectedByClick(){
            return isSelectedByClick;
        }

        @Override
	public void mouseClicked(MouseEvent e){
            GraphMap g = GraphMap.getUnit();
            buffer = g.isOn(e.getX(), e.getY());
            String buf = ActionKeyboard.getUnit().getMultiSelectionBuffer();
            if(!GraphMap.getUnit().isAlgorithmRunning()){
                    resetSelection();
                    if(buffer != null){
                        g.put(buffer, 2);
                        
                        clicksCount = e.getClickCount();
                        if(clicksCount == 2)
                            new FrameVertex();
                    } else {
                        isSelectedByClick = false;
                    }// if-else
            }// if
            bufferInfo();
            pan.repaint();
	}

	public void bufferInfo(){
		if(buffer != null){
			PanelStatus.getUnit().setInfo("[" + buffer + "]");
		} else{
			PanelStatus.getUnit().setInfo("info");
		}// if-else
	}

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
            //MultiSelection mul = MultiSelection.getUnit();
            if(!GraphMap.getUnit().isAlgorithmRunning()){ // && mul.multiEmpty()){
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
                        //MultiSelection.getUnit().substituteOnBox(e);
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

	// Singleton
	private static SelectionHandler unit;

	private SelectionHandler(){}

	public static SelectionHandler getUnit(){
		if(unit == null)
			unit = new SelectionHandler();
		return unit;
	}

}// SelectionHandler