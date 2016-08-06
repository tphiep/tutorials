package com.hiep.concurrency.thread;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Created by hiep on 8/5/2016.
 */
public class FileLinesCounter {
    private Path dir = Paths.get("src", "main", "java", "com", "oreilly", "advancedJava", "concurrent");

    private List<Future<Long>> executeCounters() throws IOException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<Long>> futures = executorService.invokeAll(getFileLineCounters());
        executorService.shutdown();
        return futures;
    }

    private List<Callable<Long>> getFileLineCounters() throws IOException {
        return Files.list(dir)
                .filter(Files::isRegularFile)
                .map(this::callablePrintingCounter)
                .collect(Collectors.toList());
    }

    private Callable<Long> callableCounter(Path p){
        return () -> Files.lines(p).count();
    }

    private Callable<Long> callablePrintingCounter(Path p){
        return () -> {
            Long count = Files.lines(p).count();
            System.out.printf("%s has %d lines\n", p, count);
            return count;
        };
    }

    private <T> T extractValueFromFuture(Future<T> future){
        T val = null;
        try {
            val = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return val;
    }

    public long computeTotalNumberOfLines(){
        long total = 0;
        try {
            total = executeCounters()
                    .stream()
                    .mapToLong(this::extractValueFromFuture)
                    .sum();
        } catch (InterruptedException | IOException e){
            e.printStackTrace();
        }
        return total;
    }

    public static void main(String[] args) {
        Long lines = new FileLinesCounter().computeTotalNumberOfLines();
        System.out.println(lines);
    }
}
