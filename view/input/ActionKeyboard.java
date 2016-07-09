package view.input;

import view.panel.Panel;
import java.awt.event.*;
import model.Graph;

import view.Frame;
import view.FrameEdge;
import view.FrameEdgeEdit;

/*
	shift + a: add vertex
	shift + e: add edge
*/
/** 
 * trata os eventos recebidos do teclado
 * implementa o padrão de projeto de software Singleton.
 */
public class ActionKeyboard extends KeyAdapter{

    /** construtor privado */
    private ActionKeyboard(){}
    
    /**
     * @return retorna a única instância da classe  */
    public static ActionKeyboard getUnit(){
            if(unit == null)
                    unit = new ActionKeyboard();
            return unit;
    }// constructor

    @Override
    public void keyTyped(KeyEvent e){
            key = e.getKeyChar();
            modifier = KeyEvent.getKeyModifiersText( e.getModifiers());
    }// keyPressed

    @Override
    public void keyPressed(KeyEvent e){
        key = e.getKeyChar();
        if((int)key >= 96)
            key = (char)((int)key - 32);

        modifier = KeyEvent.getKeyModifiersText( e.getModifiers());

        if(modifier.equals("Shift")){
            if(key == 'A'){
                Frame.getUnit().addVertex();
            } if (key == 'E'){
                if(!Graph.getUnit().isWeighted())
                    new FrameEdge(1);
                else
                    new FrameEdgeEdit(2);                    
            } if(key == 'X'){
                Frame.getUnit().removeVertex();
            } if (key == 'Z'){
                new FrameEdge(2);
            }                
        }// if
        Panel.getUnit().repaint();
    }
    @Override
    public void keyReleased(KeyEvent e){
        key = e.getKeyChar();
        modifier = KeyEvent.getKeyModifiersText( e.getModifiers());
    }

    /** única instância da classe */
    private static ActionKeyboard unit;
    /** recebe o nome da tecla de modificação pressionada */
    String modifier;
    /** recebe o valor de uma tecla comum que foi pressionada */
    char key;
}// ActionKeyboard