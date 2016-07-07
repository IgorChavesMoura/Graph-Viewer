package observer;

import model.GraphMap;
import view.panel.Panel;
import model.Vertex;
import view.*;

public class ObserverGraphMapPaint implements Observer{

	private int color;
	private Panel pan = Panel.getUnit();

	public void update(ObservableVertex o){
		Vertex u = o.getState();
		GraphMap g = GraphMap.getUnit();
		g.put(u, color);
		pan.repaint();
	}// update

	public ObserverGraphMapPaint(int newColor){
		color = newColor;
	}

	public void setColor(int newColor){
		color = newColor;
	}
}// ObserverGraphMapPaint