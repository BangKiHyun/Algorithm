package programmers;

public class pg_12913 {
    static int solution(int[][] land) {
        int ans = 0;
        for (int i = 1; i < land.length; i++) {
            for (int j = 0; j < 4; j++) {
                int max = -1;
                for (int k = 0; k < 4; k++) {
                    if (j == k) continue;
                    max = Math.max(max, land[i - 1][k]);
                }
                System.out.println(max);
                land[i][j] += max;
            }
        }
        for (int i = 0; i < 4; i++) {
            ans = Math.max(ans, land[land.length - 1][i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int map[][] =
                {{1, 2, 3, 5},
                        {5, 6, 7, 8},
                        {4, 3, 2, 1}};
        int ans = solution(map);
            System.out.println(ans);
    }
}
