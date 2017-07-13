package prime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujunshi on 2017/6/2.
 */
public class EulerFunc {

    long n;

    List<EulerUnit> eulerUnitList;

    //构造函数
    public EulerFunc(long n) {
        this.n = n;
        eulerUnitList = new ArrayList<EulerUnit>();

        //分解质因数
        String[] ss = PrimeNumberUtil.findPrimeFactor(n);
        String temp = "";
        EulerUnit eu = new EulerUnit(0, 0);
        int i = 1;
        for (String s : ss) {
            if (s == null || s.length() == 0) {
                break;
            }
            if (!temp.equals(s)) {
                eu = new EulerUnit(Long.valueOf(s), 1); //设置底数为s 指数为1
                temp = s;
                eulerUnitList.add(eu);
            } else {
                eu.incr(); //指数加1
            }

        }

    }

    // 小于等于n的正整数之中，有多少个与n构成互质关系 φ(n)

    public long getEulerNumber() {

        if (eulerUnitList == null || eulerUnitList.size() == 0) {
            return 1;
        }
        float t = 0;

        for (EulerUnit eu : eulerUnitList) {
            if (t == 0) {
                t = (1 - (1f / eu.getP()));
            } else {
                float x = 1 - (1f / eu.getP());
                t = t * x;
            }
        }

        return Math.round(n * t);
    }


    /**
     * 随机选择一个整数e，条件是1< e < φ(n)，且e与φ(n) 互质
     *
     * @param r
     * @return 返回所有
     */
    public ArrayList<Long> getE(long r) {

        ArrayList<Long> plist = new ArrayList<Long>();

        for (int i = 2; i < r; i++) {
            if (isRelativelyPrime(i, r)) {
                plist.add(Long.valueOf(i));
            }
        }
        return plist;
    }

    //判断两个数是否为互质关系 几里德算法（Euclidean algorithm）
    private boolean isRelativelyPrime(long a, long b) {
        if (a < b) {
            long tmp = a;
            a = b;
            b = tmp;
        }

        long c;
        while ((c = a % b) != 0) {
            a = b;
            b = c;
        }
        return b == 1;//是否互质 ,b就是最大公约数.

    }


    //内部类,用来拆分n的质因数,一个整数一定可以拆分成几个质数的乘积.因为如果不能拆分的则就是质数了.
    class EulerUnit {
        long p; //底数
        long k; //指数

        public EulerUnit(long p, long k) {
            this.p = p;
            this.k = k;
        }

        public void incr() {
            k++;
        }

        public long getP() {
            return p;
        }

        public void setP(long p) {
            this.p = p;
        }

        public long getK() {
            return k;
        }

        public void setK(long k) {
            this.k = k;
        }
    }

}
