package thread;

import view.panel.Panel;

/** thread que trata da pintura dos vértices
 *  implementa o padrão de projeto de software Singleton.
 */
public class ThreadPaint implements Runnable{
    /**
     * @return retorna a unica instancia da classe */
    public static ThreadPaint getUnit(){
            if(unit == null)
                    unit = new ThreadPaint();
            return unit;
    }
    /** configura a velocidade de passo do algoritmo
     * @param newSpeed nova velocidade 
     */
    public void setSpeed(int newSpeed){
            speed = newSpeed;
    }
    /**
     * @return retorna o valor de velocidade de passo do algoritmo.  */
    public int getSpeed(){
        return speed;
    }

    /** construtor */
    private ThreadPaint(){
            t = new Thread(this, "Paint");
    }// constructor

    /**
     * @return flag que indica se a thread ainda está em execução  */
    public boolean isAlive(){
            return t.isAlive();
    }

    @Override
    public void run(){
            Panel.getUnit().repaint();
            try{
                    t.sleep(speed);
            } catch(InterruptedException e){
                    System.out.println(t +": " + e);
            }
    }

    Thread t;
    int speed = 500;        
    private static ThreadPaint unit;
}