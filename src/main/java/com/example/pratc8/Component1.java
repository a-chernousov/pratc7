package com.example.pratc8;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class Component1 implements IObserver {
    private Label label;

    public Component1(Label label) {
        this.label = label;
    }

    @Override
    public void update(Subject subject) {
        // Используем Platform.runLater для обновления UI в потоке JavaFX
        Platform.runLater(() -> {
            label.setText(subject.getState() + " с");
        });
    }
}