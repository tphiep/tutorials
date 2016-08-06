package com.hiep.concurrency.thread;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hiep on 8/5/2016.
 */
public class UseEcecutors {
    public static void main(String[] args) {
        List<MyRunnable> runnables = Stream.iterate(0, n -> n + 1)
                .map(MyRunnable::new)
                .limit(50)
                .collect(Collectors.toList());

        ExecutorService executorService = Executors.newCachedThreadPool();
        runnables.forEach(executorService::execute);
        executorService.shutdown();
    }
}
