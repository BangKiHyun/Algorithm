package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//사다리에 가로선을 추가해서, 사다리 게임의 결과를 조작하려고 한다. 이때, i번 세로선의 결과가 i번이 나와야 한다.
//그렇게 하기 위해서 추가해야 하는 가로선 개수의 최솟값을 구하는 프로그램을 작성하시오.
public class bj_15684_브루트포스 {
    private static int[][] board;

    private static final int EMPTY = 0;
    private static final int RIGHT = 1;
    private static final int LEFT = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //첫째 줄에 세로선의 개수 N, 가로선의 개수 M, 세로선마다 가로선을 놓을 수 있는 위치의 개수 H가 주어진다. (2 ≤ N ≤ 10, 1 ≤ H ≤ 30, 0 ≤ M ≤ (N-1)×H)
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        board = new int[h + 1][n + 1];
        //둘째 줄부터 M개의 줄에는 가로선의 정보가 한 줄에 하나씩 주어진다.
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            board[a][b] = RIGHT;
            board[a][b + 1] = LEFT;
        }

        if (operateSuccess(h, n)) {
            System.out.println(0);
            return;
        }

        for (int i = 1; i <= 3; i++) {
            if (connectBridge(h, n, i, 0)) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }

    private static boolean connectBridge(int max_x, int max_y, int cnt, int depth) {
        if (cnt == depth) {
            if (operateSuccess(max_x, max_y)) {
                return true;
            }
            return false;
        }
        for (int j = 1; j < max_y; j++) {
            for (int i = 1; i <= max_x; i++) {
                if (board[i][j] == EMPTY && board[i][j + 1] == EMPTY) {
                    board[i][j] = RIGHT;
                    board[i][j + 1] = LEFT;
                    if (connectBridge(max_x, max_y, cnt, depth + 1)) {
                        return true;
                    }
                    board[i][j] = board[i][j + 1] = EMPTY;
                }
            }
        }
        return false;
    }

    private static boolean operateSuccess(int max_x, int max_y) {
        for (int i = 1; i <= max_y; i++) {
            int num = getGoalNumber(1, i, max_x);
            if (num != i) {
                return false;
            }
        }
        return true;
    }

    private static int getGoalNumber(int x, int y, int max_x) {
        while (x <= max_x) {
            if (board[x][y] != EMPTY) {
                if (board[x][y] == LEFT) y--;
                else y++;
            }
            x++;
        }
        return y;
    }
}

//2차월 배열 board[][] 를 이용해서 true 이면 연결된 것
//비교를 할 때 현 위치에서 좌우를 확인하여 오른쪽이면 y+1 왼쪽이면 y-1, x + 1을 해줌
//쭉 내려오다가 x의 범위를 벗어나면 그 곳이 도착지점