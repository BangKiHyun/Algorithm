package baekJ.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj_2638_치즈 {
    private static int n, m;
    private static int board[][];
    private static boolean[][] visit;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int cheeseCnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        visit = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) cheeseCnt++;
            }
        }
        checkAirArea();
        int answer = 0;
        while (cheeseCnt != 0) {
            List<Node> meltedCheeseList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 1 && isMeltable(i, j)) {
                        meltedCheeseList.add(new Node(i, j));
                    }
                }
            }
            cheeseCnt -= meltedCheeseList.size();
            for(Node cheeseNode : meltedCheeseList){
                bfsAirArea(cheeseNode.x, cheeseNode.y);
            }
            answer++;
        }
        System.out.println(answer);
    }

    private static boolean isMeltable(int x, int y) {
        int airCnt = 0;
        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (isRange(nx, ny) && board[nx][ny] == -1) airCnt++;
        }
        return airCnt >= 2;
    }

    private static void checkAirArea() {
        for (int i = 0; i < n; i++) {
            if (board[i][0] == 0) {
                bfsAirArea(i, 0);
            }
        }

        for (int i = 0; i < n; i++) {
            if (board[i][m - 1] == 0) {
                bfsAirArea(i, m - 1);
            }
        }

        for (int i = 0; i < m; i++) {
            if (board[0][i] == 0) {
                bfsAirArea(0, i);
            }
        }

        for (int i = 0; i < m; i++) {
            if (board[n - 1][i] == 0) {
                bfsAirArea(n - 1, i);
            }
        }
    }

    private static void bfsAirArea(int startX, int startY) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(startX, startY));
        board[startX][startY] = -1;
        while (!q.isEmpty()) {
            final Node curNode = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = curNode.x + dx[dir];
                int ny = curNode.y + dy[dir];

                if (isRange(nx, ny) && board[nx][ny] == 0) {
                    q.add(new Node(nx, ny));
                    board[nx][ny] = -1;
                }
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }

    private static class Node {
        private int x;
        private int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
