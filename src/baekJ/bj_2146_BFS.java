package baekJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_2146_BFS {
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<Node> q = new LinkedList<>();
    static boolean visit[][];
    static int graph[][];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        map = new int[n][n];
        visit = new boolean[n][n];
        graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int idx = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && !visit[i][j]) {
                    q.add(new Node(i, j));
                    visit[i][j] = true;
                    graph[i][j] = idx;
                    bfs(n);
                    idx++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }

        int min = Integer.MAX_VALUE;
        int path = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) {
                    visit = new boolean[n][n];
                    q.add(new Node(i, j));
                    visit[i][j] = true;
                    path = findPath(n);
                }
                min = Math.min(min, path);
                q.clear();
            }
        }
        System.out.println(min);
    }

    private static void bfs(int n) {
        while (!q.isEmpty()) {
            Node next = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = next.x + dx[i];
                int ny = next.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visit[nx][ny] && map[nx][ny] == 1) {
                    q.add(new Node(nx, ny));
                    visit[nx][ny] = true;
                    graph[nx][ny] = graph[next.x][next.y];
                }
            }
        }
    }

    private static int findPath(int n) {
        int res = 0;

        while (!q.isEmpty()) {
            Node next = q.poll();
            System.out.println(next.x + " " + next.y + " " + res);
            for (int i = 0; i < 4; i++) {
                int nx = next.x + dx[i];
                int ny = next.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visit[nx][ny] && map[nx][ny] == 0) {
                    if (graph[nx][ny] != 0 && graph[nx][ny] != graph[next.x][next.y]) {
                        return res;
                    }
                    q.add(new Node(nx, ny));
                    visit[nx][ny] = true;
                }
            }
            res++;
        }
        return res;
    }


    private static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
