package baekJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_1697_BFS {
    private static int MAX = 100001;
    private static Queue<Integer> q = new LinkedList();
    private static int next_position[] = new int[3];
    private static int visit[] = new int[MAX];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int now_position = sc.nextInt();
        int end_position = sc.nextInt();

        q.add(now_position);
        visit[now_position] = 1;

        bfs(end_position);
        System.out.println(visit[end_position] - 1);
    }

    private static void bfs(int end) {
        while (!q.isEmpty()) {
            int x = q.poll();
            if (x == end) {
                break;
            }
            next_position[0] = x + 1;
            next_position[1] = x - 1;
            next_position[2] = x * 2;
            for (int i = 0; i < 3; i++) {
                if (next_position[i] <= MAX - 1 && next_position[i] >= 0 && visit[next_position[i]] == 0) {
                    q.add(next_position[i]);
                    visit[next_position[i]] = visit[x] + 1;
                }
            }
        }
    }
}
