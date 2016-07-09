package control;

import javax.swing.JOptionPane;
import java.io.*;
import model.GraphFile;

/**
 * opera sobre as funções de salvar/carregar um grafo
 * essa class utiliza o padrao de projeto de software Singleton
 */
public class OpenSave implements Serializable{
    
    /**
     * retorna a unica instancia da classe
     * @return retorna a unica instancia da classe
     */
    public static OpenSave getUnit(){
        if (unit == null){
            unit = new OpenSave();
        }
        return unit;
    }    
    
    /** construtor */
    private OpenSave(){}
    
    /** salva o grafo
     * @param f arquivo destino
     */
    public static void saveGraph(File f){
        File f1;
        if(!f.getName().contains(".graph")){
            f1 = new File(f.getParent(), f.getName() + ".graph");
        } else{
            f1 = new File(f.getParent(), f.getName());
        }// if-else
        try{
            FileOutputStream fout = new FileOutputStream(f1);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(GraphFile.getUnit());
            oos.close();
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error while saving file");
        }

    }

    /**
     * carrega um grafo
     * @param f arquivo origem
     */
    public static void loadGraph(File f){
            try{
                FileInputStream fin = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fin);

                GraphFile.setUnit((GraphFile)ois.readObject());
                GraphFile.getUnit().load();
                ois.close();
            } catch (IOException | ClassNotFoundException ex){
               JOptionPane.showMessageDialog(null, "Error while loading file");
            }
    }

    /** unica instancia da classe */  
    private static OpenSave unit;
}// OpenSave