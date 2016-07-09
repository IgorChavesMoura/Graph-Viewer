package observer;

import model.GraphMap;
import view.panel.Panel;
import model.Vertex;

/** Observador responsável por receber uma notificação e gerenciar a
 *  coloração de vértices na interface gráfica.
 */
public class ObserverGraphMapPaint implements Observer{

    
    @Override
    /** Ver documentação de Observer */
    public void update(ObservableVertex o){
        Vertex u = o.getState();
        GraphMap g = GraphMap.getUnit();
        g.put(u, color);
        pan.repaint();
    }// update

    /** recebe o estado do observavel
     * @param newColor estado */
    public ObserverGraphMapPaint(int newColor){
        color = newColor;
    }
    
    /** altera o estado.
     * @param newColor novo estado
     */
    public void setColor(int newColor){
        color = newColor;
    }

    /** recebe o estado vindo de um observavel */
    private int color;
    /** painel central de desenho */
    private Panel pan = Panel.getUnit();        
}// ObserverGraphMapPaint