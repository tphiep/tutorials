package com.hiep.concurrency.lock;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by hiep on 8/6/2016.
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier barrier1 = new CyclicBarrier(10);
        CyclicBarrier barrier2 = new CyclicBarrier(10);

        for (int i = 0; i < 10; i++) {
            new Thread(new CyclicBarrierRunnable(barrier1, barrier2)).start();
        }
    }
}
