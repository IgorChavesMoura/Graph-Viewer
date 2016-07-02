package thread;

import view.*;

public class ThreadPaint implements Runnable{
	Thread t;

	int speed = 500;

	private static ThreadPaint unit;
	public static ThreadPaint getUnit(){
		if(unit == null)
			unit = new ThreadPaint();
		return unit;
	}

	public void setSpeed(int newSpeed){
		speed = newSpeed;
	}

	private ThreadPaint(){
		t = new Thread(this, "Paint");
	}// constructor

	public boolean isAlive(){
		return t.isAlive();
	}

	public void run(){
		Panel.getUnit().repaint();
		try{
			t.sleep(speed);
		} catch(InterruptedException e){
			System.out.println(t +": " + e);
		}
	}
}