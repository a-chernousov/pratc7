package com.example.pratc8;

import java.util.Timer;
import java.util.TimerTask;

public class TimeServer {
    private Timer timer;
    private TimerTask task;
    private int timeState = 0;
    private long delay;
    private long period;
    private Subject subject; // Ссылка на контроллер (Subject)

    public TimeServer(long delay, long period, Subject subject) {
        this.delay = delay;
        this.period = period;
        this.subject = subject; // Передаем контроллер
    }

    public void start() {
        if (timer != null) {
            stop();
        }
        timer = new Timer();
        task = new TimerTask() {
            public void run() {
                tick();
            }
        };
        timer.schedule(task, delay, period);
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void tick() {
        timeState++;
        subject.notifyAllObservers(); // Уведомляем контроллер о тике
    }

    public int getState() {
        return timeState;
    }
}