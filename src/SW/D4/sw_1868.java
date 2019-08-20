package SW.D4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class sw_1868 {
    static String map[][];
    static int N, cnt;
    static boolean visit[][];
    static Queue<Node> q = new LinkedList<>();
    static int[] X = {-1, 1, 0, 0, -1, 1, -1, 1};
    static int[] Y = {0, 0, -1, 1, -1, -1, 1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            visit = new boolean[N][N];
            map = new String[N][N];
            cnt = 0;
            for (int i = 0; i < N; i++) {
                String s = sc.next();
                for (int j = 0; j < N; j++) {
                    map[i][j] = s.substring(j, j + 1);
                }
            }
            find();
            for(int i=0;i<N;i++){
                for(int j=0;j<N; j++){
                    if(!visit[i][j] && map[i][j].equals(".")) cnt++;
                }
            }
            System.out.println("#" + test_case + " " + cnt);
        }
    }

    static void find() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int nx, ny;
                boolean check = true;
                int count = 0;
                for (int k = 0; k < 8; k++) {
                    nx = i + X[k];
                    ny = j + Y[k];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    if(visit[nx][ny]) continue;
                    count++;
                    if (map[nx][ny].equals("*")) {
                        check = false;
                    }
                }
                if (check && count!=0) {
                    for (int k = 0; k < 8; k++) {
                        nx = i + X[k];
                        ny = j + Y[k];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                        visit[nx][ny] = true;
                    }
                    q.add(new Node(i, j));
                    visit[i][j] = true;
                    bfs();
                    cnt++;
                }
            }
        }
    }

    static void check(int x, int y) {
        boolean c = true;
        int count = 0;
        int nx, ny;
        for (int i = 0; i < 8; i++) {
            nx = x + X[i];
            ny = y + Y[i];
            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if (visit[nx][ny]) continue;
            count++;
            if (map[nx][ny].equals("*")) {
                c = false;
            }
        }
        if (c && count!=0) {
            for (int i = 0; i < 8; i++) {
                nx = x + X[i];
                ny = y + Y[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                visit[nx][ny] = true;
            }
            q.add(new Node(x, y));
        }
    }

    static void bfs() {
        while (!q.isEmpty()) {
            Node n = q.poll();
            for (int i = 0; i < 8; i++) {
                int nx = n.x + X[i];
                int ny = n.y + Y[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                check(nx, ny);
            }
        }
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
