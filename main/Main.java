package main;

import view.*;

import javax.swing.JOptionPane;

public class Main{
    public static void main(String[] args){

        try{
            System.out.println(Integer.MAX_VALUE);
            Frame f = Frame.getUnit();
        } catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "An error have ocurred");
            JOptionPane.showMessageDialog(null, e);
        }
    }// main
}// Main