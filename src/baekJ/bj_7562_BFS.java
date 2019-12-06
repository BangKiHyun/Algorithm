package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_7562_BFS {
    private static int n;
    private static int endRow, endCol;
    private static final int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
    private static final int[] dy = {-2, -1, 1, 2, -2, -1, 1, 2};
    private static boolean[][] visit;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //입력의 첫째 줄에는 테스트 케이스의 개수가 주어진다.

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int i = 1; i <= T; i++) {
            //각 테스트 케이스는 세 줄로 이루어져 있다. 첫째 줄에는 체스판의 한 변의 길이 l(4 ≤ l ≤ 300)이 주어진다. 체스판의 크기는 l × l이다. 체스판의 각 칸은 두 수의 쌍 {0, ..., l-1} × {0, ..., l-1}로 나타낼 수 있다. 둘째 줄과 셋째 줄에는 나이트가 현재 있는 칸, 나이트가 이동하려고 하는 칸이 주어진다.
            n = Integer.parseInt(br.readLine());
            visit = new boolean[n][n];

            st = new StringTokenizer(br.readLine());
            int nowRow = Integer.parseInt(st.nextToken());
            int nowCol = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            endRow = Integer.parseInt(st.nextToken());
            endCol = Integer.parseInt(st.nextToken());

            int num = goBFS(nowRow, nowCol);
            System.out.println(num);
        }
    }

    private static int goBFS(int r, int c) {
        Queue<Node> queue = new LinkedList<>();
        visit[r][c] = true;
        queue.add(new Node(r, c, 0));

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            for (int i = 0; i < 8; i++) {
                if (now.x == endRow && now.y == endCol) {
                    return now.cnt;
                }

                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isRange(nx, ny)) {
                    visit[nx][ny] = true;
                    queue.add(new Node(nx, ny, now.cnt + 1));
                }
            }
        }
        return -1;
    }

    private static boolean isRange(int x, int y) {
        return (x >= 0 && y >= 0 && x < n && y < n && !visit[x][y]);
    }

    private static class Node {
        private int x;
        private int y;
        private int cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
