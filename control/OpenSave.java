package control;

import javax.swing.JOptionPane;
import java.io.*;
import model.GraphFile;

public class OpenSave implements Serializable{
       
    public static OpenSave getUnit(){
        if (unit == null){
            unit = new OpenSave();
        }
        return unit;
    }    
    
    private OpenSave(){}
    
    public void setSaved(boolean b){
        saved = b;
    }//setSaved

    public boolean isSaved(){
        return saved;
    }    
    
    public static void saveGraph(File f){
        File f1 = null;
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

    private static OpenSave unit;
    private boolean saved;
}// OpenSave