package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//N개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 M개의 버스가 있다.
//우리는 A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용을 최소화 시키려고 한다.
//A번째 도시에서 B번째 도시까지 가는데 드는 최소비용을 출력하여라. 도시의 번호는 1부터 N까지이다.
public class bj_1916_다익_Re {
    private static final int INF = Integer.MAX_VALUE;
    private static List<Bus>[] busList;
    private static boolean[] visit;
    private static int[] pay;
    private static Queue<Bus> prq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //첫째 줄에 도시의 개수 N(1 ≤ N ≤ 1,000)이 주어지고
        int n = Integer.parseInt(br.readLine());

        //둘째 줄에는 버스의 개수 M(1 ≤ M ≤ 100,000)이 주어진다.
        int m = Integer.parseInt(br.readLine());

        //그리고 셋째 줄부터 M+2줄까지 다음과 같은 버스의 정보가 주어진다.
        //먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다.
        //그리고 그 다음에는 도착지의 도시 번호가 주어지고 또 그 버스 비용이 주어진다.
        //버스 비용은 0보다 크거나 같고, 100,000보다 작은 정수이다.
        busList = new ArrayList[n + 1];
        pay = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            busList[i] = new ArrayList<>();
            pay[i] = INF;
        }
        visit = new boolean[n + 1];

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int curPath = Integer.parseInt(st.nextToken());
            int nextPath = Integer.parseInt(st.nextToken());
            int pay = Integer.parseInt(st.nextToken());
            busList[curPath].add(new Bus(nextPath, pay));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        dijkstra(start);

        System.out.println(pay[end]);
    }

    private static void dijkstra(final int start) {
        pay[start] = 0;
        prq.add(new Bus(start, 0));

        while (!prq.isEmpty()) {
            Bus cur = prq.poll();
            int curPath = cur.path;

            if (visit[curPath]) {
                continue;
            }
            visit[curPath] = true;

            for (Bus next : busList[curPath]) {
                if (pay[next.path] > pay[curPath] + next.pay) {
                    pay[next.path] = pay[curPath] + next.pay;
                    prq.add(new Bus(next.path, pay[next.path]));
                }
            }
        }
    }

    private static class Bus implements Comparable<Bus> {
        private int path;
        private int pay;

        public Bus(final int path, final int pay) {
            this.path = path;
            this.pay = pay;
        }

        @Override
        public int compareTo(final Bus bus) {
            return this.pay - bus.pay;
        }
    }
}
