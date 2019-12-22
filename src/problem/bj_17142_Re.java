package problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_17142_Re {
    private static int[][] map;
    private static ArrayList<Virus> virusList = new ArrayList<>();
    private static boolean[][] visit;
    private static int ans = Integer.MAX_VALUE;
    private static Queue<Virus> q = new LinkedList<>();
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int notVirus = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    virusList.add(new Virus(i, j, 0, 0));
                    notVirus++;
                } else if (map[i][j] == 0) notVirus++;
            }
        }
        notVirus -= m;
        boolean[] checkVirus = new boolean[virusList.size()];
        activateVirus(n, m, 0, checkVirus);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    private static void activateVirus(int n, int m, int depth, boolean[] checkVirus) {
        if (depth == m) {
            initVisit(n, checkVirus);
            ans = Math.min(ans, spreadVirus(n));
            return;
        }

        for (int i = 0; i < virusList.size(); i++) {
            if (!checkVirus[i]) {
                checkVirus[i] = true;
                map[virusList.get(i).x][virusList.get(i).y] = 3;
                activateVirus(n, m, depth + 1, checkVirus);
                map[virusList.get(i).x][virusList.get(i).y] = 2;
                checkVirus[i] = false;
            }
        }
    }

    private static void initVisit(int n, boolean[] checkVirus) {
        visit = new boolean[n][n];
        Virus virus;
        for (int i = 0; i < virusList.size(); i++) {
            if (checkVirus[i]) {
                virus = virusList.get(i);
                visit[virus.x][virus.y] = true;
                q.add(virus);
            }
        }
    }

    private static int spreadVirus(int n) {
        int spreadTime = -1;
        int spreadCnt = 0;
        while (!q.isEmpty()) {
            Virus virus = q.poll();
            spreadTime = Math.max(spreadTime, virus.cnt);

            for (int i = 0; i < 4; i++) {
                int nx = virus.x + dx[i];
                int ny = virus.y + dy[i];

                if (isRange(nx, ny, n)) {
                    if (isActivateVirus(nx, ny)) q.add(new Virus(nx, ny, virus.cnt, virus.accumulate + 1));
                    else q.add(new Virus(nx, ny, virus.cnt + 1 + virus.accumulate, 0));
                    visit[nx][ny] = true;
                    spreadCnt++;
                }
            }
        }
        return spreadCnt == notVirus ? spreadTime : Integer.MAX_VALUE;
    }

    private static boolean isRange(int x, int y, int n) {
        return x >= 0 && y >= 0 && x < n && y < n && !visit[x][y] && map[x][y] != 1;
    }

    private static boolean isActivateVirus(int x, int y) {
        return map[x][y] == 2;
    }

    private static class Virus {
        private int x;
        private int y;
        private int cnt;
        private int accumulate;

        public Virus(int x, int y, int cnt, int accumulate) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.accumulate = accumulate;
        }
    }
}
