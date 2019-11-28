package programmers;

public class pg_42898_DP {
    public static void main(String[] args) {
        int m = 4;
        int n = 3;
        int[][] puddles = {{2, 2}};
        int ans = solution(m, n, puddles);
        System.out.println(ans);
    }

    private static int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[m + 1][n + 1];

        for (int[] i : puddles) {
            int r = i[0];
            int c = i[1];
            map[r][c] = -1;
        }

        map[1][1] = 1;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (map[i][j] == -1) {
                    map[i][j] = 0;
                } else {
                    if (i == 1) {
                        map[i][j] += map[i][j - 1];
                    } else {
                        map[i][j] = map[i - 1][j] + map[i][j - 1] % 1000000007;
                    }
                }
            }
        }
        return map[m][n];
    }
}
