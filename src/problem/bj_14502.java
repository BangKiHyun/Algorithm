package problem;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class bj_14502 {
    static int r, c;
    static int map[][];
    static int copy[][];
    static List<Node> vius = new LinkedList<>();
    static int[] X = {-1, 1, 0, 0};
    static int[] Y = {0, 0, -1, 1};
    static int max = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();
        map = new int[r][c];
        copy = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 2) vius.add(new Node(i, j));
            }
        }
        solution(0, 0);
        System.out.println(max);
    }

    static void solution(int start, int wallNum) {
        if (wallNum == 3) {
            copyMap();

            for (Node n : vius) {
                spreadVirus(n.x, n.y);
            }
            max = Math.max(max, getSafeArea());
            return;
        }

        for (int i = start; i < r * c; i++) {
            int x = i / c;
            int y = i % c;

            if (map[x][y] == 0) {
                map[x][y] = 1;
                solution(i + 1, wallNum + 1);
                map[x][y] = 0;
            }
        }
    }

    static int getSafeArea() {
        int safeArea = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (copy[i][j] == 0) {
                    safeArea++;
                }
            }
        }
        return safeArea;
    }

    static void copyMap() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                copy[i][j] = map[i][j];
            }
        }
    }

    static void spreadVirus(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + X[i];
            int ny = y + Y[i];

            if (nx < r && ny < c && nx >= 0 && ny >= 0) {
                if (copy[nx][ny] == 0) {
                    copy[nx][ny] = 2;
                    spreadVirus(nx, ny);
                }
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
