package view;

import control.FrameControl;
import view.panel.PanelStatus;
import view.panel.PanelMenu;
import view.input.ActionKeyboard;
import view.input.SelectionHandler;
import view.input.MoveAllHandler;
import view.input.SelectionEdgeHandler;
import view.panel.PanelLeft;
import view.panel.Panel;
import model.Vertex;
import java.awt.*;
import javax.swing.*;

import view.panel.PanelLog;

/** janela principal do programa
 *  implementa o padrão de projeto de softwware Singleton.
 */
public class Frame extends JFrame{
    /**
     * @return Frame única instância da classe */
    public static Frame getUnit(){
        if(unit == null)
            unit = new Frame();
        return unit;
    }// getUnit

    /** Construtor responsável por organizar os componentes na janela */
    private Frame(){
            super("Grapho - Beta Version");

            Container c = getContentPane();
            c.setLayout(new BorderLayout());                

            c.add(pan, BorderLayout.CENTER);
            c.add(sp, BorderLayout.SOUTH);
            c.add(pl, BorderLayout.WEST);
            c.add(dp, BorderLayout.EAST);
            setFocusable(true);

            addKeyListener(ActionKeyboard.getUnit());

            pan.addMouseListener(SelectionHandler.getUnit());
            pan.addMouseMotionListener(SelectionHandler.getUnit());
            pan.addMouseListener(SelectionEdgeHandler.getUnit());
            pan.addMouseMotionListener(SelectionEdgeHandler.getUnit());
            pan.addMouseListener(MoveAllHandler.getUnit());
            pan.addMouseMotionListener(MoveAllHandler.getUnit());
      
            setJMenuBar(PanelMenu.getUnit());

            setVisible(true);
            setLocation(300,100);
            setSize(900,500);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }// Constructor

    /** abre uma janela de diálogo para abrir um arquivo */
    public void open(){
        final JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int state = fc.showOpenDialog(Frame.this);
        if(state == JFileChooser.APPROVE_OPTION){
            FrameControl.open(fc);
        }
    }// open

    /** abre uma janela de diálogo para salvar um arquivo */
    public void save(){
        final JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int state = fc.showSaveDialog(unit);
        if(state == JFileChooser.APPROVE_OPTION){
            FrameControl.save(fc);
        }
    }// save

    /** abre uma janela de dialógo solicitando o vértice raiz ao clicar para
     *  executar o algoritmo de prim
     */
    public void prim(){
        String newName = JOptionPane.showInputDialog("Choose the Root");
        Vertex v = FrameControl.validateInput(newName);
        if(v == null){
            JOptionPane.showMessageDialog(null, "invalid input");
        }else{
            FrameControl.playPrim(v);
        }// if-else
    }// prim

    /** abre uma janela de dialógo solicitando o vértice raiz ao clicar para
     *  executar o algoritmo de Dijkstra.
     */
    public void dijkstra(){
        String newName = JOptionPane.showInputDialog("Choose the Root");
        Vertex v = FrameControl.validateInput(newName);
        if(v == null){
            JOptionPane.showMessageDialog(null, "invalid input");
        }else{
            FrameControl.playDijkstra(v);
        }// if-else
    }// dijkstra

    /** abre uma janela de dialógo solicitando o novo nome do vertice ao clicar para
     *  adicionar um  vértice
     */    
    public void addVertex(){
        String newName = JOptionPane.showInputDialog("Set the vertex name");
        newName = FrameControl.removeSpace(newName);
        
        if(newName == null){
        } else {
            Vertex v = FrameControl.validateInput(newName);
            if(v == null){
                FrameControl.addVertex(newName);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Input");
            }// if-else
        }// if-else
    }//addVertex
    
    /** abre uma janela de dialógo solicitando o nome do vértice a ser removido
     *  ao clicar no botão de remoção de vértice
     */    
    public void removeVertex(){
        String newName = JOptionPane.showInputDialog("Set the vertex name");
        
        if(newName != null)
                newName = FrameControl.removeSpace(newName);
        Vertex v = FrameControl.validateInput(newName);
        if(newName != null){
            if(v == null){
                JOptionPane.showMessageDialog(null, "Invalid Input");
            } else {
                FrameControl.removeVertex(newName);
            }// if-else
        }
    }// removeVertex

    /** abre uma janela de dialógo solicitando o vértice raiz ao clicar para
     *  executar o algoritmo de busca em largura
     */    
    public void bfs(){
        String newName = JOptionPane.showInputDialog("Choose the Root");
        Vertex v = FrameControl.validateInput(newName);
        if(v == null){
            JOptionPane.showMessageDialog(null, "Invalid Input");
        } else {
            FrameControl.playBFS(v);
        }// if-else
    }// bfs

    /** única instância da classe */
    private static Frame unit;
    /** painél de desenho do grafo da janela */
    private Panel pan = Panel.getUnit();
    /** painel inferior de status */
    private PanelStatus sp = PanelStatus.getUnit();
    /** painel lateral esquerdo de botoes */
    private PanelLeft pl = PanelLeft.getUnit();        
    /** painel lateral direito que mostra em texto algumas informaçoes do grafo */
    private PanelLog dp = PanelLog.getUnit();         
}// Frame