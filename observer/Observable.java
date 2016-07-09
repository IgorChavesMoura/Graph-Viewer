package observer;

/** 
 * implementa o padrão de projeto de software Observer
 */
public interface Observable {
    /** Insere um observador para o observavel
     * @param o novo observador
     */
    public void attach(Observer o);
    /** remove um observador para o observavel
     * @param o observador a ser removido
     */    
    public void detach(Observer o);
    /** notifica a todos os observadores */
    public void send();
}// Observable
