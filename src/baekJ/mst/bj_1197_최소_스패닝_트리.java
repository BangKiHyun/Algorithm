package baekJ.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj_1197_최소_스패닝_트리 {
    private static int[] parents;
    private static Queue<Node> prq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        parents = new int[v + 1];
        for (int idx = 1; idx <= v; idx++) {
            parents[idx] = idx;
        }
        for (int count = 0; count < e; count++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            prq.add(new Node(from, to, distance));
        }

        int answer = 0;
        while (!prq.isEmpty()) {
            final Node curNode = prq.poll();
            int from = getParent(curNode.from);
            int to = getParent(curNode.to);
            if (from == to) continue;
            union(from, to);
            answer += curNode.distance;
        }
        System.out.println(answer);
    }

    private static void union(int a, int b) {
        a = getParent(a);
        b = getParent(b);

        if (a < b) parents[a] = b;
        else parents[b] = a;
    }

    private static int getParent(int x) {
        if (parents[x] == x) return x;
        return parents[x] = getParent(parents[x]);
    }

    private static class Node implements Comparable<Node> {
        private int from;
        private int to;
        private int distance;

        public Node(int from, int to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node target) {
            return this.distance - target.distance;
        }
    }
}
