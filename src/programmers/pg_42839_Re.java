package programmers;

import java.util.HashSet;
import java.util.Set;

public class pg_42839_Re {
    private static int length;
    private static boolean[] visit;
    private static String strNumbers;
    private static int cnt = 0;
    private static boolean[] notPrime;

    private static Set<Integer> primeSet = new HashSet<>();

    public static void main(String[] args) {
        String numbers = "011";
        System.out.println(solution(numbers));
    }

    public static int solution(String numbers) {
        length = numbers.length();
        visit = new boolean[length];
        strNumbers = numbers;
        initPrime();

        for (int i = 1; i <= length; i++) {
            goPermutation(i, 0, "");
        }

        return cnt;
    }

    private static void initPrime() {
        int max = 9999999;
        notPrime = new boolean[max + 1];
        notPrime[0] = true;
        notPrime[1] = true;

        for (int i = 2; i <= max; i++) {
            if (notPrime[i]) continue;

            for (int j = i + i; j <= max; j += i) {
                notPrime[j] = true;
            }
        }
    }

    private static void goPermutation(int size, int depth, String number) {
        if (size == depth) {
            int intNumber = parseInt(number);
            if (isPrime(intNumber) && !primeSet.contains(intNumber)) {
                System.out.println(intNumber);
                cnt++;
                primeSet.add(intNumber);
            }
            return;
        }

        for (int i = 0; i < length; i++) {
            if (!visit[i]) {
                visit[i] = true;
                goPermutation(size, depth + 1, number + strNumbers.substring(i, i + 1));
                visit[i] = false;
            }
        }
    }

    private static int parseInt(String number) {
        return Integer.parseInt(number);
    }

    private static boolean isPrime(int number) {
        return !notPrime[number];
    }
}
