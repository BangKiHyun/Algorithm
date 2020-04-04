package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//월드 나라는 모든 도로가 일방통행인 도로이고, 싸이클이 없다.
//그런데 어떤 무수히 많은 사람들이 월드 나라의 지도를 그리기 위해서, 어떤 시작 도시로부터 도착 도시까지 출발을 하여 가능한 모든 경로를 탐색한다고 한다.
//
//이 지도를 그리는 사람들은 사이가 너무 좋아서 지도를 그리는 일을 다 마치고 도착 도시에서 모두 다 만나기로 하였다.
//그렇다고 하였을 때 이들이 만나는 시간은 출발 도시로부터 출발한 후 최소 몇 시간 후에 만날 수 있는가?
//즉, 마지막에 도착하는 사람까지 도착을 하는 시간을 의미한다.
//
//어떤 사람은 이 시간에 만나기 위하여 1분도 쉬지 않고 달려야 한다.
//이런 사람들이 지나는 도로의 수를 카운트 하여라.
public class bj_1948_Topology {
    private static ArrayList<Node>[] lists;
    private static ArrayList<Node>[] trace;
    private static int[] dist;
    private static int[] inDegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        lists = new ArrayList[n + 1];
        trace = new ArrayList[n + 1];
        dist = new int[n + 1];
        inDegree = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            lists[i] = new ArrayList<>();
            trace[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            lists[from].add(new Node(to, dist));
            trace[to].add(new Node(from, dist));
            inDegree[to]++;
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int goal = Integer.parseInt(st.nextToken());

        goTopology(n, start, goal);
    }

    private static void goTopology(int n, int start, int goal) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (Node next : lists[cur]) {
                dist[next.x] = Math.max(dist[next.x], dist[cur] + next.dist);
                if (--inDegree[next.x] == 0) {
                    q.offer(next.x);
                }
            }
        }

        int count = 0;
        boolean[] visit = new boolean[n + 1];
        q.offer(goal);
        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Node next : trace[cur]) {
                if (dist[cur] - next.dist == dist[next.x]) {
                    count++;
                    if (!visit[next.x]) {
                        q.offer(next.x);
                        visit[next.x] = true;
                    }
                }
            }
        }

        System.out.println(dist[goal] + "\n" + count);
    }

    private static class Node {
        private int x;
        private int dist;

        public Node(int x, int dist) {
            this.x = x;
            this.dist = dist;
        }
    }
}
