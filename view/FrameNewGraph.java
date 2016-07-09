package view;

import control.FrameControl;
import view.panel.PanelLeft;
import view.panel.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import model.Graph;
import view.panel.PanelMenu;

/** Janela de diálogo para um novo grafo
 *  solicita caracteristicas do grafo assim que e criado.
 */
public class FrameNewGraph extends javax.swing.JFrame {

    /** cria a janela de dialogo. */
    public FrameNewGraph() {
        initComponents();
        componentHandling();
        setVisible(true);
    }

    /** trata todos os eventos dos componentes. */
    public void componentHandling(){
        ButtonHandler handler = new ButtonHandler();
        CheckBoxHandler check = new CheckBoxHandler();
        
        done.addActionListener(handler);
        oriented.addItemListener(check);
        weighted.addItemListener(check);
    }
    
    /** classe privada que trata os eventos dos botoes. */
    private class ButtonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            FrameControl.chooseNewFile();
            Graph.getUnit().setDirection(checkOriented);
            Graph.getUnit().setWeighted(checkWeighted);
            
            PanelLeft.getUnit().disableButtons();
            PanelMenu.getUnit().disableAlgorithms();
            
            Panel.getUnit().repaint();
            dispose();
        }// actionPerformed
    }// ButtonHandler
    
    /** classe privada que trata os eventos do componente de marcaçao */
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
            } else {
                checkWeighted = false;
            }// if-else
        }
    }
    
    /** flags relativas a indicar se determinada caracteristica no grafo foi
     *  marcada na caixa de marcaçao.
     */
    private boolean checkOriented, checkWeighted;
            
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        oriented = new javax.swing.JCheckBox();
        weighted = new javax.swing.JCheckBox();
        done = new javax.swing.JButton();

        setTitle("New Graph");
        setLocation(new java.awt.Point(600, 300));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setText("Choose your graph options ");

        oriented.setText("Oriented");

        weighted.setText("Weighted");

        done.setText("Done");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(oriented)
                    .addComponent(weighted)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(done))))
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
                .addComponent(done)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton done;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JCheckBox oriented;
    private javax.swing.JCheckBox weighted;
    // End of variables declaration//GEN-END:variables
}
