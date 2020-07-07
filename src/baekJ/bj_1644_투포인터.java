package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//하나 이상의 연속된 소수의 합으로 나타낼 수 있는 자연수들이 있다. 몇 가지 자연수의 예를 들어 보면 다음과 같다.
//
//3 : 3 (한 가지)
//41 : 2+3+5+7+11+13 = 11+13+17 = 41 (세 가지)
//53 : 5+7+11+13+17 = 53 (두 가지)
//하지만 연속된 소수의 합으로 나타낼 수 없는 자연수들도 있는데, 20이 그 예이다. 7+13을 계산하면 20이 되기는 하나 7과 13이 연속이 아니기에 적합한 표현이 아니다.
//또한 한 소수는 반드시 한 번만 덧셈에 사용될 수 있기 때문에, 3+5+5+7과 같은 표현도 적합하지 않다.
//
//자연수가 주어졌을 때, 이 자연수를 연속된 소수의 합으로 나타낼 수 있는 경우의 수를 구하는 프로그램을 작성하시오.
public class bj_1644_투포인터 {
    private static int MAX = 4_000_000;

    private static List<Integer> primeNumbers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(getAnswer(n));
    }

    private static int getAnswer(int n) {
        createPrimeNumbers();
        int start = 0, end = 0, sum = 0, count = 0;

        int size = primeNumbers.size();
        while (end != size) {
            if (sum >= n) {
                sum -= primeNumbers.get(start++);
            } else {
                sum += primeNumbers.get(end++);
            }

            if (sum == n) {
                count++;
            }
        }

        while (start != size) {
            sum -= primeNumbers.get(start++);

            if (sum == n) {
                count++;
            }
        }

        return count;
    }

    private static void createPrimeNumbers() {
        boolean[] visit = new boolean[MAX + 1];

        for (int i = 2; i <= MAX; i++) {
            if (visit[i]) {
                continue;
            }
            primeNumbers.add(i);
            for (int j = i + i; j <= MAX; j += i) {
                visit[j] = true;
            }
        }
    }
}
