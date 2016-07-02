package thread;

import model_control.*;

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

	public void run(){
		try{
			if(state == 0)
				Graph.getUnit().BFS(u);
			if(state == 1)
				Graph.getUnit().DFS();
			if(state == 3)
				Graph.getUnit().Prim(u);
			if(state == 4)
				Graph.getUnit().Dijkstra(u);
                        if(state == 5)
                                ResidualGraph.getUnit().Edmonds_Karp(u, v);
                        if(state == 6)
                            ResidualGraph.getUnit().BFS(u);
		} catch(InterruptedException e){}
	}
}