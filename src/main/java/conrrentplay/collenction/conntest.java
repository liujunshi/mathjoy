package conrrentplay.collenction;

import java.util.*;

/**
 * Created by liujunshi on 2017/7/7.
 */
public class conntest {

    public void dowork() {
        Map<Integer, String> map = Collections.synchronizedMap(new HashMap<Integer, String>());

        Vector<String> vector = new Vector<String>();

    }

    public static void main(String []args){
        //!! Interger 为 -127 到 128 留有缓存

        Integer a = 1000, b = 1000;
        System.out.println(a==b);

        Integer c = 100, d = 100;
        System.out.println(c==d);
    }
}
