package conrrentplay;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by liujunshi on 2017/7/6.
 */
public class InvokeAnyTest {







    ExecutorService executorService = Executors.newFixedThreadPool(3);

    List<Callable<String>> tasks = new ArrayList<Callable<String>>();


    public void invokeAny1() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Callable<String>> tasks = new ArrayList<Callable<String>>();

        tasks.add(new SleepSecondsCallable("t1", 2));
        tasks.add(new SleepSecondsCallable("t2", 1));

        String result = executorService.invokeAny(tasks);

        System.out.println("result=" + result);

        executorService.shutdown();
    }

    public static void main(String args[]) {
        InvokeAnyTest invokeAnyTest = new InvokeAnyTest();
        try {
            invokeAnyTest.invokeAny1();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
