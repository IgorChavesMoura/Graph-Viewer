package view.panel;

import control.FrameControl;
import java.awt.event.*;
import model.Graph;
import view.Frame;
import view.FrameEdge;
import view.FrameEdgeEdit;
import view.FrameSave;
import view.FrameSpeed;
import view.FrameVertex;

/** Painél de Botões esquerdo
 *  implementa o padrao de projeto de software Singleton
 */
public class PanelLeft extends javax.swing.JPanel {

    /**
     * @return  a única instância da classe */
    public static PanelLeft getUnit(){
        if(unit == null)
            unit = new PanelLeft();
        return unit;
    }

    /** construtor privado. */
    private PanelLeft() {
        initComponents();
        disableButtons();
        buttonHandling();
    }
    
    /** desativa os botões de algoritmos baseados na flag "weighted" em Graph. */
    public void disableButtons(){
        boolean b = !Graph.getUnit().isWeighted();
        bfs.setEnabled(b);
        dfs.setEnabled(b);
        prim.setEnabled(!b);
        editEdge.setEnabled(!b);
        dijkstra.setEnabled(!b);
        edkarp.setEnabled(!b);
    }
    
    /** desativa os botões durante a execução de um algoritmo
     * @param b flag de configuraçao dos botoes */
    public void disableButtonsAlgorithm(boolean b){
        addVertex.setEnabled(b);
        removeVertex.setEnabled(b);
        editVertex.setEnabled(b);
        addEdge.setEnabled(b);
        removeEdge.setEnabled(b);
        editEdge.setEnabled(b);
        random.setEnabled(b);
        digraph.setEnabled(b);
        speed.setEnabled(b);
        weight.setEnabled(b);
        
    }

    /** tratamento de eventos dos botoes */
    public void buttonHandling(){
        ButtonHandler handler = new ButtonHandler();
        addVertex.addActionListener(handler);
        removeVertex.addActionListener(handler);
        editVertex.addActionListener(handler);
        bfs.addActionListener(handler);
        dfs.addActionListener(handler);
        prim.addActionListener(handler);
        dijkstra.addActionListener(handler);
        random.addActionListener(handler);
        digraph.addActionListener(handler);
        speed.addActionListener(handler);
        weight.addActionListener(handler);
        addEdge.addActionListener(handler);
        removeEdge.addActionListener(handler);
        editEdge.addActionListener(handler);
        edkarp.addActionListener(handler);
        hide.addActionListener(handler);
    }
    
    /** classe privada de tratamento de botoes */
    private class ButtonHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ex){
            Frame f = Frame.getUnit();
            if(ex.getSource() == addVertex){
                f.addVertex();
            }
            if(ex.getSource() == addEdge){
                if(!Graph.getUnit().isWeighted())
                    new FrameEdge(1);
                else
                    new FrameEdgeEdit(2);
            }
            if(ex.getSource() == removeEdge){
                new FrameEdge(2);
            }            
            if(ex.getSource() == editEdge){
                new FrameEdgeEdit(1);
            }
            if(ex.getSource() == bfs){
                f.bfs();
            }
            if(ex.getSource() == dfs){
                FrameControl.playDFS();
            }
            if(ex.getSource() == prim){
                f.prim();
            }
            if(ex.getSource() == dijkstra){
                f.dijkstra();
            }
            if(ex.getSource() == random){
                new FrameSave(1);
            }
            if(ex.getSource() == digraph){
                new FrameSave(3);
            }
            if(ex.getSource() == weight){
                new FrameSave(4);
            }
            if(ex.getSource() == speed){
                new FrameSpeed();
            }
            if(ex.getSource() == removeVertex){
                f.removeVertex();
            }
            if(ex.getSource() == editVertex){
                new FrameVertex();
            }
            if(ex.getSource() == edkarp){
                new FrameEdge(3);
            }
            if(ex.getSource() == hide){
                visible = !visible;
                jPanel1.setVisible(visible);
                if(visible){
                    hide.setText("Hide");
                } else {
                    hide.setText("Show");
                }// if-else
            }
            PanelLog.getUnit().updateText();
            Panel.getUnit().repaint();
            f.requestFocus();
            repaint();
        }

    }// ButtonHandler

    /** unica instancia da classe */
    private static PanelLeft unit;
    /** flag usada para esconder/mostrar o painel no botao "Hide"*/
    private boolean visible = true;
    /** flag que indica se o grafo e ponderado ou nao para desativar botoes */
    boolean buttonDisabled = !Graph.getUnit().isWeighted();

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hide = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        addVertex = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        removeVertex = new javax.swing.JButton();
        editVertex = new javax.swing.JButton();
        addEdge = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        removeEdge = new javax.swing.JButton();
        editEdge = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        bfs = new javax.swing.JButton();
        dfs = new javax.swing.JButton();
        prim = new javax.swing.JButton();
        dijkstra = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        speed = new javax.swing.JButton();
        random = new javax.swing.JButton();
        digraph = new javax.swing.JButton();
        weight = new javax.swing.JButton();
        edkarp = new javax.swing.JButton();

        setBackground(new java.awt.Color(61, 135, 84));
        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(95, 195, 61), null, null));
        setToolTipText("");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setDoubleBuffered(false);

        hide.setBackground(new java.awt.Color(61, 135, 84));
        hide.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        hide.setForeground(new java.awt.Color(0, 0, 0));
        hide.setText("Hide");
        hide.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel1.setBackground(new java.awt.Color(61, 135, 84));

        addVertex.setBackground(new java.awt.Color(61, 135, 84));
        addVertex.setForeground(new java.awt.Color(61, 135, 84));
        addVertex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/add.png"))); // NOI18N
        addVertex.setToolTipText("add vertex");
        addVertex.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        addVertex.setFocusable(false);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setText("vertex");
        jLabel1.setFocusable(false);

        removeVertex.setBackground(new java.awt.Color(61, 135, 84));
        removeVertex.setForeground(new java.awt.Color(61, 135, 84));
        removeVertex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/remove.png"))); // NOI18N
        removeVertex.setToolTipText("Remove Vertex");
        removeVertex.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        removeVertex.setFocusable(false);

        editVertex.setBackground(new java.awt.Color(61, 135, 84));
        editVertex.setForeground(new java.awt.Color(61, 135, 84));
        editVertex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/edit.png"))); // NOI18N
        editVertex.setToolTipText("Vertex Info");
        editVertex.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        editVertex.setFocusable(false);

        addEdge.setBackground(new java.awt.Color(61, 135, 84));
        addEdge.setForeground(new java.awt.Color(61, 135, 84));
        addEdge.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/add.png"))); // NOI18N
        addEdge.setToolTipText("Add Edge / Click");
        addEdge.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        addEdge.setFocusable(false);

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setText("edge");
        jLabel2.setFocusable(false);

        removeEdge.setBackground(new java.awt.Color(61, 135, 84));
        removeEdge.setForeground(new java.awt.Color(61, 135, 84));
        removeEdge.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/remove.png"))); // NOI18N
        removeEdge.setToolTipText("Remove Edge / Click");
        removeEdge.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        removeEdge.setFocusable(false);

        editEdge.setBackground(new java.awt.Color(61, 135, 84));
        editEdge.setForeground(new java.awt.Color(61, 135, 84));
        editEdge.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/edit.png"))); // NOI18N
        editEdge.setToolTipText("Edit Edge");
        editEdge.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        editEdge.setFocusable(false);

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setText("algorithms");
        jLabel3.setFocusable(false);

        bfs.setBackground(new java.awt.Color(61, 135, 84));
        bfs.setForeground(new java.awt.Color(61, 135, 84));
        bfs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/alg1.png"))); // NOI18N
        bfs.setToolTipText("play BFS");
        bfs.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        dfs.setBackground(new java.awt.Color(61, 135, 84));
        dfs.setForeground(new java.awt.Color(61, 135, 84));
        dfs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/alg2.png"))); // NOI18N
        dfs.setToolTipText("play DFS");
        dfs.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        prim.setBackground(new java.awt.Color(61, 135, 84));
        prim.setForeground(new java.awt.Color(61, 135, 84));
        prim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/alg3.png"))); // NOI18N
        prim.setToolTipText("play Prim");
        prim.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        dijkstra.setBackground(new java.awt.Color(61, 135, 84));
        dijkstra.setForeground(new java.awt.Color(61, 135, 84));
        dijkstra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/alg4.png"))); // NOI18N
        dijkstra.setToolTipText("play Dijkstra");
        dijkstra.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setText("configuration");
        jLabel4.setFocusable(false);

        speed.setBackground(new java.awt.Color(61, 135, 84));
        speed.setForeground(new java.awt.Color(61, 135, 84));
        speed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/speed.png"))); // NOI18N
        speed.setToolTipText("algorithm speed");
        speed.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        speed.setFocusable(false);

        random.setBackground(new java.awt.Color(61, 135, 84));
        random.setForeground(new java.awt.Color(61, 135, 84));
        random.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/random.png"))); // NOI18N
        random.setToolTipText("generate random graph");
        random.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        random.setFocusable(false);

        digraph.setBackground(new java.awt.Color(61, 135, 84));
        digraph.setForeground(new java.awt.Color(61, 135, 84));
        digraph.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/digraph.png"))); // NOI18N
        digraph.setToolTipText("digraph / simple graph");
        digraph.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        digraph.setFocusable(false);

        weight.setBackground(new java.awt.Color(61, 135, 84));
        weight.setForeground(new java.awt.Color(61, 135, 84));
        weight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/weighted.png"))); // NOI18N
        weight.setToolTipText("weighted / nonweighted graph");
        weight.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        weight.setFocusable(false);

        edkarp.setBackground(new java.awt.Color(61, 135, 84));
        edkarp.setForeground(new java.awt.Color(61, 135, 84));
        edkarp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/flow.png"))); // NOI18N
        edkarp.setToolTipText("play Edmonds-Karp");
        edkarp.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 138, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel4)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(random, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(digraph, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(weight, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(speed, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel3)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(bfs, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(dfs, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(prim, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(dijkstra, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(edkarp, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(addVertex, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(removeVertex, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(editVertex, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(addEdge, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(removeEdge, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(editEdge, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 342, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(addVertex, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(removeVertex, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(editVertex, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(addEdge, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(removeEdge, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(editEdge, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bfs, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dfs, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(prim, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dijkstra, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(random, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(digraph, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(weight, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(speed, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(edkarp, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(hide))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hide))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addEdge;
    private javax.swing.JButton addVertex;
    private javax.swing.JButton bfs;
    private javax.swing.JButton dfs;
    private javax.swing.JButton digraph;
    private javax.swing.JButton dijkstra;
    private javax.swing.JButton editEdge;
    private javax.swing.JButton editVertex;
    private javax.swing.JButton edkarp;
    private javax.swing.JButton hide;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton prim;
    private javax.swing.JButton random;
    private javax.swing.JButton removeEdge;
    private javax.swing.JButton removeVertex;
    private javax.swing.JButton speed;
    private javax.swing.JButton weight;
    // End of variables declaration//GEN-END:variables


}
