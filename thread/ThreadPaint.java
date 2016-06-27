package thread;

import view.*;

public class ThreadPaint implements Runnable{
	Thread t;

	public ThreadPaint(){
		t = new Thread(this, "Paint");
	}// constructor

	public void run(){
		Panel.getUnit().repaint();
		try{
			t.sleep(250);
		} catch(InterruptedException e){
			System.out.println(t +": " + e);
		}
	}
}