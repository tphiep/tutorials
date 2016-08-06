package com.hiep.concurrency.lock;

/**
 * Created by hiep on 8/5/2016.
 */
public class Counter {
    private int count;

    public void increment(){
        this.count++;
    }

    public int getCount(){
        return this.count;
    }
}
