package prime;

import java.util.Scanner;

/**
 * Created by liujunshi on 2017/5/31.
 */
public class PrimeNumberUtil {

    public static void main(String[] args) {
        System.out.println("输入所求正整数:");
        Scanner sc = new Scanner(System.in);
        Long n = sc.nextLong();

        //isPrimeNumber(n);

        findPrimeFactor(n);

        sc.close();
    }


    public static boolean isPrimeNumber(long n) {

        int c = 0;
        boolean r = false;
        for (int b = 2; b < n; b++) {
            if (n % b != 0) {
                c++;
            }
        }
        if (c == n - 2) {
            System.out.println(n + "是质数");
            r = true;
        } else {
            System.out.println(n + "不是质数");
        }

        return r;

    }

    //找出最质因数
    public static String[] findPrimeFactor(long n) {

        long m = n;
        int flag = 0;
        String[] str = new String[50];
        for (long i = 2; i <= n; i++) {
            if (n % i == 0) {
                str[flag] = Long.toString(i);
                flag++;
                n = n / i;
                i--;
            }
        }

        //打印输出
        if (flag < 2)
            System.out.println(m + "为质数");
        else {
            System.out.print(m + "=" + str[0]);
            for (int k = 1; k < flag; k++) {
                System.out.print("*" + str[k]);
            }
            System.out.println("\n" + m + "共有" + flag + "个质因数.");
        }
        return str;

    }

    // e*d =1 (mod 1)

    public static  int[] ModularInverse(int a,int b,int[] p)//这里假设a > b
    {
        if(a%b == 1)
        {
            p[0] = 1;
            p[1] = -(a - 1) / b;
            return p;
        }
        else
        {
            ModularInverse(b, a % b,p);
            int t = p[0];
            p[0] = p[1];
            p[1] = t - (a / b) * p[1];
            return p;
        }
    }

}
