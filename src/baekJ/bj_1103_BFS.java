package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

//형택이는 1부터 9까지의 숫자와, 구멍이 있는 직사각형 보드에서 재밌는 게임을 한다.
//
//일단 보드의 가장 왼쪽 위에 동전을 하나 올려놓는다. 그다음에 다음과 같이 동전을 움직인다.
//
//1 동전이 있는 곳에 쓰여 있는 숫자 X를 본다.
//2 위, 아래, 왼쪽, 오른쪽 방향 중에 한가지를 고른다.
//3 동전을 위에서 고른 방향으로 X만큼 움직인다. 이때, 중간에 있는 구멍은 무시한다.
//4 만약 동전이 구멍에 빠지거나, 보드의 바깥으로 나간다면 게임은 종료된다.
//
// 형택이는 이 재밌는 게임을 되도록이면 오래 하고 싶다.
//보드의 상태가 주어졌을 때, 형택이가 최대 몇 번 동전을 움직일 수 있는지 구하는 프로그램을 작성하시오.
public class bj_1103_BFS {
    private static int[][] board;
    private static final int HOLE = 10;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);

        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            line = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                if (line[j].equals("H")) board[i][j] = HOLE;
                else board[i][j] = Integer.parseInt(line[j]);
            }
        }

        int ans = moveCoin(n, m);
        System.out.println(ans);
    }

    private static int moveCoin(int max_x, int max_y) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        boolean[][] visit = new boolean[max_x][max_y];
        visit[0][0] = true;
        Queue<Coin> q = new LinkedList<>();
        q.add(new Coin(0, 0, 1, visit));

        int max = 0;
        while (!q.isEmpty()) {
            Coin now = q.poll();
            int distance = board[now.x][now.y];
            max = Math.max(max, now.cnt);

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] * distance + now.x;
                int ny = dy[i] * distance + now.y;

                if (isValue(nx, ny, max_x, max_y)) {
                    if (now.visit[nx][ny]) {
                        return -1;
                    }
                    now.visit[nx][ny] = true;
                    q.add(new Coin(nx, ny, now.cnt + 1, now.visit));
                    now.visit[nx][ny] = false;
                }
            }
        }

        return max;
    }

    private static boolean isValue(int x, int y, int max_x, int max_y) {
        return x >= 0 && y >= 0 && x < max_x && y < max_y && board[x][y] != HOLE;
    }

    private static class Coin {
        private int x;
        private int y;
        private int cnt;
        private boolean[][] visit;

        public Coin(int x, int y, int cnt, boolean[][] visit) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.visit = visit;
        }
    }
}

/*
3 4
3552
5555
2553

5 5
4HHH9
HHHHH
HHH12
HHHHH
3HH2H
*/