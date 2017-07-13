package prime;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by liujunshi on 2017/6/2.
 * 模反元素
 */
public class ModularInverse {

    public static void main(String[] args) {
        //例子为算 a * x + b * y ==1 的解

        int a = 17;
        int b = 3120;
        //getModularInverse(a, b, p);
        ModularInverse mi  = new ModularInverse();

        Map<Long, Long> dmap = mi.getMT(a, b);
        for (Map.Entry entry : dmap.entrySet()) {
            System.out.print("p[0] is: " + entry.getKey() + ";p[1] is：" + entry.getValue());//p1为私钥
        }

    }

    /**
     * e*d = 1 (mod 1)
     * ax + by = 1
     */
    public  long[] getModularInverse(long a, long b, long[] p) {
        if (a % b == 1) {
            p[0] = 1;
            p[1] = -(a - 1) / b;
            return p;
        } else {
            getModularInverse(b, a % b, p);
            long t = p[0];
            p[0] = p[1];
            p[1] = t - (a / b) * p[1];
            return p;
        }
    }

    /*
      ax + by =1
      y = (1-ay)/b 循环 x ,如果y 也是整数,则符合结果.
     */

    public  Map<Long, Long> getMT(long a, long b) {
        Map<Long, Long> pmap = new HashMap<Long, Long>();
        int i = 0;
        while (true) {
            i++;
            double y = (1d - a * i) / b;
            int yi = (int) y;
            if (y == yi) {
                pmap.put(Long.valueOf(i), Long.valueOf(yi));
//                System.out.println("OK ! x = "+i + " ,y = "+y);
                if (pmap.size() >= 2) break; //去两组
            }
//            System.out.println("不行,x = "+i + " ,y = "+y );

        }

        return pmap;
    }

    public <K, V> Map.Entry<K, V> getHead(LinkedHashMap<K, V> map) {
        return map.entrySet().iterator().next();
    }

}
