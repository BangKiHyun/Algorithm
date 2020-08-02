package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class bj_2188_이분매칭_Re {
    private static boolean[] visit;
    private static int[] cawSpace;
    private static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        visit = new boolean[m + 1];
        cawSpace = new int[m + 1];
        list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();

            while (st.hasMoreElements()) {
                list[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            Arrays.fill(visit, false);
            if (goBinarySearch(i)) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    private static boolean goBinarySearch(final int start) {
        for (int cur : list[start]) {
            if (visit[cur]) continue;
            visit[cur] = true;

            if (cawSpace[cur] == 0 || goBinarySearch(cawSpace[cur])) {
                cawSpace[cur] = start;
                return true;
            }
        }
        return false;
    }
}
