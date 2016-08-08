package com.hiep.concurrency.thread;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hiep on 8/5/2016.
 */
public class UseMyRunnable {
    public static void main(String[] args) {
        List<Thread> threads = Stream.iterate(0, n -> n + 1)
                .map(id -> new Thread(new MyRunnable(id)))
                .limit(10)
                .collect(Collectors.toList());

        threads.forEach(Thread::start);
    }
}
