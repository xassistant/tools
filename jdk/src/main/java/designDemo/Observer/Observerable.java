package designDemo.Observer;

public interface Observerable {

    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObserver();

}