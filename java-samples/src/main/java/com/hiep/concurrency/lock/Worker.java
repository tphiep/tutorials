package com.hiep.concurrency.lock;

import java.util.concurrent.CountDownLatch;

/**
 * Created by hiep on 8/5/2016.
 */
public class Worker implements Runnable {
    private final CountDownLatch startSignal;
    private final CountDownLatch stopSignal;
    private final int id;
    private final Counter counter;

    public Worker(int id, CountDownLatch startSignal, CountDownLatch stopSignal, Counter counter) {
        this.startSignal = startSignal;
        this.stopSignal = stopSignal;
        this.id = id;
        this.counter = counter;
    }

    @Override
    public void run() {
        try {
            System.out.printf("Worker(id=%d) waiting to start...\n", id);
            startSignal.await();
            counter.increment();
            System.out.printf("Worker(id=%d) running to complettion...\n", id);
            stopSignal.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
