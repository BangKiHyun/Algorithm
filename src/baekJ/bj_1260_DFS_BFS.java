package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj_1260_DFS_BFS {
    private static int n;
    private static ArrayList<Integer>[] lists;
    private static Queue<Integer> ans = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        lists = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            lists[from].add(to);
            lists[to].add(from);
        }

        for (int i = 1; i <= n; i++) {
            Collections.sort(lists[i]);
        }

        boolean[] visit = new boolean[n + 1];
        visit[start] = true;
        goDFS(start, visit);
        goBFS(start, n);

        int cnt = 0;
        while (!ans.isEmpty()) {
            cnt++;
            if (cnt == n) {
                System.out.print(ans.poll() + " \n");
            } else {
                System.out.print(ans.poll() + " ");
            }
        }
    }

    private static void goDFS(int start, boolean[] visit) {
        ans.offer(start);
        for (int next : lists[start]) {
            if (!visit[next]) {
                visit[next] = true;
                goDFS(next, visit);
            }
        }
    }

    private static void goBFS(int start, int n) {
        boolean[] visit = new boolean[n + 1];
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visit[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            ans.offer(cur);

            for (int next : lists[cur]) {
                if (!visit[next]) {
                    q.offer(next);
                    visit[next] = true;
                }
            }
        }
    }
}
