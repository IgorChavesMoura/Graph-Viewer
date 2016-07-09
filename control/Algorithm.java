package control;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import model.Graph;
import model.Vertex;
import observer.ObservableGraphMap;
import observer.ObserverGraphMapPaint;
import thread.ThreadPaint;
import model.GraphMap;
import view.panel.PanelStatus;

/** Classe corresponde aos algoritmos em grafos que serão executados utilizando o grafo na Model e mostrados na Control
  * Essa classe implementa o padrão de projeto Singleton.
  * @version 1.0
  * @since 0.1
  */
public class Algorithm {

    /** Variável correspondente à instância única dessa classe. */
    private static Algorithm unit;

    /** Método que retorna a instância única de Algorithm.
     * @return unit*/
    public static Algorithm getUnit(){
        if(unit == null){
            unit = new Algorithm();
        }
        return unit;
    }

    /** Construtor padrão da classe. Nao utilizavel*/   
    private Algorithm(){}
    
    /** Variável responsável por finalizar o algoritmo; o algoritmo encerra se e somente se a variável for verdadeira */
    private boolean interruption;
    
    /** Variável global correspondente à contagem do tempo inicial e final dos vértices no algoritmo DFS. */
    int time;
    
    /** Método que permite ou nao a interrupçao de um algoritmo
     * @param b recebe a flag para configurar a variavel de interrupcao*/   
    public void setInterruption(boolean b){
        interruption = b;
    }      

    /** atualiza a pintura de um vertice de acordo com o algoritmo e pausa a
     *  thread por um determinado tempo, o que seria o passo-a-passo do
     *  algoritmo. Caso a flag de interrupçao esteja true, a thread para e 
     *  joga uma exception de interrupçao para tambem parar o algoritmo.
     *  Esse metodo utiliza o padrao de projeto Observer.
     * @param o observavel que recebe o vertice a ser alterado na interface
     * @param ob observador que sera notificado da alteração
     * @param color contem o valor dar cor que sera pintada em Panel
     * @param u vertice que sofrera a alteraçao
     * @throws InterruptedException lança uma exception caso o algoritmo sja interrompido
     */
    public void handleThread(ObserverGraphMapPaint o, ObservableGraphMap ob, int color, Vertex u)
                                throws InterruptedException{
        o.setColor(color);
        ob.setState(u);
        ob.send();
        ThreadPaint.getUnit().run();
        if(interruption){
            interruption = !interruption;
            throw new InterruptedException();
        }
    }

    /** Método que roda o algoritmo de busca em largura (BFS).
      * @link https://en.wikipedia.org/wiki/Breadth-first_search
      * @param s Vértice fonte.
      * @throws java.lang.InterruptedException interrompe a execução do algoritmo */
    public void BFS(Vertex s) throws InterruptedException{
        Vertex v, u;
        int i,j;

        ObservableGraphMap obs = new ObservableGraphMap(null);
        ObserverGraphMapPaint ob = new ObserverGraphMapPaint(3);
        obs.attach(ob);

        LinkedList<Vertex> F;
        GraphMap.getUnit().setAlgorithmRunning(true);

        for (Vertex x : Graph.getUnit().getV()){
            x.setPredecessor(null);
            x.setDistance(-1); //-1 representa infinito
            x.setColor(0);
            PanelStatus.getUnit().setAction("Color ["+ x +"] = white");

            handleThread(ob, obs, 3, x);
        }

        s.setDistance(0);
        s.setColor(1);
        PanelStatus.getUnit().setAction("Color ["+ s +"] = gray");

        handleThread(ob, obs, 4, s);

        F = new LinkedList<>();
        F.add(s);

        ListIterator iter;
        while(!F.isEmpty())
        {
            u = F.removeFirst();
            iter = u.getAdj().listIterator();
            while(iter.hasNext())
            {
                v = (Vertex)iter.next();
                if(v.getColor()==0)
                {
                    v.setColor(1);
                    v.setPredecessor(u);
                    v.setDistance(u.getDistance() + 1);
                    F.add(v);
                    PanelStatus.getUnit().setAction("Color ["+ v +"] = gray; d[" + v + "] = " + v.getDistance());
                    handleThread(ob, obs, 4, v);
                }
            }
            u.setColor(2);
            PanelStatus.getUnit().setAction("Color ["+ u +"] = black");
            handleThread(ob, obs, 5, u);
        }
    }

    /** Método que roda o algoritmo de busca em profundidade (DFS)
      * @link https://en.wikipedia.org/wiki/Depth-first_search 
      * @throws java.lang.InterruptedException interrompe a execução do algoritmo
      */
    public void DFS() throws InterruptedException{
        ObservableGraphMap obs = new ObservableGraphMap(null);
        ObserverGraphMapPaint ob = new ObserverGraphMapPaint(3);
        obs.attach(ob);

        GraphMap.getUnit().setAlgorithmRunning(true);

        int i;

        for(Vertex u : Graph.getUnit().getV())
        {
            u.setColor(0);
            u.setPredecessor(null);
            handleThread(ob, obs, 3, u);
            System.out.println("Vertex "+u.getName() +" virou BRANCO");
        }

        time = 0;

        for(Vertex u : Graph.getUnit().getV())
        {
            if(u.getColor() == 0)
                    DFS_Visit(u);
        }
    }

    /** Método que roda o algoritmo auxiliar do DFS
      * @param u - Vértice descoberto mais recentemente
      * @throws java.lang.InterruptedException interrompe a execução do algoritmo
      */
    public void DFS_Visit(Vertex u)  throws InterruptedException
    {
        ListIterator iter;
        Vertex v;

        ObservableGraphMap obs = new ObservableGraphMap(null);
        ObserverGraphMapPaint ob = new ObserverGraphMapPaint(3);
        obs.attach(ob);

        u.setColor(1);
        time++;
        u.setDiscoveryTime(time);
        handleThread(ob, obs, 6, u);
        System.out.println("Vertex "+ u.getName() +" virou CINZA com tempo de descoperta "+u.getDiscoveryTime());
        iter = u.getAdj().listIterator();
        while(iter.hasNext()){
            v = (Vertex)iter.next();
            if(v.getColor()==0) {
                v.setPredecessor(u);
                DFS_Visit(v);
            }
        }

        u.setColor(2);
        time++;
        u.setFinalTime(time);
        handleThread(ob, obs, 7, u);
        System.out.println("Vertex "+u.getName()+" virou PRETO com tempo de finalização "+u.getFinalTime());

    }    

    /** Algoritmo para inicializar fonte única, utilizado no algoritmo de Dijkstra
      * @param s - Vértice fonte
      * @throws InterruptedException interrompe a execução do algoritmo
      */
    public void initializeSingleSource(Vertex s) throws InterruptedException {
        ObservableGraphMap obs = new ObservableGraphMap(null);
        ObserverGraphMapPaint ob = new ObserverGraphMapPaint(3);
        obs.attach(ob);

        for(Vertex v: Graph.getUnit().getV()){
            v.setDistance(Integer.MAX_VALUE); // representa infinito
            v.setPredecessor(null);
            handleThread(ob, obs, 5, v);
        }
        s.setDistance(0);
        handleThread(ob, obs, 5, s);
    }

    /** Algoritmo de relaxamento de arestas, utilizado no algoritmo de Dijkstra
      * @param u - extremidade inicial da aresta
      * @param v - extremidade final da aresta
      * @throws InterruptedException interrompe a execução do algoritmo
      */
    public void relax(Vertex u, Vertex v) throws InterruptedException{
        ObservableGraphMap obs = new ObservableGraphMap(null);
        ObserverGraphMapPaint ob = new ObserverGraphMapPaint(3);
        obs.attach(ob);

        if(v.getDistance() > u.getDistance() + u.getWeight(v)){
            v.setDistance(u.getDistance() + u.getWeight(v));
            v.setPredecessor(u);
            handleThread(ob, obs, 5, v);
        }
    }

    /** Algoritmo de caminhos mínimos de fonte única de Dijkstra
      * @link https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
      * @param s - Vértice fonte utilizado no algoritmo
      * @throws InterruptedException interrompe a execução do algoritmo
      */ 
    public void Dijkstra(Vertex s) throws InterruptedException {
        GraphMap.getUnit().setAlgorithmRunning(true);
        ObservableGraphMap obs = new ObservableGraphMap(null);
        ObserverGraphMapPaint ob = new ObserverGraphMapPaint(3);
        obs.attach(ob);

        initializeSingleSource(s);
        ArrayList<Vertex> Q = new ArrayList<>(Graph.getUnit().getV());

        while(!Q.isEmpty()){
            Vertex u = getMinimum(Q);
            System.out.println("next: " + u);
            handleThread(ob, obs, 4, u);
            Q.remove(u);
            for(Vertex v: u.getAdj()){
                relax(u,v);
            }
            handleThread(ob, obs, 5, u);
        }
    }

    /** Algoritmo para encontrar árvores geradoras mínimas de Prim
      * @link https://en.wikipedia.org/wiki/Prim%27s_algorithm
      * @param r Vértice fonte
      * @throws InterruptedException interrompe a execução do algoritmo
      */
    public void Prim(Vertex r) throws InterruptedException
    {

        ObservableGraphMap obs = new ObservableGraphMap(null);
        ObserverGraphMapPaint ob = new ObserverGraphMapPaint(3);
        obs.attach(ob);
        GraphMap.getUnit().setAlgorithmRunning(true);

        for(Vertex u : Graph.getUnit().getV())
        {
            u.setDistance(Integer.MAX_VALUE);
            u.isInMST(false);

            handleThread(ob, obs, 5, u);
        }

        r.setDistance(0);
        r.setPredecessor(null);

        handleThread(ob, obs, 5, r);

        ArrayList<Vertex> queue = new ArrayList<>(Graph.getUnit().getV());

        Vertex u;
        int d;

        while ( !queue.isEmpty()){
            u = getMinimum(queue);

            u.isInMST(true);

            for (Vertex v : u.getAdj())
            {
                if ( !v.isInMST() && u.getWeight(v)< v.getDistance() )
                {
                    v.setDistance(u.getWeight(v));
                    v.setPredecessor(u);
                    handleThread(ob, obs, 5, v);
                }//if
            }//for
            queue.remove(u);
        }//while
    }

    /** Método que retorna o vértice de distância mínima, utilizado no Prim e Dijkstra
      * @param list - coleção contendo os vértices
      * @return Vertex - vértice com distância mínima
      */
    public Vertex getMinimum(ArrayList<Vertex> list){
        String a = list.get(0).getName();
        for(Vertex b : list){
            if(Graph.getUnit().getVertex(a).getDistance() > b.getDistance())
                a = b.getName();
        }
        return Graph.getUnit().getVertex(a);
    }        
        
}
