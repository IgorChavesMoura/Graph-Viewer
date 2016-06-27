package observer;

import model_control.*;
import view.*;

public class ObserverGraphMapRemove implements Observer{
	public void update(Observable o){
		Vertex u = o.getState();
		GraphMap g = GraphMap.getUnit();
		g.rem(u);
	}// update
}// ObserverGraphMapRemove