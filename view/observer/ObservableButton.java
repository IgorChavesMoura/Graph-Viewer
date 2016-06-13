package view.observer;

import java.util.ArrayList;

public class ObservableButton implements Observable{

	private ArrayList<Observer> set = new ArrayList<>();
	private int state;

	// *** Constructor *** //
	public ObservableButton(int state){
		this.state = state;
	}// Constructor

	// *** Implementations *** //
	public void attach(Observer o){
		set.add(o);
	}// attach

	public void detach(Observer o){
		set.remove(o);
	}// detach

	public void send(){
		for(Observer o : set)
			o.update(this);
	}// send

	public int getState(){
		return state;
	}// getState

}// ObservableButton