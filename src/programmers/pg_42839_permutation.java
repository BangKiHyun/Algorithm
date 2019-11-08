package programmers;

import java.util.HashSet;
import java.util.Set;

public class pg_42839_permutation {
    private static int IntNumber[];
    private static boolean visit[];
    private static int length;
    private static Set<Integer> set = new HashSet<>();
    private static int primeCnt = 0;

    public static void main(String[] args) {
        String numbers = "011";
        solution(numbers);
        System.out.println(primeCnt);
    }

    private static int solution(String numbers) {
        init(numbers);
        for (int i = 0; i < length; i++) {
            searchPrime(i + 1, 0, "");
        }
        return primeCnt;
    }

    private static void init(String StrNumber) {
        length = StrNumber.length();
        IntNumber = new int[length];
        visit = new boolean[length];

        for (int i = 0; i < length; i++) {
            IntNumber[i] = Integer.parseInt(StrNumber.substring(i, i + 1));
        }
    }

    private static void searchPrime(int digit, int depth, String number) {
        if (digit == depth) {
            int intNum = ConvertStrToInt(number);
            if (!set.contains(intNum) && isPrime(intNum)) {
                set.add(intNum);
                primeCnt++;
                return;
            }
            return;
        }

        for (int i = 0; i < length; i++) {
            if (!visit[i]) {
                visit[i] = true;
                searchPrime(digit, depth + 1, number + IntNumber[i]);
                visit[i] = false;
            }
        }
    }

    private static int ConvertStrToInt(String Str) {
        return Integer.parseInt(Str);
    }

    private static boolean isPrime(int number) {
        if (number <= 1) return false;
        if (number == 2) return true;

        if (number % 2 == 0) return false;

        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
