package com.javarush.task.task28.task2805;

import java.lang.ThreadGroup;
import java.lang.Runnable;

public class MyThread extends Thread {
    private static int prior = Thread.MIN_PRIORITY;

    {
        if (prior > Thread.MAX_PRIORITY) {
            prior = Thread.MIN_PRIORITY;
        }
        if(Thread.currentThread().getThreadGroup() != null){
            if(Thread.currentThread().getThreadGroup().getMaxPriority() >= prior){
                this.setPriority(prior++);
            }else{
                this.setPriority(Thread.currentThread().getThreadGroup().getMaxPriority());
            }
        }else {
            this.setPriority(prior++);
        }
    }

    public MyThread() {
    }

    public MyThread(Runnable target) {
        super(target);
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
    }

    public MyThread(String name) {
        super(name);
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
    }

}
