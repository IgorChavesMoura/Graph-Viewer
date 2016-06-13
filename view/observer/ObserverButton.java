package view.observer;

// OL
import view.panelWest.*;

public class ObserverButton implements Observer{
	public void update(Observable o){
		PWSU panel = PWSU.getUnit("");

		switch (o.getState()){
			case 1: panel.setText("Editor");
				break;
			case 2: panel.setText("Algorithm");
				break;
			case 3: panel.setText("Configuration");
				break;
		}// switch
	}// update
}// ObserverButton