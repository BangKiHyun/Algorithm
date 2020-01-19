package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_1753_다익_re {
    private static final int INF = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int start = Integer.parseInt(br.readLine());

        int node[][] = new int[v + 1][v + 1];
        for (int i = 1; i <= v; i++) {
            Arrays.fill(node[i], INF);
            node[i][i] = 0;
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            node[x][y] = weight;
        }

        boolean[] visit = new boolean[v + 1];
        int[] distance = new int[v + 1];

        dijkstra(start, node, visit, distance, v);

        for (int i = 1; i <= v; i++) {
            System.out.println(distance[i] == INF ? "INF" : distance[i]);
        }
    }

    static int getSmallIndex(boolean[] visit, int[] distance, int v) {
        int min = INF;
        int idx = 0;
        for (int i = 1; i <= v; i++) {
            if (distance[i] < min && !visit[i]) {
                min = distance[i];
                idx = i;
            }
        }
        return idx;
    }

    static void dijkstra(int start, int[][] node, boolean[] visit, int[] distance, int v) {
        for (int i = 1; i <= v; i++) {
            distance[i] = node[start][i];
        }
        visit[start] = true;
        for (int i = 1; i <= v; i++) {
            int current = getSmallIndex(visit, distance, v);
            if (current == 0) return;
            visit[current] = true;

            for (int j = 1; j <= v; j++) {
                if (!visit[j]) {
                    if (distance[current] + node[current][j] < distance[j]) {
                        distance[j] = distance[current] + node[current][j];
                    }
                }
            }
        }
    }
}
