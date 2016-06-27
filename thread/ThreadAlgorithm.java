package thread;

import model_control.*;

public class ThreadAlgorithm implements Runnable{

	Thread t;
	Vertex u;
	int state;

	public ThreadAlgorithm(Vertex u, int status){
		t = new Thread(this, "BFS");
		this.u = u;
		state = status;
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
			if(state == 2)
				Graph.getUnit().extraBFS(u);
		} catch(InterruptedException e){}
	}
}