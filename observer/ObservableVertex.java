package observer;

import model.Vertex;

public interface ObservableVertex extends Observable{
    public Vertex getState();
}// ObservableVertex