package com.hiep.concurrency.thread.forkjoin;

import java.util.concurrent.ForkJoinPool;

/**
 * Created by hiep on 8/6/2016.
 */
public class JoinForkTest {
    public static void main(String[] args) {
        Problem test = new Problem();
        // check the number of available processors
        int nThreads = Runtime.getRuntime().availableProcessors();
        System.out.println(nThreads);
        Solver mfj = new Solver(test.getList());
        ForkJoinPool pool = new ForkJoinPool(nThreads);
        pool.invoke(mfj);
        long result = mfj.getResult();
        System.out.println("Done. Result: " + result);
        long sum = 0;
        // check if the result was ok
        for (int i = 0; i < test.getList().length; i++) {
            sum += test.getList()[i];
        }
        System.out.println("Split count: " + mfj.splitCount());
        System.out.println("Done. Result: " + sum);
    }
}
