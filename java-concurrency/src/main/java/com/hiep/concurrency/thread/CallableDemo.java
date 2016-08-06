package com.hiep.concurrency.thread;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hiep on 8/5/2016.
 */
public class CallableDemo {
    public static void main(String[] args) {
        List<Callable<String>> callables = Stream.iterate(1, n -> n + 1)
                .map(MyCallable::new)
                .limit(10)
                .collect(Collectors.toList());

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        try {
            List<Future<String>> futures = executorService.invokeAll(callables);
            for (Future<String> future : futures){
                System.out.println(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }


    }
}
