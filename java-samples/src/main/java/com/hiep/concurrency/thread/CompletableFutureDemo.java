package com.hiep.concurrency.thread;

import com.google.common.base.Stopwatch;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by hiep on 8/6/2016.
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();

        CompletableFutureDemo demo = new CompletableFutureDemo();
        CompletableFuture<Integer> completableFuture = demo.createCompletableFuture();
        stopwatch.stop();
        System.out.printf("Took %d ms\n", stopwatch.elapsed(TimeUnit.MILLISECONDS));
        stopwatch.start();
        try {
            int count = completableFuture.get();
            stopwatch.stop();
            System.out.printf("Completable Future took %d ms\n", stopwatch.elapsed(TimeUnit.MILLISECONDS));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("Does this statement can run during counter is executing in another thread.");

    }

    protected CompletableFuture<Integer> createCompletableFuture(){
        return CompletableFuture.supplyAsync(
                () -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    return 20;
                }
        );
    }
}
