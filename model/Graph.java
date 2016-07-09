package model;

import java.util.ArrayList;

import observer.*;

import java.io.*;
import java.util.ConcurrentModificationException;

/** Classe para um objeto Graph que tem uma única instância e contem um conjunto de vértices e outros atributos.
  * Essa classe implementa o padrão de projeto Singleton.
  * @link https://en.wikipedia.org/wiki/Graph_(discrete_mathematics) 
  * @version 1.0
  * @since 0.1 
*/
public class Graph implements Serializable{
        
    // *** Singleton Instance *** //
    /** Esse método retorna a instância única de Graph.
      * @return Graph - instância de um grafo.
    */
    public static Graph getUnit(){
        if(unit == null)
            unit = new Graph(true);
        return unit;
    }

    /** Esse método seta a instância de Graph. 
      * @param g - Objeto de um grafo 
    */
    public static void setUnit(Graph g){
        unit = g;
    }
    
    /** Esse método configura se o peso das arestas do grafo é aleatório ou não.
      * @param b verdadeiro se o peso das arestas do grafo é aleatório ou falso caso contrário.  
    */
    public void setRandWeightValues(boolean b){
        randWeightValues = b;
    }

    /** Esse método retorna se o peso das arestas do grafo é aleatório ou não.
      * @return boolean - variável que indica se o peso das arestas do grafo é aleatório ou não.
    */
    public boolean getRandWeightValues(){
        return randWeightValues;
    }

    /** Esse método configura se o grafo é direcionado ou não.
      * @param direct verdadeiro se o grafo é direcionado e falso caso contrário.
    */
    public void setDirection(boolean direct){
        directed = direct;
    }
    
    /** Esse método configura se o grafo tem função de peso ou não.
      * @param b verdadeiro se o grafo tem função de peso nas arestas e falso caso contrário.
    */
    public void setWeighted(boolean b){
            isWeighted = b;
    }

    /** Esse método retorna se o grafo tem função de peso ou não.
      * @return indica verdadeiro se existe função de peso e falso caso contrário.
    */
    public boolean isWeighted(){
        return isWeighted;
    }

    /** Método que retorna a instância da coleção de vértices.
      * @return ArrayList<Vertex> - instância da coleção de vértices.
    */
    public ArrayList<Vertex> getV(){
            return V;
    }    
    
    /** Método que retorna se o grafo é direcionado ou não.
      * @return boolean - verdadeiro se o grafo é direcionado e falso caso contrário
    */ 
    public boolean isDirected(){
            return directed;
    }

    /** Método que esvazia a coleção de vértices. */
    public void clear(){
        V.clear();
    }

    /** Método que retorna se a coleção de vértices é vazia ou não.
        @return boolean - verdadeiro se a coleção de vértices é vazia e falso caso contrário
    */
    public boolean vertexEmpty(){
        return V.isEmpty();
    }

    /** Construtor da classe
      * @param pdirected - variável cujo valor é verdadeiro para o grafo ser direcionado e falso caso contrário
    */ 
    protected Graph(boolean pdirected){
        V = new ArrayList<>();
        directed = pdirected;
    }

    /** Construtor da classe.
      * @param n - variável que indica o número inteiro inicial de vértices do grafo.
      * @param pdirected - variável cujo valor é verdadeiro para o grafo ser direcionado e falso caso contrário. 
    */
    protected Graph(int n, boolean pdirected){
        this(pdirected);
        Integer i;
        for(i=1;i<=n;i++){
            addVertex(i.toString());
        }
    }
    // *** Constructors *** //

    /**Método que retorna a instância de um vértice do grafo.
      *@param name - nome do vértice a ser retornado. 
      *@return Vertex - vértice com o nome entrado ou null caso o vértice não exista. 
      */    
    public Vertex getVertex(String name){
        for(Vertex v : V)
            if(v.getName().equals(name))
                return v;
        return null;
    }
    
    /** Função para adicionar arestas ao grafo.
      * @param u - extremidade inicial da aresta. 
      * @param v - extremidade final da aresta
      */
    public void addEdge(Vertex u, Vertex v)
    {
        if(!existsEdge(u, v)){
            u.getAdj().add(v);
            u.putWeight(v, 1);
            
            u.setOut(u.getOut() + 1);
            v.setIn(v.getIn() + 1);

            if(!Graph.getUnit().isDirected()){
                v.getAdj().add(u);
                v.putWeight(u, 1);
                
                v.setOut(v.getOut() + 1);
                u.setIn(u.getIn() + 1);                
            }
        }
    }

    /** Função para adicionar arestas ao grafo com função de peso.
      * @param u - extremidade inicial da aresta. 
      * @param v - extremidade final da aresta. 
      * @param weight - peso da aresta
      */
    public void addEdge(Vertex u, Vertex v, int weight){
        this.addEdge(u, v);
        u.putWeight(v, weight);
        if(!Graph.getUnit().isDirected())
            v.putWeight(u, weight);
    }

    /** Metódo que retorna se a aresta entrada existe ou não.
      * @param u - extremidade inicial da aresta.
      * @param v - extremidade final da aresta.
      * @return boolean - verdadeiro se a aresta existe e falso caso contrário. 
      */
    public boolean existsEdge(Vertex u, Vertex v){
        Graph g = Graph.getUnit();
        String uN = u.getName(), vN = v.getName();
        if(!g.isDirected())
            return g.getVertex(uN).getAdj().contains(g.getVertex(vN)) || g.getVertex(vN).getAdj().contains(g.getVertex(uN));
        else
            return g.getVertex(uN).getAdj().contains(g.getVertex(vN));
    }
    
    /** Método que remove uma aresta.
      * @param u - extremidade inicial da aresta.
      * @param v - extremidade final da aresta.
      * @throws ConcurrentModificationException 
      */
    public void removeEdge(Vertex u, Vertex v) throws ConcurrentModificationException{
        u.getAdj().remove(v);

        u.setOut(u.getOut() - 1);
        v.setIn(v.getIn() - 1);
        if(!Graph.getUnit().isDirected()){
            v.getAdj().remove(u);
            u.setIn(u.getIn() - 1);
            v.setOut(v.getOut() - 1);
        }
    }

    /** Método para adicionar um vértice ao grafo. 
      * @param pname - nome do novo vértice. 
      */
    public void addVertex(String pname){
        Vertex v = new Vertex(pname);

        V.add(v);

        // Observer tools
        ObservableVertex obs = new ObservableGraphMap(v);
        obs.attach(new ObserverGraphMap());
        obs.send();
        // Observer tools
    }

    /** Método que remove um vértice do grafo.
      * @param u - vértice a ser removido 
      */
    public void removeVertex(Vertex u){        
        for(Vertex v : V){
            if(v != u)
                removeEdge(v, u);
        }// for

        V.remove(u);

        // Observer Tools
        ObservableVertex obs = new ObservableGraphMap(u);
        obs.attach(new ObserverGraphMapRemove());
        obs.send();
        // Observer Tools
    }    


    /** 
     *  chave de identificaçao do arquivo quando carregado e salvo.
     */
    static final long serialVersionUID = 1L;

    /** Instância do grafo. */
    private static Graph unit;

    /** Coleção (ArrayList) contendo os vértices do grafo. */
    ArrayList<Vertex> V;

    /** Variável indicando se o grafo é direcionado ou não. */
    boolean directed;

    /** Variável indicando se o grafo tem função de peso ou não. */
    boolean isWeighted;

    /** Variável indicando se o grafo tem pesos aleatórios ou não. */        
    private boolean randWeightValues;    
}
