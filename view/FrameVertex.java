package view;

import control.FrameControl;
import model.GraphMap;
import view.input.SelectionHandler;
import view.panel.Panel;
import model.Vertex;
import model.Graph;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/** cria uma janela de diálogo mostrando as informações do vértice e se
 *  o usuário deseja mudar o nome do vértice.
 */
public class FrameVertex extends javax.swing.JFrame {
    
    /** cria a janela. */
    public FrameVertex() {
        initComponents();
        jTextArea1.setEditable(false);
        setInfo();
        buttonHandling();
        setVisible(true);
    }

    /** recebe o vertice que foi chamado por tratamento de seleçao do mouse
     * mostra todas as informaçoes e o guarda para possivel ediçao de nome.
     */
    public void setInfo(){
        u = SelectionHandler.getUnit().getBuffer();
        Graph g = Graph.getUnit();
        String end = "\n";
        if(u != null){
            vertexName.setText("Vertex: " + u.getName());

            for(Vertex x : u.getAdj()){
                jTextArea1.append(x.getName() + end);
            }//for
            if(g.isDirected()){
                in.setText("in-degree: " + u.getIn());
                out.setText("out-degree: " + u.getOut());
            } else {
                in.setText("degree: " + (u.getIn() + u.getOut())/2);
                out.setText("");
            }            
        }

    }// setInfo
    
    /** trata todos os eventos de botões */
    public void buttonHandling(){
        ButtonHandler handler = new ButtonHandler();
        ok.addActionListener(handler);
        cancel.addActionListener(handler);
    }

    /** classe privada trata todos os eventos de botões */
    private class ButtonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            if(event.getSource() == ok){
                String s = newName.getText();
                
                if(s == null){
                    JOptionPane.showMessageDialog(null, "Invalid name");
                } else{
                    Vertex v = FrameControl.validateInput(s);
                    if(v == null){
                        GraphMap.getUnit().substitute(u.getName(), s);
                        u.setName(s);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid name");
                    }
                }// if-else
            }
            Panel.getUnit().repaint();
            dispose();
        }
    }// ButtonHandler
    
    /** recebe o vertice que mostrará as informações e que possivelmente terá
     *  o nome alterado.
     */
    private Vertex u;
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        vertexName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        newName = new javax.swing.JTextField();
        ok = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        in = new javax.swing.JLabel();
        vertexName2 = new javax.swing.JLabel();
        out = new javax.swing.JLabel();

        setTitle("Vertex Info");
        setResizable(false);

        vertexName.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        vertexName.setText("Vertex:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setText("Set new name");

        newName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newNameActionPerformed(evt);
            }
        });

        ok.setText("Ok");

        cancel.setText("Cancel");

        in.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        in.setText("Adjoints");

        vertexName2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        vertexName2.setText("Adjoints");

        out.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        out.setText("Adjoints");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(vertexName)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(newName))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ok)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(in)
                                .addGap(90, 90, 90)
                                .addComponent(out))
                            .addComponent(vertexName2))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(vertexName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vertexName2)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(in)
                    .addComponent(out))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ok)
                    .addComponent(cancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newNameActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel;
    private javax.swing.JLabel in;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField newName;
    private javax.swing.JButton ok;
    private javax.swing.JLabel out;
    private javax.swing.JLabel vertexName;
    private javax.swing.JLabel vertexName2;
    // End of variables declaration//GEN-END:variables
}
