package conrrentplay.collenction;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by liujunshi on 2017/7/13.
 */
public class QueueTest implements Runnable {

    //阻塞队列
    LinkedBlockingDeque<Integer> linkedBlockingDeque;
    ArrayBlockingQueue<Integer> arrayBlockingQueue;
    PriorityBlockingQueue priorityBlockingQueue;

    //非阻塞队列
    ConcurrentLinkedQueue<Integer> concurrentLinkedQueue ;


    public void put(Integer v) {
        linkedBlockingDeque.add(v);
        try {
            linkedBlockingDeque.put(v);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        arrayBlockingQueue.add(v);

        priorityBlockingQueue.add(v);
        priorityBlockingQueue.put(v);

        concurrentLinkedQueue.add(v);
        concurrentLinkedQueue.offer(v);


    }

    public void get(){
        try {
            linkedBlockingDeque.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        concurrentLinkedQueue.poll();

    }

    public QueueTest(LinkedBlockingDeque<Integer> linkedBlockingDeque) {
        this.linkedBlockingDeque = linkedBlockingDeque;
    }

    public QueueTest(ArrayBlockingQueue<Integer> arrayBlockingQueue) {
        this.arrayBlockingQueue = new ArrayBlockingQueue<Integer>(10, false);
    }

    public QueueTest(PriorityBlockingQueue priorityBlockingQueue) {
        this.priorityBlockingQueue = new PriorityBlockingQueue();

    }

    public QueueTest(ConcurrentLinkedQueue<Integer> concurrentLinkedQueue) {
        this.concurrentLinkedQueue = new ConcurrentLinkedQueue<Integer>();
    }

    public void run() {

    }
}
