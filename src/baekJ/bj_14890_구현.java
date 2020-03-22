package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//길을 지나갈 수 있으려면 길에 속한 모든 칸의 높이가 모두 같아야 한다. 또는, 경사로를 놓아서 지나갈 수 있는 길을 만들 수 있다.
//경사로는 높이가 항상 1이며, 길이는 L이다. 또, 개수는 매우 많아 부족할 일이 없다.
//경사로는 낮은 칸과 높은 칸을 연결하며, 아래와 같은 조건을 만족해야한다.
//
//경사로는 낮은 칸에 놓으며, L개의 연속된 칸에 경사로의 바닥이 모두 접해야 한다.
//낮은 칸과 높은 칸의 높이 차이는 1이어야 한다.
//경사로를 놓을 낮은 칸의 높이는 모두 같아야 하고, L개의 칸이 연속되어 있어야 한다.
//아래와 같은 경우에는 경사로를 놓을 수 없다.
//
//경사로를 놓은 곳에 또 경사로를 놓는 경우
//낮은 칸과 높은 칸의 높이 차이가 1이 아닌 경우
//낮은 지점의 칸의 높이가 모두 같지 않거나, L개가 연속되지 않은 경우
//경사로를 놓다가 범위를 벗어나는 경우
public class bj_14890_구현 {
    private static int n, L;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (searchCol(i)) {
                ans++;
            }
            if (searchRow(i)) {
                ans++;
            }
        }

        System.out.println(ans);
    }

    private static boolean searchCol(int idx) {
        boolean[] visit = new boolean[n];

        for (int j = 0; j < n - 1; j++) {
            if (map[idx][j] != map[idx][j + 1]) {
                if (!checkCol(idx, j, visit)) return false;
            }
        }
        return true;
    }

    private static boolean checkCol(int x, int y, boolean[] visit) {
        if (Math.abs(map[x][y] - map[x][y + 1]) > 1) return false;

        if (map[x][y] - map[x][y + 1] > 0) {
            if (y + L >= n) return false;

            for (int j = y + 1; j < y + L; j++) {
                System.out.println(y + " " + map[x][j] + " " + map[x][j + 1]);
                if (map[x][j] != map[x][j + 1] || visit[j] || visit[j + 1]) return false;
                visit[j] = true;
            }
            visit[y + L] = true;

        } else {
            y++;
            if (y - L < 0 || visit[y - 1]) return false;

            for (int j = y - L; j < y - 1; j++) {
                if (map[x][j] != map[x][j + 1] || visit[j] || visit[j + 1]) return false;
                visit[j] = true;
            }
            visit[y - 2] = true;
        }

        return true;
    }

    private static boolean searchRow(int idx) {
        boolean[] visit = new boolean[n];

        for (int i = 0; i < n - 1; i++) {
            if (map[i][idx] != map[i + 1][idx]) {
                if (!checkRow(i, idx, visit)) return false;
            }
        }

        return true;
    }

    private static boolean checkRow(int x, int y, boolean[] visit) {
        if (Math.abs(map[x][y] - map[x + 1][y]) > 1) return false;

        if (map[x][y] - map[x + 1][y] > 0) {
            if (x + L >= n) return false;

            for (int i = x + 1; i < x + L; i++) {
                if (map[i][y] != map[i + 1][y] || visit[i] || visit[i + 1]) return false;
                visit[i] = true;
            }
            visit[x + L] = true;
        } else {
            x++;
            if (x - L < 0 || visit[x - 1]) return false;

            for (int i = x - L; i < x - 1; i++) {
                if (map[i][y] != map[i + 1][y] || visit[i] || visit[i + 1]) return false;
                visit[i] = true;
            }
            visit[x - 1] = true;
        }

        return true;
    }
}
