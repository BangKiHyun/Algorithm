package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//강호네 회사에는 직원이 N명이 있고, 해야할 일이 M개가 있다.
//직원은 1번부터 N번까지 번호가 매겨져 있고, 일은 1번부터 M번까지 번호가 매겨져 있다.
//
//각 직원은 한 개의 일만 할 수 있고, 각각의 일을 담당하는 사람은 1명이어야 한다.
//여기서 지난달에 벌점을 X점 받은 사람은 일을 최대 X+1개까지 할 수 있다.
//각 직원은 자신이 지난달에 받은 벌점을 알지 못하고, 직원이 받은 벌점의 합 K만을 알고 있다.
//강호는 이런 사실을 이용해서 벌점을 적절히 나눠서 최대한 일을 많이 할 수 있게 하려고 한다.
public class bj_11378_이분매칭 {
    private static ArrayList<Integer>[] work_list;
    private static int[] employee;
    private static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //첫째 줄에 직원의 수 N과 일의 개수 M, 지난달에 받은 벌점의 합 K가 주어진다. (1 ≤ N, M ≤ 1,000, 1 ≤ K ≤ N)
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        employee = new int[m + 1];
        check = new boolean[m + 1];
        work_list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            work_list[i] = new ArrayList<>();
        }

        //둘째 줄부터 N개의 줄의 i번째 줄에는 i번 직원이 할 수 있는 일의 개수와 할 수 있는 일의 번호가 주어진다.
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int work_cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < work_cnt; j++) {
                work_list[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            Arrays.fill(check, false);
            if (canWork(i)) ans++;
        }

        int extra = 0;
        boolean flag;
        while (extra < k) {
            flag = false;
            for (int i = 1; i <= n && extra < k; i++) {
                Arrays.fill(check, false);
                if (canWork(i)) {
                    extra++;
                    flag = true;
                }
            }
            if (!flag) break;
        }

        System.out.println(ans + extra);
    }

    private static boolean canWork(int candidate) {
        for (int work : work_list[candidate]) {
            if (check[work]) continue;
            check[work] = true;

            if (employee[work] == 0 || canWork(employee[work])) {
                employee[work] = candidate;
                return true;
            }
        }
        return false;
    }
}
