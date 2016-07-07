package model;

import java.util.*;
import java.io.*;

/** Classe para um objeto Vértice, que tem um nome, uma cor, uma distância, e outros atributos. 
  * @version 1.0
  * @since 0.1
  */
public class Vertex implements Serializable
{
    /** Método para configurar o fluxo inteiro w que chega ao vértice u.
      * @param u - vértice cujo fluxo chega.
      * @param w - valor inteiro do fluxo que chega em u */
    public void putFlow(Vertex u, int w){
        flow.put(u.getName(), w);
    }

    /** Método que retorna o valor de fluxo que chega em u.
      * @return int - valor de fluxo que chega em u. */
    public int getFlow(Vertex u){
        return flow.get(u.getName());
    }

    /** Método que configura a cor do vértice. 
      * @params colour - inteiro que indica a cor do vértice */            
    public void setColor(int colour){
        color = colour;
    }

    /** Método que retorna a cor do vértice.
      * @return int - cor do vértice. */    
    public int getColor(){
        return color;
    }
    /** @deprecated */
    public boolean getParallel(){
        return isParallel;
    }

    /** @deprecated */
    public void setParallel(boolean b){
        isParallel = b;
    }    
    
    /** Método que configura . do vértice.
      * @param 
      */
    public void setName(String name){
        this.name = name;
    }
    /** Construtor da classe Vertex. */
    public Vertex(){
        Adj = new ArrayList<>();
    }
    
    /** Método que configura . do vértice.
      * @param 
      */
    public void putWeight(Vertex u, int w){
        weight.put(u.getName(), w);
    }

    /** Método que retorna o peso que aresta cujo o vértice é extremidade final.
      * @return int - peso da aresta. */
    public int getWeight(Vertex u){
        return weight.get(u.getName());
    }
    /** Construtor da classe Vertex.
      * @params name = nome do vertice. */
    public Vertex(String name){
        this();
        this.name = name;
    }

    /** Método que retorna o nome do vértice.
      * @return String - nome do vértice. */
    public String getName(){
        return name;
    }

    /** Método que retorna o tempo de descoberta do vértice.
      * @return int - tempo de descoberta. 
      * @deprecated  getDiscoveryTime() é o mais atual. */
    public int getInit(){
        return discoveryTime;
    }

    /** Método que retorna o tempo de finalização do vértice.
      * @return int - tempo de finalização.
      * @deprecated getFinalTime() é o mais atual. */
    public int getFinal(){
        return finalTime;
    }
    
    /** Método que retorna a lista de adjacência do vértice.
      * @return List<Vertex> - lista de adjacência*/
    public List<Vertex> getAdj(){
        return Adj;
    }
    
    /** Método que configura a distância do vértice.
      * @param d = nova distância
      */
    public void setDistance(int d){
        distance = d;
    }      
    
    /** Método que retorna a distância do vértice.
      * @return int - distância do vértice*/
    public int getDistance(){
        return distance;
    }
    
    
    /** Método que configura o grau de entrada do vértice.
      * @param x - inteiro com o valor do grau de entrada
      */
    public void setIn(int x){
        in = x;
    }
    
    
    /** Método que configura o grau de saída do vértice.
      * @param x - inteiro com o valor do grau de saída
      */
    public void setOut(int x){
        out = x;
    }
    
    /** Método que retorna o grau de entrada do vértice.
      * @return int - grau de entrada do vértice. */
    public int getIn(){
        return in;
    }
    /** Método que retorna o grau de saída do vértice.
      * @return int - grau de saída do vértice. */
    public int getOut(){
        return out;
    }
    
    /** Método que sobrescreve o método toString() da classe Object, configurando um polimorfismo de sobrecarga. */  
    @Override
    public String toString(){
        return getName();
    }
    
    /** Método que configura o vértice predecessor do vértice.
      * @param v - predecessor
      */
    public void setPredecessor(Vertex v){
        predecessor = v;
    }       
    /** Método que retorna o vértice predecessor do vértice.
      * @return Vertex - predecessor. */
    public Vertex getPredecessor(){
        return predecessor;
    }
    /** Método que retorna o tempo de descoberta do vértice.
      * @return int - tempo de descoberta. */
    public int getDiscoveryTime(){
        return discoveryTime;
    }        
    
    /** Método que configura o tempo de descoberta do vértice.
      * @param time - tempo de descoberta
      */
    public void setDiscoveryTime(int time){
        discoveryTime = time;
    }       
    /** Método que retorna o tempo de finalização do vértice.
      * @return int - tempo de finalização. */
    public int getFinalTime(){
        return finalTime;
    }        
    
    /** Método que configura o tempo de finalização do vértice.
      * @param time - tempo de finalização
      */
    public void setFinalTime(int time){
        finalTime = time;
    }        
    /** Esse método retorna se o vértice está na árvore geradora mínima ou não.
      * boolean - verdadeiro se o vértice está na AGM e falso caso contrário. */
    public boolean isInMST(){
        return isInMST;
    }        
    
    /** Esse método configura se o vértice está na árvore geradora mínima ou não.
      * b - verdadeiro se o vértice está na AGM e falso caso contrário. */
    public void isInMST(boolean b){
        isInMST = b;
    }
        
    /** ANDERSON, VEJA AQUI DEPOIS */
    static final long serialVersionUID = 1L;

    /** Tempo de descoberta do vértice, utilizado no algoritmo DFS */
    int discoveryTime;

    /** Tempo de finalização do vértice, utilizado no algoritmo DFS */
    int finalTime;

    /** Variável que indica se o vértice está na árvore geradora mínima ou não */
    boolean isInMST;

    /** Variável que indica se o vértice é paralelo ou não (?) */
    boolean isParallel;

    /** Distância do vértice, atributo utilizado nos algoritmos BFS, Prim e Dijkstra. */
    int distance;

    /** Grau de entrada do vértice*/ 
    int in = 0;

    /** Grau de saída do vértice */
    int out = 0;

    /** Cor do vértice */
    int color;

    /** Nome do vértice */
    String name;

    /** Vértice predessor do vértice */
    Vertex predecessor;

    /** Lista de adjacência do vértice */
    ArrayList<Vertex> Adj;

    /** Função de peso do vértice com seus vizinhos */
    Map<String, Integer> weight = new HashMap<>();

    /** Função de fluxo do vértice com seus vizinhos */
    Map<String, Integer> flow = new HashMap<>();

    /** Função de paralelismo do vértice com seus vizinhos (?) */
    Map<String, Boolean> parallel = new HashMap<>();

}