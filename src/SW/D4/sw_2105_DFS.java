package SW.D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class sw_2105_DFS {
    private static int[] dx = {1, 1, -1, -1}; //좌하, 우하, 우상, 좌상
    private static int[] dy = {-1, 1, 1, -1};
    private static Set<Integer> set;
    private static boolean[][] visit;
    private static int[][] map;
    private static int start_x, start_y;
    private static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            int n = Integer.parseInt(br.readLine());
            max = 0;
            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                String[] line = br.readLine().split(" ");
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(line[j]);
                }
            }

            for (int i = 0; i < n - 2; i++) {
                for (int j = 1; j < n - 1; j++) {
                    init(n);
                    set.add(map[i][j]);
                    visit[i][j] = true;
                    start_x = i;
                    start_y = j;
                    goDFS(1, i, j, n, 0);
                }
            }
            System.out.println("#" + test_case + " " + (max == 2 ? -1 : max));
        }
    }

    private static boolean goDFS(int cnt, int x, int y, int n, int start) {
        for (int i = start; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx == start_x && ny == start_y) {
                max = Math.max(max, cnt);
                return true;
            }
            if (isRange(nx, ny, n) && !visit[nx][ny] && !set.contains(map[nx][ny])) {
                visit[nx][ny] = true;
                set.add(map[nx][ny]);
                if (goDFS(cnt + 1, nx, ny, n, i)) {
                    return true;
                }
                visit[nx][ny] = false;
                set.remove(map[nx][ny]);
            }
        }
        return false;
    }

    private static boolean isRange(int x, int y, int n) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }

    private static void init(int n) {
        visit = new boolean[n][n];
        set = new HashSet<>();
    }
}
