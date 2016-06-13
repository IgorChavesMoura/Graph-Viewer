package view.panelWest;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

// OL
import view.observer.*;

public class PWSM_Button extends JPanel{

	protected JButton  editor = new JButton(new ImageIcon("view/icons/b1.bmp")),
						algorithm = new JButton(new ImageIcon("view/icons/b2.bmp")),
						config = new JButton(new ImageIcon("view/icons/b3.bmp"));
	private Observer obs = new ObserverButton();
	private Observer obs2 = new ObserverButtonSet();
	private static PWSM_Button unit;

	public static PWSM_Button getUnit(){
		if(unit == null)
			unit = new PWSM_Button();
		return unit;
	}// getUnit

	private PWSM_Button(){
		add(editor);
		add(algorithm);
		add(config);

		ButtonHandler handler = new ButtonHandler();
		editor.addActionListener(handler);
		algorithm.addActionListener(handler);
		config.addActionListener(handler);
	}// PWSM_Button

	private class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Observable o = new ObservableButton(0);

			if(e.getSource() == editor){
				o = new ObservableButton(1);
			} else if (e.getSource() == algorithm){
				o = new ObservableButton(2);
			} else if (e.getSource() == config){
				o = new ObservableButton(3);
			}// kaskade

			o.attach(obs);
			o.attach(obs2);
			o.send();
		}// actionPerformed
	}// ButtonHandler

} // PWSM_Butto1