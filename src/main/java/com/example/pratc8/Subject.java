package com.example.pratc8;

public interface Subject {
    void notifyAllObservers();
    void attach(IObserver obs);
    void detach(IObserver obs);
    int getState();
}