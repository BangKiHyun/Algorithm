package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//N×M (5≤N, M≤100)의 모눈종이 위에 아주 얇은 치즈가 <그림 1>과 같이 표시되어 있다.
//단, N 은 세로 격자의 수이고, M 은 가로 격자의 수이다.
//이 치즈는 냉동 보관을 해야만 하는데 실내온도에 내어놓으면 공기와 접촉하여 천천히 녹는다.
//그런데 이러한 모눈종이 모양의 치즈에서 각 치즈 격자(작 은 정사각형 모양)의 4변 중에서 적어도
//2변 이상이 실내온도의 공기와 접촉한 것은 정확히 한시간만에 녹아 없어져 버린다.
//모눈종이의 맨 가장자리에는 치즈가 놓이지 않는 것으로 가정한다.
//입력으로 주어진 치즈가 모두 녹아 없어지는데 걸리는 정확한 시간을 구하는 프로그램을 작성하시오.
public class bj_2638_BFS {
    private static final int AIR = 0;
    private static final int CHEESE = 1;
    private static final int outSideAir = 2;
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    private static int n, m;
    private static ArrayList<Node> list = new ArrayList<>();
    private static int[][] board;
    private static ArrayList<Node> meltList = new ArrayList<>();
    private static Queue<Node> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == CHEESE) {
                    list.add(new Node(i, j));
                }
            }
        }

        q.offer(new Node(0, 0));
        board[0][0] = outSideAir;
        checkOutSideAir();

        System.out.println(getMeltTime());
    }

    private static int getMeltTime() {
        int time = 0;

        while (!list.isEmpty()) {
            Iterator<Node> iterator = list.iterator();
            while (iterator.hasNext()) {
                Node cur = iterator.next();
                int x = cur.x;
                int y = cur.y;
                if (isMelt(x, y)) {
                    iterator.remove();
                    meltList.add(new Node(x, y));
                }
            }

            meltCheese();
            time++;
        }

        return time;
    }

    private static boolean isMelt(int x, int y) {
        int airCount = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isValue(nx, ny)) {
                airCount++;
            }
        }

        return airCount >= 2;
    }

    private static boolean isValue(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m && board[x][y] == outSideAir;
    }

    private static void meltCheese() {
        for (Node cur : meltList) {
            board[cur.x][cur.y] = outSideAir;
            q.offer(cur);
        }
        checkOutSideAir();
        meltList.clear();
    }

    private static void checkOutSideAir() {
        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (isOutSideAir(nx, ny)) {
                    board[nx][ny] = outSideAir;
                    q.offer(new Node(nx, ny));
                }
            }
        }
    }

    private static boolean isOutSideAir(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m && board[x][y] == AIR;
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

//tc
/*
input
9 9
0 0 0 0 0 0 0 0 0
0 1 1 1 1 1 1 1 0
0 1 0 0 0 0 0 1 0
0 1 0 0 1 0 0 1 0
0 1 0 1 0 1 0 1 0
0 1 0 0 1 0 0 1 0
0 1 0 0 0 0 0 1 0
0 1 1 1 1 1 1 1 0
0 0 0 0 0 0 0 0 0

ouput
3
*/