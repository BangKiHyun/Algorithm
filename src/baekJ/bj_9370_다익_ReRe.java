package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class bj_9370_다익_ReRe {
    private static final int INF = Integer.MAX_VALUE;

    private static boolean[] visit;
    private static List<Path>[] pathList;
    private static int[] distance;
    private static List<Integer> candidateList;
    private static Queue<Path> prq;
    private static List<Integer>[] trace;

    private static int g, h;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //첫 번째 줄에는 테스트 케이스의 T(1 ≤ T ≤ 100)가 주어진다.
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int test_case = 1; test_case <= T; test_case++) {
            //첫 번째 줄에 3개의 정수 n, m, t (2 ≤ n ≤ 2 000, 1 ≤ m ≤ 50 000 and 1 ≤ t ≤ 100)가 주어진다.
            //각각 교차로, 도로, 목적지 후보의 개수이다.
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            visit = new boolean[n + 1];
            pathList = new ArrayList[n + 1];
            distance = new int[n + 1];
            trace = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                pathList[i] = new ArrayList<>();
                distance[i] = INF;
                trace[i] = new ArrayList<>();
            }

            //두 번째 줄에 3개의 정수 s, g, h (1 ≤ s, g, h ≤ n)가 주어진다.
            //s는 예술가들의 출발지이고, g, h는 문제 설명에 나와 있다. (g ≠ h)
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            //그 다음 m개의 각 줄마다 3개의 정수 a, b, d (1 ≤ a < b ≤ n and 1 ≤ d ≤ 1 000)가 주어진다.
            //a와 b 사이에 길이 d의 양방향 도로가 있다는 뜻이다.
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                pathList[a].add(new Path(b, d));
                pathList[b].add(new Path(a, d));
            }

            //그 다음 t개의 각 줄마다 정수 x가 주어지는데, t개의 목적지 후보들을 의미한다.
            //이 t개의 지점들은 서로 다른 위치이며 모두 s와 같지 않다.
            candidateList = new ArrayList<>();
            for (int i = 0; i < t; i++) {
                candidateList.add(Integer.parseInt(br.readLine()));
            }

            dijkstra(s);

            List<Integer> answer = candidateList.stream()
                    .filter(candidate -> isValid(candidate))
                    .sorted()
                    .collect(Collectors.toList());

            for (int ans : answer) {
                System.out.print(ans + " ");
            }
            System.out.println();
        }
    }

    private static void dijkstra(final int s) {
        prq = new PriorityQueue<>();
        distance[s] = 0;
        prq.add(new Path(s, 0));

        while (!prq.isEmpty()) {
            Path curPath = prq.poll();

            int curPos = curPath.pos;
            if (visit[curPos]) {
                continue;
            }
            visit[curPos] = true;

            for (Path nextPath : pathList[curPos]) {
                if (distance[nextPath.pos] > distance[curPos] + nextPath.distance) {
                    distance[nextPath.pos] = distance[curPos] + nextPath.distance;
                    prq.add(new Path(nextPath.pos, distance[nextPath.pos]));
                    trace[nextPath.pos].add(curPos);
                }
            }
        }
    }

    private static boolean isValid(int candidate) {
        Queue<Integer> q = new LinkedList<>();
        q.add(candidate);

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : trace[cur]) {
                if ((cur == g && next == h) || (cur == h && next == g)) {
                    return true;
                }
                q.add(next);
            }
        }

        return false;
    }

    private static class Path implements Comparable<Path> {
        private int pos;
        private int distance;

        public Path(final int pos, final int distance) {
            this.pos = pos;
            this.distance = distance;
        }

        @Override
        public int compareTo(final Path o) {
            return this.distance - o.distance;
        }
    }
}
