package observer;

import model_control.*;
import view.*;

public class ObserverGraphMap implements Observer{
	public void update(Observable o){
		Vertex u = o.getState();
		GraphMap g = GraphMap.getUnit();
		g.put(u, new Ponto((int) (Math.random()*800), (int) (Math.random()*400)));
		g.put(u, 1);
	}// update
}// ObserverGraphMap