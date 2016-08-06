package com.hiep.concurrency.thread;

/**
 * Created by hiep on 8/5/2016.
 */
public class MyThread extends Thread {
    private int id;

    public MyThread(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("MyThread(id=%d)", this.id);
    }

    @Override
    public void run() {
        System.out.println("Hello from " + this);
    }
}
