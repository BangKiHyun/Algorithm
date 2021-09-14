package baekJ.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_16926_배열_돌리기1 {
    private static int n, m;
    private static int[][] board;
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int rotationCount = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int minCount = Math.min(n, m) / 2;
        for (int count = 0; count < rotationCount; count++) {
            for (int startIdx = 0; startIdx < minCount; startIdx++) {
                rotate(startIdx);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 반시계 방향으로 돌면서 값을 넣어줘야한다.
    // 그러므로 시계 방향으로 탐색을 하며 오른쪽에 있는걸 왼쪽으로 넣어주면 된다.
    private static void rotate(int startIdx) {
        int x = startIdx;
        int y = startIdx;
        int value = board[x][y];
        int dir = 0;
        while (dir < 4) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (nx >= startIdx && ny >= startIdx && nx < n - startIdx && ny < m - startIdx) {
                board[x][y] = board[nx][ny];
                x = nx;
                y = ny;
            } else dir++;
        }
        board[startIdx + 1][startIdx] = value;
    }
}
