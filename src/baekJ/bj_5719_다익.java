package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//상근이는 오직 자기 자신만 사용 가능한 네비게이션을 만들고 있다. 이 네비게이션은 절대로 최단 경로를 찾아주지 않는다. 항상 거의 최단 경로를 찾아준다.
//
//거의 최단 경로란 최단 경로에 포함되지 않는 도로로만 이루어진 경로 중 가장 짧은 것을 말한다.
public class bj_5719_다익 {
    private static int[][] map;
    private static int[] distance;
    private static int goal;
    private static ArrayList<Integer>[] trace;

    private final static int INF = 99999999;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            //입력은 여러 개의 테스트 케이스로 이루어져 있다.
            //각 테스트 케이스의 첫째 줄에는 장소의 수 N (2 ≤ N ≤ 500)과 도로의 수 M (1 ≤ M ≤ 104)가 주어진다.
            //장소는 0부터 N-1번까지 번호가 매겨져 있다.
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) break;
            map = new int[n][n];
            distance = new int[n];

            //둘째 줄에는 시작점 S와 도착점 D가 주어진다. (S ≠ D; 0 ≤ S, D < N)
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            goal = Integer.parseInt(st.nextToken());
            trace = new ArrayList[n];

            for (int i = 0; i < n; i++) {
                distance[i] = INF;
                Arrays.fill(map[i], -1);
                trace[i] = new ArrayList<>();
            }

            //다음 M개 줄에는 도로의 정보 U, V, P가 주어진다. (U ≠ V ; 0 ≤ U, V < N; 1 ≤ P ≤ 103) 이 뜻은 U에서 V로 가는 도로의 길이가 P라는 뜻이다.
            //U에서 V로 가는 도로는 최대 한 개이다. 또, U에서 V로 가는 도로와 V에서 U로 가는 도로는 다른 도로이다.
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int dis = Integer.parseInt(st.nextToken());
                map[from][to] = dis;
            }

            searchShortestPath(start, n);
            deleteShortestPath(n);
            Arrays.fill(distance, INF);
            searchShortestPath(start, n);
            System.out.println(distance[goal] == INF ? -1 : distance[goal]);
        }
    }

    private static void searchShortestPath(int start, int n) {
        Queue<Path> q = new PriorityQueue<>();
        q.add(new Path(start, distance[start] = 0));
        while (!q.isEmpty()) {
            Path now = q.poll();
            for (int next = 0; next < n; next++) {
                if (map[now.pos][next] != -1 && distance[next] >= now.dis + map[now.pos][next]) {
                    distance[next] = now.dis + map[now.pos][next];
                    q.add(new Path(next, distance[next]));
                    trace[next].add(now.pos);
                }
            }
        }
    }

    private static void deleteShortestPath(int n) {
        Queue<Integer> q = new LinkedList<>();
        q.add(goal);
        while (!q.isEmpty()) {
            int next = q.poll();
            for (int now : trace[next]) {
                if (map[now][next] != -1 && distance[next] == distance[now] + map[now][next]) {
                    q.add(now);
                    map[now][next] = -1;
                }
            }
        }
    }

    private static class Path implements Comparable<Path> {
        private int pos;
        private int dis;

        public Path(int pos, int dis) {
            this.pos = pos;
            this.dis = dis;
        }

        @Override
        public int compareTo(Path o) {
            return this.dis - o.dis;
        }
    }
}
//다익스트라를 이용해서 최단 경로를 찾는다
//역추적해서 최단 경로를 다 지운다
//다신한번 다익스트라를 이용해 최단경로 찾는다