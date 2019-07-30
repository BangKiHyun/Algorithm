package SW.D4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class sw_1238 {
    static int MAX = 101;
    static boolean visit[];
    static LinkedList<Integer> a[];
    static Queue<Node> q;
    static int result[];
    static int idx = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int test_case = 1; test_case <= 1; test_case++) {
            int len = sc.nextInt();
            int start = sc.nextInt();
            visit = new boolean[MAX];
            a = new LinkedList[MAX];
            q = new LinkedList<>();
            result = new int[MAX];
            for (int i = 1; i < MAX; i++) {
                a[i] = new LinkedList<>();
            }
            for (int i = 0; i < len / 2; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                a[x].add(y);
            }
            q.add(new Node(start,1));
            visit[start] = true;
            bfs();
        }
    }

    static void bfs() {
        int max = 0;
        while (!q.isEmpty()) {
            Node n = q.poll();
            if(max < n.cnt){

            }
            for (int i : a[x]) {
                if (!visit[i]) {
                    q.offer(i);
                    visit[i] = true;
                }
            }
            idx++;
            System.out.println(idx);
        }
    }

    static class Node {
        int num, cnt;

        public Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}