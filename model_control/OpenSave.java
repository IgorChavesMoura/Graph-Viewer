package model_control;

import model_control.*;
import view.*;

import javax.swing.JOptionPane;
import java.io.*;

public class OpenSave implements Serializable{

	public static void saveGraph(File f){
		File f1 = new File(f.getParent(), f.getName() + ".graph");
		try{
			FileOutputStream fout = new FileOutputStream(f1);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(Graph.getUnit());
			oos.close();
		} catch(Exception ex){
			ex.printStackTrace();
		}

	}

	public static void loadGraph(File f){
		 try{
		 	FileInputStream fin = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fin);

			Graph.setUnit((Graph)ois.readObject());
		 	ois.close();
		 } catch (Exception ex){
		 	JOptionPane.showMessageDialog(null, "File not Found");
		 	System.out.println(ex);
		 }
	}

	public static void saveGraphMap(File f){
		String path = f.getAbsolutePath() + ".graphmap";
		System.out.println(path);
		try{
			FileOutputStream fout = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(GraphMap.getUnit());
			oos.close();
		} catch(Exception ex){
			System.out.println("Caiu uma exception em saveGraphMap");
			System.out.println(ex);
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
		 	JOptionPane.showMessageDialog(null, "File not Found");
		 	System.out.println(ex);
		 }
	}
}