package thread;

import control.Algorithm;
import control.ResidualGraph;
import model.Vertex;

/** trata a execução dos algoritmos. */
public class ThreadAlgorithm implements Runnable{

    /** interrompe a thread */
    public void interrupt(){
        t.interrupt();
    }

    /**
     * @return retorna a thread do algoritmo */
    public Thread getThread(){
        return t;
    }

    /**
     * @return flag que indica se a thread está executando
     */
    public boolean isAlive(){
            return t.isAlive();
    }

    /** construtor que executa certos algoritmos de acordo com status.
     * 
     * @param u vertice que será a raiz do algritmo
     * @param status indica qual algoritmo sera executado
     */
    public ThreadAlgorithm(Vertex u, int status){
            t = new Thread(this, "BFS");
            this.u = u;
            state = status;
            t.start();
    }// constructor

    /**
     * construtor para a execução do algoritmo de Edmonds-Karp.
     * @param u vertice de origem da rede
     * @param v vertice sumidouro da rede
     */
    public ThreadAlgorithm(Vertex u, Vertex v){
            t = new Thread(this, "BFS");
            this.u = u;
            this.v = v;
            state = 5;
            t.start();
    }// constructor        

    /** construtor que executa o algoritmo de busca em profundidade
     */
    public ThreadAlgorithm(){
            t = new Thread(this, "DFS");
            t.start();
            state = 1;
    }// constructor

    @Override
    public void run(){
        Algorithm alg = Algorithm.getUnit();
            try{
                    if(state == 0)
                            alg.BFS(u);
                    if(state == 1)
                            alg.DFS();
                    if(state == 3)
                            alg.Prim(u);
                    if(state == 4)
                            alg.Dijkstra(u);
                    if(state == 5)
                            ResidualGraph.getUnit().Edmonds_Karp(u, v);
                    if(state == 6)
                        ResidualGraph.getUnit().BFS(u);
            } catch(InterruptedException e){}
    }// run

    /** thread de execuçao do algoritmo */
    Thread t;
    /** vertice que serao utilizados para os algoritmos */
    Vertex u, v;
    /** valor que indica qual algoritmo vai ser executado */
    int state;        
}// ThreadAlgorithm