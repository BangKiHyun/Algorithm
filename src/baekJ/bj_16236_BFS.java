package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj_16236_BFS {
    private static int n;
    private static int[][] map;
    private static boolean[][] visit;
    private static int mySize = 2;
    private static int time = 0;
    private static int eatingCnt = 0;

    private static int[] dx = {-1, 0, 0, 1};
    private static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        int start_x = 0, start_y = 0;
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    start_x = i;
                    start_y = j;
                }
            }
        }

        goBFS(start_x, start_y);
        System.out.println(time);
    }

    private static void goBFS(int start_x, int start_y) {
        while (true) {
            Queue<Shark> q = new LinkedList<>();
            ArrayList<Shark> sharks = new ArrayList<>();
            visit = new boolean[n][n];

            q.offer(new Shark(start_x, start_y, 0));
            visit[start_x][start_y] = true;
            map[start_x][start_y] = 0;
            int curMoveCnt = -1;

            while (!q.isEmpty()) {
                Shark cur = q.poll();
                if (curMoveCnt == cur.moveCnt) break;

                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if (isRange(nx, ny) && !visit[nx][ny] && map[nx][ny] <= mySize) {
                        if (eatable(nx, ny)) {
                            curMoveCnt = cur.moveCnt + 1;
                            sharks.add(new Shark(nx, ny, cur.moveCnt + 1));
                        }
                        q.offer(new Shark(nx, ny, cur.moveCnt + 1));
                        visit[nx][ny] = true;
                    }
                }
            }
            if (curMoveCnt != -1) {
                Collections.sort(sharks, new sharkComparator());
                Shark me = sharks.get(0);
                time += me.moveCnt;
                start_x = me.x;
                start_y = me.y;

                if (++eatingCnt == mySize) {
                    mySize++;
                    eatingCnt = 0;
                }
            } else break;
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }

    private static boolean eatable(int x, int y) {
        return map[x][y] != 0 && map[x][y] < mySize;
    }

    private static class sharkComparator implements Comparator<Shark> {
        @Override
        public int compare(Shark o1, Shark o2) {
            if (o2.x - o1.x > 0) return -1;
            else if (o1.x == o2.x) {
                return o1.y - o2.y;
            }
            return 1;
        }
    }

    private static class Shark {
        private int x;
        private int y;
        private int moveCnt;

        public Shark(int x, int y, int moveCnt) {
            this.x = x;
            this.y = y;
            this.moveCnt = moveCnt;
        }
    }
}
