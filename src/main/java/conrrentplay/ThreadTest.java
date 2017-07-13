package conrrentplay;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liujunshi on 2017/7/1.
 */
public class ThreadTest implements Runnable {
    int x = 0;
    private static AtomicInteger ai = new AtomicInteger(0);
    volatile int  y = 0;

    public static void main(String args[]) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ExecutorService executorService1 = Executors.newCachedThreadPool();

        final ThreadTest ThreadTest = new ThreadTest();



        for (int i = 0; i < 5; i++) {
            executorService.execute(ThreadTest);

        }


        //executorService.invokeAll()
    }


    public void run() {

        for (int j = 0; j < 10; j++) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            x++;
            y++;
            ai.incrementAndGet();
        }


        System.out.println(Thread.currentThread().getName() + " x = " + x);
        System.out.println(Thread.currentThread().getName() + " y = " + y);
        System.out.println(Thread.currentThread().getName() + " ai = " + ai.toString());

    }
}
