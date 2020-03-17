package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
    Thread thread;
    State s;
    State newS;

    public LoggingStateThread(Thread thread) {
        this.thread = thread;
    }

    public void run() {

        s = thread.getState();
        System.out.println(s);

        while (s != State.TERMINATED) {
            newS = thread.getState();
            if (s != newS) {
                System.out.println(newS);
            }
            s = newS;
        }
    }
}
