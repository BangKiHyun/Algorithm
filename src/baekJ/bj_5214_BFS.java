package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_5214_BFS {
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        visit = new boolean[n + 1];
        ArrayList<Integer>[] links = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            links[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < k - 1; j++) {
                for (int p = j + 1; p < k; p++) {
                    links[Integer.parseInt(line[j])].add(Integer.parseInt(line[p]));
                    links[Integer.parseInt(line[p])].add(Integer.parseInt(line[j]));
                }
            }
        }

        int ans = findStationCnt(1, n, links);
        System.out.println(ans);
    }

    private static int findStationCnt(int start, int end, ArrayList<Integer>[] links) {
        Queue<Station> q = new LinkedList<>();
        q.add(new Station(start, 1));
        visit[start] = true;

        while (!q.isEmpty()) {
            Station station = q.poll();

            if (station.num == end) {
                return station.cnt;
            }

            for (int next : links[station.num]) {
                if (!visit[next]) {
                    q.add(new Station(next, station.cnt + 1));
                    visit[next] = true;
                }
            }
        }
        return -1;
    }

    private static class Station {
        private int num;
        private int cnt;

        public Station(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}
