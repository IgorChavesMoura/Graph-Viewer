package observer;

import model_control.*;

public interface Observable{
	public void attach(Observer o);
	public void detach(Observer o);
	public void send();

	public Vertex getState();
}// Observable