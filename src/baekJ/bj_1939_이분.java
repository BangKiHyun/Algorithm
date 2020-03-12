package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj_1939_이분 {
    private static ArrayList<Node>[] lists;
    private static int start, end;
    private static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        check = new boolean[n + 1];
        lists = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            lists[i] = new ArrayList<>();
        }

        int max = -1;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            lists[from].add(new Node(to, weight));
            lists[to].add(new Node(from, weight));
            max = Math.max(max, weight);
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        int ans = getMaxWeight(max);

        System.out.println(ans);
    }

    private static int getMaxWeight(int max) {
        int min = 0;
        int mid;

        while (min <= max) {
            mid = (max + min) / 2;

            if (acceptable(mid)) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }

        return max;
    }

    private static boolean acceptable(int weight) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        Arrays.fill(check, false);
        check[start] = true;

        while (!q.isEmpty()) {
            int current = q.poll();

            if (current == end) return true;

            for (Node next : lists[current]) {
                if (!check[next.pos] && weight <= next.weight) {
                    check[next.pos] = true;
                    q.add(next.pos);
                }
            }
        }

        return false;
    }

    private static class Node {
        private int pos;
        private int weight;

        public Node(int pos, int weight) {
            this.pos = pos;
            this.weight = weight;
        }
    }
}
