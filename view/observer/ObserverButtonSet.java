package view.observer;

// OL
import view.panelWest.*;

public class ObserverButtonSet implements Observer{
	public void update(Observable o){
		PanelWest pw = PanelWest.getUnit();
		switch (o.getState()){
			case 1: pw.setButton(1);
				break;
			case 2: pw.setButton(2);
				break;
			case 3: pw.setButton(3);
				break;
		}// switch
	}// update
}// ObserverButton