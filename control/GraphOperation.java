package control;

import java.util.ConcurrentModificationException;
import model.Graph;
import model.Vertex;

/** Classe que corresponde a operações com o grafo, como transformar em simples/orientado, com peso/sem peso, etc.
  * @version 1.0
  * @since 1.0
  */
public class GraphOperation {

    /** Método que converte um grafo simples em um orientado.
      */
    public static void simpleOriented(){
        Graph g = Graph.getUnit();
        g.setDirection(true);
        try{
            for(Vertex x: g.getV()){
                    for(Vertex y : x.getAdj()){
                            if(!x.getName().equals(y.getName()))
                                g.removeEdge(y, x);
                    }// scan V adj
            }// scan V set
        } catch (ConcurrentModificationException ex){
            System.out.println("Exception");
        }                
    }

    /** Método que converte um grafo orientado em simples.
      */
    public static void orientedSimple(){
        Graph g = Graph.getUnit();
        for(Vertex x: g.getV()){
            for(Vertex y : x.getAdj()){
                g.addEdge(y, x);
                if(g.isWeighted())
                    y.putWeight(x, x.getWeight(y));
            }// scan V adj
        }// scan V set
        g.setDirection(false);
    }    

    /** transforma o grafo em aleatório. */
    public static void random(){
        int size = (int)(Math.random()*20) + 5;
        Graph g = Graph.getUnit();

        for(Integer index = 0 ; index < size ; index++)
            g.addVertex(index.toString());

        Integer e1, e2;
        Integer weight;
        for(int index = 0 ; index < size*2 ; index++){
            e1 = (int)(Math.random()*100)%size;
            e2 = (int)(Math.random()*100)%size;
            if(!g.isWeighted()){
                g.addEdge(g.getVertex(e1.toString()), g.getVertex(e2.toString()));
            } else {
                if(!g.getRandWeightValues()){
                    g.addEdge(g.getVertex(e1.toString()), g.getVertex(e2.toString()));
                } else {
                    weight = (int)(Math.random()*1000000);
                    g.addEdge(g.getVertex(e1.toString()), g.getVertex(e2.toString()), weight);
                }// if-else
            }// if-else
        }// for
    }
}
