package programmers;

public class pg_12913_dp {
    private static final int COL_LENGTH = 4;

    public static void main(String[] args) {
        int[][] land = {{1, 2, 3, 5},
                {5, 6, 7, 8},
                {4, 3, 2, 1}};

        System.out.println(solution(land));
    }

    public static int solution(int[][] land) {
        //각 열마다 돌린다.
        for (int i = 1; i < land.length; i++) {
            //행을 하나씩 비교 하면서 행의 max값을 찾는다.
            for (int j = 0; j < COL_LENGTH; j++) {
                int max = 0;
                for (int k = 0; k < COL_LENGTH; k++) {
                    //현재 행과 그 전의 행이 같다면 continue
                    if (j == k) continue;
                    max = Math.max(max, land[i - 1][k]);
                }
                land[i][j] += max;
            }
        }

        return getMaxValue(land);
    }

    private static int getMaxValue(int[][] land) {
        int max = 0;
        int row = land.length - 1;
        for (int col = 0; col < COL_LENGTH; col++) {
            max = Math.max(max, land[row][col]);
        }

        return max;
    }
}
