package com.hiep.concurrency.lock;

/**
 * Created by hiep on 8/5/2016.
 */
public class SyncCounter {
    private int count;

    public synchronized void increment() {
        this.count++;
    }

    public synchronized int getCount() {
        return count;
    }
}
