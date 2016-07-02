package main;

import view.*;

import javax.swing.JOptionPane;

public class Main{
    public static void main(String[] args){
        try{
            Frame f = Frame.getUnit();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "An error have ocurred");
        }
    }// main
}// Main