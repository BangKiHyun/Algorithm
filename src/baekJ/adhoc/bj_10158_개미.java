package baekJ.adhoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_10158_개미 {
    private static int n, m;
    private static int[] dx = {1, -1, -1, -1};
    private static int[] dy = {1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int time = Integer.parseInt(br.readLine());
        for (int answer : moveAnt(x, y, time)) {
            System.out.print(answer + " ");
        }
    }

    private static int[] moveAnt(int startX, int startY, int time) {
        final Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(startX, startY));

        int dir = 0;
        while (true) {
            final Pos curPos = q.poll();
            if (time-- == 0) {
                q.offer(curPos);
                break;
            }
            int nx = curPos.x + dx[dir];
            int ny = curPos.y + dy[dir];
            if (!isRange(nx, ny)) {
                if (isVertex(curPos.x, curPos.y)) {
                    dir += 2;
                } else {
                    dir++;
                }
                dir = dir % 4;
                nx = curPos.x + dx[dir];
                ny = curPos.y + dy[dir];
            }
            q.offer(new Pos(nx, ny));
        }
        final Pos pos = q.poll();
        return new int[]{pos.x, pos.y};
    }

    private static boolean isVertex(int x, int y) {
        return (x == 0 && y == 0) || (x == 0 && y == m) || (x == n && y == 0) || (x == n && y == m);
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x <= n && y <= m;
    }

    private static class Pos {
        private int x;
        private int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
