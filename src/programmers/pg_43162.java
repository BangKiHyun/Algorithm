package programmers;

import java.util.ArrayList;

public class pg_43162 {
    static int MAX = 201;
    static boolean c[] = new boolean[MAX];
    static ArrayList<Integer> a[] = new ArrayList[MAX];

    static public int solution(int n, int[][] computers) {
        int answer = 0;
        for (int i = 0; i < n; i++) {
            a[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (computers[i][j] == 1) {
                    a[i].add(j);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (!dfs(i)) answer++;
        }
        return answer;
    }

    static public boolean dfs(int x) {
        if (c[x]) return true;
        c[x] = true;
        for (int i : a[x]) {
            dfs(i);
        }
        return false;
    }

    public static void main(String[] args) {
        int n = 3;
        //int p[][] = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int p[][] = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        int cnt = solution(n, p);
        System.out.println(cnt);
    }
}
