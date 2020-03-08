package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class bj_2211_크루스칼_Fail {
    private static int[] idx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()) + 1;
        int m = Integer.parseInt(st.nextToken());

        idx = new int[n];
        for (int i = 1; i < n; i++) {
            idx[i] = i;
        }

        ArrayList<Node> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            list.add(new Node(from, to, time));
        }

        Collections.sort(list);

        int cnt = 0;
        ArrayList<Node> ans = new ArrayList<>();
        for (Node i : list) {
            if (!findIdx(i.x, i.y)) {
                unionIdx(i.x, i.y);
                ans.add(i);
                cnt++;
            }
        }

        System.out.println(cnt);
        for (Node node : ans) {
            System.out.println(node.x + " " + node.y);
        }
    }

    private static boolean findIdx(int x, int y) {
        x = getIdx(x);
        y = getIdx(y);

        return x == y ? true : false;
    }

    private static void unionIdx(int x, int y) {
        x = getIdx(x);
        y = getIdx(y);

        if (x > y) idx[y] = x;
        else idx[x] = y;
    }

    private static int getIdx(int x) {
        if (idx[x] == x) return x;
        return idx[x] = getIdx(idx[x]);
    }

    private static class Node implements Comparable<Node> {
        int x;
        int y;
        int dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }
}

/*
4 5
1 2 1
1 3 2
1 4 3
2 3 1
3 4 1
*/