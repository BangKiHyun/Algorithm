package programmers;

import java.util.ArrayList;

public class pg_43105 {
    static public int solution(int[][] triangle) {
        int max = 0;
        int[][] dp = new int[triangle.length][triangle.length];
        dp[0][0] = triangle[0][0];
        for (int i = 1; i < triangle.length; i++) {
            dp[i][0] = dp[i - 1][0] + triangle[i][0];
            dp[i][i] = dp[i - 1][i - 1] + triangle[i][i];
        }
        for (int i = 2; i < triangle.length; i++) {
            for (int j = 1; j < triangle[i].length - 1; j++) {
                dp[i][j] = Math.max(dp[i-1][j-1] + triangle[i][j], dp[i-1][j] + triangle[i][j]);
            }
        }
        for(int i = 0 ; i<triangle.length;i++){
            for(int j=0;j<triangle[i].length; j++){
                if(dp[i][j] > max){
                    max = dp[i][j];
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] t =
                {{7},
                        {3, 8},
                        {8, 1, 0},
                        {2, 7, 4, 4},
                        {4, 5, 2, 6, 5}};

        int ans = solution(t);
        System.out.println(ans);
    }
}
