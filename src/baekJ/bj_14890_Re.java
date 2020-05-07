package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_14890_Re {
    private static int n, len;
    private static int[][] map;
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        len = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (isPassedToRow(i)) cnt++;
            if (isPassedToCol(i)) cnt++;
        }

        System.out.println(cnt);
    }

    private static boolean isPassedToRow(int start) {
        visit = new boolean[n];
        int curHigh = map[start][0];

        for (int j = 1; j < n; j++) {
            int nextHigh = map[start][j];
            if (isDifference(curHigh, nextHigh)) {
                return false;
            }
            if (curHigh == nextHigh) continue;
            else {
                if (canNotLayDownToRow(curHigh, nextHigh, start, j)) {
                    return false;
                }
            }
            curHigh = map[start][j];
        }

        return true;
    }

    private static boolean canNotLayDownToRow(int cur, int next, int i, int pos) {
        if (cur > next) {
            if (canNotLayDownToNextRow(pos, i)) {
                return false;
            }
        } else {
            if (canNotLayDownToBackRow(pos, i)) {
                return false;
            }
        }
        return true;
    }

    private static boolean canNotLayDownToNextRow(int start_pos, int i) {
        if (start_pos + len - 1 >= n || visit[start_pos]) return false;
        for (int pos = start_pos + 1; pos < start_pos + len; pos++) {
            if (visit[pos] || map[i][pos] != map[i][start_pos]) return false;
        }

        for (int j = start_pos; j < start_pos + len; j++) {
            visit[j] = true;
        }

        return true;
    }

    private static boolean canNotLayDownToBackRow(int start_pos, int i) {
        if (start_pos - len < 0 || visit[start_pos - len]) return false;
        for (int pos = start_pos - len + 1; pos < start_pos; pos++) {
            if (visit[pos] || map[i][pos] != map[i][start_pos]) return false;
        }

        for (int j = start_pos - len; j < start_pos; j++) {
            visit[j] = true;
        }
        return true;
    }

    private static boolean isPassedToCol(int start) {
        visit = new boolean[n];
        int curHigh = map[0][start];
        for (int i = 1; i < n; i++) {
            int nextHigh = map[i][start];
            if (isDifference(curHigh, nextHigh)) {
                return false;
            }
            if (curHigh == nextHigh) continue;
            else {
                if (canNotLayDownToCol(curHigh, nextHigh, start, i)) {
                    return false;
                }
            }
            curHigh = map[i][start];
        }

        return true;
    }

    private static boolean canNotLayDownToCol(int cur, int next, int j, int pos) {
        if (cur > next) {
            if (canNotLayDownToNextCol(pos, j)) {
                return false;
            }
        } else {
            if (canNotLayDownToBackCol(pos, j)) {
                return false;
            }
        }

        return true;
    }

    private static boolean canNotLayDownToNextCol(int start_pos, int j) {
        if (start_pos + len - 1 >= n || visit[start_pos]) return false;
        for (int pos = start_pos + 1; pos < start_pos + len; pos++) {
            if (visit[pos] || map[pos][j] != map[start_pos][j]) return false;
        }

        for (int i = start_pos; i < start_pos + len; i++) {
            visit[i] = true;
        }
        return true;
    }

    private static boolean canNotLayDownToBackCol(int start_pos, int j) {
        if (start_pos - len < 0 || visit[start_pos - len]) return false;
        for (int pos = start_pos - len + 1; pos < start_pos; pos++) {
            if (visit[pos] || map[pos][j] != map[start_pos][j]) return false;
        }

        for (int i = start_pos - len; i < start_pos; i++) {
            visit[i] = true;
        }
        return true;
    }

    private static boolean isDifference(int cur, int next) {
        return Math.abs(cur - next) > 1;
    }
}
//방법
//한쪽 방향으로 경사로를 진행한다.
//높이가 1차이나는 경우 경사로를 놓아야한다.
//1.내려가는경우
//내려가는 경우에는 다음 블럭이 경사로 길이보다 길거나 같으면 놓을 수 있다.
//2.올라가는경
//올라가는 경우에는 현재 위치의 블럭에서 뒤의 길이가 경사로의 길이보다 길거나 같으면 놓을 수 있다.
//이미 경사로가 놓아진 경우(boolean true)에는 안된다.