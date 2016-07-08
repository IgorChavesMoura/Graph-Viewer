package thread;

import control.Algorithm;
import model.Graph;
import control.ResidualGraph;
import model.Vertex;

public class ThreadAlgorithm implements Runnable{
    
	Thread t;
	Vertex u, v;
	int state;

	public void interrupt(){
            t.interrupt();
	}
        
        public Thread getThread(){
            return t;
        }
	
	// UAT? EOQ?
	public boolean isAlive(){
		return t.isAlive();
	}

	public ThreadAlgorithm(Vertex u, int status){
		t = new Thread(this, "BFS");
		this.u = u;
		state = status;
		t.start();
	}// constructor

	public ThreadAlgorithm(Vertex u, Vertex v){
		t = new Thread(this, "BFS");
		this.u = u;
                this.v = v;
		state = 5;
		t.start();
	}// constructor        
        
	public ThreadAlgorithm(){
		t = new Thread(this, "DFS");
		t.start();
		state = 1;
	}// constructor

    @Override
	public void run(){
            Algorithm alg = Algorithm.getUnit();
		try{
			if(state == 0)
				alg.BFS(u);
			if(state == 1)
				alg.DFS();
			if(state == 3)
				alg.Prim(u);
			if(state == 4)
				alg.Dijkstra(u);
                        if(state == 5)
                                ResidualGraph.getUnit().Edmonds_Karp(u, v);
                        if(state == 6)
                            ResidualGraph.getUnit().BFS(u);
		} catch(InterruptedException e){}
	}// run
}// ThreadAlgorithm
