package com.hiep.concurrency.lock;

/**
 * Created by hiep on 8/5/2016.
 */
public class VolatileCounter {
    // Volatile is not enough, http://tutorials.jenkov.com/java-concurrency/volatile.html
    private volatile int count;

    public void increment(){
        count++;
    }

    public int getCount() {
        return count;
    }
}
