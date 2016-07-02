package model_control;

import java.util.*;

public class DistanceComparator implements Comparator<Vertex> {
	public int compare(Vertex o1, Vertex o2){
		if (o1 != null && o2 !=null){
			Vertex v1= o1;
			Vertex v2= o2;
			Integer d1 = v1.distance;
			Integer d2 = v2.distance;

			System.out.println("(" + v1 + ", " + v2 + ")");

			if(d1 < d2)
				return -1;
			if(d1 > d2)
				return 1;
			return 0;
		}
		else return 0;
	}
}