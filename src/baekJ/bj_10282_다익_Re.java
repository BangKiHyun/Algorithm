package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//최흉최악의 해커 yum3이 네트워크 시설의 한 컴퓨터를 해킹했다! 이제 서로에 의존하는 컴퓨터들은 점차 하나둘 전염되기 시작한다.
//어떤 컴퓨터 a가 다른 컴퓨터 b에 의존한다면, b가 감염되면 그로부터 일정 시간 뒤 a도 감염되고 만다.
//이때 b가 a를 의존하지 않는다면, a가 감염되더라도 b는 안전하다.
//
//최흉최악의 해커 yum3이 해킹한 컴퓨터 번호와 각 의존성이 주어질 때,
//해킹당한 컴퓨터까지 포함하여 총 몇 대의 컴퓨터가 감염되며 그에 걸리는 시간이 얼마인지 구하는 프로그램을 작성하시오.
public class bj_10282_다익_Re {
    private static final int INF = 987654321;

    private static ArrayList<Node>[] lists;
    private static int[] dist;
    private static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            lists = new ArrayList[n + 1];
            dist = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                lists[i] = new ArrayList();
                dist[i] = INF;
            }
            cnt = 1;

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int to = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());
                int dist = Integer.parseInt(st.nextToken());
                lists[from].add(new Node(to, dist));
            }

            findShortestPath(start);

            int[] result = Arrays.stream(dist).filter(k -> k != INF).toArray();
            Arrays.sort(result);

            System.out.println(cnt + " " + result[result.length - 1]);
        }
    }

    private static void findShortestPath(int start) {
        Queue<Node> q = new PriorityQueue<>();
        q.offer(new Node(start, dist[start] = 0));

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.dist > dist[cur.idx]) continue;

            for (Node next : lists[cur.idx]) {
                if (dist[next.idx] > dist[cur.idx] + next.dist) {
                    if (dist[next.idx] == INF) cnt++;

                    dist[next.idx] = dist[cur.idx] + next.dist;
                    q.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {
        private int idx;
        private int dist;

        public Node(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }
}
