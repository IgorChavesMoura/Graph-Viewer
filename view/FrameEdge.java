package view;

import model_control.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class FrameEdge extends JFrame{

	private JLabel label1 = new JLabel("Set the edge ends");
	private JTextField text1 = new JTextField(10), text2 = new JTextField(10);
	private JButton ok = new JButton("ok"), cancel = new JButton("cancel");
	private String e1, e2;
	private Panel pan = Panel.getUnit();


	public FrameEdge(int n){
		super("edit edge");
		setLayout(new FlowLayout());

		add(label1);
		add(text1);
		add(text2);
		add(ok);
		add(cancel);

		if(n == 1){
			RemoveHandler rh = new RemoveHandler();
			ok.addActionListener(rh);
			cancel.addActionListener(rh);
		} else{
			TextFieldHandler t = new TextFieldHandler();
			ok.addActionListener(t);
			cancel.addActionListener(t);
		}

		setResizable(false);
		setVisible(true);
		setSize(150,100);
		setLocation(600,200);
	}

	public void analyze(int x){
		Graph g = Graph.getUnit();
		if(g.getVertex(e1) == null || g.getVertex(e2) == null)
			JOptionPane.showMessageDialog(null, "One of ends doesn't exist");
		else switch(x){
			case 1:
				g.addEdge(g.getVertex(e1), g.getVertex(e2));
				StatusPanel.getUnit().setAction("added edge: (" + g.getVertex(e1) + ", " + g.getVertex(e2) + ")");
				break;
			case 2:
				g.removeEdge(g.getVertex(e1), g.getVertex(e2));
				StatusPanel.getUnit().setAction("removed edge: (" + g.getVertex(e1) + ", " + g.getVertex(e2) + ")");
				break;
		}// switch
	}// analyze

	private class TextFieldHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == ok){
				e1 = text1.getText();
				e2 = text2.getText();
				analyze(1);
			}
			setVisible(false);
			pan.repaint();
		}// actionPerformed
	}// TextFieldHandler

	private class RemoveHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == ok){
				e1 = text1.getText();
				e2 = text2.getText();
				analyze(2);
			}
			setVisible(false);
			pan.repaint();
		}// actionPerformed
	}

}