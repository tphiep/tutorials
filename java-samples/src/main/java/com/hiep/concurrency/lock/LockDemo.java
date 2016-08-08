package com.hiep.concurrency.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by hiep on 8/5/2016.
 */
public class LockDemo {

    public void demoCounter() {
        Counter counter = new Counter();
        ExecutorService executorService = Executors.newCachedThreadPool();
        IntStream.range(0, 1000).forEach(i -> executorService.submit(counter::increment));
        executorService.shutdown();
        try {
            executorService.awaitTermination(100, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Counter count=" + counter.getCount());
    }

    public void demoVolatileCounter() {
        VolatileCounter counter = new VolatileCounter();
        ExecutorService executorService = Executors.newCachedThreadPool();
        IntStream.range(0, 1000).forEach(i -> executorService.submit(counter::increment));
        executorService.shutdown();
        try {
            executorService.awaitTermination(100, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Counter count=" + counter.getCount());
    }

    public void demoSyncCounter() {
        SyncCounter counter = new SyncCounter();
        ExecutorService executorService = Executors.newCachedThreadPool();
        IntStream.range(0, 1000).forEach(i -> executorService.submit(counter::increment));
        executorService.shutdown();
        System.out.println("Counter count=" + counter.getCount());
    }

    public void demoAtomicCounter() {
        AtomicCounter counter = new AtomicCounter();
        ExecutorService executorService = Executors.newCachedThreadPool();
        IntStream.range(0, 1000).forEach(i -> executorService.submit(counter::increment));
        executorService.shutdown();
        System.out.println("Counter count=" + counter.getCount());
    }

    public void demoLockCounter() {
        LockCounter counter = new LockCounter();
        ExecutorService executorService = Executors.newCachedThreadPool();
        IntStream.range(0, 1000).forEach(i -> executorService.submit(counter::increment));
        executorService.shutdown();
        try {
            executorService.awaitTermination(100, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Counter count=" + counter.getCount());
    }

    public static void main(String[] args) {
        LockDemo lockDemo = new LockDemo();
        lockDemo.demoVolatileCounter();
    }
}
