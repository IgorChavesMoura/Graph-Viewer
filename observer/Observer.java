package observer;

/** classe que cria instâncias de observadores
 *  utiliza o padrão de projeto Observer.
 */
public interface Observer{
    /**
     * atualiza informações baseadas no estado do observavel recebido
     * @param o observavel que transmite a informação de estado
     */
    public void update(ObservableVertex o);
}// Observer