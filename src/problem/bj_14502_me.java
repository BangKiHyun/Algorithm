package problem;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_14502_me {
    static int N, M;
    static int map[][];
    static int copy[][];
    static Queue<Node> virus = new LinkedList<>();
    static int safeArea = -1;
    static int[] X = {-1, 1, 0, 0};
    static int[] Y = {0, 0, -1, 1};

    public static void main(String[] args) {
        init();
        solution(0, 0);
        System.out.println(safeArea);
    }

    private static void solution(int start, int depth) {
        if (depth == 3) {
            copyMap();
            for (Node n : virus) {
                spreadVirus(n.x, n.y);
            }
            safeArea = Math.max(safeArea, cntSafeArea());
            return;
        }

        for (int i = start; i < N * M; i++) {
            int x = i / M;
            int y = i % M;
            if (map[x][y] == 0) {
                map[x][y] = 1;
                solution(start + 1, depth + 1);
                map[x][y] = 0;
            }
        }
    }

    private static int cntSafeArea() {
        int cnt = 0;
        for (int i = 0; i < N * M; i++) {
            int x = i / M;
            int y = i % M;
            if (copy[x][y] == 0) cnt++;
        }
        return cnt;
    }

    private static void spreadVirus(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + X[i];
            int ny = y + Y[i];

            if (nx >= 0 && ny >= 0 && nx < N && ny < M && copy[nx][ny] == 0) {
                copy[nx][ny] = 2;
                spreadVirus(nx, ny);
            }
        }
    }


    private static void copyMap() {
        for (int i = 0; i < N * M; i++) {
            int x = i / M;
            int y = i % M;
            copy[x][y] = map[x][y];
        }
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        copy = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 2) {
                    virus.add(new Node(i, j));
                }
            }
        }
    }

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
