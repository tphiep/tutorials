package com.hiep.concurrency.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hiep on 8/5/2016.
 */
public class LockCounter {
    private int count = 0;
    private ReentrantLock lock = new ReentrantLock();

    public void increment(){
        lock.lock();
        try {
            this.count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        lock.lock();
        try {
            return this.count;
        } finally {
            lock.unlock();
        }

    }
}
