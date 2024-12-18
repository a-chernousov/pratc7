package com.example.pratc8;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class HelloController implements Subject {

    @FXML
    private Label welcomeText;

    @FXML
    private Label timeLabel;

    @FXML
    private Rectangle animationRectangle;

    private TimeServer timeServer;
    private boolean isRunning = false;
    private Component2 component2; // Добавляем Component2

    private List<IObserver> observers = new ArrayList<>();

    @FXML
    protected void initialize() {
        // Инициализация сервера времени
        timeServer = new TimeServer(0, 1000, this); // Передаем текущий контроллер

        // Добавление наблюдателей
        Component1 component1 = new Component1(timeLabel);
        component2 = new Component2(timeLabel); // Инициализируем Component2
        Component3 component3 = new Component3(animationRectangle);

        attach(component1);
        attach(component2); // Добавляем Component2 в список наблюдателей
        attach(component3);

        // Запуск сервера времени
        timeServer.start();
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onStartStopButtonClick() {
        if (isRunning) {
            timeServer.stop();
            isRunning = false;
        } else {
            timeServer.start();
            isRunning = true;
        }
    }

    @FXML
    protected void onStartMusicButtonClick() {
        component2.startMusic(); // Запуск музыки
    }

    @FXML
    protected void onStopMusicButtonClick() {
        component2.stopMusic(); // Остановка музыки
    }

    // Реализация интерфейса Subject
    @Override
    public void notifyAllObservers() {
        Platform.runLater(() -> {
            for (IObserver observer : observers) {
                observer.update(this);
            }
        });
    }

    @Override
    public void attach(IObserver obs) {
        observers.add(obs);
    }

    @Override
    public void detach(IObserver obs) {
        observers.remove(obs);
    }

    @Override
    public int getState() {
        return timeServer.getState();
    }
}