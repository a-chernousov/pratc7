package com.example.pratc8;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Component3 implements IObserver {
    private Rectangle rectangle;
    private Timeline timeline;

    public Component3(Rectangle rectangle) {
        this.rectangle = rectangle;
        // Создаем Timeline для анимации
        this.timeline = new Timeline(
                new KeyFrame(Duration.seconds(2), new KeyValue(rectangle.translateXProperty(), 200)), // Движение вправо
                new KeyFrame(Duration.seconds(4), new KeyValue(rectangle.translateXProperty(), 0)), // Возврат назад
                new KeyFrame(Duration.seconds(6), new KeyValue(rectangle.rotateProperty(), 360)), // Вращение
                new KeyFrame(Duration.seconds(8), new KeyValue(rectangle.scaleXProperty(), 2)), // Увеличение по горизонтали
                new KeyFrame(Duration.seconds(8), new KeyValue(rectangle.scaleYProperty(), 2)), // Увеличение по вертикали
                new KeyFrame(Duration.seconds(10), new KeyValue(rectangle.scaleXProperty(), 1)), // Возврат к исходному размеру
                new KeyFrame(Duration.seconds(10), new KeyValue(rectangle.scaleYProperty(), 1))  // Возврат к исходному размеру
        );
        timeline.setCycleCount(1); // Анимация выполняется один раз
    }

    @Override
    public void update(Subject subject) {
        // Используем Platform.runLater для обновления UI в потоке JavaFX
        Platform.runLater(() -> {
            if (subject.getState() % 20 == 0) {
                timeline.playFromStart(); // Запускаем анимацию
            }
        });
    }
}