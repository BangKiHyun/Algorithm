package programmers.dfs;

import java.util.HashSet;
import java.util.Set;

public class pg_소수_찾기 {

    private static String[] strNumbers;
    private static int len;
    private static int primeCnt = 0;

    private static boolean[] visit;
    private static int[] isPrimeNumber;
    private static Set<Integer> primeSet = new HashSet<>();

    public static void main(String[] args) {
        String numbers = "17";
        System.out.println(solution(numbers));
    }

    public static int solution(String numbers) {
        strNumbers = numbers.split("");
        len = strNumbers.length;
        visit = new boolean[len];

        isPrimeNumber = new int[10000000];
        initPrimerNumber();

        for (int i = 0; i < len; i++) {
            searchPrime(0, i + 1, "");
        }

        return primeCnt;
    }

    private static void initPrimerNumber() {
        int maxNumber = 10000000;
        for (int i = 2; i < maxNumber; i++) {
            isPrimeNumber[i] = i;
        }
        for (int i = 2; i < maxNumber; i++) {
            if (isPrimeNumber[i] == 0) continue;
            for (int j = i + i; j < maxNumber; j = j + i) {
                isPrimeNumber[j] = 0;
            }
        }
    }

    private static void searchPrime(int cnt, int depth, String number) {
        if (cnt == depth) {
            int intNumber = Integer.parseInt(number);
            if (!primeSet.contains(intNumber) && isPrimeNumber[intNumber] != 0) {
                primeSet.add(intNumber);
                primeCnt++;
            }
            return;
        }

        for (int i = 0; i < len; i++) {
            if (!visit[i]) {
                visit[i] = true;
                searchPrime(cnt + 1, depth, number + strNumbers[i]);
                visit[i] = false;
            }
        }
    }
}
