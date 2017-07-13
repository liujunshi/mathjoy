package conrrentplay;

/**
 * Created by liujunshi on 2017/7/7.
 */
public class DeamonTest extends Thread {

    public void run(){
        while (true){
            System.out.println("I am here");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String args[]) throws InterruptedException {
        Thread t1 = new DeamonTest();
        //优先级 t1.setPriority(10);
        t1.setDaemon(true);
        t1.start();
       // 不退出,但是无效
       // t1.setDaemon(true);
        Thread.sleep(3000);
    }

}
