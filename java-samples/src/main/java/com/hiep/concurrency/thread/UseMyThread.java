package com.hiep.concurrency.thread;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hiep on 8/5/2016.
 */
public class UseMyThread {
    public static void main(String[] args) {
        List<MyThread> threads = Stream.iterate(1, n -> n + 1)
                .map(MyThread::new)
                .limit(10)
                .collect(Collectors.toList());
        threads.forEach(MyThread::start);
    }
}
