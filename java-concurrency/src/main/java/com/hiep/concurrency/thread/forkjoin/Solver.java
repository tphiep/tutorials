package com.hiep.concurrency.thread.forkjoin;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hiep on 8/6/2016.
 */
public class Solver extends RecursiveAction {
    private int[] list;
    public long result;
    private static AtomicInteger couner = new AtomicInteger();

    public Solver(int[] array) {
        this.list = array;
    }

    @Override
    protected void compute() {
        if (list.length == 1) {
            result = list[0];
        } else {
            int midpoint = list.length / 2;
            int[] l1 = Arrays.copyOfRange(list, 0, midpoint);
            int[] l2 = Arrays.copyOfRange(list, midpoint, list.length);
            Solver s1 = new Solver(l1);
            Solver s2 = new Solver(l2);
            System.out.println("Split: " + midpoint);
            invokeAll(s1, s2);

            couner.incrementAndGet();

            result = s1.result + s2.result;
        }
    }

    public long getResult() {
        return result;
    }

    public int splitCount(){
        return couner.get();
    }
}
