package com.hiep.concurrency.thread.semaphore;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hiep on 8/7/2016.
 * What are some possible uses for counting semaphores? The following come to mind:
 *
 *   Limiting concurrent access to disk (this can kill performance due to competing disk seeks)
 *   Thread creation limiting
 *   JDBC connection pooling / limiting
 *   Network connection throttling
 *   Throttling CPU or memory intensive tasks
 */
public class ConnectionLimiter {
    private final Semaphore semaphore;
    private static AtomicInteger count = new AtomicInteger();

    public ConnectionLimiter(int maxConcurrentRequest) {
        this.semaphore = new Semaphore(maxConcurrentRequest);
    }

    public URLConnection acquire(URL url) throws InterruptedException, IOException {
        System.out.println(String.format("About to get new connection, number of connections in pool %d", count.get() + 1));
        semaphore.acquire();
        System.out.println("Acquired connection number of connection in pool " + count.incrementAndGet());
        return url.openConnection();
    }

    public void release(){
        try {
            count.decrementAndGet();
            System.out.println(String.format("About to release connection, number of connections in pool %d", count.get() + 1));
        } finally {
            semaphore.release();
        }
    }
}
