package com.example.pratc8;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Component2 implements IObserver {
    private Label label;
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;

    public Component2(Label label) {
        this.label = label;
        // Загружаем MP3-файл
        String musicFile = "src/main/resources/music/18775-rickroll354.mp3"; // Укажите путь к вашему MP3-файлу
        Media media = new Media(new File(musicFile).toURI().toString());
        this.mediaPlayer = new MediaPlayer(media);
    }

    @Override
    public void update(Subject subject) {
        // Используем Platform.runLater для обновления UI в потоке JavaFX
        Platform.runLater(() -> {
            label.setText("Время работы: " + subject.getState() + " с");
        });
    }

    // Метод для запуска проигрывания
    public void startMusic() {
        mediaPlayer.play();
        isPlaying = true;
    }

    // Метод для остановки проигрывания
    public void stopMusic() {
        mediaPlayer.stop();
        isPlaying = false;
    }

    // Метод для проверки состояния проигрывания
    public boolean isPlaying() {
        return isPlaying;
    }
}