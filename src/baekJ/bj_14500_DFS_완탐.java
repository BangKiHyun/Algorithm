package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//폴리오미노란 크기가 1×1인 정사각형을 여러 개 이어서 붙인 도형이며, 다음과 같은 조건을 만족해야 한다.
//
//정사각형은 서로 겹치면 안 된다.
//도형은 모두 연결되어 있어야 한다.
//정사각형의 변끼리 연결되어 있어야 한다. 즉, 꼭짓점과 꼭짓점만 맞닿아 있으면 안 된다.
//정사각형 4개를 이어 붙인 폴리오미노는 테트로미노라고 하며, 다음과 같은 5가지가 있다.
//
//아름이는 크기가 N×M인 종이 위에 테트로미노 하나를 놓으려고 한다.
//종이는 1×1 크기의 칸으로 나누어져 있으며, 각각의 칸에는 정수가 하나 쓰여 있다.
//
//테트로미노 하나를 적절히 놓아서 테트로미노가 놓인 칸에 쓰여 있는 수들의 합을 최대로 하는 프로그램을 작성하시오.
//
//테트로미노는 반드시 한 정사각형이 정확히 하나의 칸을 포함하도록 놓아야 하며, 회전이나 대칭을 시켜도 된다.
public class bj_14500_DFS_완탐 {
    private static int n, m;
    private static int[][] board;
    private static boolean[][] visit;
    private static int max = 0;

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        goTetromino();

        System.out.println(max);
    }

    private static void goTetromino() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visit[i][j] = true;
                goDFS(i, j, 1, board[i][j]);
                visit[i][j] = false;

                goException(i, j);
            }
        }
    }

    private static void goDFS(int cur_x, int cur_y, int depth, int sum) {
        if (depth == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = cur_x + dx[i];
            int ny = cur_y + dy[i];

            if (isRange(nx, ny) && !visit[nx][ny]) {
                visit[nx][ny] = true;
                goDFS(nx, ny, depth + 1, sum + board[nx][ny]);
                visit[nx][ny] = false;
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }

    private static void goException(int cur_x, int cur_y) {
        int wingCnt = 0;
        int sum = board[cur_x][cur_y];
        int minNumber = 1001;

        for (int i = 0; i < 4; i++) {
            int nx = cur_x + dx[i];
            int ny = cur_y + dy[i];

            if (isRange(nx, ny)) {
                wingCnt++;
                sum += board[nx][ny];
                minNumber = Math.min(minNumber, board[nx][ny]);
            }
        }

        if (wingCnt == 3) max = Math.max(max, sum);
        else if (wingCnt == 4) max = Math.max(max, sum - minNumber);
    }
}

/*
5가지 테트로미노에 대해 완탐을 한다
일단 모양별로 회전이나 대칭을해도 똑같은 모양을 유지하는데 애들이 있기떄문에 모양별로 구분해야한다
하지만 이렇게하면 모양별 case가 너무 많아진다
답은 DFS다 상하좌우 4번까지만 탐색하면 된다.
*/