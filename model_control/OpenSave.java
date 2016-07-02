package model_control;

import model_control.*;
import view.*;

import javax.swing.JOptionPane;
import java.io.*;

public class OpenSave implements Serializable{

    private OpenSave(){}
    
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
			//Graph.setUnit((Graph)ois.readObject());
                        GraphFile.getUnit().load();
		 	ois.close();
		 } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Error while loading file");
		 }
	}
/*
	public static void saveGraphMap(File f){
		String path = f.getAbsolutePath();
		if(!path.contains(".graph")){
			path += ".graphmap";
		} else{
			path += "map";
		}// if-else
		try{
			FileOutputStream fout = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(GraphMap.getUnit());
			oos.close();
		} catch(Exception ex){
                        JOptionPane.showMessageDialog(null, "Error while saving file");
		}
	}

	public static void loadGraphMap(File f){

		 String path = f.getAbsolutePath() + "map";
		 System.out.println(path);
		 try{
		 	FileInputStream fin = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fin);

			GraphMap.setUnit((GraphMap)ois.readObject());
		 	ois.close();
		 } catch (Exception ex){
		 	JOptionPane.showMessageDialog(null, "Error while loading file");
		 }
	}
*/
}