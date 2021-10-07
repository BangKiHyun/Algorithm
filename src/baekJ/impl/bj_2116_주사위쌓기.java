package baekJ.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_2116_주사위쌓기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[][] dice = new int[n][6];
        for (int idx = 0; idx < n; idx++) {
            st = new StringTokenizer(br.readLine());
            dice[idx][0] = Integer.parseInt(st.nextToken());
            dice[idx][1] = Integer.parseInt(st.nextToken());
            dice[idx][2] = Integer.parseInt(st.nextToken());
            dice[idx][4] = Integer.parseInt(st.nextToken());
            dice[idx][5] = Integer.parseInt(st.nextToken());
            dice[idx][3] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        for (int i = 0; i < 6; i++) {
            int bottom = dice[0][i];
            int top = dice[0][(i + 3) % 6];
            int sum = getSideNumber(bottom, top);
            for (int j = 1; j < n; j++) {
                for (int k = 0; k < 6; k++) {
                    if (dice[j][k] == top) {
                        bottom = dice[j][k];
                        top = dice[j][(k + 3) % 6];
                        break;
                    }
                }
                sum += getSideNumber(bottom, top);
            }
            max = Math.max(max, sum);
        }
        System.out.println(max);
    }

    private static int getSideNumber(int bottom, int top) {
        if (bottom + top == 11) return 4;
        else if (bottom == 6 || top == 6) return 5;
        else return 6;
    }
}
