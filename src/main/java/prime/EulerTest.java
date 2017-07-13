package prime;

import java.util.*;

/**
 * Created by liujunshi on 2017/6/2.
 */
public class EulerTest {

    /**
     * 密钥的产生：
     * 1.选择两个大素数 p,q ,计算 n=p*q;
     * 2.随机选择加密密钥 e ,要求 e 和 (p-1)*(q-1)互质
     * 3.利用 Euclid 算法计算解密密钥 d , 使其满足 e*d = 1(mod(p-1)*(q-1)) (其中 n,d 也要互质)
     * 4:至此得出公钥为 (n,e) 私钥为 (n,d)
     * <p/>
     * 加解密方法：
     * 1.首先将要加密的信息 m(二进制表示) 分成等长的数据块 m1,m2,...,mi 块长 s(尽可能大) ,其中 2^s<n
     * 2:对应的密文是： ci = mi^e(mod n)
     * 3:解密时作如下计算： mi = ci^d(mod n)
     */

    public static void main(String[] args) {
        int n = 3 * 7;

        EulerFunc ef = new EulerFunc(n);
        ModularInverse mi = new ModularInverse();
        //获取r = φ(n）
        long r = ef.getEulerNumber();

        // e取随机数随机，条件是1< e < φ(n)，且e与φ(n) 互质。

        long e = ef.getE(r).get(0);


        //获取模发元素 e*d -1 = ry -> ed + ry = 1
        long d = 0;


        Map<Long, Long> dmap = mi.getMT(e, r);

        //模反元素不能等于e
        for (Map.Entry<Long, Long> entry : dmap.entrySet()) {
            if ((d = entry.getKey()) != e) {
                break;
            }
//            d = entry.getKey();break;
        }
        long publicKey[] = {n, e};
        long privateKey[] = {n, d};

        System.out.println("r(φ(n): " + r + ",公钥 : " + n + "," + e + " 私钥 : " + n + "," + d);

        //且m必须小于n


        for (int m = 1; m < n; m++) {
            long mEncode = RSACoder.rsaEncode(publicKey[0], publicKey[1], m);
            long mDncode = RSACoder.rsaEncode(privateKey[0], privateKey[1], mEncode);
            boolean f = mDncode == m;
            System.out.println(f + " 原内容: " + m + ",加密后:" + mEncode + ",解密后:" + mDncode);
        }


    }

}
