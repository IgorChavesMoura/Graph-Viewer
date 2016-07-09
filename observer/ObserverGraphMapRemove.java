package observer;

import model.GraphMap;
import model.Vertex;

/** observer que remove as configurações gráficas de um vertice */
public class ObserverGraphMapRemove implements Observer{
    
        @Override
	public void update(ObservableVertex o){
		Vertex u = o.getState();
		GraphMap g = GraphMap.getUnit();
		g.rem(u);
	}// update
}// ObserverGraphMapRemove