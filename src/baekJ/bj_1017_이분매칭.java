package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//지민이는 수의 리스트가 있을 때, 이를 짝지어 각 쌍의 합이 소수가 되게 하려고 한다. 예를 들어, {1, 4, 7, 10, 11, 12}가 있다고 하자. 지민이는 다음과 같이 그룹지을 수 있다.
//
//1 + 4 = 5, 7 + 10 = 17, 11 + 12 = 23
//또는
//1 + 10 = 11, 4 + 7 = 11, 11 + 12 = 23
//
//수의 리스트가 주어졌을 때, 지민이가 모든 수를 다 짝지었을 때, 첫 번째 수와 어떤 수를 짝지었는지 오름차순으로 출력하는 프로그램을 작성하시오.
public class bj_1017_이분매칭 {
    private static ArrayList<Integer>[] lists;
    private static int[] me;
    private static boolean[] check;
    private static int[] prime_list;

    public static void main(String[] args) throws IOException {
        //첫째 줄에 리스트의 크기 N이 주어진다. N은 50보다 작거나 같은 자연수이며, 짝수이다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] num = new int[n];

        //둘째 줄에 리스트에 들어있는 수가 주어진다. 리스트에 들어있는 수는 1,000보다 작거나 같은 자연수이며, 중복되지 않는다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        int idx = 0;
        int max = 0;
        while (st.hasMoreElements()) {
            num[idx] = Integer.parseInt(st.nextToken());
            max = Math.max(max, num[idx]);
            idx++;
        }

        int length = max * 2;
        lists = new ArrayList[length];
        for (int i = 1; i < length; i++) {
            lists[i] = new ArrayList<>();
        }

        initPrimeNumber(length);

        for (int i = 0; i < n - 1; i++) {
            int from = num[i];
            for (int j = i + 1; j < n; j++) {
                int to = num[j];
                if (prime_list[from + to] != 0) {
                    lists[from].add(to);
                    lists[to].add(from);
                }
            }
        }

        me = new int[length];
        check = new boolean[length];

        if (lists[num[0]].size() == 0) {
            System.out.println(-1);
            return;
        }

        Queue<Integer> ans = new PriorityQueue<>();
        for (int start : lists[num[0]]) {
            int cnt = 0;
            Arrays.fill(me, 0);
            me[start] = num[0];
            me[num[0]] = start;
            for (int i = 1; i < n; i++) {
                Arrays.fill(check, false);
                check[num[0]] = true;
                check[start] = true;
                if (check[num[i]]) continue;
                if (successMatching(num[i])) cnt++;
            }
            if (cnt == n - 2) {
                ans.add(start);
            }
        }
        if (ans.size() == 0) System.out.println(-1);
        else {
            while (!ans.isEmpty()) {
                System.out.print(ans.poll() + " ");
            }
        }
    }

    private static void initPrimeNumber(int len) {
        initPrimeList(len);
        for (int i = 2; i < len; i++) {
            if (prime_list[i] != 0) {
                for (int j = i + i; j < len; j += i) {
                    prime_list[j] = 0;
                }
            }
        }
    }

    private static void initPrimeList(int len) {
        prime_list = new int[len];

        for (int i = 2; i < len; i++) {
            prime_list[i] = i;
        }
    }

    private static boolean successMatching(int start) {
        for (int num : lists[start]) {
            if (check[num]) continue;
            check[num] = true;

            if (me[num] == 0 || successMatching(me[num])) {
                me[num] = start;
                return true;
            }
        }
        return false;
    }
}
