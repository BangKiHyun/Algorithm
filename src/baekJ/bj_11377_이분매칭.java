package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_11377_이분매칭 {
    //강호네 회사에는 직원이 N명이 있고, 해야할 일이 M개가 있다. 직원은 1번부터 N번까지 번호가 매겨져 있고, 일은 1번부터 M번까지 번호가 매겨져 있다.
    //
    //각 직원은 한 개의 일만 할 수 있고, 각각의 일을 담당하는 사람은 1명이어야 한다. 단, N명 중에서 K명은 일을 최대 2개할 수 있다.
    //
    //각각의 직원이 할 수 있는 일의 목록이 주어졌을 때, M개의 일 중에서 최대 몇 개를 할 수 있는지 구하는 프로그램을 작성하시오.
    private static ArrayList<Integer>[] lists;
    private static boolean[] check;
    private static int[] work_num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //첫째 줄에 직원의 수 N과 일의 개수 M, 일을 2개할 수 있는 직원의 수 K가 주어진다. (1 ≤ N, M ≤ 1,000, 1 ≤ K ≤ N)
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        lists = new ArrayList[n + 1];
        check = new boolean[m + 1];
        work_num = new int[m + 1];
        for (int i = 1; i <= n; i++) {
            lists[i] = new ArrayList<>();
        }

        //둘째 줄부터 N개의 줄의 i번째 줄에는 i번 직원이 할 수 있는 일의 개수와 할 수 있는 일의 번호가 주어진다.
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            while (st.hasMoreElements()) {
                lists[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            Arrays.fill(check, false);
            if (goBipartiteMatching(i)) ans++;
        }

        int extra = 0;
        for (int i = 1; i <= n && extra < k; i++) {
            Arrays.fill(check, false);
            if (goBipartiteMatching(i)) extra++;
        }

        System.out.println(ans + extra);
    }

    private static boolean goBipartiteMatching(int start) {
        for (int i : lists[start]) {
            if (check[i]) continue;
            check[i] = true;

            if (work_num[i] == 0 || goBipartiteMatching(work_num[i])) {
                work_num[i] = start;
                return true;
            }
        }
        return false;
    }
}
