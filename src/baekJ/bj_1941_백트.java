package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_1941_백트 {
    private static final int LENGTH = 5;
    private static final int MAX = 7;

    private static int ans = 0;
    private static char[][] map = new char[LENGTH][LENGTH];
    private static boolean[][] visit = new boolean[LENGTH][LENGTH];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < LENGTH; i++) {
            String line = br.readLine();
            map[i] = line.toCharArray();
        }
        backtracking(0);
    }

    private static void backtracking(int depth) {
        if (depth == MAX) {
            if (isSameLine()) {
                ans++;
            }
            return;
        }

        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                if (!visit[i][j]) {
                    visit[i][j] = true;
                    backtracking(depth + 1);
                    visit[i][j] = false;
                }
            }
        }
    }

    private static boolean isSameLine() {
        boolean isExist = false;
        int measure_i = 0;
        int meqsure_j = 0;
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                if (visit[i][j]) {
                    if (!isExist) {
                        measure_i = i % 5;
                        meqsure_j = j % 5;
                        isExist = true;
                    } else {
                        if(i % 5 == measure_i && j%5 == meqsure_j)
                    }
                }
            }
        }
    }
}
