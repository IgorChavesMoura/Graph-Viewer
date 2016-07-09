package view;

import control.FrameControl;
import view.panel.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import model.Graph;
import control.ResidualGraph;

/** janela de diálogo responsável por receber dois vértices do usuário */
public class FrameEdge extends javax.swing.JFrame {

    
    
    /**
     * cria uma janela de diálogo
     * @param n receb um valor indicando qual tipo de controle vai tratar os
     * dois vértices recebidos.
     */
    public FrameEdge(int n) {
        state = n;
        initComponents();
        buttonHandling();
        setVisible(true);
    }

    /** trata os eventos dos botões */
    public void buttonHandling(){
        ButtonHandler bh = new ButtonHandler();
        ok.addActionListener(bh);
        cancel.addActionListener(bh);
    }
    
    /** valida as entradas recebidas, analisa o valor de status e toma as
     * devidas decisões de controle pra fazer em cima dos dados recebidos
     */
    public void handleText(){
	Graph g = Graph.getUnit();
        ResidualGraph rg = ResidualGraph.getUnit();
        if(g.getVertex(text1) == null || g.getVertex(text2) == null)
                JOptionPane.showMessageDialog(null, "One of ends doesn't exist");
        else switch(state){
            case 1:
                if(!g.existsEdge(g.getVertex(text2), g.getVertex(text1))){
                    FrameControl.addEdge(g.getVertex(text1), g.getVertex(text2));
                }// if
                break;
            case 2:
                    FrameControl.removeEdge(g.getVertex(text1), g.getVertex(text2));
                break;
            case 3:
                if(!g.getVertex(text1).equals(g.getVertex(text2)) && g.isDirected())
                    FrameControl.playEdmondsKarp(g.getVertex(text1), g.getVertex(text2));
                else
                    JOptionPane.showMessageDialog(null, "Your graph must be oriented and the origin must be different than the end");
                break;
        }// switch
    }// handleText
    
    /** classe privada que trata dos eventos de botões da janela */
    private class ButtonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == ok){
                text1 = jTextField1.getText();
                text2 = jTextField2.getText();
                
                handleText();
            }// if
            Panel.getUnit().repaint();
            dispose();
        }// actionPerformed
    }// ButtonHandler

    /** recebe um valor que indica um tpo de operação de controle sobre os
     * dados recebidos
     */
    private int state;
    /** locais onde se armazenam o nome dos vértices */
    private String text1, text2;
    
    @SuppressWarnings("unchecked")
    /** código gerado automaticamente no design da janela */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        ok = new javax.swing.JButton();
        cancel = new javax.swing.JButton();

        setTitle("Edit edge");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(650, 300));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setText("Set the edge ends");

        ok.setText("Ok");

        cancel.setText("Cancel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ok)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ok)
                    .addComponent(cancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton ok;
    // End of variables declaration//GEN-END:variables


    
}// FrameEdge
