package com.hiep.concurrency.thread.semaphore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.net.URL;

/**
 * Created by hiep on 8/7/2016.
 */

@RunWith(JUnit4.class)
public class ConnectionLimiterTest {

    private ConnectionLimiter connectionLimiter = new ConnectionLimiter(5);

    @Test
    public void testAcquire() throws Exception {
        Thread relaseThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        Thread.sleep(100);
                        connectionLimiter.release();
                    }
                } catch ( InterruptedException e ) {
                    e.printStackTrace();
                }
            }
        });
        relaseThread.start();
        for (int i = 0; i < 10; i++){
            connectionLimiter.acquire(new URL("http://vnexpress.net"));
        }
    }
}