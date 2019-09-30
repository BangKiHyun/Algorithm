package baekJ;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_13913_BFS {
    private static int MAX = 100001;
    private static int visit[] = new int[MAX];
    static int result[] = new int[MAX];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        Arrays.fill(visit, -1);

        int time = find_brother(n, k);
        for (int i = time - 1, current = k; i >= 0; i--) {
            result[i] = visit[current];
            current = visit[current];
        }

        System.out.println(time);
        for(int i=0;i<time;i++){
            System.out.print(result[i] + " ");
        }
        System.out.print(k);
    }

    private static int find_brother(int me_position, int brother_position) {
        Queue<Position> q = new LinkedList();
        q.add(new Position(me_position, 0));
        visit[me_position] = 0;
        int res = -1;

        while (!q.isEmpty()) {
            Position p = q.poll();
            if (p.now == brother_position) {
                res = p.cnt;
                return res;
            }

            for (int i = 0; i < 3; i++) {
                int next_position = cal(p.now, i);
                if (next_position >= 0 && next_position <= MAX && visit[next_position] == -1) {
                    q.add(new Position(next_position, p.cnt + 1));
                    visit[next_position] = p.now;
                }
            }
        }
        return res;
    }

    private static int cal(int num, int op) {
        switch (op) {
            case 0:
                return num + 1;
            case 1:
                return num - 1;
            case 2:
                return num * 2;
            default:
                break;
        }
        return 0;
    }
}

class Position {
    int now, cnt;

    Position(int now, int cnt) {
        this.now = now;
        this.cnt = cnt;
    }
}
