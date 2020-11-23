package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Math.*;

public class bj_10819_백트래킹 {
    private static int n;
    private static int[] numArr;
    private static int[] copyArr;
    private static boolean[] visit;
    private static int max = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        numArr = new int[n];
        copyArr = new int[n];
        visit = new boolean[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        goDFS(0);

        System.out.println(max);
    }

    private static void goDFS(int depth) {
        if (depth == n) {
            int sum = 0;
            for (int i = 0; i < n - 1; i++) {
                sum += abs(copyArr[i] - copyArr[i + 1]);
            }
            max = max(max, sum);
        }

        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                copyArr[depth] = numArr[i];
                goDFS(depth + 1);
                visit[i] = false;
            }
        }
    }
}
