package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj_2637_Topology {
    private static int n;
    private static int[] inDegree;
    private static int[][] cnt;
    private static List<Node>[] lists;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        inDegree = new int[n + 1];
        cnt = new int[n + 1][n + 1];
        lists = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            lists[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            inDegree[to]++;
            lists[from].add(new Node(to, cost));
        }

        goTopology();

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=n;i++){
            if(cnt[n][i] != 0){
                sb.append(i).append(" ").append(cnt[n][i]).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static void goTopology() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
                cnt[i][i] = 1;
            }
        }

        while (!q.isEmpty()) {
            int curIdx = q.poll();
            for (Node next : lists[curIdx]) {
                for (int i = 1; i <= n; i++) {
                    cnt[next.idx][i] += cnt[curIdx][i] * next.cost;
                }

                if (--inDegree[next.idx] == 0) {
                    q.offer(next.idx);
                }
            }
        }
    }

    private static class Node {
        private int idx;
        private int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
}
