package unoeste.fipp.mercadofipp.entities;

import java.util.ArrayList;
import java.util.List;

public interface Observable
{
    List<Observer> observerList = new ArrayList<>();
    public void addObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notificar(int qtde);
}
