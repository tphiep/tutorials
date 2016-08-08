package com.hiep.concurrency.lock;

import java.util.concurrent.CountDownLatch;

/**
 * Created by hiep on 8/5/2016.
 */
public class LachDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch stopSignal = new CountDownLatch(1000);
        Counter counter = new Counter();
        for (int i = 0; i < 1000; i++){
            new Thread(new Worker(i, startSignal, stopSignal, counter)).start();
        }
        System.out.println("Work done before starting workers.");
        startSignal.countDown();
        System.out.println("Doing work while worker are running.");
        stopSignal.await();
        System.out.println("All works are done.");
        System.out.println("Counter count=" + counter.getCount());
    }
}
