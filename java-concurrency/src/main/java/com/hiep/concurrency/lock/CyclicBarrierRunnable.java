package com.hiep.concurrency.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by hiep on 8/6/2016.
 */
public class CyclicBarrierRunnable implements Runnable {
    private CyclicBarrier barrier1;
    private CyclicBarrier barrier2;

    public CyclicBarrierRunnable(CyclicBarrier barrier1, CyclicBarrier barrier2) {
        this.barrier1 = barrier1;
        this.barrier2 = barrier2;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.printf("%s waiting at barrier 1\n", Thread.currentThread().getName());
            this.barrier1.await();
            System.out.printf("%s waiting at barrier 2\n", Thread.currentThread().getName());
            this.barrier2.await();
            System.out.printf("%s done.", Thread.currentThread().getName());
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
