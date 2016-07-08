package observer;

import model.GraphMap;
import model.Vertex;
import view.*;

public class ObserverGraphMapRemove implements Observer{
	
	public void update(ObservableVertex o){
		Vertex u = o.getState();
		GraphMap g = GraphMap.getUnit();
		g.rem(u);
	}// update
	
}// ObserverGraphMapRemove
