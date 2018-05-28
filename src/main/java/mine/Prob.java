package mine;

import static java.lang.Math.pow;


public class Prob {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 0; i <= 10; i++) {
            sum += pow(6, i);
        }
        System.out.println(sum);
        System.out.println((pow(6,10) - 1)/5);
        System.out.println(pow(6,10));
    }
}
