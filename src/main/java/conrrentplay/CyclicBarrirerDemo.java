package conrrentplay;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by liujunshi on 2017/7/7.
 */
public class CyclicBarrirerDemo {

    //士兵  执行任务
    public static class Soldier implements Runnable {
        private String soldier;
        private final CyclicBarrier cyclicBarrier;

        public Soldier(CyclicBarrier cyclicBarrier, String soldier) {
            this.cyclicBarrier = cyclicBarrier;
            this.soldier = soldier;
        }

        public void run() {

            try {
                cyclicBarrier.await();
                dowork();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }

        void dowork() {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务完成");
        }
    }

    public static class BarrierRun implements Runnable {
        boolean flag;
        int n;

        public BarrierRun(boolean flag, int n) {
            this.flag = flag;
            this.n = n;
        }

        public void run() {

            if (flag) {
                System.out.println("司令: 士兵" + n + "个完成任务");
            } else {
                System.out.println("司令: 士兵" + n + "个集合完毕");
                flag = true;
            }

        }
    }

    public static void main(String args[]) {
        final int N = 10;

        boolean flag = false;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N, new BarrierRun(flag, N));
        System.out.println("队伍集合");

        for (int i = 0; i < N; i++) {
            System.out.println("士兵" + i + "报道");
            new Thread(new Soldier(cyclicBarrier, "士兵" + i)).start();
        }

    }

}
