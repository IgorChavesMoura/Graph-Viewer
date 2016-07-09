package control;

import java.io.File;
import javax.swing.JFileChooser;
import model.Graph;
import model.GraphMap;
import model.Vertex;
import thread.ThreadAlgorithm;
import view.panel.PanelLeft;
import view.panel.PanelMenu;
import view.panel.PanelStatus;

/**
 * Controla as operações vindas da janela para o Grafo
 * @version 1.0
 * @since 0.1
 */
public class FrameControl {
    
    /**
     * abre um arquivo graph, reseta todas as configurações de acordo com as
     * que estavam previstas no arquivo
     * @param fc captura o arquivo*/
    public static void open(JFileChooser fc){
        File f = fc.getSelectedFile();

        PanelStatus.getUnit().setAction("loaded: " + f.getName());

        Graph.setUnit(null);
        GraphMap.setUnit(null);

        OpenSave.loadGraph(f);

        PanelMenu.getUnit().disableAlgorithms();
        PanelMenu.getUnit().enableButtons();
        PanelLeft.getUnit().disableButtons();        
        PanelLeft.getUnit().disableButtonsAlgorithm(true);
    }
    
    /**
     * salva um arquivo no formato graph
     * @param fc cria um arquivo ou substitui um arquivo*/
    public static void save(JFileChooser fc){
        File f = fc.getSelectedFile();
        PanelStatus.getUnit().setAction("saved: " + f.getName());
        OpenSave.saveGraph(f);
    }
    
    /**
     * interrompe um algoritmo. Ele interrompe duas Threads, a do algoritmo
     * propriamente dito e a de pintura dos vertices.
     */
    public static void interruptAlgorithm(){
        if(t != null && t.isAlive()){
            Algorithm.getUnit().setInterruption(true);
            ResidualGraph.getUnit().setInterruption(true);
        }            
    }
    
    /**
     * valida a entrada de vertice
     * @param newName nome de pesquisa ou nome sugerido para adiçao de vertice
     * @return um vertice que possui o mesmo nome ou null
     */
    public static Vertex validateInput(String newName){
        Vertex v = Graph.getUnit().getVertex(newName);        
        return v;
    }
    
    /** executa o algoritmo de Prim
     * @param v raiz da árvore geradora minima*/
    public static void playPrim(Vertex v){
        t = new ThreadAlgorithm(v, 3);
        PanelMenu.getUnit().disableButtons();
        PanelLeft.getUnit().disableButtonsAlgorithm(false);        
    }
    
    /** executa o algoritmo de Dijkstra
     * @param v raiz da arvore de caminhos minimos*/
    public static void playDijkstra(Vertex v){
        t = new ThreadAlgorithm(v, 4);
        PanelMenu.getUnit().disableButtons();
        PanelLeft.getUnit().disableButtonsAlgorithm(false);        
    }

    /** 
     * reseta as configuraçoes do grafo para um novo grafo vazio
     */
    public static void chooseNewFile(){
        PanelMenu.getUnit().enableButtons();
        Graph.setUnit(null);
        GraphMap.setUnit(null);
        PanelStatus.getUnit().setAction("new graph");        
    }
    
    /**
     * gera um grafo aleatorio
     * @param oriented configura-o para orientado
     * @param weighted configura-o para ponderado
     * @param randomWeight configura-o para que tenha pesos aleatorios nas arestas */
    public static void chooseRandomGraph(boolean oriented, boolean weighted, boolean randomWeight){        
        Graph.setUnit(null);
        GraphMap.setUnit(null);
        Graph g = Graph.getUnit();
        GraphMap.getUnit();
        
        g.setDirection(oriented);
        g.setWeighted(weighted);
        g.setRandWeightValues(randomWeight);              
        GraphOperation.random();
        PanelMenu.getUnit().disableAlgorithms();
        
        PanelLeft.getUnit().disableButtons();
        interruptAlgorithm();
        PanelStatus.getUnit().setAction("new random graph generated");        
    }
    
    /**
     * Adiciona um vertice
     * @param newName nome do novo vertice*/
    public static void addVertex(String newName){
        Graph.getUnit().addVertex(newName);
        PanelStatus.getUnit().setAction("added vertex: " + newName);        
    }
    
    /** Remove um vertice
     * @param x nome do vertice a ser removido*/
    public static void removeVertex(String x){
        Graph g = Graph.getUnit();
        g.removeVertex(g.getVertex(x));
        PanelStatus.getUnit().setAction("removed vertex: " + x);        
    }
    
    /**
     * remove os espaços vazios que podem aparecer antes da entrada que o
     * usuario deu propositalmente ou por acidente
     * @param x string que sera removida os espaços
     * @return uma nova String sem os espaços vazios iniciais
     */
    public static String removeSpace(String x){
            if(x == null)
                    return null;
            int index = 0;
            System.out.println(x);
            while(index < x.length() && x.charAt(index) == ' ')
                    index++;
            if (index == x.length())
                    return null;
            System.out.println(x.substring(index));
            return x.substring(index);
    }    

    /**
     * executa o algoritmo BFS
     * @param v raiz da arvore primeiro em largura
     */
    public static void playBFS(Vertex v){
        t = new ThreadAlgorithm(v, 0);
        PanelMenu.getUnit().disableButtons();
        PanelLeft.getUnit().disableButtonsAlgorithm(false);        
    }
    
    /** 
     * executa o algoritmo DFS
     */
    public static void playDFS(){
        t = new ThreadAlgorithm();
        PanelLeft.getUnit().disableButtonsAlgorithm(false);
        PanelMenu.getUnit().disableButtons();        
    }
    
    /**
     * executa o algoritmo de Edmonds-Karp
     * @param u fonte do fluxo
     * @param v sumidouro do fluxo
     */
    public static void playEdmondsKarp(Vertex u, Vertex v){
        PanelMenu.getUnit().disableButtons();
        t = new ThreadAlgorithm(u, v);
        PanelLeft.getUnit().disableButtonsAlgorithm(false);        
    }
    
    /** 
     * alterna entre grafo simples e grafo orientado
     */
    public static void chooseGraphOrientation(){
        Graph graph = Graph.getUnit();
        if(graph.isDirected()){
            PanelStatus.getUnit().setAction("set to simple graph");
            GraphOperation.orientedSimple();
        } else{
            PanelStatus.getUnit().setAction("set to oriented graph");
            GraphOperation.simpleOriented();
        }//if-else         
    }
    
    /**
     * alterna entre grafo nao ponderado e ponderado
     */
    public static void chooseWeight(){
        Graph graph = Graph.getUnit();
        graph.setWeighted(!graph.isWeighted());
        PanelMenu.getUnit().disableAlgorithms();
        PanelLeft.getUnit().disableButtons();        
    }
    
    /**
     * adiciona uma aresta (u,v)
     * @param u caso o grafo seja direcionado, o vertice "cauda" da aresta (u, v)
     * @param v caso o grafo seja direcionado, o vertice "cabeça" da aresta (u, v)
     */
    public static void addEdge(Vertex u, Vertex v){
        Graph g = Graph.getUnit();
        g.addEdge(u, v);
        PanelStatus.getUnit().setAction("added edge: (" + u.getName() + ", " + v.getName() + ")");        
    }
    
    /** remove uma aresta
     * @param u caso o grafo seja direcionado, o vertice "cauda" da aresta (u, v)
     * @param v caso o grafo seja direcionado, o vertice "cabeça" da aresta (u, v)
     */
    public static void removeEdge(Vertex u, Vertex v){
        Graph g = Graph.getUnit();
        g.removeEdge(u,v);
        PanelStatus.getUnit().setAction("removed edge: (" + u.getName() + ", " + v.getName() + ")");        
    }
    
    /**
     * analisa as entradas de uma ediçao/adição de aresta ponderada, validando-as
     * @param e1 caso o grafo seja direcionado, a String referente ao vertice "cauda" da aresta (u, v)
     * @param e2 caso o grafo seja direcionado, a String referente ao vertice "cabeça" da aresta (u, v)
     * @param weight peso a ser inserido
     * @param state flag que indica se é uma adição de aresta ou edição de aresta
     * @return flag indicando se as entradas são validas
     */
    public static boolean analyzeEditEdge(String e1, String e2, String weight, int state){
        Graph graph = Graph.getUnit();
        Vertex x, y;
        int value;
        try{
            value = Integer.parseInt(weight);
            x = graph.getVertex(e1);
            y = graph.getVertex(e2);
            if(state == 1){
                if(x != null && y != null && !weightOverflow(value)){
                    x.putWeight(y, value);
                    if(!graph.isDirected()){
                        y.putWeight(x, value);
                    }
                } else {
                    return false;
                }
            } if (state == 2){
                if(!weightOverflow(value)){
                    graph.addEdge(x,y,value);
                } else {
                    return false;
                }
            }
        } catch (NumberFormatException ex){
            return false;
        }
        return true;
    }
    
    /**
     * valida a entrada do peso, que varia entre 0 e 1.000.000.
     * @param n valor a ser validado
     * @return flag que valida entrada
     */
    public static boolean weightOverflow(int n){
        return n > 100000;
    }

    /**
     * Thread de execuçao do algoritmo
     */
    private static ThreadAlgorithm t;
    
}//FrameControl
