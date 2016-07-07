package observer;
import model.Vertex;

public class ObservableGraphMap implements ObservableVertex{

	private Observer o;
	private Vertex state;

	public ObservableGraphMap(Vertex newState){
		state = newState;
	}// Constructor

	public void attach(Observer o){
		this.o = o;
	}// attach

	public void detach(Observer o){
		this.o = null;
	}// detach

	public void setState(Vertex u){
		state = u;
	}

	public Vertex getState(){
		return state;
	}

	public void send(){
		o.update(this);
	}// send

}