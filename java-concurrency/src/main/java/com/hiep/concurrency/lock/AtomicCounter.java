package com.hiep.concurrency.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hiep on 8/5/2016.
 */
public class AtomicCounter {
    private AtomicInteger count = new AtomicInteger();

    public void increment(){
        this.count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }
}
