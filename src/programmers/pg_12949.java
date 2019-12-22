package programmers;

public class pg_12949 {
    public static void main(String[] args) {
        int[][] arr1 = {{2, 3, 2}, {4, 2, 4}, {3, 1, 4}};
        int[][] arr2 = {{5, 4, 3}, {2, 4, 1}, {3, 1, 1}};
        int ans[][] = solution(arr1, arr2);
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[0].length; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr2[0].length];

        int sum, mul;
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2[0].length; j++) {
                sum = 0;
                for (int k = 0; k < arr2.length; k++) {
                    mul = arr1[i][k] * arr2[k][j];
                    sum += mul;
                }
                answer[i][j] = sum;
            }
        }
        return answer;
    }
}
