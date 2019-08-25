package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class pg_1829_kakao {
    static Queue<Node> q = new LinkedList<>();
    static int[] X = {-1, 1, 0, 0};
    static int[] Y = {0, 0, -1, 1};
    static boolean visit[][];
    static int numberOfArea = 0;
    static int maxSizeOfOneArea = 0;
    static int maxNum;

    static public int[] solution(int m, int n, int[][] picture) {
        visit = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visit[i][j] || picture[i][j] == 0) continue;
                q.add(new Node(i, j));
                bfs(m, n, i, j, picture);
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    static void bfs(int m, int n, int x, int y, int map[][]) {
        maxNum = 1;
        visit[x][y] = true;
        while (!q.isEmpty()) {
            Node N = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = N.x + X[i];
                int ny = N.y + Y[i];
                if (nx >= 0 && ny >= 0 && nx < m && ny < n && !visit[nx][ny] && map[nx][ny] != 0) {
                    if (map[nx][ny] == map[x][y]) {
                        q.add(new Node(nx, ny));
                        visit[nx][ny] = true;
                        maxNum++;
                    }
                }
            }
        }
        numberOfArea++;
        if (maxNum > maxSizeOfOneArea) {
            maxSizeOfOneArea = maxNum;
        }
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        int[][] p = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};

        int[] ans = solution(6, 4, p);
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
    }
}
