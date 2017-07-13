package conrrentplay;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by liujunshi on 2017/7/6.
 */
public class InvokeallTest {
    // 固定大小的线程池，同时只能接受5个任务
    ExecutorService mExecutor = Executors.newFixedThreadPool(5);
    ExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

    public static void testInvokeAll() throws Exception
    {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        List<Callable<String>> tasks = new ArrayList<Callable<String>>();
        tasks.add(new SleepSecondsCallable("t1", 2));
        tasks.add(new SleepSecondsCallable("t2", 2));
        tasks.add(new RandomTenCharsTask());
        tasks.add(new ExceptionCallable());

        // 调用该方法的线程会阻塞,直到tasks全部执行完成(正常完成/异常退出)
        List<Future<String>> results = executorService.invokeAll(tasks);

        // 任务列表中所有任务执行完毕,才能执行该语句
        System.out.println("wait for the result." + results.size());

        executorService.shutdown();

        for (Future<String> f : results)
        {
            // isCanceled=false,isDone=true
            System.out.println("isCanceled=" + f.isCancelled() + ",isDone="
                    + f.isDone());

            // ExceptionCallable任务会报ExecutionException
            System.out.println("task result=" + f.get());
        }
    }


    public void testInvokeAllTimeout() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        List<Callable<String>> tasks = new ArrayList<Callable<String>>();
        tasks.add(new SleepSecondsCallable("t1", 2));
        tasks.add(new SleepSecondsCallable("t2", 2));
        tasks.add(new SleepSecondsCallable("t3", 3));
        tasks.add(new RandomTenCharsTask());

        List<Future<String>> results = executorService.invokeAll(tasks, 1,
                TimeUnit.SECONDS);

        System.out.println("wait for the result." + results.size());

        for (Future<String> f : results) {
            System.out.println("isCanceled=" + f.isCancelled() + ",isDone="
                    + f.isDone());
        }

        executorService.shutdown();

    }

    public static void main(String args[]){

        InvokeallTest invokeallTest = new InvokeallTest();
        try {
            invokeallTest.testInvokeAllTimeout();
            //invokeallTest.testInvokeAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
