package view.input;

import view.panel.Panel;
import model.Graph;
import model.Point2D;
import model.Vertex;

import java.awt.event.*;
import java.util.*;
import model.GraphMap;

/** trata eventos de mouse com o objetivo de movimentar a tela
 *  implementa o padrão de projeto de software Singleton.
 */
public class MoveAllHandler extends MouseAdapter{

    /** construtor privado */
    private MoveAllHandler(){}
    /**
     * @return retorna a unica instancia da classe */
    public static MoveAllHandler getUnit(){
            if(unit == null)
                    unit = new MoveAllHandler();
            return unit;
    }

    /** atualiza a tela, o que na realidade e o movimento de todos os objetos
     *  de desenho na tela
     *  Ele captura o ponto inicial de movimento do cursor,
     *  recebe o novo ponto do cursor, captura a reta formada e com base nas
     *  informaçoes de inclinaçao da reta, faz com que todos os objetos sofram
     *  o mesmo movimento retilineo paralelo.
     */
    public void setMoviment(){

        double x,y;
        x = newCenter.getX() - buffer.getX();
        y = newCenter.getY() - buffer.getY();
        int a1=0, a2=0;
        double x1 = (newCenter.getX() - buffer.getX())*(newCenter.getX() - buffer.getX()),
                        x2 = (newCenter.getY() - buffer.getY())*(newCenter.getY() - buffer.getY());
        double slope = (double)(x/y),
                        alpha = Math.atan(slope),
                        radius = Math.sqrt(x1 + x2);


        if(y >= 0){
            for(Vertex a : Graph.getUnit().getV()){
                vertex = map.get(a.getName());
                a1 = (int)(radius*Math.sin(alpha)) + vertex.getX();
                a2 = (int)(radius*Math.cos(alpha)) + vertex.getY();

                GraphMap.getUnit().put(a, new Point2D(a1, a2));
            }
        } else{
            for(Vertex a : Graph.getUnit().getV()){
                vertex = map.get(a.getName());

                a1 = (int)-(radius*Math.sin(alpha)) + vertex.getX();
                a2 = (int)-(radius*Math.cos(alpha)) + vertex.getY();

                GraphMap.getUnit().put(a, new Point2D(a1, a2));
            }
        }// if-else

    }// setMoviment

    @Override
    public void mouseDragged(MouseEvent e){

            if(e.isMetaDown() && !Graph.getUnit().vertexEmpty()){
                    if(!isMoving){
                            isMoving = true;
                            map = GraphMap.getUnit().getMapCopy();
                            buffer = new Point2D(e.getX(), e.getY());
                    }// isMoving
                    newCenter = new Point2D(e.getX(), e.getY());
                    if(map!=null)
                            setMoviment();
                    Panel.getUnit().repaint();
            }// isMetaDown
    }// mouseDragged

    @Override
    public void mouseReleased(MouseEvent e){
            isMoving = false;
    }// mouseReleased

    /** unica instancia da classe */
    private static MoveAllHandler unit;
    /** flag que indica se o mouse esta realizando o movimento */
    private boolean isMoving = false;
    /** buffer: nova posiçao do cursor
     *  newCenter: posiçao inicial do cursor durante a operaçao
     *  vertex: variavel usada para recuperar informaçao inicial de posiçao do
     *  vertice para comparaçao com a nova posiçao.
     */
    private Point2D buffer, newCenter, vertex;
    /** recebe uma copia do mapeamento de coordenadas em GraphMap para evitar
     *  alterar os dados originais ate o fim da operaçao.
     */
    private Map<String, Point2D> map = null;
}
