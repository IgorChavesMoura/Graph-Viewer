package model_control;

import java.util.Comparator;

public class DistanceComparator implements Comparator {
	public int compare(Object o1, Object o2){
		if (o1 != null && o2 !=null && o1 instanceof Vertex && o2 instanceof Vertex){
			Vertex v1= (Vertex) o1;
			Vertex v2= (Vertex) o2;
			Integer d1 = v1.distance;
			Integer d2 = v2.distance;
			return (d1.compareTo(d2));
		} 
		else return 0;
	}
}