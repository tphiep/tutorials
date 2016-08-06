package com.hiep.concurrency.thread;

import java.util.concurrent.Callable;

/**
 * Created by hiep on 8/5/2016.
 */
public class MyCallable implements Callable<String> {
    private int id;

    public MyCallable(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return String.format("%s using thread %s ", this, Thread.currentThread().getName());
    }

    @Override
    public String toString() {
        return String.format("MyCallable(id=%d)", this.id);
    }
}
