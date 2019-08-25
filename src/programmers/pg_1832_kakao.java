package programmers;

public class pg_1832_kakao {

    static int MOD = 20170805;
    static int[] X = {1, 0};
    static int[] Y = {0, 1};
    static boolean visit[][];
    static int cnt = 0;

    public static void main(String[] args) {
        //int map[][] = {{0, 0, 0,}, {0, 0, 0,}, {0, 0, 0,}};
        int map[][] = {{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}};
        int ans = solution(3, 6, map);
        System.out.println(ans);
    }

    static public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        visit = new boolean[m][n];
        dfs(m, n, 0, 0, cityMap);
        answer = cnt;
        return answer;
    }

    static void dfs(int m, int n, int x, int y, int[][] map) {
        if (x == m - 1 && y == n - 1) {
            cnt++;
            System.out.println(x + " " + y);
            return;
        }
        visit[x][y] = true;

        for (int i = 0; i < 2; i++) {
            int nx = x + X[i];
            int ny = y + Y[i];
            System.out.println(x + " " + y);

            if (nx >= 0 && ny >= 0 && nx < m && ny < n && !visit[nx][ny] && map[nx][ny] != 1) {
                if (map[nx][ny] == 0) {
                    dfs(m, n, nx, ny, map);
                } else {
                    if (x == nx && ny != n - 1) {
                        dfs(m, n, nx, ny, map);
                    } else if (y == ny && nx != m - 1) {
                        dfs(m, n, nx, ny, map);
                    }else{
                        break;
                    }
                }
            }
        }
        visit[x][y] = false;
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
