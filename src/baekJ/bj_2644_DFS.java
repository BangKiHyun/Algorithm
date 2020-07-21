package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class bj_2644_DFS {
    private static List<Integer>[] lists;
    private static boolean[] visit;
    private static int ans = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        lists = new ArrayList[n + 1];
        visit = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            lists[i] = new ArrayList();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int goal = Integer.parseInt(st.nextToken());

        int count = Integer.parseInt(br.readLine());
        for (int curCount = 0; curCount < count; curCount++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            lists[from].add(to);
            lists[to].add(from);
        }

        visit[start] = true;
        goDFS(start, goal, 0);
        System.out.println(ans);
    }

    private static boolean goDFS(final int start, final int goal, int count) {
        if (start == goal) {
            ans = count;
            return true;
        }

        for (int to : lists[start]) {
            if (visit[to]) continue;

            visit[to] = true;
            if (goDFS(to, goal, count + 1)) {
                return true;
            }
        }
        return false;
    }
}
