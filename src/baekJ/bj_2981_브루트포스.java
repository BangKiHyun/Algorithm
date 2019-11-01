package baekJ;

import java.util.*;

public class bj_2981_브루트포스 {
    private static int n;
    private static int[] arr_M;
    private static Set<Integer> divisorNum;

    public static void main(String[] args) {
        init();
        divisorNum = getDivisor(arr_M[n - 1] - arr_M[0]);
        findNum();
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr_M = new int[n];
        for (int i = 0; i < n; i++) {
            arr_M[i] = sc.nextInt();
        }
        Arrays.sort(arr_M);
    }

    private static Set<Integer> getDivisor(int num) {
        Set<Integer> set = new TreeSet<>();
        set.add(num);

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                set.add(i);
                set.add(num / i);
            }
        }
        return set;
    }

    private static void findNum() {
        for (int num : divisorNum) {
            if (isEquals(num)) {
                print(num);
            }
        }
    }

    private static boolean isEquals(int div) {
        double remainder = getRemainder(arr_M[0], div);
        for (int i = 1; i < n; i++) {
            if (remainder != getRemainder(arr_M[i], div)) return false;
        }
        return true;
    }

    private static double getRemainder(int mok, int div) {
        return mok % div;
    }

    private static void print(int div) {
        System.out.print(div + " ");
    }
}
