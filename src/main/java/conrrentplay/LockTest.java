package conrrentplay;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by liujunshi on 2017/7/1.
 */
public class LockTest implements Runnable {

    static int count = 0;
    Lock lock = new ReentrantLock();
//    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String args[]) {
//
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        final LockTest lockTest1 = new LockTest(1);

        for (int i = 0; i < 5; i++) {
            executorService.execute(lockTest1);
        }


//        new Thread(){
//            public void run(){
//                lockTest.print();
//            }
//        }.start();
//
//        new Thread(){
//            public void run(){
//                lockTest.print();
//            }
//        }.start();


    }

    int name;

    public LockTest(int name) {
        this.name = name;
    }

    public void print() {
        count++;
        System.out.println("I am printer: " + name + " curr:" + Thread.currentThread().getName() + " ms" + " time: " + System.currentTimeMillis() + "count =" + count);

        int sleepms = 1000;


        lock.lock();

//        synchronized (this) {
        try {

            Thread.sleep(sleepms);
            count++;
            System.out.println("I am printer: " + name + " curr:" + Thread.currentThread().getName() + ". I wake up .. " + sleepms + " ms" + " time: " + System.currentTimeMillis() + "count=" + count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void run() {
        print();
    }
}
