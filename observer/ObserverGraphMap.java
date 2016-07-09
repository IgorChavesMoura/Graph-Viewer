package observer;

import model.GraphMap;
import model.Point2D;
import model.Vertex;

public class ObserverGraphMap implements Observer{
        @Override
        /** Ver documentação de Observer */
	public void update(ObservableVertex o){
		Vertex u = o.getState();
		GraphMap g = GraphMap.getUnit();
		g.put(u, new Point2D((int) (Math.random()*800), (int) (Math.random()*400)));
		g.put(u, 1);
	}// update
}// ObserverGraphMap