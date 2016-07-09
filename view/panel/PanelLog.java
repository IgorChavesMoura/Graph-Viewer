package view.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Graph;
import model.Vertex;
import thread.ThreadPaint;
import model.GraphMap;
import view.Frame;

/**
 * constroi no painel direito um log sobre as informações do grafo
 * implementa o padrão de projeto de software Singleton.
 */
public class PanelLog extends javax.swing.JPanel{
    
    
    public static PanelLog getUnit(){
        if(unit == null)
            unit = new PanelLog();
        return unit;
    }
    
    private PanelLog() {
        initComponents();
        ButtonHandler handler = new ButtonHandler();
        hide.addActionListener(handler);
        text.setEditable(false);
    }
    
    private class ButtonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            visible = !visible;
            
            text.setVisible(visible);
            jScrollPane1.setVisible(visible);
            
            if(visible){
                hide.setText("Hide");
            } else {
                hide.setText("Show");
            }// if else
            Frame.getUnit().requestFocus();
            repaint();
        }
    }
    
    public void updateText(){
        Graph graph = Graph.getUnit();
        GraphMap graphmap = GraphMap.getUnit();
        text.setText(null);
        
        text.append("directed: " + graph.isDirected() + end);
        text.append("weighted: " + graph.isWeighted() + end);
        text.append("algorithm running: " + graphmap.isAlgorithmRunning() + end);
        text.append("predecessor subgraph: " + graphmap.getShowPredecessor() + end);
        text.append("algorithm speed: " + ((double)ThreadPaint.getUnit().getSpeed()/1000) + " seconds per step" + end);
        
        for(Vertex x : graph.getV()){
            text.append("[" + x + "]");
            for(Vertex y : x.getAdj())
                text.append("→ [ " + y + "]");
            text.append(end);
        }
        
        text.append("\n\nFLOW\n\n");
        for(Vertex x: graph.getV()){
            text.append(x + " ");
            for(Vertex y : x.getAdj()){
                if(x.getFlow(y) != null){
                    text.append("→ (" + y + ", [" + x.getFlow(y) + "/" + x.getWeight(y) + "])");  
                }else {
                    text.append("→ (" + y + ", [0/" + x.getWeight(y) + "])");  
                }//if-else
            }//for
            text.append(end);
        
        }//for

    }

    private static PanelLog unit;
    private String end = "\n";
    private boolean visible = true;
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        text = new javax.swing.JTextArea();
        hide = new javax.swing.JButton();

        setBackground(new java.awt.Color(88, 188, 120));
        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setAutoscrolls(true);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setText("Log");

        text.setBackground(new java.awt.Color(88, 188, 120));
        text.setColumns(20);
        text.setRows(5);
        text.setMargin(new java.awt.Insets(5, 5, 0, 0));
        jScrollPane1.setViewportView(text);

        hide.setBackground(new java.awt.Color(88, 188, 120));
        hide.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        hide.setForeground(new java.awt.Color(0, 0, 0));
        hide.setText("Hide");
        hide.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel1)
            .addComponent(hide)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hide))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton hide;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea text;
    // End of variables declaration//GEN-END:variables
}
