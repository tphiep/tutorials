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

    public int increaseLongVersion(){
        int oldValue = this.count.get();
        while(!this.count.compareAndSet(oldValue, oldValue + 1)) {
            oldValue = this.count.get(); // reset old value
        }
        return oldValue + 1;
    }

    public int getCount() {
        return count.get();
    }
}
