import view.Frame;
import model.*;

public class Main{

	public static void main(String[] args){
		new Frame();
		Graph g = Graph.getUnit();

		g.add();
		g.add();
		g.add();
		g.add();
		g.add();
		g.add();

		g.addAdj(0, 1);
		g.addAdj(0, 2);
		g.addAdj(1, 2);
		g.addAdj(2, 2);
		g.addAdj(4, 2);
		g.addAdj(3, 5);
		g.addAdj(0, 3);
		g.addAdj(1, 4);

		g.print();
	}// main

}// Main