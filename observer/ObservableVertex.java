package observer;

import model.Vertex;

/** estende Observable
 *  utiliza o padrão de projeto decorators
 */
public interface ObservableVertex extends Observable{
    /**
     * retorna o atributo estado de quem implementa esta interface
     * que no caso e um observavel de vertice
     * @return   
     */
    public Vertex getState();
}// ObservableVertex