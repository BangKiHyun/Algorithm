package baekJ.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj_7490_0만들기 {
    private static List<String> answer;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int test = Integer.parseInt(st.nextToken());
        for (int testcase = 0; testcase < test; testcase++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            answer = new ArrayList<>();
            dfs(1, 1, 0, 1, "1");
            for (String output : answer) {
                System.out.println(output);
            }
            System.out.println();
        }
    }

    private static void dfs(int idx, int number, int sum, int sign, String strResult) {
        if (n == idx) {
            sum += (number * sign);
            if (sum == 0) {
                answer.add(strResult);
            }
            return;
        }
        dfs(idx + 1, number * 10 + (idx + 1), sum, sign, strResult + " " + (idx + 1));
        dfs(idx + 1, idx + 1, sum + sign * number, 1, strResult + "+" + (idx + 1));
        dfs(idx + 1, idx + 1, sum + sign * number, -1, strResult + "-" + (idx + 1));
    }
}
