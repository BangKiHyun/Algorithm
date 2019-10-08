package baekJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_5014_BFS {
    static int max, goal;
    static boolean visit[];
    static int button[] = new int[2];
    static Queue<Graph> q = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        max = sc.nextInt();
        int now = sc.nextInt();
        goal = sc.nextInt();
        button[0] = sc.nextInt();
        button[1] = sc.nextInt() * -1;
        visit = new boolean[max + 1];

        q.add(new Graph(now, 0));
        visit[now] = true;
        int ans = bfs();

        System.out.println(ans == 0 ? "use the stairs" : String.valueOf(ans));
    }

    private static int bfs() {
        int ans = 0;

        while (!q.isEmpty()) {
            Graph now = q.poll();

            if (now.position == goal) {
                return now.cnt;
            }

            for (int i = 0; i < 2; i++) {
                int next_postion = now.position + button[i];
                if (next_postion <= max && next_postion > 0 && !visit[next_postion]) {
                    q.add(new Graph(next_postion, now.cnt + 1));
                    visit[next_postion] = true;
                }
            }
        }
        return ans;
    }

    private static class Graph {
        int position, cnt;

        Graph(int position, int cnt) {
            this.position = position;
            this.cnt = cnt;
        }
    }
}
