package view;

import control.FrameControl;
import view.panel.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import model.Graph;

/** Janela de diálogo que solicita as caracteristicas do grafo que será
 *  gerado aleatoriamente.
 */
public class FrameRandGraph extends javax.swing.JFrame {

    /** cria uma janela de dialogo. */
    public FrameRandGraph() {
        initComponents();
        componentHandling();
        random.setEnabled(false);
        setVisible(true);
    }
    
    /** trata todos os eventos dos componentes. */
    public void componentHandling(){
        ButtonHandler handler = new ButtonHandler();
        CheckBoxHandler check = new CheckBoxHandler();
        
        done.addActionListener(handler);
        oriented.addItemListener(check);
        weighted.addItemListener(check);
        random.addItemListener(check);
    }    
    
    /** classe privada que trata dos eventos de botoes. */
    private class ButtonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            FrameControl.chooseRandomGraph(checkOriented, checkWeighted, checkRandomicWeight);
            Panel.getUnit().repaint();
            dispose();
        }// actionPerformed
    }// ButtonHandler
    
    /** classe privada que trata dos eventos de caixa de marcaçao. */
    private class CheckBoxHandler implements ItemListener{
        Graph graph = Graph.getUnit();
        
        @Override
        public void itemStateChanged(ItemEvent event){
            if(oriented.isSelected()){
                checkOriented = true;
            } else {
                checkOriented = false;
            }// if-else
            
            if(weighted.isSelected()){
                checkWeighted = true;
                random.setEnabled(true);
            } else {
                checkWeighted = false;
                random.setEnabled(false);
            }// if-else
            
            if(random.isSelected()){
                checkRandomicWeight = true;
            } else {
                checkRandomicWeight = false;
            }
        }
    }    
    
    /** flags que indicam se determinada caracteristica foi marcada na
     *  caixa de marcaçao
     */
    private boolean checkOriented, checkWeighted, checkRandomicWeight;    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        oriented = new javax.swing.JCheckBox();
        weighted = new javax.swing.JCheckBox();
        done = new javax.swing.JButton();
        random = new javax.swing.JCheckBox();

        setTitle("Random Graph");
        setLocation(new java.awt.Point(600, 300));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setText("Choose your graph options ");

        oriented.setText("Oriented");

        weighted.setText("Weighted");

        done.setText("Done");
        done.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneActionPerformed(evt);
            }
        });

        random.setText("Random weight values");
        random.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(oriented)
                            .addComponent(weighted)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(random))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(done)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(oriented)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(weighted)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(random)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(done)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void doneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_doneActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton done;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JCheckBox oriented;
    private javax.swing.JCheckBox random;
    private javax.swing.JCheckBox weighted;
    // End of variables declaration//GEN-END:variables
}
