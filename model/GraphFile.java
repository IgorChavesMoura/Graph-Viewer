package model;

import java.io.Serializable;
/** classe alvo que transmite / recebe os dados de / para um arquivo 
 *  esta classe implementa o padrao de projeto de software Singleton
 */
public class GraphFile implements Serializable{
 
    /** retorna a unica instancia da classe.
     * @return a unica instancia da classe */
    public static GraphFile getUnit(){
        if(unit == null)
            unit = new GraphFile();
        return unit;
    }
    /** construtor privado da classe. */
    private GraphFile(){}
    
    /**
     * joga uma nova instância substituindo a anterior do Singleton
     * @param os nova instância.
     */ 
    public static void setUnit(GraphFile os){
        unit = os;
    } 
    
    /** carrega a nova instância nos Singletons de Graph e GraphMap*/
    public void load(){
        Graph.setUnit(g);
        GraphMap.setUnit(gm);
    }

    
    static final long serialVersionUID = 1L;
    private static GraphFile unit;
    private Graph g = Graph.getUnit();
    private GraphMap gm = GraphMap.getUnit();
    
}
