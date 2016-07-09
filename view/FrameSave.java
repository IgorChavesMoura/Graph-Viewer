package view;

import control.FrameControl;
import view.panel.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import view.panel.PanelLeft;

/** cria uma janela perguntando ao usuário se ele quer salvar o grafo antes
 *  de tomar determinada ação.
 */
public class FrameSave extends javax.swing.JFrame {
    
    /** cria a janela
     * @param state indica o tipo de operaçao de controle que sera feita sobre
     * os dados recebidos
     */
    public FrameSave(int state) {
        this.state = state;
        initComponents();
        buttonHandling();
        setVisible(true);
    }

    /** classe privada que trata os eventos de botoes*/
    private class ButtonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            Frame f = Frame.getUnit();
            setVisible(false);
            if(e.getSource() == yes || e.getSource() == no){
                
                if(e.getSource() == yes){
                    f.save();
                }// saving
                
                if(state == 0){
                    new FrameNewGraph();
                } if (state == 1){
                    new FrameRandGraph();
                } if (state == 2){
                    f.open();
                } if (state == 3){
                    FrameControl.chooseGraphOrientation();
                } if (state == 4){
                    FrameControl.chooseWeight();
                    PanelLeft.getUnit().disableButtons();
                }
            }// if
            dispose();
            Panel.getUnit().repaint();
        }// actionPerformed
    }// ButtonHandler
    
    /** trata os eventos dos botoes */
    public void buttonHandling(){
        ButtonHandler handler = new ButtonHandler();
        cancel.addActionListener(handler);
        no.addActionListener(handler);
        yes.addActionListener(handler);
    }    
    
    /** indica o tipo de operaçao de controle sobre os dados recebidos */
    private int state;
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        yes = new javax.swing.JButton();
        no = new javax.swing.JButton();
        cancel = new javax.swing.JButton();

        setTitle("Save graph");
        setAlwaysOnTop(true);
        setFocusCycleRoot(false);
        setFocusTraversalPolicyProvider(true);
        setLocation(new java.awt.Point(500, 300));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setText("your current graph still hasn't been saved. Do you wanna save?");

        yes.setText("Yes");

        no.setText("No");

        cancel.setText("Cancel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(yes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(no)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancel)
                .addGap(157, 157, 157))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yes)
                    .addComponent(no)
                    .addComponent(cancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton no;
    private javax.swing.JButton yes;
    // End of variables declaration//GEN-END:variables


}
