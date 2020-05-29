package lambda.designPattern.observerPattern;

public interface Subject {
    void rigisterObserver(Observer observer);
    void notifyObserver(String tweet);
}
