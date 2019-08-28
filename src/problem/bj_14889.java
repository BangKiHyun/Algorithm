package problem;

import java.util.Scanner;

public class bj_14889 {
    static int n;
    static int matrix[][];
    static boolean visit[];
    static int min = 987654321;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        matrix = new int[n][n];
        visit = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        dfs(0, 0);
        System.out.println(min);
    }

    static void dfs(int start, int d) {
        if (d == n / 2) {
            min = Math.min(min, compare());
            return;
        }

        for (int i = start; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                dfs(i + 1, d + 1);
                visit[i] = false;
            }
        }
    }

    static int compare() {
        int start = 0;
        int link = 0;
        for (int i = 0; i < n; i++) {
            for(int j=0;j<n;j++){
                if(visit[i] && visit[j]){
                    start += matrix[i][j];
                }else if(!visit[i] && !visit[j]){
                    link += matrix[i][j];
                }
            }
        }
        return Math.abs(start - link);
    }
}
