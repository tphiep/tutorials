package com.hiep.concurrency.thread;

/**
 * Created by hiep on 8/5/2016.
 */
public class MyRunnable implements Runnable {
    private int id;

    public MyRunnable(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("MyRunnable(id=%d), Thread(id=%d)", this.id, Thread.currentThread().getId());
    }

    @Override
    public void run() {
        System.out.println("Hello from " + this);
    }
}
