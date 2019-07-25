package SW.D4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class sw_1210 {
    static int MAX = 100;
    static int data[][] = new int[MAX][MAX];
    static Queue q = new LinkedList();
    static boolean visit[][] = new boolean[MAX][MAX];
    static int Y[] = {-1, 1, 0, 0};
    static int X[] = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int test_case = 1; test_case <= 1; test_case++) {
            System.out.print("#" + test_case + " ");
            for (int i = 0; i < MAX; i++) {
                for (int j = 0; j < MAX; j++) {
                    data[i][j] = sc.nextInt();
                    if (data[i][j] == 2) {
                        q.add(new Node(i, j));
                        System.out.println(i + " " + j);
                        visit[i][j] = true;
                    }
                }
            }
            bfs(data);
        }
    }

    static void bfs(int map[][]) {
        Breake :
        while (!q.isEmpty()) {
            Node n = (Node) q.poll();
            for (int i = 0; i < 3; i++) {
                int ny = n.y + Y[i];
                int nx = n.x + X[i];
                if (nx < 0 || ny < 0 || nx >= MAX || ny >= MAX)
                    continue;
                if (visit[nx][ny])
                    continue;

                if (map[nx][ny] == 1) {
                    if (nx == 0) {
                        System.out.println(ny);
                        break Breake;
                    }
                    q.add(new Node(nx, ny));
                    visit[nx][ny] = true;
                }
            }
        }
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            super();
            this.y = y;
            this.x = x;
        }
    }
}
