package programmers;

import java.util.HashSet;

public class pg_42839_완탐 {
    private static String StrNumbers;
    private static int[] IntNumbers;
    private static int lengthOfNumbers;
    private static int PrimeCount = 0;
    private static HashSet<Integer> hashSet = new HashSet<>();
    private static boolean visit[];

    public static void main(String[] args) {
        init();
        convertStrToInt();
        for (int i = 1; i <= lengthOfNumbers; i++) {
            permutate(0, i, "");
        }
        System.out.println(PrimeCount);
    }

    private static void init() {
        StrNumbers = "011";
        //"17";
        lengthOfNumbers = StrNumbers.length();
        IntNumbers = new int[lengthOfNumbers];
        visit = new boolean[lengthOfNumbers];
    }

    private static void convertStrToInt() {
        for (int i = 0; i < lengthOfNumbers; i++) {
            String tmpNumber = StrNumbers.substring(i, i + 1);
            IntNumbers[i] = Integer.parseInt(tmpNumber);
        }
    }

    private static void permutate(int cnt, int length, String StrNumber) {
        int IntNumber = 0;

        if (cnt != 0) {
            IntNumber = Integer.parseInt(StrNumber);
        }

        if (isCountEqualsNumberLength(cnt, length) && !hashSet.contains(IntNumber)) {
            hashSet.add(IntNumber);
            if (isPrimeNumber(IntNumber)) {
                PrimeCount++;
            }
            return;
        }

        for (int i = 0; i < lengthOfNumbers; i++) {
            if (!visit[i]) {
                visit[i] = true;
                permutate(cnt + 1, length, StrNumber + IntNumbers[i]);
                visit[i] = false;
            }
        }
    }

    private static boolean isCountEqualsNumberLength(int cnt, int length) {
        if (cnt == length) return true;
        else return false;
    }

    private static boolean isPrimeNumber(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % 2 == 0) {
                return false;
            }
        }
        return true;
    }
}
