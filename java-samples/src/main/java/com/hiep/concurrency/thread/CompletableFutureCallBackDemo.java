package com.hiep.concurrency.thread;

import com.google.common.base.Stopwatch;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hiep on 8/6/2016.
 */
public class CompletableFutureCallBackDemo {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        CompletableFutureDemo demo = new CompletableFutureDemo();
        CompletableFuture<String> future = demo.createCompletableFuture()
                .thenApply((Integer count) -> count * 10 )
                .thenApply(transformed -> {
                    String result = "Finally String " + transformed;
                    System.out.println("Done!!!!!!!!!");
                    return result;
                });

        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("Something need to be done here.");

    }

}
