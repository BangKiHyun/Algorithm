package baekJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class bj_1719_다익 {
    public static int n, m, map[][], INF = 0x3f3f3f3f;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int i, from, to, w;
        String line[] = in.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);
        map = new int[n + 1][n + 1];

        for (i = 0; i < m; i++) {
            line = in.readLine().split(" ");
            from = Integer.parseInt(line[0]);
            to = Integer.parseInt(line[1]);
            w = Integer.parseInt(line[2]);
            map[from][to] = map[to][from] = w;
        }

        StringBuilder res = new StringBuilder();
        for (i = 1; i <= n; i++) res.append(dijkstra(i));
        System.out.println(res);
    }

    private static String dijkstra(int s) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(s);
        int i, cur, d[] = new int[n + 1], trace[] = new int[n + 1];
        Arrays.fill(d, INF);
        d[s] = 0;
        trace[s] = s;

        while (!pq.isEmpty()) {
            cur = pq.poll();
            for (i = 1; i <= n; i++) {
                if (map[cur][i] == 0) continue;
                else if (d[i] > map[cur][i] + d[cur]) {
                    d[i] = map[cur][i] + d[cur];
                    trace[i] = cur;
                    pq.offer(i);
                }
            }
        }
        return tracert(s, trace);
    }

    private static String tracert(int s, int t[]) {
        StringBuilder sb = new StringBuilder();
        int i, j, v = -1;
        for (i = 1; i <= n; i++) {
            if (i == s) {
                sb.append("- ");
                continue;
            }
            for (v = i + 1, j = t[i]; j != s; j = t[j]) v = j + 1;
            sb.append((v - 1) + " ");
        }
        return sb + "\n";
    }
}
