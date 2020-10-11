package programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class pg_49189_Re {
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static List<Integer>[] lists;
    private static boolean[] visit;

    public static void main(String[] args) {
        int n = 6;
        int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        System.out.println(solution(n, edge));
    }

    public static int solution(int n, int[][] edge) {
        lists = new ArrayList[n + 1];
        visit = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int i = 0; i < edge.length; i++) {
            int from = edge[i][0];
            int to = edge[i][1];

            lists[from].add(to);
            lists[to].add(from);

        }
        return getAnswer(n);
    }

    private static int getAnswer(int length) {
        int max = 0;
        int[] distanceArr = new int[length + 1];
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(1, 0));
        visit[1] = true;

        while (!q.isEmpty()) {
            Node curNode = q.poll();
            for (int nextIdx : lists[curNode.idx]) {
                if (!visit[nextIdx]) {
                    visit[nextIdx] = true;
                    q.offer(new Node(nextIdx, curNode.cnt + 1));
                    distanceArr[nextIdx] = curNode.cnt + 1;
                    max = Math.max(max, curNode.cnt + 1);
                }
            }
        }

        int cnt = 0;
        for (int i = 1; i <= length; i++) {
            if (distanceArr[i] == max) {
                cnt++;
            }
        }
        return cnt;
    }

    private static class Node {
        private int idx;
        private int cnt;

        public Node(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }
    }
}
