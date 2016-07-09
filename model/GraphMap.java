package model;

import view.panel.Panel;
import java.util.*;
import java.io.*;
/** possui toda a estrutura de dados necessária para a interface grafica 
 *  esta classe implementa o padrao de projeto de software Singleton
 */
public class GraphMap implements Serializable{

    /** retorna a unica instancia da classe
     * @return retorna a unica instancia da classe
     */
    public static GraphMap getUnit(){
            if (unit == null)
                    unit = new GraphMap();
            return unit;
    }// getUnit

    /** cria uma nova instancia da classe substituindo a anterior
     * @param gm instancia de substituiçao
     */
    public static void setUnit(GraphMap gm){
            unit = gm;
    }

    /**
     * substitui uma chave por outra. Corresponde a editar um vertice, onde
     * precisa trocar o nome do vertice tambem no mapeamento com as coordenadas na
     * interface grafica.
     * @param a nome antigo.
     * @param b novo nome.*/
    public void substitute(String a, String b){
        Point2D p = map.get(a);
        map.remove(a);
        map.put(b, p);

        Integer n = mapSelection.get(a);
        mapSelection.remove(a);
        mapSelection.put(b, n);
    }

    /** 
     * limpa as collections da classe, utilizado para resetar as configuraçoes
     * do grafo.
     */
    public void clear(){
            map.clear();
            mapSelection.clear();
            isAlgorithmRunning = false;
            showPredecessor = false;
    }

    /** retorna a flag que indica se o algoritmo ta executando
     * @return a flag de execuçao do algoritmo */
    public boolean isAlgorithmRunning(){
            return isAlgorithmRunning;
    } // isAlgorithmRunning

    /** configura a flag de execuçao do algoritmo
     * @param b flag de configuraçao
     */
    public void setAlgorithmRunning(boolean b){
            isAlgorithmRunning = b;
    }// setAlgorithRunning

    /**
     * retorna a flag que mostra o subgrafo predecessor de um grafo
     * @return flag do subgrafo predecessor
     */
    public boolean getShowPredecessor(){
            return showPredecessor;
    }// getShowPredecessor

    /**
     * configura a flag do subgrafo predecessor
     * @param b flag de configuraçao
     */
    public void setShowPredecessor(boolean b){
            showPredecessor = b;
    }// setShowPredecessor

    /**
     * ao adicionar um vertice, suas informaçoes de coordenadas sao adicionadas
     * no Map de coordenadas, esse metodo se encarrega disso.
     * @param u vertice chave
     * @param p Ponto de valor
     */
    public void put(Vertex u, Point2D p){
            map.put(u.getName(), p);
    }//put

    /** 
     * todo vertice possui um estado relativo a sua cor (nao selecionado e preto,
     * selecionado e azul, etc...). Esse metodo associa a cada vertice um
     * mapeamento para um estado unico.
     * @param u Vertice a ser inserido o estado
     * @param x estado a associar com o vertice u
     */
    public void put(Vertex u, int x){
            mapSelection.put(u.getName(),x);
    }// put

    /** retorna um ponto dado um vertice u.
     * @param u vertice para se pegar a coordenada
     * @return retorna a coordenada do vertice u */
    public Point2D get(Vertex u){
            return map.get(u.getName());
    }

    /**
     * cria uma copia do mapeamento de coordenadas e o retorna. Necessario
     * para poder se manipular sem alterar o mapeamento original
     * @return uma copia Map<String, Point2D> */
    public Map<String, Point2D> getMapCopy(){
            Map<String, Point2D> ret = new HashMap<>();
            Graph g = Graph.getUnit();
            for(Vertex a : g.getV()){
                    ret.put(a.getName(), map.get(a.getName()));
            }
            return ret;
    }

    /**
     * remove uma chave relativa a o vertice u dos dois mapeamentos. Está
     * associado com a função de remoção do vértice u.
     * @param u vertice a ser removido as configurações de interface gráfica
     */
    public void rem(Vertex u){
            map.remove(u.getName());
            mapSelection.remove(u.getName());
    }// rem

    /**
     * retorna o mapeamento de coordenadas
     * @return retorna o mapeamento de coordenadas */
    public Map<String, Point2D> getMap(){
            return map;
    }// getMap

    /** retorna o mapeamento de estados
     * @return retorna o mapeamento de estados
     */
    public Map<String, Integer> getMapSelection(){
            return mapSelection;
    }// getMapSelection

    /**
     * verifica se a coordenada (x, y) está contida em um vértice. utilizada
     * para a percepção de eventos do mouse.
     * @param x coordenada x
     * @param y coordenada y
     * @return retorna o vertice que o contem e nulo caso nenhum o contenha
     */
    public Vertex isOn(int x, int y){
            Set<String> key = map.keySet();
            Point2D p;
            for(String u : key){
                    p = map.get(u);
                    if(x >= p.getX() && x <= p.getX() + Panel.SIZE && y >= p.getY() && y <= p.getY() + Panel.SIZE)
                            return Graph.getUnit().getVertex(u);
            }// for
            return null;
    }// isOn

    /** 
     * configura um novo ponto para o vertice v
     * 
     * @param v vertice de alteração
     * @param x nova coordenada x
     * @param y nova coordenada y
     */
    public void setXY(Vertex v, int x, int y){
            map.put(v.getName(), new Point2D(x,y));
    }// setXY

    /** construtor privado */
    private GraphMap(){} // constructor

    /** 
     *  chave de identificaçao do arquivo quando carregado e salvo.
     */
    static final long serialVersionUID = 1L;
    /** a unica instancia da classe*/
    private static GraphMap unit;
    /** mapeamento de cada vertice a uma coordenada no painel de desenho */
    private Map<String, Point2D> map = new HashMap<String, Point2D>();
    /** mapeamento de cada vertice a um estado */
    private Map<String, Integer> mapSelection = new HashMap<String, Integer>();
    /** flag que indica se um algoritmo esta sendo executado */
    boolean isAlgorithmRunning = false;
    /** flag que indica se o subgrafo predecessor está sendo mostrado na tela */
    boolean showPredecessor = false;
}//GraphMap