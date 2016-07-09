package view.panel;

import model.Graph;
import model.Point2D;
import control.ResidualGraph;
import model.Vertex;
import java.awt.*;
import javax.swing.*;
import java.util.*;

import model.GraphMap;
import view.input.SelectionEdgeHandler;

/** Painél responsável pelo desenho do grafo
 *  implementa o padrao de projeto de software Singleton
 */
public class Panel extends JPanel{
    
    /**
     * @return a unica instancia da classe */
    public static Panel getUnit(){
            if(unit == null)
                    unit = new Panel();
            return unit;
    }// getUnit

    /** cria o painel */
    private Panel(){
            setBackground(new Color(88, 188, 120));
            setFocusable(true);
    }

    /** configura a flag pra indicar se o cursor está em cima de um vértice
     *  quando estiver adicionando uma aresta, então a aresta preview forma um
     *  auto-loop.
     * @param b flag de configuração.
     */
    public void setIsLoop(boolean b){
            isLoop = b;
    }// setIsLoop

    /** desenha um loop
     * @param x vertice que possui o loop */
    public void drawLoop(Vertex x){
        if(x != null){
            int radius = 50;
            Point2D p = GraphMap.getUnit().get(x);
            graphics.drawOval(p.getX() + SIZE/2, p.getY() - SIZE/2, radius, radius);

            if(Graph.getUnit().isWeighted() && !isBufferLoop)
                    graphics.drawString("" + x.getWeight(x), p.getX() + 3*SIZE/2, p.getY() - SIZE/2 - 10);
        }
    }

    /** desenha um loop orientado
     * @param x vertice que possui o loop */
    public void drawOrientedLoop(Vertex x){
            drawLoop(x);
            Point2D p = GraphMap.getUnit().get(x);
            drawTriangle(p.getX() + SIZE, p.getY(), p.getX() - 50 + SIZE , p.getY());
    }

    @Override
    /** ocorre toda a pintura aqui */
    public void paintComponent(Graphics g){
            super.paintComponent(g);
            graphics = (Graphics2D)g;
            vertexGraphics = (Graphics2D)g;

            GraphMap gMap = GraphMap.getUnit();
            Map<String, Point2D> map = gMap.getMap();
            PanelLog.getUnit().updateText();

            Set<String> set = map.keySet();
            Point2D p, q;

            BasicStroke dashed = new BasicStroke(1.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f);

            RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setRenderingHints(rh);
            graphics.setStroke(dashed);

            edgeBuffer();
            drawAllEdges(set);
            drawAllPredecessors(set);
            drawAllVertex(set);
    }// paintComponent

    /** desenha todos os vértices
     * @param set collection de vertices usada pra desenhar todos os vertices */
    public void drawAllVertex(Set<String> set){
            for(String x : set)
                    drawVertex(Graph.getUnit().getVertex(x));
    }

    /** desenha a aresta preview durante a adição de uma aresta */
    public void edgeBuffer(){
            isBufferLoop = true;
            if(!isLoop){
                    graphics.drawLine(seh.getEx1(), seh.getEy1(),seh.getEx2(),seh.getEy2());
            } else {
                    drawLoop(seh.getBuffer());
            }// if-else
            isBufferLoop = false;
    }

    /** desenha todas as arestas.
     * @param set collection usada para desenhar todas as arestas. */
    public void drawAllEdges(Set<String> set){
            if(!GraphMap.getUnit().getShowPredecessor()){
                    graphics.setColor(Color.BLACK);
                    for(String x : set){
                            drawEdge(Graph.getUnit().getVertex(x));
                    }
            }// ifi
    }

    /** desenha todas as arestas do subgrafo predecessor.
     * @param set collection que possui todos os vertices para se desenhar as
     * arestas.
     */
    public void drawAllPredecessors(Set<String> set){
            if(GraphMap.getUnit().getShowPredecessor())
                    for(String x : set)
                            if (Graph.getUnit().getVertex(x).getPredecessor() != null)
                                    drawEdgePredecessor(Graph.getUnit().getVertex(x));
    }

    /** desenha um vértice
     * @param x vertice a ser desenhado*/
    public void drawVertex(Vertex x){
            GraphMap gMap = GraphMap.getUnit();
            Map<String, Point2D> map = gMap.getMap();
            Point2D p;
            Color color = new Color(40,40,40);
            int state = (int)gMap.getMapSelection().get(x.getName());
            p = map.get(x.getName()); // get coordinate
            switch(state){
                    case 1 : vertexGraphics.setColor(color);
                            break;
                    case 2 : vertexGraphics.setColor(Color.BLUE);
                            break;
                    case 3 : vertexGraphics.setColor(Color.WHITE);
                            break;
                    case 4 : vertexGraphics.setColor(Color.GRAY);
                            break;
                    case 5 : vertexGraphics.setColor(color);
                            break;
                    case 6 : vertexGraphics.setColor(Color.GRAY);
                            break;
                    case 7 : vertexGraphics.setColor(color);
                            break;
            }// switch
            vertexGraphics.fillOval(p.getX(), p.getY(), SIZE, SIZE); // Draw Vertice

            if(state == 3){
                    vertexGraphics.setColor(Color.BLACK);
                    vertexGraphics.drawString("∞", p.getX() + SIZE/2 - 15, p.getY() + SIZE/2);
            }else if(state == 4 || state == 6){
                    vertexGraphics.setColor(Color.BLACK);
                    if(state == 4)
                            vertexGraphics.drawString("" + x.getDistance(), p.getX() + SIZE/2 - 15, p.getY() + SIZE/2);
                    else
                            vertexGraphics.drawString("" + x.getDiscoveryTime() + "/", p.getX() + SIZE/2 - 15, p.getY() + SIZE/2);
            } else if (state == 5 || state == 7){
                    vertexGraphics.setColor(Color.WHITE);
                    if(state == 5){
                            if(Graph.getUnit().isWeighted() && x.getDistance	() == Integer.MAX_VALUE){
                                    vertexGraphics.drawString("∞", p.getX() + SIZE/2 - 15, p.getY() + SIZE/2);
                            }else{
                                    vertexGraphics.drawString("" + x.getDistance(), p.getX() + SIZE/2 - 15, p.getY() + SIZE/2);
                            }//if-else
                    }
                    else
                            vertexGraphics.drawString("" + x.getDiscoveryTime() + "/" + x.getFinalTime(), p.getX() + SIZE/2 - 15, p.getY() + SIZE/2);
            } // if-else
            vertexGraphics.setColor(Color.BLACK);
            vertexGraphics.drawOval(p.getX(), p.getY() , SIZE, SIZE);
            vertexGraphics.drawString(x.getName(), p.getX(), p.getY() - 10); // Draw Vertice Name
    }

    /** desenha uma aresta
     * @param x vertice de base para se pesquisar todos os adjacentes e desenhar
     * suas respectivas arestas.
     */
    public void drawEdge(Vertex x){
        GraphMap gMap = GraphMap.getUnit();
        Graph graph = Graph.getUnit();
        Map<String, Point2D> map = gMap.getMap();
        Point2D p,q;

        p = map.get(x.getName()); // get coordinate

        if(Graph.getUnit().isDirected()){

            for(Vertex y : x.getAdj()){
                q = map.get(y.getName());
                if(x.getName().equals(y.getName())){
                    drawOrientedLoop(x);
                } else {
                    graphics.setColor(Color.BLACK);

                    graphics.drawLine(p.getX() + SIZE/2, p.getY() + SIZE/2, q.getX() + SIZE/2, q.getY() + SIZE/2); // Draw Edge
                    drawTriangle(p.getX(), p.getY(), q.getX(), q.getY());

                    if(Graph.getUnit().isWeighted()){
                        if(!ResidualGraph.getUnit().isRunning()){
                            graphics.drawString("" + x.getWeight(y), (p.getX() + q.getX())/2, (p.getY() + q.getY())/2);
                        } else {
                            graphics.drawString("" + x.getFlow(y) + "/" + x.getWeight(y), (p.getX() + q.getX())/2, (p.getY() + q.getY())/2);
                        }// if-else
                    }
                }// if-else
            }// for - adj
        } else {
            for(Vertex y : x.getAdj()){
                if(x.getName().equals(y.getName())){
                    drawLoop(x);
                } else {
                    graphics.setColor(Color.BLACK);
                    q = map.get(y.getName());
                    graphics.drawLine(p.getX() + SIZE/2, p.getY() + SIZE/2, q.getX() + SIZE/2, q.getY() + SIZE/2); // Draw Edge

                    if(Graph.getUnit().isWeighted()){
                        if(!ResidualGraph.getUnit().isRunning()){
                            graphics.drawString("" + x.getWeight(y), (p.getX() + q.getX())/2, (p.getY() + q.getY())/2);
                        } else {
                            graphics.drawString("" + x.getFlow(y) + "/" + x.getWeight(y), (p.getX() + q.getX())/2, (p.getY() + q.getY())/2);
                        }// if-else
                    }
                }
            }//for
        }// if-else
    }// drawEdge

    /** desenha uma aresta do subgrafo predecessor
     * @param x vertice base para se encontrar o predecessor e desenhar a aresta
     */
    public void drawEdgePredecessor(Vertex x){
            GraphMap gMap = GraphMap.getUnit();
            Map<String, Point2D> map = gMap.getMap();
            Point2D p,q;
            p = map.get(x.getName()); // get coordinate
            graphics.setColor(Color.RED);
            q = map.get(x.getPredecessor().getName());
            graphics.drawLine(q.getX() + SIZE/2, q.getY() + SIZE/2, p.getX() + SIZE/2, p.getY() + SIZE/2); // Draw Edge

            if(Graph.getUnit().isDirected())
                drawTriangle(q.getX(), q.getY(), p.getX(), p.getY());
            if(Graph.getUnit().isWeighted())
                graphics.drawString("" + x.getPredecessor().getWeight(x), (p.getX() + q.getX())/2, (p.getY() + q.getY())/2);
    }

    /** desenha o triangulo da seta do arco orientado
     * @param x1 coordenada x da primeira extremidade
     * @param y1 coordenada y da primeira extremidade
     * @param x2 coordenada x da segunda extremidade
     * @param y2 coordenada y da segunda extremidade.
     */
    public void drawTriangle(int x1, int y1, int x2, int y2){
            double m = (double)(x2 - x1)/(y2 - y1);
            double a = Math.atan(m);

            int a1, b1, t1, t2, a2, b2, a3, b3;

            if(y1 > y2){
                    a1 = (int)(SIZE/2*Math.sin(a)) + x2 + SIZE/2;
                    b1 = (int)(SIZE/2*Math.cos(a)) + y2 + SIZE/2;
                    t1 = (int)((SIZE/2 + 10)*Math.sin(a)) + x2 + SIZE/2;
                    t2 = (int)((SIZE/2 + 10)*Math.cos(a)) + y2 + SIZE/2;
            } else {
                    a1 = (int)-(SIZE/2*Math.sin(a)) + x2 + SIZE/2;
                    b1 = (int)-(SIZE/2*Math.cos(a)) + y2 + SIZE/2;
                    t1 = (int)-((SIZE/2 + 10)*Math.sin(a)) + x2 + SIZE/2;
                    t2 = (int)-((SIZE/2 + 10)*Math.cos(a)) + y2 + SIZE/2;
            }

            double aRect = a + Math.PI/2;

            a2 = (int)(5*Math.sin(aRect)) + t1;
            b2 = (int)(5*Math.cos(aRect)) + t2;
            a3 = (int)-(5*Math.sin(aRect)) + t1;
            b3 = (int)-(5*Math.cos(aRect)) + t2;


            int boxX[] = {a1, a2, a3};
            int boxY[] = {b1, b2, b3};

            graphics.fillPolygon(boxX, boxY, 3);
    }// DrawTriangle

    /** unica instancia da classe */
    private static Panel unit;
    /** tratador de eventos de arestas */
    private SelectionEdgeHandler seh = SelectionEdgeHandler.getUnit();
    /** isLoop: flag que indica se um vertice possui loop
     *  isBufferLoop: flag que indica se ja existe um loop pra nao haver a
     *  necessidade de desenhar outro loop sobre o existente
     */
    private boolean isLoop = false, isBufferLoop = false;
    /** tamanho padrao dos vertices */
    public static final int SIZE = 50;
    /** objetos de desenho do painel */
    private Graphics2D graphics, vertexGraphics;        
}// Panel