package unoeste.fipp.mercadofipp.entities.interfaces;

public interface Observable
{
    public void addObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notificar(int qtde);
}
